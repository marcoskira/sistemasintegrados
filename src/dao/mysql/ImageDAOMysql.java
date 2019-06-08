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
import java.util.ArrayList;
import java.util.List;

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
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, img.getUserId());
            stmt.setString(2, img.getImagePath());
            stmt.setTimestamp(3, img.getProcessStartTime());
            stmt.setTimestamp(4,img.getProcessEndTime());
            stmt.setInt(5,img.getPixelSize());
            stmt.setInt(6,img.getIterationTimes());

            try {
                stmt.execute();
                this.conn.commit();
                stmt.close();
                return true;
            }
            catch (SQLException e){
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



    public List<Image> selectByUserId(int userId){
        List<Image> imgs = new ArrayList<Image>();

        try{
            this.conn = this.ds.getConnection();

            String sql = "SELECT * from image where user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Image image = new Image();

                image.setImageId(rs.getInt("image_id"));
                image.setUserId(rs.getInt("user_id"));
                image.setImagePath(rs.getString("image_path"));
                image.setProcessStartTime(rs.getTimestamp("process_start_time"));
                image.setProcessEndTime(rs.getTimestamp("process_end_time"));
                image.setPixelSize(rs.getInt("pixel_size"));
                image.setIterationTimes(rs.getInt("iteration_times"));

                imgs.add(image);
            }

        }
        catch (SQLException e){
            System.out.println(e);
        }


        return imgs;
    }


}

