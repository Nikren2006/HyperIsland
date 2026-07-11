package com.google.android.material.timepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import t.g;

/* JADX INFO: loaded from: classes2.dex */
class TimePickerView extends ConstraintLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Chip f2284a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Chip f2285b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ClockHandView f2286c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ClockFaceView f2287d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final MaterialButtonToggleGroup f2288e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final View.OnClickListener f2289f;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TimePickerView.c(TimePickerView.this);
        }
    }

    public class b extends GestureDetector.SimpleOnGestureListener {
        public b() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            TimePickerView.d(TimePickerView.this);
            return false;
        }
    }

    public class c implements View.OnTouchListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ GestureDetector f2292a;

        public c(GestureDetector gestureDetector) {
            this.f2292a = gestureDetector;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (((Checkable) view).isChecked()) {
                return this.f2292a.onTouchEvent(motionEvent);
            }
            return false;
        }
    }

    public interface d {
    }

    public interface e {
    }

    public TimePickerView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static /* synthetic */ e c(TimePickerView timePickerView) {
        timePickerView.getClass();
        return null;
    }

    public static /* synthetic */ d d(TimePickerView timePickerView) {
        timePickerView.getClass();
        return null;
    }

    public final /* synthetic */ void e(MaterialButtonToggleGroup materialButtonToggleGroup, int i2, boolean z2) {
    }

    public final void f() {
        Chip chip = this.f2284a;
        int i2 = t.e.f6598E;
        chip.setTag(i2, 12);
        this.f2285b.setTag(i2, 10);
        this.f2284a.setOnClickListener(this.f2289f);
        this.f2285b.setOnClickListener(this.f2289f);
        this.f2284a.setAccessibilityClassName("android.view.View");
        this.f2285b.setAccessibilityClassName("android.view.View");
    }

    public final void g() {
        c cVar = new c(new GestureDetector(getContext(), new b()));
        this.f2284a.setOnTouchListener(cVar);
        this.f2285b.setOnTouchListener(cVar);
    }

    @Override // android.view.View
    public void onVisibilityChanged(View view, int i2) {
        super.onVisibilityChanged(view, i2);
        if (view == this && i2 == 0) {
            this.f2285b.sendAccessibilityEvent(8);
        }
    }

    public TimePickerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f2289f = new a();
        LayoutInflater.from(context).inflate(g.f6645i, this);
        this.f2287d = (ClockFaceView) findViewById(t.e.f6618i);
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById(t.e.f6621l);
        this.f2288e = materialButtonToggleGroup;
        materialButtonToggleGroup.b(new MaterialButtonToggleGroup.d() { // from class: com.google.android.material.timepicker.f
            @Override // com.google.android.material.button.MaterialButtonToggleGroup.d
            public final void a(MaterialButtonToggleGroup materialButtonToggleGroup2, int i3, boolean z2) {
                this.f2307a.e(materialButtonToggleGroup2, i3, z2);
            }
        });
        this.f2284a = (Chip) findViewById(t.e.f6624o);
        this.f2285b = (Chip) findViewById(t.e.f6622m);
        this.f2286c = (ClockHandView) findViewById(t.e.f6619j);
        g();
        f();
    }
}
