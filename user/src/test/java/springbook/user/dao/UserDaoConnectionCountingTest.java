package springbook.user.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.domain.User;

import java.sql.SQLException;

/**
 * Created by miki on 15. 10. 23..
 */
public class UserDaoConnectionCountingTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        // DAO 사용 코드
        User user = new User("miki", "김민우", "1212");
        dao.add(user);
        System.out.println(user);
        System.out.println("등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2);
        System.out.println("조회 성공");

        // counting 조회
        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("Connection counter : " + ccm.getCounter());
    }
}
