package de.hszg.ProjectSimulator;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXMessageDialog;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by Andre on 26.11.2015.
 */
public class MainViewModel implements ViewModel{

    private StringProperty geldEingabeProperty = new SimpleStringProperty("");
    private StringProperty zeitEingabeProperty = new SimpleStringProperty("");
    private StringProperty qualitaetProperty = new SimpleStringProperty("0");
    private StringProperty zeitProperty = new SimpleStringProperty("0");
    private StringProperty skillProperty = new SimpleStringProperty("5.0");
    private StringProperty gehaltProperty = new SimpleStringProperty("1500.0");
    private StringProperty geldProperty = new SimpleStringProperty("0");
    private StringProperty gesundheitProperty = new SimpleStringProperty("10.0");
    private StringProperty motivationProperty = new SimpleStringProperty("5.0");
    private DoubleProperty gehaltWertProperty = new SimpleDoubleProperty();

    private Button buttonStart;
    private Button buttonNaechsteRunde;
    private TextField txtFieldZeitEingabe;
    private TextField txtFieldGeldEingabe;

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

    public void setButtonStart(Button buttonStart) {
        this.buttonStart = buttonStart;
    }

    public void setButtonNaechsteRunde(Button buttonNaechsteRunde) {
        this.buttonNaechsteRunde = buttonNaechsteRunde;
    }

    public void setTxtFieldZeitEingabe(TextField txtFieldZeitEingabe) {
        this.txtFieldZeitEingabe = txtFieldZeitEingabe;
    }

    public void setTxtFieldGeldEingabe(TextField txtFieldGeldEingabe) {
        this.txtFieldGeldEingabe = txtFieldGeldEingabe;
    }

    public void startRound() {
        if(zeitEinagabe().getValue().equals("") || geldEingabe().getValue().equals("")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setContentText("Du musst ein Startkapital und eine Entwicklungsdauer festlegen!");

            alert.showAndWait();

            resetUI();
            return;
        }

        zeitProperty.setValue(zeitEinagabe().getValue());
        geldProperty.setValue(geldEingabe().getValue());
        magicValue = (stringPropertyToDouble(zeitProperty) * 1.5) / stringPropertyToDouble(zeitProperty);
    }

    public void nextRound(String text) {
        switch (text) {
            case "Entwickeln": {
                motivationProperty.setValue(doubleToString(stringPropertyToDouble(motivationProperty) - 1));
                gesundheitProperty.setValue(doubleToString(stringPropertyToDouble(gesundheitProperty) - 1));
                zeitProperty.setValue(doubleToString(stringPropertyToDouble(zeitProperty) - 1));
                geldProperty.setValue(doubleToString(stringPropertyToDouble(geldProperty) - stringPropertyToDouble(gehaltProperty)));

                qualitaetProperty.setValue(doubleToString(stringPropertyToDouble(qualitaetProperty) + (((stringPropertyToDouble(skillProperty) + stringPropertyToDouble(motivationProperty) + stringPropertyToDouble(gesundheitProperty)) * 4) / 100) * magicValue));
                break;
            }
            case "Training": {
                if (stringPropertyToDouble(skillProperty) <= 9) {
                    skillProperty.setValue(doubleToString(stringPropertyToDouble(skillProperty) + 1));
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Information");
                    alert.setHeaderText("Das war nicht gerade schlau!");
                    alert.setContentText("Mehr Skill können deine Mitarbeiter nicht haben!");

                    alert.showAndWait();
                }
                gesundheitProperty.setValue(doubleToString(stringPropertyToDouble(gesundheitProperty) - 1));
                zeitProperty.setValue(doubleToString(stringPropertyToDouble(zeitProperty) - 1));
                geldProperty.setValue(doubleToString(stringPropertyToDouble(geldProperty) - stringPropertyToDouble(gehaltProperty) - 500));

                break;
            }
            case "Gehalt": {
                geldProperty.setValue(doubleToString(stringPropertyToDouble(geldProperty) - stringPropertyToDouble(gehaltProperty)));


                double vorher = stringPropertyToDouble(gehaltProperty);
                gehaltProperty.setValue(gehaltWertProperty.getValue().toString());

                if (stringPropertyToDouble(gehaltProperty) - vorher <= 0) {
                    motivationProperty.setValue(doubleToString(stringPropertyToDouble(motivationProperty) - 1));
                } else if (stringPropertyToDouble(motivationProperty) <= 9) {
                    motivationProperty.setValue(doubleToString(stringPropertyToDouble(motivationProperty) + 1));
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Information");
                    alert.setHeaderText("Das war nicht gerade schlau!");
                    alert.setContentText("Motivierter könnten deine Mitarbeiter nicht sein!");

                    alert.showAndWait();
                }

                zeitProperty.setValue(doubleToString(stringPropertyToDouble(zeitProperty) - 1));
                break;
            }
            case "Urlaub": {
                geldProperty.setValue(doubleToString(stringPropertyToDouble(geldProperty) - stringPropertyToDouble(gehaltProperty)));

                if (stringPropertyToDouble(motivationProperty) <= 9) {
                    motivationProperty.setValue(doubleToString(stringPropertyToDouble(motivationProperty) + 1));
                }
                if (stringPropertyToDouble(gesundheitProperty) <= 9) {
                    gesundheitProperty.setValue(doubleToString(stringPropertyToDouble(gesundheitProperty) + 1));
                }

                zeitProperty.setValue(doubleToString(stringPropertyToDouble(zeitProperty) - 1));
                break;
            }
        }

        testConstraints();
    }

    private void testConstraints() {
        if (stringPropertyToDouble(gesundheitProperty) <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("x_x");
            alert.setHeaderText("Das war ein Fehler");
            alert.setContentText("Deine Mitarbeiter sind tot: Gehe ins Gefängnis, gehe nicht über Los");

            alert.showAndWait();

            resetUI();
        }

        if (stringPropertyToDouble(motivationProperty) <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(";(");
            alert.setHeaderText("Das war ein Fehler");
            alert.setContentText("Deine Mitarbeiter haben keine Lust mehr und sind über Nacht ausgewandert!");

            alert.showAndWait();

            resetUI();
        }

        if (stringPropertyToDouble(geldProperty) < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(";(");
            alert.setHeaderText("Verloren");
            alert.setContentText("Leider ist das Geld alle, jetzt gehört die Firma mir!");

            alert.showAndWait();

            resetUI();
            return;
        }

        if (stringPropertyToDouble(qualitaetProperty) >= 10) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(":)");
            alert.setHeaderText("Gewonnen");
            alert.setContentText("Das gelingt nur den wenigsten!");

            alert.showAndWait();

            resetUI();
        } else if (stringPropertyToDouble(zeitProperty) <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(";(");
            alert.setHeaderText("Deine Zeit ist abgelaufen");
            alert.setContentText("Das war der letzte Kunde der dir einen Auftrag gegeben hat, nun musst du die Firma wohl schließen!");

            alert.showAndWait();

            resetUI();
        }

    }

    private void resetUI() {
        buttonStart.setDisable(false);
        buttonNaechsteRunde.setDisable(true);
        txtFieldGeldEingabe.setDisable(false);
        txtFieldZeitEingabe.setDisable(false);

        qualitaetProperty.setValue("0");
        motivationProperty.setValue("5");
        gesundheitProperty.setValue("10");
        skillProperty.setValue("5");
        gehaltProperty.setValue("1500");
    }

    public double stringPropertyToDouble(StringProperty stringProperty) {
        return new Double(stringProperty.get());
    }

    public String doubleToString(double value) {
        return (new Double(value)).toString();
    }
}
