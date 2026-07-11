package com.xiaomi.onetrack.api;

import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class aa implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Map f2759a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ String f2760b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f2761c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ String f2762d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ String f2763e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final /* synthetic */ String f2764f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final /* synthetic */ long f2765g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final /* synthetic */ c f2766h;

    public aa(c cVar, Map map, String str, String str2, String str3, String str4, String str5, long j2) {
        this.f2766h = cVar;
        this.f2759a = map;
        this.f2760b = str;
        this.f2761c = str2;
        this.f2762d = str3;
        this.f2763e = str4;
        this.f2764f = str5;
        this.f2765g = j2;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            if (this.f2766h.h()) {
                return;
            }
            JSONObject jSONObjectD = this.f2766h.d((Map<String, Object>) this.f2759a);
            ak akVarI = this.f2766h.i();
            String str = this.f2760b;
            String str2 = this.f2761c;
            String str3 = this.f2762d;
            String str4 = this.f2763e;
            String str5 = this.f2764f;
            long j2 = this.f2765g;
            c cVar = this.f2766h;
            akVarI.a("onetrack_bug_report", ai.a(str, str2, str3, str4, str5, j2, cVar.f2956f, cVar.f2958h, jSONObjectD, cVar.f2959i, cVar.f2960j));
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "trackException error: " + e2.toString());
        }
    }
}
