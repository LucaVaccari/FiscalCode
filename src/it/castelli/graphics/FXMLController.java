package it.castelli.graphics;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable
{
	public ComboBox dayCombo;
	public ComboBox monthCombo;
	public ComboBox yearCombo;
	public TextField firstName;
	public TextField lastName;
	public TextField birthMunicipalityTextField;
	public ComboBox genderCombo;
	public Button buttonGenerator;
	public Label fiscalCodeLabel;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle)
	{

	}
}
