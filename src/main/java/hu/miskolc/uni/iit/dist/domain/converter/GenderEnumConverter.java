package hu.miskolc.uni.iit.dist.domain.converter;

import java.beans.PropertyEditorSupport;

import hu.miskolc.uni.iit.dist.domain.Gender;

public class GenderEnumConverter extends PropertyEditorSupport
{

	@Override
	public void setAsText(String text) throws IllegalArgumentException
	{
		if ("male".equalsIgnoreCase(text))
		{
			setValue(Gender.MALE);
			return;
		}
		if ("female".equalsIgnoreCase(text))
		{
			setValue(Gender.FEMALE);
			return;
		}
	}

}
