package hu.miskolc.uni.iit.dist.dao;

import java.util.*;

import hu.miskolc.uni.iit.dist.domain.Gender;
import hu.miskolc.uni.iit.dist.domain.Qualification;
import hu.miskolc.uni.iit.dist.domain.User;
import hu.miskolc.uni.iit.dist.exception.InvalidParameterException;

public class UserDaoImpl implements UserDao
{
	private Map<String, User> userMap = new HashMap<>();
	private Object lock = new Object();

	public UserDaoImpl() {
	    User user1 = new User();
	    user1.setName("Glázser Bozsó");
	    user1.setCreditBalance("77");
	    user1.setGender(Gender.MALE);
	    user1.setQualification(Qualification.COLLEGE);
	    user1.setUserName("student1");
	    user1.setFavouriteColor(Arrays.asList("GREEN"));
	    userMap.put(user1.getUserName(), user1);

        User user2 = new User();
        user2.setName("Gipsz Jakab");
        user2.setCreditBalance("82");
        user2.setGender(Gender.FEMALE);
        user2.setQualification(Qualification.HIGHSCHOOL);
        user2.setUserName("student2");
        user2.setFavouriteColor(Arrays.asList("RED"));
        userMap.put(user2.getUserName(), user2);

        User user3 = new User();
        user3.setName("Horváth Péter");
        user3.setCreditBalance("77");
        user3.setGender(Gender.MALE);
        user3.setQualification(Qualification.UNIVERSITY);
        user3.setUserName("teacher1");
        user3.setFavouriteColor(Arrays.asList("GREEN"));
        userMap.put(user3.getUserName(), user3);

        User user4 = new User();
        user4.setName("Teszt Elek");
        user4.setCreditBalance("88");
        user4.setGender(Gender.MALE);
        user4.setQualification(Qualification.UNIVERSITY);
        user4.setUserName("teacher2");
        user4.setFavouriteColor(Arrays.asList("BLUE"));
        userMap.put(user4.getUserName(), user4);
	}

	@Override
	public User findUserById(String id) {
		synchronized (lock)
		{
			Iterator<User> iter = userMap.values().iterator();
			while(iter.hasNext())
			{
				User current = iter.next();
				if(current.getUserId().equals(id))
				{
					return current;
				}
			}

			return null;
		}
	}

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