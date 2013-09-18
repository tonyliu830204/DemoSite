package com.mycompany.api.endpoint.appfactory;

import org.broadleafcommerce.core.web.api.wrapper.CustomerWrapper;
import org.broadleafcommerce.profile.core.domain.Customer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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

    @Path("register")
    @POST
    public boolean register(@Context HttpServletRequest request, CustomerWrapper customerWrapper, String password, String passwordConfirm) {

        Customer customer = customer = customerWrapper.unwrap(request, context);

        customerService.registerCustomer(customer, password, passwordConfirm);

        return true;
    }

//    public boolean changePassword(@Context HttpServletRequest request, PasswordChange passwordChange) {
//        return true;
//    }
}
