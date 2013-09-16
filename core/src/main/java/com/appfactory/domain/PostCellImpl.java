package com.appfactory.domain;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationToOneLookup;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/16/13
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@AdminPresentationClass(friendlyName="Post Cell")
public class PostCellImpl extends AbstractCellImpl implements PostCell {


    @ManyToOne(targetEntity = PostImpl.class)
    @AdminPresentation(friendlyName = "Post")
    @AdminPresentationToOneLookup()
    private Post post;


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
