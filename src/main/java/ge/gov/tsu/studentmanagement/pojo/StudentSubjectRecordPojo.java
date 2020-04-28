package ge.gov.tsu.studentmanagement.pojo;

import java.util.List;

public class StudentSubjectRecordPojo {
    private List<StudentRecordPojo> studentGrades;

    public List<StudentRecordPojo> getStudentGrades() {
        return studentGrades;
    }

    public void setStudentGrades(List<StudentRecordPojo> studentGrades) {
        this.studentGrades = studentGrades;
    }
}
