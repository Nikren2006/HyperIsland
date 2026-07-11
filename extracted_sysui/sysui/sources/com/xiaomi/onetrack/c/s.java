package com.xiaomi.onetrack.c;

import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f3204a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3205b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3206c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3207d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3208e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f3209f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private byte[] f3210g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f3211h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Map<String, String> f3212i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private long f3213j;

    public s(long j2, String str, String str2, String str3, String str4, String str5, byte[] bArr, Map<String, String> map, long j3, int i2) {
        this.f3204a = j2;
        this.f3208e = str;
        this.f3209f = str2;
        this.f3205b = str3;
        this.f3206c = str4;
        this.f3207d = str5;
        this.f3210g = bArr;
        this.f3212i = map;
        this.f3213j = j3;
        this.f3211h = i2;
    }

    public long a() {
        return this.f3204a;
    }

    public String b() {
        return this.f3205b;
    }

    public String c() {
        return this.f3206c;
    }

    public String d() {
        return this.f3208e;
    }

    public String e() {
        return this.f3209f;
    }

    public byte[] f() {
        return this.f3210g;
    }

    public int g() {
        return this.f3211h;
    }

    public Map<String, String> h() {
        return this.f3212i;
    }

    public long i() {
        return this.f3213j;
    }

    public String j() {
        return this.f3207d;
    }

    public String toString() {
        return "TransformDBData{mId=" + this.f3204a + ", mAppId='" + this.f3205b + "', mPackageName='" + this.f3206c + "', mEventName='" + this.f3207d + "', mProjectID='" + this.f3208e + "', mTopic='" + this.f3209f + "', mData='" + this.f3210g + "', mPriority='" + this.f3211h + "', mAttributes=" + this.f3212i + ", mTimestamp=" + this.f3213j + '}';
    }

    public void a(long j2) {
        this.f3204a = j2;
    }

    public void b(String str) {
        this.f3206c = str;
    }

    public void c(String str) {
        this.f3208e = str;
    }

    public void d(String str) {
        this.f3209f = str;
    }

    public void e(String str) {
        this.f3207d = str;
    }

    public void a(String str) {
        this.f3205b = str;
    }

    public void b(long j2) {
        this.f3213j = j2;
    }

    public void a(byte[] bArr) {
        this.f3210g = bArr;
    }

    public void a(int i2) {
        this.f3211h = i2;
    }

    public void a(Map<String, String> map) {
        this.f3212i = map;
    }
}
