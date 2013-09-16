package com.appfactory.domain;

import org.broadleafcommerce.common.presentation.AdminPresentationClass;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/16/13
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@AdminPresentationClass(friendlyName = "PhoneInfoCell")
public class PhoneInfoCellImpl extends AbstractCellImpl implements PhoneInfoCell {


}
