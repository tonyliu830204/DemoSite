package com.appfactory.domain;

import org.broadleafcommerce.common.BroadleafEnumerationType;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/16/13
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
@Embeddable
public class LayoutType implements BroadleafEnumerationType, Serializable {

    private String type;

    private String friendlyType;

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getFriendlyType() {
        return this.friendlyType;
    }
}
