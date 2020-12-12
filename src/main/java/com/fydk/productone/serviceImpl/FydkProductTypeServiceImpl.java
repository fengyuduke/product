package com.fydk.productone.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fydk.productone.bean.FydkProductType;
import com.fydk.productone.mapper.FydkProductTypeMapper;
import com.fydk.productone.service.FydkProductTypeService;
@Service
@Transactional
public class FydkProductTypeServiceImpl extends ServiceImpl<FydkProductTypeMapper,FydkProductType> implements FydkProductTypeService{

}
