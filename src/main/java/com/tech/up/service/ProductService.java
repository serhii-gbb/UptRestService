package com.tech.up.service;

import com.tech.up.dao.ProductDao;
import com.tech.up.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class ProductService {

    private ProductDao productDao;
    private CategoryService categoryService;


    public ProductService(ProductDao productDao, CategoryService categoryService) {
        this.productDao = productDao;
        this.categoryService = categoryService;
    }


    @Transactional
    public Product getProductById(int id) {
        checkProduct(id);

        return productDao.findOne(id);
    }


    @Transactional
    public void save(Product product) {
        if (product.getCategory() == null) throw new NoSuchElementException("The category is not inputted");
        categoryService.checkCategory(product.getCategory().getId());
        productDao.save(product);
    }


    @Transactional
    public void update(Product product) {
        final int categoryId = product.getCategory().getId();

        checkProduct(product.getId());
        categoryService.checkCategory(categoryId);

        productDao.save(product);
    }


    @Transactional
    public void delete(int id) {
        checkProduct(id);
        productDao.delete(id);
    }


    @Transactional
    protected void checkProduct(int id) {
        if (!productDao.exists(id))
            throw new NoSuchElementException(String.format("Product with id: %s is not found!", id));
    }
}
