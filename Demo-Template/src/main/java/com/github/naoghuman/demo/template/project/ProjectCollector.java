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

import com.github.naoghuman.demo.template.configuration.ScannerConfiguration;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

/**
 *
 * @author Naoghuman
 */
public final class ProjectCollector {
    
    private static void collectClassFile(final File file, final List<File> collectedClassFiles) throws IOException {
        if (file.isDirectory()) {
            final File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    collectClassFile(child, collectedClassFiles);
                }
            }
        }
        else if (file.getName().endsWith(ScannerConfiguration.SUFFIX_CLASS)) {
            collectedClassFiles.add(file);
        }
    }
    
    public static final List<File> collectClassesAsFiles(final List<URL> filteredURLs) {
        LoggerFacade.getDefault().debug(ProjectCollector.class, "Collect Classes as Files"); // NOI18N
        
        final List<File> collectedClassFiles = FXCollections.observableArrayList();
        filteredURLs.stream()
                .forEach(url -> {
                    final File file = new File(url.getPath());
                    if (file.isDirectory()) {
                        try {
                            collectClassFile(file, collectedClassFiles);
                        } catch (IOException ex) {
                            LoggerFacade.getDefault().error(ProjectCollector.class, "Error: ", ex); // NOI18N
                        }
                    }
                });
        
        return collectedClassFiles;
    }

    public static List<String> collectProjectsAsStrings(final List<File> filteredClassFiles) {
        LoggerFacade.getDefault().debug(ProjectCollector.class, "Collect projects as Strings"); // NOI18N
        
        final List<String> collectedProjectsAsStrings = FXCollections.observableArrayList();
        filteredClassFiles.stream()
                .forEach(file -> {
                    try (final FileInputStream fileInputStream = new FileInputStream(file)) {
                        final AnnotationClassVisitor projectClassVisitor = new AnnotationClassVisitor(AnnotationClassVisitor.ANNOTATION_PROJECT);
                        final ClassReader classReader = new ClassReader(fileInputStream);
                        classReader.accept(projectClassVisitor, 0);
                        if (projectClassVisitor.hasAnnotation()) {
                            collectedProjectsAsStrings.add(projectClassVisitor.getClassNameWithPackages());
                        }
                    } catch (IOException ex) {
                        LoggerFacade.getDefault().error(ProjectCollector.class, "Error", ex); // NOI18N
                    }
                });
        
        return collectedProjectsAsStrings;
    }

    public static List<String> collectSamplesAsStrings(final List<File> filteredClassFiles) {
        LoggerFacade.getDefault().debug(ProjectCollector.class, "Collect samples as Strings"); // NOI18N
        
        final List<String> collectedSamplesAsStrings = FXCollections.observableArrayList();
        filteredClassFiles.stream()
                .forEach(file -> {
                    try (final FileInputStream fileInputStream = new FileInputStream(file)) {
                        final AnnotationClassVisitor sampleClassVisitor = new AnnotationClassVisitor(AnnotationClassVisitor.ANNOTATION_SAMPLE);
                        final ClassReader classReader = new ClassReader(fileInputStream);
                        classReader.accept(sampleClassVisitor, 0);
                        if (sampleClassVisitor.hasAnnotation()) {
                            collectedSamplesAsStrings.add(sampleClassVisitor.getClassNameWithPackages());
                        }
                    } catch (IOException ex) {
                        LoggerFacade.getDefault().error(ProjectCollector.class, "Error", ex); // NOI18N
                    }
                });
        
        return collectedSamplesAsStrings;
    }
    
    public static final List<URL> collectURLs() throws IOException {
        LoggerFacade.getDefault().debug(ProjectCollector.class, "Collect URLs"); // NOI18N
        
        final List<URL> collectedURLs = new ArrayList<>();
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        while (classLoader != null) {
            if (classLoader instanceof URLClassLoader) {
                final URL[] urls = ((URLClassLoader) classLoader).getURLs();
                collectedURLs.addAll(Arrays.asList(urls));
            }

            classLoader = classLoader.getParent();
        }
        
        return collectedURLs;
    }
    
    private static final class AnnotationClassVisitor extends ClassVisitor {
        
        public static final String ANNOTATION_PROJECT = "Lcom/github/naoghuman/demo/template/annotation/Project;"; // NOI18N
        public static final String ANNOTATION_SAMPLE  = "Lcom/github/naoghuman/demo/template/annotation/Sample;"; // NOI18N
        
        private final String annotation;
        
        private boolean hasAnnotation = Boolean.FALSE;
        
        private String classNameWithPackages;

        AnnotationClassVisitor(final String annotation) {
            super(Opcodes.ASM5);
            
            this.annotation = annotation;
        }
        
        public String getClassNameWithPackages() {
            return classNameWithPackages;
        }
        
        public boolean hasAnnotation() {
            return hasAnnotation;
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            classNameWithPackages = name.replace('/', '.');
        }

        @Override
        public AnnotationVisitor visitAnnotation(final String desc, final boolean visible) {
            if (desc.equals(annotation)) {
                hasAnnotation = Boolean.TRUE;
            }
            
            return null;
        }
        
    }
    
}
