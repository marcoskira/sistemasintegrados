package model;

//Imports
import dao.RequestDAO;
import dao.mysql.RequestDAOMysql;

import java.sql.Timestamp;

public class Request {
    private int requestId;
    private int userId;
    private String signalPath;
    private float signalSize;
    private char status;
    private Timestamp requestCreationTime;
    private Timestamp processStartTime;
    private Timestamp processEndTime;

    private RequestDAO requestDAO;

    public Request(){
        requestDAO = new RequestDAOMysql();
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSignalPath() {
        return signalPath;
    }

    public void setSignalPath(String signalPath) {
        this.signalPath = signalPath;
    }

    public float getSignalSize() {
        return signalSize;
    }

    public void setSignalSize(float signalSize) {
        this.signalSize = signalSize;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Timestamp getRequestCreationTime() {
        return requestCreationTime;
    }

    public void setRequestCreationTime(Timestamp requestCreationTime) {
        this.requestCreationTime = requestCreationTime;
    }

    public Timestamp getProcessStartTime() {
        return processStartTime;
    }

    public void setProcessStartTime(Timestamp processStartTime) {
        this.processStartTime = processStartTime;
    }

    public Timestamp getProcessEndTime() {
        return processEndTime;
    }

    public void setProcessEndTime(Timestamp processEndTime) {
        this.processEndTime = processEndTime;
    }

    public RequestDAO getRequestDAO() {
        return requestDAO;
    }

    public void setRequestDAO(RequestDAO requestDAO) {
        this.requestDAO = requestDAO;
    }

    public boolean createNewRequest(){
        return requestDAO.insert(this);
    }
}
