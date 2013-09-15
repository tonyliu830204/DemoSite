package com.appfactory.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-15
 * Time: PM12:54
 * To change this template use File | Settings | File Templates.
 */
public interface Post extends Serializable {

    String getTitle();

    String getContent();

    PostCategory getCategory();

    Long getId();

    void setId(Long id);

    void setTitle(String title);

    void setContent(String content);

    void setCategory(PostCategory category);
}
