package com.mycompany.api.endpoint.appfactory;

import com.mycompany.api.endpoint.appfactory.wrappers.order.AFOrderWrapper;
import com.mycompany.api.endpoint.cart.CartEndpoint;
import com.mycompany.api.endpoint.cart.FulfillmentEndpoint;
import com.mycompany.api.endpoint.checkout.CheckoutEndpoint;
import org.broadleafcommerce.core.checkout.service.CheckoutService;
import org.broadleafcommerce.core.checkout.service.exception.CheckoutException;
import org.broadleafcommerce.core.checkout.service.workflow.CheckoutResponse;
import org.broadleafcommerce.core.order.dao.OrderDao;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import javax.annotation.Resource;
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
 * Date: 9/27/13
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope("singleton")
@Path("/order")
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class OrderEndpoint extends BaseWrapper {


    @Resource(name = "blCheckoutService")
    CheckoutService checkoutService;

    @Resource
    OrderDao orderDao;


    @Resource
    CartEndpoint cartEndpoint;

    @Resource
    FulfillmentEndpoint fulfillmentEndpoint;

    @Resource
    CheckoutEndpoint checkoutEndpoint;

    @POST
    public AFOrderWrapper createOrder(AFOrderWrapper orderWrapper, @Context HttpServletRequest request) throws CheckoutException {
        Order order = orderWrapper.unwrap(request, context);
        order = orderDao.save(order);
        CheckoutResponse response = checkoutService.performCheckout(order);

        return orderWrapper;
    }

}
