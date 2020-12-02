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
	private final static HashMap<Character, Integer> oddAlphanumericCharactersTable, evenAlphanumericCharactersTable;
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

		oddAlphanumericCharactersTable = new HashMap<>();
		evenAlphanumericCharactersTable = new HashMap<>();

		oddAlphanumericCharactersTable.put('0', 1);
		oddAlphanumericCharactersTable.put('1', 0);
		oddAlphanumericCharactersTable.put('2', 5);
		oddAlphanumericCharactersTable.put('3', 7);
		oddAlphanumericCharactersTable.put('4', 9);
		oddAlphanumericCharactersTable.put('5', 13);
		oddAlphanumericCharactersTable.put('6', 15);
		oddAlphanumericCharactersTable.put('7', 17);
		oddAlphanumericCharactersTable.put('8', 19);
		oddAlphanumericCharactersTable.put('9', 21);
		oddAlphanumericCharactersTable.put('a', 1);
		oddAlphanumericCharactersTable.put('b', 0);
		oddAlphanumericCharactersTable.put('c', 5);
		oddAlphanumericCharactersTable.put('d', 7);
		oddAlphanumericCharactersTable.put('e', 9);
		oddAlphanumericCharactersTable.put('f', 13);
		oddAlphanumericCharactersTable.put('g', 15);
		oddAlphanumericCharactersTable.put('h', 17);
		oddAlphanumericCharactersTable.put('i', 19);
		oddAlphanumericCharactersTable.put('j', 21);
		oddAlphanumericCharactersTable.put('k', 2);
		oddAlphanumericCharactersTable.put('l', 4);
		oddAlphanumericCharactersTable.put('m', 18);
		oddAlphanumericCharactersTable.put('n', 20);
		oddAlphanumericCharactersTable.put('o', 11);
		oddAlphanumericCharactersTable.put('p', 3);
		oddAlphanumericCharactersTable.put('q', 6);
		oddAlphanumericCharactersTable.put('r', 8);
		oddAlphanumericCharactersTable.put('s', 2);
		oddAlphanumericCharactersTable.put('t', 14);
		oddAlphanumericCharactersTable.put('u', 16);
		oddAlphanumericCharactersTable.put('v', 10);
		oddAlphanumericCharactersTable.put('w', 22);
		oddAlphanumericCharactersTable.put('x', 25);
		oddAlphanumericCharactersTable.put('y', 24);
		oddAlphanumericCharactersTable.put('z', 23);

		evenAlphanumericCharactersTable.put('0', 0);
		evenAlphanumericCharactersTable.put('1', 1);
		evenAlphanumericCharactersTable.put('2', 2);
		evenAlphanumericCharactersTable.put('3', 3);
		evenAlphanumericCharactersTable.put('4', 4);
		evenAlphanumericCharactersTable.put('5', 5);
		evenAlphanumericCharactersTable.put('6', 6);
		evenAlphanumericCharactersTable.put('7', 7);
		evenAlphanumericCharactersTable.put('8', 8);
		evenAlphanumericCharactersTable.put('9', 9);
		evenAlphanumericCharactersTable.put('a', 0);
		evenAlphanumericCharactersTable.put('b', 1);
		evenAlphanumericCharactersTable.put('c', 2);
		evenAlphanumericCharactersTable.put('d', 3);
		evenAlphanumericCharactersTable.put('e', 4);
		evenAlphanumericCharactersTable.put('f', 5);
		evenAlphanumericCharactersTable.put('g', 6);
		evenAlphanumericCharactersTable.put('h', 7);
		evenAlphanumericCharactersTable.put('i', 8);
		evenAlphanumericCharactersTable.put('j', 9);
		evenAlphanumericCharactersTable.put('k', 10);
		evenAlphanumericCharactersTable.put('l', 11);
		evenAlphanumericCharactersTable.put('m', 12);
		evenAlphanumericCharactersTable.put('n', 13);
		evenAlphanumericCharactersTable.put('o', 14);
		evenAlphanumericCharactersTable.put('p', 15);
		evenAlphanumericCharactersTable.put('q', 16);
		evenAlphanumericCharactersTable.put('r', 17);
		evenAlphanumericCharactersTable.put('s', 18);
		evenAlphanumericCharactersTable.put('t', 19);
		evenAlphanumericCharactersTable.put('u', 20);
		evenAlphanumericCharactersTable.put('v', 21);
		evenAlphanumericCharactersTable.put('w', 22);
		evenAlphanumericCharactersTable.put('x', 23);
		evenAlphanumericCharactersTable.put('y', 24);
		evenAlphanumericCharactersTable.put('z', 25);
	}

	public static String getFiscalCode(PhysicalPerson person)
	{
		String partialFiscalCode = getPartialFiscalCode(person).toLowerCase();
		return partialFiscalCode + getControlCode(partialFiscalCode);
	}

	private static String getPartialFiscalCode(PhysicalPerson person)
	{
		return getLastName(person.getLastName()) +
		       getName(person.getFirstName()) +
		       getBirthDay(person.getBirthday(), person.getGender()) +
		       getBirthMunicipality(person.getBirthMunicipality());
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
		String municipalityCode = italianMunicipalityCodes.get(municipality);
		return municipalityCode == null ? foreignMunicipalityCodes.get(municipality) : municipalityCode;
	}

	private static String getControlCode(String partialFC)
	{
		int sum = 0;
		for (int i = 0; i < partialFC.length(); i++)
		{
			if ((i + 1) % 2 == 0)
				sum += evenAlphanumericCharactersTable.get(partialFC.charAt(i));
			else
				sum += oddAlphanumericCharactersTable.get(partialFC.charAt(i));
		}

		return String.valueOf((char)(sum % 26 + 'a'));
	}
}
