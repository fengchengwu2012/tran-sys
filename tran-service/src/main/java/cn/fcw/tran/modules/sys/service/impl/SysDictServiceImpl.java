package cn.fcw.tran.modules.sys.service.impl;
import cn.fcw.tran.common.base.PageData;
import cn.fcw.tran.common.base.QueryPage;
import cn.fcw.tran.modules.sys.mapper.SysDictMapper;
import cn.fcw.tran.modules.sys.entity.SysDictEntity;
import cn.fcw.tran.modules.sys.service.SysDictService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;


/**
 * @author Administrator
 */
@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDictEntity> implements SysDictService {

    @Override
    public PageData queryPage(Map<String, Object> params) {
        String name = (String) params.get("name");
        IPage<SysDictEntity> page = this.page(
                new QueryPage<SysDictEntity>().getPage(params),
                new QueryWrapper<SysDictEntity>()
                        .like(StringUtils.isNotBlank(name), "name", name)
        );
        return new PageData(page);
    }

}
