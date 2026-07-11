package androidx.core.util;

import android.util.SizeF;
import androidx.annotation.RequiresApi;
import com.miui.maml.folme.AnimatedProperty;

/* JADX INFO: loaded from: classes.dex */
public final class SizeFCompat {
    private final float mHeight;
    private final float mWidth;

    @RequiresApi(21)
    public static final class Api21Impl {
        private Api21Impl() {
        }

        public static SizeF toSizeF(SizeFCompat sizeFCompat) {
            Preconditions.checkNotNull(sizeFCompat);
            return new SizeF(sizeFCompat.getWidth(), sizeFCompat.getHeight());
        }

        public static SizeFCompat toSizeFCompat(SizeF sizeF) {
            Preconditions.checkNotNull(sizeF);
            return new SizeFCompat(sizeF.getWidth(), sizeF.getHeight());
        }
    }

    public SizeFCompat(float f2, float f3) {
        this.mWidth = Preconditions.checkArgumentFinite(f2, "width");
        this.mHeight = Preconditions.checkArgumentFinite(f3, "height");
    }

    @RequiresApi(21)
    public static SizeFCompat toSizeFCompat(SizeF sizeF) {
        return Api21Impl.toSizeFCompat(sizeF);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SizeFCompat)) {
            return false;
        }
        SizeFCompat sizeFCompat = (SizeFCompat) obj;
        return sizeFCompat.mWidth == this.mWidth && sizeFCompat.mHeight == this.mHeight;
    }

    public float getHeight() {
        return this.mHeight;
    }

    public float getWidth() {
        return this.mWidth;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.mHeight) ^ Float.floatToIntBits(this.mWidth);
    }

    @RequiresApi(21)
    public SizeF toSizeF() {
        return Api21Impl.toSizeF(this);
    }

    public String toString() {
        return this.mWidth + AnimatedProperty.PROPERTY_NAME_X + this.mHeight;
    }
}
