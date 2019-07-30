package cn.fcw.tran.modules.api.mapper;
import cn.fcw.tran.modules.api.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 * @author admin
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}
