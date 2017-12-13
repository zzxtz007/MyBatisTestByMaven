package top.haha233.smbms.model.bo;

import java.util.Date;
import java.util.List;

public class UserBo {
	private Integer id;
	private String userCode;
	private String userName;
	private String userPassword;
	private Integer gender;
	private Date birthday;
	private String phone;
	private String address;
	private RoleBo userRole;
	private UserBo createBy;
	private Date createDate;
	private UserBo modifyBy;
	private Date modifyDate;
	private List<AddressBo> addressList;

	public List<AddressBo> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressBo> addressList) {
		this.addressList = addressList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public RoleBo getUserRole() {
		return userRole;
	}

	public void setUserRole(RoleBo userRole) {
		this.userRole = userRole;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public UserBo getCreateBy() {
		return createBy;
	}

	public void setCreateBy(UserBo createBy) {
		this.createBy = createBy;
	}

	public UserBo getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(UserBo modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
