package miuix.colorful.texteffect.formatter;

import miuix.colorful.texteffect.DateFormatterIntf;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultDateFormatter implements DateFormatterIntf {
    public static final String FORMAT_DD_HH_MM_SS = "dd:HH:mm:ss";
    public static final String FORMAT_HH_MM_SS = "HH:mm:ss";
    public static final String FORMAT_MM_SS = "mm:ss";
    static final String defaultFormatStr = "mm:ss";
    private String mFormatStr;

    public DefaultDateFormatter(String str) {
        this.mFormatStr = str;
    }

    private String formatNum(long j2) {
        if (j2 > 9) {
            return j2 + "";
        }
        return "0" + j2;
    }

    @Override // miuix.colorful.texteffect.TextChangeProcessor
    public void clear() {
    }

    @Override // miuix.colorful.texteffect.TextChangeProcessor
    public CharSequence formatContent(TimerTextEffectView timerTextEffectView, CharSequence charSequence) {
        return charSequence;
    }

    @Override // miuix.colorful.texteffect.DateFormatterIntf
    public CharSequence formatTime(TimerTextEffectView timerTextEffectView, long j2) {
        String strReplace = this.mFormatStr;
        long j3 = j2 / 1000;
        long j4 = j3 % 60;
        long j5 = j3 / 60;
        long j6 = j5 % 60;
        long j7 = j5 / 60;
        long j8 = j7 % 24;
        long j9 = j7 / 24;
        if (!strReplace.contains("dd")) {
            j8 += j9 * 24;
        } else if (j9 == 0) {
            int iIndexOf = strReplace.indexOf("dd");
            strReplace = strReplace.replace(strReplace.substring(iIndexOf, iIndexOf + 3), "");
        } else {
            strReplace = strReplace.replace("dd", formatNum(j9));
        }
        if (strReplace.contains("HH")) {
            strReplace = strReplace.replace("HH", formatNum(j8));
        } else {
            j6 += j8 * 60;
        }
        if (strReplace.contains("mm")) {
            strReplace = strReplace.replace("mm", formatNum(j6));
        } else {
            j4 += j6 * 60;
        }
        return strReplace.contains("ss") ? strReplace.replace("ss", formatNum(j4)) : strReplace;
    }

    @Override // miuix.colorful.texteffect.TextChangeProcessor
    public boolean isRunningAnim() {
        return false;
    }

    public DefaultDateFormatter() {
        this("mm:ss");
    }
}
