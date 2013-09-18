package com.mycompany.api.endpoint.appfactory.wrappers;

import org.broadleafcommerce.core.catalog.domain.Category;
import org.broadleafcommerce.core.catalog.domain.Product;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/18/13
 * Time: 12:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class PromotionWrapper {

    private String text;

    private String iconURL;

    private String type;

    private String targetId;

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void wrap(Product product) {
        if (product.getProductAttributes() != null && product.getProductAttributes().containsKey("promotionMessage")) {
            this.text = product.getProductAttributes().get("promotionMessage").getValue();
        } else {
            this.text = product.getDescription();
        }
        if (product.getMedia() != null && product.getMedia().size() > 0) {
            this.iconURL = product.getMedia().get("primary").getUrl();
        }
        this.type = "PRODUCT";
        this.targetId = product.getId().toString();
    }

    public void wrap(Category category) {
        if (category.getCategoryAttributesMap() != null && category.getCategoryAttributesMap().containsKey("promotionMessage")) {
            this.text = category.getCategoryAttributesMap().get("promotionMessage").getValue();
        } else {
            this.text = category.getDescription();
        }
        if (category.getCategoryMedia() != null && category.getCategoryMedia().containsKey("primary")) {
            this.iconURL = category.getCategoryMedia().get("primary").getUrl();
        }
        this.type = "PRODUCT_CATEGORY";
        this.targetId = category.getId().toString();
    }
}
