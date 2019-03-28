package com.prophet.web.business.metadata.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prophet.web.business.BusinessResultEnum;
import com.prophet.web.business.common.entity.Dict;
import com.prophet.web.business.metadata.entity.ProductEntity;
import com.prophet.web.business.metadata.services.ProductService;
import com.prophet.web.framework.global.model.Result;
import com.prophet.web.framework.global.model.ResultEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: meta product
 * @Author: lvzhh
 * @CreateDate: 2018/12/05
 * @Version: 1.0
 */
@RestController
@RequestMapping("/meta/product")
@Api(value="meta product manage",tags= {"meta product manage"})
public class ProductController {

	@Autowired
	private ProductService productService;
	
	/**
	 * 产品列表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "", notes = "", httpMethod = "GET")
	public Result<ProductEntity> list(){
		Result<ProductEntity> result = new Result<ProductEntity>();
		List<ProductEntity> productList = productService.productList();
		result.successInfo(ResultEnum.SUCCESS.getCode(), productList);
		return result;
	}
	
	/**
	 * 新增
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/save")
	@ApiOperation(value = "", notes = "", httpMethod = "post")
	public Result save(@RequestBody ProductEntity product){
		try {
			productService.productSave(product);
			return new Result().success();
		}catch(DuplicateKeyException e) {
			//产品代码重复
			return new Result().error(BusinessResultEnum.BUS00000.getCode());
		}
	}
	
	
	/**
	 * 产品接入字典
	 */
	@GetMapping("/dict")
	@ApiOperation(value = "", notes = "", httpMethod = "GET")
	public Result<Dict> dict(){
		Result<Dict> result = new Result<Dict>();
		List<Dict> productDictList = productService.productDict();
		result.successInfo(ResultEnum.SUCCESS.getCode(), productDictList);
		return result;
	}
	
	
	
	
}
