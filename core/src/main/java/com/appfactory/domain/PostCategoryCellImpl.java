package com.appfactory.domain;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationToOneLookup;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/16/13
 * Time: 4:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@AdminPresentationClass(friendlyName = "PostCategory Cell")
public class PostCategoryCellImpl extends AbstractCellImpl implements PostCategoryCell {

    @ManyToOne(targetEntity = PostCategoryImpl.class)
    @AdminPresentation(friendlyName = "Category")
    @AdminPresentationToOneLookup(lookupDisplayProperty = "name")
    private PostCategory category;

    @Override
    public PostCategory getCategory() {
        return category;
    }

    @Override
    public void setCategory(PostCategory category) {
        this.category = category;
    }

    @Override
    public String getType() {
        return "POST_CATEGORY";
    }
}
