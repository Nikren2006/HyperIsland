package com.airbnb.lottie;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.value.e;
import d.AbstractC0300a;
import d.AbstractC0302c;
import d.AbstractC0315p;
import d.C0307h;
import d.F;
import d.H;
import d.InterfaceC0301b;
import d.J;
import d.K;
import d.L;
import d.N;
import d.O;
import d.P;
import d.Q;
import d.S;
import d.T;
import d.U;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import p.AbstractC0724d;
import p.h;

/* JADX INFO: loaded from: classes.dex */
public class LottieAnimationView extends AppCompatImageView {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final String f1361n = "LottieAnimationView";

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final H f1362o = new H() { // from class: d.f
        @Override // d.H
        public final void onResult(Object obj) {
            LottieAnimationView.n((Throwable) obj);
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final H f1363a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final H f1364b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public H f1365c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1366d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1367e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1368f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1369g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1370h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1371i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Set f1372j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Set f1373k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public N f1374l;
    private final F lottieDrawable;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C0307h f1375m;

    public class a implements H {
        public a() {
        }

        @Override // d.H
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th) {
            if (LottieAnimationView.this.f1366d != 0) {
                LottieAnimationView lottieAnimationView = LottieAnimationView.this;
                lottieAnimationView.setImageResource(lottieAnimationView.f1366d);
            }
            (LottieAnimationView.this.f1365c == null ? LottieAnimationView.f1362o : LottieAnimationView.this.f1365c).onResult(th);
        }
    }

    public class b extends com.airbnb.lottie.value.c {
        public b(e eVar) {
        }

        @Override // com.airbnb.lottie.value.c
        public Object getValue(com.airbnb.lottie.value.b bVar) {
            throw null;
        }
    }

    public static class c extends View.BaseSavedState {
        public static final Parcelable.Creator<c> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f1378a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1379b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1380c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1381d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f1382e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1383f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1384g;

        public class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public c createFromParcel(Parcel parcel) {
                return new c(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public c[] newArray(int i2) {
                return new c[i2];
            }
        }

        public /* synthetic */ c(Parcel parcel, a aVar) {
            this(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeString(this.f1378a);
            parcel.writeFloat(this.f1380c);
            parcel.writeInt(this.f1381d ? 1 : 0);
            parcel.writeString(this.f1382e);
            parcel.writeInt(this.f1383f);
            parcel.writeInt(this.f1384g);
        }

        public c(Parcelable parcelable) {
            super(parcelable);
        }

        public c(Parcel parcel) {
            super(parcel);
            this.f1378a = parcel.readString();
            this.f1380c = parcel.readFloat();
            this.f1381d = parcel.readInt() == 1;
            this.f1382e = parcel.readString();
            this.f1383f = parcel.readInt();
            this.f1384g = parcel.readInt();
        }
    }

    public enum d {
        SET_ANIMATION,
        SET_PROGRESS,
        SET_REPEAT_MODE,
        SET_REPEAT_COUNT,
        SET_IMAGE_ASSETS,
        PLAY_OPTION
    }

    public LottieAnimationView(Context context) {
        super(context);
        this.f1363a = new H() { // from class: d.d
            @Override // d.H
            public final void onResult(Object obj) {
                this.f3906a.setComposition((C0307h) obj);
            }
        };
        this.f1364b = new a();
        this.f1366d = 0;
        this.lottieDrawable = new F();
        this.f1369g = false;
        this.f1370h = false;
        this.f1371i = true;
        this.f1372j = new HashSet();
        this.f1373k = new HashSet();
        k(null, P.f3827a);
    }

    public static /* synthetic */ void n(Throwable th) {
        if (!h.k(th)) {
            throw new IllegalStateException("Unable to parse composition", th);
        }
        AbstractC0724d.d("Unable to load composition.", th);
    }

    private void setCompositionTask(N n2) {
        this.f1372j.add(d.SET_ANIMATION);
        h();
        g();
        this.f1374l = n2.d(this.f1363a).c(this.f1364b);
    }

    public void addAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.lottieDrawable.r(animatorListener);
    }

    @RequiresApi(api = 19)
    public void addAnimatorPauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.lottieDrawable.s(animatorPauseListener);
    }

    public void addAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.lottieDrawable.t(animatorUpdateListener);
    }

    public boolean addLottieOnCompositionLoadedListener(@NonNull J j2) {
        C0307h c0307h = this.f1375m;
        if (c0307h != null) {
            j2.a(c0307h);
        }
        return this.f1373k.add(j2);
    }

    public <T> void addValueCallback(i.e eVar, T t2, com.airbnb.lottie.value.c cVar) {
        this.lottieDrawable.u(eVar, t2, cVar);
    }

    @MainThread
    public void cancelAnimation() {
        this.f1372j.add(d.PLAY_OPTION);
        this.lottieDrawable.x();
    }

    @Deprecated
    public void disableExtraScaleModeInFitXY() {
        this.lottieDrawable.C();
    }

    public void enableMergePathsForKitKatAndAbove(boolean z2) {
        this.lottieDrawable.E(z2);
    }

    public final void g() {
        N n2 = this.f1374l;
        if (n2 != null) {
            n2.j(this.f1363a);
            this.f1374l.i(this.f1364b);
        }
    }

    public boolean getClipToCompositionBounds() {
        return this.lottieDrawable.K();
    }

    @Nullable
    public C0307h getComposition() {
        return this.f1375m;
    }

    public long getDuration() {
        C0307h c0307h = this.f1375m;
        if (c0307h != null) {
            return (long) c0307h.d();
        }
        return 0L;
    }

    public int getFrame() {
        return this.lottieDrawable.O();
    }

    @Nullable
    public String getImageAssetsFolder() {
        return this.lottieDrawable.Q();
    }

    public boolean getMaintainOriginalImageBounds() {
        return this.lottieDrawable.S();
    }

    public float getMaxFrame() {
        return this.lottieDrawable.T();
    }

    public float getMinFrame() {
        return this.lottieDrawable.U();
    }

    @Nullable
    public O getPerformanceTracker() {
        return this.lottieDrawable.V();
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getProgress() {
        return this.lottieDrawable.W();
    }

    public S getRenderMode() {
        return this.lottieDrawable.X();
    }

    public int getRepeatCount() {
        return this.lottieDrawable.Y();
    }

    public int getRepeatMode() {
        return this.lottieDrawable.Z();
    }

    public float getSpeed() {
        return this.lottieDrawable.a0();
    }

    public final void h() {
        this.f1375m = null;
        this.lottieDrawable.y();
    }

    public boolean hasMasks() {
        return this.lottieDrawable.d0();
    }

    public boolean hasMatte() {
        return this.lottieDrawable.e0();
    }

    public final N i(final String str) {
        return isInEditMode() ? new N(new Callable() { // from class: d.e
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f3907a.l(str);
            }
        }, true) : this.f1371i ? AbstractC0315p.j(getContext(), str) : AbstractC0315p.k(getContext(), str, null);
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        Drawable drawable = getDrawable();
        if ((drawable instanceof F) && ((F) drawable).X() == S.SOFTWARE) {
            this.lottieDrawable.invalidateSelf();
        }
    }

    @Override // android.widget.ImageView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable drawable2 = getDrawable();
        F f2 = this.lottieDrawable;
        if (drawable2 == f2) {
            super.invalidateDrawable(f2);
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public boolean isAnimating() {
        return this.lottieDrawable.g0();
    }

    public boolean isMergePathsEnabledForKitKatAndAbove() {
        return this.lottieDrawable.j0();
    }

    public final N j(final int i2) {
        return isInEditMode() ? new N(new Callable() { // from class: d.g
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f3909a.m(i2);
            }
        }, true) : this.f1371i ? AbstractC0315p.s(getContext(), i2) : AbstractC0315p.t(getContext(), i2, null);
    }

    public final void k(AttributeSet attributeSet, int i2) {
        String string;
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, Q.f3830C, i2, 0);
        this.f1371i = typedArrayObtainStyledAttributes.getBoolean(Q.f3832E, true);
        int i3 = Q.f3842O;
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i3);
        int i4 = Q.f3837J;
        boolean zHasValue2 = typedArrayObtainStyledAttributes.hasValue(i4);
        int i5 = Q.f3847T;
        boolean zHasValue3 = typedArrayObtainStyledAttributes.hasValue(i5);
        if (zHasValue && zHasValue2) {
            throw new IllegalArgumentException("lottie_rawRes and lottie_fileName cannot be used at the same time. Please use only one at once.");
        }
        if (zHasValue) {
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(i3, 0);
            if (resourceId != 0) {
                setAnimation(resourceId);
            }
        } else if (zHasValue2) {
            String string2 = typedArrayObtainStyledAttributes.getString(i4);
            if (string2 != null) {
                setAnimation(string2);
            }
        } else if (zHasValue3 && (string = typedArrayObtainStyledAttributes.getString(i5)) != null) {
            setAnimationFromUrl(string);
        }
        setFallbackResource(typedArrayObtainStyledAttributes.getResourceId(Q.f3836I, 0));
        if (typedArrayObtainStyledAttributes.getBoolean(Q.f3831D, false)) {
            this.f1370h = true;
        }
        if (typedArrayObtainStyledAttributes.getBoolean(Q.f3840M, false)) {
            this.lottieDrawable.i1(-1);
        }
        int i6 = Q.f3845R;
        if (typedArrayObtainStyledAttributes.hasValue(i6)) {
            setRepeatMode(typedArrayObtainStyledAttributes.getInt(i6, 1));
        }
        int i7 = Q.f3844Q;
        if (typedArrayObtainStyledAttributes.hasValue(i7)) {
            setRepeatCount(typedArrayObtainStyledAttributes.getInt(i7, -1));
        }
        int i8 = Q.f3846S;
        if (typedArrayObtainStyledAttributes.hasValue(i8)) {
            setSpeed(typedArrayObtainStyledAttributes.getFloat(i8, 1.0f));
        }
        int i9 = Q.f3833F;
        if (typedArrayObtainStyledAttributes.hasValue(i9)) {
            setClipToCompositionBounds(typedArrayObtainStyledAttributes.getBoolean(i9, true));
        }
        setImageAssetsFolder(typedArrayObtainStyledAttributes.getString(Q.f3839L));
        setProgress(typedArrayObtainStyledAttributes.getFloat(Q.f3841N, 0.0f));
        enableMergePathsForKitKatAndAbove(typedArrayObtainStyledAttributes.getBoolean(Q.f3835H, false));
        int i10 = Q.f3834G;
        if (typedArrayObtainStyledAttributes.hasValue(i10)) {
            addValueCallback(new i.e("**"), K.f3782K, new com.airbnb.lottie.value.c(new T(AppCompatResources.getColorStateList(getContext(), typedArrayObtainStyledAttributes.getResourceId(i10, -1)).getDefaultColor())));
        }
        int i11 = Q.f3843P;
        if (typedArrayObtainStyledAttributes.hasValue(i11)) {
            S s2 = S.AUTOMATIC;
            int iOrdinal = typedArrayObtainStyledAttributes.getInt(i11, s2.ordinal());
            if (iOrdinal >= S.values().length) {
                iOrdinal = s2.ordinal();
            }
            setRenderMode(S.values()[iOrdinal]);
        }
        setIgnoreDisabledSystemAnimations(typedArrayObtainStyledAttributes.getBoolean(Q.f3838K, false));
        typedArrayObtainStyledAttributes.recycle();
        this.lottieDrawable.m1(Boolean.valueOf(h.f(getContext()) != 0.0f));
    }

    public final /* synthetic */ L l(String str) {
        return this.f1371i ? AbstractC0315p.l(getContext(), str) : AbstractC0315p.m(getContext(), str, null);
    }

    @Deprecated
    public void loop(boolean z2) {
        this.lottieDrawable.i1(z2 ? -1 : 0);
    }

    public final /* synthetic */ L m(int i2) {
        return this.f1371i ? AbstractC0315p.u(getContext(), i2) : AbstractC0315p.v(getContext(), i2, null);
    }

    public final void o() {
        boolean zIsAnimating = isAnimating();
        setImageDrawable(null);
        setImageDrawable(this.lottieDrawable);
        if (zIsAnimating) {
            this.lottieDrawable.I0();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode() || !this.f1370h) {
            return;
        }
        this.lottieDrawable.A0();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        int i2;
        if (!(parcelable instanceof c)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        c cVar = (c) parcelable;
        super.onRestoreInstanceState(cVar.getSuperState());
        this.f1367e = cVar.f1378a;
        Set set = this.f1372j;
        d dVar = d.SET_ANIMATION;
        if (!set.contains(dVar) && !TextUtils.isEmpty(this.f1367e)) {
            setAnimation(this.f1367e);
        }
        this.f1368f = cVar.f1379b;
        if (!this.f1372j.contains(dVar) && (i2 = this.f1368f) != 0) {
            setAnimation(i2);
        }
        if (!this.f1372j.contains(d.SET_PROGRESS)) {
            setProgress(cVar.f1380c);
        }
        if (!this.f1372j.contains(d.PLAY_OPTION) && cVar.f1381d) {
            playAnimation();
        }
        if (!this.f1372j.contains(d.SET_IMAGE_ASSETS)) {
            setImageAssetsFolder(cVar.f1382e);
        }
        if (!this.f1372j.contains(d.SET_REPEAT_MODE)) {
            setRepeatMode(cVar.f1383f);
        }
        if (this.f1372j.contains(d.SET_REPEAT_COUNT)) {
            return;
        }
        setRepeatCount(cVar.f1384g);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        c cVar = new c(super.onSaveInstanceState());
        cVar.f1378a = this.f1367e;
        cVar.f1379b = this.f1368f;
        cVar.f1380c = this.lottieDrawable.W();
        cVar.f1381d = this.lottieDrawable.h0();
        cVar.f1382e = this.lottieDrawable.Q();
        cVar.f1383f = this.lottieDrawable.Z();
        cVar.f1384g = this.lottieDrawable.Y();
        return cVar;
    }

    @MainThread
    public void pauseAnimation() {
        this.f1370h = false;
        this.lottieDrawable.z0();
    }

    @MainThread
    public void playAnimation() {
        this.f1372j.add(d.PLAY_OPTION);
        this.lottieDrawable.A0();
    }

    public void removeAllAnimatorListeners() {
        this.lottieDrawable.B0();
    }

    public void removeAllLottieOnCompositionLoadedListener() {
        this.f1373k.clear();
    }

    public void removeAllUpdateListeners() {
        this.lottieDrawable.C0();
    }

    public void removeAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.lottieDrawable.D0(animatorListener);
    }

    @RequiresApi(api = 19)
    public void removeAnimatorPauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.lottieDrawable.E0(animatorPauseListener);
    }

    public boolean removeLottieOnCompositionLoadedListener(@NonNull J j2) {
        return this.f1373k.remove(j2);
    }

    public void removeUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.lottieDrawable.F0(animatorUpdateListener);
    }

    public List<i.e> resolveKeyPath(i.e eVar) {
        return this.lottieDrawable.H0(eVar);
    }

    @MainThread
    public void resumeAnimation() {
        this.f1372j.add(d.PLAY_OPTION);
        this.lottieDrawable.I0();
    }

    public void reverseAnimationSpeed() {
        this.lottieDrawable.J0();
    }

    public void setAnimation(@RawRes int i2) {
        this.f1368f = i2;
        this.f1367e = null;
        setCompositionTask(j(i2));
    }

    @Deprecated
    public void setAnimationFromJson(String str) {
        setAnimationFromJson(str, null);
    }

    public void setAnimationFromUrl(String str) {
        setCompositionTask(this.f1371i ? AbstractC0315p.w(getContext(), str) : AbstractC0315p.x(getContext(), str, null));
    }

    public void setApplyingOpacityToLayersEnabled(boolean z2) {
        this.lottieDrawable.L0(z2);
    }

    public void setCacheComposition(boolean z2) {
        this.f1371i = z2;
    }

    public void setClipToCompositionBounds(boolean z2) {
        this.lottieDrawable.M0(z2);
    }

    public void setComposition(@NonNull C0307h c0307h) {
        if (AbstractC0302c.f3895a) {
            Log.v(f1361n, "Set Composition \n" + c0307h);
        }
        this.lottieDrawable.setCallback(this);
        this.f1375m = c0307h;
        this.f1369g = true;
        boolean zN0 = this.lottieDrawable.N0(c0307h);
        this.f1369g = false;
        if (getDrawable() != this.lottieDrawable || zN0) {
            if (!zN0) {
                o();
            }
            onVisibilityChanged(this, getVisibility());
            requestLayout();
            Iterator it = this.f1373k.iterator();
            if (it.hasNext()) {
                android.support.v4.media.a.a(it.next());
                throw null;
            }
        }
    }

    public void setFailureListener(@Nullable H h2) {
        this.f1365c = h2;
    }

    public void setFallbackResource(@DrawableRes int i2) {
        this.f1366d = i2;
    }

    public void setFontAssetDelegate(AbstractC0300a abstractC0300a) {
        this.lottieDrawable.O0(abstractC0300a);
    }

    public void setFrame(int i2) {
        this.lottieDrawable.P0(i2);
    }

    public void setIgnoreDisabledSystemAnimations(boolean z2) {
        this.lottieDrawable.Q0(z2);
    }

    public void setImageAssetDelegate(InterfaceC0301b interfaceC0301b) {
        this.lottieDrawable.R0(interfaceC0301b);
    }

    public void setImageAssetsFolder(String str) {
        this.lottieDrawable.S0(str);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        g();
        super.setImageBitmap(bitmap);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        g();
        super.setImageDrawable(drawable);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i2) {
        g();
        super.setImageResource(i2);
    }

    public void setMaintainOriginalImageBounds(boolean z2) {
        this.lottieDrawable.T0(z2);
    }

    public void setMaxFrame(int i2) {
        this.lottieDrawable.U0(i2);
    }

    public void setMaxProgress(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        this.lottieDrawable.W0(f2);
    }

    public void setMinAndMaxFrame(String str) {
        this.lottieDrawable.Y0(str);
    }

    public void setMinAndMaxProgress(@FloatRange(from = 0.0d, to = 1.0d) float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3) {
        this.lottieDrawable.a1(f2, f3);
    }

    public void setMinFrame(int i2) {
        this.lottieDrawable.b1(i2);
    }

    public void setMinProgress(float f2) {
        this.lottieDrawable.d1(f2);
    }

    public void setOutlineMasksAndMattes(boolean z2) {
        this.lottieDrawable.e1(z2);
    }

    public void setPerformanceTrackingEnabled(boolean z2) {
        this.lottieDrawable.f1(z2);
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        this.f1372j.add(d.SET_PROGRESS);
        this.lottieDrawable.g1(f2);
    }

    public void setRenderMode(S s2) {
        this.lottieDrawable.h1(s2);
    }

    public void setRepeatCount(int i2) {
        this.f1372j.add(d.SET_REPEAT_COUNT);
        this.lottieDrawable.i1(i2);
    }

    public void setRepeatMode(int i2) {
        this.f1372j.add(d.SET_REPEAT_MODE);
        this.lottieDrawable.j1(i2);
    }

    public void setSafeMode(boolean z2) {
        this.lottieDrawable.k1(z2);
    }

    public void setSpeed(float f2) {
        this.lottieDrawable.l1(f2);
    }

    public void setTextDelegate(U u2) {
        this.lottieDrawable.n1(u2);
    }

    @Override // android.view.View
    public void unscheduleDrawable(Drawable drawable) {
        F f2;
        if (!this.f1369g && drawable == (f2 = this.lottieDrawable) && f2.g0()) {
            pauseAnimation();
        } else if (!this.f1369g && (drawable instanceof F)) {
            F f3 = (F) drawable;
            if (f3.g0()) {
                f3.z0();
            }
        }
        super.unscheduleDrawable(drawable);
    }

    @Nullable
    public Bitmap updateBitmap(String str, @Nullable Bitmap bitmap) {
        return this.lottieDrawable.o1(str, bitmap);
    }

    public <T> void addValueCallback(i.e eVar, T t2, e eVar2) {
        this.lottieDrawable.u(eVar, t2, new b(eVar2));
    }

    public void setAnimationFromJson(String str, @Nullable String str2) {
        setAnimation(new ByteArrayInputStream(str.getBytes()), str2);
    }

    public void setMaxFrame(String str) {
        this.lottieDrawable.V0(str);
    }

    public void setMinAndMaxFrame(String str, String str2, boolean z2) {
        this.lottieDrawable.Z0(str, str2, z2);
    }

    public void setMinFrame(String str) {
        this.lottieDrawable.c1(str);
    }

    public void setMinAndMaxFrame(int i2, int i3) {
        this.lottieDrawable.X0(i2, i3);
    }

    public void setAnimation(String str) {
        this.f1367e = str;
        this.f1368f = 0;
        setCompositionTask(i(str));
    }

    public void setAnimationFromUrl(String str, @Nullable String str2) {
        setCompositionTask(AbstractC0315p.x(getContext(), str, str2));
    }

    public void setAnimation(InputStream inputStream, @Nullable String str) {
        setCompositionTask(AbstractC0315p.n(inputStream, str));
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1363a = new H() { // from class: d.d
            @Override // d.H
            public final void onResult(Object obj) {
                this.f3906a.setComposition((C0307h) obj);
            }
        };
        this.f1364b = new a();
        this.f1366d = 0;
        this.lottieDrawable = new F();
        this.f1369g = false;
        this.f1370h = false;
        this.f1371i = true;
        this.f1372j = new HashSet();
        this.f1373k = new HashSet();
        k(attributeSet, P.f3827a);
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1363a = new H() { // from class: d.d
            @Override // d.H
            public final void onResult(Object obj) {
                this.f3906a.setComposition((C0307h) obj);
            }
        };
        this.f1364b = new a();
        this.f1366d = 0;
        this.lottieDrawable = new F();
        this.f1369g = false;
        this.f1370h = false;
        this.f1371i = true;
        this.f1372j = new HashSet();
        this.f1373k = new HashSet();
        k(attributeSet, i2);
    }
}
