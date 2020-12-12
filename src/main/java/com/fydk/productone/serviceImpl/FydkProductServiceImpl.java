package com.fydk.productone.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fydk.productone.bean.FydkProduct;
import com.fydk.productone.mapper.FydkProductMapper;
import com.fydk.productone.service.FydkProductService;
@Service
@Transactional
public class FydkProductServiceImpl extends ServiceImpl<FydkProductMapper,FydkProduct> implements FydkProductService{

	@Override
	public List<Map<String, Object>> selectPageList(Page<Object> page, Map<String, Object> param) {
		return this.baseMapper.selectPageList(page,param);
	}

}
