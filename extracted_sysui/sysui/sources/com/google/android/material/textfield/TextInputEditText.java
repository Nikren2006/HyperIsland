package com.google.android.material.textfield;

import H.e;
import H.k;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import t.AbstractC0741a;
import t.i;
import t.j;

/* JADX INFO: loaded from: classes2.dex */
public class TextInputEditText extends AppCompatEditText {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Rect f2124a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2125b;

    public TextInputEditText(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0741a.f6512l);
    }

    @Nullable
    private CharSequence getHintFromLayout() {
        TextInputLayout textInputLayout = getTextInputLayout();
        if (textInputLayout != null) {
            return textInputLayout.getHint();
        }
        return null;
    }

    @Nullable
    private TextInputLayout getTextInputLayout() {
        for (ViewParent parent = getParent(); parent instanceof View; parent = parent.getParent()) {
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }
        return null;
    }

    public final boolean a(TextInputLayout textInputLayout) {
        return textInputLayout != null && this.f2125b;
    }

    @Override // android.widget.TextView, android.view.View
    public void getFocusedRect(Rect rect) {
        super.getFocusedRect(rect);
        TextInputLayout textInputLayout = getTextInputLayout();
        if (!a(textInputLayout) || rect == null) {
            return;
        }
        textInputLayout.getFocusedRect(this.f2124a);
        rect.bottom = this.f2124a.bottom;
    }

    @Override // android.view.View
    public boolean getGlobalVisibleRect(Rect rect, Point point) {
        TextInputLayout textInputLayout = getTextInputLayout();
        if (!a(textInputLayout)) {
            return super.getGlobalVisibleRect(rect, point);
        }
        boolean globalVisibleRect = textInputLayout.getGlobalVisibleRect(rect, point);
        if (globalVisibleRect && point != null) {
            point.offset(-getScrollX(), -getScrollY());
        }
        return globalVisibleRect;
    }

    @Override // android.widget.TextView
    @Nullable
    public CharSequence getHint() {
        TextInputLayout textInputLayout = getTextInputLayout();
        return (textInputLayout == null || !textInputLayout.R()) ? super.getHint() : textInputLayout.getHint();
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout textInputLayout = getTextInputLayout();
        if (textInputLayout != null && textInputLayout.R() && super.getHint() == null && e.b()) {
            setHint("");
        }
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (inputConnectionOnCreateInputConnection != null && editorInfo.hintText == null) {
            editorInfo.hintText = getHintFromLayout();
        }
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        getTextInputLayout();
    }

    @Override // android.view.View
    public boolean requestRectangleOnScreen(Rect rect) {
        TextInputLayout textInputLayout = getTextInputLayout();
        if (!a(textInputLayout) || rect == null) {
            return super.requestRectangleOnScreen(rect);
        }
        this.f2124a.set(rect.left, rect.top, rect.right, rect.bottom + (textInputLayout.getHeight() - getHeight()));
        return super.requestRectangleOnScreen(this.f2124a);
    }

    public void setTextInputLayoutFocusedRectEnabled(boolean z2) {
        this.f2125b = z2;
    }

    public TextInputEditText(Context context, AttributeSet attributeSet, int i2) {
        super(S.a.c(context, attributeSet, i2, 0), attributeSet, i2);
        this.f2124a = new Rect();
        TypedArray typedArrayI = k.i(context, attributeSet, j.k5, i2, i.f6681d, new int[0]);
        setTextInputLayoutFocusedRectEnabled(typedArrayI.getBoolean(j.l5, false));
        typedArrayI.recycle();
    }
}
