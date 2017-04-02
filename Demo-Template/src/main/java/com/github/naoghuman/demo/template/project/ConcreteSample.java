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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Naoghuman
 */
public final class ConcreteSample implements Comparable<ConcreteSample> {
    
    private final ObservableList<String> cssURLs        = FXCollections.observableArrayList();
    private final ObservableList<String> javaDocURLs    = FXCollections.observableArrayList();
    private final ObservableList<String> sourceCodeURLs = FXCollections.observableArrayList();

    private final long id;
    private final boolean visible;
    
    private final String description;
    private final String name;
    
    private final ConcreteProject project;
    
    public static final ConcreteSample create(
            final String name, final ConcreteProject project,
            final ObservableList<String> sourceCodeURLs,
            final ObservableList<String> javaDocURLs,
            final ObservableList<String> cssURLs,
            final String description, final boolean visible
    ) {
        return create(System.nanoTime(), name, project, sourceCodeURLs, 
                javaDocURLs, cssURLs, description, visible);
    }
    
    public static final ConcreteSample create(
            final long id, final String name, final ConcreteProject project,
            final ObservableList<String> sourceCodeURLs,
            final ObservableList<String> javaDocURLs,
            final ObservableList<String> cssURLs,
            final String description, final boolean visible
    ) {
        final ConcreteSample concreteSample = new ConcreteSample(id, name, project, 
                sourceCodeURLs, javaDocURLs, cssURLs, description, visible);
        
        return concreteSample;
    }
    
//    public static final ConcreteSample create(final ConcreteSample sample, final ConcreteProject project) {
//        final ConcreteSample concreteSample = new ConcreteSample(sample.getId(), 
//                sample.getName(), project, sample.getSourceCodeURLs(), 
//                sample.getJavaDocURLs(), sample.getCssURLs(), sample.getDescription(), sample.isVisible());
//        
//        return concreteSample;
//    }
    
    private ConcreteSample(
            final long id, final String name, final ConcreteProject project,
            final ObservableList<String> sourceCodeURLs, final ObservableList<String> javaDocURLs, 
            final ObservableList<String> cssURLs, final String description, final boolean visible
    ) {
        this.id = id;
        this.visible = visible;
        
        this.name = name;
        this.description = description;
        
        this.project = project;
        
        this.sourceCodeURLs.addAll(sourceCodeURLs);
        this.javaDocURLs.addAll(javaDocURLs);
        this.cssURLs.addAll(cssURLs);
    }

    public final ObservableList<String> getCssURLs() {
        return cssURLs;
    }
    
    public final long getId() {
        return id;
    }

    public final ObservableList<String> getJavaDocURLs() {
        return javaDocURLs;
    }

    public final ObservableList<String> getSourceCodeURLs() {
        return sourceCodeURLs;
    }

    public final boolean isVisible() {
        return visible;
    }

    public final String getDescription() {
        return description;
    }

    public final String getName() {
        return name;
    }

    public final ConcreteProject getProject() {
        return project;
    }
    
    @Override
    public int compareTo(final ConcreteSample other) {
        int compareTo = this.getName().compareTo(other.getName());
        if (compareTo != 0) {
            return compareTo;
        }

        compareTo = Long.compare(this.getId(), other.getId());
        if (compareTo != 0) {
            return compareTo;
        }
        
        return compareTo;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (this.getId() ^ (this.getId() >>> 32));
        result = prime * result + this.getName().hashCode();
        
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final ConcreteSample other = (ConcreteSample) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        
        return !this.getName().equals(other.getName());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ConcreteSample ["); // NOI18N
        
        sb.append("id=")           .append(this.getId()); // NOI18N
        sb.append(", name=")       .append(this.getName()); // NOI18N
        sb.append(", project=")    .append(this.getProject().getName()); // NOI18N
        sb.append(", description=").append(this.getDescription()); // NOI18N
        sb.append(", visible=")    .append(this.isVisible()); // NOI18N
        
        sb.append("]"); // NOI18N
        
        return sb.toString();
    }
    
}
