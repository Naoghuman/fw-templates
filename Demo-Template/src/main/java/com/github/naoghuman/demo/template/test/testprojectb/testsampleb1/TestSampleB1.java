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
package com.github.naoghuman.demo.template.test.testprojectb.testsampleb1;

import com.github.naoghuman.demo.template.annotation.Project;
import com.github.naoghuman.demo.template.annotation.Sample;

/**
 *
 * @author Naoghuman
 */
@Sample(
        cssURL        = "https://raw.githubusercontent.com/glyphsoft/JavaFX-CSS-Themes/master/win7glass.css", // NOI18N
        description   = "Description TestSampleB1", // NOI18N
        javaDocURL    = "https://www.google.de/", // NOI18N
        name          = "TestSampleB1", // NOI18N
        project       = @Project(name = "TestProjectB"), // NOI18N
        sourceCodeURL = "https://raw.githubusercontent.com/Naoghuman/Project-Templates/master/Demo-Template/src/main/java/com/github/naoghuman/demo/template/application/ApplicationPresenter.java", // NOI18N
        visible       = true
)
public class TestSampleB1 {
    
}
