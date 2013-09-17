package com.appfactory.dao;

import com.appfactory.domain.Cell;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/17/13
 * Time: 12:31 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CellDao {

    public List<Cell> findAllCells();

}
