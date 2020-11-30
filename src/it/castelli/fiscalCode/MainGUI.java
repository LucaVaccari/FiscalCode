package it.castelli.fiscalCode;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainGUI extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		HBox hBox = new HBox();
		Button generateButton = new Button("GENERATE!");
		Label label = new Label("");
		VBox vBox = new VBox(hBox, generateButton, label);

		Scene mainScene = new Scene(vBox);

		stage.setTitle("Fiscal Code Generator - Luca Vaccari - 2020");

		stage.setScene(mainScene);
		stage.show();
	}
}
