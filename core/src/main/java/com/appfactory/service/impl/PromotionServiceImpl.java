package com.appfactory.service.impl;

import com.appfactory.service.PromotionService;
import org.broadleafcommerce.core.catalog.domain.Category;
import org.broadleafcommerce.core.catalog.domain.Product;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/18/13
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("promotionService")
public class PromotionServiceImpl implements PromotionService {

    @PersistenceContext(unitName = "blPU")
    private EntityManager em;

    @Override
    public List<Product> findFeaturedProducts() {
        TypedQuery<Product> query = em.createNamedQuery("FIND_ALL_FEATURED_PRODUCTS", Product.class);
        query.setParameter("currentDate", new Date());
        return query.getResultList();
    }

    @Override
    public List<Category> findPromotableCategories() {
        TypedQuery<Category> query = em.createNamedQuery("FIND_ALL_PROMOTABLE_CATEGORIES", Category.class);
        query.setParameter("currentDate", new Date());
        return query.getResultList();
    }
}
