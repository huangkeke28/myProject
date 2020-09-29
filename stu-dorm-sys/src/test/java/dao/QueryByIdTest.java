package dao;

import model.Student;
import org.junit.Test;
import util.JSONUtil;

public class QueryByIdTest {
    @Test
    public void t1() {
        Student s1 = StudentDAO.queryById(2);
        Student s2 = StudentDAO.queryById(12);
        Student s3 = StudentDAO.queryById(22);
        System.out.println(s1.toString());
        System.out.println(s2.toString());
        System.out.println(s3.toString());
    }

}
