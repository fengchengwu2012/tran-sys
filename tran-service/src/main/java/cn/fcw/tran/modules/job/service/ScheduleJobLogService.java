package cn.fcw.tran.modules.job.service;
import cn.fcw.tran.common.base.PageData;
import cn.fcw.tran.modules.job.entity.ScheduleJobLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;

/**
 * 定时任务日志
 *
 * @author admin
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

    PageData queryPage(Map<String, Object> params);
}
