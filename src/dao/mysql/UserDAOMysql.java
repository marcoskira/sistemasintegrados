package dao.mysql;

// Imports
import dao.DataSourceFactory;
import dao.UserDAO;
import model.User;
//
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAOMysql implements UserDAO {

    private DataSource ds;
    private Connection conn;

    public UserDAOMysql() {
         this.ds = new DataSourceFactory().getMysqlDataSource();
    }

    //Inserts a new registry in user table
    public boolean insert(User u){
        try {
            this.conn = ds.getConnection();
            this.conn.setAutoCommit(false);
        }
        catch (SQLException e){
            System.out.println(e);
        }


        String sql = "INSERT INTO user (login, password) VALUES(?, ?)";

        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, u.getLogin());
            stmt.setString(2, u.getPassword());

            try{
                stmt.execute();
                this.conn.commit();
                stmt.close();
                return true;
            }catch (Exception e){
                System.out.println(e);
                this.conn.rollback();
                stmt.close();
            }

        }
        catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }


    public User selectByUserId(int id){
        User user = null;

        try {
            this.conn = ds.getConnection();
            String sql = "SELECT user_id, login, password, date_created from user";

            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setDateCreated(rs.getDate("date_created"));
            }

        }catch (SQLException e){
            System.out.println(e);
        }

        return user;
    }

    //Validate user's credentials and returns its ID
    public int validateCredentials(String login, String password) {
        try{
            this.conn = ds.getConnection();
            String sql = "SELECT * from user where login = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if(rs.getString("password").equals(password))
                return rs.getInt("user_id");
            else
                return 0;


        }catch(SQLException e){
            System.out.println(e);
            return 0;
        }
    }
}
