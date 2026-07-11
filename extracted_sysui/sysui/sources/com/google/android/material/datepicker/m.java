package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: loaded from: classes2.dex */
public final class m implements Comparable, Parcelable {
    public static final Parcelable.Creator<m> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Calendar f2009a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2010b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2011c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2012d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2013e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f2014f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f2015g;

    public class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public m createFromParcel(Parcel parcel) {
            return m.r(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public m[] newArray(int i2) {
            return new m[i2];
        }
    }

    public m(Calendar calendar) {
        calendar.set(5, 1);
        Calendar calendarC = t.c(calendar);
        this.f2009a = calendarC;
        this.f2010b = calendarC.get(2);
        this.f2011c = calendarC.get(1);
        this.f2012d = calendarC.getMaximum(7);
        this.f2013e = calendarC.getActualMaximum(5);
        this.f2014f = calendarC.getTimeInMillis();
    }

    public static m r(int i2, int i3) {
        Calendar calendarI = t.i();
        calendarI.set(1, i2);
        calendarI.set(2, i3);
        return new m(calendarI);
    }

    public static m s(long j2) {
        Calendar calendarI = t.i();
        calendarI.setTimeInMillis(j2);
        return new m(calendarI);
    }

    public static m t() {
        return new m(t.g());
    }

    public int A(m mVar) {
        if (this.f2009a instanceof GregorianCalendar) {
            return ((mVar.f2011c - this.f2011c) * 12) + (mVar.f2010b - this.f2010b);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return this.f2010b == mVar.f2010b && this.f2011c == mVar.f2011c;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f2010b), Integer.valueOf(this.f2011c)});
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public int compareTo(m mVar) {
        return this.f2009a.compareTo(mVar.f2009a);
    }

    public int u(int i2) {
        int i3 = this.f2009a.get(7);
        if (i2 <= 0) {
            i2 = this.f2009a.getFirstDayOfWeek();
        }
        int i4 = i3 - i2;
        return i4 < 0 ? i4 + this.f2012d : i4;
    }

    public long v(int i2) {
        Calendar calendarC = t.c(this.f2009a);
        calendarC.set(5, i2);
        return calendarC.getTimeInMillis();
    }

    public int w(long j2) {
        Calendar calendarC = t.c(this.f2009a);
        calendarC.setTimeInMillis(j2);
        return calendarC.get(5);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f2011c);
        parcel.writeInt(this.f2010b);
    }

    public String x() {
        if (this.f2015g == null) {
            this.f2015g = e.f(this.f2009a.getTimeInMillis());
        }
        return this.f2015g;
    }

    public long y() {
        return this.f2009a.getTimeInMillis();
    }

    public m z(int i2) {
        Calendar calendarC = t.c(this.f2009a);
        calendarC.add(2, i2);
        return new m(calendarC);
    }
}
