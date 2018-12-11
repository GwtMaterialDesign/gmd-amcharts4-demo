package gmd.am4charts.demo.client.application.charts.propertyfields;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class MyCustomPropertyField {

    @JsProperty
    public String fill;

    @JsProperty
    public String color;

    @JsProperty
    public String stroke;

    @JsProperty
    public String opacity;

    @JsProperty
    public String fillOpacity;

    @JsProperty
    public String href;

    @JsProperty
    public String widthRatio;
}
