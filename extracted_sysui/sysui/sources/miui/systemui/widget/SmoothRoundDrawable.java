package miui.systemui.widget;

import H0.h;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.collection.ArrayMap;
import androidx.core.content.res.TypedArrayUtils;
import java.io.IOException;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.quicksettings.common.R;
import miui.systemui.util.ThemeUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes4.dex */
public final class SmoothRoundDrawable extends Drawable {
    private TypedValue colorValue;
    private final Paint paint;
    private Path path;
    private float[] radii;
    private float radius;
    private float smooth = 0.7f;
    public static final Companion Companion = new Companion(null);
    private static final ArrayMap<SmoothInfo, Path> pathCache = new ArrayMap<>();
    private static final SmoothPathProvider factory = new SmoothPathProvider();

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ Path getPath$default(Companion companion, SmoothInfo smoothInfo, boolean z2, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                z2 = true;
            }
            return companion.getPath(smoothInfo, z2);
        }

        public final void clearCache() {
            SmoothRoundDrawable.pathCache.clear();
        }

        public final Path getPath(int i2, int i3, float[] radius, double d2) {
            n.g(radius, "radius");
            return getPath$default(this, new SmoothInfo(i2, i3, radius, d2), false, 2, null);
        }

        private Companion() {
        }

        private final Path getPath(SmoothInfo smoothInfo, boolean z2) {
            Path smoothPath = (Path) SmoothRoundDrawable.pathCache.get(smoothInfo);
            if (smoothPath == null) {
                SmoothPathProvider smoothPathProvider = SmoothRoundDrawable.factory;
                smoothPathProvider.buildSmoothData(smoothInfo.getWidth(), smoothInfo.getHeight(), smoothInfo.getRadius(), smoothInfo.getSmooth());
                smoothPath = smoothPathProvider.getSmoothPath();
                if (z2) {
                    SmoothRoundDrawable.pathCache.put(smoothInfo, smoothPath);
                }
                n.d(smoothPath);
            }
            return smoothPath;
        }
    }

    public static final class SmoothInfo {
        private final int height;
        private final float[] radius;
        private final double smooth;
        private final int width;

        public SmoothInfo(int i2, int i3, float[] radius, double d2) {
            n.g(radius, "radius");
            this.width = i2;
            this.height = i3;
            this.radius = radius;
            this.smooth = d2;
        }

        public static /* synthetic */ SmoothInfo copy$default(SmoothInfo smoothInfo, int i2, int i3, float[] fArr, double d2, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i2 = smoothInfo.width;
            }
            if ((i4 & 2) != 0) {
                i3 = smoothInfo.height;
            }
            int i5 = i3;
            if ((i4 & 4) != 0) {
                fArr = smoothInfo.radius;
            }
            float[] fArr2 = fArr;
            if ((i4 & 8) != 0) {
                d2 = smoothInfo.smooth;
            }
            return smoothInfo.copy(i2, i5, fArr2, d2);
        }

        public final int component1() {
            return this.width;
        }

        public final int component2() {
            return this.height;
        }

        public final float[] component3() {
            return this.radius;
        }

        public final double component4() {
            return this.smooth;
        }

        public final SmoothInfo copy(int i2, int i3, float[] radius, double d2) {
            n.g(radius, "radius");
            return new SmoothInfo(i2, i3, radius, d2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!n.c(SmoothInfo.class, obj != null ? obj.getClass() : null)) {
                return false;
            }
            n.e(obj, "null cannot be cast to non-null type miui.systemui.widget.SmoothRoundDrawable.SmoothInfo");
            SmoothInfo smoothInfo = (SmoothInfo) obj;
            return this.width == smoothInfo.width && this.height == smoothInfo.height && Arrays.equals(this.radius, smoothInfo.radius) && this.smooth == smoothInfo.smooth;
        }

        public final int getHeight() {
            return this.height;
        }

        public final float[] getRadius() {
            return this.radius;
        }

        public final double getSmooth() {
            return this.smooth;
        }

        public final int getWidth() {
            return this.width;
        }

        public int hashCode() {
            return (((((this.width * 31) + this.height) * 31) + Arrays.hashCode(this.radius)) * 31) + Double.hashCode(this.smooth);
        }

        public String toString() {
            return "SmoothInfo(width=" + this.width + ", height=" + this.height + ", radius=" + Arrays.toString(this.radius) + ", smooth=" + this.smooth + ")";
        }
    }

    public static final class SmoothRoundState extends Drawable.ConstantState {
        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            throw new h("An operation is not implemented: Not yet implemented");
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            throw new h("An operation is not implemented: Not yet implemented");
        }
    }

    public SmoothRoundDrawable() {
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.FILL);
        this.paint = paint;
    }

    private final Path getSmoothPathFromProvider(Rect rect, float f2) {
        float[] fArr = this.radii;
        if (fArr == null) {
            float f3 = this.radius;
            fArr = new float[]{f3, f3, f3, f3, f3, f3, f3, f3};
        }
        return Companion.getPath(rect.width(), rect.height(), fArr, f2);
    }

    private final void invalidatePath() {
        Rect bounds = getBounds();
        n.f(bounds, "getBounds(...)");
        Path path = new Path(getSmoothPathFromProvider(bounds, this.smooth));
        this.path = path;
        path.offset(getBounds().left, getBounds().top);
    }

    public static /* synthetic */ void setColor$default(SmoothRoundDrawable smoothRoundDrawable, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        smoothRoundDrawable.setColor(i2, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme t2) {
        n.g(t2, "t");
        TypedValue typedValue = this.colorValue;
        if (typedValue == null) {
            return;
        }
        setColor(ThemeUtils.INSTANCE.getThemedAttrColor(t2, typedValue.data), false);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        n.g(canvas, "canvas");
        Path path = this.path;
        if (path == null) {
            n.w("path");
            path = null;
        }
        canvas.drawPath(path, this.paint);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public final float getRadius() {
        return this.radius;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources r2, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme) throws XmlPullParserException, IOException {
        Integer numValueOf;
        n.g(r2, "r");
        n.g(parser, "parser");
        n.g(attrs, "attrs");
        super.inflate(r2, parser, attrs, theme);
        TypedArray typedArrayObtainAttributes = TypedArrayUtils.obtainAttributes(r2, theme, attrs, R.styleable.SmoothRoundDrawable);
        n.f(typedArrayObtainAttributes, "obtainAttributes(...)");
        this.radius = typedArrayObtainAttributes.getDimensionPixelSize(R.styleable.SmoothRoundDrawable_android_radius, 0);
        TypedValue typedValue = new TypedValue();
        typedArrayObtainAttributes.getValue(R.styleable.SmoothRoundDrawable_android_color, typedValue);
        if (typedValue.type == 2) {
            this.colorValue = typedValue;
            numValueOf = null;
        } else {
            numValueOf = typedValue.isColorType() ? Integer.valueOf(typedValue.data) : 0;
        }
        int i2 = R.styleable.SmoothRoundDrawable_android_topLeftRadius;
        if (typedArrayObtainAttributes.hasValue(i2) || typedArrayObtainAttributes.hasValue(R.styleable.SmoothRoundDrawable_android_topRightRadius) || typedArrayObtainAttributes.hasValue(R.styleable.SmoothRoundDrawable_android_bottomRightRadius) || typedArrayObtainAttributes.hasValue(R.styleable.SmoothRoundDrawable_android_bottomLeftRadius)) {
            float dimensionPixelSize = typedArrayObtainAttributes.getDimensionPixelSize(i2, 0);
            float dimensionPixelSize2 = typedArrayObtainAttributes.getDimensionPixelSize(R.styleable.SmoothRoundDrawable_android_topRightRadius, 0);
            float dimensionPixelSize3 = typedArrayObtainAttributes.getDimensionPixelSize(R.styleable.SmoothRoundDrawable_android_bottomRightRadius, 0);
            float dimensionPixelSize4 = typedArrayObtainAttributes.getDimensionPixelSize(R.styleable.SmoothRoundDrawable_android_bottomLeftRadius, 0);
            this.radii = new float[]{dimensionPixelSize, dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize3, dimensionPixelSize4, dimensionPixelSize4};
        }
        typedArrayObtainAttributes.recycle();
        if (numValueOf != null) {
            setColor$default(this, numValueOf.intValue(), false, 2, null);
        } else if (theme != null) {
            applyTheme(theme);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        n.g(bounds, "bounds");
        invalidatePath();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (getAlpha() == i2) {
            return;
        }
        this.paint.setAlpha(i2);
        invalidateSelf();
    }

    public final void setColor(int i2, boolean z2) {
        this.paint.setColor(i2);
        if (z2) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }

    public final void setRadii(float[] fArr) {
        this.radii = fArr;
        invalidatePath();
        invalidateSelf();
    }

    public final void setRadius(float f2) {
        this.radii = null;
        this.radius = f2;
        invalidatePath();
        invalidateSelf();
    }
}
