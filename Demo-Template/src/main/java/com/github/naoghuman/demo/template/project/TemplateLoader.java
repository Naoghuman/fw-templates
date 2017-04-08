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

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Naoghuman
 */
public class TemplateLoader {
    
    private static final String APPEND__NEWLINE = "\n";  // NOI18N
    
    private static final String PLACE_HOLDER__CSS         = "<css/>";  // NOI18N
    private static final String PLACE_HOLDER__SOURCE_CODE = "<sourceCode/>"; // NOI18N
    
    private static final String REPLACE__TARGET      = "<"; // NOI18N
    private static final String REPLACE__REPLACEMENT = "&lt;"; // NOI18N
    
    private static final String TEMPLATE__CSS                    = "/com/github/naoghuman/demo/template/templates/CssTemplate.html"; // NOI18N
    private static final String TEMPLATE__NO_PROJECT_URL_DEFINED = "/com/github/naoghuman/demo/template/templates/NoProjectURLdefinedTemplate.html"; // NOI18N
    private static final String TEMPLATE__SOURCE_CODE            = "/com/github/naoghuman/demo/template/templates/SourceCodeTemplate.html"; // NOI18N
    
    public static final ObservableList<String> loadCSStemplates(final ObservableList<String> cssURLs) {
        final ObservableList<String> loadedCSStemplates = FXCollections.observableArrayList();
    
        cssURLs.stream()
                .forEach(cssURL -> {
                    String src = getResource(cssURL);
                    src = src.replace(REPLACE__TARGET, REPLACE__REPLACEMENT);
                    
                    String template = getResource(TemplateLoader.class.getResourceAsStream(TEMPLATE__CSS));
                    template = template.replace(PLACE_HOLDER__CSS, src);
                    
                    loadedCSStemplates.add(template);
                });
        
        return loadedCSStemplates;
    }
    
    public static final String loadNoProjectURLdefined() {
        return getResource(TemplateLoader.class.getResourceAsStream(TEMPLATE__NO_PROJECT_URL_DEFINED));
    }
    
    public static final ObservableList<String> loadSourceCodeTemplates(final ObservableList<String> sourceCodeURLs) {
        final ObservableList<String> loadedJavaDocTemplate = FXCollections.observableArrayList();
    
        sourceCodeURLs.stream()
                .forEach(sourceCodeURL -> {
                    String src = getResource(sourceCodeURL);
                    src = src.replace(REPLACE__TARGET, REPLACE__REPLACEMENT);
                    
                    String template = getResource(TemplateLoader.class.getResourceAsStream(TEMPLATE__SOURCE_CODE));
                    template = template.replace(PLACE_HOLDER__SOURCE_CODE, src);
                    
                    loadedJavaDocTemplate.add(template);
                });
        
        return loadedJavaDocTemplate;
    }
    
    private static String getResource(String name) {
        String loadedResource = null;
        try {
            loadedResource = new String(Files.readAllBytes(Paths.get(TemplateLoader.class.getResource(name).toURI())));
        } catch(URISyntaxException | IOException ex){
            LoggerFacade.getDefault().error(ProjectCollector.class, "Error read resources: " + name, ex); // NOI18N
        }
        
        return loadedResource;
    }

    private static String getResource(final InputStream inputStream) {
        final StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(APPEND__NEWLINE);
            }
        } catch (IOException ex) {
            LoggerFacade.getDefault().error(ProjectCollector.class, "Error read resources: ", ex); // NOI18N
        }
        
        return sb.toString();
    }
    
}
