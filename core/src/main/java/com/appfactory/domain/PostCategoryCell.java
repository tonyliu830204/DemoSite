package com.appfactory.domain;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/16/13
 * Time: 4:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PostCategoryCell extends Cell {
    PostCategory getCategory();

    void setCategory(PostCategory category);
}
