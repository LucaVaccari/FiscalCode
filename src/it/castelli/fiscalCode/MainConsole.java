package it.castelli.fiscalCode;


import it.castelli.dateSystem.Date;

public class MainConsole
{
	public static void main(String[] args)
	{
		System.out.println(
				FiscalCodeGenerator.getFiscalCode(
						new PhysicalPerson("Luca",
						                   "Vaccari",
						                   new Date(28, 8, 2003),
						                   Gender.MALE,
						                   "Brescia")));
	}
}
