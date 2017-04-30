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
package com.github.naoghuman.demo.template.project;

import com.github.naoghuman.demo.template.configuration.ITemplateConfiguration;
import com.github.naoghuman.demo.template.images.ImageLoader;
import com.github.naoghuman.lib.properties.api.PropertiesFacade;
import java.util.Optional;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Naoghuman
 */
public final class ComingSoonView implements ITemplateConfiguration {
    
    private static final Text TEXT = new Text();
    private static final VBox VBOX = new VBox();
    
    static {
        VBOX.setAlignment(Pos.CENTER);
        VBOX.setSpacing(7.0d);
        VBox.setVgrow(VBOX, Priority.ALWAYS);
        
        final Font FONT = Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, FontPosture.ITALIC, 48.0d);
        TEXT.setFont(FONT);
        TEXT.setFill(Color.RED);
        TEXT.setText(PropertiesFacade.getDefault().getProperty(KEY__TEMPLATE__RESOURCE_BUNDLE, KEY__TEMPLATE__PROJECT_COMINGSOONVIEW_TEXT));
    }
    
    public static final VBox getComingSoonView() {
        VBOX.getChildren().clear();
        
        final Optional<Image> image = ImageLoader.getDefault().loadComingSoonImage();
        if (image.isPresent()) {
            final ImageView imageView = new ImageView();
            imageView.setImage(image.get());
            VBOX.getChildren().add(imageView);
        }
        else {
            VBOX.getChildren().add(TEXT);
        }
        
        return VBOX;
    }
    
}
