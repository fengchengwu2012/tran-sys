package cn.fcw.tran.modules.controller.sys;
import cn.fcw.tran.common.annotation.SysLog;
import cn.fcw.tran.common.base.AjaxResult;
import cn.fcw.tran.common.group.AddGroup;
import cn.fcw.tran.common.group.UpdateGroup;
import cn.fcw.tran.common.utils.AssertUtils;
import cn.fcw.tran.common.base.PageData;
import cn.fcw.tran.common.utils.ValidatorUtils;
import cn.fcw.tran.modules.controller.BaseController;
import cn.fcw.tran.modules.sys.entity.SysUserEntity;
import cn.fcw.tran.modules.sys.service.SysUserRoleService;
import cn.fcw.tran.modules.sys.service.SysUserService;
import cn.fcw.tran.modules.sys.shiro.ShiroUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author  fengchengwu
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public AjaxResult list(@RequestParam Map<String, Object> params){
		PageData page = sysUserService.queryPage(params);
		return AjaxResult.ok().put("page", page);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/info")
	public AjaxResult info(){
		return AjaxResult.ok().put("user", getUser());
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@RequestMapping("/password")
	public AjaxResult password(String password, String newPassword){
		AssertUtils.isBlank(newPassword, "新密码不为能空");
		password = ShiroUtils.sha256(password, getUser().getSalt());
		newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return AjaxResult.error("原密码不正确");
		}
		return AjaxResult.ok();
	}
	
	/**
	 * 用户信息
	 */
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public AjaxResult info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.getById(userId);
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		return AjaxResult.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	public AjaxResult save(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		sysUserService.saveUser(user);
		return AjaxResult.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
	public AjaxResult update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);
		sysUserService.update(user);
		return AjaxResult.ok();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public AjaxResult delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return AjaxResult.error("系统管理员不能删除");
		}
		if(ArrayUtils.contains(userIds, getUserId())){
			return AjaxResult.error("当前用户不能删除");
		}
		sysUserService.removeByIds(Arrays.asList(userIds));
		return AjaxResult.ok();
	}
}
