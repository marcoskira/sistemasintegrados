package model;

import java.sql.Timestamp;

public class Image {
    private int imageId;
    private int userId;
    private String imagePath;
    private Timestamp processStartTime;
    private Timestamp processEndTime;
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
