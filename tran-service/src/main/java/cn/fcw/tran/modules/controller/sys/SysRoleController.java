package cn.fcw.tran.modules.controller.sys;
import cn.fcw.tran.common.annotation.SysLog;
import cn.fcw.tran.common.base.AjaxResult;
import cn.fcw.tran.common.base.PageData;
import cn.fcw.tran.common.utils.ValidatorUtils;
import cn.fcw.tran.modules.controller.BaseController;
import cn.fcw.tran.modules.sys.entity.SysRoleEntity;
import cn.fcw.tran.modules.sys.service.SysRoleDeptService;
import cn.fcw.tran.modules.sys.service.SysRoleMenuService;
import cn.fcw.tran.modules.sys.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author admin
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:role:list")
    public AjaxResult list(@RequestParam Map<String, Object> params) {
        PageData page = sysRoleService.queryPage(params);
        return AjaxResult.ok().put("page", page);
    }

    /**
     * 角色列表
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:role:select")
    public AjaxResult select() {
        List<SysRoleEntity> list = sysRoleService.list();
        return AjaxResult.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @RequestMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public AjaxResult info(@PathVariable("roleId") Long roleId) {
        SysRoleEntity role = sysRoleService.getById(roleId);
        //查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);
        //查询角色对应的部门
        List<Long> deptIdList = sysRoleDeptService.queryDeptIdList(new Long[]{roleId});
        role.setDeptIdList(deptIdList);
        return AjaxResult.ok().put("role", role);
    }

    /**
     * 保存角色
     */
    @SysLog("保存角色")
    @RequestMapping("/save")
    @RequiresPermissions("sys:role:save")
    public AjaxResult save(@RequestBody SysRoleEntity role) {
        ValidatorUtils.validateEntity(role);
        sysRoleService.saveRole(role);
        return AjaxResult.ok();
    }

    /**
     * 修改角色
     */
    @SysLog("修改角色")
    @RequestMapping("/update")
    @RequiresPermissions("sys:role:update")
    public AjaxResult update(@RequestBody SysRoleEntity role) {
        ValidatorUtils.validateEntity(role);
        sysRoleService.update(role);
        return AjaxResult.ok();
    }

    /**
     * 删除角色
     */
    @SysLog("删除角色")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public AjaxResult delete(@RequestBody Long[] roleIds) {
        sysRoleService.deleteBatch(roleIds);
        return AjaxResult.ok();
    }
}
