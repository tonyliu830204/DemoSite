package com.appfactory.domain;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/16/13
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@AdminPresentationClass(friendlyName = "Phone Info")
public class PhoneInfoImpl implements PhoneInfo {

    @Id
    @GeneratedValue
    private Long id;

    @AdminPresentation(friendlyName = "Name", gridOrder = 1, prominent = true)
    private String name;

    @AdminPresentation(friendlyName = "Number", gridOrder = 2, prominent = true)
    private String number;

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }
}
