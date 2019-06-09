import controller.LoginController;
import dao.mysql.UserDAOMysql;
import model.Image;
import model.User;

public class Main {

    public static void main(String[] args) {

        //launch(args);

        LoginController loginCtrl = new LoginController();
        loginCtrl.startApplication();


        /*
        //Insert example
        User user = new User();
        user.setLogin("marcos");
        user.setPassword("senha123");

        UserDAOMysql dao = new UserDAOMysql();
        dao.insert(user);

        User u = new User();
       u = u.getUserById(4);
        System.out.println(u.getLogin());
        System.out.println(u.getPassword());
        System.out.println(u.getDateCreated());

        u = u.getUserById(4);


        System.out.println(u.getUserId());
        System.out.println(u.getLogin());
        System.out.println(u.getPassword());
        System.out.println(u.getDateCreated());
        System.out.print("-----------------");


      for(Image i: u.getImgs()){
          System.out.println("UserId: " + i.getUserId());
          System.out.println("ImageId: " + i.getImageId());
          System.out.println("ImagePath: " + i.getImagePath());
          System.out.println("------------");
      }


*/
    }


}
