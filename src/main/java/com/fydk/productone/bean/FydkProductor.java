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
public class FydkProductor {
    /**
     * 主键
     */
	@TableId(value = "productor_id",type = IdType.AUTO)
    private Long productorId;

    private String productorName;
    
    private String telNumber;
    
    private String password;

    /**
     * 级别
     */
    private Integer level;
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public Long getProductorId() {
        return productorId;
    }

    public void setProductorId(Long productorId) {
        this.productorId = productorId;
    }

    public String getProductorName() {
        return productorName;
    }

    public void setProductorName(String productorName) {
        this.productorName = productorName == null ? null : productorName.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}