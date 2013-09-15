package com.mycompany.api.endpoint.appfactory;

import com.mycompany.api.endpoint.appfactory.wrappers.AppFactoryProductWrapper;
import com.mycompany.api.endpoint.appfactory.wrappers.CategoriesWrapper;
import com.mycompany.api.endpoint.appfactory.wrappers.ProductsWrapper;
import org.broadleafcommerce.core.catalog.domain.Category;
import org.broadleafcommerce.core.catalog.domain.Product;
import org.broadleafcommerce.core.web.api.endpoint.catalog.CatalogEndpoint;
import org.broadleafcommerce.core.web.api.wrapper.ProductWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-9
 * Time: PM8:43
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope("singleton")
@Path("/products")
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class AppFactoryEndpoint extends CatalogEndpoint {


    @GET
    @Path("categories")
    public CategoriesWrapper getAllCategories(@Context HttpServletRequest request) {
        List<Category> categories = catalogService.findActiveSubCategoriesByCategory(catalogService.findCategoryById(2L));
        CategoriesWrapper wrapper = context.getBean(CategoriesWrapper.class);
        wrapper.wrapSummary(categories, request);
        return wrapper;
    }


    @GET
    @Path("categories/{id}")
    public ProductsWrapper findProductsByCategory(@Context HttpServletRequest request, @PathParam("id") Long id) {

        Category category = catalogService.findCategoryById(id);
        List<Product> products = catalogService.findActiveProductsByCategory(category, new Date());

        ProductsWrapper wrapper = context.getBean(ProductsWrapper.class);
        wrapper.wrapSummary(products, request);

        return wrapper;
    }

    @GET
    @Path("{id}")
    public AppFactoryProductWrapper getProductDetail(@Context HttpServletRequest request, @PathParam("id") Long id) {
        Product product = catalogService.findProductById(id);
        AppFactoryProductWrapper wrapper = context.getBean(AppFactoryProductWrapper.class);
        wrapper.wrapDetails(product, request);
        return wrapper;
    }
}
