package cn.fcw.tran.modules.sys.service.impl;
import cn.fcw.tran.common.base.PageData;
import cn.fcw.tran.common.base.QueryPage;
import cn.fcw.tran.modules.sys.dao.SysLogMapper;
import cn.fcw.tran.modules.sys.entity.SysLogEntity;
import cn.fcw.tran.modules.sys.service.SysLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Administrator
 */
@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogEntity> implements SysLogService {

    @Override
    public PageData queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        IPage<SysLogEntity> page = this.page(
                new QueryPage<SysLogEntity>().getPage(params),
                new QueryWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key), "username", key)
        );
        return new PageData(page);
    }
}
