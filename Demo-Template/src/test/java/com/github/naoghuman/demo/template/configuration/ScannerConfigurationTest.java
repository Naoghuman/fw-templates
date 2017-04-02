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
package com.github.naoghuman.demo.template.configuration;

import com.github.naoghuman.demo.template.configuration.ScannerConfiguration;
import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PRo
 */
public class ScannerConfigurationTest {
    
    public ScannerConfigurationTest() {
    }

    @Test
    public void testAddExcludedClassWithoutSuffix() {
        ScannerConfiguration.reset();
        
        ScannerConfiguration.addExcludedClass("com.github.example.MyClass");
        ObservableList<String> excludedClasses = ScannerConfiguration.getExcludedClasses();
        
        assertTrue(excludedClasses.size() == 1);
        assertEquals("com\\github\\example\\MyClass.class", excludedClasses.get(0));
    }

    @Test
    public void testAddExcludedClassWithSuffix() {
        ScannerConfiguration.reset();
        
        ScannerConfiguration.addExcludedClass("com.github.example.MyClass.class");
        ObservableList<String> excludedClasses = ScannerConfiguration.getExcludedClasses();
        
        assertTrue(excludedClasses.size() == 1);
        assertEquals("com\\github\\example\\MyClass.class", excludedClasses.get(0));
    }

    /*
        EXCLUDED_JARS.add("lib-action"           ); // NOI18N
        EXCLUDED_JARS.add("lib-database-objectdb"); // NOI18N
        EXCLUDED_JARS.add("lib-logger"           ); // NOI18N
        EXCLUDED_JARS.add("lib-preferences"      ); // NOI18N
        EXCLUDED_JARS.add("lib-properties"       ); // NOI18N
        
        EXCLUDED_JARS.add("afterburner.fx"       ); // NOI18N
        EXCLUDED_JARS.add("asm"                  ); // NOI18N
        EXCLUDED_JARS.add("javax.persistence"    ); // NOI18N
        EXCLUDED_JARS.add("jta"                  ); // NOI18N
        EXCLUDED_JARS.add("log4j-api"            ); // NOI18N
        EXCLUDED_JARS.add("log4j-core"           ); // NOI18N
        EXCLUDED_JARS.add("objectdb"             ); // NOI18N
    */
    @Test
    public void testAddExcludedJar() {
        ScannerConfiguration.reset();
        
        ScannerConfiguration.addExcludedJar("my-excluded-jar");
        ObservableList<String> excludedJars = ScannerConfiguration.getExcludedJars();
        
        assertTrue(excludedJars.size() == 14);
        assertTrue(excludedJars.contains("my-excluded-jar"));
    }

    @Test
    public void testAddExcludedPackage() {
        ScannerConfiguration.reset();
        
        ScannerConfiguration.addExcludedPackage("com.github.example");
        ObservableList<String> excludedPackages = ScannerConfiguration.getExcludedPackages();
        
        assertTrue(excludedPackages.size() == 1);
        assertEquals("com\\github\\example", excludedPackages.get(0));
    }

    @Test
    public void testGetExcludedClassesDefaultValues() {
        ScannerConfiguration.reset();
        
        ObservableList<String> excludedClasses = ScannerConfiguration.getExcludedClasses();
        
        assertTrue(excludedClasses.isEmpty());
    }

    /*
        EXCLUDED_JARS.add("lib-action"           ); // NOI18N
        EXCLUDED_JARS.add("lib-database-objectdb"); // NOI18N
        EXCLUDED_JARS.add("lib-logger"           ); // NOI18N
        EXCLUDED_JARS.add("lib-preferences"      ); // NOI18N
        EXCLUDED_JARS.add("lib-properties"       ); // NOI18N
        
        EXCLUDED_JARS.add("afterburner.fx"       ); // NOI18N
        EXCLUDED_JARS.add("asm"                  ); // NOI18N
        EXCLUDED_JARS.add("javax.persistence"    ); // NOI18N
        EXCLUDED_JARS.add("jta"                  ); // NOI18N
        EXCLUDED_JARS.add("log4j-api"            ); // NOI18N
        EXCLUDED_JARS.add("log4j-core"           ); // NOI18N
        EXCLUDED_JARS.add("objectdb"             ); // NOI18N
    */
    @Test
    public void testGetExcludedJarsDefaultValues() {
        ScannerConfiguration.reset();
        
        ObservableList<String> excludedJars = ScannerConfiguration.getExcludedJars();
        
        assertTrue(excludedJars.size() == 13);
    }

    @Test
    public void testGetExcludedPackagesDefaultValues() {
        ScannerConfiguration.reset();
        
        ObservableList<String> excludedPackages = ScannerConfiguration.getExcludedPackages();
        
        assertTrue(excludedPackages.isEmpty());
    }
    
}
