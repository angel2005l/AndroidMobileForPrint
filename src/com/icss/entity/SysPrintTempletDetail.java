package com.icss.entity;

import java.math.BigDecimal;
import java.util.Date;

import android.os.Parcel;

public class SysPrintTempletDetail  {
	private Integer id;

	private Integer templetId;

	private String fieldName;

	private String fieldType;

	private BigDecimal width;

	private BigDecimal height;

	private BigDecimal top;

	private BigDecimal left;

	private String fieldRemark;

	private String fontType;

	private Integer fontSize;

	private String isBold;

	private Date createTime;

	private String creatorId;

	private Date updateTime;

	private String updatorId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTempletId() {
		return templetId;
	}

	public void setTempletId(Integer templetId) {
		this.templetId = templetId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName == null ? null : fieldName.trim();
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType == null ? null : fieldType.trim();
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getTop() {
		return top;
	}

	public void setTop(BigDecimal top) {
		this.top = top;
	}

	public BigDecimal getLeft() {
		return left;
	}

	public void setLeft(BigDecimal left) {
		this.left = left;
	}

	public String getFieldRemark() {
		return fieldRemark;
	}

	public void setFieldRemark(String fieldRemark) {
		this.fieldRemark = fieldRemark == null ? null : fieldRemark.trim();
	}

	public String getFontType() {
		return fontType;
	}

	public void setFontType(String fontType) {
		this.fontType = fontType == null ? null : fontType.trim();
	}

	public Integer getFontSize() {
		return fontSize;
	}

	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}

	public String getIsBold() {
		return isBold;
	}

	public void setIsBold(String isBold) {
		this.isBold = isBold == null ? null : isBold.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId == null ? null : creatorId.trim();
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(String updatorId) {
		this.updatorId = updatorId == null ? null : updatorId.trim();
	}

//	@Override
//	public int describeContents() {
//		// TODO 自动生成的方法存根
//		return 0;
//	}

//	@Override
//	public void writeToParcel(Parcel dest, int flags) {
//		// TODO 自动生成的方法存根
//		dest.writeInt(id);
//		dest.writeInt(templetId);
//		dest.writeString(fieldName);
//		dest.writeString(fieldType);
//
//		dest.writeValue(width);
//		dest.writeString(width == null ? "0" : width.toString());
//		private BigDecimal width;
//
//		private BigDecimal height;
//
//		private BigDecimal top;
//
//		private BigDecimal left;
//
//		private String fieldRemark;
//
//		private String fontType;
//
//		private Integer fontSize;
//
//		private String isBold;
//
//		private Date createTime;
//
//		private String creatorId;
//
//		private Date updateTime;
//
//		private String updatorId;
//	}

	public void readFromParcel(Parcel in) {

	}
}