package it.castelli.graphics;

import it.castelli.dateSystem.Date;
import it.castelli.fiscalCode.FiscalCodeGenerator;
import it.castelli.fiscalCode.Gender;
import it.castelli.fiscalCode.PhysicalPerson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

public class FXMLController implements Initializable
{
	public TextField firstNameTextField;
	public TextField lastNameTextField;
	public TextField birthMunicipalityTextField;
	public Button buttonGenerator;
	public Label fiscalCodeLabel;
	public ChoiceBox<String> genderChoice;
	public TextField yearTextField;
	public ChoiceBox<Integer> dayChoiceBox;
	public ChoiceBox<String> monthChoiceBox;

	private final LinkedHashMap<String, Integer> numberOfDaysPerMonth = new LinkedHashMap<>();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle)
	{
		numberOfDaysPerMonth.put("Gennaio", 31);
		numberOfDaysPerMonth.put("Febbraio", 28);
		numberOfDaysPerMonth.put("Marzo", 31);
		numberOfDaysPerMonth.put("Aprile", 30);
		numberOfDaysPerMonth.put("Maggio", 31);
		numberOfDaysPerMonth.put("Giugno", 30);
		numberOfDaysPerMonth.put("Luglio", 31);
		numberOfDaysPerMonth.put("Agosto", 31);
		numberOfDaysPerMonth.put("Settembre", 30);
		numberOfDaysPerMonth.put("Ottobre", 31);
		numberOfDaysPerMonth.put("Novembre", 30);
		numberOfDaysPerMonth.put("Dicembre", 31);

		dayChoiceBox.setValue(1);
		monthChoiceBox.getItems().addAll(numberOfDaysPerMonth.keySet());
		monthChoiceBox.setValue("Gennaio");
		yearTextField.setText("2020");

		monthChoiceBox.setOnAction((ActionEvent evt) -> refreshDate());
		yearTextField.setOnKeyTyped(e -> refreshDate());

		refreshDate();

		genderChoice.getItems().addAll("Maschio", "Femmina", "Altro...");
		genderChoice.setValue("Maschio");
	}

	private void refreshDate()
	{
		if (monthChoiceBox.getValue().equals("Febbraio"))
		{
			int year = Integer.parseInt(yearTextField.getText());
			// bool == bool : xnor
			boolean leapYear = year % 4 == 0 && (year % 100 == 0) == (year % 400 == 0);
			numberOfDaysPerMonth.put("Febbraio", leapYear ? 29 : 28);
		}

		int day = dayChoiceBox.getValue();
		dayChoiceBox.getItems().clear();
		for (int i = 1; i <= numberOfDaysPerMonth.get(monthChoiceBox.getValue()); i++)
		{
			dayChoiceBox.getItems().add(i);
		}
		dayChoiceBox.setValue(dayChoiceBox.getItems().contains(day) ? day : 1);
	}

	@FXML
	public void generateFiscalCode()
	{
		String errorStack = "";
		String fiscalCode = "";

		String firstName = firstNameTextField.getText();
		String lastName = lastNameTextField.getText();
		int day = dayChoiceBox.getValue();
		String month = monthChoiceBox.getValue();
		int year = 2000;
		try
		{
			year = Integer.parseInt(yearTextField.getText());
		}
		catch (Exception e)
		{
			errorStack += "L'anno deve essere un numero! PIRLA!!!\n";
		}

		String gender = genderChoice.getValue();
		switch (gender)
		{
			case "Altro..." -> errorStack += "Guardati nei pantaloni...\n";
			case "Maschio" -> gender = "MALE";
			case "Femmina" -> gender = "FEMALE";
		}

		String municipalityCode = birthMunicipalityTextField.getText();


		if (errorStack.equals(""))
		{
			Date birthDay = new Date(day, new ArrayList<>(numberOfDaysPerMonth.keySet()).indexOf(month) + 1, year);

			// 5/10/1582 - 14/10/1582
			if (birthDay.getDay() >= 5 && birthDay.getDay() <= 14 && birthDay.getMonth() == 10 && birthDay.getYear() == 1582)
				errorStack += "Questa data non è mai esistita nella storia!\n";

			fiscalCode = FiscalCodeGenerator.getFiscalCode(new PhysicalPerson(
					firstName, lastName, birthDay, Gender.valueOf(gender), municipalityCode
			));
		}

		errorStack += FiscalCodeGenerator.getLastError();

		fiscalCodeLabel.setText(errorStack.equals("") ? fiscalCode.toUpperCase() : errorStack);
	}
}
