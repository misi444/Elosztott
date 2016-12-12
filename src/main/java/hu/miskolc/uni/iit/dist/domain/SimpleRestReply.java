package hu.miskolc.uni.iit.dist.domain;

public class SimpleRestReply
{
	private boolean isSuccess;
	
	public SimpleRestReply()
	{
	}

	public SimpleRestReply(boolean isSuccess)
	{
		super();
		this.isSuccess = isSuccess;
	}

	public boolean isSuccess()
	{
		return isSuccess;
	}

	public SimpleRestReply setSuccess(boolean isSuccess)
	{
		this.isSuccess = isSuccess;
		return this;
	}
}