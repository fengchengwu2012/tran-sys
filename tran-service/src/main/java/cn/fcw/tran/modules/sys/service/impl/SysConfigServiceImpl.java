package cn.fcw.tran.modules.sys.service.impl;
import cn.fcw.tran.common.exception.CustomException;
import cn.fcw.tran.common.base.PageData;
import cn.fcw.tran.common.base.QueryPage;
import cn.fcw.tran.modules.sys.mapper.SysConfigMapper;
import cn.fcw.tran.modules.sys.entity.SysConfigEntity;
import cn.fcw.tran.modules.sys.service.SysConfigService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Map;

/**
 * @author   admin
 */
@Service("sysConfigService")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfigEntity> implements SysConfigService {

    @Autowired
    private SysConfigRedis sysConfigRedis;

    @Override
    public PageData queryPage(Map<String, Object> params) {
        String paramKey = (String) params.get("paramKey");

        IPage<SysConfigEntity> page = this.page(
                new QueryPage<SysConfigEntity>().getPage(params),
                new QueryWrapper<SysConfigEntity>()
                        .like(StringUtils.isNotBlank(paramKey), "param_key", paramKey)
                        .eq("status", 1)
        );

        return new PageData(page);
    }

    @Override
    public void saveConfig(SysConfigEntity config) {
        this.save(config);
        sysConfigRedis.saveOrUpdate(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysConfigEntity config) {
        this.updateById(config);
        sysConfigRedis.saveOrUpdate(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateValueByKey(String key, String value) {
        baseMapper.updateValueByKey(key, value);
        sysConfigRedis.delete(key);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] ids) {
        for (Long id : ids) {
            SysConfigEntity config = this.getById(id);
            sysConfigRedis.delete(config.getParamKey());
        }
        this.removeByIds(Arrays.asList(ids));
    }

    @Override
    public String getValue(String key) {
        SysConfigEntity config = sysConfigRedis.get(key);
        if (config == null) {
            config = baseMapper.queryByKey(key);
            sysConfigRedis.saveOrUpdate(config);
        }

        return config == null ? null : config.getParamValue();
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        String value = getValue(key);
        if (StringUtils.isNotBlank(value)) {
            return JSON.parseObject(value,clazz);
        }

        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new CustomException("获取参数失败");
        }
    }
}
