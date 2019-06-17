package dao.mysql;

import dao.DataSourceFactory;
import dao.RequestDAO;
import model.Request;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RequestDAOMysql implements RequestDAO {
    private DataSource ds;
    private Connection conn;

    public RequestDAOMysql(){ this.ds = new DataSourceFactory().getMysqlDataSource(); }


    public boolean insert(Request req){
        try {
            this.conn = ds.getConnection();
            conn.setAutoCommit(false);

            String sql = "INSERT INTO request_queue(user_id, signal_path, signal_size) VALUES(?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, req.getUserId());
            stmt.setString(2, req.getSignalPath());
            stmt.setFloat(3, req.getSignalSize());

            try {
                stmt.execute();
                conn.commit();
                stmt.close();

                return true;

            }catch(SQLException e){
                System.out.println(e);
                conn.rollback();
                stmt.close();
            }

        }catch (SQLException e){
            System.out.println(e);
        }

        return false;
    }


    public Request selectByRequestId(int id){
        Request req = null;
        try{
            this.conn = ds.getConnection();

            String sql = "SELECT * FROM request_queue where request_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            req = new Request();

            req.setRequestId(rs.getInt("request_id"));
            req.setUserId(rs.getInt("user_id"));
            req.setProcessStartTime(rs.getTimestamp("process_start_time"));
            req.setProcessEndTime(rs.getTimestamp("process_end_time"));
            req.setSignalPath(rs.getString("signal_path"));
            req.setSignalSize(rs.getFloat("signal_size"));
            req.setStatus(rs.getString("status").charAt(0));
            req.setRequestCreationTime(rs.getTimestamp("request_creation_time"));

        }catch(SQLException e){
            System.out.println(e);
        }

        return req;

    }

}
