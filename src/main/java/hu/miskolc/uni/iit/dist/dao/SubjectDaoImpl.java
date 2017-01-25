package hu.miskolc.uni.iit.dist.dao;

import hu.miskolc.uni.iit.dist.domain.Subject;
import hu.miskolc.uni.iit.dist.domain.SubjectType;
import hu.miskolc.uni.iit.dist.exception.InvalidParameterException;

import java.util.*;
import java.util.stream.Collectors;

public class SubjectDaoImpl implements SubjectDao
{
	private Map<String, Subject> subjectMap = new HashMap<>();
	private Object lock = new Object();

	public SubjectDaoImpl() {
	    Subject subject1 = new Subject();
	    subject1.setName("ASD1 tantárgy");
	    subject1.setSubjectId("asd1");
	    subject1.setTeacher("teacher1");
	    subject1.getStudents().add("anonymousUser");
	    subject1.setType(SubjectType.EXAM);
	    this.subjectMap.put("asd1", subject1);
        Subject subject2 = new Subject();
        subject2.setName("ASD2 tantárgy");
        subject2.setSubjectId("asd2");
        subject2.setTeacher("teacher2");
        subject2.setType(SubjectType.EXAM);
        this.subjectMap.put("asd2", subject2);
    }

	@Override
    public void storeSubject(Subject subject) {
		synchronized (lock)
		{
            subjectMap.put(subject.getSubjectId(), subject);
		}
	}

    @Override
    public Subject getSubjectById(String id) {
        return this.subjectMap.get(id);
    }

	@Override
    public Collection<Subject> getSubjects() {
		synchronized (lock)
		{
			return subjectMap.values();
		}
	}

    @Override
    public Collection<Subject> getSubjectsByTeacher(String teacherId) {
        synchronized (lock)
        {
            List<Subject> subjects = subjectMap
					.entrySet()
					.stream()
                    .filter(x -> x.getValue().getTeacher().equals(teacherId))
                    .map(x -> x.getValue())
                    .collect(Collectors.toList());

            Collections.sort(subjects);
            return subjects;
        }
    }

    @Override
    public Collection<Subject> getStudiedSubjectsByStudent(String studentId) {
        synchronized (lock)
        {
            List<Subject> subjects =  subjectMap
                    .entrySet()
                    .stream()
                    .filter(x -> x.getValue().getStudents().contains(studentId))
                    .map(x -> x.getValue())
                    .collect(Collectors.toList());

            Collections.sort(subjects);
            return subjects;
        }
    }

    @Override
    public Collection<Subject> getAvailableSubjectsByStudent(String studentId) {
        synchronized (lock)
        {
            List<Subject> subjects =  subjectMap
                    .entrySet()
                    .stream()
                    .filter(x -> !x.getValue().getStudents().contains(studentId))
                    .map(x -> x.getValue())
                    .collect(Collectors.toList());

            Collections.sort(subjects);
            return subjects;
        }
    }

	@Override
	public void deleteSubject(String id) throws InvalidParameterException
	{
		synchronized (lock)
		{
			Iterator<Subject> iter = subjectMap.values().iterator();
			while(iter.hasNext())
			{
				if(iter.next().getSubjectId().equals(id))
				{
					iter.remove();
					return;
				}
			}
		}

		throw new InvalidParameterException("WARNING: No subject found with given id(" + id + ").");
	}
}