package com.mycompany.api.endpoint.appfactory.wrappers;

import com.appfactory.domain.Cell;
import com.appfactory.domain.Layout;
import org.broadleafcommerce.core.web.api.wrapper.APIWrapper;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-16
 * Time: PM10:50
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class AppConfigWrapper extends BaseWrapper implements APIWrapper<Layout>{

    @XmlElement(name = "layout_type")
    private String layoutType;

    @XmlElement(name = "cells")
    private List<CellWrapper> cells = new ArrayList<CellWrapper>();

    @Override
    public void wrapDetails(Layout model, HttpServletRequest request) {
        this.wrapDetails(model, null, request);
    }

    @Override
    public void wrapSummary(Layout model, HttpServletRequest request) {
        this.wrapDetails(model, null, request);
    }

    public void wrapDetails(Layout layout, List<Cell> cells, HttpServletRequest request) {
        layoutType = layout.getType();

        if (cells != null) {
            for (Cell cell : cells) {
                CellWrapper cellWrapper = context.getBean(CellWrapper.class);
                cellWrapper.wrapSummary(cell, request);
                this.cells.add(cellWrapper);
            }
        }
    }
}
