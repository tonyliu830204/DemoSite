package com.appfactory.dao;

import com.appfactory.domain.Post;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/23/13
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PostDao {

    Post findById(Long id);

    List<Post> findByCategory(Long id);
}
