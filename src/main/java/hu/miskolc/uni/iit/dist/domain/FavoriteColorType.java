package hu.miskolc.uni.iit.dist.domain;

public class FavoriteColorType
{
	private String colorCode;
	private String colorValue;
	private boolean enabled;

	public FavoriteColorType()
	{
	}
	
	public FavoriteColorType(String colorCode, String colorValue, boolean enabled)
	{
		super();
		this.colorCode = colorCode;
		this.colorValue = colorValue;
		this.enabled = enabled;
	}

	public String getColorCode()
	{
		return colorCode;
	}
	public void setColorCode(String colorCode)
	{
		this.colorCode = colorCode;
	}
	public String getColorValue()
	{
		return colorValue;
	}
	public void setColorValue(String colorValue)
	{
		this.colorValue = colorValue;
	}
	public boolean isEnabled()
	{
		return enabled;
	}
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
}