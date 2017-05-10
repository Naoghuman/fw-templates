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
 * Unittests
    ( ) id
    (v) name
    ( ) project
    (v) overviewURL
    (v) sourceCodeURL
    (v) javaDocURL
    (v) cssURL
    (v) sampleNr
    (v) sampleType
    (v) sampleViewClass
    (v) description
    (v) visible
    ( ) compareTo
    ( ) equals
    ( ) print
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

    @Test (expected=NullPointerException.class)
    public void testGetName_null() {
        String name = null;
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
    }

    @Test
    public void testGetName_empty() {
        String name = "";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.getName().isEmpty());
    }

    @Test
    public void testGetName_valid() {
        String name = "my-name";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);

        assertEquals("my-name", concreteSample.getName());
    }

    @Test
    public void testHasCssURL_false_empty() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasCssURL());
        assertFalse(concreteSample.getCssURL().isPresent());
    }

    @Test
    public void testHasCssURL_false_undefined() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "[undefined]";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasCssURL());
        assertTrue(concreteSample.getCssURL().isPresent());
        assertEquals("[undefined]", concreteSample.getCssURL().get());
    }

    @Test
    public void testGetCssURL_true() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "www.css-url.com";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.hasCssURL());
        assertTrue(concreteSample.getCssURL().isPresent());
        assertEquals("www.css-url.com", concreteSample.getCssURL().get());
    }
    
    @Test
    public void testHasJavaDocURL_false_empty() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasJavaDocURL());
        assertFalse(concreteSample.getJavaDocURL().isPresent());
    }

    @Test
    public void testHasJavaDocURL_false_undefined() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "[undefined]";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasJavaDocURL());
        assertTrue(concreteSample.getJavaDocURL().isPresent());
        assertEquals("[undefined]", concreteSample.getJavaDocURL().get());
    }

    @Test
    public void testGetJavaDocURL_true() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "www.javadoc-url.com";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.hasJavaDocURL());
        assertTrue(concreteSample.getJavaDocURL().isPresent());
        assertEquals("www.javadoc-url.com", concreteSample.getJavaDocURL().get());
    }
    
    @Test
    public void testHasSourceCodeURL_false_empty() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasSourceCodeURL());
        assertFalse(concreteSample.getSourceCodeURL().isPresent());
    }

    @Test
    public void testHasSourceCodeURL_false_undefined() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "[undefined]";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasSourceCodeURL());
        assertTrue(concreteSample.getSourceCodeURL().isPresent());
        assertEquals("[undefined]", concreteSample.getSourceCodeURL().get());
    }

    @Test
    public void testGetSourceCodeURL_true() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "www.sourcecode-url.com";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.hasSourceCodeURL());
        assertTrue(concreteSample.getSourceCodeURL().isPresent());
        assertEquals("www.sourcecode-url.com", concreteSample.getSourceCodeURL().get());
    }

    @Test
    public void testGetSampleNr_lesser_then_default_value() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "www.sourcecode-url.com";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = -123;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        int defaultSampleNr = -1;
        assertEquals(defaultSampleNr, concreteSample.getSampleNr());
    }

    @Test
    public void testGetSampleNr_equals_default_value() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "www.sourcecode-url.com";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = -1;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        int defaultSampleNr = -1;
        assertEquals(defaultSampleNr, concreteSample.getSampleNr());
    }

    @Test
    public void testGetSampleNr_greater_then_default_value() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "www.sourcecode-url.com";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 123;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertEquals(123, concreteSample.getSampleNr());
    }
    
    @Test
    public void testIsVisible_false() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "www.sourcecode-url.com";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 123;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = false;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.isVisible());
    }
    
    @Test
    public void testIsVisible_true() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "www.sourcecode-url.com";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 123;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.isVisible());
    }

    @Test
    public void testHasDescription_false_empty() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasDescription());
        assertFalse(concreteSample.getDescription().isPresent());
    }

    @Test
    public void testHasDescription_false_undefined() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "[undefined]";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasDescription());
        assertTrue(concreteSample.getDescription().isPresent());
        assertEquals("[undefined]", concreteSample.getDescription().get());
    }

    @Test
    public void testGetDescription_true() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.hasDescription());
        assertTrue(concreteSample.getDescription().isPresent());
        assertEquals("description", concreteSample.getDescription().get());
    }
    
    @Test
    public void testHasOverviewURL_false_empty() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasOverviewURL());
        assertFalse(concreteSample.getOverviewURL().isPresent());
    }

    @Test
    public void testHasOverviewURL_false_undefined() {
        String name = "Sample";
        String overviewURL = "[undefined]";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasOverviewURL());
        assertTrue(concreteSample.getOverviewURL().isPresent());
        assertEquals("[undefined]", concreteSample.getOverviewURL().get());
    }

    @Test
    public void testGetOverviewURL_true() {
        String name = "Sample";
        String overviewURL = "www.overview-url.com";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.hasOverviewURL());
        assertTrue(concreteSample.getOverviewURL().isPresent());
        assertEquals("www.overview-url.com", concreteSample.getOverviewURL().get());
    }

    @Test
    public void testGetSampleType_NORMAL() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertEquals(SampleType.NORMAL, concreteSample.getSampleType());
    }

    @Test
    public void testGetSampleType_OVERVIEW() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "sampleClassView";
        String description = "";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.OVERVIEW, sampleViewClass,
                description, visible);
        
        assertEquals(SampleType.OVERVIEW, concreteSample.getSampleType());
    }

    @Test(expected = NullPointerException.class)
    public void testGetSampleViewClass_NullPointerException() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = null;
        String description = "";
        boolean visible = true;
        ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.OVERVIEW, sampleViewClass,
                description, visible);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSampleViewClass_IllegalArgumentException_empty() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "";
        String description = "";
        boolean visible = true;
        ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.OVERVIEW, sampleViewClass,
                description, visible);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSampleViewClass_IllegalArgumentException_lesser_4_signs() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "Xyz";
        String description = "";
        boolean visible = true;
        ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.OVERVIEW, sampleViewClass,
                description, visible);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSampleViewClass_IllegalArgumentException_equals_4_signs() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "Xyzt";
        String description = "";
        boolean visible = true;
        ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.OVERVIEW, sampleViewClass,
                description, visible);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSampleViewClass_IllegalArgumentException_ends_not_with_view() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "Xsfsfsdyzt";
        String description = "";
        boolean visible = true;
        ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.OVERVIEW, sampleViewClass,
                description, visible);
    }
    
}
