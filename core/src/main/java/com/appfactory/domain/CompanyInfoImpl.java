package com.appfactory.domain;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.client.SupportedFieldType;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/25/13
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "BLC_APP_COMPANY_INFO")
@AdminPresentationClass(friendlyName = "CompanyInfoImpl")
@NamedQueries({
        @NamedQuery(
                name = "FIND_DEFAULT_COMPANYINFO",
                query = "select info from CompanyInfoImpl as info where info.id = -1"
        )
})
public class CompanyInfoImpl implements CompanyInfo {

    @Id
    private Long id;


    @Lob
    @AdminPresentation(friendlyName = "CompanyInfoImpl_content", prominent = true, fieldType = SupportedFieldType.HTML)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
