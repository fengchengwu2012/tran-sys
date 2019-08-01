package cn.fcw.tran.modules.sys.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 机构、部门管理
 *
 * @author admin
 */
@Data
@TableName("sys_dept")
public class SysDeptEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 部门ID
	 */
	@TableId
	private Long deptId;

	/**
	 * 上级部门ID，一级部门为0
	 */
	private Long parentId;

	/**
	 * 部门名称
	 */
	private String name;

	/**
	 * 等级  1 一级医院  2 二级医院  3 三级医院 4 医院所属科室，与三级医院有父子关系
	 */
	private Integer  level;

	/**
	 *排序
	 */
	private Integer orderNum;

	@TableLogic
	private Integer delFlag;


	/**
	 * ztree属性
	 */
	@TableField(exist=false)
	private Boolean open;

	/**
	 * 上级部门名称
	 */
	@TableField(exist=false)
	private String parentName;

	@TableField(exist=false)
	private List<?> list;

}
