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
public class ConcreteProjectTest {
    
    public ConcreteProjectTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test(expected=NullPointerException.class)
    public void testGetName_throw_NullPointerException() {
        String name = null;
        
        ConcreteProject.create(name);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetName_empty_throw_IllegalArgumentException() {
        String name = "";
        
        ConcreteProject.create(name);
    }
    
    @Test
    public void testGetName_valid() {
        String name = "Project X";
        
        ConcreteProject result = ConcreteProject.create(name);
        
        assertEquals("Project X", result.getName());
    }
    
    @Test
    public void testHasGetDescription_empty_is_false() {
        String name = "Project X";
        String description = "";
        int projectNr = -1;
        String projectURL = null;
        String version = null;
        boolean visible = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertFalse(result.hasDescription());
        assertFalse(result.getDescription().isPresent());
    }
    
    @Test
    public void testHasGetDescription_is_default_undefined() {
        String name = "Project X";
        
        ConcreteProject result = ConcreteProject.create(name);
        
        assertFalse(result.hasDescription());
        assertTrue(result.getDescription().isPresent());
        assertEquals("[undefined]", result.getDescription().get());
    }
    
    @Test
    public void testHasGetDescription_is_valid() {
        String name        = "Project X";
        String description = "my-description";
        int projectNr = -1;
        String projectURL  = "";
        String version     = "";
        boolean visible    = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertTrue(result.hasDescription());
        assertTrue(result.getDescription().isPresent());
        assertEquals("my-description", result.getDescription().get());
    }
    
    @Test
    public void testHasGetDescription_null_is_not_present() {
        String name = "Project X";
        String description = null;
        int projectNr = -1;
        String projectURL = null;
        String version = null;
        boolean visible = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertFalse(result.hasDescription());
        assertFalse(result.getDescription().isPresent());
    }

    @Test
    public void testHasGetDescription_value_undefined_is_not_valid() {
        String name        = "Project X";
        String description = "[undefined]";
        int projectNr = -1;
        String projectURL  = "";
        String version     = "";
        boolean visible    = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertFalse(result.hasDescription());
        assertTrue(result.getDescription().isPresent());
        assertEquals("[undefined]", result.getDescription().get());
    }
    
    @Test
    public void testGetProjectNr_is_default_minus1() {
        String name = "Project X";
        
        ConcreteProject result = ConcreteProject.create(name);
        
        assertEquals(-1, result.getProjectNr());
    }
    
    @Test
    public void testGetProjectNr_is_lesser_then_default_value() {
        String name        = "Project X";
        String description = "[undefined]";
        int projectNr = -123;
        String projectURL  = "";
        String version     = "";
        boolean visible    = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertEquals(-1, result.getProjectNr());
    }
    
    @Test
    public void testGetProjectNr_is_equals_default_value() {
        String name        = "Project X";
        String description = "[undefined]";
        int projectNr = -1;
        String projectURL  = "";
        String version     = "";
        boolean visible    = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertEquals(-1, result.getProjectNr());
    }
    
    @Test
    public void testGetProjectNr_is_greater_then_default_value() {
        String name        = "Project X";
        String description = "[undefined]";
        int projectNr = 5678;
        String projectURL  = "";
        String version     = "";
        boolean visible    = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertEquals(5678, result.getProjectNr());
    }
    
    @Test
    public void testHasGetProjectURL_empty_is_false() {
        String name = "Project X";
        String description = null;
        int projectNr = -1;
        String projectURL = "";
        String version = null;
        boolean visible = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertFalse(result.hasProjectURL());
        assertFalse(result.getProjectURL().isPresent());
    }
    
    @Test
    public void testHasGetProjectURL_is_default_undefined() {
        String name = "Project X";
        
        ConcreteProject result = ConcreteProject.create(name);
        
        assertFalse(result.hasProjectURL());
        assertTrue(result.getProjectURL().isPresent());
        assertEquals("[undefined]", result.getProjectURL().get());
    }
    
    @Test
    public void testHasGetProjectURL_is_valid() {
        String name        = "Project X";
        String description = "my-description";
        int projectNr = -1;
        String projectURL  = "my-projecturl";
        String version     = "";
        boolean visible    = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertTrue(result.hasProjectURL());
        assertTrue(result.getProjectURL().isPresent());
        assertEquals("my-projecturl", result.getProjectURL().get());
    }
    
    @Test
    public void testHasGetProjectURL_null_is_not_present() {
        String name = "Project X";
        String description = null;
        int projectNr = -1;
        String projectURL = null;
        String version = null;
        boolean visible = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertFalse(result.hasProjectURL());
        assertFalse(result.getProjectURL().isPresent());
    }
    
    @Test
    public void testHasGetProjectURL_value_undefined_is_not_valid() {
        String name        = "Project X";
        String description = "[undefined]";
        int projectNr = -1;
        String projectURL  = "[undefined]";
        String version     = "";
        boolean visible    = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertFalse(result.hasProjectURL());
        assertTrue(result.getProjectURL().isPresent());
        assertEquals("[undefined]", result.getProjectURL().get());
    }
    
    @Test
    public void testHasGetVersion_empty_is_false() {
        String name = "Project X";
        String description = null;
        int projectNr = -1;
        String projectURL = null;
        String version = "";
        boolean visible = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertFalse(result.hasVersion());
        assertFalse(result.getVersion().isPresent());
    }
    
    @Test
    public void testHasGetVersion_is_default_undefined() {
        String name = "Project X";
        
        ConcreteProject result = ConcreteProject.create(name);
        
        assertFalse(result.hasVersion());
        assertTrue(result.getVersion().isPresent());
        assertEquals("[undefined]", result.getVersion().get());
    }

    @Test
    public void testHasGetVersion_is_valid() {
        String name        = "Project X";
        String description = "my-description";
        int projectNr = -1;
        String projectURL  = "my-projecturl";
        String version     = "1.2.3";
        boolean visible    = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertTrue(result.hasVersion());
        assertTrue(result.getVersion().isPresent());
        assertEquals("1.2.3", result.getVersion().get());
    }

    @Test
    public void testHasGetVersion_null_is_not_present() {
        String name = "Project X";
        String description = null;
        int projectNr = -1;
        String projectURL = null;
        String version = null;
        boolean visible = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertFalse(result.hasVersion());
        assertFalse(result.getVersion().isPresent());
    }

    @Test
    public void testHasGetVersion_value_undefined_is_not_valid() {
        String name        = "Project X";
        String description = "[undefined]";
        int projectNr = -1;
        String projectURL  = "[undefined]";
        String version     = "[undefined]";
        boolean visible    = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertFalse(result.hasVersion());
        assertTrue(result.getVersion().isPresent());
        assertEquals("[undefined]", result.getVersion().get());
    }

    @Test
    public void testIsVisible_is_default_true() {
        String name = "Project X";
        
        ConcreteProject result = ConcreteProject.create(name);
        
        assertTrue(result.isVisible());
    }

    @Test
    public void testIsVisible_is_false() {
        String name = "Project X";
        String description = null;
        int projectNr = -1;
        String projectURL = null;
        String version = null;
        boolean visible = false;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertFalse(result.isVisible());
    }

    @Test
    public void testIsVisible_is_true() {
        String name = "Project X";
        String description = null;
        int projectNr = -1;
        String projectURL = null;
        String version = null;
        boolean visible = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertTrue(result.isVisible());
    }
    
    @Test
    public void testCreate_1arg() {
        String name = "Project X";
        ConcreteProject result = ConcreteProject.create(name);
        
        assertEquals("Project X", result.getName());
        assertFalse(result.hasDescription());
        assertEquals("[undefined]", result.getDescription().get());
        assertEquals(-1, result.getProjectNr());
        assertFalse(result.hasProjectURL());
        assertEquals("[undefined]", result.getProjectURL().get());
        assertFalse(result.hasVersion());
        assertEquals("[undefined]", result.getVersion().get());
    }
    
    @Test
    public void testCreate_6args() {
        String name        = "Project X";
        String description = "my-description";
        int projectNr = 234;
        String projectURL  = "my-projecturl.com";
        String version     = "1.2.3";
        boolean visible    = true;
        
        ConcreteProject result = ConcreteProject.create(
                name, description, projectNr,
                projectURL, version, visible);
        
        assertEquals("Project X", result.getName());
        assertTrue(result.hasDescription());
        assertTrue(result.getDescription().isPresent());
        assertEquals("my-description", result.getDescription().get());
        assertEquals(234, result.getProjectNr());
        assertTrue(result.hasProjectURL());
        assertTrue(result.getProjectURL().isPresent());
        assertEquals("my-projecturl.com", result.getProjectURL().get());
        assertTrue(result.hasVersion());
        assertTrue(result.getVersion().isPresent());
        assertEquals("1.2.3", result.getVersion().get());
        assertTrue(result.isVisible());
    }

    @Test
    public void testCreate_7args() {
        long id            = 566l;
        String name        = "Project X";
        String description = "my-description";
        int projectNr      = 234;
        String projectURL  = "my-projecturl.com";
        String version     = "1.2.3";
        boolean visible    = true;
        
        ConcreteProject result = ConcreteProject.create(
                id, name, description,
                projectNr, projectURL, version,
                visible);
        
        assertEquals(566l, result.getId());
        assertEquals("Project X", result.getName());
        assertTrue(result.hasDescription());
        assertTrue(result.getDescription().isPresent());
        assertEquals("my-description", result.getDescription().get());
        assertEquals(234, result.getProjectNr());
        assertTrue(result.hasProjectURL());
        assertTrue(result.getProjectURL().isPresent());
        assertEquals("my-projecturl.com", result.getProjectURL().get());
        assertTrue(result.hasVersion());
        assertTrue(result.getVersion().isPresent());
        assertEquals("1.2.3", result.getVersion().get());
        assertTrue(result.isVisible());
    }
    
    @Test
    public void testCompareTo_projectnr_and_name() {
        String name        = "Project a";
        String description = "";
        int projectNr      = 123;
        String projectURL  = "www.project.com";
        String version     = "0.0.1";
        boolean visible    = true;
        ConcreteProject result1 = ConcreteProject.create(
                name, description, 
                projectNr, projectURL,
                version, visible);
        
        String name2        = "Project b";
        String description2 = "";
        int projectNr2      = 234;
        String projectURL2  = "www.project.com";
        String version2     = "0.0.2";
        boolean visible2    = true;
        ConcreteProject result2 = ConcreteProject.create(
                name2, description2, 
                projectNr2, projectURL2,
                version2, visible2);
        
        assertTrue(result1.compareTo(result2) == -1);
    }

    @Test
    public void testCompareTo_version() {
        String name        = "Project a";
        String description = "";
        int projectNr      = 123;
        String projectURL  = "www.project.com";
        String version     = "0.0.1";
        boolean visible    = true;
        ConcreteProject result1 = ConcreteProject.create(
                name, description, 
                projectNr, projectURL,
                version, visible);
        
        String name2        = "Project a";
        String description2 = "";
        int projectNr2      = 123;
        String projectURL2  = "www.project.com";
        String version2     = "0.0.2";
        boolean visible2    = true;
        ConcreteProject result2 = ConcreteProject.create(
                name2, description2, 
                projectNr2, projectURL2,
                version2, visible2);
        
        assertTrue(result1.compareTo(result2) == -1);
    }

    @Test
    public void testCompareTo_projecturl() {
        String name        = "Project a";
        String description = "";
        int projectNr      = 123;
        String projectURL  = "www.project1.com";
        String version     = "0.0.1";
        boolean visible    = true;
        ConcreteProject result1 = ConcreteProject.create(
                name, description, 
                projectNr, projectURL,
                version, visible);
        
        String name2        = "Project a";
        String description2 = "";
        int projectNr2      = 123;
        String projectURL2  = "www.project2.com";
        String version2     = "0.0.1";
        boolean visible2    = true;
        ConcreteProject result2 = ConcreteProject.create(
                name2, description2, 
                projectNr2, projectURL2,
                version2, visible2);
        
        assertTrue(result1.compareTo(result2) == -1);
    }

    @Test
    public void testEquals_true() {
        long id            = -123L;
        String name        = "Project a";
        String description = "";
        int projectNr      = 23;
        String projectURL  = "www.project.com";
        String version     = "0.0.1";
        boolean visible    = true;
        ConcreteProject result1 = ConcreteProject.create(
                id, name, description, 
                projectNr, projectURL, version, 
                visible);
        
        long id2            = -123L;
        String name2        = "Project a";
        String description2 = "";
        int projectNr2      = 23;
        String projectURL2  = "www.project.com";
        String version2     = "0.0.1";
        boolean visible2    = true;
        ConcreteProject result2 = ConcreteProject.create(
                id2, name2, description2, 
                projectNr2, projectURL2, version2, 
                visible2);
        
        assertTrue(result1.equals(result2));
    }

    @Test
    public void testToString() {
        long id            = -123L;
        String name        = "Project a";
        String description = "xyz";
        int projectNr      = 23;
        String projectURL  = "www.project.com";
        String version     = "0.0.1";
        boolean visible    = true;
        ConcreteProject result1 = ConcreteProject.create(id, name, description, 
                projectNr, projectURL, version, visible);
        
        String expected = "ConcreteProject [id=-123, name=Project a, description=xyz, projectNr=23, "
                + "projectURL=www.project.com, version=0.0.1, visible=true]";
        assertEquals(expected, result1.toString());
    }
    
}
