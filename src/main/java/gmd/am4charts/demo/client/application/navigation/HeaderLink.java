package gmd.am4charts.demo.client.application.navigation;

public class HeaderLink {

    String name;
    String href;

    public HeaderLink(String name, String href) {
        this.name = name;
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
