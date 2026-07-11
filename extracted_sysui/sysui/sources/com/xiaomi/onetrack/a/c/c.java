package com.xiaomi.onetrack.a.c;

import com.xiaomi.onetrack.util.q;
import com.xiaomi.onetrack.util.r;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2739a = "AdMonitorUploader";

    public static void a() {
        ArrayList<com.xiaomi.onetrack.a.b.a> arrayList;
        try {
            if (b()) {
                q.a(f2739a, "即将读取数据库并上传数据");
                int i2 = 0;
                while (i2 <= 20) {
                    com.xiaomi.onetrack.a.a.a().d();
                    a aVarB = com.xiaomi.onetrack.a.a.a().b();
                    if (aVarB != null && (arrayList = aVarB.f2734b) != null && arrayList.size() > 0) {
                        ArrayList<Integer> arrayList2 = new ArrayList<>();
                        ArrayList<Integer> arrayList3 = new ArrayList<>();
                        for (com.xiaomi.onetrack.a.b.a aVar : aVarB.f2734b) {
                            boolean zB = com.xiaomi.onetrack.g.b.b(aVar.c());
                            int iB = aVar.b();
                            if (zB) {
                                arrayList2.add(Integer.valueOf(iB));
                            } else {
                                arrayList3.add(Integer.valueOf(iB));
                            }
                        }
                        if (arrayList2.size() > 0) {
                            com.xiaomi.onetrack.a.a.a().a(arrayList2);
                        }
                        if (arrayList3.size() > 0) {
                            com.xiaomi.onetrack.a.a.a().b(arrayList3);
                        }
                        i2++;
                        if (aVarB.f2735c) {
                            q.a(f2739a, "No more ad monitor records");
                            return;
                        }
                    }
                    q.a(f2739a, "满足条件的adMonitor记录为空，即将返回");
                    return;
                }
            }
        } catch (Throwable th) {
            q.a(f2739a, "uploadData Throwable:" + th.getMessage());
        }
    }

    private static boolean b() {
        if (r.a(f2739a)) {
            q.a(f2739a, "the device is not provisioned, stop poll!");
            return false;
        }
        if (!com.xiaomi.onetrack.g.c.a()) {
            q.a(f2739a, "network is unconnected, stop poll!");
            return false;
        }
        if (com.xiaomi.onetrack.a.a.a().e() != 0) {
            return true;
        }
        q.a(f2739a, "no data remain in db, stop poll!");
        return false;
    }
}
