package model;

//Imports
import dao.ImageDAO;
import dao.UserDAO;
import dao.mysql.ImageDAOMysql;
import dao.mysql.UserDAOMysql;
//
import java.util.Date;
import java.util.List;

public class User {
    private Integer userId;
    private String login;
    private String password;
    private Date dateCreated;
    private List<Image> imgs;
    private UserDAO userDao;
    private ImageDAO imageDao;


    public User (){
        this.userDao = new UserDAOMysql();
        this.imageDao = new ImageDAOMysql();

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

    public List<Image> getImgs() {
        return imgs;
    }

    public void setImgs(List<Image> imgs) {
        this.imgs = imgs;
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    public ImageDAO getImageDao() {
        return imageDao;
    }

    public void setImageDao(ImageDAO imageDao) {
        this.imageDao = imageDao;
    }

    //Methods
    public boolean createNewUser(){
        return userDao.insert(this);
    }

    //
    public int validateCredentials(String login, String password) {
        return this.userDao.validateCredentials(login, password);
    }

    //Given an ID, returns an User object with that ID (returns null if no user was found)
    public User getUserById(int id){
        User u = this.userDao.selectByUserId(id);

        //If exists an user with that user_id, find all images that this user has
        if (u != null)
            this.imgs = this.imageDao.selectByUserId(u.getUserId());

        return u;
    }





}
