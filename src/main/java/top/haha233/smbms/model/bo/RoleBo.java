package top.haha233.smbms.model.bo;

import java.util.Date;

public class RoleBo {
	private int id;
	private String roleCode;
	private String roleName;
	private UserBo createBy;
	private Date createDate;
	private UserBo modifyBy;
	private Date modifyDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public UserBo getCreateBy() {
		return createBy;
	}

	public void setCreateBy(UserBo createBy) {
		this.createBy = createBy;
	}

	public void setModifyBy(UserBo modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
