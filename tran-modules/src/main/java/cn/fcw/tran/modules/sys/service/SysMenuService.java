package cn.fcw.tran.modules.sys.service;
import cn.fcw.tran.modules.sys.entity.SysMenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;


/**
 * 菜单管理
 *
 * @author admin
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     * @return
     */
    List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @return
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     * @return
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 获取用户菜单列表
     * @param  userId
     * @return
     */
    List<SysMenuEntity> getUserMenuList(Long userId);

    /**
     * 删除
     * @param  menuId
     */
    void delete(Long menuId);
}
