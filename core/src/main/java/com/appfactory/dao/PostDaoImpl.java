package com.appfactory.dao;

import com.appfactory.domain.Post;
import com.appfactory.domain.PostImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/23/13
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("postDao")
public class PostDaoImpl implements PostDao {

    @PersistenceContext(unitName = "blPU")
    private EntityManager em;

    @Override
    public Post findById(Long id) {
        return em.find(PostImpl.class, id);
    }

    @Override
    public List<Post> findByCategory(Long id) {
        TypedQuery<Post> query = em.createNamedQuery("FIND_POST_BY_CATEGORY", Post.class);
        query.setParameter("id", id);

        return query.getResultList();

    }
}
