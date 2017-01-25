package hu.miskolc.uni.iit.dist.dao;

import hu.miskolc.uni.iit.dist.domain.Subject;
import hu.miskolc.uni.iit.dist.domain.User;
import hu.miskolc.uni.iit.dist.exception.InvalidParameterException;

import java.util.Collection;

public interface SubjectDao
{
	Subject getSubjectById(String id);
	void storeSubject(Subject subject);
	Collection<Subject> getSubjects();
    Collection<Subject> getSubjectsByTeacher(String teacherId);
    Collection<Subject> getStudiedSubjectsByStudent(String studentId);
	Collection<Subject> getAvailableSubjectsByStudent(String studentId);
	void deleteSubject(String id) throws InvalidParameterException;
}