package com.fydk.productone.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 
 * 
 * @author luoc
 * 
 * @date 2020-12-10
 */
public class FydkProduct {
    /**
     * 产品ID主键
     */
	@TableId(value = "product_id",type = IdType.AUTO)
    private Long productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品编号
     */
    private String productCode;

    /**
     * 产品类型
     */
    private Long productType;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public Long getProductType() {
        return productType;
    }

    public void setProductType(Long productType) {
        this.productType = productType;
    }
}