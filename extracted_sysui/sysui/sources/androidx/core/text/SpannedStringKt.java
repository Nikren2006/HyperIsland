package androidx.core.text;

import android.text.Spanned;
import android.text.SpannedString;
import androidx.exifinterface.media.ExifInterface;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class SpannedStringKt {
    public static final /* synthetic */ <T> T[] getSpans(Spanned spanned, int i2, int i3) {
        n.l(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T[]) spanned.getSpans(i2, i3, Object.class);
    }

    public static /* synthetic */ Object[] getSpans$default(Spanned spanned, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = 0;
        }
        if ((i4 & 2) != 0) {
            i3 = spanned.length();
        }
        n.l(4, ExifInterface.GPS_DIRECTION_TRUE);
        return spanned.getSpans(i2, i3, Object.class);
    }

    public static final Spanned toSpanned(CharSequence charSequence) {
        return SpannedString.valueOf(charSequence);
    }
}
