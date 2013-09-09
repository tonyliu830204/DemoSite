package com.mycompany.api.endpoint.appfactory.wrappers;

import org.broadleafcommerce.core.catalog.domain.Product;
import org.broadleafcommerce.core.web.api.wrapper.APIWrapper;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-9
 * Time: PM10:13
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "product")
@XmlType(name = "myproduct")
public class ProductWrapper extends BaseWrapper implements APIWrapper<Product> {

    @XmlElement
    protected Long id;

    @XmlElement
    protected String name;

    @XmlElement
    protected String desc;

    @XmlElement
    protected String iconURL;


    private String price;

    @Override
    public void wrapDetails(Product model, HttpServletRequest request) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void wrapSummary(Product model, HttpServletRequest request) {
        this.id = model.getId();
        this.name = model.getName();
        this.desc = model.getDescription();
        this.iconURL = model.getMedia().get("primary").getUrl();
        if (model.getDefaultSku().getSalePrice() != null) {
            this.price = model.getDefaultSku().getSalePrice().getAmount().toString();
        } else {
            this.price = "0";
        }
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
