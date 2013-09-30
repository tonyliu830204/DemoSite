package com.mycompany.api.endpoint.appfactory.wrappers.order;

import org.broadleafcommerce.common.money.Money;
import org.broadleafcommerce.core.order.domain.FulfillmentGroup;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.domain.OrderItem;
import org.broadleafcommerce.core.order.service.type.FulfillmentType;
import org.broadleafcommerce.core.order.service.type.OrderStatus;
import org.broadleafcommerce.core.web.api.wrapper.APIUnwrapper;
import org.broadleafcommerce.core.web.api.wrapper.APIWrapper;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;
import org.broadleafcommerce.profile.core.domain.Address;
import org.broadleafcommerce.profile.core.domain.Customer;
import org.broadleafcommerce.profile.core.domain.Phone;
import org.broadleafcommerce.profile.core.service.CustomerService;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-29
 * Time: AM9:32
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "order")
public class AFOrderWrapper extends BaseWrapper implements APIUnwrapper<Order>, APIWrapper<Order> {

    @XmlElement
    private String id;

    @XmlElement
    private String name;

    @XmlElement
    private String phone;

    @XmlElement
    private String address;

    @XmlElement
    private String subTotal;

    @XmlElement
    private String status;

    @XmlElement
    private String orderNumber;

    @XmlElement
    private Date submitDate;

    @XmlElementWrapper(name = "products")
    @XmlElement
    private List<OrderProductWrapper> products = new ArrayList<OrderProductWrapper>();

    private String payment = "alipay";

    @Override
    public Order unwrap(HttpServletRequest request, ApplicationContext context) {

        CustomerService customerService = context.getBean("blCustomerService", CustomerService.class);

        Order order = context.getBean(Order.class);
        order.setStatus(OrderStatus.IN_PROCESS);
        String customerEmail = request.getRemoteUser();
        if (customerEmail != null) {
            order.setEmailAddress(customerEmail);
            Customer customer = customerService.readCustomerByEmail(customerEmail);
            order.setCustomer(customer);
        }

        order.setSubmitDate(new Date());

        double sub_total = 0d;

        for (OrderProductWrapper product : products) {
            OrderItem item = product.unwrap(request, context);
            order.addOrderItem(item);
            item.setOrder(order);
            sub_total += item.getPrice().doubleValue() * item.getQuantity();
        }

        Money subTotal = new Money(sub_total);
        order.setSubTotal(subTotal);
        order.setTotal(subTotal);

        FulfillmentGroup group = context.getBean(FulfillmentGroup.class);
        group.setOrder(order);
        Phone phone = context.getBean(Phone.class);
        phone.setPhoneNumber(this.phone);
        group.setPhone(phone);
        Address address = context.getBean(Address.class);
        address.setEmailAddress(customerEmail);
        address.setFirstName(name);
        address.setAddressLine1(this.address);
        address.setCity("XXX");
        address.setPostalCode("000");
        address.setPhonePrimary(phone);

        group.setAddress(address);
        group.setPrimary(true);
        group.setType(FulfillmentType.PHYSICAL_SHIP);

        List<FulfillmentGroup> groups = new ArrayList<FulfillmentGroup>();
        groups.add(group);
        order.setFulfillmentGroups(groups);

        return order;
    }

    @Override
    public void wrapDetails(Order model, HttpServletRequest request) {
        this.id = model.getId().toString();
        this.orderNumber = model.getOrderNumber();
        this.submitDate = model.getSubmitDate();
        this.status = model.getStatus().getType();

        List<FulfillmentGroup> fulfillmentGroups = model.getFulfillmentGroups();
        if (fulfillmentGroups != null && fulfillmentGroups.size() > 0) {
            Address address = fulfillmentGroups.get(0).getAddress();
            this.address = address.getAddressLine1();
            this.name = address.getFirstName();
            Phone phone = address.getPhonePrimary();
            if (phone != null) {
                this.phone = address.getPhonePrimary().getPhoneNumber();
            } else {
                this.phone = fulfillmentGroups.get(0).getPhone().getPhoneNumber();
            }

        }
        this.subTotal = model.getSubTotal().getAmount().toString();

        for (OrderItem item : model.getOrderItems()) {
            OrderProductWrapper orderProductWrapper = context.getBean(OrderProductWrapper.class);
            orderProductWrapper.wrapDetails(item, request);
            this.products.add(orderProductWrapper);
        }
    }

    @Override
    public void wrapSummary(Order model, HttpServletRequest request) {
        wrapDetails(model, request);
    }
}
