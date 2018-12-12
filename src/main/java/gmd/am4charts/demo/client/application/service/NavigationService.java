package gmd.am4charts.demo.client.application.service;

import gmd.am4charts.demo.client.application.navigation.HeaderLink;

import java.util.ArrayList;
import java.util.List;

public class NavigationService {

    public static List<HeaderLink> getHeaderLinks() {
        List<HeaderLink> headerLinks = new ArrayList<>();
        headerLinks.add(new HeaderLink("All Types", "types"));
        headerLinks.add(new HeaderLink("Maps", "maps"));
        headerLinks.add(new HeaderLink("Basic", "basic"));
        return headerLinks;
    }
}
