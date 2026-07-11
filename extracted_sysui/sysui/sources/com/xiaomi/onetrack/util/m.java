package com.xiaomi.onetrack.util;

import android.text.TextUtils;
import android.util.LruCache;
import com.xiaomi.onetrack.util.l;

/* JADX INFO: loaded from: classes2.dex */
final class m extends LruCache<String, l.a> {
    public m(int i2) {
        super(i2);
    }

    @Override // android.util.LruCache
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int sizeOf(String str, l.a aVar) {
        if (aVar == null || TextUtils.isEmpty(aVar.f3524a)) {
            return 0;
        }
        return aVar.f3524a.length();
    }
}
