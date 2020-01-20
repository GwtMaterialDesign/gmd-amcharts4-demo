package gmd.amcharts4.demo.client.application.charts.plugin.bullet;

import java.util.Date;

public class Bullet {

    private Date date;
    private int value;
    private boolean hideBullet;
    private String bulletText;
    private String bulletColor;
    private int height;

    public Bullet(Date date, int value, boolean hideBullet, String bulletText, String bulletColor, int height) {
        this.date = date;
        this.value = value;
        this.hideBullet = hideBullet;
        this.bulletText = bulletText;
        this.bulletColor = bulletColor;
        this.height = height;
    }

    public Bullet() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isHideBullet() {
        return hideBullet;
    }

    public void setHideBullet(boolean hideBullet) {
        this.hideBullet = hideBullet;
    }

    public String getBulletText() {
        return bulletText;
    }

    public void setBulletText(String bulletText) {
        this.bulletText = bulletText;
    }

    public String getBulletColor() {
        return bulletColor;
    }

    public void setBulletColor(String bulletColor) {
        this.bulletColor = bulletColor;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
