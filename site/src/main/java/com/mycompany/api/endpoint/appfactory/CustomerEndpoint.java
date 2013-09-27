package com.mycompany.api.endpoint.appfactory;

import com.appfactory.exception.RegisterFailedException;
import com.appfactory.service.AFCustomerService;
import com.mycompany.api.endpoint.appfactory.wrappers.CustomerRegistrationWrapper;
import org.broadleafcommerce.core.web.api.wrapper.CustomerWrapper;
import org.broadleafcommerce.profile.core.domain.Customer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/18/13
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope("singleton")
@Path("/customers")
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CustomerEndpoint extends org.broadleafcommerce.core.web.api.endpoint.customer.CustomerEndpoint {

    @Resource(name = "afCustomerService")
    private AFCustomerService afCustomerService;

    @Path("register")
    @POST
    public CustomerWrapper register(@Context HttpServletRequest request, @Context HttpServletResponse response, CustomerRegistrationWrapper wrapper) {

        Customer customer = customerService.createNewCustomer();

        wrapper.populate(customer);
        String password = wrapper.getPassword();
        String passwordConfirm = wrapper.getPasswordConfirm();

        try {
            customer = afCustomerService.register(customer, password, passwordConfirm);
        } catch (RegisterFailedException e) {
            response.addHeader("ErrorCode", e.getMessage());
            throw new RuntimeException();
        }

//        customerService.registerCustomer(customer, password, passwordConfirm);

        CustomerWrapper result = context.getBean(CustomerWrapper.class);
        result.wrapSummary(customer, request);
        return result;

    }

//    public boolean changePassword(@Context HttpServletRequest request, PasswordChange passwordChange) {
//        return true;
//    }
}
