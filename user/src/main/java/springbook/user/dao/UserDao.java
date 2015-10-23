package springbook.user.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.domain.User;

import java.sql.*;

/**
 * Created by miki on 15. 10. 17..
 */
public class UserDao {
    // 인터페이스를 통해 오브젝트에 접근
    // 싱글턴 스코프를 갖는 객체는 인스턴스 상태를 두지 않는다. 하지만 읽기 전용인 final은 무관하다.
    private final ConnectionMaker connectionMaker;

    // DL : Dependency Lookup 의존관계 검색, UserDao가 Bean일 필요가 없다.
    public UserDao() {
//        DaoFactory daoFactory = new DaoFactory();
//        this.connectionMaker = daoFactory.connectionMaker();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker.class);
    }

    // DI : 의존 관계 주입(설정), 사용 의존 관계, 스프링에 의해 DI 받으려면 UserDao가 Bean이어야 한다.
    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection(); // 인터페이스에 정의된 메소드이므로 변경될 걱정이 없음

        PreparedStatement ps = c.prepareStatement("INSERT INTO users(id, name, password) VALUES(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

}
