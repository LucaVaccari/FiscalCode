package it.castelli.dateSystem;

public class Date
{
	int day, month, year;

	public Date(int day, int month, int year)
	{
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay()
	{
		return day;
	}

	public void setDay(byte day)
	{
		this.day = day;
	}

	public int getMonth()
	{
		return month;
	}

	public void setMonth(byte month)
	{
		this.month = month;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}
}
