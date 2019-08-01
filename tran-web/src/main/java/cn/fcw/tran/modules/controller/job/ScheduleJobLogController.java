package cn.fcw.tran.modules.controller.job;
import cn.fcw.tran.common.base.AjaxResult;
import cn.fcw.tran.common.base.PageData;
import cn.fcw.tran.modules.job.entity.ScheduleJobLogEntity;
import cn.fcw.tran.modules.job.service.ScheduleJobLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {

    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    /**
     * 定时任务日志列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:schedule:log")
    public AjaxResult list(@RequestParam Map<String, Object> params) {
        PageData page = scheduleJobLogService.queryPage(params);

        return AjaxResult.ok().put("page", page);
    }

    /**
     * 定时任务日志信息
     */
    @RequestMapping("/info/{logId}")
    public AjaxResult info(@PathVariable("logId") Long logId) {
        ScheduleJobLogEntity log = scheduleJobLogService.getById(logId);

        return AjaxResult.ok().put("log", log);
    }
}
