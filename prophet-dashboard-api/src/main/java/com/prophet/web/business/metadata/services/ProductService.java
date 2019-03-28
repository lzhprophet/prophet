package com.prophet.web.business.metadata.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prophet.web.business.common.entity.Dict;
import com.prophet.web.business.metadata.entity.ProductEntity;
import com.prophet.web.business.metadata.mapper.ProductMapper;

@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	
	public List<ProductEntity> productList() {
		return productMapper.list();
	}
	
	public void productSave(ProductEntity product) {
		productMapper.save(product);
	}
	
	public List<Dict> productDict() {
		return productMapper.dict();
	}
}
