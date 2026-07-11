package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;
import android.widget.ImageButton;
import androidx.annotation.RestrictTo;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;

/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CheckableImageButton extends AppCompatImageButton implements Checkable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f2041d = {R.attr.state_checked};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2042a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2043b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2044c;

    public class a extends AccessibilityDelegateCompat {
        public a() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setChecked(CheckableImageButton.this.isChecked());
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setCheckable(CheckableImageButton.this.a());
            accessibilityNodeInfoCompat.setChecked(CheckableImageButton.this.isChecked());
        }
    }

    public static class b extends AbsSavedState {
        public static final Parcelable.Creator<b> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2046a;

        public class a implements Parcelable.ClassLoaderCreator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public b createFromParcel(Parcel parcel) {
                return new b(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public b createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new b(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public b[] newArray(int i2) {
                return new b[i2];
            }
        }

        public b(Parcelable parcelable) {
            super(parcelable);
        }

        private void q(Parcel parcel) {
            this.f2046a = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f2046a ? 1 : 0);
        }

        public b(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            q(parcel);
        }
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, androidx.appcompat.R.attr.imageButtonStyle);
    }

    public boolean a() {
        return this.f2043b;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f2042a;
    }

    @Override // android.widget.ImageView, android.view.View
    public int[] onCreateDrawableState(int i2) {
        if (!this.f2042a) {
            return super.onCreateDrawableState(i2);
        }
        int[] iArr = f2041d;
        return ImageButton.mergeDrawableStates(super.onCreateDrawableState(i2 + iArr.length), iArr);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof b)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        b bVar = (b) parcelable;
        super.onRestoreInstanceState(bVar.getSuperState());
        setChecked(bVar.f2046a);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        b bVar = new b(super.onSaveInstanceState());
        bVar.f2046a = this.f2042a;
        return bVar;
    }

    public void setCheckable(boolean z2) {
        if (this.f2043b != z2) {
            this.f2043b = z2;
            sendAccessibilityEvent(0);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z2) {
        if (!this.f2043b || this.f2042a == z2) {
            return;
        }
        this.f2042a = z2;
        refreshDrawableState();
        sendAccessibilityEvent(2048);
    }

    public void setPressable(boolean z2) {
        this.f2044c = z2;
    }

    @Override // android.view.View
    public void setPressed(boolean z2) {
        if (this.f2044c) {
            super.setPressed(z2);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.f2042a);
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f2043b = true;
        this.f2044c = true;
        ViewCompat.setAccessibilityDelegate(this, new a());
    }
}
