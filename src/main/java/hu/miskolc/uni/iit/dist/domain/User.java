package hu.miskolc.uni.iit.dist.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class User 
{
	@NotBlank
	private String userId;
	
	@NotBlank
	@Length(min = 1, max = 20)
	private String userName;

	@NotBlank
	@Length(min = 1, max = 50)
	private String name;
	
	@NotEmpty
	@NotBlank
	@Pattern(regexp = "[0-9]{1,10}")
	private String creditBalance;
	
	@NotNull
	private Qualification qualification;
	
	@NotNull
	private Gender gender;
	
	@NotEmpty
	private List<String> favouriteColor;
	
	public User()
	{
		this.userId = UUID.randomUUID().toString();
	}

	public String getUserId()
	{
		return userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public User setName(String name)
	{
		this.name = name;
		return this;
	}

	public String getName()
	{
		return name;
	}

	public User setUserName(String userName)
	{
		this.userName = userName;
		return this;
	}

	public String getCreditBalance()
	{
		return creditBalance;
	}

	public User setCreditBalance(String creditBalance)
	{
		this.creditBalance = creditBalance;
		return this;
	}

	public Qualification getQualification()
	{
		return qualification;
	}

	public User setQualification(Qualification qualification)
	{
		this.qualification = qualification;
		return this;
	}

	public Gender getGender()
	{
		return gender;
	}

	public User setGender(Gender gender)
	{
		this.gender = gender;
		return this;
	}

	public List<String> getFavouriteColor()
	{
		if(favouriteColor == null)
		{
			favouriteColor = new ArrayList<>();
		}
		return favouriteColor;
	}

	public User setFavouriteColor(List<String> favouriteColor)
	{
		this.favouriteColor = favouriteColor;
		return this;
	}

	@Override
	public String toString()
	{
		return "User [userId=" + userId + ", userName=" + userName + ", creditBalance=" + creditBalance
				+ ", qualification=" + qualification + ", gender=" + gender + ", favouriteColor=" + favouriteColor
				+ "]";
	}
}