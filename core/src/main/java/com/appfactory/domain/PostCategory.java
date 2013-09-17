package com.appfactory.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-15
 * Time: PM12:48
 * To change this template use File | Settings | File Templates.
 */
public interface PostCategory extends Serializable{

    String getName();

    void setName(String name);

    Long getId();

    void setId(Long id);

    List<Post> getPosts();

    void setPosts(List<Post> posts);
}
