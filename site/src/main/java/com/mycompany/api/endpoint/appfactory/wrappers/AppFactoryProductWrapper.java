package com.mycompany.api.endpoint.appfactory.wrappers;

import org.broadleafcommerce.core.catalog.domain.Product;
import org.broadleafcommerce.core.catalog.domain.Sku;
import org.broadleafcommerce.core.web.api.wrapper.APIWrapper;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-9
 * Time: PM10:13
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "product")
//@XmlType(name = "myproduct")
public class AppFactoryProductWrapper extends BaseWrapper implements APIWrapper<Product> {

    @XmlElement
    protected Long id;

    @XmlElement
    protected String name;

    @XmlElement
    protected String desc;

    @XmlElement
    protected String iconURL;

    @XmlElement(name = "price")
    private String priceDesc;

    @XmlElementWrapper(name = "sizes")
    List<ProductSizeWrapper> sizes = new ArrayList<ProductSizeWrapper>();

    @Override
    public void wrapDetails(Product model, HttpServletRequest request) {
        wrapSummary(model, request);
        if (model.getSkus() != null && model.getSkus().size() > 0) {
            for (Sku sku : model.getSkus()) {
                ProductSizeWrapper size = context.getBean(ProductSizeWrapper.class);
                size.wrapDetails(sku, request);
                sizes.add(size);
            }
        }
    }

    @Override
    public void wrapSummary(Product model, HttpServletRequest request) {
        this.id = model.getId();
        this.name = model.getName();
        this.desc = model.getDescription();
        this.iconURL = model.getMedia().get("primary").getUrl();
        if (model.getDefaultSku().getSalePrice() != null) {
            this.priceDesc = model.getDefaultSku().getSalePrice().getAmount().toString();
        } else {
            this.priceDesc = "0";
        }


    }

}
