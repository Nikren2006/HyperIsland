package h;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import d.G;
import d.InterfaceC0301b;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import p.AbstractC0724d;
import p.h;

/* JADX INFO: renamed from: h.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0400b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f4474d = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4475a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f4476b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map f4477c;

    public C0400b(Drawable.Callback callback, String str, InterfaceC0301b interfaceC0301b, Map map) {
        if (TextUtils.isEmpty(str) || str.charAt(str.length() - 1) == '/') {
            this.f4476b = str;
        } else {
            this.f4476b = str + '/';
        }
        if (callback instanceof View) {
            this.f4475a = ((View) callback).getContext();
            this.f4477c = map;
            d(interfaceC0301b);
        } else {
            AbstractC0724d.c("LottieDrawable must be inside of a view for images to work.");
            this.f4477c = new HashMap();
            this.f4475a = null;
        }
    }

    public Bitmap a(String str) {
        G g2 = (G) this.f4477c.get(str);
        if (g2 == null) {
            return null;
        }
        Bitmap bitmapA = g2.a();
        if (bitmapA != null) {
            return bitmapA;
        }
        String strB = g2.b();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        options.inDensity = 160;
        if (strB.startsWith("data:") && strB.indexOf("base64,") > 0) {
            try {
                byte[] bArrDecode = Base64.decode(strB.substring(strB.indexOf(44) + 1), 0);
                return c(str, BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length, options));
            } catch (IllegalArgumentException e2) {
                AbstractC0724d.d("data URL did not have correct base64 format.", e2);
                return null;
            }
        }
        try {
            if (TextUtils.isEmpty(this.f4476b)) {
                throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
            }
            try {
                return c(str, h.l(BitmapFactory.decodeStream(this.f4475a.getAssets().open(this.f4476b + strB), null, options), g2.e(), g2.c()));
            } catch (IllegalArgumentException e3) {
                AbstractC0724d.d("Unable to decode image.", e3);
                return null;
            }
        } catch (IOException e4) {
            AbstractC0724d.d("Unable to open asset.", e4);
            return null;
        }
    }

    public boolean b(Context context) {
        return (context == null && this.f4475a == null) || this.f4475a.equals(context);
    }

    public final Bitmap c(String str, Bitmap bitmap) {
        synchronized (f4474d) {
            ((G) this.f4477c.get(str)).f(bitmap);
        }
        return bitmap;
    }

    public void d(InterfaceC0301b interfaceC0301b) {
    }

    public Bitmap e(String str, Bitmap bitmap) {
        if (bitmap != null) {
            Bitmap bitmapA = ((G) this.f4477c.get(str)).a();
            c(str, bitmap);
            return bitmapA;
        }
        G g2 = (G) this.f4477c.get(str);
        Bitmap bitmapA2 = g2.a();
        g2.f(null);
        return bitmapA2;
    }
}
