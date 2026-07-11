package miuix.animation.property;

import android.view.View;
import androidx.annotation.RequiresApi;
import com.miui.maml.folme.AnimatedProperty;
import miuix.animation.R;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ViewProperty extends FloatProperty<View> {
    public static final ViewProperty ALPHA;
    public static final ViewProperty AUTO_ALPHA;
    public static final ViewProperty BACKGROUND;
    public static final ViewProperty BIG_VIEW_SCALE_X;
    public static final ViewProperty BIG_VIEW_SCALE_Y;
    public static final ViewProperty ELEVATION;
    public static final ViewProperty FOREGROUND;
    public static final ViewProperty HEIGHT;
    public static final ViewProperty ROTATION;
    public static final ViewProperty ROTATION_X;
    public static final ViewProperty ROTATION_Y;
    public static final ViewProperty SCALE_X;
    public static final ViewProperty SCALE_Y;
    public static final ViewProperty SCROLL_X;
    public static final ViewProperty SCROLL_Y;

    @RequiresApi(api = 29)
    public static final ViewProperty TRANSITION_ALPHA;
    public static final ViewProperty TRANSLATION_X;
    public static final ViewProperty TRANSLATION_Y;
    public static final ViewProperty TRANSLATION_Z;
    public static final ViewProperty WIDTH;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public static final ViewProperty f6001X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public static final ViewProperty f6002Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public static final ViewProperty f6003Z;

    static {
        float f2 = 1.0f;
        TRANSLATION_X = new ViewProperty("translationX", f2) { // from class: miuix.animation.property.ViewProperty.1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getTranslationX();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f3) {
                view.setTranslationX(f3);
            }
        };
        TRANSLATION_Y = new ViewProperty("translationY", f2) { // from class: miuix.animation.property.ViewProperty.2
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getTranslationY();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f3) {
                view.setTranslationY(f3);
            }
        };
        TRANSLATION_Z = new ViewProperty("translationZ", f2) { // from class: miuix.animation.property.ViewProperty.3
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getTranslationZ();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f3) {
                view.setTranslationZ(f3);
            }
        };
        float f3 = 0.004f;
        SCALE_X = new ViewProperty("scaleX", f3) { // from class: miuix.animation.property.ViewProperty.4
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getScaleX();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f4) {
                view.setScaleX(f4);
            }
        };
        SCALE_Y = new ViewProperty("scaleY", f3) { // from class: miuix.animation.property.ViewProperty.5
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getScaleY();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f4) {
                view.setScaleY(f4);
            }
        };
        float f4 = 0.1f;
        ROTATION = new ViewProperty("rotation", f4) { // from class: miuix.animation.property.ViewProperty.6
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getRotation();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f5) {
                view.setRotation(f5);
            }
        };
        ROTATION_X = new ViewProperty("rotationX", f4) { // from class: miuix.animation.property.ViewProperty.7
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getRotationX();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f5) {
                view.setRotationX(f5);
            }
        };
        ROTATION_Y = new ViewProperty("rotationY", f4) { // from class: miuix.animation.property.ViewProperty.8
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getRotationY();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f5) {
                view.setRotationY(f5);
            }
        };
        f6001X = new ViewProperty(AnimatedProperty.PROPERTY_NAME_X, f2) { // from class: miuix.animation.property.ViewProperty.9
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getX();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f5) {
                view.setX(f5);
            }
        };
        f6002Y = new ViewProperty(AnimatedProperty.PROPERTY_NAME_Y, f2) { // from class: miuix.animation.property.ViewProperty.10
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getY();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f5) {
                view.setY(f5);
            }
        };
        f6003Z = new ViewProperty("z", f2) { // from class: miuix.animation.property.ViewProperty.11
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getZ();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f5) {
                view.setZ(f5);
            }
        };
        HEIGHT = new ViewProperty("height", f2) { // from class: miuix.animation.property.ViewProperty.12
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                int height = view.getHeight();
                Float f5 = (Float) view.getTag(R.id.miuix_animation_tag_set_height);
                if (f5 != null) {
                    return f5.floatValue();
                }
                if (height == 0 && ViewProperty.isInInitLayout(view)) {
                    height = view.getMeasuredHeight();
                }
                return height;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f5) {
                view.getLayoutParams().height = (int) f5;
                view.setTag(R.id.miuix_animation_tag_set_height, Float.valueOf(f5));
                view.requestLayout();
            }
        };
        WIDTH = new ViewProperty("width", f2) { // from class: miuix.animation.property.ViewProperty.13
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                int width = view.getWidth();
                Float f5 = (Float) view.getTag(R.id.miuix_animation_tag_set_width);
                if (f5 != null) {
                    return f5.floatValue();
                }
                if (width == 0 && ViewProperty.isInInitLayout(view)) {
                    width = view.getMeasuredWidth();
                }
                return width;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f5) {
                view.getLayoutParams().width = (int) f5;
                view.setTag(R.id.miuix_animation_tag_set_width, Float.valueOf(f5));
                view.requestLayout();
            }
        };
        float f5 = 0.00390625f;
        ALPHA = new ViewProperty("alpha", f5) { // from class: miuix.animation.property.ViewProperty.14
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getAlpha();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f6) {
                view.setAlpha(f6);
            }
        };
        TRANSITION_ALPHA = new ViewProperty("transitionAlpha", f5) { // from class: miuix.animation.property.ViewProperty.15
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getTransitionAlpha();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f6) {
                view.setTransitionAlpha(f6);
            }
        };
        AUTO_ALPHA = new ViewProperty("autoAlpha", f5) { // from class: miuix.animation.property.ViewProperty.16
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getAlpha();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f6) {
                view.setAlpha(f6);
                boolean z2 = Math.abs(f6) <= 0.00390625f;
                if (view.getVisibility() != 0 && f6 > 0.0f && !z2) {
                    view.setVisibility(0);
                } else if (z2) {
                    view.setVisibility(8);
                }
            }
        };
        SCROLL_X = new ViewProperty("scrollX", f2) { // from class: miuix.animation.property.ViewProperty.17
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getScrollX();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f6) {
                view.setScrollX((int) f6);
            }
        };
        SCROLL_Y = new ViewProperty("scrollY", f2) { // from class: miuix.animation.property.ViewProperty.18
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getScrollY();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f6) {
                view.setScrollY((int) f6);
            }
        };
        FOREGROUND = new ViewProperty("deprecated_foreground", f5) { // from class: miuix.animation.property.ViewProperty.19
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return 0.0f;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f6) {
            }
        };
        BACKGROUND = new ViewProperty("deprecated_background", f5) { // from class: miuix.animation.property.ViewProperty.20
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return 0.0f;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f6) {
            }
        };
        ELEVATION = new ViewProperty("elevation", f4) { // from class: miuix.animation.property.ViewProperty.21
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getElevation();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f6) {
                view.setElevation(f6);
            }
        };
        float f6 = 3.3333333E-4f;
        BIG_VIEW_SCALE_X = new ViewProperty("bigViewScaleX", f6) { // from class: miuix.animation.property.ViewProperty.22
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getScaleX();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f7) {
                view.setScaleX(f7);
            }
        };
        BIG_VIEW_SCALE_Y = new ViewProperty("bigViewScaleY", f6) { // from class: miuix.animation.property.ViewProperty.23
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                return view.getScaleY();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f7) {
                view.setScaleY(f7);
            }
        };
    }

    public ViewProperty(String str) {
        super(str, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isInInitLayout(View view) {
        return view.getTag(R.id.miuix_animation_tag_init_layout) != null;
    }

    @Override // miuix.animation.property.FloatProperty
    public String toString() {
        return "ViewProperty{name='" + this.mPropertyName + "',min='" + this.mMinVisibleChange + "'}";
    }

    public ViewProperty(String str, float f2) {
        super(str, f2);
    }
}
