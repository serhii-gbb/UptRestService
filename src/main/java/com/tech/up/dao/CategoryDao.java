package com.tech.up.dao;

import com.tech.up.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CategoryDao extends JpaRepository<Category, Integer> {


    /**
     * These both method perform the same query to DB! I preferred second, because he has shorter name and,
     * only one parameter that according to clean code convention.
     *
     * @param keyword {@link String}
     * @return {@code List<Category>}
     */

//    List<Category> findCategoriesByNameContainsOrDescriptionContains(String nameKey, String descriptionKey);

    @Query(value = "SELECT c FROM Category c WHERE c.name LIKE %?1% OR c.description LIKE %?1%")
    List<Category> findByKeyword(String keyword);

}
