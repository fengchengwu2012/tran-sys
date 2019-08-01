package cn.fcw.tran.modules.sys.service;
import cn.fcw.tran.modules.sys.entity.SysDeptEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author   adin
 */
public interface SysDeptService extends IService<SysDeptEntity> {

    /**
     * 查询满足条件的所有机构部门
     * @param map
     * @return
     */
    List<SysDeptEntity> queryList(Map<String, Object> map);

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     * @return
     */
    List<Long> queryDeptIdList(Long parentId);

    /**
     * 获取子部门ID，用于数据过滤
     * @param deptId
     * @return
     */
    List<Long> getSubDeptIdList(Long deptId);

}
