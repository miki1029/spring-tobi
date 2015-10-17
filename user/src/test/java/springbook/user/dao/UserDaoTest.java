package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.SQLException;

/**
 * Created by miki on 15. 10. 17..
 */
public class UserDaoTest {
    // 런타임 오브젝트 관계를 맺어주는 것이 클라이언트의 책임
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionMaker connectionMaker = new DConnectionMaker();

        UserDao dao = new UserDao(connectionMaker);

        User user = new User("miki", "김민우", "1212");
        dao.add(user);
        System.out.println(user);
        System.out.println("등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2);
        System.out.println("조회 성공");

    }
}
