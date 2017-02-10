package org.islamright.tebian.model;

/**
 * Created by AlaaAlShaikh on 25/06/15.
 */
public class DataBus {
    private int progressMax;
    private int progressVale;
    private int resString;
    private boolean finished;
    private int percentage;

    public DataBus(int progressMax, int progressVale ,int percentage) {
        this.progressMax = progressMax;
        this.progressVale = progressVale;
        this.percentage = percentage;
    }

    public DataBus() {
    }

    public DataBus(boolean finished, int resString) {
        this.finished = finished;
        this.resString = resString;
    }


    public int getProgressMax() {
        return progressMax;
    }

    public void setProgressMax(int progressMax) {
        this.progressMax = progressMax;
    }

    public int getProgressVale() {
        return progressVale;
    }

    public void setProgressVale(int progressVale) {
        this.progressVale = progressVale;
    }

    public int getResString() {
        return resString;
    }

    public void setResString(int resString) {
        this.resString = resString;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
