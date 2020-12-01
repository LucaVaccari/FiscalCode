package it.castelli.fiscalCode;

import it.castelli.dateSystem.Date;

public class PhysicalPerson
{
	private String firstName, lastName;
	private Date birthday;
	private Gender gender;
	private String birthMunicipality;

	public PhysicalPerson(String firstName, String lastName, Date birthday, Gender gender, String birthMunicipality)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.gender = gender;
		this.birthMunicipality = birthMunicipality;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public Gender getGender()
	{
		return gender;
	}

	public void setGender(Gender gender)
	{
		this.gender = gender;
	}

	public String getBirthMunicipality()
	{
		return birthMunicipality;
	}

	public void setBirthMunicipality(String birthMunicipality)
	{
		this.birthMunicipality = birthMunicipality;
	}
}
