package dao;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.mysql.cj.jdbc.MysqlDataSource;


public class DataSourceFactory {

    //Get datasource
    public DataSource getMysqlDataSource (){
        Properties props = new Properties();
        FileInputStream fis = null;
        MysqlDataSource ds = new MysqlDataSource();;

        try {
            fis = new FileInputStream("src/dao/db.properties");
            props.load(fis);
        }
        catch (IOException e) {
            System.out.println(e);
        }
            ds.setUrl(props.getProperty("MYSQL_DB_URL"));
            ds.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            ds.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));

        return ds;
    }


}
