package com.mycompany.api.endpoint.appfactory.wrappers.order;

import org.broadleafcommerce.core.catalog.domain.Category;
import org.broadleafcommerce.core.catalog.domain.Product;
import org.broadleafcommerce.core.catalog.domain.Sku;
import org.broadleafcommerce.core.catalog.service.CatalogService;
import org.broadleafcommerce.core.order.domain.DiscreteOrderItem;
import org.broadleafcommerce.core.order.domain.OrderItem;
import org.broadleafcommerce.core.web.api.wrapper.APIUnwrapper;
import org.broadleafcommerce.core.web.api.wrapper.OrderItemWrapper;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-28
 * Time: PM3:40
 * To change this template use File | Settings | File Templates.
 */
public class UnWrappableOrderItemWrapper extends OrderItemWrapper implements APIUnwrapper<OrderItem> {


    @Override
    public OrderItem unwrap(HttpServletRequest request, ApplicationContext context) {
        CatalogService catalogService = context.getBean("blCatalogService", CatalogService.class);

        DiscreteOrderItem item = context.getBean("org.broadleafcommerce.core.order.domain.DiscreteOrderItem", DiscreteOrderItem.class);

        Product product = catalogService.findProductById(this.productId);
        Category category = catalogService.findCategoryById(this.categoryId);
        Sku sku = catalogService.findSkuById(this.skuId);
        item.setCategory(category);
        item.setQuantity(this.quantity);
        item.setName(this.name);
        item.setRetailPrice(this.retailPrice);
        item.setSalePrice(this.salePrice);
        item.setProduct(product);
        item.setSku(sku);
        return item;
    }

}
