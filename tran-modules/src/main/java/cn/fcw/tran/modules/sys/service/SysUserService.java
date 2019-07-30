package cn.fcw.tran.modules.sys.service;
import cn.fcw.tran.common.base.PageData;
import cn.fcw.tran.modules.sys.entity.SysUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author  admin
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 查询用户列表
     * @param params
     * @return
     */
    PageData queryPage(Map<String, Object> params);

    /**
     * 查询用户的所有菜单ID
     * @param userId
     * @return
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 保存用户
     * @param user
     */
    void saveUser(SysUserEntity user);

    /**
     * 修改用户
     * @param
     */
    void update(SysUserEntity user);

    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     * @return
     */
    boolean updatePassword(Long userId, String password, String newPassword);
}
