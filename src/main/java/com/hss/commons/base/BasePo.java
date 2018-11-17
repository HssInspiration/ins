package com.hss.commons.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 代码描述:基类
 * FileName:com.hss.commons.base.BasePo
 *
 * @author: Dckj_Lxh
 * @date: 2018/11/16 15:50
 * @mdate: 2018/11/16 15:50
 * @Modified By:
 * Copyright (c) 2008-2018 鼎昌科技 All Rights Reserved.
 */
public class BasePo implements Serializable {
	private static final long serialVersionUID = 6377951362890878816L;
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	private              Long id;

	/**
	 * 创建日期
	 */
	@Column(name = "create_date")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;

	/**
	 * 修改日期
	 */
	@Column(name = "update_date")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateDate;

	/**
	 * 创建人id
	 */
	@Column(name = "create_user_id")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long createUserId;

	/**
	 * 创建人id
	 */
	@Column(name = "create_user_name")
	private String createUsername;

	/**
	 * 修改人id
	 */
	@Column(name = "update_user_id")
	@JsonSerialize(
			using = ToStringSerializer.class
	)
	private Long updateUserId;

	/**
	 * 修改人id
	 */
	@Column(
			name = "update_user_name"
	)
	private String updateUsername;
	/**
	 * 备注
	 */
	@Column(name = "remarks")
	private String remarks;

	/**
	 * 逻辑删除标记
	 */
	@Column(name = "del_flag")
	private Integer delFlag;

	/**
	 * 扩展字段
	 */
	@Transient
	private Map<String, String> extend;

	public BasePo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUsername() {
		return createUsername;
	}

	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateUsername() {
		return updateUsername;
	}

	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Map<String, String> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, String> extend) {
		this.extend = extend;
	}

	@Override
	public String toString() {
		return "BasePo{" +
				"id=" + this.getId() +
				", createDate=" + this.getCreateDate() +
				", updateDate=" + this.getUpdateDate() +
				", createUserId=" + this.getCreateUserId() +
				", createUsername='" + this.getCreateUsername() + '\'' +
				", updateUserId=" + this.getUpdateUserId() +
				", updateUsername='" + this.getUpdateUsername() + '\'' +
				", remarks='" + this.getRemarks() + '\'' +
				", delFlag=" + this.getDelFlag() +
				'}';
	}
}
