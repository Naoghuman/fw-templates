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

import com.github.naoghuman.demo.template.annotation.Project;
import com.github.naoghuman.demo.template.annotation.Sample;
import com.github.naoghuman.demo.template.annotation.SampleType;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.lang.annotation.Annotation;
import java.util.List;
import javafx.collections.FXCollections;

/**
 *
 * @author Naoghuman
 */
public class ProjectConverter {
    
    public static String convertProjectNrOrSampleNrToPrefix(int leadingZeros, int projectNrOrSampleNr) {
        final StringBuilder sb = new StringBuilder();
        sb.append(""); // NOI18N
        
        if (projectNrOrSampleNr != -1) {
           sb.append(String.format("%0" + leadingZeros + "d", projectNrOrSampleNr)); // NOI18N
           sb.append(" "); // NOI18N 
        }
        
        return sb.toString();
    }
    
    public static String convertProjectNrOrSampleNrToPrefix(int projectNrOrSampleNr) {
        return convertProjectNrOrSampleNrToPrefix(3, projectNrOrSampleNr);
    }
    
    public static List<ConcreteProject> convertProjectsToConcreteProjects(List<Class<?>> convertedProjectsToClasses) {
        LoggerFacade.getDefault().debug(ProjectConverter.class, "Convert projects to [ConcreteProject]s"); // NOI18N

        final List<ConcreteProject> convertedProjectsToConcreteProjects = FXCollections.observableArrayList();
        convertedProjectsToClasses.stream()
                .forEach(projectAsClass -> {
                    final Annotation annotation = projectAsClass.getAnnotation(Project.class);
                    final Project project       = (Project) annotation;
                    
                    final String name        = project.name();
                    final int projectNr      = project.projectNr();
                    final String projectURL  = project.projectURL();
                    final String version     = project.version();
                    
                    final ConcreteProject concreteProject = ConcreteProject.create(name, projectNr, projectURL, version);
                    convertedProjectsToConcreteProjects.add(concreteProject);
                });
        
        return convertedProjectsToConcreteProjects;
    }

    public static List<Class<?>> convertProjectsToClasses(final List<String> collectedProjectsAsStrings) {
        LoggerFacade.getDefault().debug(ProjectConverter.class, "Convert Projects to Classes"); // NOI18N

        final List<Class<?>> convertedProjectsToClasses = FXCollections.observableArrayList();
        collectedProjectsAsStrings.stream()
                .forEach(projectAsString -> {
                    try {
                        final Class<?> clazz = Class.forName(projectAsString);
                        convertedProjectsToClasses.add(clazz);
                    } catch (Throwable e) {
                        LoggerFacade.getDefault().error(ProjectConverter.class, "Error converting project to class: " + projectAsString, e); // NOI18N
                    }
                });
        
        return convertedProjectsToClasses;
    }

    public static List<ConcreteSample> convertSamplesToConcreteSamples(List<Class<?>> convertedSamplesToClasses) {
        LoggerFacade.getDefault().debug(ProjectConverter.class, "Convert samples to [ConcreteSample]s"); // NOI18N

        final List<ConcreteSample> convertedSamplesToConcreteSamples = FXCollections.observableArrayList();
        convertedSamplesToClasses.stream()
                .forEach(sampleAsClass -> {
                    final Annotation annotation = sampleAsClass.getAnnotation(Sample.class);
                    final Sample sample   = (Sample) annotation;
                    
                    final String name                     = sample.name();
                    final ConcreteProject concreteProject = ConcreteProject.create(sample.project());
                    final String overviewURL              = sample.overviewURL();
                    final String sourceCodeURL            = sample.sourceCodeURL();
                    final String javaDocURL               = sample.javaDocURL();
                    final String cssURL                   = sample.cssURL();
                    final SampleType sampleType           = sample.sampleType();
                    final String sampleViewClass          = sampleAsClass.getName() + "View"; // NOI18N
                    final String description              = sample.description();
                    final boolean visible                 = sample.visible();
                    
                    final ConcreteSample concreteSample = ConcreteSample.create(
                            name, concreteProject, overviewURL, sourceCodeURL, 
                            javaDocURL, cssURL, sampleType, sampleViewClass, 
                            description, visible);
                    convertedSamplesToConcreteSamples.add(concreteSample);
                });
        
        return convertedSamplesToConcreteSamples;
    }
    
    public static List<Class<?>> convertSamplesToClasses(final List<String> collectedSamplesAsFiles) {
        LoggerFacade.getDefault().debug(ProjectConverter.class, "Convert Samples to Classes"); // NOI18N

        final List<Class<?>> collectedSamplesAsClasses = FXCollections.observableArrayList();
        collectedSamplesAsFiles.stream()
                .forEach(sampleAsString -> {
                    try {
                        final Class<?> clazz = Class.forName(sampleAsString);
                        collectedSamplesAsClasses.add(clazz);
                    } catch (Throwable e) {
                        LoggerFacade.getDefault().error(ProjectConverter.class, "Error converting sample to class: " + sampleAsString, e); // NOI18N
                    }
                });
        
        return collectedSamplesAsClasses;
    }
    
}
