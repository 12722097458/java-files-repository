package com.igeek.shop.service.mybatisspring;

import com.igeek.shop.entity.Product;
import com.igeek.shop.entity.ProductCondition;
import com.igeek.shop.mapper.ProductMapper;
import com.igeek.shop.service.ProductService;
import com.igeek.shop.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void selectByCondition(Product condition, PageUtils pageUtils) {

        ProductCondition productCondition = new ProductCondition();
        productCondition.setCount(pageUtils.getPageSize());
        productCondition.setConditionBean(condition);

        try {
            // 符合条件的总记录数
            int totalCount = productMapper.countByCondition(productCondition);
            // 根据总记录数和每页显示的记录数，算出总页码
            int totalPages = totalCount / pageUtils.getPageSize();
            if (totalCount % pageUtils.getPageSize() != 0) {
                totalPages++;
            }

            pageUtils.setTotalCount(totalCount);
            pageUtils.setTotalPages(totalPages);

            if (pageUtils.getPageNo() <= 0) {
                pageUtils.setPageNo(1);
            } else if (pageUtils.getPageNo() > totalPages && totalPages > 0) {
                pageUtils.setPageNo(totalPages);
            }

            // 算出查询的起始下标
            int beginIndex = (pageUtils.getPageNo() - 1) * pageUtils.getPageSize();

            productCondition.setBeginIndex(beginIndex);

            // 根据条件及分页信息，查询出指定页的数据集
            List<Product> selectByCondition = productMapper.selectByCondition(productCondition);

            pageUtils.setData(selectByCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectByCondition(ProductCondition productCondition, PageUtils pageUtils) {

        try {
            // 符合条件的总记录数
            int totalCount = productMapper.countByCondition(productCondition);
            // 根据总记录数和每页显示的记录数，算出总页码
            int totalPages = totalCount / pageUtils.getPageSize();
            if (totalCount % pageUtils.getPageSize() != 0) {
                totalPages++;
            }

            pageUtils.setTotalCount(totalCount);
            pageUtils.setTotalPages(totalPages);

            if (pageUtils.getPageNo() <= 0) {
                pageUtils.setPageNo(1);
            } else if (pageUtils.getPageNo() > totalPages && totalPages > 0) {
                pageUtils.setPageNo(totalPages);
            }

            // 算出查询的起始下标
            int beginIndex = (pageUtils.getPageNo() - 1) * pageUtils.getPageSize();

            productCondition.setBeginIndex(beginIndex);
            productCondition.setCount(pageUtils.getPageSize());

            // 根据条件及分页信息，查询出指定页的数据集
            List<Product> selectByCondition = productMapper.selectByCondition(productCondition);

            pageUtils.setData(selectByCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product selectById(String pid) {
        Product product = null;
        try {
            product = productMapper.selectById(pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Integer deleteByPid(String pid) {
        int result = 0;
        try {
            result = productMapper.delete(pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insertProduct(Product product) {
        int result = 0;
        try {
            result = productMapper.insert(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer countByCondition(Product condition) {
        Integer result = null;
        ProductCondition productCondition = new ProductCondition();
        productCondition.setConditionBean(condition);
        try {
            result = productMapper.countByCondition(productCondition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer updateProduct(Product product) {
        Integer result = null;
        Product sqlProduct = this.selectById(product.getPid());
        //日期不进行修改
        if (sqlProduct!=null) {
            product.setPdate(sqlProduct.getPdate());
            //图片上传与否进行判断
            if (product.getPimage() == null) {
                product.setPimage(sqlProduct.getPimage());
            }
        }
        try {
            result = productMapper.update(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Product> selectHotProducts(Integer beginIndex, Integer pageSize) {
        ProductCondition productCondition = new ProductCondition();
        productCondition.setCount(pageSize);
        productCondition.setBeginIndex(beginIndex);
        return productMapper.selectHotProducts(productCondition);
    }

    @Override
    public List<Product> selectNewProducts(Integer beginIndex, Integer pageSize) {
        ProductCondition productCondition = new ProductCondition();
        productCondition.setCount(pageSize);
        productCondition.setBeginIndex(beginIndex);
        return productMapper.selectNewProducts(productCondition);
    }
}
