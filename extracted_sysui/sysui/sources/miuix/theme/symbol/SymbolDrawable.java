package miuix.theme.symbol;

import android.R;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ScatterMapKt;
import androidx.core.graphics.drawable.DrawableCompat;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes5.dex */
public class SymbolDrawable extends Drawable {
    private static final int[] STATE_DISABLED = {-16842910};
    private static final int[] STATE_PRESSED = {R.attr.state_enabled, R.attr.state_pressed};
    private boolean autoMirroredCompat;
    private SymbolPaint<Paint> backgroundBrush;
    private SymbolPaint<Paint> backgroundContourBrush;
    private int backgroundContourWidthPx;
    private int compatAlpha;
    private SymbolPaint<Paint> contourBrush;
    private int contourWidthPx;
    private float disabledAlpha;
    private boolean drawBackgroundContour;
    private boolean drawContour;
    private String fontPath;
    private SymbolPaint<TextPaint> iconBrush;
    private ColorFilter iconColorFilter;
    private int iconHeight;
    private int iconOffsetXPx;
    private int iconOffsetYPx;
    private int iconSize;
    private String iconText;
    private int iconWidth;
    private boolean invalidateShadowEnabled;
    private boolean invalidationEnabled;
    private float normalAlpha;
    private Rect paddingBounds;
    private int paddingPx;
    private float pressedAlpha;
    private Resources res;
    private boolean respectFontBounds;
    private float roundedCornerRxPx;
    private float roundedCornerRyPx;

    @ColorInt
    private int shadowColorInt;
    private float shadowDxPx;
    private float shadowDyPx;
    private float shadowRadiusPx;
    private int sizeXPx;
    private int sizeYPx;
    private Rect textBound;
    private Point textBoundOffset;
    private Resources.Theme theme;

    @Nullable
    private ColorStateList tint;
    private ColorFilter tintFilter;
    private PorterDuff.Mode tintPorterMode;
    private Typeface typeface;

    public SymbolDrawable() {
        this.iconBrush = new SymbolPaint<>(new TextPaint(1));
        this.backgroundContourBrush = new SymbolPaint<>(new Paint(1));
        this.backgroundBrush = new SymbolPaint<>(new Paint(1));
        this.contourBrush = new SymbolPaint<>(new Paint(1));
        this.paddingBounds = new Rect();
        this.textBound = new Rect();
        this.textBoundOffset = new Point();
        this.tintPorterMode = PorterDuff.Mode.SRC_IN;
        this.compatAlpha = 255;
        this.autoMirroredCompat = false;
        this.invalidationEnabled = true;
        this.invalidateShadowEnabled = true;
        this.sizeXPx = -1;
        this.sizeYPx = -1;
        this.respectFontBounds = false;
        this.drawContour = false;
        this.drawBackgroundContour = false;
        this.roundedCornerRxPx = -1.0f;
        this.roundedCornerRyPx = -1.0f;
        this.paddingPx = 0;
        this.contourWidthPx = 0;
        this.backgroundContourWidthPx = 0;
        this.iconOffsetXPx = 0;
        this.iconOffsetYPx = 0;
        this.shadowRadiusPx = 0.0f;
        this.shadowDxPx = 0.0f;
        this.shadowDyPx = 0.0f;
        this.shadowColorInt = 0;
        this.normalAlpha = 0.8f;
        this.pressedAlpha = 0.5f;
        this.disabledAlpha = 0.3f;
        this.typeface = null;
    }

    private void init(Resources resources, AttributeSet attributeSet, Resources.Theme theme) {
        this.res = resources;
        this.theme = theme;
        TypedArray typedArrayObtainStyledAttributes = theme != null ? theme.obtainStyledAttributes(attributeSet, miuix.theme.R.styleable.SymbolDrawable, 0, 0) : resources.obtainAttributes(attributeSet, miuix.theme.R.styleable.SymbolDrawable);
        this.tint = typedArrayObtainStyledAttributes.getColorStateList(miuix.theme.R.styleable.SymbolDrawable_symbolTintColor);
        this.fontPath = typedArrayObtainStyledAttributes.getString(miuix.theme.R.styleable.SymbolDrawable_android_fontFamily);
        this.normalAlpha = typedArrayObtainStyledAttributes.getFloat(miuix.theme.R.styleable.SymbolDrawable_symbolNormalAlpha, 1.0f);
        this.pressedAlpha = typedArrayObtainStyledAttributes.getFloat(miuix.theme.R.styleable.SymbolDrawable_symbolPressedAlpha, 0.0f);
        this.disabledAlpha = typedArrayObtainStyledAttributes.getFloat(miuix.theme.R.styleable.SymbolDrawable_symbolDisabledAlpha, 0.0f);
        this.iconWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(miuix.theme.R.styleable.SymbolDrawable_symbolIconWidth, 0);
        this.iconHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(miuix.theme.R.styleable.SymbolDrawable_symbolIconHeight, 0);
        this.iconSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(miuix.theme.R.styleable.SymbolDrawable_symbolIconSize, 0);
        this.autoMirroredCompat = typedArrayObtainStyledAttributes.getBoolean(miuix.theme.R.styleable.SymbolDrawable_symbolAutoMirroredCompat, false);
        int i2 = miuix.theme.R.styleable.SymbolDrawable_symbolText;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            this.iconText = typedArrayObtainStyledAttributes.getString(i2);
        }
        String str = "'wght' " + HyperSymbolFont.getWeightByConfig(resources.getConfiguration());
        try {
            AssetManager assets = resources.getAssets();
            String str2 = this.fontPath;
            if (str2 == null) {
                str2 = "fonts/misymbol_vf.ttf";
            }
            this.typeface = new Typeface.Builder(assets, str2).setFontVariationSettings(str).build();
        } catch (Exception e2) {
            Log.w("MiuixSymbol", "Warning!! fontPath=" + this.fontPath + " build typeface failed: " + e2);
            this.typeface = new Typeface.Builder("fonts/misymbol_vf.ttf").setFontVariationSettings(str).build();
        }
        typedArrayObtainStyledAttributes.recycle();
        int i3 = this.iconHeight;
        if (i3 > 0 || this.iconWidth > 0) {
            this.paddingPx = (i3 - this.iconSize) / 2;
        }
        initBrushes(this.typeface);
        if (this.tint != null) {
            updateTintFilter();
        }
        setAlphaF(this.normalAlpha);
    }

    private void initBrushes(Typeface typeface) {
        ((TextPaint) this.iconBrush.getPaint()).setTypeface(typeface);
        ((TextPaint) this.iconBrush.getPaint()).setStyle(Paint.Style.FILL);
        ((TextPaint) this.iconBrush.getPaint()).setTextAlign(Paint.Align.LEFT);
        ((TextPaint) this.iconBrush.getPaint()).setUnderlineText(false);
        Paint paint = this.contourBrush.getPaint();
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        this.backgroundContourBrush.getPaint().setStyle(style);
    }

    private boolean needMirroring() {
        return this.autoMirroredCompat && DrawableCompat.getLayoutDirection(this) == 1;
    }

    private void offsetIcon(Rect rect) {
        if (this.respectFontBounds) {
            this.textBoundOffset.set(this.iconOffsetXPx, this.iconOffsetYPx);
            return;
        }
        int iWidth = (rect.width() - this.textBound.width()) / 2;
        int iHeight = (rect.height() - this.textBound.height()) / 2;
        Point point = this.textBoundOffset;
        int i2 = iWidth + this.iconOffsetXPx;
        Rect rect2 = this.textBound;
        point.set(i2 - rect2.left, (iHeight + this.iconOffsetYPx) - rect2.bottom);
    }

    private void setAlphaF(float f2) {
        setAlpha((int) (f2 * 255.0f));
    }

    private boolean toDisabledState() {
        setAlphaF(this.disabledAlpha);
        return true;
    }

    private boolean toNormalState() {
        setAlphaF(this.normalAlpha);
        return true;
    }

    private boolean toPressedState() {
        setAlphaF(this.pressedAlpha);
        return true;
    }

    private void updatePaddingBounds(Rect rect) {
        int i2 = this.paddingPx;
        if (i2 < 0 || i2 * 2 > rect.width() || this.paddingPx * 2 > rect.height()) {
            return;
        }
        Rect rect2 = this.paddingBounds;
        int i3 = rect.left;
        int i4 = this.paddingPx;
        rect2.set(i3 + i4, rect.top + i4, rect.right - i4, rect.bottom - i4);
    }

    private void updateTextBounds() {
        String str = this.iconText;
        int iHeight = this.iconSize;
        if (iHeight <= 0) {
            iHeight = this.paddingBounds.height();
        }
        ((TextPaint) this.iconBrush.getPaint()).setTextSize(iHeight);
        ((TextPaint) this.iconBrush.getPaint()).getTextBounds(str, 0, str.length(), this.textBound);
    }

    private void updateTintFilter() {
        ColorStateList colorStateList = this.tint;
        if (colorStateList == null) {
            this.tintFilter = null;
        } else {
            this.tintFilter = new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), this.tintPorterMode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void clearColorFilter() {
        this.iconColorFilter = null;
    }

    public SymbolDrawable copy(SymbolDrawable symbolDrawable, Resources resources, Resources.Theme theme, Typeface typeface, int i2, int i3, int i4, int i5, String str, boolean z2, int i6, int i7, boolean z3, boolean z4, boolean z5, float f2, float f3, int i8, int i9, int i10, int i11, int i12, float f4, float f5, float f6, int i13, ColorStateList colorStateList, PorterDuff.Mode mode, ColorFilter colorFilter) {
        SymbolDrawable symbolDrawable2 = symbolDrawable != null ? symbolDrawable : new SymbolDrawable(resources, theme, str, typeface, i2, i3, i4, colorStateList);
        symbolDrawable2.compatAlpha = i5 != 0 ? i5 : this.compatAlpha;
        symbolDrawable2.iconText = str != null ? str : this.iconText;
        symbolDrawable2.autoMirroredCompat = z2;
        int i14 = i6;
        if (i14 == -1) {
            i14 = this.sizeXPx;
        }
        symbolDrawable2.sizeXPx = i14;
        int i15 = i7;
        if (i15 == -1) {
            i15 = this.sizeYPx;
        }
        symbolDrawable2.sizeYPx = i15;
        symbolDrawable2.respectFontBounds = z3;
        symbolDrawable2.drawContour = z4;
        symbolDrawable2.drawBackgroundContour = z5;
        symbolDrawable2.roundedCornerRxPx = f2 != -1.0f ? f2 : this.roundedCornerRxPx;
        symbolDrawable2.roundedCornerRyPx = f3 != -1.0f ? f3 : this.roundedCornerRyPx;
        symbolDrawable2.paddingPx = i8 != 0 ? i8 : this.paddingPx;
        symbolDrawable2.contourWidthPx = i9 != 0 ? i9 : this.contourWidthPx;
        symbolDrawable2.backgroundContourWidthPx = i10 != 0 ? i10 : this.backgroundContourWidthPx;
        symbolDrawable2.iconOffsetXPx = i11 != 0 ? i11 : this.iconOffsetXPx;
        symbolDrawable2.iconOffsetYPx = i12 != 0 ? i12 : this.iconOffsetYPx;
        symbolDrawable2.shadowRadiusPx = f4 != 0.0f ? f4 : this.shadowRadiusPx;
        symbolDrawable2.shadowDxPx = f5 != 0.0f ? f5 : this.shadowDxPx;
        symbolDrawable2.shadowDyPx = f6 != 0.0f ? f6 : this.shadowDyPx;
        symbolDrawable2.shadowColorInt = i13 != 0 ? i13 : this.shadowColorInt;
        symbolDrawable2.tint = colorStateList != null ? colorStateList : this.tint;
        symbolDrawable2.tintPorterMode = mode != null ? mode : this.tintPorterMode;
        symbolDrawable2.iconColorFilter = colorFilter != null ? colorFilter : this.iconColorFilter;
        return symbolDrawable2;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.iconText == null) {
            return;
        }
        Rect bounds = getBounds();
        updatePaddingBounds(bounds);
        updateTextBounds();
        offsetIcon(bounds);
        if (needMirroring()) {
            canvas.translate(bounds.right - bounds.left, bounds.top);
            canvas.scale(-1.0f, 1.0f);
        } else {
            canvas.translate(bounds.left, bounds.top);
        }
        if (this.roundedCornerRyPx > -1.0f && this.roundedCornerRxPx > -1.0f) {
            if (this.drawBackgroundContour) {
                float f2 = this.backgroundContourWidthPx / 2.0f;
                RectF rectF = new RectF(f2, f2, bounds.width() - f2, bounds.height() - f2);
                canvas.drawRoundRect(rectF, this.roundedCornerRxPx, this.roundedCornerRyPx, this.backgroundBrush.getPaint());
                canvas.drawRoundRect(rectF, this.roundedCornerRxPx, this.roundedCornerRyPx, this.backgroundContourBrush.getPaint());
            } else {
                canvas.drawRoundRect(new RectF(0.0f, 0.0f, bounds.width(), bounds.height()), this.roundedCornerRxPx, this.roundedCornerRyPx, this.backgroundBrush.getPaint());
            }
        }
        TextPaint textPaint = (TextPaint) this.iconBrush.getPaint();
        ColorFilter colorFilter = this.iconColorFilter;
        if (colorFilter == null) {
            colorFilter = this.tintFilter;
        }
        textPaint.setColorFilter(colorFilter);
        if (this.respectFontBounds) {
            String str = this.iconText;
            int length = str.length();
            Point point = this.textBoundOffset;
            canvas.drawText(str, 0, length, point.x, (-this.textBound.top) + point.y, this.iconBrush.getPaint());
        } else {
            String str2 = this.iconText;
            canvas.drawText(str2, 0, str2.length(), this.textBoundOffset.x, this.textBound.height() + this.textBoundOffset.y, this.iconBrush.getPaint());
        }
        if (!needMirroring()) {
            canvas.translate(-bounds.left, -bounds.top);
        } else {
            canvas.translate(-(bounds.right - bounds.left), -bounds.top);
            canvas.scale(1.0f, -1.0f);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.compatAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        int i2 = this.iconHeight;
        return i2 == 0 ? super.getIntrinsicHeight() : i2;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        int i2 = this.iconWidth;
        return i2 == 0 ? super.getIntrinsicWidth() : i2;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        if (this.tintFilter == null && this.iconColorFilter == null) {
            int i2 = this.compatAlpha;
            if (i2 == 255) {
                return -1;
            }
            if (i2 == 0) {
                return -2;
            }
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        init(resources, attributeSet, theme);
        super.inflate(resources, xmlPullParser, attributeSet, theme);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean z2 = true;
        boolean z3 = this.backgroundContourBrush.applyState(iArr) || (this.backgroundBrush.applyState(iArr) || (this.contourBrush.applyState(iArr) || this.iconBrush.applyState(iArr)));
        if (this.tint != null) {
            updateTintFilter();
        } else {
            z2 = z3;
        }
        if (StateSet.stateSetMatches(STATE_DISABLED, iArr)) {
            toDisabledState();
            return z2;
        }
        if (StateSet.stateSetMatches(STATE_PRESSED, iArr)) {
            toPressedState();
            return z2;
        }
        toNormalState();
        return z2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(@IntRange(from = 0, to = ScatterMapKt.Sentinel) int i2) {
        this.iconBrush.setAlpha(i2);
        this.contourBrush.setAlpha(i2);
        this.backgroundBrush.setAlpha(i2);
        this.backgroundContourBrush.setAlpha(i2);
        this.compatAlpha = i2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.iconColorFilter = colorFilter;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(@Nullable int[] iArr) {
        if (iArr != null) {
            return super.setState(iArr);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(@Nullable ColorStateList colorStateList) {
        if (this.tint != colorStateList) {
            this.tint = colorStateList;
            updateTintFilter();
            invalidateSelf();
        }
    }

    public SymbolDrawable(Context context, @NonNull String str, @NonNull Typeface typeface, int i2, int i3, ColorStateList colorStateList) {
        this(context.getResources(), context.getTheme(), str, typeface, i2, i2, i3, colorStateList);
    }

    public SymbolDrawable(Resources resources, Resources.Theme theme, @NonNull String str, @NonNull Typeface typeface, int i2, int i3, int i4, ColorStateList colorStateList) {
        this.iconBrush = new SymbolPaint<>(new TextPaint(1));
        this.backgroundContourBrush = new SymbolPaint<>(new Paint(1));
        this.backgroundBrush = new SymbolPaint<>(new Paint(1));
        this.contourBrush = new SymbolPaint<>(new Paint(1));
        this.paddingBounds = new Rect();
        this.textBound = new Rect();
        this.textBoundOffset = new Point();
        this.tintPorterMode = PorterDuff.Mode.SRC_IN;
        this.compatAlpha = 255;
        this.autoMirroredCompat = false;
        this.invalidationEnabled = true;
        this.invalidateShadowEnabled = true;
        this.sizeXPx = -1;
        this.sizeYPx = -1;
        this.respectFontBounds = false;
        this.drawContour = false;
        this.drawBackgroundContour = false;
        this.roundedCornerRxPx = -1.0f;
        this.roundedCornerRyPx = -1.0f;
        this.paddingPx = 0;
        this.contourWidthPx = 0;
        this.backgroundContourWidthPx = 0;
        this.iconOffsetXPx = 0;
        this.iconOffsetYPx = 0;
        this.shadowRadiusPx = 0.0f;
        this.shadowDxPx = 0.0f;
        this.shadowDyPx = 0.0f;
        this.shadowColorInt = 0;
        this.normalAlpha = 0.8f;
        this.pressedAlpha = 0.5f;
        this.disabledAlpha = 0.3f;
        this.res = resources;
        this.theme = theme;
        this.iconText = str;
        this.typeface = typeface;
        if (i2 > 0) {
            this.iconWidth = i2;
        }
        if (i3 > 0) {
            this.iconHeight = i3;
        }
        if (i4 > 0) {
            this.iconSize = i4;
        }
        this.tint = colorStateList;
        initBrushes(typeface);
    }
}
