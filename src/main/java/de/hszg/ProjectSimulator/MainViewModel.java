package de.hszg.ProjectSimulator;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.*;

/**
 * Created by Andre on 26.11.2015.
 */
public class MainViewModel implements ViewModel{

    private StringProperty geldEingabeProperty = new SimpleStringProperty();
    private StringProperty zeitEingabeProperty = new SimpleStringProperty();
    private StringProperty qualitaetProperty = new SimpleStringProperty("0");
    private StringProperty zeitProperty = new SimpleStringProperty("0");
    private StringProperty skillProperty = new SimpleStringProperty("5");
    private StringProperty gehaltProperty = new SimpleStringProperty("1500");
    private StringProperty geldProperty = new SimpleStringProperty("0");
    private StringProperty gesundheitProperty = new SimpleStringProperty("10");
    private StringProperty motivationProperty = new SimpleStringProperty("5");
    private DoubleProperty gehaltWertProperty = new SimpleDoubleProperty();


    public Property<String> geldEingabe() {
        return geldEingabeProperty;
    }

    public Property<String> zeitEinagabe() {
        return zeitEingabeProperty;
    }

    public Property<String> qualitaetText() {
        return qualitaetProperty;
    }

    public Property<String> zeitText() {
        return zeitProperty;
    }

    public Property<String> skillText() {
        return skillProperty;
    }

    public Property<String> gehaltText() {
        return gehaltProperty;
    }

    public Property<String> geldText() {
        return geldProperty;
    }

    public Property<String> gesundheitText() {
        return gesundheitProperty;
    }

    public Property<String> motivationText() {
        return motivationProperty;
    }

    public Property<Number> gehaltProperty() {
        return gehaltWertProperty;
    }

    public void startRound() {
    }

    public void nextRound(String text) {
        System.out.println(text);
    }
}
