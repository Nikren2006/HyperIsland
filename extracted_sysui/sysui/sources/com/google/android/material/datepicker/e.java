package com.google.android.material.datepicker;

import android.content.Context;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public abstract class e {
    public static String a(Context context, long j2, boolean z2, boolean z3, boolean z4) {
        String strD = d(j2);
        if (z2) {
            strD = String.format(context.getString(t.h.f6671q), strD);
        }
        return z3 ? String.format(context.getString(t.h.f6670p), strD) : z4 ? String.format(context.getString(t.h.f6667m), strD) : strD;
    }

    public static String b(long j2) {
        return c(j2, Locale.getDefault());
    }

    public static String c(long j2, Locale locale) {
        return t.d(locale).format(new Date(j2));
    }

    public static String d(long j2) {
        return i(j2) ? b(j2) : g(j2);
    }

    public static String e(Context context, int i2) {
        return t.g().get(1) == i2 ? String.format(context.getString(t.h.f6668n), Integer.valueOf(i2)) : String.format(context.getString(t.h.f6669o), Integer.valueOf(i2));
    }

    public static String f(long j2) {
        return t.k(Locale.getDefault()).format(new Date(j2));
    }

    public static String g(long j2) {
        return h(j2, Locale.getDefault());
    }

    public static String h(long j2, Locale locale) {
        return t.l(locale).format(new Date(j2));
    }

    public static boolean i(long j2) {
        Calendar calendarG = t.g();
        Calendar calendarI = t.i();
        calendarI.setTimeInMillis(j2);
        return calendarG.get(1) == calendarI.get(1);
    }
}
