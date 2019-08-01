package cn.fcw.tran.modules.controller.job;
import cn.fcw.tran.common.annotation.SysLog;
import cn.fcw.tran.common.base.AjaxResult;
import cn.fcw.tran.common.base.PageData;
import cn.fcw.tran.common.utils.ValidatorUtils;
import cn.fcw.tran.modules.job.entity.ScheduleJobEntity;
import cn.fcw.tran.modules.job.service.ScheduleJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * 定时任务
 *
 * @author admin
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {

    @Autowired
    private ScheduleJobService scheduleJobServiceImpl;

    /**
     * 定时任务列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:schedule:list")
    public AjaxResult list(@RequestParam Map<String, Object> params) {
        PageData page = scheduleJobServiceImpl.queryPage(params);
        return AjaxResult.ok().put("page", page);
    }

    /**
     * 定时任务信息
     */
    @RequestMapping("/info/{jobId}")
    @RequiresPermissions("sys:schedule:info")
    public AjaxResult info(@PathVariable("jobId") Long jobId) {
        ScheduleJobEntity schedule = scheduleJobServiceImpl.getById(jobId);
        return AjaxResult.ok().put("schedule", schedule);
    }

    /**
     * 保存定时任务
     */
    @SysLog("保存定时任务")
    @RequestMapping("/save")
    @RequiresPermissions("sys:schedule:save")
    public AjaxResult save(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobServiceImpl.save(scheduleJob);
        return AjaxResult.ok();
    }

    /**
     * 修改定时任务
     */
    @SysLog("修改定时任务")
    @RequestMapping("/update")
    @RequiresPermissions("sys:schedule:update")
    public AjaxResult update(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobServiceImpl.update(scheduleJob);
        return AjaxResult.ok();
    }

    /**
     * 删除定时任务
     */
    @SysLog("删除定时任务")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:schedule:delete")
    public AjaxResult delete(@RequestBody Long[] jobIds) {
        scheduleJobServiceImpl.deleteBatch(jobIds);
        return AjaxResult.ok();
    }

    /**
     * 立即执行任务
     */
    @SysLog("立即执行任务")
    @RequestMapping("/run")
    @RequiresPermissions("sys:schedule:run")
    public AjaxResult run(@RequestBody Long[] jobIds) {
        scheduleJobServiceImpl.run(jobIds);
        return AjaxResult.ok();
    }

    /**
     * 暂停定时任务
     */
    @SysLog("暂停定时任务")
    @RequestMapping("/pause")
    @RequiresPermissions("sys:schedule:pause")
    public AjaxResult pause(@RequestBody Long[] jobIds) {
        scheduleJobServiceImpl.pause(jobIds);
        return AjaxResult.ok();
    }

    /**
     * 恢复定时任务
     */
    @SysLog("恢复定时任务")
    @RequestMapping("/resume")
    @RequiresPermissions("sys:schedule:resume")
    public AjaxResult resume(@RequestBody Long[] jobIds) {
        scheduleJobServiceImpl.resume(jobIds);
        return AjaxResult.ok();
    }

}
