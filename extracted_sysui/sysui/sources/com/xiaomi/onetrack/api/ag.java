package com.xiaomi.onetrack.api;

import com.xiaomi.onetrack.Configuration;

/* JADX INFO: loaded from: classes2.dex */
public class ag {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int f2790a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f2791b = 2;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f2792c = 4;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int f2793d = 8;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final int f2794e = 16;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [int] */
    public static int a(Configuration configuration) {
        if (configuration == null) {
            return 0;
        }
        boolean zIsGAIDEnable = configuration.isGAIDEnable();
        ?? r02 = zIsGAIDEnable;
        if (configuration.isIMSIEnable()) {
            r02 = (zIsGAIDEnable ? 1 : 0) | 2;
        }
        ?? r03 = r02;
        if (configuration.isIMEIEnable()) {
            r03 = (r02 == true ? 1 : 0) | 4;
        }
        ?? r04 = r03;
        if (configuration.isExceptionCatcherEnable()) {
            r04 = (r03 == true ? 1 : 0) | 8;
        }
        return configuration.isOverrideMiuiRegionSetting() ? r04 | 16 : r04;
    }
}
