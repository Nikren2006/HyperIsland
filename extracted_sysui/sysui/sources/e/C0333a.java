package e;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.LocaleList;
import p.AbstractC0727g;

/* JADX INFO: renamed from: e.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0333a extends Paint {
    public C0333a() {
    }

    @Override // android.graphics.Paint
    public void setAlpha(int i2) {
        super.setAlpha(AbstractC0727g.c(i2, 0, 255));
    }

    @Override // android.graphics.Paint
    public void setTextLocales(LocaleList localeList) {
    }

    public C0333a(int i2) {
        super(i2);
    }

    public C0333a(PorterDuff.Mode mode) {
        setXfermode(new PorterDuffXfermode(mode));
    }

    public C0333a(int i2, PorterDuff.Mode mode) {
        super(i2);
        setXfermode(new PorterDuffXfermode(mode));
    }
}
