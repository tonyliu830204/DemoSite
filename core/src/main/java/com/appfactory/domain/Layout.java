package com.appfactory.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/16/13
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Layout extends Serializable {
    Long getId();

    void setId(Long id);

    LayoutType getLayoutType();

    void setLayoutType(LayoutType layoutType);
}
