package systemui.plugin.eventtracking;

import android.content.Context;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes5.dex */
public final class EventTracker$desktopTracker$2 extends o implements Function0 {
    final /* synthetic */ Context $ctx;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EventTracker$desktopTracker$2(Context context) {
        super(0);
        this.$ctx = context;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Tracker invoke() {
        return EventTrackerFactory.Companion.createDesktopTracker(this.$ctx);
    }
}
