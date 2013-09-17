package com.appfactory.domain;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationDataDrivenEnumeration;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/16/13
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@AdminPresentationClass(friendlyName = "Layout")
public class LayoutImpl implements Layout {

    @Id
    @GeneratedValue
    private Long id;


    @Embedded
    @AdminPresentation(friendlyName = "Layout Type")
    private LayoutType layoutType;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public LayoutType getLayoutType() {
        return layoutType;
    }

    @Override
    public void setLayoutType(LayoutType layoutType) {
        this.layoutType = layoutType;
    }
}
