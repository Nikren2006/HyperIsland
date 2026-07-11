package miui.systemui.util;

import android.os.SystemClock;
import android.util.ArrayMap;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes4.dex */
public final class OnClickListenerEx {
    public static final OnClickListenerEx INSTANCE = new OnClickListenerEx();
    public static final long TIMEOUT = 200;

    public static final class Click implements View.OnClickListener {
        private final Function1 action;
        private long lastTriggeredTime;

        public Click(Function1 action) {
            kotlin.jvm.internal.n.g(action, "action");
            this.action = action;
            this.lastTriggeredTime = -1L;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (jElapsedRealtime > this.lastTriggeredTime + 200) {
                this.lastTriggeredTime = jElapsedRealtime;
                this.action.invoke(view);
            }
        }
    }

    public static final class LongClick implements View.OnLongClickListener {
        private final Function1 action;
        private long lastTriggeredTime;

        public LongClick(Function1 action) {
            kotlin.jvm.internal.n.g(action, "action");
            this.action = action;
            this.lastTriggeredTime = -1L;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (jElapsedRealtime <= this.lastTriggeredTime + 200) {
                return false;
            }
            this.lastTriggeredTime = jElapsedRealtime;
            return ((Boolean) this.action.invoke(view)).booleanValue();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.util.OnClickListenerEx$OnClickListenerEx, reason: collision with other inner class name */
    public static final class ViewOnClickListenerC0154OnClickListenerEx implements View.OnClickListener {
        private final Function1 action;
        private final ArrayMap<WeakReference<View>, Long> map;

        public ViewOnClickListenerC0154OnClickListenerEx(Function1 action) {
            kotlin.jvm.internal.n.g(action, "action");
            this.action = action;
            this.map = new ArrayMap<>();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            for (Map.Entry<WeakReference<View>, Long> entry : this.map.entrySet()) {
                WeakReference<View> key = entry.getKey();
                Long value = entry.getValue();
                if (key.get() == view) {
                    if (jElapsedRealtime > value.longValue() + 200) {
                        this.map.put(key, Long.valueOf(jElapsedRealtime));
                        this.action.invoke(view);
                        return;
                    }
                    return;
                }
            }
            this.map.put(new WeakReference<>(view), Long.valueOf(jElapsedRealtime));
            this.action.invoke(view);
        }
    }

    public static final class OnLongClickListenerEx implements View.OnLongClickListener {
        private final Function1 action;
        private final ArrayMap<WeakReference<View>, Long> map;

        public OnLongClickListenerEx(Function1 action) {
            kotlin.jvm.internal.n.g(action, "action");
            this.action = action;
            this.map = new ArrayMap<>();
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            for (Map.Entry<WeakReference<View>, Long> entry : this.map.entrySet()) {
                WeakReference<View> key = entry.getKey();
                Long value = entry.getValue();
                if (key.get() == view) {
                    if (jElapsedRealtime <= value.longValue() + 200) {
                        return false;
                    }
                    this.map.put(key, Long.valueOf(jElapsedRealtime));
                    return ((Boolean) this.action.invoke(view)).booleanValue();
                }
            }
            this.map.put(new WeakReference<>(view), Long.valueOf(jElapsedRealtime));
            return ((Boolean) this.action.invoke(view)).booleanValue();
        }
    }

    private OnClickListenerEx() {
    }

    public final void setOnClickListenerEx(View view, Function1 onClick) {
        kotlin.jvm.internal.n.g(view, "<this>");
        kotlin.jvm.internal.n.g(onClick, "onClick");
        view.setOnClickListener(new Click(onClick));
    }

    public final void setOnLongClickListenerEx(View view, Function1 onLongClick) {
        kotlin.jvm.internal.n.g(view, "<this>");
        kotlin.jvm.internal.n.g(onLongClick, "onLongClick");
        view.setOnLongClickListener(new LongClick(onLongClick));
    }
}
