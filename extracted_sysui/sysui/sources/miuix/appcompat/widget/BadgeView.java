package miuix.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.collection.ScatterMapKt;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.FolmeObject;
import miuix.animation.base.AnimConfig;
import miuix.animation.property.ViewProperty;
import miuix.appcompat.R;
import miuix.appcompat.widget.BadgeDrawable;

/* JADX INFO: loaded from: classes3.dex */
public class BadgeView extends View {
    private BadgeAnimator mAnimator;
    private Context mContext;
    private boolean mHasNumber;
    private int mNumber;

    public class BadgeAnimator implements FolmeObject {
        final ViewProperty mBadgeAlphaProperty;
        private Folme.ObjectFolmeImpl mFolmeAnimator;

        private BadgeAnimator() {
            this.mBadgeAlphaProperty = new ViewProperty("badgeAlpha", 1.0f) { // from class: miuix.appcompat.widget.BadgeView.BadgeAnimator.1
                @Override // miuix.animation.property.FloatProperty
                public float getValue(View view) {
                    if (view.getBackground() != null) {
                        return r0.getAlpha();
                    }
                    return 0.0f;
                }

                @Override // miuix.animation.property.FloatProperty
                public void setValue(View view, float f2) {
                    Drawable background;
                    if (f2 < 0.0f || f2 > 255.0f || (background = view.getBackground()) == null) {
                        return;
                    }
                    background.setAlpha((int) f2);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBackgroundAlpha(int i2) {
            BadgeView badgeView = BadgeView.this;
            Float fValueOf = Float.valueOf(1.0f);
            Float fValueOf2 = Float.valueOf(0.5f);
            if (i2 <= 0) {
                AnimConfig animConfig = new AnimConfig();
                animConfig.setEase(FolmeEase.spring(1.0f, 0.3f));
                Folme.use((View) badgeView).to(this.mBadgeAlphaProperty, Float.valueOf(0.0f), ViewProperty.SCALE_X, fValueOf2, ViewProperty.SCALE_Y, fValueOf2, animConfig);
                BadgeView.this.setImportantForAccessibility(2);
                return;
            }
            if (badgeView.getBackground() != null && BadgeView.this.getBackground().getAlpha() == 0) {
                badgeView.setScaleX(0.5f);
                badgeView.setScaleY(0.5f);
            }
            AnimConfig animConfig2 = new AnimConfig();
            animConfig2.setEase(FolmeEase.spring(0.65f, 0.35f));
            animConfig2.setSpecial(this.mBadgeAlphaProperty, FolmeEase.spring(1.0f, 0.3f), new float[0]);
            Folme.use((View) badgeView).to(this.mBadgeAlphaProperty, Float.valueOf(255.0f), ViewProperty.SCALE_X, fValueOf, ViewProperty.SCALE_Y, fValueOf, animConfig2);
            BadgeView.this.setImportantForAccessibility(1);
        }

        @Override // miuix.animation.FolmeObject
        public Folme.ObjectFolmeImpl folme() {
            return this.mFolmeAnimator;
        }

        @Override // miuix.animation.FolmeObject
        public void setFolmeImpl(Folme.ObjectFolmeImpl objectFolmeImpl) {
            this.mFolmeAnimator = objectFolmeImpl;
        }
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.ClassLoaderCreator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: miuix.appcompat.widget.BadgeView.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }
        };
        boolean hasNumber;
        boolean isBgVisible;
        int number;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.isBgVisible ? 1 : 0);
            parcel.writeInt(this.hasNumber ? 1 : 0);
            parcel.writeInt(this.number);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.isBgVisible = parcel.readInt() != 0;
            this.hasNumber = parcel.readInt() != 0;
            this.number = parcel.readInt();
        }

        @RequiresApi(api = 24)
        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.isBgVisible = parcel.readInt() != 0;
            this.hasNumber = parcel.readInt() != 0;
            this.number = parcel.readInt();
        }
    }

    public BadgeView(Context context) {
        this(context, null);
    }

    @NonNull
    private Drawable getBackgroundInternal() {
        return (this.mHasNumber ? new BadgeDrawable(this.mContext, 0, BadgeDrawable.BadgeConfig.EXPAND_INSIDE, this.mNumber) : new BadgeDrawable(this.mContext, BadgeDrawable.BadgeConfig.SIZE_MEDIUM)).getCurrentBadgeDrawable();
    }

    private void init(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        this.mContext = context;
        this.mAnimator = new BadgeAnimator();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BadgeView, i2, i3);
        this.mHasNumber = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BadgeView_hasNumber, false);
        this.mNumber = typedArrayObtainStyledAttributes.getInt(R.styleable.BadgeView_badgeNumber, 0);
        typedArrayObtainStyledAttributes.recycle();
        if (getId() == -1) {
            setId(R.id.miuix_appcompat_badge_view);
        }
        if (getBackground() == null) {
            Drawable backgroundInternal = getBackgroundInternal();
            backgroundInternal.setAlpha(0);
            setBackground(backgroundInternal);
        }
        setImportantForAccessibility(2);
    }

    private void setBackgroundAlpha(@IntRange(from = 0, to = ScatterMapKt.Sentinel) int i2) {
        this.mAnimator.setBackgroundAlpha(i2);
    }

    public int getNumber() {
        return this.mNumber;
    }

    public boolean hasNumber() {
        return this.mHasNumber;
    }

    public void hide() {
        setBackgroundAlpha(0);
        if (this.mHasNumber) {
            this.mNumber = 0;
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        Drawable background = getBackground();
        if (background != null) {
            int intrinsicWidth = background.getIntrinsicWidth();
            int intrinsicHeight = background.getIntrinsicHeight();
            if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                setMeasuredDimension(intrinsicWidth, intrinsicHeight);
                return;
            }
        }
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            Log.w("BadgeView", "Wrong state class, expecting SavedState! This usually happens when two views of different type have the same id in the same hierarchy.");
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mHasNumber = savedState.hasNumber;
        this.mNumber = savedState.number;
        if (savedState.isBgVisible) {
            setBackground(getBackgroundInternal());
        }
    }

    @Override // android.view.View
    @Nullable
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.hasNumber = this.mHasNumber;
        savedState.number = this.mNumber;
        Drawable background = getBackground();
        savedState.isBgVisible = background != null && background.getAlpha() > 0;
        return savedState;
    }

    public void setHasNumberOrNot(boolean z2) {
        if (this.mHasNumber != z2) {
            this.mHasNumber = z2;
        }
    }

    public void setNumber(int i2) {
        Drawable background = getBackground();
        if (background == null || !this.mHasNumber || this.mNumber == i2) {
            return;
        }
        this.mNumber = i2;
        Drawable backgroundInternal = getBackgroundInternal();
        if (background.getAlpha() != 0) {
            setBackground(backgroundInternal);
            return;
        }
        backgroundInternal.setAlpha(0);
        setBackground(backgroundInternal);
        setBackgroundAlpha(255);
    }

    public void show() {
        if (this.mHasNumber || getBackground() == null) {
            return;
        }
        setBackgroundAlpha(255);
    }

    public BadgeView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BadgeView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, R.style.Widget_BadgeView);
    }

    public BadgeView(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        init(context, attributeSet, i2, i3);
    }
}
