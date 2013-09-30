package com.mycompany.api.endpoint.appfactory.wrappers.order;

import com.mycompany.api.endpoint.appfactory.wrappers.AppFactoryProductWrapper;
import org.broadleafcommerce.common.money.Money;
import org.broadleafcommerce.core.catalog.domain.Product;
import org.broadleafcommerce.core.catalog.domain.Sku;
import org.broadleafcommerce.core.catalog.service.CatalogService;
import org.broadleafcommerce.core.order.domain.DiscreteOrderItem;
import org.broadleafcommerce.core.order.domain.OrderItem;
import org.broadleafcommerce.core.web.api.wrapper.APIUnwrapper;
import org.broadleafcommerce.core.web.api.wrapper.APIWrapper;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-29
 * Time: AM9:34
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "product")
public class OrderProductWrapper extends BaseWrapper implements APIUnwrapper<OrderItem>, APIWrapper<OrderItem> {

    @XmlElement
    private Long productId;

    @XmlElement
    private Integer quantity;

    @XmlElement
    private String price;

    @XmlElement
    private Long skuId;

    @XmlElement(name = "product")
    private AppFactoryProductWrapper product;

    @Override
    public OrderItem unwrap(HttpServletRequest request, ApplicationContext context) {
        CatalogService catalogService = context.getBean(CatalogService.class);

        DiscreteOrderItem item = context.getBean("org.broadleafcommerce.core.order.domain.DiscreteOrderItem", DiscreteOrderItem.class);
        Sku sku = catalogService.findSkuById(this.skuId);
        if (sku != null) {
            item.setSku(sku);
        }

        Product product = catalogService.findProductById(this.productId);
        if (product != null) {
            item.setProduct(product);
        }

        item.setQuantity(quantity);
        Money money = new Money(price);
        item.setPrice(money);

        return item;
    }

    @Override
    public void wrapDetails(OrderItem model, HttpServletRequest request) {
        this.quantity = model.getQuantity();
        this.price = model.getPrice().getAmount().toString();
        if (model instanceof DiscreteOrderItem) {
            DiscreteOrderItem discreteOrderItem = (DiscreteOrderItem) model;
            Product product = discreteOrderItem.getProduct();
            this.productId = product.getId();
            this.skuId = discreteOrderItem.getSku().getId();
            AppFactoryProductWrapper productWrapper = context.getBean(AppFactoryProductWrapper.class);
            productWrapper.wrapDetails(product, request);
            this.product = productWrapper;
        }
    }

    @Override
    public void wrapSummary(OrderItem model, HttpServletRequest request) {
        wrapDetails(model, request);
    }
}
