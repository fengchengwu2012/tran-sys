package cn.fcw.tran.service;

import cn.fcw.tran.common.annotation.DataSource;
import cn.fcw.tran.modules.sys.dao.SysUserMapper;
import cn.fcw.tran.modules.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试多数据源
 *
 * @author admin
 */
@Service
//@DataSource("slave1")
public class DynamicDataSourceTestService {

    @Autowired
    private SysUserMapper sysUserDao;

    @Transactional
    public void updateUser(Long id) {
        SysUserEntity user = new SysUserEntity();
        user.setUserId(id);
        user.setMobile("13500000000");
        sysUserDao.updateById(user);
    }

    @Transactional
    @DataSource("slave1")
    public void updateUserBySlave1(Long id) {
        SysUserEntity user = new SysUserEntity();
        user.setUserId(id);
        user.setMobile("13500000001");
        sysUserDao.updateById(user);
    }

    @DataSource("slave2")
    @Transactional
    public void updateUserBySlave2(Long id) {
        SysUserEntity user = new SysUserEntity();
        user.setUserId(id);
        user.setMobile("13500000002");
        sysUserDao.updateById(user);
        //测试事物
        int i = 1 / 0;
    }
}