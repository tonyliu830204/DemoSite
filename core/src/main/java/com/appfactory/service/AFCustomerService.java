package com.appfactory.service;

import com.appfactory.exception.RegisterFailedException;
import org.broadleafcommerce.profile.core.domain.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-21
 * Time: PM10:07
 * To change this template use File | Settings | File Templates.
 */
public interface AFCustomerService {

    public Customer register(Customer customer, String password, String passwordConfrim) throws RegisterFailedException;

}
