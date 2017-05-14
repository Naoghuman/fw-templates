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

import com.github.naoghuman.demo.template.annotation.SampleType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
additional tests
 ( ) compareTo
 * 
 * @author Naoghuman
 */
public class ConcreteSampleTest {
    
    public ConcreteSampleTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testHasGetCssURL_empty_is_false() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasCssURL());
        assertFalse(concreteSample.getCssURL().isPresent());
    }

    @Test
    public void testHasGetCssURL_is_valid() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "www.css-url.com";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.hasCssURL());
        assertTrue(concreteSample.getCssURL().isPresent());
        assertEquals("www.css-url.com", concreteSample.getCssURL().get());
    }

    @Test
    public void testHasGetCssURL_null_is_not_present() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = null;
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasCssURL());
        assertFalse(concreteSample.getCssURL().isPresent());
    }

    @Test
    public void testHasGetCssURL_value_undefined_is_not_valid() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "[undefined]";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasCssURL());
        assertTrue(concreteSample.getCssURL().isPresent());
        assertEquals("[undefined]", concreteSample.getCssURL().get());
    }
    
    @Test
    public void testHasGetDescription_empty_is_false() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasDescription());
        assertFalse(concreteSample.getDescription().isPresent());
    }

    @Test
    public void testHasGetDescription_is_valid() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "www.css-url.com";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "my-description";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.hasDescription());
        assertTrue(concreteSample.getDescription().isPresent());
        assertEquals("my-description", concreteSample.getDescription().get());
    }

    @Test
    public void testHasGetDescription_null_is_not_present() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = null;
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = null;
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasDescription());
        assertFalse(concreteSample.getDescription().isPresent());
    }

    @Test
    public void testHasGetDescription_value_undefined_is_not_valid() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "[undefined]";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "[undefined]";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasDescription());
        assertTrue(concreteSample.getDescription().isPresent());
        assertEquals("[undefined]", concreteSample.getDescription().get());
    }
    
    @Test
    public void testHasGetJavaDocURL_empty_is_false() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasJavaDocURL());
        assertFalse(concreteSample.getJavaDocURL().isPresent());
    }

    @Test
    public void testHasGetJavaDocURL_is_valid() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "www.javadoc-url.com";
        String cssURL          = "www.css-url.com";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "my-description";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.hasJavaDocURL());
        assertTrue(concreteSample.getJavaDocURL().isPresent());
        assertEquals("www.javadoc-url.com", concreteSample.getJavaDocURL().get());
    }

    @Test
    public void testHasGetJavaDocURL_null_is_not_present() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = null;
        String cssURL          = null;
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = null;
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasJavaDocURL());
        assertFalse(concreteSample.getJavaDocURL().isPresent());
    }

    @Test
    public void testHasGetJavaDocURL_value_undefined_is_not_valid() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "[undefined]";
        String cssURL          = "[undefined]";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "[undefined]";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasJavaDocURL());
        assertTrue(concreteSample.getJavaDocURL().isPresent());
        assertEquals("[undefined]", concreteSample.getJavaDocURL().get());
    }
    
    @Test
    public void testHasGetOverviewURL_empty_is_false() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasOverviewURL());
        assertFalse(concreteSample.getOverviewURL().isPresent());
    }

    @Test
    public void testHasGetOverviewURL_is_valid() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "www.overview-url.com";
        String sourceCodeURL   = "";
        String javaDocURL      = "www.javadoc-url.com";
        String cssURL          = "www.css-url.com";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "my-description";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.hasOverviewURL());
        assertTrue(concreteSample.getOverviewURL().isPresent());
        assertEquals("www.overview-url.com", concreteSample.getOverviewURL().get());
    }

    @Test
    public void testHasGetOverviewURL_null_is_not_present() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = null;
        String sourceCodeURL   = "";
        String javaDocURL      = null;
        String cssURL          = null;
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = null;
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasOverviewURL());
        assertFalse(concreteSample.getOverviewURL().isPresent());
    }

    @Test
    public void testHasGetOverviewURL_value_undefined_is_not_valid() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "[undefined]";
        String sourceCodeURL   = "";
        String javaDocURL      = "[undefined]";
        String cssURL          = "[undefined]";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "[undefined]";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasOverviewURL());
        assertTrue(concreteSample.getOverviewURL().isPresent());
        assertEquals("[undefined]", concreteSample.getOverviewURL().get());
    }
    
    @Test(expected=NullPointerException.class)
    public void testGetName_throw_NullPointerException() {
        String name            = null;
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetName_empty_throw_IllegalArgumentException() {
        String name            = "";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
    }
    
    @Test
    public void testGetName_valid() {
        String name            = "Hello Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertEquals("Hello Sample", concreteSample.getName());
    }
    
    @Test (expected=NullPointerException.class)
    public void testGetProject_throw_NullPointerException() {
        String name            = "Hello Sample";
        ConcreteProject concreteProject = null;
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
    }

    @Test
    public void testGetProject_valid() {
        String name            = "Hello Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertNotNull(concreteSample.getProject());
        assertEquals("new-project", concreteSample.getProject().getName());
    }
    
    @Test
    public void testGetSampleNr_is_lesser_then_default_value() {
        String name            = "Hello Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = -123;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        int defaultSampleNr = -1;
        assertEquals(defaultSampleNr, concreteSample.getSampleNr());
    }

    @Test
    public void testGetSampleNr_is_equals_default_value() {
        String name            = "Hello Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = -1;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        int defaultSampleNr = -1;
        assertEquals(defaultSampleNr, concreteSample.getSampleNr());
    }

    @Test
    public void testGetSampleNr_is_greater_then_default_value() {
        String name            = "Hello Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 123;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertEquals(123, concreteSample.getSampleNr());
    }

    @Test
    public void testGetSampleType_NORMAL() {
        String name            = "Hello Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertEquals(SampleType.NORMAL, concreteSample.getSampleType());
    }

    @Test
    public void testGetSampleType_OVERVIEW() {
        String name            = "Hello Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.OVERVIEW, sampleViewClass,
                description, visible);
        
        assertEquals(SampleType.OVERVIEW, concreteSample.getSampleType());
    }
    
    @Test
    public void testHasGetSourceCodeURL_empty_is_false() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasSourceCodeURL());
        assertFalse(concreteSample.getSourceCodeURL().isPresent());
    }

    @Test
    public void testHasGetSourceCodeURL_is_valid() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "www.overview-url.com";
        String sourceCodeURL   = "www.sourcecode-url.com";
        String javaDocURL      = "www.javadoc-url.com";
        String cssURL          = "www.css-url.com";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "my-description";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.hasSourceCodeURL());
        assertTrue(concreteSample.getSourceCodeURL().isPresent());
        assertEquals("www.sourcecode-url.com", concreteSample.getSourceCodeURL().get());
    }

    @Test
    public void testHasGetSourceCodeURL_null_is_not_present() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = null;
        String sourceCodeURL   = null;
        String javaDocURL      = null;
        String cssURL          = null;
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = null;
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasSourceCodeURL());
        assertFalse(concreteSample.getSourceCodeURL().isPresent());
    }

    @Test
    public void testHasGetSourceCodeURL_value_undefined_is_not_valid() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "[undefined]";
        String sourceCodeURL   = "[undefined]";
        String javaDocURL      = "[undefined]";
        String cssURL          = "[undefined]";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "[undefined]";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasSourceCodeURL());
        assertTrue(concreteSample.getSourceCodeURL().isPresent());
        assertEquals("[undefined]", concreteSample.getSourceCodeURL().get());
    }
    
    @Test
    public void testIsVisible_is_false() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = false;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.isVisible());
    }
    
    @Test
    public void testIsVisible_is_true() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.isVisible());
    }

    @Test(expected = NullPointerException.class)
    public void testGetSampleViewClass_NullPointerException() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = null;
        String description     = "";
        boolean visible        = true;
        
        ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.OVERVIEW, sampleViewClass,
                description, visible);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSampleViewClass_IllegalArgumentException_empty() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "";
        String description     = "";
        boolean visible        = true;
        
        ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.OVERVIEW, sampleViewClass,
                description, visible);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSampleViewClass_IllegalArgumentException_lesser_4_signs() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "xyz";
        String description     = "";
        boolean visible        = true;
        
        ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.OVERVIEW, sampleViewClass,
                description, visible);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSampleViewClass_IllegalArgumentException_equals_4_signs() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "1234";
        String description     = "";
        boolean visible        = true;
        
        ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.OVERVIEW, sampleViewClass,
                description, visible);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSampleViewClass_IllegalArgumentException_ends_not_with_view() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "sa-name-without-suffix";
        String description     = "";
        boolean visible        = true;
        
        ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.OVERVIEW, sampleViewClass,
                description, visible);
    }

    public void testGetSampleViewClass_is_valid() {
        String name            = "Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "";
        String sourceCodeURL   = "";
        String javaDocURL      = "";
        String cssURL          = "";
        int sampleNr           = 0;
        String sampleViewClass = "sampleClassView";
        String description     = "";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.OVERVIEW, sampleViewClass,
                description, visible);
        
        assertEquals("sampleClassView", concreteSample.getSampleViewClass());
    }
    
    @Test
    public void testCreate_6args() {
        String name            = "Hello Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "www.overview-url.com";
        String sourceCodeURL   = "www.sourcecode-url.com";
        String javaDocURL      = "www.javadoc-url.com";
        String cssURL          = "www.css-url.com";
        int sampleNr           = 567;
        String sampleViewClass = "sampleClassView";
        String description     = "my-description";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, concreteProject, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertEquals("Hello Sample", concreteSample.getName());
        
        assertNotNull(concreteSample.getProject());
        assertEquals("new-project", concreteSample.getProject().getName());
        
        assertTrue(concreteSample.hasOverviewURL());
        assertTrue(concreteSample.getOverviewURL().isPresent());
        assertEquals("www.overview-url.com", concreteSample.getOverviewURL().get());
        
        assertTrue(concreteSample.hasSourceCodeURL());
        assertTrue(concreteSample.getSourceCodeURL().isPresent());
        assertEquals("www.sourcecode-url.com", concreteSample.getSourceCodeURL().get());
        
        assertTrue(concreteSample.hasJavaDocURL());
        assertTrue(concreteSample.getJavaDocURL().isPresent());
        assertEquals("www.javadoc-url.com", concreteSample.getJavaDocURL().get());
        
        assertTrue(concreteSample.hasCssURL());
        assertTrue(concreteSample.getCssURL().isPresent());
        assertEquals("www.css-url.com", concreteSample.getCssURL().get());
        
        assertEquals(567, concreteSample.getSampleNr());
        
        assertEquals(SampleType.NORMAL, concreteSample.getSampleType());
        
        assertEquals("sampleClassView", concreteSample.getSampleViewClass());
        
        assertTrue(concreteSample.hasDescription());
        assertTrue(concreteSample.getDescription().isPresent());
        assertEquals("my-description", concreteSample.getDescription().get());
        
        assertTrue(concreteSample.isVisible());
    }

    @Test
    public void testCreate_7args() {
        long id                = 1234567l;
        String name            = "Hello Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "www.overview-url.com";
        String sourceCodeURL   = "www.sourcecode-url.com";
        String javaDocURL      = "www.javadoc-url.com";
        String cssURL          = "www.css-url.com";
        int sampleNr           = 567;
        String sampleViewClass = "sampleClassView";
        String description     = "my-description";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                id, name, concreteProject,
                overviewURL, sourceCodeURL, javaDocURL,
                cssURL, sampleNr, SampleType.NORMAL,
                sampleViewClass, description, visible);
        
        assertEquals(1234567l, concreteSample.getId());
        
        assertEquals("Hello Sample", concreteSample.getName());
        
        assertNotNull(concreteSample.getProject());
        assertEquals("new-project", concreteSample.getProject().getName());
        
        assertTrue(concreteSample.hasOverviewURL());
        assertTrue(concreteSample.getOverviewURL().isPresent());
        assertEquals("www.overview-url.com", concreteSample.getOverviewURL().get());
        
        assertTrue(concreteSample.hasSourceCodeURL());
        assertTrue(concreteSample.getSourceCodeURL().isPresent());
        assertEquals("www.sourcecode-url.com", concreteSample.getSourceCodeURL().get());
        
        assertTrue(concreteSample.hasJavaDocURL());
        assertTrue(concreteSample.getJavaDocURL().isPresent());
        assertEquals("www.javadoc-url.com", concreteSample.getJavaDocURL().get());
        
        assertTrue(concreteSample.hasCssURL());
        assertTrue(concreteSample.getCssURL().isPresent());
        assertEquals("www.css-url.com", concreteSample.getCssURL().get());
        
        assertEquals(567, concreteSample.getSampleNr());
        
        assertEquals(SampleType.NORMAL, concreteSample.getSampleType());
        
        assertEquals("sampleClassView", concreteSample.getSampleViewClass());
        
        assertTrue(concreteSample.hasDescription());
        assertTrue(concreteSample.getDescription().isPresent());
        assertEquals("my-description", concreteSample.getDescription().get());
        
        assertTrue(concreteSample.isVisible());
    }
    
    @Test
    public void testCompareTo_samplenr_and_name() {
        long id                = 1234567l;
        String name            = "Sample A";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "www.overview-url.com";
        String sourceCodeURL   = "www.sourcecode-url.com";
        String javaDocURL      = "www.javadoc-url.com";
        String cssURL          = "www.css-url.com";
        int sampleNr           = 567;
        String sampleViewClass = "sampleClassView";
        String description     = "my-description";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                id, name, concreteProject,
                overviewURL, sourceCodeURL, javaDocURL,
                cssURL, sampleNr, SampleType.NORMAL,
                sampleViewClass, description, visible);
        
        long id2                = 1234567l;
        String name2            = "Sample B";
        ConcreteProject concreteProject2 = ConcreteProject.create("new-project");
        String overviewURL2     = "www.overview-url.com";
        String sourceCodeURL2   = "www.sourcecode-url.com";
        String javaDocURL2      = "www.javadoc-url.com";
        String cssURL2          = "www.css-url.com";
        int sampleNr2           = 567;
        String sampleViewClass2 = "sampleClassView";
        String description2     = "my-description";
        boolean visible2        = true;
        
        final ConcreteSample concreteSample2 = ConcreteSample.create(
                id2, name2, concreteProject2,
                overviewURL2, sourceCodeURL2, javaDocURL2,
                cssURL2, sampleNr2, SampleType.NORMAL,
                sampleViewClass2, description2, visible2);
        
        assertTrue(concreteSample.compareTo(concreteSample2) == -1);
    }
    
    @Test
    public void testCompareTo_SampleType() {
        long id                = 1234567l;
        String name            = "Sample A";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "www.overview-url.com";
        String sourceCodeURL   = "www.sourcecode-url.com";
        String javaDocURL      = "www.javadoc-url.com";
        String cssURL          = "www.css-url.com";
        int sampleNr           = 567;
        String sampleViewClass = "sampleClassView";
        String description     = "my-description";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                id, name, concreteProject,
                overviewURL, sourceCodeURL, javaDocURL,
                cssURL, sampleNr, SampleType.NORMAL,
                sampleViewClass, description, visible);
        
        long id2                = 1234567l;
        String name2            = "Sample A";
        ConcreteProject concreteProject2 = ConcreteProject.create("new-project");
        String overviewURL2     = "www.overview-url.com";
        String sourceCodeURL2   = "www.sourcecode-url.com";
        String javaDocURL2      = "www.javadoc-url.com";
        String cssURL2          = "www.css-url.com";
        int sampleNr2           = 567;
        String sampleViewClass2 = "sampleClassView";
        String description2     = "my-description";
        boolean visible2        = true;
        
        final ConcreteSample concreteSample2 = ConcreteSample.create(
                id2, name2, concreteProject2,
                overviewURL2, sourceCodeURL2, javaDocURL2,
                cssURL2, sampleNr2, SampleType.OVERVIEW,
                sampleViewClass2, description2, visible2);
        
        assertTrue(concreteSample.compareTo(concreteSample2) == -1);
    }
    
    @Test
    public void testCompareTo_id() {
        long id                = 1234567l;
        String name            = "Sample A";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "www.overview-url.com";
        String sourceCodeURL   = "www.sourcecode-url.com";
        String javaDocURL      = "www.javadoc-url.com";
        String cssURL          = "www.css-url.com";
        int sampleNr           = 567;
        String sampleViewClass = "sampleClassView";
        String description     = "my-description";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                id, name, concreteProject,
                overviewURL, sourceCodeURL, javaDocURL,
                cssURL, sampleNr, SampleType.NORMAL,
                sampleViewClass, description, visible);
        
        long id2                = 6784567l;
        String name2            = "Sample A";
        ConcreteProject concreteProject2 = ConcreteProject.create("new-project");
        String overviewURL2     = "www.overview-url.com";
        String sourceCodeURL2   = "www.sourcecode-url.com";
        String javaDocURL2      = "www.javadoc-url.com";
        String cssURL2          = "www.css-url.com";
        int sampleNr2           = 567;
        String sampleViewClass2 = "sampleClassView";
        String description2     = "my-description";
        boolean visible2        = true;
        
        final ConcreteSample concreteSample2 = ConcreteSample.create(
                id2, name2, concreteProject2,
                overviewURL2, sourceCodeURL2, javaDocURL2,
                cssURL2, sampleNr2, SampleType.NORMAL,
                sampleViewClass2, description2, visible2);
        
        assertTrue(concreteSample.compareTo(concreteSample2) == -1);
    }
    
    @Test
    public void testEquals_true() {
        long id                = 1234567l;
        String name            = "Hello Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "www.overview-url.com";
        String sourceCodeURL   = "www.sourcecode-url.com";
        String javaDocURL      = "www.javadoc-url.com";
        String cssURL          = "www.css-url.com";
        int sampleNr           = 567;
        String sampleViewClass = "sampleClassView";
        String description     = "my-description";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                id, name, concreteProject,
                overviewURL, sourceCodeURL, javaDocURL,
                cssURL, sampleNr, SampleType.NORMAL,
                sampleViewClass, description, visible);
        
        long id2                = 1234567l;
        String name2            = "Hello Sample";
        ConcreteProject concreteProject2 = ConcreteProject.create("new-project");
        String overviewURL2     = "www.overview-url.com";
        String sourceCodeURL2   = "www.sourcecode-url.com";
        String javaDocURL2      = "www.javadoc-url.com";
        String cssURL2          = "www.css-url.com";
        int sampleNr2           = 567;
        String sampleViewClass2 = "sampleClassView";
        String description2     = "my-description";
        boolean visible2        = true;
        
        final ConcreteSample concreteSample2 = ConcreteSample.create(
                id2, name2, concreteProject2,
                overviewURL2, sourceCodeURL2, javaDocURL2,
                cssURL2, sampleNr2, SampleType.NORMAL,
                sampleViewClass2, description2, visible2);
        
        assertTrue(concreteSample.equals(concreteSample2));
    }
    
    @Test
    public void testToString() {
        long id                = 1234567l;
        String name            = "Hello Sample";
        ConcreteProject concreteProject = ConcreteProject.create("new-project");
        String overviewURL     = "www.overview-url.com";
        String sourceCodeURL   = "www.sourcecode-url.com";
        String javaDocURL      = "www.javadoc-url.com";
        String cssURL          = "www.css-url.com";
        int sampleNr           = 567;
        String sampleViewClass = "sampleClassView";
        String description     = "my-description";
        boolean visible        = true;
        
        final ConcreteSample concreteSample = ConcreteSample.create(
                id, name, concreteProject,
                overviewURL, sourceCodeURL, javaDocURL,
                cssURL, sampleNr, SampleType.NORMAL,
                sampleViewClass, description, visible);
        
        String expected = "ConcreteSample [id=1234567, name=Hello Sample, project=new-project, "
                + "overviewURL=www.overview-url.com, sourceCodeURL=www.sourcecode-url.com, "
                + "javaDocURL=www.javadoc-url.com, cssURL=www.css-url.com, sampleNr=567, "
                + "sampleType=NORMAL, sampleViewClass=sampleClassView, description=my-description, visible=true]";
        assertEquals(expected, concreteSample.toString());
    }
    
}
