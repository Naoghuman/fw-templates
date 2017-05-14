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
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import com.github.naoghuman.lib.properties.api.PropertiesFacade;
import java.util.Optional;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
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
    
    private static final Text TEXT_COMING_SOON = new Text();
    private static final Text TEXT_TITLE = new Text();
    private static final TextArea TEXTAREA_DESCRIPTION = new TextArea();
    private static final VBox VBOX_COMING_SOON = new VBox();
    private static final VBox VBOX_INFORMATION = new VBox();
    
    static {
        TEXT_COMING_SOON.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, FontPosture.ITALIC, 48.0d));
        TEXT_COMING_SOON.setFill(Color.RED);
        TEXT_COMING_SOON.setText(PropertiesFacade.getDefault().getProperty(KEY__TEMPLATE__RESOURCE_BUNDLE, KEY__TEMPLATE__PROJECT_COMINGSOONVIEW_TEXT));
    
        TEXTAREA_DESCRIPTION.setEditable(Boolean.FALSE);
        TEXTAREA_DESCRIPTION.setFont(Font.font(Font.getDefault().getFamily(), 16.0d));
        TEXTAREA_DESCRIPTION.setPrefHeight(128.0d);
        TEXTAREA_DESCRIPTION.setWrapText(true);
        TEXTAREA_DESCRIPTION.getStylesheets().add("/com/github/naoghuman/demo/template/project/comingsoonview.css");
    
        TEXT_TITLE.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 56.0d));
        TEXT_TITLE.setFill(Color.BLACK);
        
        VBOX_COMING_SOON.setAlignment(Pos.CENTER);
        VBOX_COMING_SOON.setPadding(new Insets(0, 96.0d, 0, 96.0d));
        VBox.setVgrow(VBOX_COMING_SOON, Priority.ALWAYS);
    
        VBOX_INFORMATION.setAlignment(Pos.CENTER);
        VBOX_INFORMATION.setSpacing(14.0d);
        VBOX_INFORMATION.setPadding(new Insets(14.0d));
        VBOX_INFORMATION.setStyle( // TODO move stylesheet into own file
                "-fx-base: LIGHTSKYBLUE; "
                + "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color; "
                + "-fx-background-radius: 5, 5, 4, 3; "
                + "-fx-background-insets: 0 0 -1 0, 0, 1, 2; "
                + "-fx-border-width: 0;");
    }
    
    public static final VBox getComingSoonView(final String title, final Optional<String> description) {
        LoggerFacade.getDefault().debug(ComingSoonView.class, "Get [ComingSoonView]"); // NOI18N
        
        // Cleanup
        VBOX_COMING_SOON.getChildren().clear();
        VBOX_INFORMATION.getChildren().clear();
        
        // Title
        TEXT_TITLE.setText(title);
        VBOX_INFORMATION.getChildren().add(TEXT_TITLE);
        
        // Separator
        VBOX_INFORMATION.getChildren().add(new Separator());
        
        // Image
        final Optional<Image> image = ImageLoader.getDefault().loadComingSoonImage();
        if (image.isPresent()) {
            final ImageView imageView = new ImageView();
            imageView.setImage(image.get());
            VBOX_INFORMATION.getChildren().add(imageView);
        }
        else {
            VBOX_INFORMATION.getChildren().add(TEXT_COMING_SOON);
        }
        
        // Separator
        VBOX_INFORMATION.getChildren().add(new Separator());
        
        // Description
        TEXTAREA_DESCRIPTION.setText(description.isPresent() ? description.get() : "[undefinend]"); // NOI18N
        VBOX_INFORMATION.getChildren().add(TEXTAREA_DESCRIPTION);
        
        VBOX_COMING_SOON.getChildren().add(VBOX_INFORMATION);
        
        return VBOX_COMING_SOON;
    }
    
}
