package com.prophet.web.business.metadata.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prophet.web.business.common.entity.Dict;
import com.prophet.web.business.metadata.entity.ProductEntity;

@Mapper
public interface ProductMapper {

	List<ProductEntity> list();
	
	void save(ProductEntity product);
	
	List<Dict> dict();
}