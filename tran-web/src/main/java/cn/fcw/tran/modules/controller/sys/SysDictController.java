package cn.fcw.tran.modules.controller.sys;
import cn.fcw.tran.common.base.AjaxResult;
import cn.fcw.tran.common.base.PageData;
import cn.fcw.tran.common.utils.ValidatorUtils;
import cn.fcw.tran.modules.sys.entity.SysDictEntity;
import cn.fcw.tran.modules.sys.service.SysDictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.Map;

/**
 * 数据字典
 *
 * @author   admin
 */
@RestController
@RequestMapping("sys/dict")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dict:list")
    public AjaxResult list(@RequestParam Map<String, Object> params) {
        PageData page = sysDictService.queryPage(params);

        return AjaxResult.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:dict:info")
    public AjaxResult info(@PathVariable("id") Long id) {
        SysDictEntity dict = sysDictService.getById(id);

        return AjaxResult.ok().put("dict", dict);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:dict:save")
    public AjaxResult save(@RequestBody SysDictEntity dict) {
        //校验类型
        ValidatorUtils.validateEntity(dict);
        sysDictService.save(dict);
        return AjaxResult.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:dict:update")
    public AjaxResult update(@RequestBody SysDictEntity dict) {
        //校验类型
        ValidatorUtils.validateEntity(dict);
        sysDictService.updateById(dict);
        return AjaxResult.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dict:delete")
    public AjaxResult delete(@RequestBody Long[] ids) {
        sysDictService.removeByIds(Arrays.asList(ids));
        return AjaxResult.ok();
    }

}
