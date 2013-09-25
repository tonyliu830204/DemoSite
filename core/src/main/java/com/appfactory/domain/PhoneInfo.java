package com.appfactory.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/16/13
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PhoneInfo extends Serializable {
    String getNumber();

    void setNumber(String number);

    String getName();

    void setName(String name);
}
