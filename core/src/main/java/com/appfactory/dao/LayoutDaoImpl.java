package com.appfactory.dao;

import com.appfactory.domain.Layout;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-16
 * Time: PM11:20
 * To change this template use File | Settings | File Templates.
 */
@Repository("layoutDao")
public class LayoutDaoImpl implements LayoutDao {

    @PersistenceContext(unitName = "blPU")
    protected EntityManager em;

    @Override
    public Layout findDefaultLayout() {
        TypedQuery<Layout> query = em.createNamedQuery("FIND_DEFAULT_LAYOUT", Layout.class);
        return query.getSingleResult();
    }
}
