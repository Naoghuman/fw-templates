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

import java.util.Optional;

/**
 *
 * @author Naoghuman
 */
public final class ConcreteSample implements Comparable<ConcreteSample> {
    
    private static final String UNDEFINED = "[undefined]"; // NOI18N
    
    private final long id;
    private final boolean visible;
    
    private final Optional<String> cssURL;
    private final Optional<String> description;
    private final Optional<String> javaDocURL;
    private final String name;
    private final Optional<String> sourceCodeURL;
    
    private final ConcreteProject project;
    
    public static final ConcreteSample create(
            final String name, final ConcreteProject project,
            final String sourceCodeURL, final String javaDocURL,
            final String cssURL, final String description,
            final boolean visible
    ) {
        return create(System.nanoTime(), name, project, sourceCodeURL, 
                javaDocURL, cssURL, description, visible);
    }
    
    public static final ConcreteSample create(
            final long id, final String name, 
            final ConcreteProject project, final String sourceCodeURL,
            final String javaDocURL, final String cssURL,
            final String description, final boolean visible
    ) {
        final ConcreteSample concreteSample = new ConcreteSample(id, name, project, 
                sourceCodeURL, javaDocURL, cssURL, description, visible);
        
        return concreteSample;
    }
    
    private ConcreteSample(
            final long id, final String name, final ConcreteProject project,
            final String sourceCodeURL, final String javaDocURL, 
            final String cssURL, final String description, final boolean visible
    ) {
        this.id = id;
        
        this.name = name;
        this.project = project;
        
        this.sourceCodeURL = Optional.ofNullable(sourceCodeURL);
        this.javaDocURL = Optional.ofNullable(javaDocURL);
        this.cssURL = Optional.ofNullable(cssURL);
        this.description = Optional.ofNullable(description);
        
        this.visible = visible;
    }
    
    public boolean hasCssURL() {
        return this.getCssURL().isPresent()
                && !this.getCssURL().get().isEmpty()
                && !this.getCssURL().get().equals(UNDEFINED);
    }

    public final Optional<String> getCssURL() {
        return cssURL;
    }
    
    public final long getId() {
        return id;
    }
    
    public boolean hasJavaDocURL() {
        return this.getJavaDocURL().isPresent()
                && !this.getJavaDocURL().get().isEmpty()
                && !this.getJavaDocURL().get().equals(UNDEFINED);
    }

    public final Optional<String> getJavaDocURL() {
        return javaDocURL;
    }
    
    public boolean hasSourceCodeURL() {
        return this.getSourceCodeURL().isPresent()
                && !this.getSourceCodeURL().get().isEmpty()
                && !this.getSourceCodeURL().get().equals(UNDEFINED);
    }

    public final Optional<String> getSourceCodeURL() {
        return sourceCodeURL;
    }

    public final boolean isVisible() {
        return visible;
    }
    
    public boolean hasDescription() {
        return this.getDescription().isPresent() && !this.getDescription().get().equals(UNDEFINED);
    }

    public final Optional<String> getDescription() {
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
        
        sb.append("id=")             .append(this.getId()); // NOI18N
        sb.append(", name=")         .append(this.getName()); // NOI18N
        sb.append(", project=")      .append(this.getProject()      .getName()); // NOI18N
        sb.append(", sourceCodeURL=").append(this.getSourceCodeURL().isPresent() ? this.getSourceCodeURL().get() : UNDEFINED); // NOI18N
        sb.append(", javaDocURL=")   .append(this.getJavaDocURL()   .isPresent() ? this.getJavaDocURL()   .get() : UNDEFINED); // NOI18N
        sb.append(", cssURL=")       .append(this.getCssURL()       .isPresent() ? this.getCssURL()       .get() : UNDEFINED); // NOI18N
        sb.append(", description=")  .append(this.getDescription()  .isPresent() ? this.getDescription()  .get() : UNDEFINED); // NOI18N
        sb.append(", visible=")      .append(this.isVisible()); // NOI18N
        
        sb.append("]"); // NOI18N
        
        return sb.toString();
    }
    
}
