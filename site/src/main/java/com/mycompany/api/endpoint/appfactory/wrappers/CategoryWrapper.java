package com.mycompany.api.endpoint.appfactory.wrappers;

import org.broadleafcommerce.core.catalog.domain.Category;
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
 * Time: PM11:16
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "category")
@XmlType(name = "APPFACTORY_CATEGORY")
public class CategoryWrapper extends BaseWrapper implements APIWrapper<Category> {

    @XmlElement
    protected String id;

    @XmlElement
    protected String name;

    @XmlElement
    protected String desc;

    @XmlElement
    protected String iconURL;

    @Override
    public void wrapDetails(Category model, HttpServletRequest request) {
        this.id = model.getId().toString();
        this.name = model.getName();
        this.desc = model.getDescription();
        if (model.getCategoryMedia().size() > 0) {
            this.iconURL = model.getCategoryMedia().get("primary").getUrl();
        }
    }

    @Override
    public void wrapSummary(Category model, HttpServletRequest request) {
        wrapDetails(model, request);
    }
}
