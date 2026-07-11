package miuix.pickerwidget.date;

import android.content.Context;
import android.text.format.DateFormat;
import java.util.TimeZone;
import miuix.core.util.Pools;
import miuix.pickerwidget.R;

/* JADX INFO: loaded from: classes5.dex */
public class DateUtils {
    private static final Pools.Pool<Calendar> CALENDAR_POOL = Pools.createSoftReferencePool(new Pools.Manager<Calendar>() { // from class: miuix.pickerwidget.date.DateUtils.1
        @Override // miuix.core.util.Pools.Manager
        public Calendar createInstance() {
            return new Calendar();
        }
    }, 1);
    public static final int FORMAT_12HOUR = 16;
    public static final int FORMAT_24HOUR = 32;
    public static final int FORMAT_ABBREV_ALL = 28672;
    public static final int FORMAT_ABBREV_MONTH = 4096;
    public static final int FORMAT_ABBREV_TIME = 16384;
    public static final int FORMAT_ABBREV_WEEKDAY = 8192;
    public static final int FORMAT_NO_AM_PM = 64;
    public static final int FORMAT_NUMERIC_DATE = 32768;
    public static final int FORMAT_SHOW_BRIEF_TIME = 12;
    public static final int FORMAT_SHOW_DATE = 896;
    public static final int FORMAT_SHOW_HOUR = 8;
    public static final int FORMAT_SHOW_MILLISECOND = 1;
    public static final int FORMAT_SHOW_MINUTE = 4;
    public static final int FORMAT_SHOW_MONTH = 256;
    public static final int FORMAT_SHOW_MONTH_DAY = 128;
    public static final int FORMAT_SHOW_SECOND = 2;
    public static final int FORMAT_SHOW_TIME = 15;
    public static final int FORMAT_SHOW_TIME_ZONE = 2048;
    public static final int FORMAT_SHOW_WEEKDAY = 1024;
    public static final int FORMAT_SHOW_YEAR = 512;

    public DateUtils() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class");
    }

    public static String formatDateTime(Context context, long j2, int i2) {
        StringBuilder sbAcquire = Pools.getStringBuilderPool().acquire();
        String string = formatDateTime(context, sbAcquire, j2, i2, null).toString();
        Pools.getStringBuilderPool().release(sbAcquire);
        return string;
    }

    public static String formatRelativeTime(Context context, long j2, boolean z2) {
        StringBuilder sbAcquire = Pools.getStringBuilderPool().acquire();
        String string = formatRelativeTime(context, sbAcquire, j2, z2, null).toString();
        Pools.getStringBuilderPool().release(sbAcquire);
        return string;
    }

    private static int getDatePatternResId(int i2) {
        if ((i2 & 32768) == 32768) {
            if ((i2 & 512) == 512) {
                return (i2 & 256) == 256 ? (i2 & 128) == 128 ? R.string.fmt_date_numeric_year_month_day : R.string.fmt_date_numeric_year_month : R.string.fmt_date_numeric_year;
            }
            if ((i2 & 256) == 256) {
                return (i2 & 128) == 128 ? R.string.fmt_date_numeric_month_day : R.string.fmt_date_numeric_month;
            }
            if ((i2 & 128) == 128) {
                return R.string.fmt_date_numeric_day;
            }
            throw new IllegalArgumentException("no any time date");
        }
        if ((i2 & 4096) == 4096) {
            if ((i2 & 512) == 512) {
                return (i2 & 256) == 256 ? (i2 & 128) == 128 ? R.string.fmt_date_short_year_month_day : R.string.fmt_date_short_year_month : R.string.fmt_date_year;
            }
            if ((i2 & 256) == 256) {
                return (i2 & 128) == 128 ? R.string.fmt_date_short_month_day : R.string.fmt_date_short_month;
            }
            if ((i2 & 128) == 128) {
                return R.string.fmt_date_day;
            }
            throw new IllegalArgumentException("no any time date");
        }
        if ((i2 & 512) == 512) {
            return (i2 & 256) == 256 ? (i2 & 128) == 128 ? R.string.fmt_date_long_year_month_day : R.string.fmt_date_long_year_month : R.string.fmt_date_year;
        }
        if ((i2 & 256) == 256) {
            return (i2 & 128) == 128 ? R.string.fmt_date_long_month_day : R.string.fmt_date_long_month;
        }
        if ((i2 & 128) == 128) {
            return R.string.fmt_date_day;
        }
        throw new IllegalArgumentException("no any time date");
    }

    private static int getFormatResId(int i2) {
        return (i2 & 1024) == 1024 ? (i2 & 896) != 0 ? (i2 & 15) != 0 ? (i2 & 2048) == 2048 ? R.string.fmt_weekday_date_time_timezone : R.string.fmt_weekday_date_time : (i2 & 2048) == 2048 ? R.string.fmt_weekday_date_timezone : R.string.fmt_weekday_date : (i2 & 15) != 0 ? (i2 & 2048) == 2048 ? R.string.fmt_weekday_time_timezone : R.string.fmt_weekday_time : (i2 & 2048) == 2048 ? R.string.fmt_weekday_timezone : R.string.fmt_weekday : (i2 & 896) != 0 ? (i2 & 15) != 0 ? (i2 & 2048) == 2048 ? R.string.fmt_date_time_timezone : R.string.fmt_date_time : (i2 & 2048) == 2048 ? R.string.fmt_date_timezone : R.string.fmt_date : (i2 & 15) != 0 ? (i2 & 2048) == 2048 ? R.string.fmt_time_timezone : R.string.fmt_time : (i2 & 2048) == 2048 ? R.string.fmt_timezone : R.string.empty;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003b A[PHI: r0
      0x003b: PHI (r0v8 int) = (r0v6 int), (r0v7 int), (r0v7 int), (r0v6 int) binds: [B:15:0x0028, B:17:0x0032, B:19:0x0036, B:13:0x0024] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int getTimePatternResId(miuix.pickerwidget.date.Calendar r4, int r5) {
        /*
            Method dump skipped, instruction units count: 205
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.pickerwidget.date.DateUtils.getTimePatternResId(miuix.pickerwidget.date.Calendar, int):int");
    }

    private static int getWeekdayPatternResId(int i2) {
        return (i2 & 8192) == 8192 ? R.string.fmt_weekday_short : R.string.fmt_weekday_long;
    }

    public static String formatDateTime(Context context, long j2, int i2, TimeZone timeZone) {
        StringBuilder sbAcquire = Pools.getStringBuilderPool().acquire();
        String string = formatDateTime(context, sbAcquire, j2, i2, timeZone).toString();
        Pools.getStringBuilderPool().release(sbAcquire);
        return string;
    }

    public static String formatRelativeTime(Context context, long j2, boolean z2, TimeZone timeZone) {
        StringBuilder sbAcquire = Pools.getStringBuilderPool().acquire();
        String string = formatRelativeTime(context, sbAcquire, j2, z2, timeZone).toString();
        Pools.getStringBuilderPool().release(sbAcquire);
        return string;
    }

    public static StringBuilder formatDateTime(Context context, StringBuilder sb, long j2, int i2) {
        return formatDateTime(context, sb, j2, i2, null);
    }

    public static StringBuilder formatRelativeTime(Context context, StringBuilder sb, long j2, boolean z2) {
        return formatRelativeTime(context, sb, j2, z2, null);
    }

    public static StringBuilder formatDateTime(Context context, StringBuilder sb, long j2, int i2, TimeZone timeZone) {
        if ((i2 & 16) == 0 && (i2 & 32) == 0) {
            i2 |= DateFormat.is24HourFormat(context) ? 32 : 16;
        }
        String string = context.getString(getFormatResId(i2));
        StringBuilder sbAcquire = Pools.getStringBuilderPool().acquire();
        Calendar calendarAcquire = CALENDAR_POOL.acquire();
        calendarAcquire.setTimeZone(timeZone);
        calendarAcquire.setTimeInMillis(j2);
        int length = string.length();
        for (int i3 = 0; i3 < length; i3++) {
            char cCharAt = string.charAt(i3);
            if (cCharAt == 'D') {
                sbAcquire.append(context.getString(getDatePatternResId(i2)));
            } else if (cCharAt == 'T') {
                sbAcquire.append(context.getString(getTimePatternResId(calendarAcquire, i2)));
            } else if (cCharAt != 'W') {
                sbAcquire.append(cCharAt);
            } else {
                sbAcquire.append(context.getString(getWeekdayPatternResId(i2)));
            }
        }
        calendarAcquire.format(context, sb, sbAcquire);
        Pools.getStringBuilderPool().release(sbAcquire);
        CALENDAR_POOL.release(calendarAcquire);
        return sb;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0134  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.StringBuilder formatRelativeTime(android.content.Context r16, java.lang.StringBuilder r17, long r18, boolean r20, java.util.TimeZone r21) {
        /*
            Method dump skipped, instruction units count: 357
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.pickerwidget.date.DateUtils.formatRelativeTime(android.content.Context, java.lang.StringBuilder, long, boolean, java.util.TimeZone):java.lang.StringBuilder");
    }
}
