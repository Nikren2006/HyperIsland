package Y;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TimeZone f964a = TimeZone.getTimeZone("UTC");

    public static boolean a(String str, int i2, char c2) {
        return i2 < str.length() && str.charAt(i2) == c2;
    }

    public static int b(String str, int i2) {
        while (i2 < str.length()) {
            char cCharAt = str.charAt(i2);
            if (cCharAt < '0' || cCharAt > '9') {
                return i2;
            }
            i2++;
        }
        return str.length();
    }

    public static Date c(String str, ParsePosition parsePosition) throws ParseException {
        String str2;
        int i2;
        int i3;
        int i4;
        int iD;
        int length;
        TimeZone timeZone;
        char cCharAt;
        try {
            int index = parsePosition.getIndex();
            int i5 = index + 4;
            int iD2 = d(str, index, i5);
            if (a(str, i5, '-')) {
                i5 = index + 5;
            }
            int i6 = i5 + 2;
            int iD3 = d(str, i5, i6);
            if (a(str, i6, '-')) {
                i6 = i5 + 3;
            }
            int i7 = i6 + 2;
            int iD4 = d(str, i6, i7);
            boolean zA = a(str, i7, 'T');
            if (!zA && str.length() <= i7) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(iD2, iD3 - 1, iD4);
                gregorianCalendar.setLenient(false);
                parsePosition.setIndex(i7);
                return gregorianCalendar.getTime();
            }
            if (zA) {
                int i8 = i6 + 5;
                int iD5 = d(str, i6 + 3, i8);
                if (a(str, i8, ':')) {
                    i8 = i6 + 6;
                }
                int i9 = i8 + 2;
                int iD6 = d(str, i8, i9);
                if (a(str, i9, ':')) {
                    i9 = i8 + 3;
                }
                if (str.length() <= i9 || (cCharAt = str.charAt(i9)) == 'Z' || cCharAt == '+' || cCharAt == '-') {
                    i3 = iD6;
                    i4 = 0;
                    iD = 0;
                    i7 = i9;
                    i2 = iD5;
                } else {
                    int i10 = i9 + 2;
                    iD = d(str, i9, i10);
                    if (iD > 59 && iD < 63) {
                        iD = 59;
                    }
                    if (a(str, i10, '.')) {
                        int i11 = i9 + 3;
                        int iB = b(str, i9 + 4);
                        int iMin = Math.min(iB, i9 + 6);
                        int iD7 = d(str, i11, iMin);
                        int i12 = iMin - i11;
                        if (i12 == 1) {
                            iD7 *= 100;
                        } else if (i12 == 2) {
                            iD7 *= 10;
                        }
                        i2 = iD5;
                        i7 = iB;
                        i3 = iD6;
                        i4 = iD7;
                    } else {
                        i2 = iD5;
                        i7 = i10;
                        i3 = iD6;
                        i4 = 0;
                    }
                }
            } else {
                i2 = 0;
                i3 = 0;
                i4 = 0;
                iD = 0;
            }
            if (str.length() <= i7) {
                throw new IllegalArgumentException("No time zone indicator");
            }
            char cCharAt2 = str.charAt(i7);
            if (cCharAt2 == 'Z') {
                timeZone = f964a;
                length = i7 + 1;
            } else {
                if (cCharAt2 != '+' && cCharAt2 != '-') {
                    throw new IndexOutOfBoundsException("Invalid time zone indicator '" + cCharAt2 + "'");
                }
                String strSubstring = str.substring(i7);
                if (strSubstring.length() < 5) {
                    strSubstring = strSubstring + "00";
                }
                length = i7 + strSubstring.length();
                if ("+0000".equals(strSubstring) || "+00:00".equals(strSubstring)) {
                    timeZone = f964a;
                } else {
                    String str3 = "GMT" + strSubstring;
                    TimeZone timeZone2 = TimeZone.getTimeZone(str3);
                    String id = timeZone2.getID();
                    if (!id.equals(str3) && !id.replace(MethodCodeHelper.IDENTITY_INFO_SEPARATOR, "").equals(str3)) {
                        throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str3 + " given, resolves to " + timeZone2.getID());
                    }
                    timeZone = timeZone2;
                }
            }
            GregorianCalendar gregorianCalendar2 = new GregorianCalendar(timeZone);
            gregorianCalendar2.setLenient(false);
            gregorianCalendar2.set(1, iD2);
            gregorianCalendar2.set(2, iD3 - 1);
            gregorianCalendar2.set(5, iD4);
            gregorianCalendar2.set(11, i2);
            gregorianCalendar2.set(12, i3);
            gregorianCalendar2.set(13, iD);
            gregorianCalendar2.set(14, i4);
            parsePosition.setIndex(length);
            return gregorianCalendar2.getTime();
        } catch (IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException e2) {
            if (str == null) {
                str2 = null;
            } else {
                str2 = '\"' + str + '\"';
            }
            String message = e2.getMessage();
            if (message == null || message.isEmpty()) {
                message = "(" + e2.getClass().getName() + ")";
            }
            ParseException parseException = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException.initCause(e2);
            throw parseException;
        }
    }

    public static int d(String str, int i2, int i3) {
        int i4;
        int i5;
        if (i2 < 0 || i3 > str.length() || i2 > i3) {
            throw new NumberFormatException(str);
        }
        if (i2 < i3) {
            i5 = i2 + 1;
            int iDigit = Character.digit(str.charAt(i2), 10);
            if (iDigit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i2, i3));
            }
            i4 = -iDigit;
        } else {
            i4 = 0;
            i5 = i2;
        }
        while (i5 < i3) {
            int i6 = i5 + 1;
            int iDigit2 = Character.digit(str.charAt(i5), 10);
            if (iDigit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i2, i3));
            }
            i4 = (i4 * 10) - iDigit2;
            i5 = i6;
        }
        return -i4;
    }
}
