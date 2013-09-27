package com.mycompany.api.endpoint.appfactory;

import com.appfactory.service.PromotionService;
import com.mycompany.api.endpoint.appfactory.wrappers.AppFactoryProductWrapper;
import com.mycompany.api.endpoint.appfactory.wrappers.CategoriesWrapper;
import com.mycompany.api.endpoint.appfactory.wrappers.PromotionsWrapper;
import com.mycompany.api.endpoint.appfactory.wrappers.SubCategoryWrapper;
import org.broadleafcommerce.core.catalog.domain.Category;
import org.broadleafcommerce.core.catalog.domain.Product;
import org.broadleafcommerce.core.web.api.endpoint.catalog.CatalogEndpoint;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
public class ProductsEndpoint extends CatalogEndpoint {

    @Resource(name = "promotionService")
    private PromotionService promotionService;

    @GET
    @Path("categories")
    public CategoriesWrapper getAllRootCategories(@Context HttpServletRequest request) {
        List<Category> categories = catalogService.findAllSubCategories(catalogService.findCategoryById(2L));
        CategoriesWrapper wrapper = context.getBean(CategoriesWrapper.class);
        wrapper.wrapSummary(categories, request);
        return wrapper;
    }


    @GET
    @Path("categories/{id}")
    public SubCategoryWrapper viewCategory(@Context HttpServletRequest request, @PathParam("id") Long id) {

        Category category = catalogService.findCategoryById(id);

        SubCategoryWrapper wrapper = context.getBean(SubCategoryWrapper.class);
        wrapper.wrapDetails(category, request);

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

    @GET
    @Path("promotions")
    public PromotionsWrapper getPromotions(@Context HttpServletRequest request) {

        List<Product> featuredProducts = promotionService.findFeaturedProducts();

        PromotionsWrapper promotionsWrapper = context.getBean(PromotionsWrapper.class);
        promotionsWrapper.wrapProducts(featuredProducts);


        List<Category> promotableCategories = promotionService.findPromotableCategories();
        promotionsWrapper.wrapCategories(promotableCategories);

        return promotionsWrapper;
    }
}
