package com.careline.interview.test.model;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class HUser {
	private String id;
	private String email;
	private String newEmailConfirm;

	private String name;
	private String password;

	private String oldPassword;
	private String newPassword;
	private String newPasswordConfirm;

	private String errorMsg;

	private MultipartFile picture;

	public String getId() {
		return id;
	}

	/**
	 * @return the newEmailConfirm
	 */
	public String getNewEmailConfirm() {
		return newEmailConfirm;
	}

	/**
	 * @param newEmailConfirm the newEmailConfirm to set
	 */
	public void setNewEmailConfirm(String newEmailConfirm) {
		this.newEmailConfirm = newEmailConfirm;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
