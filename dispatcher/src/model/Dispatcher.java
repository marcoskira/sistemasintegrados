package model;
import dao.ImageDAO;
import dao.RequestDAO;

import dao.UserDAO;
import dao.mysql.ImageDAOMysql;
import dao.mysql.RequestDAOMysql;
import dao.mysql.UserDAOMysql;

import java.util.TimerTask;

public class Dispatcher extends  TimerTask{
    private Reconstruction rec;
    private Request req;
    private Image img;
    private User user;
    private ImageDAO imageDAO;
    private RequestDAO requestDAO;
    private UserDAO userDAO;


    public Dispatcher(){
        rec = new Reconstruction();
        req = new Request();
        user = new User();
        imageDAO = new ImageDAOMysql();
        requestDAO = new RequestDAOMysql();
        userDAO = new UserDAOMysql();
    }


    public void run(){

        //Verifiy if reconstruction method is processing something
        if(rec.isFree()){

            //select in request queue looking for new entries with status 'N'
            req = requestDAO.selectByOldestNewRequest();
            if(req != null) {
                rec.setFree(false);
                requestDAO.updateRequestStatus(req, "W");

                req = requestDAO.selectByRequestId(req.getRequestId());
                user = userDAO.selectByUserId(req.getUserId());

                //Image reconstruction
                img = rec.imageReconstruction(req, user);

                requestDAO.updateRequestStatus(req, "P");
                imageDAO.insert(img);
                rec.setFree(true);
            }else {
                System.out.println("Request queue is empty");
            }
        }



    }



}
