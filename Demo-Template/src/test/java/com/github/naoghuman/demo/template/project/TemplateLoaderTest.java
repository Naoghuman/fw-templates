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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Naoghuman
 */
public class TemplateLoaderTest {
    
    public TemplateLoaderTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testLoadCSStemplate() {
//        System.out.println("loadCSStemplate");
//        String cssURL = "";
//        String expResult = "";
//        String result = TemplateLoader.loadCSStemplate(cssURL);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testLoadLoadingTemplate() {
        final String TEMPLATE__LOADING =     
"<!DOCTYPE html>\n"+
"<!--\n"+
"Copyright (C) 2017 Naoghuman\n"+
"\n"+
"This program is free software: you can redistribute it and/or modify\n"+
"it under the terms of the GNU General Public License as published by\n"+
"the Free Software Foundation, either version 3 of the License, or\n"+
"(at your option) any later version.\n"+
"\n"+
"This program is distributed in the hope that it will be useful,\n"+
"but WITHOUT ANY WARRANTY; without even the implied warranty of\n"+
"MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n"+
"GNU General Public License for more details.\n"+
"\n"+
"You should have received a copy of the GNU General Public License\n"+
"along with this program.  If not, see <http://www.gnu.org/licenses/>.\n"+
"-->\n"+
"<html>\n"+
"    <head>\n"+
"        <title>loading</title>\n"+
"        <meta charset=\"UTF-8\">\n"+
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"+
"        <style>\n"+
"            .margin-100 {\n"+
"                color: red;\n"+
"                font-size: 44px;\n"+
"                margin-top: 100px;\n"+
"            }\n"+
"            .margin-20 {\n"+
"                color: red;\n"+
"                font-size: 22px;\n"+
"                font-style: italic;\n"+
"                margin-top: 11px;\n"+
"            }\n"+
"        </style>\n"+
"    </head>\n"+
"    <body>\n"+
"        <div align=\"center\" class=\"margin-100\">\n"+
"            &nbsp;\n"+
"        </div>\n"+
"        <div align=\"center\" class=\"margin-20\">\n"+
"            loading ...\n"+
"        </div>\n"+
"    </body>\n"+
"</html>\n";
        
        String result = TemplateLoader.loadLoadingTemplate();
        assertEquals(TEMPLATE__LOADING, result);
    }

    @Test
    public void testLoadNoCSSURLisDefinedTemplate() {
        final String TEMPLATE__NO_CSS_URL_IS_DEFINED = 
"<!DOCTYPE html>\n"+
"<!--\n"+
"Copyright (C) 2017 Naoghuman\n"+
"\n"+
"This program is free software: you can redistribute it and/or modify\n"+
"it under the terms of the GNU General Public License as published by\n"+
"the Free Software Foundation, either version 3 of the License, or\n"+
"(at your option) any later version.\n"+
"\n"+
"This program is distributed in the hope that it will be useful,\n"+
"but WITHOUT ANY WARRANTY; without even the implied warranty of\n"+
"MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n"+
"GNU General Public License for more details.\n"+
"\n"+
"You should have received a copy of the GNU General Public License\n"+
"along with this program.  If not, see <http://www.gnu.org/licenses/>.\n"+
"-->\n"+
"<html>\n"+
"    <head>\n"+
"        <title>No css-url is defined</title>\n"+
"        <meta charset=\"UTF-8\">\n"+
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"+
"        <style>\n"+
"            .margin-100 {\n"+
"                color: red;\n"+
"                font-size: 44px;\n"+
"                margin-top: 100px;\n"+
"            }\n"+
"            .margin-20 {\n"+
"                font-size: 22px;\n"+
"                margin-top: 11px;\n"+
"            }\n"+
"        </style>\n"+
"    </head>\n"+
"    <body>\n"+
"        <div align=\"center\" class=\"margin-100\">\n"+
"            Warning\n"+
"        </div>\n"+
"        <div align=\"center\" class=\"margin-20\">\n"+
"            For this project no CSS-URL is defined!\n"+
"        </div>\n"+
"    </body>\n"+
"</html>\n";
        
        String result = TemplateLoader.loadNoCssURLisDefinedTemplate();
        assertEquals(TEMPLATE__NO_CSS_URL_IS_DEFINED, result);
    }

    @Test
    public void testLoadNoJavaDocURLisDefinedTemplate() {
        final String TEMPLATE__NO_JAVADOC_URL_IS_DEFINED =
"<!DOCTYPE html>\n"+
"<!--\n"+
"Copyright (C) 2017 Naoghuman\n"+
"\n"+
"This program is free software: you can redistribute it and/or modify\n"+
"it under the terms of the GNU General Public License as published by\n"+
"the Free Software Foundation, either version 3 of the License, or\n"+
"(at your option) any later version.\n"+
"\n"+
"This program is distributed in the hope that it will be useful,\n"+
"but WITHOUT ANY WARRANTY; without even the implied warranty of\n"+
"MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n"+
"GNU General Public License for more details.\n"+
"\n"+
"You should have received a copy of the GNU General Public License\n"+
"along with this program.  If not, see <http://www.gnu.org/licenses/>.\n"+
"-->\n"+
"<html>\n"+
"    <head>\n"+
"        <title>No javadoc-url is defined</title>\n"+
"        <meta charset=\"UTF-8\">\n"+
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"+
"        <style>\n"+
"            .margin-100 {\n"+
"                color: red;\n"+
"                font-size: 44px;\n"+
"                margin-top: 100px;\n"+
"            }\n"+
"            .margin-20 {\n"+
"                font-size: 22px;\n"+
"                margin-top: 11px;\n"+
"            }\n"+
"        </style>\n"+
"    </head>\n"+
"    <body>\n"+
"        <div align=\"center\" class=\"margin-100\">\n"+
"            Warning\n"+
"        </div>\n"+
"        <div align=\"center\" class=\"margin-20\">\n"+
"            For this project no JavaDoc-URL is defined!\n"+
"        </div>\n"+
"    </body>\n"+
"</html>\n";

        String result = TemplateLoader.loadNoJavaDocURLisDefinedTemplate();
        assertEquals(TEMPLATE__NO_JAVADOC_URL_IS_DEFINED, result);
    }

    @Test
    public void testLoadNoProjectURLisDefinedTemplate() {
//        System.out.println("loadNoProjectURLisDefinedTemplate");
//        String expResult = "";
//        String result = TemplateLoader.loadNoProjectURLisDefinedTemplate();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testLoadNoSourceCodeURLisDefinedTemplate() {
//        System.out.println("loadNoSourceCodeURLisDefinedTemplate");
//        String expResult = "";
//        String result = TemplateLoader.loadNoSourceCodeURLisDefinedTemplate();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testLoadSourceCodeTemplate() {
//        System.out.println("loadSourceCodeTemplate");
//        String sourceCodeURL = "";
//        String expResult = "";
//        String result = TemplateLoader.loadSourceCodeTemplate(sourceCodeURL);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }
    
}
