package gmd.amcharts4.demo.client.application.charts.misc.data;

import com.google.gwt.core.client.JavaScriptObject;

public class JsObjectData extends JavaScriptObject {

    protected JsObjectData() {
    }

    public final native String getColor() /*-{
        return this.color;
    }-*/;

    public final native String getCategory() /*-{
        return this.category;
    }-*/;

    public final native int getValue()  /*-{
        return this.value;
    }-*/;

    public final native void setCategory(String category) /*-{
        this.category = category;
    }-*/;

    public final native void setValue(int value)  /*-{
        this.value = value;
    }-*/;

    public final native void setColor(String color) /*-{
        this.color = color;
    }-*/;
}
