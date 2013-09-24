package com.mycompany.api.endpoint.appfactory;

import com.appfactory.dao.PostDao;
import com.appfactory.domain.Post;
import com.mycompany.api.endpoint.appfactory.wrappers.PostsWrapper;
import org.broadleafcommerce.core.web.api.endpoint.BaseEndpoint;
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
 * User: wli
 * Date: 9/24/13
 * Time: 12:57 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope("singleton")
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Path("/")
public class PostEndpoint extends BaseEndpoint {

    @Resource(name = "postDao")
    private PostDao postDao;

    @GET
    @Path("/post_categories/{id}")
    public PostsWrapper getPostsForCategory(@Context HttpServletRequest request, @PathParam("id") Long id) {

        List<Post> posts = postDao.findByCategory(id);

        PostsWrapper wrapper = context.getBean(PostsWrapper.class);
        wrapper.wrapSummary(posts, request);

        return wrapper;
    }

}
