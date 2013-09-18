package com.mycompany.api.endpoint.appfactory.wrappers;

import org.broadleafcommerce.core.catalog.domain.Category;
import org.broadleafcommerce.core.catalog.domain.Product;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/18/13
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class PromotionsWrapper extends BaseWrapper {

    @XmlElement
    private List<PromotionWrapper> promotions = new ArrayList<PromotionWrapper>();


    public void wrapProducts(List<Product> featuredProducts) {
        for (Product p : featuredProducts) {
            PromotionWrapper wrapper = context.getBean(PromotionWrapper.class);
            wrapper.wrap(p);
            promotions.add(wrapper);
        }
    }

    public void wrapCategories(List<Category> promotableCategories) {
        for (Category c : promotableCategories) {
            PromotionWrapper wrapper = context.getBean(PromotionWrapper.class);
            wrapper.wrap(c);
            promotions.add(wrapper);
        }
    }
}
