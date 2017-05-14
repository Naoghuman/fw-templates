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
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import javafx.collections.FXCollections;

/**
 *
 * @author Naoghuman
 */
public final class ConcreteProject implements Comparable<ConcreteProject> {
    
    private static final int DEFAULT_PROJECT_NR = -1;
    private static final String UNDEFINED = "[undefined]"; // NOI18N
    
    public static final ConcreteProject create(final String name) {
        return create(
                System.nanoTime(), name,
                UNDEFINED, DEFAULT_PROJECT_NR, 
                UNDEFINED, UNDEFINED,
                Boolean.TRUE);
    }
    
    public static final ConcreteProject create(Project project) {
        return create(
                System.nanoTime(), project.name(),
                project.description(), project.projectNr(),
                project.projectURL(), project.version(),
                project.visible());
    }
    
    public static final ConcreteProject create(
            final String name, final String description,
            final int projectNr, final String projectURL,
            final String version, final boolean visible
    ) {
        return create(
                System.nanoTime(), name,
                description, projectNr,
                projectURL, version,
                visible);
    }
    
    public static final ConcreteProject create(
            final long id, final String name, 
            final String description, final int projectNr,
            final String projectURL, final String version,
            final boolean visible
    ) {
        final ConcreteProject concreteProject = new ConcreteProject(
                id, name, 
                description, projectNr,
                projectURL, version,
                visible);
        
        return concreteProject;
    }
    
    private final List<ConcreteSample> concreteSamples = FXCollections.observableArrayList();
    
    private final int projectNr;
    private final long id;
    private final boolean visible;
    
    private final Optional<String> description;
    private final String name;
    private final Optional<String> projectURL;
    private final Optional<String> version;
    
    private ConcreteProject(
            final long id, final String name,
            final String description, final int projectNr,
            final String projectURL, final String version,
            final boolean visible
    ) {
        Objects.requireNonNull(name);
        
        if (name.isEmpty()) {
            throw new IllegalArgumentException("The [name] can't be EMPTY"); // NOI18N
        }
        
        this.id          = id;
        this.name        = name;
        this.description = Optional.ofNullable((description == null) ? description : ((description.isEmpty()) ? null : description));
        this.projectNr   = (projectNr > DEFAULT_PROJECT_NR)          ? projectNr   : DEFAULT_PROJECT_NR;
        this.projectURL  = Optional.ofNullable((projectURL  == null) ? projectURL  : ((projectURL.isEmpty())  ? null : projectURL));
        this.version     = Optional.ofNullable((version     == null) ? version     : ((version.isEmpty())     ? null : version));
        this.visible     = visible;
    }
    
    public void add(ConcreteSample concreteSample) {
        if (!concreteSamples.contains(concreteSample)) {
            concreteSamples.add(concreteSample);
        }
    }
    
    public boolean hasDescription() {
        return this.getDescription().isPresent()
                && !this.getDescription().get().equals(UNDEFINED);
    }
    
    public Optional<String> getDescription() {
        return description;
    }
    
    public int getProjectNr() {
        return projectNr;
    }
    
    public boolean hasProjectURL() {
        return this.getProjectURL().isPresent()
                && !this.getProjectURL().get().equals(UNDEFINED);
    }
    
    public Optional<String> getProjectURL() {
        return projectURL;
    }
    
    public final long getId() {
        return id;
    }

    public final String getName() {
        return name;
    }
    
    public final List<ConcreteSample> getConcreteSamples() {
        Collections.sort(concreteSamples);
        
        return concreteSamples;
    }
    
    public boolean hasVersion() {
        return this.getVersion().isPresent()
                && !this.getVersion().get().equals(UNDEFINED);
    }
    
    public Optional<String> getVersion() {
        return version;
    }

    public final boolean isVisible() {
        return visible;
    }
    
    @Override
    public int compareTo(final ConcreteProject other) {
        int compareTo = (this.getProjectNr() + this.getName()).compareTo(other.getProjectNr() + other.getName());
        if (compareTo != 0) {
            return compareTo;
        }
        
        if (this.getVersion().isPresent() && other.getVersion().isPresent()) {
            compareTo = this.getVersion().get().compareTo(other.getVersion().get());
            if (compareTo != 0) {
                return compareTo;
            }
        }
        
        if (this.getVersion().isPresent() && !other.getVersion().isPresent()) {
            return +1;
        }
        
        if (!this.getVersion().isPresent() && other.getVersion().isPresent()) {
            return -1;
        }
        
        if (this.getProjectURL().isPresent() && other.getProjectURL().isPresent()) {
            compareTo = this.getProjectURL().get().compareTo(other.getProjectURL().get());
            if (compareTo != 0) {
                return compareTo;
            }
        }
        
        if (this.getProjectURL().isPresent() && !other.getProjectURL().isPresent()) {
            return +1;
        }
        
        if (!this.getProjectURL().isPresent() && other.getProjectURL().isPresent()) {
            return -1;
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
        result = prime * result + this.getName()      .hashCode();
        result = prime * result + this.getVersion()   .hashCode();
        result = prime * result + this.getProjectURL().hashCode();
        
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
        
        final ConcreteProject other = (ConcreteProject) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        
        // version
        if (!this.getName().equals(other.getName())) {
            return false;
        }
        
        if (this.getVersion().isPresent() && !other.getVersion().isPresent()) {
            return false;
        }
        
        if (!this.getVersion().isPresent() && other.getVersion().isPresent()) {
            return false;
        }
        
        if (
                this.getVersion().isPresent()
                && other.getVersion().isPresent()
                && !this.getVersion().get().equals(other.getVersion().get())
        ) {
            return false;
        }
        
        // project-url
        if (this.getProjectURL().isPresent() && !other.getProjectURL().isPresent()) {
            return false;
        }
        
        if (!this.getProjectURL().isPresent() && other.getProjectURL().isPresent()) {
            return false;
        }
        
        if (
                this.getProjectURL().isPresent()
                && other.getProjectURL().isPresent()
                && !this.getProjectURL().get().equals(other.getProjectURL().get())
        ) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ConcreteProject ["); // NOI18N
        
        sb.append("id=")           .append(this.getId());        // NOI18N
        sb.append(", name=")       .append(this.getName());      // NOI18N
        sb.append(", description=").append(this.getDescription().isPresent() ? this.getDescription().get() : UNDEFINED); // NOI18N
        sb.append(", projectNr=")  .append(this.getProjectNr()); // NOI18N
        sb.append(", projectURL=") .append(this.getProjectURL().isPresent()  ? this.getProjectURL().get()  : UNDEFINED); // NOI18N
        
        if (!this.getConcreteSamples().isEmpty()) {
            final StringJoiner stringJoiner = new StringJoiner(", ", ", ConcreteSample[", "]"); // NOI18N
            this.getConcreteSamples().stream()
                    .forEach(concreteSample -> {
                        stringJoiner.add("concreteSample=" + concreteSample.toString()); // NOI18N
                    });
            sb.append(stringJoiner.toString());
        }
        
        sb.append(", version=").append(this.getVersion().isPresent() ? this.getVersion().get() : UNDEFINED); // NOI18N
        sb.append(", visible=").append(this.isVisible()); // NOI18N
        
        sb.append("]"); // NOI18N
        
        return sb.toString();
    }
    
}
