package com.mycompany.api.endpoint.appfactory;

import com.appfactory.exception.RegisterFailedException;
import com.appfactory.service.AFCustomerService;
import com.mycompany.api.endpoint.appfactory.wrappers.CustomerRegistrationWrapper;
import org.broadleafcommerce.core.web.api.wrapper.CustomerWrapper;
import org.broadleafcommerce.profile.core.domain.Customer;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
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

    @Resource(name = "blAuthenticationManager")
    private AuthenticationManager authenticationManager;

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

    @Path("login")
    @POST
    public CustomerWrapper login(CustomerRegistrationWrapper loginData, @Context HttpServletRequest request, @Context HttpServletResponse response) {

        String username = loginData.getEmail();
        String password = loginData.getPassword();

        Authentication authRequest = new UsernamePasswordAuthenticationToken(username, password);
        Authentication result = authenticationManager.authenticate(authRequest);
        if (!result.isAuthenticated()) {
            response.addHeader("ErrorCode", "Bad Credentials");
            throw new BadCredentialsException("Bad Credentials");
        }

        CustomerWrapper wrapper = context.getBean(CustomerWrapper.class);
        Customer customer = customerService.readCustomerByEmail(username);
        wrapper.wrapSummary(customer, request);
        return wrapper;
    }
}
