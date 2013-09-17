package com.appfactory.domain;

import org.broadleafcommerce.common.admin.domain.AdminMainEntity;
import org.broadleafcommerce.common.media.domain.Media;
import org.broadleafcommerce.common.media.domain.MediaImpl;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationMap;
import org.broadleafcommerce.common.presentation.AdminPresentationMapKey;
import org.broadleafcommerce.common.presentation.client.SupportedFieldType;
import org.broadleafcommerce.common.presentation.client.VisibilityEnum;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-8
 * Time: PM3:09
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "BLC_APP_CELL")
@Inheritance(strategy = InheritanceType.JOINED)
@AdminPresentationClass(friendlyName = "Cell")
@NamedQueries(
        {
                @NamedQuery(
                        name = "FIND_ALL_CELL",
                        query = "SELECT c FROM AbstractCellImpl as c"
                )
        }
)
public abstract class AbstractCellImpl implements Cell, AdminMainEntity {

    @Id
    @GeneratedValue
    @AdminPresentation(friendlyName = "ID", visibility = VisibilityEnum.HIDDEN_ALL)
    protected Long id;

    @AdminPresentation(friendlyName = "Name", gridOrder = 1, columnWidth = "100px", prominent = true)
    private String name;

    @ManyToMany(targetEntity = MediaImpl.class)
    @JoinTable(name = "BLC_APP_CELL_MEDIA_MAP", inverseJoinColumns = @JoinColumn(name = "MEDIA_ID", referencedColumnName = "MEDIA_ID"))
    @MapKeyColumn(name = "MAP_KEY")
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blStandardElements")
    @BatchSize(size = 50)
    @AdminPresentationMap(
            friendlyName = "Picture",
            keyPropertyFriendlyName = "SkuImpl_Sku_Media_Key",
            deleteEntityUponRemove = true,
            mediaField = "url",
            keys = {
                    @AdminPresentationMapKey(keyName = "primary", friendlyKeyName = "mediaPrimary"),
            }
    )
    private Map<String, Media> medias = new HashMap<String , Media>(10);


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getMainEntityName() {
        return "Cell";
    }

    @Override
    public String getIconUrl() {
        return this.getMedias().get("primary").getUrl();
    }

    @Override
    public Map<String, Media> getMedias() {
        return medias;
    }

    @Override
    public void setMedias(Map<String, Media> medias) {
        this.medias = medias;
    }
}
