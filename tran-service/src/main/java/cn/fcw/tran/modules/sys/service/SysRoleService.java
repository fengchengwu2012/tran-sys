package cn.fcw.tran.modules.sys.service;
import cn.fcw.tran.common.base.PageData;
import cn.fcw.tran.modules.sys.entity.SysRoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;


/**
 * 角色
 *
 * @author admin
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 查询角色列表
     * @param params
     * @return
     */
    PageData queryPage(Map<String, Object> params);

    /**
     * 保存角色
     * @param role
     */
    void saveRole(SysRoleEntity role);

    /**
     * 更新角色
     * @param role
     */
    void update(SysRoleEntity role);

    /**
     * 批量删除角色
     * @param roleIds
     */
    void deleteBatch(Long[] roleIds);

}
