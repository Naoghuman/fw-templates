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
import com.github.naoghuman.demo.template.project.TemplateLoader;
import com.github.naoghuman.lib.action.api.IRegisterActions;
import com.github.naoghuman.lib.logger.api.LoggerFacade;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
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
import javafx.util.Duration;

/**
 *
 * @author Name
 */
public class ApplicationPresenter implements Initializable, IRegisterActions {
    
    private static final int INDEX_TAB__SAMPLE     = 0;
    private static final int INDEX_TAB__SOURCECODE = 1;
    private static final int INDEX_TAB__JAVADOC    = 2;
    private static final int INDEX_TAB__CSS        = 3;
    
    @FXML private Accordion aJavaDocMultiPages;
    @FXML private Accordion aSourceCodeMultiPages;
    @FXML private ListView<ConcreteProject> lvNavigationProjects;
    @FXML private ListView<ConcreteSample> lvNavigationSamples;
    @FXML private Tab tCSS;
    @FXML private Tab tJavaDoc;
    @FXML private Tab tProject;
    @FXML private Tab tSample;
    @FXML private Tab tSourceCode;
    @FXML private TabPane tpProjectPages;
    @FXML private VBox vbSamplePage;
    @FXML private WebView wvCssSinglePage;
    @FXML private WebView wvJavaDocSinglePage;
    @FXML private WebView wvSourceCodeSinglePage;
    @FXML private WebView wvProjectSinglePage;
    
    private final List<ConcreteProject> concreteProjects = FXCollections.observableArrayList();
    
    private ConcreteSample concreteSample;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ApplicationPresenter"); // NOI18N
        
//        assert (apView != null) : "fx:id=\"apView\" was not injected: check your FXML file 'Application.fxml'."; // NOI18N
        
        this.initializeNavigationProjects();
        this.initializeNavigationSamples();
        this.initializeTabPanePages();

        this.registerActions();
        
        this.onActionScanForProjects();
        
        this.onActionRefreshNavigationProjects();
        this.onActionPrepareTabsForOnStartApplication();
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.getDefault().debug(this.getClass(), "Initialize ApplicationPresenter after window is showing"); // NOI18N
    }
    
    private void initializeNavigationProjects() {
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
            if (!lvNavigationProjects.getSelectionModel().isEmpty()) {
                this.onActionPrepareTabsForProjects();
            }
        });
        
        lvNavigationProjects.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends ConcreteProject> observable, ConcreteProject oldValue, ConcreteProject newValue) -> {
            this.onActionShowPageProject(newValue);
            this.onActionRefreshNavigationSamples(newValue.getConcreteSamples());
        });
    }
    
    private void initializeNavigationSamples() {
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
            if (!lvNavigationSamples.getSelectionModel().isEmpty()) {
                this.onActionPrepareTabsForSamples();
                
                concreteSample = lvNavigationSamples.getSelectionModel().getSelectedItem();
                this.onActionShowPageSample(concreteSample);
            }
        });
    }
    
    private void initializeTabPanePages() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize TabPanePages"); // NOI18N
        
        tpProjectPages.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) -> {
            if (tpProjectPages.getTabs().size() > 1) {
                this.onActionShowConcreteSample();
            }
        });
    }
    
    private void onActionPrepareTabsForProjects() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action prepare Tabs for Projects"); // NOI18N

        Platform.runLater(() -> {
            tpProjectPages.getTabs().clear();
            tpProjectPages.getTabs().add(tProject);
        });
    }
    
    private void onActionPrepareTabsForSamples() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action prepare Tabs for Samples"); // NOI18N

        Platform.runLater(() -> {
            final ObservableList<Tab> tabs = tpProjectPages.getTabs();
            tabs.clear();
            tabs.add(tSample);
            tabs.add(tSourceCode);
            tabs.add(tJavaDoc);
            tabs.add(tCSS);
            
            tpProjectPages.getSelectionModel().selectFirst();
        });
    }
    
    private void onActionPrepareTabsForOnStartApplication() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action prepare Tabs for on start Application"); // NOI18N

        Platform.runLater(() -> {
            tpProjectPages.getTabs().clear();
        });
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
    
    private void onActionScanForProjects() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action scan for Projects"); // NOI18N
        
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

    private void onActionShowConcreteSample() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show ConcreteSample"); // NOI18N
        
        // TODO update only the selected tab
        final ConcreteSample concreteSample = lvNavigationSamples.getSelectionModel().getSelectedItem();
        if (concreteSample != null) {
            final int selectedIndex = tpProjectPages.getSelectionModel().getSelectedIndex();
            switch (selectedIndex) {
                case INDEX_TAB__SAMPLE:     { this.onActionShowPageSample(concreteSample);     break; }
                case INDEX_TAB__SOURCECODE: { this.onActionShowPageSourceCode(concreteSample); break; }
                case INDEX_TAB__JAVADOC:    { this.onActionShowPageJavaDoc(concreteSample);    break; }
                case INDEX_TAB__CSS:        { this.onActionShowPageCSS(concreteSample);        break; }
            }
        }
    }

    private void onActionShowPageCSS(ConcreteSample concreteSample) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show CSS Page"); // NOI18N
        
        Platform.runLater(() -> {
            final boolean hasCssURL = concreteSample.hasCssURL();
            if (hasCssURL) {
                LoggerFacade.getDefault().debug(this.getClass(), "Only one css-url is defined."); // NOI18N
                
                wvCssSinglePage.getEngine().loadContent(TemplateLoader.loadLoadingTemplate());

                final PauseTransition pt = new PauseTransition();
                pt.setDuration(Duration.millis(25.0d));
                pt.setOnFinished(event -> {
                    LoggerFacade.getDefault().debug(this.getClass(), "Load css-url..."); // NOI18N
                
                    wvCssSinglePage.getEngine().loadContent(TemplateLoader.loadCSStemplate(concreteSample.getCssURL().get()));
                });
                pt.playFromStart();
            }
            else {
                LoggerFacade.getDefault().warn(this.getClass(), "No css-url is defined!"); // NOI18N
                
                wvCssSinglePage.getEngine().loadContent(TemplateLoader.loadNoCSSURLsAreDefinedTemplate());
            }
        });
    }

    private void onActionShowPageJavaDoc(ConcreteSample concreteSample) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show JavaDoc Page"); // NOI18N
        
        // Prepare the page
        final boolean pageJavaDocIsSinglePage = concreteSample.getJavaDocURLs().size() > 1;
        aJavaDocMultiPages.setManaged(!pageJavaDocIsSinglePage);
        aJavaDocMultiPages.setVisible(!pageJavaDocIsSinglePage);
        
        wvJavaDocSinglePage.setManaged(pageJavaDocIsSinglePage);
        wvJavaDocSinglePage.setVisible(pageJavaDocIsSinglePage);
        
        // Load content
    }

    private void onActionShowPageProject(final ConcreteProject concreteProject) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show Project Page for: " + concreteProject.getName()); // NOI18N
        
        Platform.runLater(() -> {
            if (concreteProject.hasProjectURL()) {
                LoggerFacade.getDefault().debug(this.getClass(), "Project-url is defined."); // NOI18N
                
                wvProjectSinglePage.getEngine().loadContent(TemplateLoader.loadLoadingTemplate());
                
                final PauseTransition pt = new PauseTransition();
                pt.setDuration(Duration.millis(25.0d));
                pt.setOnFinished(event -> {
                    LoggerFacade.getDefault().debug(this.getClass(), "Load project-url..."); // NOI18N
                
                    wvProjectSinglePage.getEngine().load(concreteProject.getProjectURL().get());
                });
                pt.playFromStart();
            }
            else {
                LoggerFacade.getDefault().warn(this.getClass(), "No project-url is defined!"); // NOI18N
                
                wvProjectSinglePage.getEngine().loadContent(TemplateLoader.loadNoProjectURLisDefinedTemplate());
            }
        });
    }

    private void onActionShowPageSample(ConcreteSample concreteSample) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show Sample Page"); // NOI18N
        
    }

    private void onActionShowPageSourceCode(ConcreteSample concreteSample) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show SourceCode Page"); // NOI18N
        
        // Prepare the page
        final boolean pageSourceCodeIsSinglePage = concreteSample.getSourceCodeURLs().size() > 1;
        aSourceCodeMultiPages.setManaged(!pageSourceCodeIsSinglePage);
        aSourceCodeMultiPages.setVisible(!pageSourceCodeIsSinglePage);
        wvSourceCodeSinglePage.setManaged(pageSourceCodeIsSinglePage);
        wvSourceCodeSinglePage.setVisible(pageSourceCodeIsSinglePage);
        
        // Load content
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
    }
    
}
