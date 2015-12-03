package de.hszg.ProjectSimulator;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends MvvmfxCdiApplication {

	public static void main(String[] args) {
		launch(args);
	}

	public void startMvvmfx(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Project-Simulator");

		ViewTuple<MainView, MainViewModel> viewTuple = FluentViewLoader.fxmlView(MainView.class).load();

		Parent root = viewTuple.getView();
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}
