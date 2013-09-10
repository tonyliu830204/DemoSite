package com.mycompany.api.endpoint.appfactory.wrappers;


import org.broadleafcommerce.core.catalog.domain.Product;
import org.broadleafcommerce.core.web.api.wrapper.APIWrapper;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
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
public class ProductsWrapper extends BaseWrapper implements APIWrapper<List<Product>> {

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private List<AppFactoryProductWrapper> products = new ArrayList<AppFactoryProductWrapper>();

    @Override
    public void wrapDetails(List<Product> products, HttpServletRequest request) {
        for (Product p : products) {
            AppFactoryProductWrapper wrapper = context.getBean(AppFactoryProductWrapper.class);
            wrapper.wrapSummary(p, request);

            this.products.add(wrapper);
        }
    }

    @Override
    public void wrapSummary(List<Product> products, HttpServletRequest request) {
        wrapDetails(products, request);
    }
}
