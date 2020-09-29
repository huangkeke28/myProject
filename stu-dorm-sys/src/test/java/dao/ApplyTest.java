package dao;

import model.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplyTest {
    @Test
    public void t1() {
        Student s = StudentDAO.queryById(28);
        List<Integer> ids = new ArrayList<>();
        ids.add(25);
        ids.add(26);
        ids.add(31);
        s.setIds(ids);
        s.setDormId(2);
        int num = StudentDAO.apply(s);
        Student s1 = StudentDAO.queryById(25);
        Student s2 = StudentDAO.queryById(26);
        Student s3 = StudentDAO.queryById(31);
        System.out.println(s1.toString());
        System.out.println(s2.toString());
        System.out.println(s3.toString());



    }
}
