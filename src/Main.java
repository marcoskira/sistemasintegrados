import dao.mysql.UserDAOMysql;
import model.User;

public class Main {

    public static void main(String[] args) {

        //Insert example
/*        User user = new User();
        user.setLogin("marcos");
        user.setPassword("senha123");

        UserDAOMysql dao = new UserDAOMysql();
        dao.insert(user);*/

        User u = new User();
        u = u.getUserById(4);
        System.out.println(u.getLogin());
        System.out.println(u.getPassword());
        System.out.println(u.getDateCreated());

    }
}
