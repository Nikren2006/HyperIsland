package g1;

import androidx.core.location.LocationRequestCompat;
import miuix.animation.internal.FolmeCore;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l1.F f4409a = new l1.F("REMOVED_TASK");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final l1.F f4410b = new l1.F("CLOSED_EMPTY");

    public static final long c(long j2) {
        if (j2 <= 0) {
            return 0L;
        }
        return j2 >= 9223372036854L ? LocationRequestCompat.PASSIVE_INTERVAL : FolmeCore.NANOS_TO_MS * j2;
    }
}
