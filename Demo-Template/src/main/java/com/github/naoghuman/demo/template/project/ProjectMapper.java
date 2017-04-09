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

import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.util.List;
import javafx.collections.FXCollections;

/**
 *
 * @author Naoghuman
 */
public class ProjectMapper {

    public static List<ConcreteProject> mapConcreteSampelsToConcreteProjects(
            final List<ConcreteProject> convertedProjectsToConcreteProjects,
            final List<ConcreteSample>  convertedSamplesToConcreteSamples
    ) {
        LoggerFacade.getDefault().debug(ProjectMapper.class, "Map ConcreteSampels to ConcreteProjects"); // NOI18N

        convertedProjectsToConcreteProjects.stream()
                .forEach(concreteProject -> {
                    convertedSamplesToConcreteSamples.stream()
                            .forEach(concreteSample -> {
                                if (concreteProject.getName().equals(concreteSample.getProject().getName())) {
                                    concreteProject.add(concreteSample);
                                }
                            });
                });
        
        final List<ConcreteProject> mappedConcreteSampelsToConcreteProjects = FXCollections.observableArrayList();
        mappedConcreteSampelsToConcreteProjects.addAll(convertedProjectsToConcreteProjects);
        
        return mappedConcreteSampelsToConcreteProjects;
    }
    
}
