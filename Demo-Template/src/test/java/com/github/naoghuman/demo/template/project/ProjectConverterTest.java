/*
 * Copyright (C) 2017 PRo
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
 * @author PRo
 */
public class ProjectConverterTest {
    
    public ProjectConverterTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConvertProjectNrOrSampleNrToPrefix_lesser_zero() {
        int projectNrOrSampleNr = -1;
        String expResult = "";
        String result = ProjectConverter.convertProjectNrOrSampleNrToPrefix(projectNrOrSampleNr);
        assertEquals(expResult, result);
    }

    @Test
    public void testConvertProjectNrOrSampleNrToPrefix_is_zero() {
        int projectNrOrSampleNr = 0;
        String expResult = "00 ";
        String result = ProjectConverter.convertProjectNrOrSampleNrToPrefix(projectNrOrSampleNr);
        assertEquals(expResult, result);
    }

    @Test
    public void testConvertProjectNrOrSampleNrToPrefix_is_999() {
        int leadingZeros = 3;
        int projectNrOrSampleNr = 999;
        String expResult = "999 ";
        String result = ProjectConverter.convertProjectNrOrSampleNrToPrefix(leadingZeros, projectNrOrSampleNr);
        assertEquals(expResult, result);
    }

    @Test
    public void testConvertProjectNrOrSampleNrToPrefix_with_4_leadingzeros() {
        int leadingZeros = 4;
        int projectNrOrSampleNr = 0;
        String expResult = "0000 ";
        String result = ProjectConverter.convertProjectNrOrSampleNrToPrefix(leadingZeros, projectNrOrSampleNr);
        assertEquals(expResult, result);
    }

    @Test
    public void testConvertProjectNrOrSampleNrToPrefix_with_5_leadingzeros() {
        int leadingZeros = 5;
        int projectNrOrSampleNr = 12345;
        String expResult = "12345 ";
        String result = ProjectConverter.convertProjectNrOrSampleNrToPrefix(leadingZeros, projectNrOrSampleNr);
        assertEquals(expResult, result);
    }

    @Test
    public void testConvertProjectsToConcreteProjects() {
//        System.out.println("convertProjectsToConcreteProjects");
//        List<Class<?>> convertedProjectsToClasses = null;
//        List<ConcreteProject> expResult = null;
//        List<ConcreteProject> result = ProjectConverter.convertProjectsToConcreteProjects(convertedProjectsToClasses);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testConvertProjectsToClasses() {
//        System.out.println("convertProjectsToClasses");
//        List<String> collectedProjectsAsStrings = null;
//        List<Class<?>> expResult = null;
//        List<Class<?>> result = ProjectConverter.convertProjectsToClasses(collectedProjectsAsStrings);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testConvertSamplesToConcreteSamples() {
//        System.out.println("convertSamplesToConcreteSamples");
//        List<Class<?>> convertedSamplesToClasses = null;
//        List<ConcreteSample> expResult = null;
//        List<ConcreteSample> result = ProjectConverter.convertSamplesToConcreteSamples(convertedSamplesToClasses);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testConvertSamplesToClasses() {
//        System.out.println("convertSamplesToClasses");
//        List<String> collectedSamplesAsFiles = null;
//        List<Class<?>> expResult = null;
//        List<Class<?>> result = ProjectConverter.convertSamplesToClasses(collectedSamplesAsFiles);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }
    
}
