package cn.fcw.tran.modules.sys.service;
import cn.fcw.tran.modules.sys.entity.SysRoleDeptEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色与部门对应关系
 *
 * @author admin
 */
public interface SysRoleDeptService extends IService<SysRoleDeptEntity> {

    /**
     *保存更新
     * @param roleId
     * @param deptIdList
     */
    void saveOrUpdate(Long roleId, List<Long> deptIdList);

    /**
     * 根据角色ID，获取部门ID列表
     * @param  roleIds
     * @return
     */
    List<Long> queryDeptIdList(Long[] roleIds);

    /**
     * 根据角色ID数组，批量删除
     * @param   roleIds
     * @return
     */
    int deleteBatch(Long[] roleIds);


}
