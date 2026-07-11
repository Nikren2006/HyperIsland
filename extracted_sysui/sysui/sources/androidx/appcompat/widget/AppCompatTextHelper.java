package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.LocaleList;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
class AppCompatTextHelper {
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int TEXT_FONT_WEIGHT_UNSPECIFIED = -1;
    private boolean mAsyncFontPending;

    @NonNull
    private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableEndTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableStartTint;
    private TintInfo mDrawableTint;
    private TintInfo mDrawableTopTint;
    private Typeface mFontTypeface;

    @NonNull
    private final TextView mView;
    private int mStyle = 0;
    private int mFontWeight = -1;

    @RequiresApi(17)
    public static class Api17Impl {
        private Api17Impl() {
        }

        @DoNotInline
        public static Drawable[] getCompoundDrawablesRelative(TextView textView) {
            return textView.getCompoundDrawablesRelative();
        }

        @DoNotInline
        public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textView, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        }

        @DoNotInline
        public static void setTextLocale(TextView textView, Locale locale) {
            textView.setTextLocale(locale);
        }
    }

    @RequiresApi(21)
    public static class Api21Impl {
        private Api21Impl() {
        }

        @DoNotInline
        public static Locale forLanguageTag(String str) {
            return Locale.forLanguageTag(str);
        }
    }

    @RequiresApi(24)
    public static class Api24Impl {
        private Api24Impl() {
        }

        @DoNotInline
        public static LocaleList forLanguageTags(String str) {
            return LocaleList.forLanguageTags(str);
        }

        @DoNotInline
        public static void setTextLocales(TextView textView, LocaleList localeList) {
            textView.setTextLocales(localeList);
        }
    }

    @RequiresApi(26)
    public static class Api26Impl {
        private Api26Impl() {
        }

        @DoNotInline
        public static int getAutoSizeStepGranularity(TextView textView) {
            return textView.getAutoSizeStepGranularity();
        }

        @DoNotInline
        public static void setAutoSizeTextTypeUniformWithConfiguration(TextView textView, int i2, int i3, int i4, int i5) {
            textView.setAutoSizeTextTypeUniformWithConfiguration(i2, i3, i4, i5);
        }

        @DoNotInline
        public static void setAutoSizeTextTypeUniformWithPresetSizes(TextView textView, int[] iArr, int i2) {
            textView.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
        }

        @DoNotInline
        public static boolean setFontVariationSettings(TextView textView, String str) {
            return textView.setFontVariationSettings(str);
        }
    }

    @RequiresApi(28)
    public static class Api28Impl {
        private Api28Impl() {
        }

        @DoNotInline
        public static Typeface create(Typeface typeface, int i2, boolean z2) {
            return Typeface.create(typeface, i2, z2);
        }
    }

    public AppCompatTextHelper(@NonNull TextView textView) {
        this.mView = textView;
        this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(textView);
    }

    private void applyCompoundDrawableTint(Drawable drawable, TintInfo tintInfo) {
        if (drawable == null || tintInfo == null) {
            return;
        }
        AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
    }

    private static TintInfo createTintInfo(Context context, AppCompatDrawableManager appCompatDrawableManager, int i2) {
        ColorStateList tintList = appCompatDrawableManager.getTintList(context, i2);
        if (tintList == null) {
            return null;
        }
        TintInfo tintInfo = new TintInfo();
        tintInfo.mHasTintList = true;
        tintInfo.mTintList = tintList;
        return tintInfo;
    }

    private void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        if (drawable5 != null || drawable6 != null) {
            Drawable[] compoundDrawablesRelative = Api17Impl.getCompoundDrawablesRelative(this.mView);
            TextView textView = this.mView;
            if (drawable5 == null) {
                drawable5 = compoundDrawablesRelative[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative[1];
            }
            if (drawable6 == null) {
                drawable6 = compoundDrawablesRelative[2];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative[3];
            }
            Api17Impl.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, drawable5, drawable2, drawable6, drawable4);
            return;
        }
        if (drawable == null && drawable2 == null && drawable3 == null && drawable4 == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative2 = Api17Impl.getCompoundDrawablesRelative(this.mView);
        Drawable drawable7 = compoundDrawablesRelative2[0];
        if (drawable7 != null || compoundDrawablesRelative2[2] != null) {
            TextView textView2 = this.mView;
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative2[1];
            }
            Drawable drawable8 = compoundDrawablesRelative2[2];
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative2[3];
            }
            Api17Impl.setCompoundDrawablesRelativeWithIntrinsicBounds(textView2, drawable7, drawable2, drawable8, drawable4);
            return;
        }
        Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
        TextView textView3 = this.mView;
        if (drawable == null) {
            drawable = compoundDrawables[0];
        }
        if (drawable2 == null) {
            drawable2 = compoundDrawables[1];
        }
        if (drawable3 == null) {
            drawable3 = compoundDrawables[2];
        }
        if (drawable4 == null) {
            drawable4 = compoundDrawables[3];
        }
        textView3.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }

    private void setCompoundTints() {
        TintInfo tintInfo = this.mDrawableTint;
        this.mDrawableLeftTint = tintInfo;
        this.mDrawableTopTint = tintInfo;
        this.mDrawableRightTint = tintInfo;
        this.mDrawableBottomTint = tintInfo;
        this.mDrawableStartTint = tintInfo;
        this.mDrawableEndTint = tintInfo;
    }

    private void setTextSizeInternal(int i2, float f2) {
        this.mAutoSizeTextHelper.setTextSizeInternal(i2, f2);
    }

    private void updateTypefaceAndStyle(Context context, TintTypedArray tintTypedArray) {
        String string;
        this.mStyle = tintTypedArray.getInt(R.styleable.TextAppearance_android_textStyle, this.mStyle);
        int i2 = tintTypedArray.getInt(R.styleable.TextAppearance_android_textFontWeight, -1);
        this.mFontWeight = i2;
        if (i2 != -1) {
            this.mStyle &= 2;
        }
        int i3 = R.styleable.TextAppearance_android_fontFamily;
        if (!tintTypedArray.hasValue(i3) && !tintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)) {
            int i4 = R.styleable.TextAppearance_android_typeface;
            if (tintTypedArray.hasValue(i4)) {
                this.mAsyncFontPending = false;
                int i5 = tintTypedArray.getInt(i4, 1);
                if (i5 == 1) {
                    this.mFontTypeface = Typeface.SANS_SERIF;
                    return;
                } else if (i5 == 2) {
                    this.mFontTypeface = Typeface.SERIF;
                    return;
                } else {
                    if (i5 != 3) {
                        return;
                    }
                    this.mFontTypeface = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.mFontTypeface = null;
        int i6 = R.styleable.TextAppearance_fontFamily;
        if (tintTypedArray.hasValue(i6)) {
            i3 = i6;
        }
        final int i7 = this.mFontWeight;
        final int i8 = this.mStyle;
        if (!context.isRestricted()) {
            final WeakReference weakReference = new WeakReference(this.mView);
            try {
                Typeface font = tintTypedArray.getFont(i3, this.mStyle, new ResourcesCompat.FontCallback() { // from class: androidx.appcompat.widget.AppCompatTextHelper.1
                    @Override // androidx.core.content.res.ResourcesCompat.FontCallback
                    /* JADX INFO: renamed from: onFontRetrievalFailed */
                    public void lambda$callbackFailAsync$1(int i9) {
                    }

                    @Override // androidx.core.content.res.ResourcesCompat.FontCallback
                    /* JADX INFO: renamed from: onFontRetrieved */
                    public void lambda$callbackSuccessAsync$0(@NonNull Typeface typeface) {
                        int i9 = i7;
                        if (i9 != -1) {
                            typeface = Api28Impl.create(typeface, i9, (i8 & 2) != 0);
                        }
                        AppCompatTextHelper.this.onAsyncTypefaceReceived(weakReference, typeface);
                    }
                });
                if (font != null) {
                    if (this.mFontWeight != -1) {
                        this.mFontTypeface = Api28Impl.create(Typeface.create(font, 0), this.mFontWeight, (this.mStyle & 2) != 0);
                    } else {
                        this.mFontTypeface = font;
                    }
                }
                this.mAsyncFontPending = this.mFontTypeface == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.mFontTypeface != null || (string = tintTypedArray.getString(i3)) == null) {
            return;
        }
        if (this.mFontWeight != -1) {
            this.mFontTypeface = Api28Impl.create(Typeface.create(string, 0), this.mFontWeight, (this.mStyle & 2) != 0);
        } else {
            this.mFontTypeface = Typeface.create(string, this.mStyle);
        }
    }

    public void applyCompoundDrawablesTints() {
        if (this.mDrawableLeftTint != null || this.mDrawableTopTint != null || this.mDrawableRightTint != null || this.mDrawableBottomTint != null) {
            Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
            applyCompoundDrawableTint(compoundDrawables[0], this.mDrawableLeftTint);
            applyCompoundDrawableTint(compoundDrawables[1], this.mDrawableTopTint);
            applyCompoundDrawableTint(compoundDrawables[2], this.mDrawableRightTint);
            applyCompoundDrawableTint(compoundDrawables[3], this.mDrawableBottomTint);
        }
        if (this.mDrawableStartTint == null && this.mDrawableEndTint == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative = Api17Impl.getCompoundDrawablesRelative(this.mView);
        applyCompoundDrawableTint(compoundDrawablesRelative[0], this.mDrawableStartTint);
        applyCompoundDrawableTint(compoundDrawablesRelative[2], this.mDrawableEndTint);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void autoSizeText() {
        this.mAutoSizeTextHelper.autoSizeText();
    }

    public int getAutoSizeMaxTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMaxTextSize();
    }

    public int getAutoSizeMinTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMinTextSize();
    }

    public int getAutoSizeStepGranularity() {
        return this.mAutoSizeTextHelper.getAutoSizeStepGranularity();
    }

    public int[] getAutoSizeTextAvailableSizes() {
        return this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
    }

    public int getAutoSizeTextType() {
        return this.mAutoSizeTextHelper.getAutoSizeTextType();
    }

    @Nullable
    public ColorStateList getCompoundDrawableTintList() {
        TintInfo tintInfo = this.mDrawableTint;
        if (tintInfo != null) {
            return tintInfo.mTintList;
        }
        return null;
    }

    @Nullable
    public PorterDuff.Mode getCompoundDrawableTintMode() {
        TintInfo tintInfo = this.mDrawableTint;
        if (tintInfo != null) {
            return tintInfo.mTintMode;
        }
        return null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public boolean isAutoSizeEnabled() {
        return this.mAutoSizeTextHelper.isAutoSizeEnabled();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ff  */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void loadFromAttributes(@androidx.annotation.Nullable android.util.AttributeSet r18, int r19) {
        /*
            Method dump skipped, instruction units count: 602
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatTextHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }

    public void onAsyncTypefaceReceived(WeakReference<TextView> weakReference, final Typeface typeface) {
        if (this.mAsyncFontPending) {
            this.mFontTypeface = typeface;
            final TextView textView = weakReference.get();
            if (textView != null) {
                if (!ViewCompat.isAttachedToWindow(textView)) {
                    textView.setTypeface(typeface, this.mStyle);
                } else {
                    final int i2 = this.mStyle;
                    textView.post(new Runnable() { // from class: androidx.appcompat.widget.AppCompatTextHelper.2
                        @Override // java.lang.Runnable
                        public void run() {
                            textView.setTypeface(typeface, i2);
                        }
                    });
                }
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            return;
        }
        autoSizeText();
    }

    public void onSetCompoundDrawables() {
        applyCompoundDrawablesTints();
    }

    public void onSetTextAppearance(Context context, int i2) {
        String string;
        TintTypedArray tintTypedArrayObtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, i2, R.styleable.TextAppearance);
        int i3 = R.styleable.TextAppearance_textAllCaps;
        if (tintTypedArrayObtainStyledAttributes.hasValue(i3)) {
            setAllCaps(tintTypedArrayObtainStyledAttributes.getBoolean(i3, false));
        }
        int i4 = R.styleable.TextAppearance_android_textSize;
        if (tintTypedArrayObtainStyledAttributes.hasValue(i4) && tintTypedArrayObtainStyledAttributes.getDimensionPixelSize(i4, -1) == 0) {
            this.mView.setTextSize(0, 0.0f);
        }
        updateTypefaceAndStyle(context, tintTypedArrayObtainStyledAttributes);
        int i5 = R.styleable.TextAppearance_fontVariationSettings;
        if (tintTypedArrayObtainStyledAttributes.hasValue(i5) && (string = tintTypedArrayObtainStyledAttributes.getString(i5)) != null) {
            Api26Impl.setFontVariationSettings(this.mView, string);
        }
        tintTypedArrayObtainStyledAttributes.recycle();
        Typeface typeface = this.mFontTypeface;
        if (typeface != null) {
            this.mView.setTypeface(typeface, this.mStyle);
        }
    }

    public void populateSurroundingTextIfNeeded(@NonNull TextView textView, @Nullable InputConnection inputConnection, @NonNull EditorInfo editorInfo) {
    }

    public void setAllCaps(boolean z2) {
        this.mView.setAllCaps(z2);
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int i2, int i3, int i4, int i5) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(i2, i3, i4, i5);
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] iArr, int i2) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
    }

    public void setAutoSizeTextTypeWithDefaults(int i2) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(i2);
    }

    public void setCompoundDrawableTintList(@Nullable ColorStateList colorStateList) {
        if (this.mDrawableTint == null) {
            this.mDrawableTint = new TintInfo();
        }
        TintInfo tintInfo = this.mDrawableTint;
        tintInfo.mTintList = colorStateList;
        tintInfo.mHasTintList = colorStateList != null;
        setCompoundTints();
    }

    public void setCompoundDrawableTintMode(@Nullable PorterDuff.Mode mode) {
        if (this.mDrawableTint == null) {
            this.mDrawableTint = new TintInfo();
        }
        TintInfo tintInfo = this.mDrawableTint;
        tintInfo.mTintMode = mode;
        tintInfo.mHasTintMode = mode != null;
        setCompoundTints();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void setTextSize(int i2, float f2) {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE || isAutoSizeEnabled()) {
            return;
        }
        setTextSizeInternal(i2, f2);
    }
}
