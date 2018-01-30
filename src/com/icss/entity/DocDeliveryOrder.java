package com.icss.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DocDeliveryOrder implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String deliveryOrderNo;

    private String ownerCode;

    private String erpOrderNo;

    private String preErpOrderNo;

    private String preDeliveryOrderNo;

    private String srcOrderNo;

    private String warehouseCode;

    private String storeCode;

    private String orderType;

    private String orderFlag;

    private String srcPlatformCode;

    private String srcPlatformName;

    private Date orderCreateTime;

    private Date placeOrderTime;

    private Date payTime;

    private String payNo;

    private String operatorCode;

    private String operatorName;

    private Date opeartorTime;

    private String shopNick;

    private String sellerNick;

    private String buyerNick;

    private BigDecimal totalAmount;

    private BigDecimal itemAmount;

    private BigDecimal discAmount;

    private BigDecimal freight;

    private BigDecimal arAmount;

    private BigDecimal gotAmount;

    private BigDecimal serviceFee;

    private String logiCode;

    private String logiName;

    private String waybillType;

    private String isMultLogiNo;

    private String logiNo;

    private String logiAreaCode;

    private String logiProduct;

    private String logiAccount;

    private Integer schedType;

    private String expressSchedType;

    private String schedDay;

    private String schedStartTime;

    private String schedEndTime;

    private String schedArriveTime;

    private String deliveryType;

    private String senderCompany;

    private String senderName;

    private String senderZipcode;

    private String senderTel;

    private String senderMobile;

    private String senderEmail;

    private String senderCountryCode;

    private String senderProvince;

    private String senderCity;

    private String senderArea;

    private String senderTown;

    private String senderAddress;

    private String receiverCompany;

    private String receiverName;

    private String receiverZipcode;

    private String receiverTel;

    private String receiverMobile;

    private String receiverEmail;

    private String receiverCountryCode;

    private String receiverProvince;

    private String receiverCity;

    private String receiverArea;

    private String receiverTown;

    private String receiverAddress;

    private String receiverIdType;

    private String receiverIdNo;

    private String isUrgency;

    private String invoiceFlag;

    private String invoiceType;

    private String invoiceHeader;

    private BigDecimal invoiceAmount;

    private String invoiceContent;

    private String insuranceFlag;

    private String insuranceType;

    private BigDecimal insuranceAmount;

    private String buyerMessage;

    private String sellerMessage;

    private String orderCode;

    private BigDecimal postFee;

    private String prevLogiNo;

    private String cnOrderType;

    private Integer cnOrderSource;

    private String schedStockOutTime;

    private String remark;

    private Integer packQty;

    private Integer skuQty;

    private BigDecimal totalVolume;

    private BigDecimal totalGrossWeight;

    private BigDecimal totalNetWeight;

    private String billName;

    private String routeCode;

    private String routeName;

    private String isPickbillPrinted;

    private String schedStatus;

    private Integer schedCount;

    private String pushStatus;

    private Integer pushCount;

    private String excpCode;

    private String excpMsg;

    private String isInWave;

    private String isErpCanceled;

    private String isInvMgr;

    private String productType;

    private String isPackage;

    private String storageType;

    private String createType;

    private String status;

    private Date deliverTime;

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

    public String getDeliveryOrderNo() {
        return deliveryOrderNo;
    }

    public void setDeliveryOrderNo(String deliveryOrderNo) {
        this.deliveryOrderNo = deliveryOrderNo == null ? null : deliveryOrderNo.trim();
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode == null ? null : ownerCode.trim();
    }

    public String getErpOrderNo() {
        return erpOrderNo;
    }

    public void setErpOrderNo(String erpOrderNo) {
        this.erpOrderNo = erpOrderNo == null ? null : erpOrderNo.trim();
    }

    public String getPreErpOrderNo() {
        return preErpOrderNo;
    }

    public void setPreErpOrderNo(String preErpOrderNo) {
        this.preErpOrderNo = preErpOrderNo == null ? null : preErpOrderNo.trim();
    }

    public String getPreDeliveryOrderNo() {
        return preDeliveryOrderNo;
    }

    public void setPreDeliveryOrderNo(String preDeliveryOrderNo) {
        this.preDeliveryOrderNo = preDeliveryOrderNo == null ? null : preDeliveryOrderNo.trim();
    }

    public String getSrcOrderNo() {
        return srcOrderNo;
    }

    public void setSrcOrderNo(String srcOrderNo) {
        this.srcOrderNo = srcOrderNo == null ? null : srcOrderNo.trim();
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode == null ? null : warehouseCode.trim();
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode == null ? null : storeCode.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(String orderFlag) {
        this.orderFlag = orderFlag == null ? null : orderFlag.trim();
    }

    public String getSrcPlatformCode() {
        return srcPlatformCode;
    }

    public void setSrcPlatformCode(String srcPlatformCode) {
        this.srcPlatformCode = srcPlatformCode == null ? null : srcPlatformCode.trim();
    }

    public String getSrcPlatformName() {
        return srcPlatformName;
    }

    public void setSrcPlatformName(String srcPlatformName) {
        this.srcPlatformName = srcPlatformName == null ? null : srcPlatformName.trim();
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Date getPlaceOrderTime() {
        return placeOrderTime;
    }

    public void setPlaceOrderTime(Date placeOrderTime) {
        this.placeOrderTime = placeOrderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo == null ? null : payNo.trim();
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode == null ? null : operatorCode.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Date getOpeartorTime() {
        return opeartorTime;
    }

    public void setOpeartorTime(Date opeartorTime) {
        this.opeartorTime = opeartorTime;
    }

    public String getShopNick() {
        return shopNick;
    }

    public void setShopNick(String shopNick) {
        this.shopNick = shopNick == null ? null : shopNick.trim();
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick == null ? null : sellerNick.trim();
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick == null ? null : buyerNick.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(BigDecimal itemAmount) {
        this.itemAmount = itemAmount;
    }

    public BigDecimal getDiscAmount() {
        return discAmount;
    }

    public void setDiscAmount(BigDecimal discAmount) {
        this.discAmount = discAmount;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getArAmount() {
        return arAmount;
    }

    public void setArAmount(BigDecimal arAmount) {
        this.arAmount = arAmount;
    }

    public BigDecimal getGotAmount() {
        return gotAmount;
    }

    public void setGotAmount(BigDecimal gotAmount) {
        this.gotAmount = gotAmount;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getLogiCode() {
        return logiCode;
    }

    public void setLogiCode(String logiCode) {
        this.logiCode = logiCode == null ? null : logiCode.trim();
    }

    public String getLogiName() {
        return logiName;
    }

    public void setLogiName(String logiName) {
        this.logiName = logiName == null ? null : logiName.trim();
    }

    public String getWaybillType() {
        return waybillType;
    }

    public void setWaybillType(String waybillType) {
        this.waybillType = waybillType == null ? null : waybillType.trim();
    }

    public String getIsMultLogiNo() {
        return isMultLogiNo;
    }

    public void setIsMultLogiNo(String isMultLogiNo) {
        this.isMultLogiNo = isMultLogiNo == null ? null : isMultLogiNo.trim();
    }

    public String getLogiNo() {
        return logiNo;
    }

    public void setLogiNo(String logiNo) {
        this.logiNo = logiNo == null ? null : logiNo.trim();
    }

    public String getLogiAreaCode() {
        return logiAreaCode;
    }

    public void setLogiAreaCode(String logiAreaCode) {
        this.logiAreaCode = logiAreaCode == null ? null : logiAreaCode.trim();
    }

    public String getLogiProduct() {
        return logiProduct;
    }

    public void setLogiProduct(String logiProduct) {
        this.logiProduct = logiProduct == null ? null : logiProduct.trim();
    }

    public String getLogiAccount() {
        return logiAccount;
    }

    public void setLogiAccount(String logiAccount) {
        this.logiAccount = logiAccount == null ? null : logiAccount.trim();
    }

    public Integer getSchedType() {
        return schedType;
    }

    public void setSchedType(Integer schedType) {
        this.schedType = schedType;
    }

    public String getExpressSchedType() {
        return expressSchedType;
    }

    public void setExpressSchedType(String expressSchedType) {
        this.expressSchedType = expressSchedType == null ? null : expressSchedType.trim();
    }

    public String getSchedDay() {
        return schedDay;
    }

    public void setSchedDay(String schedDay) {
        this.schedDay = schedDay == null ? null : schedDay.trim();
    }

    public String getSchedStartTime() {
        return schedStartTime;
    }

    public void setSchedStartTime(String schedStartTime) {
        this.schedStartTime = schedStartTime == null ? null : schedStartTime.trim();
    }

    public String getSchedEndTime() {
        return schedEndTime;
    }

    public void setSchedEndTime(String schedEndTime) {
        this.schedEndTime = schedEndTime == null ? null : schedEndTime.trim();
    }

    public String getSchedArriveTime() {
        return schedArriveTime;
    }

    public void setSchedArriveTime(String schedArriveTime) {
        this.schedArriveTime = schedArriveTime == null ? null : schedArriveTime.trim();
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType == null ? null : deliveryType.trim();
    }

    public String getSenderCompany() {
        return senderCompany;
    }

    public void setSenderCompany(String senderCompany) {
        this.senderCompany = senderCompany == null ? null : senderCompany.trim();
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    public String getSenderZipcode() {
        return senderZipcode;
    }

    public void setSenderZipcode(String senderZipcode) {
        this.senderZipcode = senderZipcode == null ? null : senderZipcode.trim();
    }

    public String getSenderTel() {
        return senderTel;
    }

    public void setSenderTel(String senderTel) {
        this.senderTel = senderTel == null ? null : senderTel.trim();
    }

    public String getSenderMobile() {
        return senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile == null ? null : senderMobile.trim();
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail == null ? null : senderEmail.trim();
    }

    public String getSenderCountryCode() {
        return senderCountryCode;
    }

    public void setSenderCountryCode(String senderCountryCode) {
        this.senderCountryCode = senderCountryCode == null ? null : senderCountryCode.trim();
    }

    public String getSenderProvince() {
        return senderProvince;
    }

    public void setSenderProvince(String senderProvince) {
        this.senderProvince = senderProvince == null ? null : senderProvince.trim();
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity == null ? null : senderCity.trim();
    }

    public String getSenderArea() {
        return senderArea;
    }

    public void setSenderArea(String senderArea) {
        this.senderArea = senderArea == null ? null : senderArea.trim();
    }

    public String getSenderTown() {
        return senderTown;
    }

    public void setSenderTown(String senderTown) {
        this.senderTown = senderTown == null ? null : senderTown.trim();
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress == null ? null : senderAddress.trim();
    }

    public String getReceiverCompany() {
        return receiverCompany;
    }

    public void setReceiverCompany(String receiverCompany) {
        this.receiverCompany = receiverCompany == null ? null : receiverCompany.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverZipcode() {
        return receiverZipcode;
    }

    public void setReceiverZipcode(String receiverZipcode) {
        this.receiverZipcode = receiverZipcode == null ? null : receiverZipcode.trim();
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel == null ? null : receiverTel.trim();
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile == null ? null : receiverMobile.trim();
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail == null ? null : receiverEmail.trim();
    }

    public String getReceiverCountryCode() {
        return receiverCountryCode;
    }

    public void setReceiverCountryCode(String receiverCountryCode) {
        this.receiverCountryCode = receiverCountryCode == null ? null : receiverCountryCode.trim();
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince == null ? null : receiverProvince.trim();
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity == null ? null : receiverCity.trim();
    }

    public String getReceiverArea() {
        return receiverArea;
    }

    public void setReceiverArea(String receiverArea) {
        this.receiverArea = receiverArea == null ? null : receiverArea.trim();
    }

    public String getReceiverTown() {
        return receiverTown;
    }

    public void setReceiverTown(String receiverTown) {
        this.receiverTown = receiverTown == null ? null : receiverTown.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public String getReceiverIdType() {
        return receiverIdType;
    }

    public void setReceiverIdType(String receiverIdType) {
        this.receiverIdType = receiverIdType == null ? null : receiverIdType.trim();
    }

    public String getReceiverIdNo() {
        return receiverIdNo;
    }

    public void setReceiverIdNo(String receiverIdNo) {
        this.receiverIdNo = receiverIdNo == null ? null : receiverIdNo.trim();
    }

    public String getIsUrgency() {
        return isUrgency;
    }

    public void setIsUrgency(String isUrgency) {
        this.isUrgency = isUrgency == null ? null : isUrgency.trim();
    }

    public String getInvoiceFlag() {
        return invoiceFlag;
    }

    public void setInvoiceFlag(String invoiceFlag) {
        this.invoiceFlag = invoiceFlag == null ? null : invoiceFlag.trim();
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    public String getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(String invoiceHeader) {
        this.invoiceHeader = invoiceHeader == null ? null : invoiceHeader.trim();
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent == null ? null : invoiceContent.trim();
    }

    public String getInsuranceFlag() {
        return insuranceFlag;
    }

    public void setInsuranceFlag(String insuranceFlag) {
        this.insuranceFlag = insuranceFlag == null ? null : insuranceFlag.trim();
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType == null ? null : insuranceType.trim();
    }

    public BigDecimal getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(BigDecimal insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage == null ? null : buyerMessage.trim();
    }

    public String getSellerMessage() {
        return sellerMessage;
    }

    public void setSellerMessage(String sellerMessage) {
        this.sellerMessage = sellerMessage == null ? null : sellerMessage.trim();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public BigDecimal getPostFee() {
        return postFee;
    }

    public void setPostFee(BigDecimal postFee) {
        this.postFee = postFee;
    }

    public String getPrevLogiNo() {
        return prevLogiNo;
    }

    public void setPrevLogiNo(String prevLogiNo) {
        this.prevLogiNo = prevLogiNo == null ? null : prevLogiNo.trim();
    }

    public String getCnOrderType() {
        return cnOrderType;
    }

    public void setCnOrderType(String cnOrderType) {
        this.cnOrderType = cnOrderType == null ? null : cnOrderType.trim();
    }

    public Integer getCnOrderSource() {
        return cnOrderSource;
    }

    public void setCnOrderSource(Integer cnOrderSource) {
        this.cnOrderSource = cnOrderSource;
    }

    public String getSchedStockOutTime() {
        return schedStockOutTime;
    }

    public void setSchedStockOutTime(String schedStockOutTime) {
        this.schedStockOutTime = schedStockOutTime == null ? null : schedStockOutTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getPackQty() {
        return packQty;
    }

    public void setPackQty(Integer packQty) {
        this.packQty = packQty;
    }

    public Integer getSkuQty() {
        return skuQty;
    }

    public void setSkuQty(Integer skuQty) {
        this.skuQty = skuQty;
    }

    public BigDecimal getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(BigDecimal totalVolume) {
        this.totalVolume = totalVolume;
    }

    public BigDecimal getTotalGrossWeight() {
        return totalGrossWeight;
    }

    public void setTotalGrossWeight(BigDecimal totalGrossWeight) {
        this.totalGrossWeight = totalGrossWeight;
    }

    public BigDecimal getTotalNetWeight() {
        return totalNetWeight;
    }

    public void setTotalNetWeight(BigDecimal totalNetWeight) {
        this.totalNetWeight = totalNetWeight;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName == null ? null : billName.trim();
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode == null ? null : routeCode.trim();
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName == null ? null : routeName.trim();
    }

    public String getIsPickbillPrinted() {
        return isPickbillPrinted;
    }

    public void setIsPickbillPrinted(String isPickbillPrinted) {
        this.isPickbillPrinted = isPickbillPrinted == null ? null : isPickbillPrinted.trim();
    }

    public String getSchedStatus() {
        return schedStatus;
    }

    public void setSchedStatus(String schedStatus) {
        this.schedStatus = schedStatus == null ? null : schedStatus.trim();
    }

    public Integer getSchedCount() {
        return schedCount;
    }

    public void setSchedCount(Integer schedCount) {
        this.schedCount = schedCount;
    }

    public String getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(String pushStatus) {
        this.pushStatus = pushStatus == null ? null : pushStatus.trim();
    }

    public Integer getPushCount() {
        return pushCount;
    }

    public void setPushCount(Integer pushCount) {
        this.pushCount = pushCount;
    }

    public String getExcpCode() {
        return excpCode;
    }

    public void setExcpCode(String excpCode) {
        this.excpCode = excpCode == null ? null : excpCode.trim();
    }

    public String getExcpMsg() {
        return excpMsg;
    }

    public void setExcpMsg(String excpMsg) {
        this.excpMsg = excpMsg == null ? null : excpMsg.trim();
    }

    public String getIsInWave() {
        return isInWave;
    }

    public void setIsInWave(String isInWave) {
        this.isInWave = isInWave == null ? null : isInWave.trim();
    }

    public String getIsErpCanceled() {
        return isErpCanceled;
    }

    public void setIsErpCanceled(String isErpCanceled) {
        this.isErpCanceled = isErpCanceled == null ? null : isErpCanceled.trim();
    }

    public String getIsInvMgr() {
        return isInvMgr;
    }

    public void setIsInvMgr(String isInvMgr) {
        this.isInvMgr = isInvMgr == null ? null : isInvMgr.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getIsPackage() {
        return isPackage;
    }

    public void setIsPackage(String isPackage) {
        this.isPackage = isPackage == null ? null : isPackage.trim();
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType == null ? null : storageType.trim();
    }

    public String getCreateType() {
        return createType;
    }

    public void setCreateType(String createType) {
        this.createType = createType == null ? null : createType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
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

	@Override
	public String toString() {
		return "DocDeliveryOrder [id=" + id + ", deliveryOrderNo=" + deliveryOrderNo + ", ownerCode=" + ownerCode
				+ ", erpOrderNo=" + erpOrderNo + ", preErpOrderNo=" + preErpOrderNo + ", preDeliveryOrderNo="
				+ preDeliveryOrderNo + ", srcOrderNo=" + srcOrderNo + ", warehouseCode=" + warehouseCode
				+ ", storeCode=" + storeCode + ", orderType=" + orderType + ", orderFlag=" + orderFlag
				+ ", srcPlatformCode=" + srcPlatformCode + ", srcPlatformName=" + srcPlatformName + ", orderCreateTime="
				+ orderCreateTime + ", placeOrderTime=" + placeOrderTime + ", payTime=" + payTime + ", payNo=" + payNo
				+ ", operatorCode=" + operatorCode + ", operatorName=" + operatorName + ", opeartorTime=" + opeartorTime
				+ ", shopNick=" + shopNick + ", sellerNick=" + sellerNick + ", buyerNick=" + buyerNick
				+ ", totalAmount=" + totalAmount + ", itemAmount=" + itemAmount + ", discAmount=" + discAmount
				+ ", freight=" + freight + ", arAmount=" + arAmount + ", gotAmount=" + gotAmount + ", serviceFee="
				+ serviceFee + ", logiCode=" + logiCode + ", logiName=" + logiName + ", waybillType=" + waybillType
				+ ", isMultLogiNo=" + isMultLogiNo + ", logiNo=" + logiNo + ", logiAreaCode=" + logiAreaCode
				+ ", logiProduct=" + logiProduct + ", logiAccount=" + logiAccount + ", schedType=" + schedType
				+ ", expressSchedType=" + expressSchedType + ", schedDay=" + schedDay + ", schedStartTime="
				+ schedStartTime + ", schedEndTime=" + schedEndTime + ", schedArriveTime=" + schedArriveTime
				+ ", deliveryType=" + deliveryType + ", senderCompany=" + senderCompany + ", senderName=" + senderName
				+ ", senderZipcode=" + senderZipcode + ", senderTel=" + senderTel + ", senderMobile=" + senderMobile
				+ ", senderEmail=" + senderEmail + ", senderCountryCode=" + senderCountryCode + ", senderProvince="
				+ senderProvince + ", senderCity=" + senderCity + ", senderArea=" + senderArea + ", senderTown="
				+ senderTown + ", senderAddress=" + senderAddress + ", receiverCompany=" + receiverCompany
				+ ", receiverName=" + receiverName + ", receiverZipcode=" + receiverZipcode + ", receiverTel="
				+ receiverTel + ", receiverMobile=" + receiverMobile + ", receiverEmail=" + receiverEmail
				+ ", receiverCountryCode=" + receiverCountryCode + ", receiverProvince=" + receiverProvince
				+ ", receiverCity=" + receiverCity + ", receiverArea=" + receiverArea + ", receiverTown=" + receiverTown
				+ ", receiverAddress=" + receiverAddress + ", receiverIdType=" + receiverIdType + ", receiverIdNo="
				+ receiverIdNo + ", isUrgency=" + isUrgency + ", invoiceFlag=" + invoiceFlag + ", invoiceType="
				+ invoiceType + ", invoiceHeader=" + invoiceHeader + ", invoiceAmount=" + invoiceAmount
				+ ", invoiceContent=" + invoiceContent + ", insuranceFlag=" + insuranceFlag + ", insuranceType="
				+ insuranceType + ", insuranceAmount=" + insuranceAmount + ", buyerMessage=" + buyerMessage
				+ ", sellerMessage=" + sellerMessage + ", orderCode=" + orderCode + ", postFee=" + postFee
				+ ", prevLogiNo=" + prevLogiNo + ", cnOrderType=" + cnOrderType + ", cnOrderSource=" + cnOrderSource
				+ ", schedStockOutTime=" + schedStockOutTime + ", remark=" + remark + ", packQty=" + packQty
				+ ", skuQty=" + skuQty + ", totalVolume=" + totalVolume + ", totalGrossWeight=" + totalGrossWeight
				+ ", totalNetWeight=" + totalNetWeight + ", billName=" + billName + ", routeCode=" + routeCode
				+ ", routeName=" + routeName + ", isPickbillPrinted=" + isPickbillPrinted + ", schedStatus="
				+ schedStatus + ", schedCount=" + schedCount + ", pushStatus=" + pushStatus + ", pushCount=" + pushCount
				+ ", excpCode=" + excpCode + ", excpMsg=" + excpMsg + ", isInWave=" + isInWave + ", isErpCanceled="
				+ isErpCanceled + ", isInvMgr=" + isInvMgr + ", productType=" + productType + ", isPackage=" + isPackage
				+ ", storageType=" + storageType + ", createType=" + createType + ", status=" + status
				+ ", deliverTime=" + deliverTime + ", createTime=" + createTime + ", creatorId=" + creatorId
				+ ", updateTime=" + updateTime + ", updatorId=" + updatorId + "]";
	}
    
    
}