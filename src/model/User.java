package model;

//Imports
import dao.UserDAO;
import dao.mysql.UserDAOMysql;
//
import java.util.Date;

public class User {
    private Integer userId;
    private String login;
    private String password;
    private Date dateCreated;
    private UserDAO dao;


    public User (){
        this.dao = new UserDAOMysql();
    }


    /* Getters and setters */
    public int getUserId() {
            return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }


    //
    public boolean createNewUser(){
        return dao.insert(this);
    }

    //
    public int validateCredentials(String login, String password) {
        return this.dao.validateCredentials(login, password);
    }

    //Given an ID, returns an User object with that ID (returns null if no user was found)
    public User getUserById(int id){
        return this.dao.selectById(id);
    }





}
