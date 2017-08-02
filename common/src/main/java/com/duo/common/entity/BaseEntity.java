/**
 * 实体类公共字段
 */
package com.duo.common.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Entity支持类
 * 
 * @author ThinkGem
 * @version 2014-05-16
 */
// @SupTreeList
public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 实体编号（唯一标识）
	 */
	protected String id;
	protected String remarks; // 备注
	protected Date createDate; // 创建日期
	protected Date updateDate; // 更新日期
	protected String createBy; // 创建日期
	protected String updateBy; // 更新日期
	protected String delFlag; // 删除标记（0：正常；1：删除；2：审核）
	//
	// /**
	// * 当前实体分页对象
	// */
	// protected Page<T> page;

	/**
	 * 自定义SQL（SQL标识，SQL内容）
	 */
	protected Map<String, String> sqlMap;

	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	protected boolean isNewRecord = false;

	public BaseEntity() {

	}

	public BaseEntity(String id) {
		this();
		this.id = id;
	}

	
	
	// @JsonIgnore
	// @XmlTransient
	// public Page<T> getPage() {
	// if (page == null){
	// page = new Page<T>();
	// }
	// return page;
	// }
	//
	// public Page<T> setPage(Page<T> page) {
	// this.page = page;
	// return page;
	// }

	// @JsonIgnore
	// @XmlTransient
	// public Map<String, String> getSqlMap() {
	// if (sqlMap == null){
	// sqlMap = Maps.newHashMap();
	// }
	// return sqlMap;
	// }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public boolean isNewRecord() {
		return isNewRecord;
	}

	public void setNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}

	public Map<String, String> getSqlMap() {
		return sqlMap;
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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}

	/**
	 * 插入之前执行方法
	 */
	public void preInsert() {
		// isNewRecord 为 true则id为传入id,为false则为自动生成id
		if (!isNewRecord || id == null || id == "") {
			UUID uuid = UUID.randomUUID();
			this.id = uuid.toString();
		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;

	};

	/**
	 * 更新之前执行方法
	 */
	public void preUpdate() {
		this.updateDate = new Date();
	}

	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	public void setIsNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		BaseEntity<?> that = (BaseEntity<?>) obj;
		return null == this.getId() ? false : this.getId().equals(that.getId());
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	/**
	 * 删除标记（0：正常；1：删除；2：审核；）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
