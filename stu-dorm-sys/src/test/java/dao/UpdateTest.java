package dao;

import model.Student;
import org.junit.Test;

public class UpdateTest {

    //测试方法错误
    @Test
    public void t1() {
        Student s = StudentDAO.queryById(23);
        System.out.println(s.toString());
        Student s2 = new Student();
        s2.setStudentName("嘻嘻");
        s2.setStudentMajor("英语系");
        s2.setStudentGraduateYear("2020");
        s2.setBuildingName("男生楼-1");
        s2.setDormId(1);
        s2.setId(23);
        int num = StudentDAO.update(s2);
        System.out.println(s.toString());
    }

}
