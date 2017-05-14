/*
 * Copyright (C) 2017 Naoghuman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.naoghuman.demo.template.images;

import com.github.naoghuman.demo.template.configuration.ITemplateConfiguration;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.lib.properties.api.PropertiesFacade;
import java.net.URI;
import java.util.Optional;
import javafx.scene.image.Image;

/**
 *
 * @author Naoghuman
 */
public class ImageLoader implements ITemplateConfiguration {
    
    private static final String COMING_SOON__ICON = PropertiesFacade.getDefault().getProperty(KEY__TEMPLATE__RESOURCE_BUNDLE, KEY__TEMPLATE__IMAGES_IMAGELOADER_IMAGE_COMINGSOON);
	
    private static final Optional<ImageLoader> INSTANCE = Optional.of(new ImageLoader());

    public static final ImageLoader getDefault() {
        return INSTANCE.get();
    }

    private ImageLoader() {

    }
    
    public Optional<Image> loadComingSoonImage() {
        Optional<Image> placeHolderImage;
        try {
            final URI uri = ImageLoader.class.getResource(COMING_SOON__ICON).toURI();
            placeHolderImage = Optional.ofNullable(new Image(uri.toString(), 300.0d, 145.0d, true, true, true));
//            placeHolderImage = Optional.ofNullable(new Image(uri.toString(), 400.0d, 193.0d, true, true, true));
        } catch (Exception ex) {
            LoggerFacade.getDefault().error(this.getClass(), "Can't load the image: " + COMING_SOON__ICON, ex); // NOI18N
            
            placeHolderImage = Optional.empty();
        }
        
        return placeHolderImage;
    }
}
