package miui.systemui.dynamicisland.touch;

import android.graphics.Rect;
import android.graphics.Region;
import android.os.SystemProperties;
import android.view.MotionEvent;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.util.MotionEventKt;

/* JADX INFO: loaded from: classes3.dex */
public final class TouchEvent {
    public static final Companion Companion = new Companion(null);
    private static final boolean DEBUG = SystemProperties.getBoolean("debug.sysui.notif.island.touch", false);
    public static final String SOURCE_DYNAMIC_ISLAND = "dynamic_island";
    public static final String SOURCE_NOTIF_SHADE = "notif_shade";
    public static final String SOURCE_STATUS_BAR = "status_bar";
    private final MotionEvent event;
    private Boolean result;
    private final String source;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean getDEBUG() {
            return TouchEvent.DEBUG;
        }

        public final boolean getShouldLog(MotionEvent motionEvent) {
            int actionMasked;
            n.g(motionEvent, "<this>");
            return getDEBUG() || (actionMasked = motionEvent.getActionMasked()) == 0 || actionMasked == 1 || actionMasked == 3;
        }

        public final int getToInt(Boolean bool) {
            if (bool != null) {
                return bool.booleanValue() ? 1 : 0;
            }
            return -1;
        }

        public final Boolean getToNullableBoolean(int i2) {
            if (i2 == 0) {
                return Boolean.FALSE;
            }
            if (i2 != 1) {
                return null;
            }
            return Boolean.TRUE;
        }

        public final boolean inRect(MotionEvent motionEvent, Rect rect) {
            n.g(motionEvent, "<this>");
            n.g(rect, "rect");
            float x2 = motionEvent.getX();
            if (x2 < rect.left || x2 > rect.right) {
                return false;
            }
            float y2 = motionEvent.getY();
            return y2 >= ((float) rect.top) && y2 <= ((float) rect.bottom);
        }

        public final boolean inRegion(MotionEvent motionEvent, Region region) {
            n.g(motionEvent, "<this>");
            n.g(region, "region");
            return region.contains((int) motionEvent.getX(), (int) motionEvent.getY());
        }

        private Companion() {
        }
    }

    public TouchEvent(MotionEvent event, String source) {
        n.g(event, "event");
        n.g(source, "source");
        this.event = event;
        this.source = source;
    }

    public static /* synthetic */ TouchEvent copy$default(TouchEvent touchEvent, MotionEvent motionEvent, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            motionEvent = touchEvent.event;
        }
        if ((i2 & 2) != 0) {
            str = touchEvent.source;
        }
        return touchEvent.copy(motionEvent, str);
    }

    public final MotionEvent component1() {
        return this.event;
    }

    public final String component2() {
        return this.source;
    }

    public final TouchEvent copy(MotionEvent event, String source) {
        n.g(event, "event");
        n.g(source, "source");
        return new TouchEvent(event, source);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TouchEvent)) {
            return false;
        }
        TouchEvent touchEvent = (TouchEvent) obj;
        return n.c(this.event, touchEvent.event) && n.c(this.source, touchEvent.source);
    }

    public final MotionEvent getEvent() {
        return this.event;
    }

    public final Boolean getResult() {
        return this.result;
    }

    public final String getSource() {
        return this.source;
    }

    public int hashCode() {
        return (this.event.hashCode() * 31) + this.source.hashCode();
    }

    public final void setResult(Boolean bool) {
        this.result = bool;
    }

    public String toString() {
        return "TouchEvent { action=" + MotionEventKt.getMotionEventAction(this.event) + ", source=" + this.source + ", result=" + this.result + " }";
    }
}
