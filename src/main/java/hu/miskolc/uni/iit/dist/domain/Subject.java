package hu.miskolc.uni.iit.dist.domain;

import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class Subject implements Comparable<Subject>
{
	@NotBlank
	private String subjectId;

	@NotBlank
	private String name;

    @NotBlank
    private String teacher;

    private SubjectType type;

    public SubjectType getType() {
        return this.type;
    }

    public void setType(SubjectType type) {
        this.type = type;
    }

    private List<String> students = new ArrayList<String>();

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public List<String> getStudents() {
        return students;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
	public String toString()
	{
		return "Subject [subjectId=" + subjectId + ", name=" + name + "]";
	}

    @Override
    public int compareTo(Subject o) {
        return this.name.compareTo(o.name);
    }
}