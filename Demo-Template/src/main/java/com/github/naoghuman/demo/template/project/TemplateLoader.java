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
import java.net.URL;

/**
 *
 * @author Naoghuman
 */
public class TemplateLoader {
    
    private static final String APPEND__NEWLINE = "\n";  // NOI18N
    
    private static final String PLACE_HOLDER__CSS         = "<css/>";  // NOI18N
    private static final String PLACE_HOLDER__SOURCE_CODE = "<sourcecode/>"; // NOI18N
    
    private static final String REPLACE__TARGET      = "<"; // NOI18N
    private static final String REPLACE__REPLACEMENT = "&lt;"; // NOI18N
    
    private static final String TEMPLATE__CSS                          = "/com/github/naoghuman/demo/template/templates/CssTemplate.html"; // NOI18N
    private static final String TEMPLATE__LOADING                      = "/com/github/naoghuman/demo/template/templates/LoadingTemplate.html"; // NOI18N
    private static final String TEMPLATE__NO_CSS_URL_IS_DEFINED        = "/com/github/naoghuman/demo/template/templates/NoCssURLisDefinedTemplate.html"; // NOI18N
    private static final String TEMPLATE__NO_JAVADOC_URL_IS_DEFINED    = "/com/github/naoghuman/demo/template/templates/NoJavaDocURLisDefinedTemplate.html"; // NOI18N
    private static final String TEMPLATE__NO_OVERVIEW_URL_IS_DEFINED   = "/com/github/naoghuman/demo/template/templates/NoOverviewURLisDefinedTemplate.html"; // NOI18N
    private static final String TEMPLATE__NO_PROJECT_URL_IS_DEFINED    = "/com/github/naoghuman/demo/template/templates/NoProjectURLisDefinedTemplate.html"; // NOI18N
    private static final String TEMPLATE__NO_SOURCECODE_URL_IS_DEFINED = "/com/github/naoghuman/demo/template/templates/NoSourceCodeURLisDefinedTemplate.html"; // NOI18N
    private static final String TEMPLATE__SOURCE_CODE                  = "/com/github/naoghuman/demo/template/templates/SourceCodeTemplate.html"; // NOI18N
    
    private static String loadingTemplate                  = null;
    private static String noCSSURLisDefinedTemplate        = null;
    private static String noJavaDocURLisDefinedTemplate    = null;
    private static String noOverviewURLisDefinedTemplate   = null;
    private static String noProjectURLisDefinedTemplate    = null;
    private static String noSourceCodeURLisDefinedTemplate = null;
    
    public static final String loadCSStemplate(final String cssURL) {
        LoggerFacade.getDefault().debug(TemplateLoader.class, "Load css template"); // NOI18N
        
        String src = getResource(cssURL);
        src = src.replace(REPLACE__TARGET, REPLACE__REPLACEMENT);

        String template = getResource(TemplateLoader.class.getResourceAsStream(TEMPLATE__CSS));
        template = template.replace(PLACE_HOLDER__CSS, src);
        
        return template;
    }
    
    public static final String loadLoadingTemplate() {
        LoggerFacade.getDefault().debug(TemplateLoader.class, "Load loading template"); // NOI18N
        
        if (loadingTemplate == null) {
            loadingTemplate = getResource(TemplateLoader.class.getResourceAsStream(TEMPLATE__LOADING));
        }
        
        return loadingTemplate;
    }
    
    public static final String loadNoCssURLisDefinedTemplate() {
        LoggerFacade.getDefault().debug(TemplateLoader.class, "Load no css-url is defined template"); // NOI18N
        
        if (noCSSURLisDefinedTemplate == null) {
            noCSSURLisDefinedTemplate = getResource(TemplateLoader.class.getResourceAsStream(TEMPLATE__NO_CSS_URL_IS_DEFINED));
        }
        
        return noCSSURLisDefinedTemplate;
    }
    
    public static final String loadNoJavaDocURLisDefinedTemplate() {
        LoggerFacade.getDefault().debug(TemplateLoader.class, "Load no javadoc-url is defined template"); // NOI18N
        
        if (noJavaDocURLisDefinedTemplate == null) {
            noJavaDocURLisDefinedTemplate = getResource(TemplateLoader.class.getResourceAsStream(TEMPLATE__NO_JAVADOC_URL_IS_DEFINED));
        }
        
        return noJavaDocURLisDefinedTemplate;
    }

    public static String loadNoOverviewURLisDefinedTemplate() {
        LoggerFacade.getDefault().debug(TemplateLoader.class, "Load no overview-url is defined template"); // NOI18N
        
        if (noOverviewURLisDefinedTemplate == null) {
            noOverviewURLisDefinedTemplate = getResource(TemplateLoader.class.getResourceAsStream(TEMPLATE__NO_OVERVIEW_URL_IS_DEFINED));
        }
        
        return noOverviewURLisDefinedTemplate;
    }
    
    public static final String loadNoProjectURLisDefinedTemplate() {
        LoggerFacade.getDefault().debug(TemplateLoader.class, "Load no project-url is defined template"); // NOI18N
        
        if (noProjectURLisDefinedTemplate == null) {
            noProjectURLisDefinedTemplate = getResource(TemplateLoader.class.getResourceAsStream(TEMPLATE__NO_PROJECT_URL_IS_DEFINED));
        }
        
        return noProjectURLisDefinedTemplate;
    }
    
    public static final String loadNoSourceCodeURLisDefinedTemplate() {
        LoggerFacade.getDefault().debug(TemplateLoader.class, "Load no sourcecode-url is defined template"); // NOI18N
        
        if (noSourceCodeURLisDefinedTemplate == null) {
            noSourceCodeURLisDefinedTemplate = getResource(TemplateLoader.class.getResourceAsStream(TEMPLATE__NO_SOURCECODE_URL_IS_DEFINED));
        }
        
        return noSourceCodeURLisDefinedTemplate;
    }
    
    public static final void loadResourcesInCache() {
        LoggerFacade.getDefault().debug(TemplateLoader.class, "Load resources in cache"); // NOI18N
        
        TemplateLoader.loadLoadingTemplate();
        TemplateLoader.loadNoCssURLisDefinedTemplate();
        TemplateLoader.loadNoJavaDocURLisDefinedTemplate();
        TemplateLoader.loadNoOverviewURLisDefinedTemplate();
        TemplateLoader.loadNoProjectURLisDefinedTemplate();
        TemplateLoader.loadNoSourceCodeURLisDefinedTemplate();
    }
    
    public static final String loadSourceCodeTemplate(final String sourceCodeURL) {
        LoggerFacade.getDefault().debug(TemplateLoader.class, "Load SourceCode template"); // NOI18N
        
        String src = getResource(sourceCodeURL);
        src = src.replace(REPLACE__TARGET, REPLACE__REPLACEMENT);

        String template = getResource(TemplateLoader.class.getResourceAsStream(TEMPLATE__SOURCE_CODE));
        template = template.replace(PLACE_HOLDER__SOURCE_CODE, src);
        
        return template;
    }
    
    private static String getResource(String name) {
        String loadedResource = null;
        try {
            final URL url = new URL(name);
            loadedResource = getResource(url.openStream());
        } catch(IOException ex){
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
