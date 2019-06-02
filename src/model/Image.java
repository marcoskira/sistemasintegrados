package model;

import java.util.Date;

public class Image {
    private int imageId;
    private int userId;
    private String imagePath;
    private java.sql.Date processStartTime;
    private java.sql.Date processEndTime;
    private int pixelSize;
    private int iterationTimes;


    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public java.sql.Date getProcessStartTime() {
        return processStartTime;
    }

    public void setProcessStartTime(java.sql.Date processStartTime) {
        this.processStartTime = processStartTime;
    }

    public java.sql.Date getProcessEndTime() {
        return processEndTime;
    }

    public void setProcessEndTime(java.sql.Date processEndTime) {
        this.processEndTime = processEndTime;
    }

    public int getPixelSize() {
        return pixelSize;
    }

    public void setPixelSize(int pixelSize) {
        this.pixelSize = pixelSize;
    }

    public int getIterationTimes() {
        return iterationTimes;
    }

    public void setIterationTimes(int iterationTimes) {
        this.iterationTimes = iterationTimes;
    }
}
