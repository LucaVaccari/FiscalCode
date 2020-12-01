package it.castelli.fiscalCode;

public enum Gender
{
	MALE, FEMALE;

	public int getAddedDays()
	{
		return this.equals(Gender.MALE) ? 0 : 30;
	}
}
