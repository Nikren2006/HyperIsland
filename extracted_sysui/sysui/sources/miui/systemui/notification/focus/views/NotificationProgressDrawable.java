package miui.systemui.notification.focus.views;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.systemui.miui.notification.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes4.dex */
public final class NotificationProgressDrawable extends Drawable {
    private static final String TAG = "NotifProgressDrawable";
    private int mAlpha;
    private final Paint mFillPaint;
    private boolean mMutated;
    private final ArrayList<Part> mParts;
    private final Rect mPointRect;
    private final RectF mPointRectF;
    private final RectF mSegmentRectF;
    private State mState;
    private final Paint mStrokePaint;

    public interface Part {
    }

    public static final class Point implements Part {

        @ColorInt
        private final int mColor;
        private final boolean mFaded;

        @Nullable
        private final Drawable mIcon;
        private final int mPhase;

        public Point(@Nullable Drawable drawable) {
            this(drawable, 0, false, 1);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Point.class != obj.getClass()) {
                return false;
            }
            Point point = (Point) obj;
            return Objects.equals(this.mIcon, point.mIcon) && this.mColor == point.mColor && this.mFaded == point.mFaded;
        }

        public int getColor() {
            return this.mColor;
        }

        public boolean getFaded() {
            return this.mFaded;
        }

        @Nullable
        public Drawable getIcon() {
            return this.mIcon;
        }

        public int hashCode() {
            return Objects.hash(this.mIcon, Integer.valueOf(this.mColor), Boolean.valueOf(this.mFaded));
        }

        public String toString() {
            return "Point(icon=" + this.mIcon + ", color=" + this.mColor + ", faded=" + this.mFaded + ")";
        }

        public Point(@Nullable Drawable drawable, @ColorInt int i2, int i3) {
            this(drawable, i2, false, i3);
        }

        public Point(@Nullable Drawable drawable, @ColorInt int i2, boolean z2, int i3) {
            this.mIcon = drawable;
            this.mColor = i2;
            this.mFaded = z2;
            this.mPhase = i3;
        }
    }

    public static final class Segment implements Part {

        @ColorInt
        private final int mColor;
        private final boolean mFaded;
        private final float mFilledProgress;
        private final float mFraction;

        public Segment(float f2) {
            this(f2, 0);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Segment.class != obj.getClass()) {
                return false;
            }
            Segment segment = (Segment) obj;
            return Float.compare(this.mFraction, segment.mFraction) == 0 && this.mColor == segment.mColor && this.mFaded == segment.mFaded;
        }

        public int getColor() {
            return this.mColor;
        }

        public boolean getFaded() {
            return this.mFaded;
        }

        public float getFraction() {
            return this.mFraction;
        }

        public int hashCode() {
            return Objects.hash(Float.valueOf(this.mFraction), Integer.valueOf(this.mColor), Boolean.valueOf(this.mFaded));
        }

        public String toString() {
            return "Segment(fraction=" + this.mFraction + ", color=" + this.mColor + ", faded=" + this.mFaded + ')';
        }

        public Segment(float f2, @ColorInt int i2) {
            this(f2, i2, false, 0.0f);
        }

        public Segment(float f2, @ColorInt int i2, boolean z2, float f3) {
            this.mFraction = f2;
            this.mColor = i2;
            this.mFaded = z2;
            this.mFilledProgress = f3;
        }
    }

    public static final class State extends Drawable.ConstantState {
        int mChangingConfigurations;
        int mDensity;
        int mFadedPointRectColor;
        int mFadedSegmentColor;
        float mFadedSegmentHeight;
        float mPointInnerRadius;
        float mPointNormalRadius;
        float mPointOuterRadius;
        float mPointRadius;
        int mPointRectColor;
        float mPointRectCornerRadius;
        float mPointRectInset;
        float mPointStrokeWidth;
        float mSegPointGap;
        float mSegSegGap;
        int mSegmentColor;
        float mSegmentCornerRadius;
        float mSegmentHeight;
        float mSegmentMinWidth;
        int[] mThemeAttrs;
        int[] mThemeAttrsPoints;
        int[] mThemeAttrsSegments;

        public State() {
            this.mSegSegGap = 0.0f;
            this.mSegPointGap = 0.0f;
            this.mSegmentMinWidth = 0.0f;
            this.mDensity = 160;
        }

        private void applyDensityScaling(int i2, int i3) {
            float f2 = this.mSegSegGap;
            if (f2 > 0.0f) {
                this.mSegSegGap = NotificationProgressDrawable.scaleFromDensity(f2, i2, i3);
            }
            float f3 = this.mSegPointGap;
            if (f3 > 0.0f) {
                this.mSegPointGap = NotificationProgressDrawable.scaleFromDensity(f3, i2, i3);
            }
            float f4 = this.mSegmentMinWidth;
            if (f4 > 0.0f) {
                this.mSegmentMinWidth = NotificationProgressDrawable.scaleFromDensity(f4, i2, i3);
            }
            float f5 = this.mSegmentHeight;
            if (f5 > 0.0f) {
                this.mSegmentHeight = NotificationProgressDrawable.scaleFromDensity(f5, i2, i3);
            }
            float f6 = this.mFadedSegmentHeight;
            if (f6 > 0.0f) {
                this.mFadedSegmentHeight = NotificationProgressDrawable.scaleFromDensity(f6, i2, i3);
            }
            float f7 = this.mSegmentCornerRadius;
            if (f7 > 0.0f) {
                this.mSegmentCornerRadius = NotificationProgressDrawable.scaleFromDensity(f7, i2, i3);
            }
            float f8 = this.mPointRadius;
            if (f8 > 0.0f) {
                this.mPointRadius = NotificationProgressDrawable.scaleFromDensity(f8, i2, i3);
            }
            float f9 = this.mPointRectInset;
            if (f9 > 0.0f) {
                this.mPointRectInset = NotificationProgressDrawable.scaleFromDensity(f9, i2, i3);
            }
            float f10 = this.mPointRectCornerRadius;
            if (f10 > 0.0f) {
                this.mPointRectCornerRadius = NotificationProgressDrawable.scaleFromDensity(f10, i2, i3);
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return (this.mThemeAttrs == null && this.mThemeAttrsSegments == null && this.mThemeAttrsPoints == null && !super.canApplyTheme()) ? false : true;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            return new NotificationProgressDrawable(this, null);
        }

        public void setDensity(int i2) {
            int i3 = this.mDensity;
            if (i3 != i2) {
                this.mDensity = i2;
                applyDensityScaling(i3, i2);
            }
        }

        public void setPointRectColor(int i2, int i3) {
            this.mPointRectColor = i2;
            this.mFadedPointRectColor = i3;
        }

        public void setSegmentColor(int i2, int i3) {
            this.mSegmentColor = i2;
            this.mFadedSegmentColor = i3;
        }

        @NonNull
        public String toString() {
            return "NotificationProgressDrawable.State{mSegSegGap=" + this.mSegSegGap + ", mSegPointGap=" + this.mSegPointGap + ", mSegmentMinWidth=" + this.mSegmentMinWidth + ", mSegmentHeight=" + this.mSegmentHeight + ", mFadedSegmentHeight=" + this.mFadedSegmentHeight + ", mSegmentCornerRadius=" + this.mSegmentCornerRadius + ", mPointRadius=" + this.mPointRadius + ", mPointRectInset=" + this.mPointRectInset + ", mPointRectCornerRadius=" + this.mPointRectCornerRadius + ", mThemeAttrs=" + Arrays.toString(this.mThemeAttrs) + ", mThemeAttrsSegments=" + Arrays.toString(this.mThemeAttrsSegments) + ", mThemeAttrsPoints=" + Arrays.toString(this.mThemeAttrsPoints) + ", mDensity=" + this.mDensity + ", mSegmentColor=" + this.mSegmentColor + ", mFadedSegmentColor=" + this.mFadedSegmentColor + ", mPointRectColor=" + this.mPointRectColor + ", mFadedPointRectColor=" + this.mFadedPointRectColor + ", mPointOuterRadius=" + this.mPointOuterRadius + ", mPointNormalRadius=" + this.mPointNormalRadius + ", mPointInnerRadius=" + this.mPointInnerRadius + ", mPointStrokeWidth=" + this.mPointStrokeWidth + "}";
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(@Nullable Resources resources) {
            if (NotificationProgressDrawable.resolveDensity(resources, this.mDensity) != this.mDensity) {
                this = new State(this, resources);
            }
            return new NotificationProgressDrawable(this, resources);
        }

        public State(@NonNull State state, @Nullable Resources resources) {
            this.mSegSegGap = 0.0f;
            this.mSegPointGap = 0.0f;
            this.mSegmentMinWidth = 0.0f;
            this.mDensity = 160;
            this.mChangingConfigurations = state.mChangingConfigurations;
            this.mSegSegGap = state.mSegSegGap;
            this.mSegPointGap = state.mSegPointGap;
            this.mSegmentMinWidth = state.mSegmentMinWidth;
            this.mSegmentHeight = state.mSegmentHeight;
            this.mFadedSegmentHeight = state.mFadedSegmentHeight;
            this.mSegmentCornerRadius = state.mSegmentCornerRadius;
            this.mSegmentColor = state.mSegmentColor;
            this.mFadedSegmentColor = state.mFadedSegmentColor;
            this.mPointRadius = state.mPointRadius;
            this.mPointRectInset = state.mPointRectInset;
            this.mPointRectCornerRadius = state.mPointRectCornerRadius;
            this.mPointRectColor = state.mPointRectColor;
            this.mFadedPointRectColor = state.mFadedPointRectColor;
            this.mThemeAttrs = state.mThemeAttrs;
            this.mThemeAttrsSegments = state.mThemeAttrsSegments;
            this.mThemeAttrsPoints = state.mThemeAttrsPoints;
            int iResolveDensity = NotificationProgressDrawable.resolveDensity(resources, state.mDensity);
            this.mDensity = iResolveDensity;
            int i2 = state.mDensity;
            if (i2 != iResolveDensity) {
                applyDensityScaling(i2, iResolveDensity);
            }
        }
    }

    private void applyThemeChildElements(Resources.Theme theme) {
    }

    private void debugLog(String str, String str2) {
    }

    @ColorInt
    public static int getFadedColor(@ColorInt int i2) {
        return Color.argb(Color.alpha(i2) / 2, Color.red(i2), Color.green(i2), Color.blue(i2));
    }

    private static float getSegEndOffset(Part part, float f2, float f3, float f4) {
        if (part == null) {
            return 0.0f;
        }
        return part instanceof Point ? f3 : f4;
    }

    private static float getSegStartOffset(Part part, float f2, float f3) {
        if (part instanceof Point) {
            return f3;
        }
        return 0.0f;
    }

    private void inflateChildElements(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int depth;
        int depth2 = xmlPullParser.getDepth() + 1;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1 || ((depth = xmlPullParser.getDepth()) < depth2 && next == 3)) {
                break;
            }
            if (next == 2 && depth <= depth2) {
                String name = xmlPullParser.getName();
                if (name.equals("segments")) {
                    TypedArray typedArrayObtainAttributes = Drawable.obtainAttributes(resources, theme, attributeSet, R.styleable.NotificationProgressDrawableSegments);
                    updateSegmentsFromTypedArray(typedArrayObtainAttributes);
                    typedArrayObtainAttributes.recycle();
                } else if (name.equals("points")) {
                    TypedArray typedArrayObtainAttributes2 = Drawable.obtainAttributes(resources, theme, attributeSet, R.styleable.NotificationProgressDrawablePoints);
                    updatePointsFromTypedArray(typedArrayObtainAttributes2);
                    typedArrayObtainAttributes2.recycle();
                } else {
                    Log.w(TAG, "Bad element under NotificationProgressDrawable: " + name);
                }
            }
        }
        debugLog(TAG, "inflated child elements state=" + this.mState);
    }

    public static int resolveDensity(@Nullable Resources resources, int i2) {
        if (resources != null) {
            i2 = resources.getDisplayMetrics().densityDpi;
        }
        if (i2 == 0) {
            return 160;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float scaleFromDensity(float f2, int i2, int i3) {
        return (f2 * i3) / i2;
    }

    private void updateLocalState() {
    }

    private void updatePointsFromTypedArray(TypedArray typedArray) {
        State state = this.mState;
        state.mChangingConfigurations |= typedArray.getChangingConfigurations();
        state.mPointRadius = typedArray.getDimension(R.styleable.NotificationProgressDrawablePoints_radius, state.mPointRadius);
        state.mPointRectInset = typedArray.getDimension(R.styleable.NotificationProgressDrawablePoints_inset, state.mPointRectInset);
        state.mPointRectCornerRadius = typedArray.getDimension(R.styleable.NotificationProgressDrawablePoints_cornerRadius, state.mPointRectCornerRadius);
        state.mPointOuterRadius = typedArray.getDimension(R.styleable.NotificationProgressDrawablePoints_outerRadius, state.mPointOuterRadius);
        state.mPointNormalRadius = typedArray.getDimension(R.styleable.NotificationProgressDrawablePoints_normalRadius, state.mPointNormalRadius);
        state.mPointInnerRadius = typedArray.getDimension(R.styleable.NotificationProgressDrawablePoints_innerRadius, state.mPointInnerRadius);
        state.mPointStrokeWidth = typedArray.getDimension(R.styleable.NotificationProgressDrawablePoints_strokeWidth, state.mPointStrokeWidth);
        setPointRectDefaultColor(typedArray.getColor(R.styleable.NotificationProgressDrawablePoints_color, state.mPointRectColor), typedArray.getColor(R.styleable.NotificationProgressDrawablePoints_fadedColor, state.mFadedPointRectColor));
    }

    private void updateSegmentsFromTypedArray(TypedArray typedArray) {
        State state = this.mState;
        state.mChangingConfigurations |= typedArray.getChangingConfigurations();
        state.mSegmentMinWidth = typedArray.getDimension(R.styleable.NotificationProgressDrawableSegments_minWidth, state.mSegmentMinWidth);
        state.mSegmentHeight = typedArray.getDimension(R.styleable.NotificationProgressDrawableSegments_height, state.mSegmentHeight);
        state.mFadedSegmentHeight = typedArray.getDimension(R.styleable.NotificationProgressDrawableSegments_fadedHeight, state.mFadedSegmentHeight);
        state.mSegmentCornerRadius = typedArray.getDimension(R.styleable.NotificationProgressDrawableSegments_cornerRadius, state.mSegmentCornerRadius);
        setSegmentDefaultColor(typedArray.getColor(R.styleable.NotificationProgressDrawableSegments_color, state.mSegmentColor), typedArray.getColor(R.styleable.NotificationProgressDrawableSegments_fadedColor, state.mFadedSegmentColor));
    }

    private void updateStateFromTypedArray(TypedArray typedArray) {
        State state = this.mState;
        state.mChangingConfigurations |= typedArray.getChangingConfigurations();
        state.mSegSegGap = typedArray.getDimension(R.styleable.NotificationProgressDrawable_segSegGap, state.mSegSegGap);
        state.mSegPointGap = typedArray.getDimension(R.styleable.NotificationProgressDrawable_segPointGap, state.mSegPointGap);
    }

    @Override // android.graphics.drawable.Drawable
    public void applyTheme(@NonNull Resources.Theme theme) {
        super.applyTheme(theme);
        State state = this.mState;
        if (state == null) {
            return;
        }
        state.setDensity(resolveDensity(theme.getResources(), 0));
        applyThemeChildElements(theme);
        updateLocalState();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return this.mState.canApplyTheme() || super.canApplyTheme();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        float f2;
        int i2;
        float f3;
        String str;
        int i3;
        float f4;
        float f5;
        State state = this.mState;
        float f6 = state.mPointRadius;
        float f7 = state.mPointOuterRadius;
        float f8 = state.mPointNormalRadius;
        float f9 = state.mPointInnerRadius;
        float f10 = getBounds().left;
        float fCenterY = getBounds().centerY();
        float fWidth = getBounds().width();
        int size = this.mParts.size();
        float f11 = fWidth - ((f6 * 2.0f) * (size >> 1));
        String str2 = "draw: x=" + f10 + " totalWidth=" + fWidth + " numParts=" + size + " segmentTotalWidth=" + f11 + " segmentColor=" + this.mState.mSegmentColor;
        String str3 = TAG;
        debugLog(TAG, str2);
        int i4 = 0;
        while (i4 < size) {
            Part part = this.mParts.get(i4);
            Part part2 = i4 == 0 ? null : this.mParts.get(i4 - 1);
            int i5 = i4 + 1;
            Part part3 = i5 == size ? null : this.mParts.get(i5);
            if (part instanceof Segment) {
                Segment segment = (Segment) part;
                float f12 = segment.mFraction * f11;
                float f13 = segment.mFilledProgress * f11;
                f2 = f7;
                float segStartOffset = getSegStartOffset(part2, f6, this.mState.mSegPointGap) + f10;
                State state2 = this.mState;
                i2 = size;
                float segEndOffset = getSegEndOffset(part3, f6, state2.mSegPointGap, state2.mSegSegGap);
                debugLog(str3, " draw seg=" + segment + " cal: start=" + segStartOffset + " segWidth=" + f12 + " endOffset=" + segEndOffset + " segFilledWidth=" + f13);
                float f14 = f12 + f10;
                float f15 = f14 - segEndOffset;
                if (segStartOffset > f15) {
                    f3 = f11;
                    i3 = i5;
                    str = str3;
                } else {
                    if (segment.mFaded) {
                        f5 = this.mState.mFadedSegmentHeight;
                        f4 = 2.0f;
                    } else {
                        f4 = 2.0f;
                        f5 = this.mState.mSegmentHeight;
                    }
                    float f16 = f5 / f4;
                    float f17 = this.mState.mSegmentCornerRadius;
                    float f18 = fCenterY - f16;
                    float f19 = f16 + fCenterY;
                    f3 = f11;
                    this.mSegmentRectF.set(segStartOffset, f18, f15, f19);
                    int i6 = segment.mColor != 0 ? segment.mColor : this.mState.mSegmentColor;
                    Paint paint = this.mFillPaint;
                    if (segment.mFaded) {
                        i6 = this.mState.mFadedSegmentColor;
                    }
                    paint.setColor(i6);
                    canvas.drawRoundRect(this.mSegmentRectF, f17, f17, this.mFillPaint);
                    if (f13 > 0.0f) {
                        this.mSegmentRectF.set(segStartOffset, f18, (f13 + segStartOffset) - segEndOffset, f19);
                        this.mFillPaint.setColor(segment.mColor != 0 ? segment.mColor : this.mState.mSegmentColor);
                        canvas.drawRoundRect(this.mSegmentRectF, f17, f17, this.mFillPaint);
                    }
                    str = str3;
                    i3 = i5;
                    f10 = f14;
                }
            } else {
                f2 = f7;
                i2 = size;
                f3 = f11;
                if (part instanceof Point) {
                    float f20 = f10 + f6;
                    Point point = (Point) part;
                    debugLog(str3, " draw Point Rect=" + this.mPointRect + " point=" + point + "");
                    if (point.mIcon != null) {
                        this.mPointRect.set((int) (f20 - f6), (int) (fCenterY - f6), (int) (f20 + f6), (int) (fCenterY + f6));
                        point.mIcon.setBounds(this.mPointRect);
                        point.mIcon.draw(canvas);
                    } else {
                        int i7 = point.mPhase;
                        float f21 = i7 == 0 ? f2 : f8;
                        this.mPointRect.set((int) (f20 - f21), (int) (fCenterY - f21), (int) (f20 + f21), (int) (f21 + fCenterY));
                        this.mPointRectF.set(this.mPointRect);
                        State state3 = this.mState;
                        float f22 = state3.mPointRectInset;
                        float f23 = state3.mPointRectCornerRadius;
                        this.mPointRectF.inset(f22, f22);
                        int i8 = point.mColor != 0 ? point.mColor : this.mState.mPointRectColor;
                        this.mFillPaint.setColor(point.mFaded ? this.mState.mFadedPointRectColor : i8);
                        Paint paint2 = this.mStrokePaint;
                        if (point.mFaded) {
                            i8 = this.mState.mFadedPointRectColor;
                        }
                        paint2.setColor(i8);
                        this.mStrokePaint.setStrokeWidth(this.mState.mPointStrokeWidth);
                        if (i7 < 0) {
                            canvas.drawCircle(f20, fCenterY, f8, this.mFillPaint);
                        } else {
                            if (i7 == 0) {
                                i3 = i5;
                                str = str3;
                                canvas.drawArc(this.mPointRectF, 0.0f, 360.0f, true, this.mStrokePaint);
                                canvas.drawCircle(f20, fCenterY, f9, this.mFillPaint);
                            } else {
                                str = str3;
                                i3 = i5;
                                canvas.drawArc(this.mPointRectF, 0.0f, 360.0f, true, this.mStrokePaint);
                            }
                            f10 = f20 + f6;
                        }
                    }
                    str = str3;
                    i3 = i5;
                    f10 = f20 + f6;
                } else {
                    str = str3;
                    i3 = i5;
                }
            }
            i4 = i3;
            str3 = str;
            f7 = f2;
            size = i2;
            f11 = f3;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return this.mState.getChangingConfigurations() | super.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        this.mState.mChangingConfigurations = getChangingConfigurations();
        return this.mState;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        this.mState.setDensity(resolveDensity(resources, 0));
        TypedArray typedArrayObtainAttributes = Drawable.obtainAttributes(resources, theme, attributeSet, R.styleable.NotificationProgressDrawable);
        updateStateFromTypedArray(typedArrayObtainAttributes);
        typedArrayObtainAttributes.recycle();
        inflateChildElements(resources, xmlPullParser, attributeSet, theme);
        updateLocalState();
        debugLog(TAG, "inflated state=" + this.mState);
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            this.mState = new State(this.mState, null);
            updateLocalState();
            this.mMutated = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (this.mAlpha != i2) {
            this.mAlpha = i2;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public void setParts(List<Part> list) {
        this.mParts.clear();
        this.mParts.addAll(list);
        invalidateSelf();
    }

    public void setPointRectDefaultColor(@ColorInt int i2, @ColorInt int i3) {
        this.mState.setPointRectColor(i2, i3);
    }

    public void setSegmentDefaultColor(@ColorInt int i2, @ColorInt int i3) {
        this.mState.setSegmentColor(i2, i3);
    }

    public NotificationProgressDrawable() {
        this(new State(), null);
    }

    private static int scaleFromDensity(int i2, int i3, int i4, boolean z2) {
        if (i2 == 0 || i3 == i4) {
            return i2;
        }
        float f2 = (i4 * i2) / i3;
        if (!z2) {
            return (int) f2;
        }
        int iRound = Math.round(f2);
        return iRound != 0 ? iRound : i2 > 0 ? 1 : -1;
    }

    private NotificationProgressDrawable(@NonNull State state, @Nullable Resources resources) {
        this.mParts = new ArrayList<>();
        this.mSegmentRectF = new RectF();
        this.mPointRect = new Rect();
        this.mPointRectF = new RectF();
        Paint paint = new Paint();
        this.mFillPaint = paint;
        Paint paint2 = new Paint();
        this.mStrokePaint = paint2;
        paint.setStyle(Paint.Style.FILL);
        paint2.setStyle(Paint.Style.STROKE);
        this.mState = state;
        updateLocalState();
    }

    public void setParts(@NonNull Part... partArr) {
        setParts(Arrays.asList(partArr));
    }
}
