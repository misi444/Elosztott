package hu.miskolc.uni.iit.dist.domain.converter;

import java.beans.PropertyEditorSupport;

import hu.miskolc.uni.iit.dist.domain.Qualification;

public class QualificationEnumConverter extends PropertyEditorSupport
{

	@Override
	public void setAsText(String text) throws IllegalArgumentException
	{
		if ("highschool".equalsIgnoreCase(text))
		{
			setValue(Qualification.HIGHSCHOOL);
			return;
		}
		if ("college".equalsIgnoreCase(text))
		{
			setValue(Qualification.COLLEGE);
			return;
		}
		if ("university".equalsIgnoreCase(text))
		{
			setValue(Qualification.UNIVERSITY);
			return;
		}
	}

}
