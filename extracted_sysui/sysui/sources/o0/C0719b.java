package o0;

import android.content.Context;
import android.content.IntentFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import o0.C0720c;
import z0.e;

/* JADX INFO: renamed from: o0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0719b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f6321a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f6322b;

    public C0719b(Context context, C0720c.a aVar) {
        this.f6321a = context;
        ArrayList arrayList = new ArrayList();
        this.f6322b = arrayList;
        arrayList.add(new C0720c(aVar));
        a();
    }

    public final void a() {
        try {
            for (AbstractC0718a abstractC0718a : this.f6322b) {
                IntentFilter intentFilter = new IntentFilter();
                for (String str : abstractC0718a.a()) {
                    intentFilter.addAction(str);
                }
                e.c("BroadcastManager", "registerReceiver: " + abstractC0718a.getClass().getSimpleName());
                this.f6321a.registerReceiver(abstractC0718a, intentFilter, 2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void b() {
        c();
    }

    public final void c() {
        try {
            Iterator it = this.f6322b.iterator();
            while (it.hasNext()) {
                this.f6321a.unregisterReceiver((AbstractC0718a) it.next());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
