package dao.mysql;

//Imports
import dao.DataSourceFactory;
import dao.ImageDAO;
import model.Image;
//
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageDAOMysql implements ImageDAO{
    private DataSource ds;
    private Connection conn;

    public ImageDAOMysql(){ this.ds = new DataSourceFactory().getMysqlDataSource(); }


    public boolean insert(Image img){

        try {
            this.conn = this.ds.getConnection();
            this.conn.setAutoCommit(false);
        }
        catch (SQLException e){
            System.out.println(e);
        }

        String sql = "INSERT INTO image (user_id, image_path, process_start_time, process_end_time, pixel_size, iteration_times) values (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, img.getUserId());
            stmt.setString(2, img.getImagePath());
            stmt.setDate(3, img.getProcessStartTime());

            stmt.execute();

        }
        catch(SQLException e){

        }
        return true;
    }


}
