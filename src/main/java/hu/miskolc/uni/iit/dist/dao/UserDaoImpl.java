package hu.miskolc.uni.iit.dist.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import hu.miskolc.uni.iit.dist.domain.User;
import hu.miskolc.uni.iit.dist.exception.InvalidParameterException;

public class UserDaoImpl implements UserDao
{
	private Map<String, User> userMap = new HashMap<>();
	private Object lock = new Object();

	@Override
	public User findUserByName(String userName)
	{
		synchronized (lock)
		{
			return userMap.get(userName);
		}
	}

	@Override
	public void storeUser(User user)
	{
		synchronized (lock)
		{
			userMap.put(user.getUserName(), user);
		}
	}

	@Override
	public Collection<User> getUsers()
	{
		synchronized (lock)
		{
			return userMap.values();
		}
	}

	@Override
	public void deleteUser(String userId) throws InvalidParameterException
	{
		synchronized (lock)
		{
			Iterator<User> iter = userMap.values().iterator();
			while(iter.hasNext())
			{
				if(iter.next().getUserId().equals(userId))
				{
					iter.remove();
					return;
				}
			}
		}
		throw new InvalidParameterException("WARNING: No user found with given id("+userId+").");
	}
}