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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author PRo
 */
public class ScannerConfigurationTest {
    
    public ScannerConfigurationTest() {
    }
    
    @Before
    public void setUp() {
        ScannerConfiguration.getExcludedClasses() .clear();
        ScannerConfiguration.getExcludedJars()    .clear();
        ScannerConfiguration.getExcludedPackages().clear();
        
        final ObservableList<String> excludedJars = FXCollections.observableArrayList();
        excludedJars.add("lib-action"           ); // NOI18N
        excludedJars.add("lib-database-objectdb"); // NOI18N
        excludedJars.add("lib-logger"           ); // NOI18N
        excludedJars.add("lib-preferences"      ); // NOI18N
        excludedJars.add("lib-properties"       ); // NOI18N
        
        excludedJars.add("afterburner.fx"   ); // NOI18N
        excludedJars.add("asm"              ); // NOI18N
        excludedJars.add("javax.persistence"); // NOI18N
        excludedJars.add("jta"              ); // NOI18N
        excludedJars.add("log4j-api"        ); // NOI18N
        excludedJars.add("log4j-core"       ); // NOI18N
        excludedJars.add("objectdb"         ); // NOI18N
        
        excludedJars.add("jre"); // NOI18N
        
        excludedJars.stream()
                .forEach(excludedJar -> { 
                    ScannerConfiguration.addExcludedJar(excludedJar);
                });
    }

    @Test
    public void testAddExcludedClassWithoutSuffix() {
        ScannerConfiguration.addExcludedClass("com.github.example.MyClass");
        ObservableList<String> excludedClasses = ScannerConfiguration.getExcludedClasses();
        
        assertTrue(excludedClasses.size() == 1);
        assertEquals("com\\github\\example\\MyClass.class", excludedClasses.get(0));
    }

    @Test
    public void testAddExcludedClassWithSuffix() {
        ScannerConfiguration.addExcludedClass("com.github.example.MyClass.class");
        ObservableList<String> excludedClasses = ScannerConfiguration.getExcludedClasses();
        
        assertTrue(excludedClasses.size() == 1);
        assertEquals("com\\github\\example\\MyClass.class", excludedClasses.get(0));
    }

    @Test
    public void testAddExcludedJar() {
        ScannerConfiguration.addExcludedJar("my-excluded-jar");
        ObservableList<String> excludedJars = ScannerConfiguration.getExcludedJars();
        
        assertTrue(excludedJars.size() == 14);
        assertTrue(excludedJars.contains("my-excluded-jar"));
    }

    @Test
    public void testAddExcludedPackage() {
        ScannerConfiguration.addExcludedPackage("com.github.example");
        ObservableList<String> excludedPackages = ScannerConfiguration.getExcludedPackages();
        
        assertTrue(excludedPackages.size() == 1);
        assertEquals("com\\github\\example", excludedPackages.get(0));
    }

    @Test
    public void testGetExcludedClassesDefaultValues() {
        ObservableList<String> excludedClasses = ScannerConfiguration.getExcludedClasses();
        
        assertTrue(excludedClasses.isEmpty());
    }

    @Test
    public void testGetExcludedJarsDefaultValues() {
        ObservableList<String> excludedJars = ScannerConfiguration.getExcludedJars();
        
        assertTrue(excludedJars.size() == 13);
    }

    @Test
    public void testGetExcludedPackagesDefaultValues() {
        ObservableList<String> excludedPackages = ScannerConfiguration.getExcludedPackages();
        
        assertTrue(excludedPackages.isEmpty());
    }
    
}
