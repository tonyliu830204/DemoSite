package com.appfactory.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-16
 * Time: PM8:42
 * To change this template use File | Settings | File Templates.
 */
public interface Layout extends Serializable {
    String getType();

    void setType(String type);

    Long getId();

    void setId(Long id);
}
