package com.mycompany.api.endpoint.appfactory.wrappers;

import org.broadleafcommerce.core.catalog.domain.Product;
import org.broadleafcommerce.core.catalog.domain.ProductOptionValue;
import org.broadleafcommerce.core.catalog.domain.Sku;
import org.broadleafcommerce.core.web.api.wrapper.APIWrapper;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-10
 * Time: PM9:13
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "size")
public class ProductSizeWrapper extends BaseWrapper implements APIWrapper<Sku> {

    @XmlElement
    private String name;

    @XmlElement
    private Double price;

    @Override
    public void wrapDetails(Sku model, HttpServletRequest request) {
        if (model.getProductOptionValues() != null && model.getProductOptionValues().size() > 0) {

            StringBuffer buffer = new StringBuffer();

            for (ProductOptionValue value : model.getProductOptionValues()) {
                buffer.append(value.getProductOption().getAttributeName()).append(":").append(value.getAttributeValue()).append(",");
            }

            name = buffer.toString();
            if (model.getSalePrice() != null) {
                price = model.getSalePrice().getAmount().doubleValue();
            } else {
                price = model.getRetailPrice().getAmount().doubleValue();
            }
        }
    }

    @Override
    public void wrapSummary(Sku model, HttpServletRequest request) {
        wrapDetails(model, request);
    }
}
