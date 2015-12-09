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

    private double magicValue = 0;

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
        zeitProperty.setValue(zeitEinagabe().getValue());
        geldProperty.setValue(geldEingabe().getValue());
        magicValue = 12 / stringPropertyToDouble(zeitProperty);
    }

    public void nextRound(String text) {
        switch (text) {
            case "Entwickeln": {
                motivationProperty.setValue(doubleToString(stringPropertyToDouble(motivationProperty) * (0.05 + (stringPropertyToDouble(gesundheitProperty) / 10d))));
                gesundheitProperty.setValue(doubleToString(stringPropertyToDouble(gesundheitProperty) * 0.95));
                zeitProperty.setValue(doubleToString(stringPropertyToDouble(zeitProperty) - 1));
                geldProperty.setValue(doubleToString(stringPropertyToDouble(geldProperty) - stringPropertyToDouble(gehaltProperty)));

                qualitaetProperty.setValue(doubleToString(stringPropertyToDouble(qualitaetProperty) + (((stringPropertyToDouble(skillProperty) + stringPropertyToDouble(motivationProperty) + stringPropertyToDouble(gesundheitProperty)) * 4) / 100) * magicValue));
                break;
            }
            case "Training": {
                skillProperty.setValue(doubleToString(stringPropertyToDouble(skillProperty) + 1));
                gesundheitProperty.setValue(doubleToString(stringPropertyToDouble(gesundheitProperty) * 0.95));
                zeitProperty.setValue(doubleToString(stringPropertyToDouble(zeitProperty) - 1));
                geldProperty.setValue(doubleToString(stringPropertyToDouble(geldProperty) - stringPropertyToDouble(gehaltProperty) - 500));

                break;
            }
            case "Gehalt": {
                motivationProperty.setValue(doubleToString(stringPropertyToDouble(motivationProperty) + 1));
                geldProperty.setValue(doubleToString(stringPropertyToDouble(geldProperty) - stringPropertyToDouble(gehaltProperty)));

                break;
            }
            case "Urlaub": {
                geldProperty.setValue(doubleToString(stringPropertyToDouble(geldProperty) - stringPropertyToDouble(gehaltProperty) - 500));
                motivationProperty.setValue(doubleToString(stringPropertyToDouble(motivationProperty) + 1));
                gesundheitProperty.setValue(doubleToString(stringPropertyToDouble(gesundheitProperty) + 1));

                break;
            }
        }
    }

    public double stringPropertyToDouble(StringProperty stringProperty) {
        return new Double(stringProperty.get());
    }

    public String doubleToString(double value) {
        return (new Double(value)).toString();
    }
}
