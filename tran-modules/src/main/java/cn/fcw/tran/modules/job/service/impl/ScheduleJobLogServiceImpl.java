package cn.fcw.tran.modules.job.service.impl;
import cn.fcw.tran.modules.job.mapper.ScheduleJobLogMapper;
import cn.fcw.tran.modules.job.entity.ScheduleJobLogEntity;
import cn.fcw.tran.modules.job.service.ScheduleJobLogService;
import cn.fcw.tran.common.base.PageData;
import cn.fcw.tran.common.base.QueryPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * 查询任务调度列表
 * @author   admin
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLogEntity> implements ScheduleJobLogService {
    @Override
    public PageData queryPage(Map<String, Object> params) {
        String jobId = (String) params.get("jobId");
        IPage<ScheduleJobLogEntity> page = this.page(new QueryPage<ScheduleJobLogEntity>().getPage(params),
                new QueryWrapper<ScheduleJobLogEntity>().like(StringUtils.isNotBlank(jobId), "job_id", jobId)
        );
        return new PageData(page);
    }
}
