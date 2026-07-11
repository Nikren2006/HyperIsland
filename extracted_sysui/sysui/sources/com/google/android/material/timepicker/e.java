package com.google.android.material.timepicker;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class e implements Parcelable {
    public static final Parcelable.Creator<e> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f2300a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f2301b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2302c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2303d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2304e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2305f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2306g;

    public class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e createFromParcel(Parcel parcel) {
            return new e(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public e[] newArray(int i2) {
            return new e[i2];
        }
    }

    public e(int i2, int i3, int i4, int i5) {
        this.f2303d = i2;
        this.f2304e = i3;
        this.f2305f = i4;
        this.f2302c = i5;
        this.f2306g = s(i2);
        this.f2300a = new b(59);
        this.f2301b = new b(i5 == 1 ? 23 : 12);
    }

    public static String q(Resources resources, CharSequence charSequence) {
        return r(resources, charSequence, "%02d");
    }

    public static String r(Resources resources, CharSequence charSequence, String str) {
        try {
            return String.format(resources.getConfiguration().locale, str, Integer.valueOf(Integer.parseInt(String.valueOf(charSequence))));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static int s(int i2) {
        return i2 >= 12 ? 1 : 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return this.f2303d == eVar.f2303d && this.f2304e == eVar.f2304e && this.f2302c == eVar.f2302c && this.f2305f == eVar.f2305f;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f2302c), Integer.valueOf(this.f2303d), Integer.valueOf(this.f2304e), Integer.valueOf(this.f2305f)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f2303d);
        parcel.writeInt(this.f2304e);
        parcel.writeInt(this.f2305f);
        parcel.writeInt(this.f2302c);
    }

    public e(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
    }
}
