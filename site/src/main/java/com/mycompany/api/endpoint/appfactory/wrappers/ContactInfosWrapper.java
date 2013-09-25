package com.mycompany.api.endpoint.appfactory.wrappers;

import com.appfactory.domain.PhoneInfo;
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
 * User: wli
 * Date: 9/25/13
 * Time: 4:06 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class ContactInfosWrapper extends BaseWrapper implements APIWrapper<List<PhoneInfo>> {

    @XmlElementWrapper(name = "contact_info_list")
    @XmlElement(name = "contact_info")
    List<ContactInfoWrapper> contactInfoList = new ArrayList<ContactInfoWrapper>();

    @Override
    public void wrapDetails(List<PhoneInfo> model, HttpServletRequest request) {
        this.wrapSummary(model, request);
    }

    @Override
    public void wrapSummary(List<PhoneInfo> model, HttpServletRequest request) {
        for (PhoneInfo phone : model) {
            ContactInfoWrapper wrapper = context.getBean(ContactInfoWrapper.class);
            wrapper.wrapSummary(phone, request);
            contactInfoList.add(wrapper);
        }
    }
}
