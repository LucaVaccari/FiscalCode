import java.util.Date;

public class MainConsole
{
	public static void main(String[] args)
	{
		System.out.println(
				FiscalCodeGenerator.getName(
						new PhysicalPerson("Nicola",
						                   "De Rossi",
						                   new Date(),
						                   Gender.FEMALE,
						                   "Anguria")));
	}
}
