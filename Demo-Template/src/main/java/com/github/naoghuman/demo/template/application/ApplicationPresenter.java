/*
 * Copyright (C) 2016 Name
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
package com.github.naoghuman.demo.template.application;

import com.github.naoghuman.demo.template.project.ConcreteProject;
import com.github.naoghuman.demo.template.project.ConcreteSample;
import com.github.naoghuman.demo.template.project.ProjectCollector;
import com.github.naoghuman.demo.template.project.ProjectConverter;
import com.github.naoghuman.demo.template.project.ProjectFilter;
import com.github.naoghuman.demo.template.project.ProjectMapper;
import com.github.naoghuman.demo.template.project.ProjectPrinter;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

/**
 *
 * @author Name
 */
public class ApplicationPresenter implements Initializable, IRegisterActions {
    
    @FXML private Accordion aCssMultiPages;
    @FXML private Accordion aJavaDocMultiPages;
    @FXML private Accordion aSourceCodeMultiPages;
    @FXML private BorderPane bpSamplePageLeftArea;
    @FXML private TextField tfSearchInSamples;
    @FXML private TreeView<String> tvOverviewSamples;
    @FXML private VBox vbSamplePageRigthSide;
    @FXML private WebView wvCssSinglePage;
    @FXML private WebView wvJavaDocSinglePage;
    @FXML private WebView wvSourceCodeSinglePage;
    
    private final List<ConcreteProject> concreteProject = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
//        assert (apView != null) : "fx:id=\"apView\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        this.initializeSearchInSamples();
        this.initializeOverviewSamples();

        this.registerActions();
        this.onActionPrepareProjects();
        this.onActionShowPreparedProjects();
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.getDefault().debug(this.getClass(), "Initialize ApplicationPresenter after window is showing"); // NOI18N
    }
    
    public void initializeSearchInSamples() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize Search in Samples"); // NOI18N
        
//        tfSearchInSamples.getStyleClass().add("search-box");
        tfSearchInSamples.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable o) {
                onActionRefreshOverviewSamples(tfSearchInSamples.getText());
            }
        });
    }
    
    public void initializeOverviewSamples() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize Overview Samples"); // NOI18N
        
    }
    
    private void onActionPrepareProjects() {
        LoggerFacade.getDefault().info(this.getClass(), "On action prepare projects"); // NOI18N
        
        try {
            final List<URL> collectedURLs = ProjectCollector.collectURLs();
//            ProjectPrinter.printURLs(collectedURLs);
            
            final List<URL> filteredURLs = ProjectFilter.filterURLs(collectedURLs);
//            ProjectPrinter.printURLs(filteredURLs);
            
            final List<File> collectedClassesAsFiles = ProjectCollector.collectClassesAsFiles(filteredURLs);
//            ProjectPrinter.printFiles(collectedClassesAsFiles);
            
            final List<File> filteredClassFiles = ProjectFilter.filterClassFiles(collectedClassesAsFiles);
//            ProjectPrinter.printFiles(filteredClassFiles);
            
            final List<String> collectedProjectsAsStrings = ProjectCollector.collectProjectsAsStrings(filteredClassFiles);
//            ProjectPrinter.print(collectedProjectsAsStrings);
            
            final List<Class<?>> convertedProjectsToClasses = ProjectConverter.convertProjectsToClasses(collectedProjectsAsStrings);
//            ProjectPrinter.printClasses(convertedProjectsToClasses);
            
            final List<ConcreteProject> convertedProjectsToConcreteProjects = ProjectConverter.convertProjectsToConcreteProjects(convertedProjectsToClasses);
//            ProjectPrinter.printConcreteProjects(convertedProjectsToConcreteProjects);
            
            final List<String> collectedSamplesAsStrings = ProjectCollector.collectSamplesAsStrings(filteredClassFiles);
            ProjectPrinter.print(collectedSamplesAsStrings);
            
            final List<Class<?>> convertedSamplesToClasses = ProjectConverter.convertSamplesToClasses(collectedSamplesAsStrings);
            ProjectPrinter.printClasses(convertedSamplesToClasses);
            
            final List<ConcreteSample> convertedSamplesToConcreteSamples = ProjectConverter.convertSamplesToConcreteSamples(convertedSamplesToClasses);
//            ProjectPrinter.printConcreteSamples(convertedSamplesToConcreteSamples);

            final List<ConcreteProject> mappedConcreteSampesToConcreteProjects = ProjectMapper.mapConcreteSampelsToConcreteProjects(convertedProjectsToConcreteProjects, convertedSamplesToConcreteSamples);
            ProjectPrinter.printConcreteProjects(mappedConcreteSampesToConcreteProjects);
            
            concreteProject.addAll(mappedConcreteSampesToConcreteProjects);
        } catch (IOException ex) {
            LoggerFacade.getDefault().debug(this.getClass(), "error: ", ex); // NOI18N
        }
    }

    private void onActionRefreshOverviewSamples(String searchText) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action refresh Overview Samples"); // NOI18N
    
    }
    
    private void onActionShowPreparedProjects() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show prepared projects"); // NOI18N
    
        
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
    }
    
}
