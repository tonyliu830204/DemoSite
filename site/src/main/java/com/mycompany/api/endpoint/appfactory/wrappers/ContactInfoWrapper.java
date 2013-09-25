package com.mycompany.api.endpoint.appfactory.wrappers;

import com.appfactory.domain.PhoneInfo;
import org.broadleafcommerce.core.web.api.wrapper.APIWrapper;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/25/13
 * Time: 3:44 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "ContactInfo")
public class ContactInfoWrapper extends BaseWrapper implements APIWrapper<PhoneInfo> {

    @XmlElement(name = "phoneName")
    private String phoneName;

    @XmlElement(name = "phoneNumber")
    private String phoneNumber;

    @Override
    public void wrapDetails(PhoneInfo model, HttpServletRequest request) {
        wrapSummary(model, request);
    }

    @Override
    public void wrapSummary(PhoneInfo model, HttpServletRequest request) {
        this.phoneNumber = model.getNumber();
        this.phoneName = model.getName();
    }
}
