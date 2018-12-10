package gmd.am4charts.demo.client.application.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class ApplyThemeEvent extends GwtEvent<ApplyThemeEvent.ApplyThemeHandler> {
    public interface ApplyThemeHandler extends EventHandler {
        void onApplyThemeEvent(ApplyThemeEvent event);
    }

    public static final Type<ApplyThemeHandler> TYPE = new Type<>();

    ApplyThemeEvent() {
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new ApplyThemeEvent());
    }

    @Override
    public Type<ApplyThemeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ApplyThemeHandler handler) {
        handler.onApplyThemeEvent(this);
    }
}