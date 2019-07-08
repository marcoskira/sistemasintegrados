package dao.mysql;

import dao.DataSourceFactory;
import dao.RequestDAO;
import model.Request;

import javax.sql.DataSource;
import java.sql.*;


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

    public Request selectByOldestNewRequest(){
        Request req = null;
        try{
            this.conn = ds.getConnection();

            String sql = "SELECT * FROM request_queue where status = ? order by request_creation_time asc limit 1";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,"N");
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                req = new Request();
                req.setSignalPath(rs.getString("signal_path"));
                req.setSignalSize(rs.getFloat("signal_size"));
                req.setStatus(rs.getString("status").charAt(0));
                req.setRequestCreationTime(rs.getTimestamp("request_creation_time"));
                req.setRequestId(rs.getInt("request_id"));
                req.setUserId(rs.getInt("user_id"));
                req.setProcessStartTime(rs.getTimestamp("process_start_time"));
                req.setProcessEndTime(rs.getTimestamp("process_end_time"));
            }

        }catch(SQLException e){
            System.out.println(e);
        }

        return req;
    }

    public boolean updateRequestStatus(Request req, String status){
        try {
            this.conn = ds.getConnection();
            conn.setAutoCommit(false);


            //Change to "working" status
            if(status.equals("W")){
                System.out.println("update para W");
                String sql = "UPDATE request_queue set status = ?, process_start_time = ? where request_id = ? and status = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, status);
                stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                stmt.setInt(3, req.getRequestId());
                stmt.setString(4, "N");

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
            }
            else if(status.equals("P")){
                System.out.println("update para P");
                String sql = "UPDATE request_queue set status = ?, process_end_time = ? where request_id = ? and status = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, status);
                stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                stmt.setInt(3, req.getRequestId());
                stmt.setString(4, "W");

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
            }



        }catch (SQLException e){
            System.out.println(e);
        }

        return false;
    }

}
