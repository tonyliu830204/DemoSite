package com.mycompany.api.endpoint.appfactory;

import com.appfactory.dao.PhoneInfoDao;
import com.appfactory.domain.PhoneInfo;
import com.mycompany.api.endpoint.appfactory.wrappers.ContactInfoWrapper;
import com.mycompany.api.endpoint.appfactory.wrappers.ContactInfosWrapper;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/25/13
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope("singleton")
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Path("/contact_info")
public class ContactInfoEndpoint extends BaseEndpoint {

    @Resource(name = "phoneInfoDao")
    private PhoneInfoDao phoneInfoDao;

    @GET
    public ContactInfosWrapper getContactInfo(@Context HttpServletRequest request) {

        Map<String, List<ContactInfoWrapper>> result = new HashMap<String, List<ContactInfoWrapper>>();

        List<PhoneInfo> models = phoneInfoDao.findAllPhoneInfos();

        ContactInfosWrapper wrapper = context.getBean(ContactInfosWrapper.class);
        wrapper.wrapSummary(models, request);

        return wrapper;
    }

}
