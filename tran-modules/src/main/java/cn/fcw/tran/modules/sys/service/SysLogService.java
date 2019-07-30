package cn.fcw.tran.modules.sys.service;
import cn.fcw.tran.modules.sys.entity.SysLogEntity;
import cn.fcw.tran.common.base.PageData;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;

/**
 * 系统日志
 *
 * @author   admin
 */
public interface SysLogService extends IService<SysLogEntity> {

    /**
     *分页查询日志
     * @param params
     * @return
     */
    PageData queryPage(Map<String, Object> params);

}
