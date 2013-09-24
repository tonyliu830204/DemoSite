package com.mycompany.api.endpoint.appfactory.wrappers;

import com.appfactory.domain.Post;
import org.broadleafcommerce.core.web.api.wrapper.APIWrapper;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/24/13
 * Time: 12:58 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class PostsWrapper extends BaseWrapper implements APIWrapper<List<Post>> {

    @XmlElementWrapper(name = "posts")
    @XmlElement
    private List<PostWrapper> posts = new ArrayList<PostWrapper>();


    @Override
    public void wrapDetails(List<Post> model, HttpServletRequest request) {
        this.wrapSummary(model, request);
    }

    @Override
    public void wrapSummary(List<Post> model, HttpServletRequest request) {
        for (Post post : model) {
            PostWrapper wrapper = context.getBean(PostWrapper.class);
            wrapper.wrapSummary(post, request);
            posts.add(wrapper);
        }
    }
}
