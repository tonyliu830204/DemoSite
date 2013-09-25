package com.appfactory.dao;

import com.appfactory.domain.PhoneInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/25/13
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("phoneInfoDao")
public class PhoneInfoDaoImpl implements PhoneInfoDao {

    @PersistenceContext(unitName = "blPU")
    EntityManager em;

    @Override
    public List<PhoneInfo> findAllPhoneInfos() {
        return em.createNamedQuery("FIND_ALL_PHONE_INFO", PhoneInfo.class).getResultList();
    }
}
