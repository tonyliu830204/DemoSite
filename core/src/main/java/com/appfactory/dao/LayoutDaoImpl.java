package com.appfactory.dao;

import com.appfactory.domain.Layout;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-16
 * Time: PM11:20
 * To change this template use File | Settings | File Templates.
 */
@Repository("layoutDao")
public class LayoutDaoImpl implements LayoutDao, ApplicationContextAware {

    @PersistenceContext(unitName = "blPU")
    protected EntityManager em;

    private ApplicationContext context;

    @Override
    public Layout findDefaultLayout() {
        TypedQuery<Layout> query = em.createNamedQuery("FIND_DEFAULT_LAYOUT", Layout.class);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            Layout layout = context.getBean(Layout.class);
            layout.setId(-1L);
            em.persist(layout);
        }

        return query.getSingleResult();

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
