package com.xiaomi.onetrack.a.b;

import android.text.TextUtils;
import com.xiaomi.onetrack.util.q;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f2719a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f2720b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f2721c = 2;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f2722d = "AdMonitor";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2723e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f2724f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private long f2725g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f2726h = "";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private String f2727i = "";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private String f2728j = "";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f2729k = 0;

    public String a() {
        return this.f2728j;
    }

    public int b() {
        return this.f2723e;
    }

    public String c() {
        return this.f2724f;
    }

    public long d() {
        return this.f2725g;
    }

    public String e() {
        return this.f2726h;
    }

    public String f() {
        return this.f2727i;
    }

    public int g() {
        return this.f2729k;
    }

    public boolean h() {
        try {
            if (TextUtils.isEmpty(this.f2724f) || TextUtils.isEmpty(this.f2726h)) {
                return false;
            }
            return !TextUtils.isEmpty(this.f2727i);
        } catch (Exception e2) {
            q.a(f2722d, "check AdMonitor isValid error:" + e2.getMessage());
            return false;
        }
    }

    public void a(String str) {
        this.f2728j = str;
    }

    public void b(String str) {
        this.f2724f = str;
    }

    public void c(String str) {
        this.f2726h = str;
    }

    public void d(String str) {
        this.f2727i = str;
    }

    public void a(int i2) {
        this.f2723e = i2;
    }

    public void b(int i2) {
        this.f2729k = i2;
    }

    public void a(long j2) {
        this.f2725g = j2;
    }
}
