package gmd.am4charts.demo.client.application.charts;

public class PictorialData {

    String category;
    int height;
    double ratio;

    public PictorialData() {
    }

    public PictorialData(String category, int height, double ratio) {
        this.category = category;
        this.height = height;
        this.ratio = ratio;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
