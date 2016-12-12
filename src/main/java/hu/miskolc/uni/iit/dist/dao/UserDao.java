package hu.miskolc.uni.iit.dist.dao;

import java.util.Collection;

import hu.miskolc.uni.iit.dist.domain.User;

public interface UserDao
{
	User findUserByName(String userName);
	void storeUser(User user);
	Collection<User> getUsers();
	void deleteUser(String userId);
}