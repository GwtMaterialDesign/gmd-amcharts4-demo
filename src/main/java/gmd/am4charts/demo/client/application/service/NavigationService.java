package gmd.am4charts.demo.client.application.service;

import gmd.am4charts.demo.client.application.navigation.HeaderLink;
import gmd.am4charts.demo.client.place.NameTokens;

import java.util.ArrayList;
import java.util.List;

public class NavigationService {

    public static List<HeaderLink> getHeaderLinks() {
        List<HeaderLink> headerLinks = new ArrayList<>();
        headerLinks.add(new HeaderLink("All Types", NameTokens.TYPES));
        headerLinks.add(new HeaderLink("Maps", NameTokens.MAPS));
        headerLinks.add(new HeaderLink("Gauge", NameTokens.GAUGES));
        return headerLinks;
    }
}
