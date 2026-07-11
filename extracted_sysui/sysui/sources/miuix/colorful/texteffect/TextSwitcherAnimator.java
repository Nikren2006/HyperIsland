package miuix.colorful.texteffect;

import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.Collection;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.IntValueProperty;
import miuix.animation.property.ValueProperty;

/* JADX INFO: loaded from: classes3.dex */
public class TextSwitcherAnimator {
    static final long ANIM_DELAY = 15;
    static final long ANIM_DURATION = 550;
    public static final ValueProperty<TextSwitcherAnimator> BLUR_RADIUS = new IntValueProperty("blurRadius", 1.0f);
    private int alphaInt;
    private long animDelay;
    private int blurRadius;
    private int mEffectLevel;

    @Nullable
    private IStateStyle mFolmeState;
    private final WeakReference<TextSwitcher> mHost;
    private int mId;
    private boolean mIsRunningAnim;
    private CharSequence mText;
    private float rotationX;
    private float scale;
    private float transY;

    public TextSwitcherAnimator(@NonNull TextSwitcher textSwitcher, int i2) {
        this.mId = i2;
        this.mEffectLevel = textSwitcher.getEffectLevel();
        this.mHost = new WeakReference<>(textSwitcher);
        if (this.mEffectLevel > 0) {
            this.mFolmeState = Folme.useValue(this);
        }
        this.alphaInt = 0;
        this.blurRadius = 30;
    }

    public void cancel() {
        IStateStyle iStateStyle = this.mFolmeState;
        if (iStateStyle != null) {
            iStateStyle.cancel();
            this.mFolmeState = null;
        }
        Folme.clean(this);
    }

    public void enter() {
        if (this.mFolmeState == null) {
            this.mFolmeState = Folme.useValue(this);
        }
        if (TimerTextEffectView.DEBUGGABLE) {
            Log.d(TimerTextEffectView.TAG, "animator do start id=" + this.mId + " text=" + ((Object) this.mText));
        }
        this.mIsRunningAnim = true;
        AnimConfig animConfig = new AnimConfig();
        animConfig.setDelay(this.animDelay);
        animConfig.setEase(FolmeEase.spring(0.75f, 0.35f)).addListeners(new TransitionListener() { // from class: miuix.colorful.texteffect.TextSwitcherAnimator.1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                if (TimerTextEffectView.DEBUGGABLE) {
                    Log.d(TimerTextEffectView.TAG, "animator showState onBegin id=" + TextSwitcherAnimator.this.mId + " text=" + ((Object) TextSwitcherAnimator.this.mText) + " host=" + TextSwitcherAnimator.this.mHost.get());
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                TextSwitcherAnimator.this.mIsRunningAnim = false;
                if (TimerTextEffectView.DEBUGGABLE) {
                    Log.d(TimerTextEffectView.TAG, "animator showState onCancel id=" + TextSwitcherAnimator.this.mId + " text=" + ((Object) TextSwitcherAnimator.this.mText) + " host=" + TextSwitcherAnimator.this.mHost.get());
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                TextSwitcherAnimator.this.mIsRunningAnim = false;
                if (TimerTextEffectView.DEBUGGABLE) {
                    Log.d(TimerTextEffectView.TAG, "animator showState onComplete id=" + TextSwitcherAnimator.this.mId + " text=" + ((Object) TextSwitcherAnimator.this.mText) + " host=" + TextSwitcherAnimator.this.mHost.get());
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                Object obj2 = (TextSwitcher) TextSwitcherAnimator.this.mHost.get();
                if (obj2 instanceof View) {
                    ((View) obj2).invalidate();
                }
            }
        });
        if (this.mFolmeState != null) {
            AnimState animState = new AnimState("text_enter");
            if (this.mEffectLevel >= 1) {
                animState.add(ValueProperty.ALPHA_INT, 255.0d);
                animState.add(ValueProperty.TRANSLATION_Y, 0.0d);
                animState.add(ValueProperty.SCALE, 1.0d);
                if (this.mEffectLevel >= 2) {
                    animState.add(ValueProperty.ROTATION_X, 0.0d);
                    if (this.mEffectLevel >= 3) {
                        animState.add(BLUR_RADIUS, 0.0d);
                    }
                }
            }
            this.mFolmeState.to(animState, animConfig);
            TextSwitcher textSwitcher = this.mHost.get();
            if (textSwitcher != null) {
                textSwitcher.requestAnimation();
            }
        }
    }

    public void exit() {
        if (this.mFolmeState == null) {
            this.mFolmeState = Folme.useValue(this);
        }
        if (TimerTextEffectView.DEBUGGABLE) {
            Log.d(TimerTextEffectView.TAG, "animator do start id=" + this.mId + " text=" + ((Object) this.mText));
        }
        this.mIsRunningAnim = true;
        AnimConfig animConfig = new AnimConfig();
        animConfig.setDelay(this.animDelay);
        animConfig.setEase(FolmeEase.spring(0.75f, 0.35f)).addListeners(new TransitionListener() { // from class: miuix.colorful.texteffect.TextSwitcherAnimator.2
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                if (TimerTextEffectView.DEBUGGABLE) {
                    Log.d(TimerTextEffectView.TAG, "animator hideState onBegin id=" + TextSwitcherAnimator.this.mId + " text=" + ((Object) TextSwitcherAnimator.this.mText) + " host=" + TextSwitcherAnimator.this.mHost.get());
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                TextSwitcherAnimator.this.mIsRunningAnim = false;
                if (TimerTextEffectView.DEBUGGABLE) {
                    Log.d(TimerTextEffectView.TAG, "animator hideState onCancel id=" + TextSwitcherAnimator.this.mId + " text=" + ((Object) TextSwitcherAnimator.this.mText) + " host=" + TextSwitcherAnimator.this.mHost.get());
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                TextSwitcherAnimator.this.mIsRunningAnim = false;
                if (TimerTextEffectView.DEBUGGABLE) {
                    Log.d(TimerTextEffectView.TAG, "animator hideState onComplete id=" + TextSwitcherAnimator.this.mId + " text=" + ((Object) TextSwitcherAnimator.this.mText) + " host=" + TextSwitcherAnimator.this.mHost.get());
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                Object obj2 = (TextSwitcher) TextSwitcherAnimator.this.mHost.get();
                if (obj2 instanceof View) {
                    ((View) obj2).invalidate();
                }
            }
        });
        float translationY = getTranslationY();
        setTranslationY(0.0f);
        float rotationX = getRotationX();
        setRotationX(0.0f);
        float scale = getScale();
        setScale(1.0f);
        if (this.mFolmeState != null) {
            AnimState animState = new AnimState("text_exit");
            if (this.mEffectLevel >= 1) {
                animState.add(ValueProperty.ALPHA_INT, 0.0d);
                animState.add(ValueProperty.TRANSLATION_Y, translationY);
                animState.add(ValueProperty.SCALE, scale);
                if (this.mEffectLevel >= 2) {
                    animState.add(ValueProperty.ROTATION_X, rotationX);
                    if (this.mEffectLevel >= 3) {
                        animState.add(BLUR_RADIUS, 30.0d);
                    }
                }
            }
            this.mFolmeState.to(animState, animConfig);
            TextSwitcher textSwitcher = this.mHost.get();
            if (textSwitcher != null) {
                textSwitcher.requestAnimation();
            }
        }
    }

    public int getAlphaInt() {
        return this.alphaInt;
    }

    public int getBlurRadius() {
        return this.blurRadius;
    }

    public int getEffectLevel() {
        return this.mEffectLevel;
    }

    public float getRotationX() {
        return this.rotationX;
    }

    public float getScale() {
        return this.scale;
    }

    public float getTranslationY() {
        return this.transY;
    }

    public boolean isRunningAnim() {
        return this.mIsRunningAnim;
    }

    public void setAlphaInt(int i2) {
        this.alphaInt = i2;
    }

    public void setAnimDelay(long j2) {
        this.animDelay = j2;
    }

    public void setBlurRadius(int i2) {
        this.blurRadius = i2;
    }

    public void setRotationX(float f2) {
        this.rotationX = f2;
    }

    public void setScale(float f2) {
        this.scale = f2;
    }

    public void setText(CharSequence charSequence) {
        this.mText = charSequence;
    }

    public void setTranslationY(float f2) {
        this.transY = f2;
    }
}
