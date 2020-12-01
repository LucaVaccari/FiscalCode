package it.castelli.fiscalCode;


import it.castelli.dateSystem.Date;

public class MainConsole
{
	public static void main(String[] args)
	{
		System.out.println(
				FiscalCodeGenerator.getFiscalCode(
						new PhysicalPerson("Nicola",
						                   "De Rossi",
						                   new Date(10, 1, 2003),
						                   Gender.FEMALE,
						                   "Anguria")));
	}
}
