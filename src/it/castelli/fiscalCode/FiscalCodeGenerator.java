package it.castelli.fiscalCode;

import it.castelli.CSVParser;
import it.castelli.dateSystem.Date;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FiscalCodeGenerator
{
	private final static String ITALY_CODE_FILE_PATH = "res/italianCodes.csv";
	private final static String FOREIGN_CODE_FILE_PATH = "res/foreignCodes.csv";
	private final static int ITALIAN_MUNICIPALITY_NAME_INDEX = 6;
	private final static int ITALIAN_MUNICIPALITY_CODE_INDEX = 19;
	private final static int FOREIGN_MUNICIPALITY_NAME_INDEX = 6;
	private final static int FOREIGN_MUNICIPALITY_CODE_INDEX = 9;

	private static final HashMap<Integer, Character> monthLetterTable = new HashMap<>();
	private final static HashMap<String, String> italianMunicipalityCodes, foreignMunicipalityCodes;
	static
	{
		// map initialization
		monthLetterTable.put(1, 'A');
		monthLetterTable.put(2, 'B');
		monthLetterTable.put(3, 'C');
		monthLetterTable.put(4, 'D');
		monthLetterTable.put(5, 'E');
		monthLetterTable.put(6, 'H');
		monthLetterTable.put(7, 'L');
		monthLetterTable.put(8, 'M');
		monthLetterTable.put(9, 'P');
		monthLetterTable.put(10, 'R');
		monthLetterTable.put(11, 'S');
		monthLetterTable.put(12, 'T');

		italianMunicipalityCodes = CSVParser.arrayListToHashMap(CSVParser.getColumns(ITALY_CODE_FILE_PATH,
		                                                                             ITALIAN_MUNICIPALITY_NAME_INDEX,
		                                                                             ITALIAN_MUNICIPALITY_CODE_INDEX));
		foreignMunicipalityCodes = CSVParser.arrayListToHashMap(CSVParser.getColumns(FOREIGN_CODE_FILE_PATH,
		                                                                             FOREIGN_MUNICIPALITY_NAME_INDEX,
		                                                                             FOREIGN_MUNICIPALITY_CODE_INDEX));
	}

	public static String getFiscalCode(PhysicalPerson person)
	{
		return getLastName(person.getLastName()) +
		       getName(person.getFirstName()) +
		       getBirthDay(person.getBirthday(), person.getGender()) +
		       getBirthMunicipality(person.getBirthMunicipality()) +
		       getControlCode(person);
	}

	private static String getLastName(String lastName)
	{
		// find the first consonants
		Pattern pattern = Pattern.compile("[^aeiou\\d\\s]");
		Matcher matcher = pattern.matcher(lastName.toLowerCase());

		char[] resultArray = new char[3];
		int index = 0;
		while (matcher.find())
		{
			if (index >= 3)
				break;

			resultArray[index++] = matcher.group().toCharArray()[0];
		}

		// find vocals if consonants are less than 3
		pattern = Pattern.compile("[aeiou]");
		matcher = pattern.matcher(lastName.toLowerCase());

		while (matcher.find())
		{
			if (index >= 3)
				break;

			resultArray[index++] = matcher.group().toCharArray()[0];
		}

		// if the last name is too short, add "x"s
		while (index < 3)
			resultArray[index++] = 'x';

		return new String(resultArray);
	}

	private static String getName(String person)
	{
		// find the first consonants
		Pattern pattern = Pattern.compile("[^aeiou\\d\\s]");
		Matcher matcher = pattern.matcher(person.toLowerCase());

		char[] resultArray = new char[4];
		boolean fourthConsonantExists = false;
		int index = 0;

		while (matcher.find())
		{
			if (index >= 4)
			{
				fourthConsonantExists = true;
				break;
			}

			resultArray[index++] = matcher.group().toCharArray()[0];
		}

		// find vocals if consonants are less than 3
		pattern = Pattern.compile("[aeiou]");
		matcher = pattern.matcher(person.toLowerCase());

		while (matcher.find())
		{
			if (index >= 3)
			{
				break;
			}

			resultArray[index++] = matcher.group().toCharArray()[0];
		}

		// if the last name is too short, add "x"s
		while (index < 3)
		{
			resultArray[index++] = 'x';
		}

		return new String(new char[]
				                  {
				                  		resultArray[0],
						                  resultArray[fourthConsonantExists ? 2 : 1],
						                  resultArray[fourthConsonantExists ? 3 : 2]
				                  });
	}

	private static String getBirthDay(Date birthday, Gender gender)
	{
		return String.format("%02d", birthday.getYear() % 100) +
		       monthLetterTable.get(birthday.getMonth()) +
		       (birthday.getDay() + gender.getAddedDays());
	}

	private static String getBirthMunicipality(String municipality)
	{
		System.out.println(italianMunicipalityCodes.get(municipality));
		return "";
	}

	private static String getControlCode(PhysicalPerson person)
	{
		return "";
	}
}
