package cn.fcw.tran.modules.controller.sys;
import cn.fcw.tran.common.base.AjaxResult;
import cn.fcw.tran.common.base.PageData;
import cn.fcw.tran.modules.sys.service.SysLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("sys:log:list")
    public AjaxResult list(@RequestParam Map<String, Object> params) {
        PageData page = sysLogService.queryPage(params);
        return AjaxResult.ok().put("page", page);
    }

}
