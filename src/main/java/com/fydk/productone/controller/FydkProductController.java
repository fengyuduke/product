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
import com.fydk.productone.common.ResponseEx;
import com.fydk.productone.common.ResponsePageEx;
import com.fydk.productone.common.StringUtils;
import com.fydk.productone.service.FydkProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "产品", tags = "产品设置相关")
@RestController
@RequestMapping("/fydkProduct")
public class FydkProductController extends BaseController{

	private final static Logger logger = LoggerFactory.getLogger(FydkProductController.class);
	@Autowired
	private FydkProductService fydkProductService;

	@ApiOperation(value = "新增一个商品")
	@ApiImplicitParams({ @ApiImplicitParam(name = "productName", value = "产品名称", required = true, dataType = "String",example = "1"),
			@ApiImplicitParam(name = "productCode", value = "产品编号", required = true, dataType = "String",example = "1"),
			@ApiImplicitParam(name = "productType", value = "产品类型", required = true, dataType = "Long",example = "1"),
			
			})
	@PostMapping("/addFydkProduct")
	public ResponseEx<Object> addFydkProduct(@RequestParam(required = true) String key,@RequestParam(required = true) String productName,
			@RequestParam(required = true) String productCode, @RequestParam(required = true) Long productType) {
		try {

			FydkProduct entity = new FydkProduct();

			entity.setProductName(productName);
			entity.setProductCode(productCode);
			entity.setProductType(productType);
			
			EntityWrapper<FydkProduct> wrapper = new EntityWrapper<>();
			
			wrapper.eq("product_code ", productCode);
			
			FydkProduct record = fydkProductService.selectOne(wrapper);
			
			if(record!= null) {
				return ResponseEx.createError("已存在");
			}

			fydkProductService.insert(entity);
			return ResponseEx.createSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return ResponseEx.createError(e.getMessage());
		}
	}

	@ApiOperation(value = "删除一个商品")
	@ApiImplicitParams({ @ApiImplicitParam(name = "productId", value = "产品ID", required = true, dataType = "String",example = "1"), })
	@DeleteMapping("/deleteFydkProduct")
	public ResponseEx<Object> deleteFydkProduct(@RequestParam(required = true) String key,@RequestParam(required = true) Long productId) {
		try {
			fydkProductService.deleteById(productId);
			return ResponseEx.createSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return ResponseEx.createError(e.getMessage());
		}
	}

	@ApiOperation(value = "更新商品")
	@ApiImplicitParams({ @ApiImplicitParam(name = "productId", value = "产品ID", required = true, dataType = "String",example = "1"),
			@ApiImplicitParam(name = "productName", value = "产品名称", required = false, dataType = "String",example = "1"),
			@ApiImplicitParam(name = "productType", value = "产品类型", required = false, dataType = "Long",example = "1"),
			@ApiImplicitParam(name = "productCode", value = "产品编码", required = false, dataType = "String",example = "1"), })
	@PostMapping("/updateFydkProduct")
	public ResponseEx<Object> updateFydkProduct(@RequestParam(required = true) String key,@RequestParam(required = true) Long productId,
			@RequestParam(required = false) String productName, @RequestParam(required = false) Long productType,
			@RequestParam(required = false) String productCode) {
		try {

			FydkProduct entity = fydkProductService.selectById(productId);

			if (!StringUtils.isBlank(productName)) {
				entity.setProductName(productName);
			}
			if (productType != null) {
				entity.setProductType(productType);
			}
			if (productCode != null) {
				entity.setProductCode(productCode);
			}
			
			EntityWrapper<FydkProduct> wrapper = new EntityWrapper<>();
			
			wrapper.eq("product_code ", productCode);
			
			List<FydkProduct> recordList = fydkProductService.selectList(wrapper);
			
			for(FydkProduct p : recordList) {
				if(!productId.equals(p.getProductId())) {
					return ResponseEx.createError("编号已重复");
				}
			}

			fydkProductService.updateById(entity);

			return ResponseEx.createSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return ResponseEx.createError(e.getMessage());
		}
	}

	@ApiOperation(value = "商品分页查询")
	@ApiImplicitParams({ @ApiImplicitParam(name = "productName", value = "产品名称", required = false, dataType = "String",example = "1"),
			@ApiImplicitParam(name = "productType", value = "产品类型", required = false, dataType = "Long",example = "1"),
			@ApiImplicitParam(name = "pageSize", value = "每页显示数量", required = true,example = "1"),
			@ApiImplicitParam(name = "pageIndex", value = "当前页", required = true,example = "1"), })
	@GetMapping("/selectFydkProduct")
	public ResponsePageEx<Object> selectFydkProduct(@RequestParam(required = true) String key,@RequestParam(required = false) String productName,
			@RequestParam(required = false) Long productType, @RequestParam(required = true) int pageSize,
			@RequestParam(required = true) int pageIndex) {
		try {

			Page<Object> page = new Page<>(pageIndex,pageSize);

			Map<String, Object> param = new HashMap<>();

			if (!StringUtils.isBlank(productName)) {
				param.put("productName", productName);
			}

			if (productType != null) {
				param.put("productType", productType);
			}

			List<Map<String, Object>> list = fydkProductService.selectPageList(page, param);

			return ResponsePageEx.createSuccess(list, page.getTotal(), page.getSize(), page.getCurrent());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return ResponsePageEx.createError(e.getMessage());
		}
	}

	@ApiOperation(value = "test1")
	@ApiImplicitParams({ @ApiImplicitParam(name = "message", value = "信息", required = true, dataType = "String",example = "1"), })
	@GetMapping("/test1")
	public String test1(@RequestParam(required = true) String message) {

		System.out.println("**************" + message);
		return message + "====" + Math.random()+"";
	}

}
