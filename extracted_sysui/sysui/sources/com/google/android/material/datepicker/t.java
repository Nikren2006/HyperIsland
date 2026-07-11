package com.google.android.material.datepicker;

import android.icu.text.DateFormat;
import android.icu.text.DisplayContext;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes2.dex */
public abstract class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AtomicReference f2034a = new AtomicReference();

    public static long a(long j2) {
        Calendar calendarI = i();
        calendarI.setTimeInMillis(j2);
        return c(calendarI).getTimeInMillis();
    }

    public static DateFormat b(String str, Locale locale) {
        DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton(str, locale);
        instanceForSkeleton.setTimeZone(h());
        instanceForSkeleton.setContext(DisplayContext.CAPITALIZATION_FOR_STANDALONE);
        return instanceForSkeleton;
    }

    public static Calendar c(Calendar calendar) {
        Calendar calendarJ = j(calendar);
        Calendar calendarI = i();
        calendarI.set(calendarJ.get(1), calendarJ.get(2), calendarJ.get(5));
        return calendarI;
    }

    public static DateFormat d(Locale locale) {
        return b("MMMMEEEEd", locale);
    }

    public static s e() {
        s sVar = (s) f2034a.get();
        return sVar == null ? s.c() : sVar;
    }

    public static TimeZone f() {
        return TimeZone.getTimeZone("UTC");
    }

    public static Calendar g() {
        Calendar calendarA = e().a();
        calendarA.set(11, 0);
        calendarA.set(12, 0);
        calendarA.set(13, 0);
        calendarA.set(14, 0);
        calendarA.setTimeZone(f());
        return calendarA;
    }

    public static android.icu.util.TimeZone h() {
        return android.icu.util.TimeZone.getTimeZone("UTC");
    }

    public static Calendar i() {
        return j(null);
    }

    public static Calendar j(Calendar calendar) {
        Calendar calendar2 = Calendar.getInstance(f());
        if (calendar == null) {
            calendar2.clear();
        } else {
            calendar2.setTimeInMillis(calendar.getTimeInMillis());
        }
        return calendar2;
    }

    public static DateFormat k(Locale locale) {
        return b("yMMMM", locale);
    }

    public static DateFormat l(Locale locale) {
        return b("yMMMMEEEEd", locale);
    }
}
