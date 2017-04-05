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
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.util.Callback;

/**
 *
 * @author Name
 */
public class ApplicationPresenter implements Initializable, IRegisterActions {
    
    private static final String TAB_PROJECT = "Project"; // NOI18N
    private static final String TAB_SAMPLE  = "Sample"; // NOI18N
    
    @FXML private Accordion aCssMultiPages;
    @FXML private Accordion aJavaDocMultiPages;
    @FXML private Accordion aSourceCodeMultiPages;
    @FXML private ListView<ConcreteProject> lvNavigationProjects;
    @FXML private ListView<ConcreteSample> lvNavigationSamples;
    @FXML private TabPane tpProjectPages;
    @FXML private VBox vbSamplePage;
    @FXML private WebView wvCssSinglePage;
    @FXML private WebView wvJavaDocSinglePage;
    @FXML private WebView wvSourceCodeSinglePage;
    
    private final List<ConcreteProject> concreteProjects = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
//        assert (apView != null) : "fx:id=\"apView\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        this.initializeNavigationProjects();
        this.initializeNavigationSamples();
        this.initializeProjectScanning();

        this.registerActions();
        
        this.onActionRefreshNavigationProjects();
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.getDefault().debug(this.getClass(), "Initialize ApplicationPresenter after window is showing"); // NOI18N
    }
    
    public void initializeNavigationProjects() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize Navigation Projects"); // NOI18N
        
        final Callback callbackConcreteProjects = (Callback<ListView<ConcreteProject>, ListCell<ConcreteProject>>) (ListView<ConcreteProject> listView) -> new ListCell<ConcreteProject>() {
            @Override
            protected void updateItem(ConcreteProject concreteProject, boolean empty) {
                super.updateItem(concreteProject, empty);
                
                this.setGraphic(null);
                
                if (concreteProject == null || empty) {
                    this.setText(null);
                } else {
                    this.setText(concreteProject.getName());
                }
            }
        };
        
        lvNavigationProjects.setCellFactory(callbackConcreteProjects);
        
        lvNavigationProjects.setOnMouseClicked(event -> {
            this.onActionPrepareTabsForProjects();
        });
        
        lvNavigationProjects.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends ConcreteProject> observable, ConcreteProject oldValue, ConcreteProject newValue) -> {
            this.onActionShowProjectPage(newValue);
            this.onActionRefreshNavigationSamples(newValue.getConcreteSamples());
        });
    }
    
    public void initializeNavigationSamples() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize Navigation Samples"); // NOI18N
        
        final Callback callbackConcreteSamples = (Callback<ListView<ConcreteSample>, ListCell<ConcreteSample>>) (ListView<ConcreteSample> listView) -> new ListCell<ConcreteSample>() {
            @Override
            protected void updateItem(ConcreteSample concreteSample, boolean empty) {
                super.updateItem(concreteSample, empty);
                
                this.setGraphic(null);
                
                if (concreteSample == null || empty) {
                    this.setText(null);
                } else {
                    this.setText(concreteSample.getName());
                }
            }
        };
        
        lvNavigationSamples.setCellFactory(callbackConcreteSamples);
        
        lvNavigationSamples.setOnMouseClicked(event -> {
            this.onActionPrepareTabsForSamples();
            
            // Open the Link
            if (
                    event.getClickCount() == 2
                    && !lvNavigationSamples.getSelectionModel().isEmpty()
            ) {
                final ConcreteSample concreteSample = lvNavigationSamples.getSelectionModel().getSelectedItem();
                this.onActionShowConcreteSample(concreteSample);
            }
        });
    }
    
    private void initializeProjectScanning() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize Projects Scanning"); // NOI18N
        
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
//            ProjectPrinter.print(collectedSamplesAsStrings);
            
            final List<Class<?>> convertedSamplesToClasses = ProjectConverter.convertSamplesToClasses(collectedSamplesAsStrings);
//            ProjectPrinter.printClasses(convertedSamplesToClasses);
            
            final List<ConcreteSample> convertedSamplesToConcreteSamples = ProjectConverter.convertSamplesToConcreteSamples(convertedSamplesToClasses);
//            ProjectPrinter.printConcreteSamples(convertedSamplesToConcreteSamples);

            final List<ConcreteProject> mappedConcreteSampesToConcreteProjects = ProjectMapper.mapConcreteSampelsToConcreteProjects(convertedProjectsToConcreteProjects, convertedSamplesToConcreteSamples);
//            ProjectPrinter.printConcreteProjects(mappedConcreteSampesToConcreteProjects);
            
            concreteProjects.addAll(mappedConcreteSampesToConcreteProjects);
        } catch (IOException ex) {
            LoggerFacade.getDefault().debug(this.getClass(), "error: ", ex); // NOI18N
        }
    }
    
    private void onActionPrepareTabsForProjects() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action prepare Tabs for Projects"); // NOI18N

        final ObservableList<Tab> tabs = tpProjectPages.getTabs();
        if (tabs.get(0).getText().equals(TAB_SAMPLE)) {
            Platform.runLater(() -> {
                tabs.get(0).setText(TAB_PROJECT);
                tabs.get(1).setDisable(Boolean.TRUE);
                tabs.get(2).setDisable(Boolean.TRUE);
                tabs.get(3).setDisable(Boolean.TRUE);

                tpProjectPages.getSelectionModel().selectFirst();
            });
        }
    }
    
    private void onActionPrepareTabsForSamples() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action prepare Tabs for Samples"); // NOI18N

        final ObservableList<Tab> tabs = tpProjectPages.getTabs();
        if (tabs.get(0).getText().equals(TAB_PROJECT)) {
            Platform.runLater(() -> {
                tabs.get(0).setText(TAB_SAMPLE);
                tabs.get(1).setDisable(Boolean.FALSE);
                tabs.get(2).setDisable(Boolean.FALSE);
                tabs.get(3).setDisable(Boolean.FALSE);
            });
        }
    }
    
    private void onActionRefreshNavigationProjects() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action refresh Navigation Projects"); // NOI18N

        lvNavigationProjects.getItems().clear();
        lvNavigationProjects.getItems().addAll(concreteProjects);
    }

    private void onActionRefreshNavigationSamples(final List<ConcreteSample> concreteSamples) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action refresh Navigation Samples"); // NOI18N

        lvNavigationSamples.getItems().clear();
        lvNavigationSamples.getItems().addAll(concreteSamples);
    }

    private void onActionShowConcreteSample(final ConcreteSample concreteSample) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show ConcreteSample"); // NOI18N
        
        // TODO update only the selected tab
    }

    private void onActionShowProjectPage(ConcreteProject concreteProject) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show Project Page"); // NOI18N
        
        // TODO update only first tab
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
    }
    
}
