package com.appfactory.dao;

import com.appfactory.domain.PhoneInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/25/13
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PhoneInfoDao {

    List<PhoneInfo> findAllPhoneInfos();

}
