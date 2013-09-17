package com.mycompany.api.endpoint.appfactory.wrappers;

import com.appfactory.domain.*;
import org.broadleafcommerce.core.web.api.wrapper.APIWrapper;
import org.broadleafcommerce.core.web.api.wrapper.BaseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/17/13
 * Time: 12:39 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "cell")
public class CellWrapper extends BaseWrapper implements APIWrapper<Cell> {

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "functionCode")
    private String functionCode;

    @XmlElement(name = "iconURL")
    private String iconURL;

    @Override
    public void wrapDetails(Cell cell, HttpServletRequest httpServletRequest) {
        this.wrapSummary(cell, httpServletRequest);
    }

    @Override
    public void wrapSummary(Cell cell, HttpServletRequest httpServletRequest) {
        this.title = cell.getName();
        this.iconURL = cell.getIconUrl();

        if (cell instanceof PostCategoryCell) {
            PostCategoryCell postCategoryCell = (PostCategoryCell) cell;
            functionCode = cell.getType() + "/" + postCategoryCell.getCategory().getId();
        } else if (cell instanceof PostCell) {
            PostCell postCell = (PostCell) cell;
            functionCode = cell.getType() + "/" + postCell.getPost().getId();
        } else {
            functionCode = cell.getType();
        }
    }
}
