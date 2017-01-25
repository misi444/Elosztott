package hu.miskolc.uni.iit.dist.dao;

import java.util.Collection;

import hu.miskolc.uni.iit.dist.domain.User;
import hu.miskolc.uni.iit.dist.exception.InvalidParameterException;

public interface UserDao
{
	User findUserById(String id);
	User findUserByName(String userName);
	void storeUser(User user);
	Collection<User> getUsers();
	void deleteUser(String userId) throws InvalidParameterException;
}