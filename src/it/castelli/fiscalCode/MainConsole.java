package it.castelli.fiscalCode;

import java.util.Date;

public class MainConsole
{
	public static void main(String[] args)
	{
		System.out.println(
				FiscalCodeGenerator.getFiscalCode(
						new PhysicalPerson("Nicola",
						                   "De Rossi",
						                   new Date(),
						                   Gender.FEMALE,
						                   "Anguria")));
	}
}
