package com.mycompany.api.endpoint.appfactory.wrappers;

import com.appfactory.domain.Layout;
import org.broadleafcommerce.core.web.api.wrapper.APIWrapper;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-16
 * Time: PM10:50
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class AppConfigWrapper extends BaseWrapper implements APIWrapper<Layout>{

    @XmlElement(name = "layout_type")
    private String layoutType;

    @Override
    public void wrapDetails(Layout model, HttpServletRequest request) {
        this.wrapSummary(model, request);
    }

    @Override
    public void wrapSummary(Layout model, HttpServletRequest request) {
        layoutType = model.getType();
    }
}
