import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FiscalCodeGenerator
{
	public static String getFiscalCode(PhysicalPerson person)
	{
		return getLastName(person) +
		       getName(person) +
		       getBirthDay(person) +
		       getGender(person) +
		       getBirthMunicipality(person) +
		       getControlCode(person);
	}

	public static String getLastName(PhysicalPerson person)
	{
		// find the first consonants
		Pattern pattern = Pattern.compile("[^aeiou\\d\\s]");
		Matcher matcher = pattern.matcher(person.getLastName().toLowerCase());

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
		matcher = pattern.matcher(person.getLastName().toLowerCase());

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

	public static String getName(PhysicalPerson person)
	{
		// find the first consonants
		Pattern pattern = Pattern.compile("[^aeiou\\d\\s]");
		Matcher matcher = pattern.matcher(person.getFirstName().toLowerCase());

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
		matcher = pattern.matcher(person.getFirstName().toLowerCase());

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

	private static String getBirthDay(PhysicalPerson person)
	{
		return null;
	}

	private static String getGender(PhysicalPerson person)
	{
		return null;
	}

	private static String getBirthMunicipality(PhysicalPerson person)
	{
		return null;
	}

	private static String getControlCode(PhysicalPerson person)
	{
		return null;
	}
}
