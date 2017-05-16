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
package com.github.naoghuman.demo.template.configuration;

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.io.File;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Naoghuman
 */
public final class ScannerConfiguration {
    
    public static final String SUFFIX_CLASS = ".class"; // NOI18N
    public static final String SUFFIX_JAR   = ".jar"; // NOI18N
    
    private static final String DOUBLE_SLASH = "\\"; // NOI18N
    private static final String INNER_CLASS  = "$"; // NOI18N
    private static final String POINT        = "."; // NOI18N
    
    private ScannerConfiguration() {
        
    }
    
    private static final ObservableList<String> EXCLUDED_CLASSES = FXCollections.observableArrayList();
    static {
        final ObservableList<String> excluded = FXCollections.observableArrayList();
        excluded.add("com.github.naoghuman.demo.application.StartApplication"); // NOI18N
        
        excluded.stream()
                .forEach(excludedClass -> { 
                    addExcludedClass(excludedClass);
                });
    }
    
    private static final ObservableList<String> EXCLUDED_JARS = FXCollections.observableArrayList();
    static {
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
        
        EXCLUDED_JARS.add("jre"                  ); // NOI18N
    }
    
    private static final ObservableList<String> EXCLUDED_PACKAGES = FXCollections.observableArrayList();
    static {
        final ObservableList<String> excluded = FXCollections.observableArrayList();
//        excluded.add("com.github.naoghuman.demo.annotation"); // NOI18N
        
        excluded.stream()
                .forEach(excludedPackage -> { 
                    addExcludedPackage(excludedPackage);
                });
    }
    
    public static final void addExcludedClass(final String excludedClass) {
        String convertedExcludedClass = excludedClass;
        if (convertedExcludedClass.endsWith(SUFFIX_CLASS)) {
            convertedExcludedClass = excludedClass.substring(0, excludedClass.length() - SUFFIX_CLASS.length());
        }
        
        convertedExcludedClass = convertedExcludedClass.replace(POINT, DOUBLE_SLASH);
        convertedExcludedClass = convertedExcludedClass + SUFFIX_CLASS;
        
        if (!EXCLUDED_CLASSES.contains(convertedExcludedClass)) {
            EXCLUDED_CLASSES.add(convertedExcludedClass);
            LoggerFacade.getDefault().debug(ScannerConfiguration.class, "  -> Exclude class: " + convertedExcludedClass); // NOI18N
        }
    }
    
    public static final void addExcludedJar(final String excludedJar) {
        if (!EXCLUDED_JARS.contains(excludedJar)) {
            EXCLUDED_JARS.add(excludedJar);
            LoggerFacade.getDefault().debug(ScannerConfiguration.class, "  -> Exclude jar: " + excludedJar); // NOI18N
        }
    }
    
    public static final void addExcludedPackage(final String excludedPackage) {
        final String convertedExcludedPackage = excludedPackage.replace(POINT, DOUBLE_SLASH);
        if (!EXCLUDED_PACKAGES.contains(convertedExcludedPackage)) {
            EXCLUDED_PACKAGES.add(convertedExcludedPackage);
            LoggerFacade.getDefault().debug(ScannerConfiguration.class, "  -> Exclude package: " + convertedExcludedPackage); // NOI18N
        }
    }
    
    public static final ObservableList<String> getExcludedClasses() {
        return EXCLUDED_CLASSES;
    }
    
    public static final ObservableList<String> getExcludedJars() {
        return EXCLUDED_JARS;
    }
    
    public static final ObservableList<String> getExcludedPackages() {
        return EXCLUDED_PACKAGES;
    }
    
    public static Predicate<File> isNotAnExcludedClass() {
        return file -> isNotAnExcludedClass(file.getAbsolutePath());
    }
    
    private static boolean isNotAnExcludedClass(String path) {
        final AtomicBoolean isAnExcludedClass = new AtomicBoolean(Boolean.FALSE);
        EXCLUDED_CLASSES.stream()
                .forEach(clazz -> {
                    if (path.contains(clazz)) {
                        isAnExcludedClass.set(Boolean.TRUE);
                        LoggerFacade.getDefault().debug(ScannerConfiguration.class, "  -> Exclude class: " + path); // NOI18N
                    }
                });
        
        return !isAnExcludedClass.get();
    }
    
    public static Predicate<URL> isNotAnExcludedJar() {
        return url -> isNotAnExcludedJar(url.getPath());
    }
    
    private static boolean isNotAnExcludedJar(final String path) {
        final AtomicBoolean isAnExcludedJar = new AtomicBoolean(Boolean.FALSE);
        EXCLUDED_JARS.stream()
                .forEach(jar -> {
                    if (
                            path.contains(jar)
                            && path.endsWith(SUFFIX_JAR)
                    ) {
                        isAnExcludedJar.set(Boolean.TRUE);
                        LoggerFacade.getDefault().debug(ScannerConfiguration.class, "  -> Exclude jar: " + path); // NOI18N
                    }
                });
        
        return !isAnExcludedJar.get();
    }
    
    public static Predicate<File> isNotAnInnerClass() {
        return file -> isNotAnInnerClass(file.getAbsolutePath());
    }
    
    private static boolean isNotAnInnerClass(final String path) {
        final boolean isAnInnerClass = 
                path.contains(INNER_CLASS) 
                && path.endsWith(SUFFIX_CLASS);
        if (isAnInnerClass) {
            LoggerFacade.getDefault().debug(ScannerConfiguration.class, "  -> Exclude inner class: " + path); // NOI18N
        }
        
        return !isAnInnerClass;
    }
    
    public static void reset() {
        EXCLUDED_CLASSES.clear();
        
        EXCLUDED_JARS.clear();
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
        
        EXCLUDED_JARS.add("jre"                  ); // NOI18N
        
        EXCLUDED_PACKAGES.clear();
    }
    
}
