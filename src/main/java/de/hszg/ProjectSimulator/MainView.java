package de.hszg.ProjectSimulator;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
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

        buttonStart.setOnAction(event -> viewModel.startRound());
        buttonNachsteRunde.setOnAction(event -> viewModel.nextRound());
    }


}
