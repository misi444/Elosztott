package hu.miskolc.uni.iit.dist.dao;

import hu.miskolc.uni.iit.dist.domain.Assignment;
import hu.miskolc.uni.iit.dist.exception.InvalidParameterException;

import java.util.Collection;

public interface AssignmentDao
{
    Assignment getAssignmentById(String id);
	void storeAssignment(Assignment assignment);
    Collection<Assignment> getAssignmentsBySubject(String subjectId);
    Collection<Assignment> getAssignmentsByStudentAndSubject(String studentId, String subjectId);
	void deleteAssignment(String id) throws InvalidParameterException;
}