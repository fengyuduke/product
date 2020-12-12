package com.fydk.productone.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fydk.productone.bean.FydkProductor;
import com.fydk.productone.mapper.FydkProductorMapper;
import com.fydk.productone.service.FydkProductorService;
@Service
@Transactional
public class FydkProductorServiceImpl extends ServiceImpl<FydkProductorMapper,FydkProductor> implements FydkProductorService{

}
