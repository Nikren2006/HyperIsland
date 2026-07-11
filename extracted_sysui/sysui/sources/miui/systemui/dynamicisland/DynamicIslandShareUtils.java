package miui.systemui.dynamicisland;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import java.io.ByteArrayOutputStream;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandShareUtils {
    public static final DynamicIslandShareUtils INSTANCE = new DynamicIslandShareUtils();

    private DynamicIslandShareUtils() {
    }

    public static /* synthetic */ RoundedBitmapDrawable drawableAddRounded$default(DynamicIslandShareUtils dynamicIslandShareUtils, Context context, Drawable drawable, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return dynamicIslandShareUtils.drawableAddRounded(context, drawable, i2);
    }

    public final byte[] bitmapToByteArray(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public final byte[] compressImageUnder32KB(Bitmap bitmap) {
        byte[] byteArray;
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 100;
        do {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
            n.f(byteArray, "toByteArray(...)");
            i2 -= 10;
            if (byteArray.length <= 32768) {
                break;
            }
        } while (i2 > 10);
        return byteArray;
    }

    public final RoundedBitmapDrawable drawableAddRounded(Context context, Drawable drawable, int i2) {
        n.g(context, "context");
        if (drawable == null) {
            return null;
        }
        RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(context.getResources(), drawableToBitmap(drawable));
        n.f(roundedBitmapDrawableCreate, "create(...)");
        roundedBitmapDrawableCreate.setCornerRadius(i2 * 2);
        roundedBitmapDrawableCreate.setAntiAlias(true);
        return roundedBitmapDrawableCreate;
    }

    public final Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        Bitmap bitmapCreateBitmap = (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) ? Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) : Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public final byte[] iconToByteArrayAndCompress(Icon icon, Context context) {
        n.g(context, "context");
        if (icon == null) {
            return null;
        }
        return compressImageUnder32KB(drawableToBitmap(icon.loadDrawable(context)));
    }

    public final boolean isProviderAccessible(Context context, Uri uri) {
        n.g(context, "context");
        n.g(uri, "uri");
        try {
            String authority = uri.getAuthority();
            if (authority == null) {
                return false;
            }
            return context.getPackageManager().resolveContentProvider(authority, 0) != null;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }
}
