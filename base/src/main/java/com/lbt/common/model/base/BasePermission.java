package com.lbt.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BasePermission<M extends BasePermission<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	/**
	 * 角色ID
	 */
	public void setRoleId(java.lang.Integer roleId) {
		set("roleId", roleId);
	}
	
	/**
	 * 角色ID
	 */
	public java.lang.Integer getRoleId() {
		return getInt("roleId");
	}

	/**
	 * 模块ID
	 */
	public void setModuleId(java.lang.Integer moduleId) {
		set("moduleId", moduleId);
	}
	
	/**
	 * 模块ID
	 */
	public java.lang.Integer getModuleId() {
		return getInt("moduleId");
	}

	public void setCreateDate(java.util.Date createDate) {
		set("createDate", createDate);
	}
	
	public java.util.Date getCreateDate() {
		return get("createDate");
	}

	public void setUpdateDate(java.util.Date updateDate) {
		set("updateDate", updateDate);
	}
	
	public java.util.Date getUpdateDate() {
		return get("updateDate");
	}

}
