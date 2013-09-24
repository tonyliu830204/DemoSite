package com.mycompany.api.endpoint.appfactory.wrappers;

import com.appfactory.domain.Post;
import org.broadleafcommerce.core.web.api.wrapper.APIWrapper;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/24/13
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "post")
public class PostWrapper extends BaseWrapper implements APIWrapper<Post> {

    @XmlElement
    private String title;

    @XmlElement
    private String url;

    @Override
    public void wrapDetails(Post model, HttpServletRequest request) {
        this.wrapDetails(model, request);
    }

    @Override
    public void wrapSummary(Post model, HttpServletRequest request) {
        this.title = model.getTitle();
        this.url = "/posts/" + model.getId();
    }
}
