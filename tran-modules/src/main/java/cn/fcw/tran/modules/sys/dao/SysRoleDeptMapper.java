package cn.fcw.tran.modules.sys.dao;
import cn.fcw.tran.modules.sys.entity.SysRoleDeptEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色与部门对应关系
 *
 * @author admin
 */
@Mapper
public interface SysRoleDeptMapper extends BaseMapper<SysRoleDeptEntity> {

    /**
     * 根据角色ID，获取部门ID列表
     * @param roleIds
     * @return
     */
    List<Long> queryDeptIdList(Long[] roleIds);


    /**
     * 根据角色ID数组，批量删除
     * @param roleIds
     * @return
     */
    int deleteBatch(Long[] roleIds);
}
