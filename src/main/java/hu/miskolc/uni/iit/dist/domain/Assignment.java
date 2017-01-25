package hu.miskolc.uni.iit.dist.domain;

import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Assignment implements Comparable<Assignment>
{
    @NotBlank
    private String id;

	@NotBlank
	private String subjectId;

	@NotBlank
	private String studentId;

	private User student;

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    @NotBlank
    private String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Assignment()
    {
        this.id = UUID.randomUUID().toString();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @NotBlank
    private Date date;

    @Override
	public String toString()
	{
		return "Assignment [id=" + id + ", subjectId=" + subjectId + ", studentId=" + studentId + ", path=" + path + ", date=" + date + "]";
	}

    @Override
    public int compareTo(Assignment o) {
        return this.date.compareTo(o.date);
    }
}