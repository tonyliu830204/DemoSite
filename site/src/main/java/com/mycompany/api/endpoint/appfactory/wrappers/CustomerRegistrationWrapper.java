package com.mycompany.api.endpoint.appfactory.wrappers;

import org.broadleafcommerce.core.web.api.wrapper.APIUnwrapper;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;
import org.broadleafcommerce.core.web.api.wrapper.CustomerWrapper;
import org.broadleafcommerce.profile.core.domain.Customer;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-21
 * Time: PM8:34
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "register")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CustomerRegistrationWrapper extends BaseWrapper {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "email")
    private String email;


    @XmlElement(name = "password")
    private String password;

    @XmlElement(name = "passwordConfirm")
    private String passwordConfirm;


    public void populate(Customer model) {
        model.setFirstName(name);
        model.setEmailAddress(email);
        model.setUsername(email);
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }
}
