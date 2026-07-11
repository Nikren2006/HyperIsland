package com.google.android.material.datepicker;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.util.ObjectsCompat;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new C0056a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m f1899a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m f1900b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f1901c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public m f1902d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1903e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1904f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f1905g;

    /* JADX INFO: renamed from: com.google.android.material.datepicker.a$a, reason: collision with other inner class name */
    public class C0056a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createFromParcel(Parcel parcel) {
            return new a((m) parcel.readParcelable(m.class.getClassLoader()), (m) parcel.readParcelable(m.class.getClassLoader()), (c) parcel.readParcelable(c.class.getClassLoader()), (m) parcel.readParcelable(m.class.getClassLoader()), parcel.readInt(), null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public a[] newArray(int i2) {
            return new a[i2];
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final long f1906f = t.a(m.r(1900, 0).f2014f);

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final long f1907g = t.a(m.r(2100, 11).f2014f);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f1908a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f1909b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Long f1910c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1911d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public c f1912e;

        public b(a aVar) {
            this.f1908a = f1906f;
            this.f1909b = f1907g;
            this.f1912e = f.q(Long.MIN_VALUE);
            this.f1908a = aVar.f1899a.f2014f;
            this.f1909b = aVar.f1900b.f2014f;
            this.f1910c = Long.valueOf(aVar.f1902d.f2014f);
            this.f1911d = aVar.f1903e;
            this.f1912e = aVar.f1901c;
        }

        public a a() {
            Bundle bundle = new Bundle();
            bundle.putParcelable("DEEP_COPY_VALIDATOR_KEY", this.f1912e);
            m mVarS = m.s(this.f1908a);
            m mVarS2 = m.s(this.f1909b);
            c cVar = (c) bundle.getParcelable("DEEP_COPY_VALIDATOR_KEY");
            Long l2 = this.f1910c;
            return new a(mVarS, mVarS2, cVar, l2 == null ? null : m.s(l2.longValue()), this.f1911d, null);
        }

        public b b(long j2) {
            this.f1910c = Long.valueOf(j2);
            return this;
        }
    }

    public interface c extends Parcelable {
        boolean g(long j2);
    }

    public /* synthetic */ a(m mVar, m mVar2, c cVar, m mVar3, int i2, C0056a c0056a) {
        this(mVar, mVar2, cVar, mVar3, i2);
    }

    public m A() {
        return this.f1899a;
    }

    public int B() {
        return this.f1904f;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f1899a.equals(aVar.f1899a) && this.f1900b.equals(aVar.f1900b) && ObjectsCompat.equals(this.f1902d, aVar.f1902d) && this.f1903e == aVar.f1903e && this.f1901c.equals(aVar.f1901c);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f1899a, this.f1900b, this.f1902d, Integer.valueOf(this.f1903e), this.f1901c});
    }

    public c v() {
        return this.f1901c;
    }

    public m w() {
        return this.f1900b;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f1899a, 0);
        parcel.writeParcelable(this.f1900b, 0);
        parcel.writeParcelable(this.f1902d, 0);
        parcel.writeParcelable(this.f1901c, 0);
        parcel.writeInt(this.f1903e);
    }

    public int x() {
        return this.f1903e;
    }

    public int y() {
        return this.f1905g;
    }

    public m z() {
        return this.f1902d;
    }

    public a(m mVar, m mVar2, c cVar, m mVar3, int i2) {
        Objects.requireNonNull(mVar, "start cannot be null");
        Objects.requireNonNull(mVar2, "end cannot be null");
        Objects.requireNonNull(cVar, "validator cannot be null");
        this.f1899a = mVar;
        this.f1900b = mVar2;
        this.f1902d = mVar3;
        this.f1903e = i2;
        this.f1901c = cVar;
        if (mVar3 != null && mVar.compareTo(mVar3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (mVar3 != null && mVar3.compareTo(mVar2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        if (i2 < 0 || i2 > t.i().getMaximum(7)) {
            throw new IllegalArgumentException("firstDayOfWeek is not valid");
        }
        this.f1905g = mVar.A(mVar2) + 1;
        this.f1904f = (mVar2.f2011c - mVar.f2011c) + 1;
    }
}
