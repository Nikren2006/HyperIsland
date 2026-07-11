package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.a;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class f implements a.c {
    public static final Parcelable.Creator<f> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f1927a;

    public class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public f createFromParcel(Parcel parcel) {
            return new f(parcel.readLong(), null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public f[] newArray(int i2) {
            return new f[i2];
        }
    }

    public /* synthetic */ f(long j2, a aVar) {
        this(j2);
    }

    public static f q(long j2) {
        return new f(j2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof f) && this.f1927a == ((f) obj).f1927a;
    }

    @Override // com.google.android.material.datepicker.a.c
    public boolean g(long j2) {
        return j2 >= this.f1927a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.f1927a)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.f1927a);
    }

    public f(long j2) {
        this.f1927a = j2;
    }
}
