package com.mycompany.api.endpoint.appfactory.wrappers;

import org.broadleafcommerce.core.catalog.domain.Category;
import org.broadleafcommerce.core.web.api.wrapper.APIWrapper;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-9
 * Time: PM11:15
 * To change this template use File | Settings | File Templates.
 */
@XmlType(name = "APP_CATEGORIES")
@XmlRootElement
public class CategoriesWrapper extends BaseWrapper implements APIWrapper<List<Category>> {

    @XmlElement(name = "category")
    @XmlElementWrapper(name = "categories")
    protected List<AppFactoryCategoryWrapper> categories = new ArrayList<AppFactoryCategoryWrapper>();

    @Override
    public void wrapDetails(List<Category> model, HttpServletRequest request) {
        for (Category c : model) {
            AppFactoryCategoryWrapper wrapper = context.getBean(AppFactoryCategoryWrapper.class);
            wrapper.wrapSummary(c, request);
            this.categories.add(wrapper);
        }
    }

    @Override
    public void wrapSummary(List<Category> model, HttpServletRequest request) {
        wrapDetails(model, request);
    }
}
