package miui.systemui.notification;

import android.content.Context;
import android.util.AttributeSet;
import miuix.colorful.texteffect.HyperChronometer;

/* JADX INFO: loaded from: classes4.dex */
public class HyperChronometerPlugin extends HyperChronometer {
    public HyperChronometerPlugin(Context context) {
        super(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String formatDuration(java.lang.CharSequence r6) {
        /*
            if (r6 != 0) goto L4
            r6 = 0
            return r6
        L4:
            java.lang.String r6 = r6.toString()
            java.lang.String r0 = ":"
            java.lang.String[] r0 = r6.split(r0)
            r1 = 0
            int r2 = r0.length     // Catch: java.lang.Exception -> L25
            r3 = 1
            r4 = 2
            if (r2 != r4) goto L29
            r2 = r0[r1]     // Catch: java.lang.Exception -> L25
            int r2 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.Exception -> L25
            r0 = r0[r3]     // Catch: java.lang.Exception -> L21
            int r6 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Exception -> L21
            goto L61
        L21:
            r0 = move-exception
            r3 = r2
            r2 = r1
            goto L48
        L25:
            r0 = move-exception
            r2 = r1
            r3 = r2
            goto L48
        L29:
            int r2 = r0.length     // Catch: java.lang.Exception -> L25
            r5 = 3
            if (r2 != r5) goto L45
            r2 = r0[r1]     // Catch: java.lang.Exception -> L25
            int r2 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.Exception -> L25
            r3 = r0[r3]     // Catch: java.lang.Exception -> L42
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.Exception -> L42
            r0 = r0[r4]     // Catch: java.lang.Exception -> L40
            int r1 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Exception -> L40
            goto L5e
        L40:
            r0 = move-exception
            goto L48
        L42:
            r0 = move-exception
            r3 = r1
            goto L48
        L45:
            r6 = r1
            r2 = r6
            goto L61
        L48:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "formatDuration failed:"
            r4.append(r5)
            r4.append(r6)
            java.lang.String r6 = r4.toString()
            java.lang.String r4 = "HyperChronometer"
            android.util.Log.w(r4, r6, r0)
        L5e:
            r6 = r1
            r1 = r2
            r2 = r3
        L61:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            if (r1 <= 0) goto L76
            android.icu.util.Measure r3 = new android.icu.util.Measure
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            android.icu.util.TimeUnit r4 = android.icu.util.MeasureUnit.HOUR
            r3.<init>(r1, r4)
            r0.add(r3)
        L76:
            if (r2 <= 0) goto L86
            android.icu.util.Measure r1 = new android.icu.util.Measure
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            android.icu.util.TimeUnit r3 = android.icu.util.MeasureUnit.MINUTE
            r1.<init>(r2, r3)
            r0.add(r1)
        L86:
            if (r6 <= 0) goto L96
            android.icu.util.Measure r1 = new android.icu.util.Measure
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            android.icu.util.TimeUnit r2 = android.icu.util.MeasureUnit.SECOND
            r1.<init>(r6, r2)
            r0.add(r1)
        L96:
            java.util.Locale r6 = java.util.Locale.getDefault()
            android.icu.text.MeasureFormat$FormatWidth r1 = android.icu.text.MeasureFormat.FormatWidth.WIDE
            android.icu.text.MeasureFormat r6 = android.icu.text.MeasureFormat.getInstance(r6, r1)
            int r1 = r0.size()
            android.icu.util.Measure[] r1 = new android.icu.util.Measure[r1]
            java.lang.Object[] r0 = r0.toArray(r1)
            android.icu.util.Measure[] r0 = (android.icu.util.Measure[]) r0
            java.lang.String r6 = r6.formatMeasures(r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.HyperChronometerPlugin.formatDuration(java.lang.CharSequence):java.lang.String");
    }

    @Override // android.widget.Chronometer, android.view.View
    public CharSequence getContentDescription() {
        return formatDuration(getText());
    }

    public HyperChronometerPlugin(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HyperChronometerPlugin(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
