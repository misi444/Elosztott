package hu.miskolc.uni.iit.dist.domain;

import java.util.ArrayList;
import java.util.List;

public class UserPreferencePreloadReply
{
	private List<FavoriteColorType> favoriteColorType;
	private List<QualificationType> qualificationType;

	public UserPreferencePreloadReply()
	{
	}

	public UserPreferencePreloadReply(List<FavoriteColorType> favoriteColorType,
			List<QualificationType> qualificationType)
	{
		super();
		this.favoriteColorType = favoriteColorType;
		this.qualificationType = qualificationType;
	}

	public List<FavoriteColorType> getFavoriteColorType()
	{
		if(this.favoriteColorType == null)
		{
			this.favoriteColorType = new ArrayList<>();
		}
		return favoriteColorType;
	}

	public void setFavoriteColorType(List<FavoriteColorType> favoriteColorType)
	{
		this.favoriteColorType = favoriteColorType;
	}

	public List<QualificationType> getQualificationType()
	{
		if(this.qualificationType == null)
		{
			this.qualificationType = new ArrayList<>();
		}
		return qualificationType;
	}

	public void setQualificationType(List<QualificationType> qualificationType)
	{
		this.qualificationType = qualificationType;
	}
}