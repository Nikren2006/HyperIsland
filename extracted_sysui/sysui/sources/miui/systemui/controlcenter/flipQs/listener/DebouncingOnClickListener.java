package miui.systemui.controlcenter.flipQs.listener;

import android.os.SystemClock;
import android.view.View;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class DebouncingOnClickListener implements View.OnClickListener {
    private final long intervalMillis;
    private long lastClickTime;

    public DebouncingOnClickListener() {
        this(0L, 1, null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v2) {
        n.g(v2, "v");
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - this.lastClickTime >= this.intervalMillis) {
            this.lastClickTime = jElapsedRealtime;
            onDebouncingClick(v2);
        }
    }

    public abstract void onDebouncingClick(View view);

    public DebouncingOnClickListener(long j2) {
        this.intervalMillis = j2;
    }

    public /* synthetic */ DebouncingOnClickListener(long j2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 500L : j2);
    }
}
