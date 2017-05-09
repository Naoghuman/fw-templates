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

    @Test
    public void testCreate_1arg() {
        String name = "Project X";
        ConcreteProject result = ConcreteProject.create(name);
        
        assertEquals("Project X", result.getName());
    }

    @Test
    public void testCreate_1arg_default_values() {
        String name = "Project X";
        ConcreteProject result = ConcreteProject.create(name);
        
        assertEquals("Project X", result.getName());
        assertEquals(-1, result.getProjectNr());
        assertTrue(result.getProjectURL().isPresent());
        assertEquals("[undefined]", result.getProjectURL().get());
        assertTrue(result.getVersion().isPresent());
        assertEquals("[undefined]", result.getVersion().get());
    }

    @Test
    public void testCreate_3args() {
        String name = "Project X";
        String projectURL = "www.project.com";
        String version = "0.0.1";
        boolean visible = true;
        ConcreteProject result = ConcreteProject.create(name, projectURL, version, visible);
        
        assertEquals("Project X", result.getName());
        assertEquals(-1, result.getProjectNr());
        assertTrue(result.getProjectURL().isPresent());
        assertEquals("www.project.com", result.getProjectURL().get());
        assertTrue(result.getVersion().isPresent());
        assertEquals("0.0.1", result.getVersion().get());
    }

    @Test
    public void testCreate_5args() {
        long id = -1L;
        String name = "Project X";
        int projectNr = 23;
        String projectURL = "www.project.com";
        String version = "0.0.1";
        boolean visible = true;
        ConcreteProject result = ConcreteProject.create(id, name, projectNr, projectURL, version, visible);
        
        assertEquals(-1L, result.getId());
        assertEquals("Project X", result.getName());
        assertTrue(result.getProjectURL().isPresent());
        assertEquals("www.project.com", result.getProjectURL().get());
        assertTrue(result.getVersion().isPresent());
        assertEquals("0.0.1", result.getVersion().get());
    }

    @Test
    public void testHasProjectURL_false() {
        String name = "Project X";
        ConcreteProject result = ConcreteProject.create(name);
        
        assertFalse(result.hasProjectURL());
    }

    @Test
    public void testHasCssURL_false_empty() {
        String name = "Project X";
        String projectURL = "";
        String version = "0.0.1";
        boolean visible = true;
        ConcreteProject result = ConcreteProject.create(name, projectURL, version, visible);
        
        assertFalse(result.hasProjectURL());
    }

    @Test
    public void testHasProjectURL_true() {
        String name = "Project X";
        String projectURL = "www.project.com";
        String version = "0.0.1";
        boolean visible = true;
        ConcreteProject result = ConcreteProject.create(name, projectURL, version, visible);
        
        assertTrue(result.hasProjectURL());
    }

    @Test
    public void testGetProjectURL() {
        String name = "Project X";
        String projectURL = "www.project.com";
        String version = "0.0.1";
        boolean visible = true;
        ConcreteProject result = ConcreteProject.create(name, projectURL, version, visible);
        
        assertTrue(result.getProjectURL().isPresent());
        assertEquals("www.project.com", result.getProjectURL().get());
    }

    @Test
    public void testGetId() {
        long id = -1L;
        String name = "Project X";
        int projectNr = 23;
        String projectURL = "www.project.com";
        String version = "0.0.1";
        boolean visible = true;
        ConcreteProject result = ConcreteProject.create(id, name, projectNr, projectURL, version, visible);
        
        assertEquals(-1L, result.getId());
    }

    @Test
    public void testGetName() {
        String name = "Project X";
        String projectURL = "www.project.com";
        String version = "0.0.1";
        boolean visible = true;
        ConcreteProject result = ConcreteProject.create(name, projectURL, version, visible);
        
        assertEquals("Project X", result.getName());
    }

    @Test
    public void testGetVersion() {
        String name = "Project X";
        String projectURL = "www.project.com";
        String version = "0.0.1";
        boolean visible = true;
        ConcreteProject result = ConcreteProject.create(name, projectURL, version, visible);
        
        assertTrue(result.getVersion().isPresent());
        assertEquals("0.0.1", result.getVersion().get());
    }

    @Test
    public void testIsVisibleTrue() {
        long id = -1L;
        String name = "Project X";
        int projectNr = 23;
        String projectURL = "www.project.com";
        String version = "0.0.1";
        boolean visible = true;
        ConcreteProject result = ConcreteProject.create(id, name, projectNr, projectURL, version, visible);
        
        assertEquals(true, result.isVisible());
    }

    @Test
    public void testIsVisibleFalse() {
        long id = -1L;
        String name = "Project X";
        int projectNr = 23;
        String projectURL = "www.project.com";
        String version = "0.0.1";
        boolean visible = false;
        ConcreteProject result = ConcreteProject.create(id, name, projectNr, projectURL, version, visible);
        
        assertEquals(false, result.isVisible());
    }

    @Test
    public void testCompareTo_name() {
        String name = "Project a";
        String projectURL = "www.project.com";
        String version = "0.0.1";
        boolean visible = true;
        ConcreteProject result1 = ConcreteProject.create(name, projectURL, version, visible);
        
        String name2 = "Project b";
        String projectURL2 = "www.project.com";
        String version2 = "0.0.2";
        boolean visible2 = true;
        ConcreteProject result2 = ConcreteProject.create(name2, projectURL2, version2, visible2);
        
        assertTrue(result1.compareTo(result2) == -1);
    }

    @Test
    public void testCompareTo_name_version() {
        String name = "Project a";
        String projectURL = "www.project.com";
        String version = "0.0.1";
        boolean visible = true;
        ConcreteProject result1 = ConcreteProject.create(name, projectURL, version, visible);
        
        String name2 = "Project a";
        String projectURL2 = "www.project.com";
        String version2 = "0.0.2";
        boolean visible2 = true;
        ConcreteProject result2 = ConcreteProject.create(name2, projectURL2, version2, visible2);
        
        assertTrue(result1.compareTo(result2) == -1);
    }

    @Test
    public void testCompareTo_name_version_projecturl() {
        String name = "Project a";
        String projectURL = "www.project1.com";
        String version = "0.0.1";
        boolean visible = true;
        ConcreteProject result1 = ConcreteProject.create(name, projectURL, version, visible);
        
        String name2 = "Project a";
        String projectURL2 = "www.project2.com";
        String version2 = "0.0.1";
        boolean visible2 = true;
        ConcreteProject result2 = ConcreteProject.create(name2, projectURL2, version2, visible2);
        
        assertTrue(result1.compareTo(result2) == -1);
    }

    @Test
    public void testEquals() {
        long id = -123L;
        String name = "Project a";
        int projectNr = 23;
        String projectURL = "www.project.com";
        String version = "0.0.1";
        boolean visible = true;
        ConcreteProject result1 = ConcreteProject.create(id, name, projectNr, projectURL, version, visible);
        
        long id2 = -123L;
        String name2 = "Project a";
        int projectNr2 = 23;
        String projectURL2 = "www.project.com";
        String version2 = "0.0.1";
        boolean visible2 = true;
        ConcreteProject result2 = ConcreteProject.create(id2, name2, projectNr2, projectURL2, version2, visible2);
        
        assertTrue(result1.equals(result2));
    }

    @Test
    public void testToString() {
        long id = -123L;
        String name = "Project a";
        int projectNr = 23;
        String projectURL = "www.project.com";
        String version = "0.0.1";
        boolean visible = true;
        ConcreteProject result1 = ConcreteProject.create(id, name, projectNr, projectURL, version, visible);
        
        String expected = "ConcreteProject [id=-123, name=Project a, projectNr=23, "
                + "projectURL=www.project.com, version=0.0.1, visible=true]";
        assertEquals(expected, result1.toString());
    }
    
}
