package com.mycompany.api.endpoint.appfactory.wrappers.order;

import org.broadleafcommerce.core.order.dao.OrderDao;
import org.broadleafcommerce.core.order.domain.FulfillmentGroup;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.domain.OrderImpl;
import org.broadleafcommerce.core.order.domain.OrderItem;
import org.broadleafcommerce.core.order.service.FulfillmentGroupService;
import org.broadleafcommerce.core.order.service.call.FulfillmentGroupRequest;
import org.broadleafcommerce.core.order.service.type.OrderStatus;
import org.broadleafcommerce.core.pricing.service.exception.PricingException;
import org.broadleafcommerce.core.web.api.wrapper.APIUnwrapper;
import org.broadleafcommerce.core.web.api.wrapper.FulfillmentGroupWrapper;
import org.broadleafcommerce.core.web.api.wrapper.OrderItemWrapper;
import org.broadleafcommerce.core.web.api.wrapper.OrderWrapper;
import org.broadleafcommerce.profile.core.domain.Customer;
import org.broadleafcommerce.profile.core.service.CustomerService;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-28
 * Time: PM2:53
 * To change this template use File | Settings | File Templates.
 */
public class UnWrappableOrderWrapper extends OrderWrapper implements APIUnwrapper<Order> {

    @XmlElement(name = "orderItem")
    @XmlElementWrapper(name = "orderItems")
    private List<UnWrappableOrderItemWrapper> orderItems;



    @Override
    public Order unwrap(HttpServletRequest request, ApplicationContext context) {
        CustomerService customerService = context.getBean("blCustomerService", CustomerService.class);
        Order order = context.getBean(Order.class);

        String customerEmail = request.getRemoteUser();

        Customer customer = customerService.readCustomerByEmail(customerEmail);

        order.setCustomer(customer);
        order.setSubTotal(this.subTotal);
        order.setTotal(this.total);
        order.setTotalFulfillmentCharges(this.totalShipping);
        order.setStatus(OrderStatus.IN_PROCESS);

        for (OrderItemWrapper orderItemWrapper : this.orderItems) {
            APIUnwrapper<OrderItem> apiUnwrapper = ((UnWrappableOrderItemWrapper) orderItemWrapper);
            OrderItem orderItem = apiUnwrapper.unwrap(request, context);
            order.addOrderItem(orderItem);
            orderItem.setOrder(order);
        }

        OrderDao orderDao = context.getBean(OrderDao.class);
        order = orderDao.save(order);

        List<FulfillmentGroup> groups = new ArrayList<FulfillmentGroup>();

        for (FulfillmentGroupWrapper fulfillmentGroupWrapper : this.fulfillmentGroups) {
            FulfillmentGroupRequest fulfillmentGroupRequest = fulfillmentGroupWrapper.unwrap(request, context);
            fulfillmentGroupRequest.setOrder(order);
            FulfillmentGroupService fulfillmentGroupService = context.getBean(FulfillmentGroupService.class);
            try {
                fulfillmentGroupService.addFulfillmentGroupToOrder(fulfillmentGroupRequest, false);
            } catch (PricingException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }

        return order;
    }
}
