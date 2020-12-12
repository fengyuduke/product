package com.fydk.productone.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fydk.productone.bean.FydkProduct;
import com.fydk.productone.bean.FydkProductType;
import com.fydk.productone.bean.FydkProductor;
import com.fydk.productone.common.ResponseEx;
import com.fydk.productone.common.ResponsePageEx;
import com.fydk.productone.common.StringUtils;
import com.fydk.productone.service.FydkProductService;
import com.fydk.productone.service.FydkProductTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "产品类型",tags = "产品类型设置相关")
@RestController
@RequestMapping("/fydkProductType")
public class FydkProductTypeController {
	private final static Logger logger = LoggerFactory.getLogger(FydkProductTypeController.class);
	@Autowired
	private FydkProductTypeService fydkProductTypeService;
	
	@Autowired
	private FydkProductService fydkProductService;
	
	

	@ApiOperation(value = "新增一个商品类型")
	@ApiImplicitParams({ @ApiImplicitParam(name = "productTypeName", value = "产品类型名称", required = true, dataType = "String",example = "1"),
		@ApiImplicitParam(name = "level", value = "级别", required = true, dataType = "String",example = "1"),
			})
	@PostMapping("/addFydkProductType")
	public ResponseEx<Object> addFydkProductType(@RequestParam(required = true) String productTypeName,
			@RequestParam(required = true) String level) {
		try {

			FydkProductType entity = new FydkProductType();

			entity.setProductTypeName(productTypeName);
			entity.setLevel(Integer.parseInt(level));
			
			EntityWrapper<FydkProductType> wrapper = new EntityWrapper<>();
			
			wrapper.eq("product_type_name ", productTypeName);
			
			FydkProductType record = fydkProductTypeService.selectOne(wrapper);
			
			if(record!= null) {
				return ResponseEx.createError("已存在");
			}

			fydkProductTypeService.insert(entity);
			return ResponseEx.createSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return ResponseEx.createError(e.getMessage());
		}
	}

	@ApiOperation(value = "删除一个商品类别")
	@ApiImplicitParams({ @ApiImplicitParam(name = "productTypeId", value = "产品类别ID", required = true, dataType = "Long",example = "1"), })
	@DeleteMapping("/deleteFydkProductType")
	public ResponseEx<Object> deleteFydkProductType(@RequestParam(required = true) Long productTypeId) {
		try {
			fydkProductTypeService.deleteById(productTypeId);
			
			EntityWrapper<FydkProduct> wrapper = new EntityWrapper<>();
			
			wrapper.eq("product_type", productTypeId);
			
			fydkProductService.delete(wrapper);
			
			return ResponseEx.createSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return ResponseEx.createError(e.getMessage());
		}
	}

	@ApiOperation(value = "更新商品类别")
	@ApiImplicitParams({ @ApiImplicitParam(name = "productTypeId", value = "产品ID", required = true, dataType = "String",example = "1"),
			@ApiImplicitParam(name = "productTypeName", value = "产品类型名称", required = false, dataType = "String",example = "1"),
			@ApiImplicitParam(name = "level", value = "级别", required = false, dataType = "String",example = "1"),
	})
	@PostMapping("/updateFydkProductType")
	public ResponseEx<Object> updateFydkProductType(@RequestParam(required = true) Long productTypeId,
			@RequestParam(required = false) String productTypeName, @RequestParam(required = false) String level) {
		try {

			FydkProductType entity = fydkProductTypeService.selectById(productTypeId);

			if (!StringUtils.isBlank(productTypeName)) {
				entity.setProductTypeName(productTypeName);
			}
			if (!StringUtils.isBlank(level)) {
				entity.setLevel(Integer.parseInt(level));
			}

			EntityWrapper<FydkProductType> wrapper = new EntityWrapper<>();
			
			wrapper.eq("product_type_name ", productTypeName);
			
			List<FydkProductType> recordList = fydkProductTypeService.selectList(wrapper);
			
			for(FydkProductType p : recordList) {
				if(!productTypeId.equals(p.getTypeId())) {
					return ResponseEx.createError("商品类型名称已存在");
				}
			}
			
			fydkProductTypeService.updateById(entity);

			return ResponseEx.createSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return ResponseEx.createError(e.getMessage());
		}
	}

	@ApiOperation(value = "商品类别分页查询")
	@ApiImplicitParams({ @ApiImplicitParam(name = "productTypeName", value = "产品类别名称", required = false, dataType = "String",example = "1"),
			@ApiImplicitParam(name = "level", value = "产品级别", required = false, dataType = "Long",example = "1"),
			@ApiImplicitParam(name = "pageSize", value = "每页显示数量", required = true,example = "1"),
			@ApiImplicitParam(name = "pageIndex", value = "当前页", required = true,example = "1"), })
	@GetMapping("/selectFydkProductType")
	public ResponsePageEx<Object> selectFydkProductType(@RequestParam(required = false) String productTypeName,
			@RequestParam(required = false) String level, @RequestParam(required = true) int pageSize,
			@RequestParam(required = true) int pageIndex) {
		try {

			Page<Object> page = new Page<>(pageIndex,pageSize);

			EntityWrapper<FydkProductType> wrapper = new EntityWrapper<>();
			if (!StringUtils.isBlank(productTypeName)) {
				wrapper.like("product_type_name", productTypeName);
			}

			if (!StringUtils.isBlank(level)) {
				wrapper.eq("level", level);
			}

			Page<Map<String, Object>> pageList = fydkProductTypeService.selectMapsPage(page, wrapper);

			logger.info("*****"+ pageList);
			return ResponsePageEx.createSuccess(pageList.getRecords(), page.getTotal(), page.getSize(), page.getCurrent());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return ResponsePageEx.createError(e.getMessage());
		}
	}

	@ApiOperation(value = "test2")
	@ApiImplicitParams({ @ApiImplicitParam(name = "message", value = "信息", required = true, dataType = "String",example = "1"), })
	@GetMapping("/test2")
	public String test1(@RequestParam(required = true) String message) {

		System.out.println("**************" + message);
		return message + "====" + Math.random();
	}
}
