package dao;

import model.Student;
import org.junit.Test;

public class InsertTest {
    @Test
    public void t1() {
        Student s = new Student();
        s.setStudentName("哈哈");
        s.setStudentMajor("英语系");
        s.setStudentGraduateYear("2020");
        s.setBuildingName("男生楼-1");
        s.setDormId(1);
        int num = StudentDAO.insert(s);
        System.out.println(num);
    }
}
