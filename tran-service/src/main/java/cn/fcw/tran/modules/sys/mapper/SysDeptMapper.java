package cn.fcw.tran.modules.sys.mapper;
import cn.fcw.tran.modules.sys.entity.SysDeptEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDeptEntity> {

    /**
     * 查询部门列表
     * @param params
     * @return
     */
    List<SysDeptEntity> queryList(Map<String, Object> params);

    /**
     *查询子部门
     * @param parentId
     * @return
     */
    List<Long> queryDetpIdList(Long parentId);

}
