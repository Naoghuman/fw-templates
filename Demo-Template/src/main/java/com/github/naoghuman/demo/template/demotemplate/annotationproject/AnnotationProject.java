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
package com.github.naoghuman.demo.template.demotemplate.annotationproject;

import com.github.naoghuman.demo.template.annotation.Project;
import com.github.naoghuman.demo.template.annotation.Sample;
import com.github.naoghuman.demo.template.annotation.SampleType;

/**
 *
 * @author Naoghuman
 */
@Sample(
        /* cssURL = "[undefined]"; */
        description = "This sample shows an overview from the annotation [com.github.naoghuman.demo.template.annotation.Project].", // NOI18N
        /* javaDocURL = "[undefined]"; */
        name = "01 Overview from the annotation [Project]", // NOI18N
        /* overviewURL = "[undefined]", */
        project = @Project(name = "Demo-Template"), // NOI18N
        sampleType = SampleType.OVERVIEW,
        /* sourceCodeURL = "[undefined]", */
        visible = true
)
public class AnnotationProject {
    
}
