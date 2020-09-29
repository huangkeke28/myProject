package dao;

import model.Page;
import model.Student;
import org.junit.Test;

import java.util.List;

public class QueryTest {

    @Test
    public void t1() {
        Page p = new Page();
        p.setSearchText("");
        p.setSortOrder("asc");
        p.setPageSize(7);
        p.setPageNumber(1);
        List<Student> list = StudentDAO.query(p);
        for (Object o : list) {
            System.out.println(o.toString());
        }
    }
}
