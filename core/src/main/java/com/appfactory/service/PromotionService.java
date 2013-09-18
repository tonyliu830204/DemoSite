package com.appfactory.service;

import org.broadleafcommerce.core.catalog.domain.Category;
import org.broadleafcommerce.core.catalog.domain.Product;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/18/13
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PromotionService {

    List<Product> findFeaturedProducts();

    List<Category> findPromotableCategories();

}
