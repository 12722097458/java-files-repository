package com.igeek.shop.mapper;

import com.igeek.shop.entity.Product;
import com.igeek.shop.entity.ProductCondition;

import java.util.List;

public interface ProductMapper extends BaseDao<Product> {

    public abstract List<Product> selectByCondition(ProductCondition condition);
    public abstract Integer countByCondition(ProductCondition condition);

    public abstract List<Product> selectNewProducts(ProductCondition condition);
    public abstract List<Product> selectHotProducts(ProductCondition condition);

}
