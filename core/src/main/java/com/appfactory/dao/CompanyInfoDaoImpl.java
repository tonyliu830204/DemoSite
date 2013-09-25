package com.appfactory.dao;

import com.appfactory.domain.CompanyInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/25/13
 * Time: 4:30 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("companyInfoDao")
public class CompanyInfoDaoImpl implements CompanyInfoDao {

    @PersistenceContext(unitName = "blPU")
    private EntityManager em;

    @Override
    public CompanyInfo findDefaultCompanyInfo() {
        return em.createNamedQuery("FIND_DEFAULT_COMPANYINFO", CompanyInfo.class).getSingleResult();
    }
}
