package com.fydk.productone.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fydk.productone.bean.FydkProduct;
@Mapper
public interface FydkProductMapper extends BaseMapper<FydkProduct>{

	List<Map<String, Object>> selectPageList(Page<Object> page, Map<String, Object> param);
}