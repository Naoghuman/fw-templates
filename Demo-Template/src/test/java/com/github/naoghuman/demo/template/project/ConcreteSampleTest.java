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
 * TODO add unittests for overview-url
 * TODO add unittests for sampletype
 * TODO add unittest  for sampleViewClass
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

//    @Test
//    public void testCreate_7args() {
//        System.out.println("create");
//        String name = "";
//        ConcreteProject project = null;
//        ObservableList<String> sourceCodeURLs = null;
//        ObservableList<String> javaDocURLs = null;
//        ObservableList<String> cssURLs = null;
//        String description = "";
//        boolean visible = false;
//        ConcreteSample expResult = null;
//        ConcreteSample result = ConcreteSample.create(name, project, sourceCodeURLs, javaDocURLs, cssURLs, description, visible);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

//    @Test
//    public void testCreate_8args() {
//        System.out.println("create");
//        long id = 0L;
//        String name = "";
//        ConcreteProject project = null;
//        ObservableList<String> sourceCodeURLs = null;
//        ObservableList<String> javaDocURLs = null;
//        ObservableList<String> cssURLs = null;
//        String description = "";
//        boolean visible = false;
//        ConcreteSample expResult = null;
//        ConcreteSample result = ConcreteSample.create(id, name, project, sourceCodeURLs, javaDocURLs, cssURLs, description, visible);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testHasCssURL_false_empty() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasCssURL());
    }

    @Test
    public void testHasCssURL_false_undefined() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "[undefined]";
        int sampleNr = 0;
        String sampleViewClass = "";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasCssURL());
    }

    @Test
    public void testHasCssURL_true() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "www.css-url.com";
        int sampleNr = 0;
        String sampleViewClass = "";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.hasCssURL());
    }

    @Test
    public void testGetCssURL() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "www.css-url.com";
        int sampleNr = 0;
        String sampleViewClass = "";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.hasCssURL());
        assertEquals("www.css-url.com", concreteSample.getCssURL().get());
    }
    
    @Test
    public void testHasJavaDocURL_false_empty() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "";
        String cssURL = "";
        String sampleViewClass = "";
        int sampleNr = 0;
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasJavaDocURL());
    }

    @Test
    public void testHasJavaDocURL_false_undefined() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "[undefined]";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasJavaDocURL());
    }

    @Test
    public void testHasJavaDocURL_true() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "www.javadoc-url.com";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.hasJavaDocURL());
    }

    @Test
    public void testGetJavaDocURL() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "";
        String javaDocURL = "www.javadoc-url.com";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
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
        String sampleViewClass = "";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasSourceCodeURL());
    }

    @Test
    public void testHasSourceCodeURL_false_undefined() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "[undefined]";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertFalse(concreteSample.hasSourceCodeURL());
    }

    @Test
    public void testHasSourceCodeURL_true() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "www.sourcecode-url.com";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.hasSourceCodeURL());
    }

    @Test
    public void testGetSourceCodeURL() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "www.sourcecode-url.com";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 0;
        String sampleViewClass = "";
        String description = "description";
        boolean visible = true;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertTrue(concreteSample.getSourceCodeURL().isPresent());
        assertEquals("www.sourcecode-url.com", concreteSample.getSourceCodeURL().get());
    }

    @Test
    public void testGetSampleNr() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "www.sourcecode-url.com";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 123;
        String sampleViewClass = "";
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
    public void testIsVisible() {
        String name = "Sample";
        String overviewURL = "";
        String sourceCodeURL = "www.sourcecode-url.com";
        String javaDocURL = "";
        String cssURL = "";
        int sampleNr = 123;
        String sampleViewClass = "";
        String description = "description";
        boolean visible = false;
        final ConcreteSample concreteSample = ConcreteSample.create(
                name, null, overviewURL,
                sourceCodeURL, javaDocURL, cssURL,
                sampleNr, SampleType.NORMAL, sampleViewClass,
                description, visible);
        
        assertEquals(false, concreteSample.isVisible());
    }

//    @Test
//    public void testHasDescription() {
//        System.out.println("hasDescription");
//        ConcreteSample instance = null;
//        boolean expResult = false;
//        boolean result = instance.hasDescription();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

//    @Test
//    public void testGetDescription() {
//        System.out.println("getDescription");
//        ConcreteSample instance = null;
//        Optional<String> expResult = null;
//        Optional<String> result = instance.getDescription();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

//    @Test
//    public void testGetName() {
//        System.out.println("getName");
//        ConcreteSample instance = null;
//        String expResult = "";
//        String result = instance.getName();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

//    @Test
//    public void testGetProject() {
//        System.out.println("getProject");
//        ConcreteSample instance = null;
//        ConcreteProject expResult = null;
//        ConcreteProject result = instance.getProject();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

//    @Test
//    public void testCompareTo() {
//        System.out.println("compareTo");
//        ConcreteSample other = null;
//        ConcreteSample instance = null;
//        int expResult = 0;
//        int result = instance.compareTo(other);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

//    @Test
//    public void testEquals() {
//        System.out.println("equals");
//        Object obj = null;
//        ConcreteSample instance = null;
//        boolean expResult = false;
//        boolean result = instance.equals(obj);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        ConcreteSample instance = null;
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
    
}
