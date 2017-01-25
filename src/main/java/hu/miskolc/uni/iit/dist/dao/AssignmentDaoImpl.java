package hu.miskolc.uni.iit.dist.dao;

import hu.miskolc.uni.iit.dist.domain.Assignment;
import hu.miskolc.uni.iit.dist.exception.InvalidParameterException;

import java.util.*;
import java.util.stream.Collectors;

public class AssignmentDaoImpl implements AssignmentDao
{
	private Map<String, Assignment> assignmentMap = new HashMap<>();
	private Object lock = new Object();

	public AssignmentDaoImpl() {

    }

	@Override
	public Assignment getAssignmentById(String id) {
		synchronized (lock)
		{
			Iterator<Assignment> iter = this.assignmentMap.values().iterator();
			while(iter.hasNext())
			{
				Assignment current = iter.next();
				if(current.getId().equals(id))
				{
					return current;
				}
			}

			return null;
		}
	}

	@Override
    public void storeAssignment(Assignment assignment) {
		synchronized (lock)
		{
            assignmentMap.put(assignment.getId(), assignment);
		}
	}

    @Override
    public Collection<Assignment> getAssignmentsBySubject(String subjectId) {
        synchronized (lock)
        {
            List<Assignment> assignments = assignmentMap
                    .entrySet()
                    .stream()
                    .filter(x -> x.getValue().getSubjectId().equals(subjectId))
                    .map(x -> x.getValue())
                    .collect(Collectors.toList());

            Collections.sort(assignments);

            return assignments;
        }
    }

    @Override
    public Collection<Assignment> getAssignmentsByStudentAndSubject(String studentId, String subjectId) {
        synchronized (lock)
        {
            List<Assignment> assignments = assignmentMap
                    .entrySet()
                    .stream()
                    .filter(x -> x.getValue().getStudentId().equals(studentId) && x.getValue().getSubjectId().equals(subjectId))
                    .map(x -> x.getValue())
                    .collect(Collectors.toList());

            Collections.sort(assignments);
            return assignments;
        }
    }

	@Override
	public void deleteAssignment(String id) throws InvalidParameterException
	{
		synchronized (lock)
		{
			Iterator<Assignment> iter = assignmentMap.values().iterator();
			while(iter.hasNext())
			{
				if(iter.next().getId().equals(id))
				{
					iter.remove();
					return;
				}
			}
		}

		throw new InvalidParameterException("WARNING: No assignment found with given id(" + id + ").");
	}
}