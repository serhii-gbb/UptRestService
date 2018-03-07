package com.tech.up.service;

import com.tech.up.dao.CategoryDao;
import com.tech.up.entity.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {

    private CategoryDao categoryDao;


    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }


    @Transactional
    public Category getById(int id) {
        checkCategory(id);

        return categoryDao.findOne(id);
    }


    @Transactional
    public void save(Category category) {
        categoryDao.save(category);
    }


    @Transactional
    public void update(Category category) {
        checkCategory(category.getId());
        categoryDao.save(category);
    }


    @Transactional
    public void delete(int id) {
        checkCategory(id);
        categoryDao.delete(id);
    }


    @Transactional
    public List<Category> getByKeyword(String keyword) {
        return categoryDao.findByKeyword(keyword);
    }


    @Transactional
    protected void checkCategory(int id) {
        if (!categoryDao.exists(id))
            throw new NoSuchElementException(String.format("Category with id: %s is not found!", id));
    }


}
