package cn.fcw.tran.modules.sys.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.fcw.tran.modules.sys.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 *
 * @author admin
 */
@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfigEntity> {

    /**
     * 根据key，查询value
     * @param paramKey
     * @return
     */
    SysConfigEntity queryByKey(String paramKey);


    /**
     * 根据key，更新value
     * @param paramKey
     * @param paramValue
     * @return
     */
    int updateValueByKey(@Param("paramKey") String paramKey, @Param("paramValue") String paramValue);

}
