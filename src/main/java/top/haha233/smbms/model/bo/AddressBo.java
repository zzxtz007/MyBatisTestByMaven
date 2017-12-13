package top.haha233.smbms.model.bo;

public class AddressBo {
	private Integer id;
	private String postCode;
	private String contact;
	private String addressDesc;
	private String tel;
	private UserBo userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddressDesc() {
		return addressDesc;
	}

	public void setAddressDesc(String addressDesc) {
		this.addressDesc = addressDesc;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public UserBo getUserId() {
		return userId;
	}

	public void setUserId(UserBo userId) {
		this.userId = userId;
	}
}
