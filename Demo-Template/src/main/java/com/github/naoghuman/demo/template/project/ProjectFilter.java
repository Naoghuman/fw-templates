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
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Naoghuman
 */
public final class ProjectFilter {
    
    public static List<File> filterClassFiles(final List<File> classFilesToFilter) {
        LoggerFacade.getDefault().debug(ProjectFilter.class, "Filter Class Files"); // NOI18N
        
        final List<File> filteredClassFiles = classFilesToFilter.stream()
                .filter(ScannerConfiguration.isNotAnInnerClass())
                .filter(ScannerConfiguration.isNotAnExcludedClass())
                .collect(Collectors.<File>toList());

        return filteredClassFiles;
    }
    
    public static final List<URL> filterURLs(final List<URL> urlsToFilter) {
        LoggerFacade.getDefault().debug(ProjectFilter.class, "Filter URLs"); // NOI18N
        
        final List<URL> filteredURLs = urlsToFilter.stream()
                .filter(ScannerConfiguration.isNotAnExcludedJar())
                .collect(Collectors.<URL>toList());
        
        return filteredURLs;
    }
    
}
