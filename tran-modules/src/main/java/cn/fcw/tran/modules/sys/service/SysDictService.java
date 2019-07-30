package cn.fcw.tran.modules.sys.service;
import cn.fcw.tran.common.base.PageData;
import cn.fcw.tran.modules.sys.entity.SysDictEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;

/**
 * 数据字典
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysDictService extends IService<SysDictEntity> {

    PageData queryPage(Map<String, Object> params);
}

