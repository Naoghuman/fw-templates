/*
 * Copyright (C) 2016 Naoghuman
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

import com.github.naoghuman.demo.template.annotation.SampleType;
import com.github.naoghuman.demo.template.project.ComingSoonView;
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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 *
// TODO show sample
// use reflection to instanziate
// write feature convention over configuration for sample-view-class in documentation.
// write feature to read the annotations without instanziating -> library org.ow2.asm
 *
 * TODO show waring in onActionShowPageSample(ConcreteSample concreteSample)
 * if the class cant be find during reflection. give an advice in webview with
 * warning-template.
 * 
 * @author Naoghuman
 */
public class ApplicationPresenter implements Initializable, IRegisterActions {
    
    private static final int INDEX_TAB__SAMPLE     = 0;
    private static final int INDEX_TAB__SOURCECODE = 1;
    private static final int INDEX_TAB__JAVADOC    = 2;
    private static final int INDEX_TAB__CSS        = 3;
    
    @FXML private ListView<ConcreteProject> lvNavigationProjects;
    @FXML private ListView<ConcreteSample>  lvNavigationSamples;
    @FXML private Tab tCSS;
    @FXML private Tab tJavaDoc;
    @FXML private Tab tOverview;
    @FXML private Tab tProject;
    @FXML private Tab tSample;
    @FXML private Tab tSourceCode;
    @FXML private TabPane tpProjectPages;
    @FXML private VBox vbSamplePage;
    @FXML private WebView wvCssPage;
    @FXML private WebView wvJavaDocPage;
    @FXML private WebView wvOverviewPage;
    @FXML private WebView wvSourceCodePage;
    @FXML private WebView wvProjectPage;
    
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
                this.setText(null);
                
                if (concreteProject != null && !empty) {
                    final String prefix = ProjectConverter.convertProjectNrOrSampleNrToPrefix(concreteProject.getProjectNr());
                    final String name = concreteProject.getName();
                    final String text = prefix + name;
                    this.setText(text);
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
            
            private final Text TEXT = new Text();
            {
                final Font f = TEXT.getFont();
                TEXT.setFont(Font.font(f.getFamily(), FontPosture.ITALIC, f.getSize()));
                TEXT.setStrikethrough(Boolean.TRUE);
            }
            
            @Override
            protected void updateItem(ConcreteSample concreteSample, boolean empty) {
                super.updateItem(concreteSample, empty);
                
                this.setGraphic(null);
                this.setText(null);
                
                if (concreteSample != null && !empty) {
                    final boolean isSampleVisible = concreteSample.isVisible();
                    
                    if (isSampleVisible) {
                        this.setText(concreteSample.getName());
                    }
                    else {
                        TEXT.setText(concreteSample.getName());
                        this.setGraphic(TEXT);
                    }
                }
            }
        };
        
        lvNavigationSamples.setCellFactory(callbackConcreteSamples);
        
        lvNavigationSamples.setOnMouseClicked(event -> {
            if (!lvNavigationSamples.getSelectionModel().isEmpty()) {
                concreteSample = lvNavigationSamples.getSelectionModel().getSelectedItem();
                this.onActionPrepareTabsForSamples(concreteSample);
                
                // Select new sample in navigation shows the first tab
                final int selectedIndex = 0;
                this.onActionShowConcreteSample(selectedIndex);
            }
        });
    }
    
    private void initializeTabPanePages() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize TabPanePages"); // NOI18N
        
        tpProjectPages.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) -> {
            if (tpProjectPages.getTabs().size() > 1) {
                // Reset gui
                wvSourceCodePage.getEngine().loadContent(TemplateLoader.loadLoadingTemplate());
                wvJavaDocPage   .getEngine().loadContent(TemplateLoader.loadLoadingTemplate());
                wvCssPage       .getEngine().loadContent(TemplateLoader.loadLoadingTemplate());
                
                // Show new page
                final int selectedIndex = tpProjectPages.getSelectionModel().getSelectedIndex();
                this.onActionShowConcreteSample(selectedIndex);
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
    
    private void onActionPrepareTabsForSamples(ConcreteSample concreteSample) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action prepare Tabs for Samples"); // NOI18N

        Platform.runLater(() -> {
            final ObservableList<Tab> tabs = tpProjectPages.getTabs();
            tabs.clear();
            
            final boolean isSampleVisible = concreteSample.isVisible();
            if (SampleType.isNormal(concreteSample.getSampleType())) {
                tabs.add(tSample);
                tabs.add(tSourceCode);
                tabs.add(tJavaDoc);
                tabs.add(tCSS);
                
                tSourceCode.setDisable(!isSampleVisible);
                tJavaDoc   .setDisable(!isSampleVisible);
                tCSS       .setDisable(!isSampleVisible);
            
                if (isSampleVisible) {
                    wvSourceCodePage.getEngine().loadContent(TemplateLoader.loadLoadingTemplate());
                    wvJavaDocPage   .getEngine().loadContent(TemplateLoader.loadLoadingTemplate());
                    wvCssPage       .getEngine().loadContent(TemplateLoader.loadLoadingTemplate());
                }
            }
            else {
                tabs.add(tOverview);
                
                wvOverviewPage.getEngine().loadContent(TemplateLoader.loadLoadingTemplate());
            }
            
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

    private void onActionShowConcreteSample(final int selectedIndex) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show ConcreteSample"); // NOI18N
        
        final ConcreteSample selectedConcreteSample = lvNavigationSamples.getSelectionModel().getSelectedItem();
        if (selectedConcreteSample != null) {
            if (SampleType.isNormal(selectedConcreteSample.getSampleType())) {
                switch (selectedIndex) {
                    case INDEX_TAB__SAMPLE:     { this.onActionShowPageSample(selectedConcreteSample);     break; }
                    case INDEX_TAB__SOURCECODE: { this.onActionShowPageSourceCode(selectedConcreteSample); break; }
                    case INDEX_TAB__JAVADOC:    { this.onActionShowPageJavaDoc(selectedConcreteSample);    break; }
                    case INDEX_TAB__CSS:        { this.onActionShowPageCSS(selectedConcreteSample);        break; }
                }
            }
            else {
                this.onActionShowPageOverview(selectedConcreteSample);
            }
        }
    }

    private void onActionShowPageCSS(ConcreteSample concreteSample) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show page [CSS]"); // NOI18N
        
        final PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(25.0d));
        pt.setOnFinished(event -> {
            LoggerFacade.getDefault().debug(this.getClass(), "Load css-url..."); // NOI18N
            
            final boolean hasCssURL = concreteSample.hasCssURL();
            if (hasCssURL) {
                LoggerFacade.getDefault().debug(this.getClass(), "A css-url is defined."); // NOI18N
                
                wvCssPage.getEngine().loadContent(TemplateLoader.loadCSStemplate(concreteSample.getCssURL().get()));
            }
            else {
                LoggerFacade.getDefault().warn(this.getClass(), "No css-url is defined!"); // NOI18N
                
                wvCssPage.getEngine().loadContent(TemplateLoader.loadNoXyURLisDefinedTemplate(TemplateLoader.UrlType.CSS));
            }
        });
        
        pt.playFromStart();
    }

    private void onActionShowPageJavaDoc(ConcreteSample concreteSample) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show page [JavaDoc]"); // NOI18N
        
        final PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(25.0d));
        pt.setOnFinished(event -> {
            LoggerFacade.getDefault().debug(this.getClass(), "Load javadoc-url..."); // NOI18N
            
            final boolean hasJavaDocURL = concreteSample.hasJavaDocURL();
            if (hasJavaDocURL) {
                LoggerFacade.getDefault().debug(this.getClass(), "A javadoc-url is defined."); // NOI18N
            
                wvJavaDocPage.getEngine().load(concreteSample.getJavaDocURL().get());
            }
            else {
                LoggerFacade.getDefault().warn(this.getClass(), "No javadoc-url is defined!"); // NOI18N
                
                wvJavaDocPage.getEngine().loadContent(TemplateLoader.loadNoXyURLisDefinedTemplate(TemplateLoader.UrlType.JAVA_DOC));
            }
        });
        
        pt.playFromStart();
    }
    
    private void onActionShowPageOverview(ConcreteSample concreteSample) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show page [Overview]"); // NOI18N
        
        final PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(25.0d));
        pt.setOnFinished(event -> {
            LoggerFacade.getDefault().debug(this.getClass(), "Load overview-url..."); // NOI18N
            
            final boolean hasOverviewURL = concreteSample.hasOverviewURL();
            if (hasOverviewURL) {
                LoggerFacade.getDefault().debug(this.getClass(), "A overview-url is defined."); // NOI18N
            
                wvOverviewPage.getEngine().load(concreteSample.getOverviewURL().get());
            }
            else {
                LoggerFacade.getDefault().warn(this.getClass(), "No overview-url is defined!"); // NOI18N
                
                wvOverviewPage.getEngine().loadContent(TemplateLoader.loadNoXyURLisDefinedTemplate(TemplateLoader.UrlType.OVERVIEW));
            }
        });
        
        pt.playFromStart();
    }

    private void onActionShowPageProject(final ConcreteProject concreteProject) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show page [Project] for: " + concreteProject.getName()); // NOI18N
        
        // Reset previous shown content in sample-view
        wvProjectPage.getEngine().loadContent(TemplateLoader.loadLoadingTemplate());

        final PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(25.0d));
        pt.setOnFinished(event -> {
            LoggerFacade.getDefault().debug(this.getClass(), "Load project-url..."); // NOI18N

            // Show new project-view
            if (concreteProject.hasProjectURL()) {
                LoggerFacade.getDefault().debug(this.getClass(), "A project-url is defined."); // NOI18N

                wvProjectPage.getEngine().load(concreteProject.getProjectURL().get());
            }
            else {
                LoggerFacade.getDefault().warn(this.getClass(), "No project-url is defined!"); // NOI18N

                wvProjectPage.getEngine().loadContent(TemplateLoader.loadNoXyURLisDefinedTemplate(TemplateLoader.UrlType.PROJECT));
            }
        });
        
        pt.playFromStart();
    }

    private void onActionShowPageSample(ConcreteSample concreteSample) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show page [Sample]"); // NOI18N
        
        vbSamplePage.getChildren().clear();
        
        final boolean isSampleVisible = concreteSample.isVisible();
        if (isSampleVisible) {
            LoggerFacade.getDefault().debug(this.getClass(), "  -> show [Sample]"); // NOI18N
            
            try {
                final String sampleViewClass = concreteSample.getSampleViewClass();
                final Class clazz = Class.forName(sampleViewClass);
                final Object instance = clazz.newInstance();
                final Method method = clazz.getSuperclass().getDeclaredMethod("getView"); // NOI18N
                final Object value = method.invoke(instance);
                if (value instanceof Parent) {
                    final Parent parent = (Parent) value;
                    VBox.setVgrow(parent, Priority.ALWAYS);
                    vbSamplePage.getChildren().add(parent);
                }
            } catch (
                    ClassNotFoundException
                    | InstantiationException
                    | IllegalAccessException
                    | NoSuchMethodException
                    | SecurityException
                    | IllegalArgumentException
                    | InvocationTargetException ex
            ) {
                LoggerFacade.getDefault().error(this.getClass(), "Error during reflection!", ex); // NOI18N
                // TODO show warning in webview with template
            }
        }
        else {
            LoggerFacade.getDefault().debug(this.getClass(), "  -> show [ComingSoonView]"); // NOI18N
            
            vbSamplePage.getChildren().add(ComingSoonView.getComingSoonView());
        }
    }

    private void onActionShowPageSourceCode(ConcreteSample concreteSample) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action show page [SourceCode]"); // NOI18N
        
        final PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(25.0d));
        pt.setOnFinished(event -> {
            LoggerFacade.getDefault().debug(this.getClass(), "Load sourcecode-url..."); // NOI18N
            
            final boolean hasCssURL = concreteSample.hasCssURL();
            if (hasCssURL) {
                LoggerFacade.getDefault().debug(this.getClass(), "A sourcecode-url is defined."); // NOI18N
                
                wvSourceCodePage.getEngine().loadContent(TemplateLoader.loadSourceCodeTemplate(concreteSample.getSourceCodeURL().get()));
            }
            else {
                LoggerFacade.getDefault().warn(this.getClass(), "No sourcecode-url is defined!"); // NOI18N
                
                wvSourceCodePage.getEngine().loadContent(TemplateLoader.loadNoXyURLisDefinedTemplate(TemplateLoader.UrlType.SOURCE_CODE));
            }
        });
        
        pt.playFromStart();
    }
    
    @Override
    public void registerActions() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register actions in ApplicationPresenter"); // NOI18N
    }
    
}
