package o0;

import android.content.Context;
import android.content.Intent;
import java.lang.ref.WeakReference;
import m0.E;
import z0.e;

/* JADX INFO: renamed from: o0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0720c extends AbstractC0718a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakReference f6323a;

    /* JADX INFO: renamed from: o0.c$a */
    public interface a {
        void c(boolean z2);
    }

    public C0720c(a aVar) {
        this.f6323a = new WeakReference(aVar);
    }

    @Override // o0.AbstractC0718a
    public String[] a() {
        return new String[]{"com.xiaomi.miplay.action.SMARTPLAY_VIDEO"};
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.xiaomi.miplay.action.SMARTPLAY_VIDEO")) {
            int intExtra = intent.getIntExtra("smartplay_video", -1);
            e.c("VideoSessionBroadcastReceiver", "onReceive, getExtra, " + intExtra);
            if (intExtra != 0 && intExtra != 1) {
                e.c("VideoSessionBroadcastReceiver", "onReceive, extra not match..." + intExtra);
                return;
            }
            E.g().a(intExtra == 0);
            a aVar = (a) this.f6323a.get();
            if (aVar != null) {
                aVar.c(intExtra == 0);
            }
        }
    }
}
