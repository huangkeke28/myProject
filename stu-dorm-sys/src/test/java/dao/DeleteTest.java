package dao;

import org.junit.Test;

public class DeleteTest {
    @Test
    public void t1() {
        String[] ids = {"1", "2", "3"};
        int num = StudentDAO.delete(ids);
        System.out.println(num);
    }
}
