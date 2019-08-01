$(function () {
    //初始化角色列表
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/role/list',
        datatype: "json",
        colModel: [
            {label: '角色ID', name: 'roleId', index: "role_id", width: 45, key: true},
            {label: '角色名称', name: 'roleName', index: "role_name", width: 100},
            //{label: '所属部门', name: 'deptName', sortable: false, width: 75,hidedlg:true},
            {label: '备注', name: 'remark', width: 70},
            {label: '创建时间', name: 'createTime', index: "create_time", width: 80}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

//菜单树
let menuZTree;
let menuSetting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "menuId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    },
    check: {
        enable: true,
        nocheckInherit: true
    }
};

//部门结构树
let deptZTree;
let deptSetting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    }
};

//数据树
let dataZTree;
let dataSetting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    },
    check: {
        enable: true,
        nocheckInherit: true,
        chkboxType: {"Y": "", "N": ""}
    }
};

let vm = new Vue({
    el: '#tran-app',
    data: {
        q: {
            roleName: null
        },
        showList: true,
        title: null,
        role: {
            deptId: null,
            deptName: null
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.role = {deptName: null, deptId: null};
            vm.getMenuTree(null);
            vm.getDept();
            vm.getDataTree();
        },
        update: function () {
            let roleId = getSelectedRow();
            if (roleId == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.getDataTree();
            vm.getMenuTree(roleId);
            vm.getDept();
        },
        del: function () {
            let roleIds = getSelectedRows();
            if (roleIds == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/role/delete",
                    contentType: "application/json",
                    data: JSON.stringify(roleIds),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function () {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getRole: function (roleId) {
            $.get(baseURL + "sys/role/info/" + roleId, function (r) {
                vm.role = r.role;
                //勾选角色所拥有的菜单
                let menuIds = vm.role.menuIdList;
                for (let i = 0; i < menuIds.length; i++) {
                    let node = menuZTree.getNodeByParam("menuId", menuIds[i]);
                    menuZTree.checkNode(node, true, false);
                }
                //勾选角色所拥有的部门数据权限
                let deptIds = vm.role.deptIdList;
                for (let i = 0; i < deptIds.length; i++) {
                    let node = dataZTree.getNodeByParam("deptId", deptIds[i]);
                    dataZTree.checkNode(node, true, false);
                }

                vm.getDept();
            });
        },
        saveOrUpdate: function () {

            //获取选择的菜单
            let menuNodes = menuZTree.getCheckedNodes(true);
            let menuIdList = [];
            for (let i = 0; i < menuNodes.length; i++) {
                menuIdList.push(menuNodes[i].menuId);
            }
            vm.role.menuIdList = menuIdList;

            //获取选择的数据
            let deptNodes = dataZTree.getCheckedNodes(true);
            let deptIdList = [];
            for (let i = 0; i < deptNodes.length; i++) {
                deptIdList.push(deptNodes[i].deptId);
            }
            vm.role.deptIdList = deptIdList;
            let url = vm.role.roleId == null ? "sys/role/save" : "sys/role/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.role),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function () {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        getMenuTree: function (roleId) {
            //加载菜单树
            $.get(baseURL + "sys/menu/list", function (r) {
                menuZTree = $.fn.zTree.init($("#menuTree"), menuSetting, r);
                //展开所有节点
                menuZTree.expandAll(true);

                if (roleId != null) {
                    vm.getRole(roleId);
                }
            });
        },
        getDataTree: function (roleId) {
            //加载菜单树
            $.get(baseURL + "sys/dept/list", function (r) {
                dataZTree = $.fn.zTree.init($("#dataTree"), dataSetting, r);
                //展开所有节点
                dataZTree.expandAll(true);
            });
        },
        getDept: function () {
            //加载部门树
            $.get(baseURL + "sys/dept/list", function (r) {
                deptZTree = $.fn.zTree.init($("#deptTree"), deptSetting, r);
                let node = deptZTree.getNodeByParam("deptId", vm.role.deptId);
                if (node != null) {
                    deptZTree.selectNode(node);
                    vm.role.deptName = node.name;
                }
            })
        },
        deptTree: function () {
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择部门",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    let node = deptZTree.getSelectedNodes();
                    //选择上级部门
                    vm.role.deptId = node[0].deptId;
                    vm.role.deptName = node[0].name;
                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'roleName': vm.q.roleName},
                page: page
            }).trigger("reloadGrid");
        }
    }
});