package com.appfactory.dao;

import com.appfactory.domain.CompanyInfo;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/25/13
 * Time: 4:30 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("companyInfoDao")
public class CompanyInfoDaoImpl implements CompanyInfoDao, ApplicationContextAware {

    @PersistenceContext(unitName = "blPU")
    private EntityManager em;
    private ApplicationContext context;

    @Override
    public CompanyInfo findDefaultCompanyInfo() {
        TypedQuery<CompanyInfo> query = em.createNamedQuery("FIND_DEFAULT_COMPANYINFO", CompanyInfo.class);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            CompanyInfo info = context.getBean(CompanyInfo.class);
            info.setId(-1L);
            em.persist(info);
        }

        return query.getSingleResult();

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
