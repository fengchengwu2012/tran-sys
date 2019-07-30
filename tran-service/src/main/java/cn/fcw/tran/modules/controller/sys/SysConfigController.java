package cn.fcw.tran.modules.controller.sys;
import cn.fcw.tran.common.annotation.SysLog;
import cn.fcw.tran.common.base.AjaxResult;
import cn.fcw.tran.common.base.PageData;
import cn.fcw.tran.common.utils.ValidatorUtils;
import cn.fcw.tran.modules.controller.BaseController;
import cn.fcw.tran.modules.sys.entity.SysConfigEntity;
import cn.fcw.tran.modules.sys.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * 系统配置信息
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends BaseController {

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 所有配置列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:config:list")
    public AjaxResult list(@RequestParam Map<String, Object> params) {
        PageData page = sysConfigService.queryPage(params);
        return AjaxResult.ok().put("page", page);
    }


    /**
     * 配置信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id) {
        SysConfigEntity config = sysConfigService.getById(id);

        return AjaxResult.ok().put("config", config);
    }

    /**
     * 保存配置
     */
    @SysLog("保存配置")
    @RequestMapping("/save")
    @RequiresPermissions("sys:config:save")
    public AjaxResult save(@RequestBody SysConfigEntity config) {
        ValidatorUtils.validateEntity(config);
        sysConfigService.saveConfig(config);
        return AjaxResult.ok();
    }

    /**
     * 修改配置
     */
    @SysLog("修改配置")
    @RequestMapping("/update")
    @RequiresPermissions("sys:config:update")
    public AjaxResult update(@RequestBody SysConfigEntity config) {
        ValidatorUtils.validateEntity(config);
        sysConfigService.update(config);
        return AjaxResult.ok();
    }

    /**
     * 删除配置
     */
    @SysLog("删除配置")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public AjaxResult delete(@RequestBody Long[] ids) {
        sysConfigService.deleteBatch(ids);
        return AjaxResult.ok();
    }

}
