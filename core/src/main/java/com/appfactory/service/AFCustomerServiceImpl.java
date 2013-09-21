package com.appfactory.service;

import com.appfactory.exception.RegisterFailedException;
import org.broadleafcommerce.common.service.GenericResponse;
import org.broadleafcommerce.profile.core.dao.CustomerDao;
import org.broadleafcommerce.profile.core.domain.Customer;
import org.broadleafcommerce.profile.core.service.CustomerServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-21
 * Time: PM10:03
 * To change this template use File | Settings | File Templates.
 */
@Service("afCustomerService")
@Transactional("blTransactionManager")
public class AFCustomerServiceImpl extends CustomerServiceImpl implements AFCustomerService {

    @Resource(name = "blCustomerDao")
    private CustomerDao customerDao;

    @Override
    public Customer register(Customer customer, String password, String passwordConfirm) throws RegisterFailedException {

        GenericResponse response = new GenericResponse();
        super.checkPassword(password, passwordConfirm, response);
        super.checkCustomer(customer, response);


        this.checkEmail(customer, response);

        if (response.getHasErrors()) {
            throw new RegisterFailedException(response.getErrorCodesList().toString());
        }
        return super.registerCustomer(customer, password, passwordConfirm);
    }

    protected void checkEmail(Customer customer, GenericResponse response) {
        if (customerDao.readCustomerByEmail(customer.getEmailAddress()) != null) {
            response.addErrorCode("email.duplicate");
        }
    }
}
