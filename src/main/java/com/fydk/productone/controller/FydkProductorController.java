package com.fydk.productone.controller;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
import com.fydk.productone.bean.FydkProductor;
import com.fydk.productone.common.ResponseEx;
import com.fydk.productone.common.ResponsePageEx;
import com.fydk.productone.common.StringUtils;
import com.fydk.productone.service.FydkProductorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "生产者", tags = "生产者相关")
@RestController
@RequestMapping("/fydkProductor")
public class FydkProductorController  extends BaseController{
	private final static Logger logger = LoggerFactory.getLogger(FydkProductorController.class);

	@Autowired
	private FydkProductorService fydkProductorService;

	@ApiOperation(value = "新增一个商品生产者")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "productorName", value = "生产者名称", required = true, dataType = "String", example = "1"),
			@ApiImplicitParam(name = "level", value = "级别", required = true, dataType = "String", example = "1"),
			@ApiImplicitParam(name = "telNumber", value = "号码", required = true, dataType = "String", example = "1"), })
	@PostMapping("/addFydkProductor")
	public ResponseEx<Object> addFydkProductor(@RequestParam(required = true) String productorName,
			@RequestParam(required = true) String telNumber, @RequestParam(required = true) String level) {
		try {

			if (telNumber.length() != 11|| !isInteger(telNumber)) {

				return ResponseEx.createError("号码格式不正确！");
			}

			FydkProductor entity = new FydkProductor();

			EntityWrapper<FydkProductor> wrapper = new EntityWrapper<>();

			wrapper.eq("tel_number ", telNumber);

			FydkProductor record = fydkProductorService.selectOne(wrapper);

			if (record != null) {
				return ResponseEx.createError("已存在");
			}
			entity.setProductorName(productorName);
			entity.setLevel(Integer.parseInt(level));
			entity.setTelNumber(telNumber);

			fydkProductorService.insert(entity);
			return ResponseEx.createSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return ResponseEx.createError(e.getMessage());
		}
	}

	@ApiOperation(value = "删除一个商品生产者")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "productorId", value = "产品生产者ID", required = true, dataType = "Long", example = "1"), })
	@DeleteMapping("/deleteFydkProductor")
	public ResponseEx<Object> deleteFydkProductor(@RequestParam(required = true) Long productorId) {
		try {
			fydkProductorService.deleteById(productorId);
			return ResponseEx.createSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return ResponseEx.createError(e.getMessage());
		}
	}

	@ApiOperation(value = "更新商品生产者")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "productorId", value = "产品生产者ID", required = true, dataType = "String", example = "1"),
			@ApiImplicitParam(name = "productorName", value = "产品生产者名称", required = false, dataType = "String", example = "1"),
			@ApiImplicitParam(name = "telNumber", value = "产品生产者号码", required = false, dataType = "String", example = "1"),
			@ApiImplicitParam(name = "level", value = "级别", required = false, dataType = "String", example = "1"), })
	@PostMapping("/updateFydkProductorType")
	public ResponseEx<Object> updateFydkProductorType(@RequestParam(required = true) Long productorId,
			@RequestParam(required = false) String productorName, @RequestParam(required = false) String telNumber,
			@RequestParam(required = false) String level) {
		try {

			FydkProductor entity = fydkProductorService.selectById(productorId);

			if (!StringUtils.isBlank(productorName)) {
				entity.setProductorName(productorName);
			}
			if (!StringUtils.isBlank(level)) {
				entity.setLevel(Integer.parseInt(level));
			}

			if (!StringUtils.isBlank(telNumber)) {
				entity.setTelNumber(telNumber);
			}

			EntityWrapper<FydkProductor> wrapper = new EntityWrapper<>();

			wrapper.eq("tel_number ", telNumber);

			List<FydkProductor> recordList = fydkProductorService.selectList(wrapper);

			for (FydkProductor p : recordList) {
				if (!productorId.equals(p.getProductorId())) {
					return ResponseEx.createError("号码已重复");
				}
			}

			fydkProductorService.updateById(entity);

			return ResponseEx.createSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return ResponseEx.createError(e.getMessage());
		}
	}

	@ApiOperation(value = "商品生产者分页查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "productorName", value = "产品类别名称", required = false, dataType = "String", example = "1"),
			@ApiImplicitParam(name = "level", value = "产品级别", required = false, dataType = "Long", example = "1"),
			@ApiImplicitParam(name = "key", value = "key值", required = true, dataType = "String",example = "1"),
			@ApiImplicitParam(name = "pageSize", value = "每页显示数量", required = true,  example = "1"),
			@ApiImplicitParam(name = "pageIndex", value = "当前页", required = true,  example = "1"), })
	@GetMapping("/selectFydkProductor")
	public ResponsePageEx<Object> selectFydkProductor(@RequestParam(required = false) String productorName,
			@RequestParam(required = false) String level, 
			@RequestParam(required = true) String key, 
			@RequestParam(required = true) int pageSize,
			@RequestParam(required = true) int pageIndex) {
		try {

			Page<Object> page = new Page<>(pageIndex, pageSize);

			EntityWrapper<FydkProductor> wrapper = new EntityWrapper<>();
			if (!StringUtils.isBlank(productorName)) {
				wrapper.like("productor_name", productorName);
			}

			if (!StringUtils.isBlank(level)) {
				wrapper.eq("level", level);
			}

			Page<Map<String, Object>> pageList = fydkProductorService.selectMapsPage(page, wrapper);

			logger.info("*****" + pageList);
			return ResponsePageEx.createSuccess(pageList.getRecords(), page.getTotal(), page.getSize(),
					page.getCurrent());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return ResponsePageEx.createError(e.getMessage());
		}
	}

	@ApiOperation(value = "test3")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "message", value = "信息", required = true, dataType = "String", example = "1"), })
	@GetMapping("/test3")
	public String test3(@RequestParam(required = true) String message) {

		System.out.println("**************" + message);
		return message + "====" + Math.random() + "#############";
	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

}
