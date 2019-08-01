package cn.fcw.tran.modules.job.mapper;
import cn.fcw.tran.modules.job.entity.ScheduleJobEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.Map;

/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface ScheduleJobMapper extends BaseMapper<ScheduleJobEntity> {
    /**
     * 批量更新状态
     * @param   map
     * @return
     */
    int updateBatch(Map<String, Object> map);
}
