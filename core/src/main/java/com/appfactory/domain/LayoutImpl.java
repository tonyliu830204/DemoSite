package com.appfactory.domain;

import org.broadleafcommerce.common.presentation.*;
import org.broadleafcommerce.common.presentation.override.*;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-16
 * Time: PM8:40
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "BLC_APP_MENUPAGE_LAYOUT")
@AdminPresentationClass(friendlyName = "LayoutImpl")
@NamedQueries({
        @NamedQuery(
                name = "FIND_DEFAULT_LAYOUT",
                query = "select l from LayoutImpl as l where l.id = -1"
        )
})

public class LayoutImpl implements Layout {

    @Id
    private Long id;

    @AdminPresentation(friendlyName = "LayoutImpl_Type", prominent = true)
    @AdminPresentationDataDrivenEnumeration(
            optionFilterParams = {
                    @OptionFilterParam(
                            param = "type.key",
                            value = "layout_type",
                            paramType = OptionFilterParamType.STRING
                    )
            }
    )
    @Column(name = "LAYOUT_TYPE")
    private String type;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
