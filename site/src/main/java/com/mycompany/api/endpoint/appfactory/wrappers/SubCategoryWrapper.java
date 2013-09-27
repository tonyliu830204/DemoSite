package com.mycompany.api.endpoint.appfactory.wrappers;


import org.broadleafcommerce.core.catalog.domain.Category;
import org.broadleafcommerce.core.catalog.domain.Product;
import org.broadleafcommerce.core.catalog.service.CatalogService;
import org.broadleafcommerce.core.web.api.wrapper.APIWrapper;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-9
 * Time: PM9:56
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SubCategoryWrapper extends BaseWrapper implements APIWrapper<Category> {

    @Resource(name = "blCatalogService")
    @XmlTransient
    private CatalogService catalogService;

    @XmlElementWrapper(name = "categories")
    @XmlElement(name = "category")
    private List<AppFactoryCategoryWrapper> categories = new ArrayList<AppFactoryCategoryWrapper>();

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private List<AppFactoryProductWrapper> products = new ArrayList<AppFactoryProductWrapper>();

    @Override
    public void wrapDetails(Category model, HttpServletRequest request) {
        wrapSubProducts(model, request);
        wrapSubCategories(model, request);
    }

    private void wrapSubCategories(Category model, HttpServletRequest request) {
        List<Category> subCategories = catalogService.findActiveSubCategoriesByCategory(model);
        for (Category category : subCategories) {
            AppFactoryCategoryWrapper categoryWrapper = context.getBean(AppFactoryCategoryWrapper.class.getName(), AppFactoryCategoryWrapper.class);
            categoryWrapper.wrapSummary(category, request);
            this.categories.add(categoryWrapper);
        }
    }

    private void wrapSubProducts(Category model, HttpServletRequest request) {
        List<Product> subProducts = catalogService.findActiveProductsByCategory(model, new Date());
        for (Product product : subProducts) {
            AppFactoryProductWrapper productWrapper = context.getBean(AppFactoryProductWrapper.class.getName(), AppFactoryProductWrapper.class);
            productWrapper.wrapSummary(product, request);
            this.products.add(productWrapper);
        }
    }

    @Override
    public void wrapSummary(Category model, HttpServletRequest request) {
        wrapDetails(model, request);
    }
}
