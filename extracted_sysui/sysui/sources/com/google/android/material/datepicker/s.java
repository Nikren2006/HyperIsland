package com.google.android.material.datepicker;

import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public class s {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final s f2031c = new s(null, null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Long f2032a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TimeZone f2033b;

    public s(Long l2, TimeZone timeZone) {
        this.f2032a = l2;
        this.f2033b = timeZone;
    }

    public static s c() {
        return f2031c;
    }

    public Calendar a() {
        return b(this.f2033b);
    }

    public Calendar b(TimeZone timeZone) {
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l2 = this.f2032a;
        if (l2 != null) {
            calendar.setTimeInMillis(l2.longValue());
        }
        return calendar;
    }
}
