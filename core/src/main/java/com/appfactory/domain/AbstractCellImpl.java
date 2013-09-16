package com.appfactory.domain;

import org.broadleafcommerce.cms.file.domain.ImageStaticAsset;
import org.broadleafcommerce.cms.file.domain.StaticAsset;
import org.broadleafcommerce.cms.file.domain.StaticAssetImpl;
import org.broadleafcommerce.common.admin.domain.AdminMainEntity;
import org.broadleafcommerce.common.media.domain.Media;
import org.broadleafcommerce.common.media.domain.MediaImpl;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationToOneLookup;
import org.broadleafcommerce.common.presentation.client.LookupType;
import org.broadleafcommerce.common.presentation.client.VisibilityEnum;

import javax.persistence.*;

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
public abstract class AbstractCellImpl implements Cell, AdminMainEntity {

    @Id
    @GeneratedValue
    @AdminPresentation(friendlyName = "ID", visibility = VisibilityEnum.HIDDEN_ALL)
    protected Long id;

    @AdminPresentation(friendlyName = "Name", gridOrder = 1, columnWidth = "100px", prominent = true)
    private String name;

    @ManyToOne(targetEntity = StaticAssetImpl.class)
    @JoinColumn(name = "MEDIA_ID")
    @AdminPresentation(friendlyName = "Image")
    @AdminPresentationToOneLookup()
    private StaticAsset media;





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
    public StaticAsset getMedia() {
        return media;
    }

    @Override
    public void setMedia(StaticAsset media) {
        this.media = media;
    }
}
