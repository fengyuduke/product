package com.fydk.productone.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2020-12-10
 */
public class FydkProductType {
    /**
     * 主键
     */
	@TableId(value = "type_id",type = IdType.AUTO)
    private Long typeId;

    /**
     * 类型名称
     */
    private String productTypeName;

    /**
     * 级别
     */
    private Integer level;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName == null ? null : productTypeName.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}