package com.mycompany.api.endpoint.appfactory;

import com.appfactory.dao.LayoutDao;
import com.appfactory.domain.Layout;
import com.mycompany.api.endpoint.appfactory.wrappers.AppConfigWrapper;
import org.broadleafcommerce.core.web.api.endpoint.BaseEndpoint;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Component
@Scope("singleton")
@Path("/appconfig")
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class AppConfigEndpoint extends BaseEndpoint {

    @Resource(name = "layoutDao")
    private LayoutDao layoutDao;

    @GET
    @Path("menu_page/layout")
    public AppConfigWrapper getMenuPageLayout(@Context HttpServletRequest request) {
        Layout layout = layoutDao.findDefaultLayout();
        AppConfigWrapper wrapper = context.getBean(AppConfigWrapper.class);
        wrapper.wrapSummary(layout, request);
        return wrapper;
    }


}
