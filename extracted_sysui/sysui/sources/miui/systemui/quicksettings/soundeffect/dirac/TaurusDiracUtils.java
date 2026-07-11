package miui.systemui.quicksettings.soundeffect.dirac;

import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public class TaurusDiracUtils extends DiracUtils {
    public static final String TAG = "TaurusDiracUtils";

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void setLevel(Context context, int i2, float f2) {
        Log.i(TAG, "set EQ Level: " + DiracUtils.formatStd("diracband=%d;value=%f", Integer.valueOf(i2), Float.valueOf(f2)));
        if (i2 == 0) {
            super.setLevel(context, 0, f2);
        } else if (i2 == 6) {
            super.setLevel(context, 8, f2);
        }
        super.setLevel(context, i2 + 1, f2);
    }
}
