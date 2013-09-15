package com.appfactory.domain;

import org.broadleafcommerce.common.admin.domain.AdminMainEntity;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationToOneLookup;
import org.broadleafcommerce.common.presentation.client.SupportedFieldType;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-15
 * Time: PM12:55
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class PostImpl implements Post, AdminMainEntity {


    @Id
    @GeneratedValue
    @AdminPresentation
    private Long id;

    @AdminPresentation(prominent = true, friendlyName = "Title", gridOrder = 1)
    private String title;

    @Lob
    @AdminPresentation(fieldType = SupportedFieldType.HTML)
    private String content;

    @ManyToOne(targetEntity = PostCategoryImpl.class)
    @AdminPresentation(gridOrder = 2, friendlyName = "Category", prominent = true)
    @AdminPresentationToOneLookup(lookupDisplayProperty = "name")
    private PostCategory category;



    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public PostCategory getCategory() {
        return category;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void setCategory(PostCategory category) {
        this.category = category;
    }

    @Override
    public String getMainEntityName() {
        return "Post";
    }
}
