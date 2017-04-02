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
import java.io.File;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Naoghuman
 */
public final class ProjectPrinter {
    
    public static final void print(final List<String> stringsToPrint) {
        LoggerFacade.getDefault().debug(ProjectPrinter.class, "Print Strings"); // NOI18N
        
        stringsToPrint.stream()
                .forEach(string -> {
                    LoggerFacade.getDefault().debug(ProjectPrinter.class, "  -> " + string); // NOI18N
                });
    }

    public static final void printClasses(final List<Class<?>> classesToPrint) {
        LoggerFacade.getDefault().debug(ProjectPrinter.class, "Print classes"); // NOI18N
        
        classesToPrint.stream()
                .forEach(clazz -> {
                    LoggerFacade.getDefault().debug(ProjectPrinter.class, "  -> " + clazz.getName()); // NOI18N
                });
    }

    public static final void printConcreteProjects(final List<ConcreteProject> concreteProjectsToPrint) {
        LoggerFacade.getDefault().debug(ProjectPrinter.class, "Print ConcreteProjects"); // NOI18N
        
        concreteProjectsToPrint.stream()
                .forEach(concreteProject -> {
                    LoggerFacade.getDefault().debug(ProjectPrinter.class, "  -> " + concreteProject.toString()); // NOI18N
                });
    }

    public static void printConcreteSamples(List<ConcreteSample> convertedSamplesToConcreteSamples) {
        LoggerFacade.getDefault().debug(ProjectPrinter.class, "Print ConcreteSamples"); // NOI18N
        
        convertedSamplesToConcreteSamples.stream()
                .forEach(concreteSample -> {
                    LoggerFacade.getDefault().debug(ProjectPrinter.class, "  -> " + concreteSample.toString()); // NOI18N
                });
    }
    
    public static final void printFiles(final List<File> filesToPrint ) {
        LoggerFacade.getDefault().debug(ProjectPrinter.class, "Print files"); // NOI18N
        
        filesToPrint.stream()
                .forEach(file -> {
                    LoggerFacade.getDefault().debug(ProjectPrinter.class, "  -> " + file.getAbsolutePath()); // NOI18N
                });
    }
    
    public static final void printURLs(final List<URL> urlsToPrint) {
        LoggerFacade.getDefault().debug(ProjectPrinter.class, "Print URLs"); // NOI18N
        
        urlsToPrint.stream()
                .forEach(url -> {
                    LoggerFacade.getDefault().debug(ProjectPrinter.class, "  -> " + url.getPath()); // NOI18N
                });
    }
}
