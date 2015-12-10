package de.hszg.ProjectSimulator;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by Andre on 26.11.2015.
 */
public class MainView implements FxmlView<MainViewModel>, Initializable {

    @FXML
    public TextField txtFieldZeitEingabe;
    @FXML
    public TextField txtFieldGeldEingabe;
    @FXML
    public Button buttonStart;
    @FXML
    public Label labelQualitaet;
    @FXML
    public Label labelZeit;
    @FXML
    public Label labelGeld;
    @FXML
    public Label labelGesundheit;
    @FXML
    public Label labelSkill;
    @FXML
    public Label labelMotivation;
    @FXML
    public Label labelGehalt;
    @FXML
    public Slider SliderGehalt;
    @FXML
    public Button buttonNachsteRunde;
    @FXML
    public Label labelNeuesGehalt;
    @FXML
    public ToggleGroup ToggleGroupSelect;

    @InjectViewModel
    private MainViewModel viewModel;

    public void initialize(URL location, ResourceBundle resources) {
        txtFieldGeldEingabe.textProperty().bindBidirectional(viewModel.geldEingabe());
        txtFieldZeitEingabe.textProperty().bindBidirectional(viewModel.zeitEinagabe());
        labelQualitaet.textProperty().bindBidirectional(viewModel.qualitaetText());
        labelZeit.textProperty().bindBidirectional(viewModel.zeitText());
        labelSkill.textProperty().bindBidirectional(viewModel.skillText());
        labelGehalt.textProperty().bindBidirectional(viewModel.gehaltText());
        labelGeld.textProperty().bindBidirectional(viewModel.geldText());
        labelGesundheit.textProperty().bindBidirectional(viewModel.gesundheitText());
        labelMotivation.textProperty().bindBidirectional(viewModel.motivationText());
        SliderGehalt.valueProperty().bindBidirectional(viewModel.gehaltProperty());
        SliderGehalt.setValue(1500);
        SliderGehalt.setShowTickMarks(true);
        SliderGehalt.setMajorTickUnit(100);
        SliderGehalt.setMinorTickCount(0);
        SliderGehalt.setBlockIncrement(100);
        SliderGehalt.setSnapToTicks(true);

        labelNeuesGehalt.textProperty().bind(SliderGehalt.valueProperty().asString("%.0f"));

        buttonStart.setOnAction(event -> {
            buttonNachsteRunde.setDisable(false);
            buttonStart.setDisable(true);
            txtFieldGeldEingabe.setDisable(true);
            txtFieldZeitEingabe.setDisable(true);
            viewModel.startRound();
        });

        buttonNachsteRunde.setOnAction(event -> {
            RadioButton rb = (RadioButton) ToggleGroupSelect.getSelectedToggle();
            viewModel.nextRound(rb.getText());
        });
    }


}
