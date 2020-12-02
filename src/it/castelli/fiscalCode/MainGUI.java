package it.castelli.fiscalCode;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainGUI extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		File fxmlFile = new File("res/Graphics/index.fxml");
		loader.setLocation(fxmlFile.toURI().toURL());

		VBox vBox = loader.<VBox>load();
		Scene mainScene = new Scene(vBox);

		stage.setTitle("Fiscal Code Generator - Luca Vaccari - Nicola Vivante - 2020");

		stage.setScene(mainScene);
		stage.show();
	}
}
