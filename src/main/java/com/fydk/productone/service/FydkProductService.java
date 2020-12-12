package com.fydk.productone.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fydk.productone.bean.FydkProduct;

public interface FydkProductService extends IService<FydkProduct>{

	List<Map<String, Object>> selectPageList(Page<Object> page, Map<String, Object> param);

}
