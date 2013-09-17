package com.appfactory.domain;

import org.broadleafcommerce.cms.file.domain.ImageStaticAsset;
import org.broadleafcommerce.cms.file.domain.StaticAsset;
import org.broadleafcommerce.common.media.domain.Media;

import java.io.Serializable;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-8
 * Time: PM3:16
 * To change this template use File | Settings | File Templates.
 */
public interface Cell extends Serializable {
    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getType();

    String getIconUrl();

    Map<String, Media> getMedias();

    void setMedias(Map<String, Media> medias);
}
