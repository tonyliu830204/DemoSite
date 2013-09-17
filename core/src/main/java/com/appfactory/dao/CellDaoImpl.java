package com.appfactory.dao;

import com.appfactory.domain.Cell;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/17/13
 * Time: 12:31 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("cellDao")
public class CellDaoImpl implements CellDao {

    @PersistenceContext(unitName = "blPU")
    private EntityManager em;

    @Override
    public List<Cell> findAllCells() {
        TypedQuery<Cell> query = em.createNamedQuery("FIND_ALL_CELL", Cell.class);
        return query.getResultList();
    }
}
