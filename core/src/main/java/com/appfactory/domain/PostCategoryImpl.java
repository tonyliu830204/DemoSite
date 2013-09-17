package com.appfactory.domain;

import org.broadleafcommerce.common.admin.domain.AdminMainEntity;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationCollection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AdminPresentationClass(friendlyName = "PostCategory")
public class PostCategoryImpl implements PostCategory, AdminMainEntity {

    @Id
    @GeneratedValue
    private Long id;

    @AdminPresentation(prominent = true, friendlyName = "name")
    private String name;


    @AdminPresentationCollection(friendlyName = "Posts", manyToField = "category")
    @OneToMany(targetEntity = PostImpl.class, mappedBy = "category")
    private List<Post> posts;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getMainEntityName() {
        return "Post Category";
    }

    @Override
    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
