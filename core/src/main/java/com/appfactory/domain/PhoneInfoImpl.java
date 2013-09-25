package com.appfactory.domain;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/16/13
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@AdminPresentationClass(friendlyName = "PhoneInfoImpl")
@NamedQueries(
        {
                @NamedQuery(
                        name = "FIND_ALL_PHONE_INFO",
                        query = "select p from PhoneInfoImpl as p order by p.id"
                )
        }
)
public class PhoneInfoImpl implements PhoneInfo {

    @Id
    @GeneratedValue
    private Long id;

    @AdminPresentation(friendlyName = "PhoneInfoImpl_Name", gridOrder = 1, prominent = true)
    private String name;

    @AdminPresentation(friendlyName = "PhoneInfoImpl_Number", gridOrder = 2, prominent = true)
    private String number;

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
