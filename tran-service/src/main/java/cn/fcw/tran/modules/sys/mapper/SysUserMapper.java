package cn.fcw.tran.modules.sys.mapper;
import cn.fcw.tran.modules.sys.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统用户
 *
 * @author admin
 */
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUserEntity> {


    /**
     * 查询用户的所有权限
     * @param userId
     * @return
     */
    List<String> queryAllPerms(Long userId);


    /**
     * 查询用户的所有菜单ID
     * @param userId
     * @return
     */
    List<Long> queryAllMenuId(Long userId);

}
