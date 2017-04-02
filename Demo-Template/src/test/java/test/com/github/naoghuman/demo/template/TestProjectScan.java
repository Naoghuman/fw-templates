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
package test.com.github.naoghuman.demo.template;

import com.github.naoghuman.demo.template.project.ConcreteProject;
import com.github.naoghuman.demo.template.project.ConcreteSample;
import com.github.naoghuman.demo.template.project.ProjectCollector;
import com.github.naoghuman.demo.template.project.ProjectConverter;
import com.github.naoghuman.demo.template.project.ProjectFilter;
import com.github.naoghuman.demo.template.project.ProjectMapper;
import com.github.naoghuman.demo.template.project.ProjectPrinter;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Naoghuman
 */
public class TestProjectScan {
    
    public static void main(String[] args) {
        
        try {
            // ----------------------------------------------------------------
            final List<URL> collectedURLs = ProjectCollector.collectURLs();
//            ProjectPrinter.printURLs(collectedURLs);
            
            final List<URL> filteredURLs = ProjectFilter.filterURLs(collectedURLs);
//            ProjectPrinter.printURLs(filteredURLs);
            
            // ----------------------------------------------------------------
            final List<File> collectedClassesAsFiles = ProjectCollector.collectClassesAsFiles(filteredURLs);
//            ProjectPrinter.printFiles(collectedClassesAsFiles);
            
            final List<File> filteredClassFiles = ProjectFilter.filterClassFiles(collectedClassesAsFiles);
//            ProjectPrinter.printFiles(filteredClassFiles);
            

            // ----------------------------------------------------------------
            final List<String> collectedProjectsAsStrings = ProjectCollector.collectProjectsAsStrings(filteredClassFiles);
//            ProjectPrinter.print(collectedProjectsAsStrings);
            
            final List<Class<?>> convertedProjectsToClasses = ProjectConverter.convertProjectsToClasses(collectedProjectsAsStrings);
//            ProjectPrinter.printClasses(convertedProjectsToClasses);
            
            final List<ConcreteProject> convertedProjectsToConcreteProjects = ProjectConverter.convertProjectsToConcreteProjects(convertedProjectsToClasses);
//            ProjectPrinter.printConcreteProjects(convertedProjectsToConcreteProjects);
            

            // ----------------------------------------------------------------
            final List<String> collectedSamplesAsStrings = ProjectCollector.collectSamplesAsStrings(filteredClassFiles);
            ProjectPrinter.print(collectedSamplesAsStrings);
            
            final List<Class<?>> convertedSamplesToClasses = ProjectConverter.convertSamplesToClasses(collectedSamplesAsStrings);
            ProjectPrinter.printClasses(convertedSamplesToClasses);
            
            final List<ConcreteSample> convertedSamplesToConcreteSamples = ProjectConverter.convertSamplesToConcreteSamples(convertedSamplesToClasses);
//            ProjectPrinter.printConcreteSamples(convertedSamplesToConcreteSamples);

            
            // ----------------------------------------------------------------
            final List<ConcreteProject> mappedConcreteSampesToConcreteProjects = ProjectMapper.mapConcreteSampelsToConcreteProjects(convertedProjectsToConcreteProjects, convertedSamplesToConcreteSamples);
            ProjectPrinter.printConcreteProjects(mappedConcreteSampesToConcreteProjects);
        } catch (IOException ex) {
            LoggerFacade.getDefault().debug(TestProjectScan.class,
                    "error: ", ex); // NOI18N
        }
        
    }
    
}
