package gmd.amcharts4.demo.client.application.charts.plugin.bullet;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class BulletPropertyField {

    @JsProperty
    public String disabled;

    @JsProperty
    public String poleHeight;

    @JsProperty
    public String stroke;

    @JsProperty
    public String fill;
}
