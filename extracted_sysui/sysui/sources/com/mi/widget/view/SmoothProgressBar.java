package com.mi.widget.view;

import H0.s;
import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import androidx.annotation.AnyThread;
import androidx.annotation.AttrRes;
import androidx.annotation.CallSuper;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.StyleRes;
import androidx.annotation.UiThread;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.Composable;
import androidx.compose.runtime.ComposableTarget;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.ComposeView;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
public final class SmoothProgressBar extends ViewGroup implements SavedStateRegistryOwner, ViewModelStoreOwner {
    private static final boolean DEBUG = true;
    private static final int DRAWABLE_LEVEL_SIZE = 10000;
    private static final int MAX_PROGRESS = 100;
    private static final int MIN_PROGRESS = 0;
    private static final String TAG = "SmoothProgressBar";
    private MutableState<Drawable> _progressDrawable;
    private MutableState<SmoothDrawer> _progressDrawer;
    private MutableState<ColorStateList> _progressTint;
    private long duration;
    private LinearInterpolator interpolator;
    private final LifecycleRegistry lifecycle;
    private final ComposeView mComposeAdapter;
    private ValueAnimator mPendingValueAnimator;
    private final SavedStateRegistryController mSavedStateController;
    private final ValueAnimator mValueAnimator;
    private MutableIntState mVisualProgress;
    private int max;
    private int min;
    private int progress;
    private ProgressChangeListener progressChangeListener;
    private Drawable progressDrawable;
    private SmoothDrawer progressDrawer;
    private ColorStateList progressTint;
    private final SavedStateRegistry savedStateRegistry;
    private SmoothChangeListener smoothChangeListener;
    private final ViewModelStore viewModelStore;
    private static final b Companion = new b(null);
    public static final int $stable = 8;

    public interface ProgressChangeListener {
        @UiThread
        void onProgressChanged(int i2, int i3);
    }

    public interface SmoothChangeListener {
        @UiThread
        void onSmoothUpdate(@IntRange(from = 0, to = 1) int i2);
    }

    public interface SmoothDrawer {
        @UiThread
        void draw(Canvas canvas, @FloatRange(from = 0.0d, to = 1.0d) float f2);
    }

    public static final class a extends o implements Function2 {
        public a() {
            super(2);
        }

        public final void b(Composer composer, int i2) {
            if ((i2 & 11) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(759267892, i2, -1, "com.mi.widget.view.SmoothProgressBar.<anonymous> (SmoothProgressBar.kt:284)");
            }
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
            SmoothProgressBar smoothProgressBar = SmoothProgressBar.this;
            composer.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation(composer, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.Companion.getTopStart(), false, composer, 0);
            composer.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(composer, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            Function3 function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxSize$default);
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composer2 = Updater.constructor-impl(composer);
            Updater.set-impl(composer2, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer2.getInserting() || !n.c(composer2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composer2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composer2.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            function3ModifierMaterializerOf.invoke(SkippableUpdater.box-impl(SkippableUpdater.constructor-impl(composer)), composer, 0);
            composer.startReplaceableGroup(2058660585);
            ComposerKt.sourceInformationMarkerStart(composer, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            smoothProgressBar.DrawableProgressBar(composer, 8);
            smoothProgressBar.DrawerProgressBar(composer, 8);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            b((Composer) obj, ((Number) obj2).intValue());
            return s.f314a;
        }
    }

    public static final class b {
        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public b() {
        }
    }

    public static final class c extends o implements Function2 {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2440b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(int i2) {
            super(2);
            this.f2440b = i2;
        }

        public final void b(Composer composer, int i2) {
            SmoothProgressBar.this.DrawableProgressBar(composer, RecomposeScopeImplKt.updateChangedFlags(this.f2440b | 1));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            b((Composer) obj, ((Number) obj2).intValue());
            return s.f314a;
        }
    }

    public static final class d extends o implements Function2 {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2442b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(int i2) {
            super(2);
            this.f2442b = i2;
        }

        public final void b(Composer composer, int i2) {
            SmoothProgressBar.this.DrawableProgressBar(composer, RecomposeScopeImplKt.updateChangedFlags(this.f2442b | 1));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            b((Composer) obj, ((Number) obj2).intValue());
            return s.f314a;
        }
    }

    public static final class e extends o implements Function1 {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ float f2444b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(float f2) {
            super(1);
            this.f2444b = f2;
        }

        public final void b(DrawScope Canvas) {
            n.g(Canvas, "$this$Canvas");
            SmoothProgressBar smoothProgressBar = SmoothProgressBar.this;
            float f2 = this.f2444b;
            androidx.compose.ui.graphics.Canvas canvas = Canvas.getDrawContext().getCanvas();
            SmoothDrawer smoothDrawer = (SmoothDrawer) smoothProgressBar._progressDrawer.getValue();
            if (smoothDrawer != null) {
                smoothDrawer.draw(AndroidCanvas_androidKt.getNativeCanvas(canvas), f2);
            }
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            b((DrawScope) obj);
            return s.f314a;
        }
    }

    public static final class f extends o implements Function2 {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2446b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(int i2) {
            super(2);
            this.f2446b = i2;
        }

        public final void b(Composer composer, int i2) {
            SmoothProgressBar.this.DrawerProgressBar(composer, RecomposeScopeImplKt.updateChangedFlags(this.f2446b | 1));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            b((Composer) obj, ((Number) obj2).intValue());
            return s.f314a;
        }
    }

    public static final class g implements SmoothDrawer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Paint f2447a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Paint f2448b;

        public g() {
            Paint paint = new Paint();
            Paint.Style style = Paint.Style.STROKE;
            paint.setStyle(style);
            paint.setStrokeWidth(20.0f);
            paint.setAntiAlias(true);
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.f2447a = paint;
            Paint paint2 = new Paint();
            paint2.setStyle(style);
            paint2.setStrokeWidth(20.0f);
            paint2.setAntiAlias(true);
            paint2.setColor(-65536);
            this.f2448b = paint2;
        }

        @Override // com.mi.widget.view.SmoothProgressBar.SmoothDrawer
        public void draw(Canvas canvas, float f2) {
            n.g(canvas, "canvas");
            float f3 = 2;
            float f4 = (c1.f.f(canvas.getWidth(), canvas.getHeight()) / f3) - (this.f2447a.getStrokeWidth() / f3);
            float width = canvas.getWidth() / f3;
            float height = canvas.getHeight() / f3;
            RectF rectF = new RectF(width - f4, height - f4, width + f4, height + f4);
            canvas.drawOval(rectF, this.f2447a);
            canvas.drawArc(rectF, -90.0f, 360 * f2, false, this.f2448b);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SmoothProgressBar(Context context) {
        this(context, null, 0, 0, 14, null);
        n.g(context, "context");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    public final void DrawableProgressBar(Composer composer, int i2) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-784742520);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-784742520, i2, -1, "com.mi.widget.view.SmoothProgressBar.DrawableProgressBar (SmoothProgressBar.kt:366)");
        }
        final Drawable drawable = (Drawable) this._progressDrawable.getValue();
        if (drawable == null) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new d(i2));
                return;
            }
            return;
        }
        drawable.setLevel((this.mVisualProgress.getIntValue() * 10000) / (this.max - this.min));
        Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
        int intValue = this.mVisualProgress.getIntValue();
        ColorStateList colorStateList = (ColorStateList) this._progressTint.getValue();
        composerStartRestartGroup.startReplaceGroup(-591819017);
        boolean zChanged = composerStartRestartGroup.changed(intValue) | composerStartRestartGroup.changed(drawable) | composerStartRestartGroup.changed(colorStateList);
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new Painter() { // from class: com.mi.widget.view.SmoothProgressBar$DrawableProgressBar$1$1
                /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc, reason: not valid java name */
                public long m78getIntrinsicSizeNHjbRc() {
                    return androidx.compose.ui.geometry.SizeKt.Size(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                }

                public void onDraw(DrawScope drawScope) {
                    n.g(drawScope, "<this>");
                    Drawable drawable2 = drawable;
                    drawable2.setBounds(0, 0, (int) Size.getWidth-impl(drawScope.getSize-NH-jbRc()), (int) Size.getHeight-impl(drawScope.getSize-NH-jbRc()));
                    drawable2.draw(AndroidCanvas_androidKt.getNativeCanvas(drawScope.getDrawContext().getCanvas()));
                }
            };
            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
        }
        composerStartRestartGroup.endReplaceGroup();
        ImageKt.Image((SmoothProgressBar$DrawableProgressBar$1$1) objRememberedValue, TAG, modifierFillMaxSize$default, (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composerStartRestartGroup, 432, 120);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            scopeUpdateScopeEndRestartGroup2.updateScope(new c(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    public final void DrawerProgressBar(Composer composer, int i2) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-1342207589);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1342207589, i2, -1, "com.mi.widget.view.SmoothProgressBar.DrawerProgressBar (SmoothProgressBar.kt:391)");
        }
        CanvasKt.Canvas(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), new e(this.mVisualProgress.getIntValue() / (this.max - this.min)), composerStartRestartGroup, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new f(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(SmoothProgressBar this$0, ValueAnimator it) {
        n.g(this$0, "this$0");
        n.g(it, "it");
        MutableIntState mutableIntState = this$0.mVisualProgress;
        Object animatedValue = it.getAnimatedValue();
        n.e(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        mutableIntState.setIntValue(((Integer) animatedValue).intValue());
        SmoothChangeListener smoothChangeListener = this$0.smoothChangeListener;
        if (smoothChangeListener != null) {
            smoothChangeListener.onSmoothUpdate(this$0.mVisualProgress.getIntValue());
        }
    }

    private final void updateProgress(int i2, int i3) {
        this.mValueAnimator.setDuration(this.duration);
        this.mValueAnimator.setInterpolator(this.interpolator);
        this.mValueAnimator.setIntValues(i2, i3);
        Log.d(TAG, "updateProgress isAttachedToWindow=" + isAttachedToWindow() + " curProgress=" + i2 + " nextProgress=" + i3);
        if (isAttachedToWindow()) {
            this.mValueAnimator.start();
        } else {
            this.mPendingValueAnimator = this.mValueAnimator;
        }
    }

    @AnyThread
    public final long getDuration() {
        return this.duration;
    }

    @AnyThread
    public final LinearInterpolator getInterpolator() {
        return this.interpolator;
    }

    @AnyThread
    public final int getMax() {
        return this.max;
    }

    @AnyThread
    public final int getMin() {
        return this.min;
    }

    @AnyThread
    public final int getProgress() {
        return this.progress;
    }

    @AnyThread
    public final ProgressChangeListener getProgressChangeListener() {
        return this.progressChangeListener;
    }

    @AnyThread
    public final Drawable getProgressDrawable() {
        return this.progressDrawable;
    }

    @AnyThread
    public final SmoothDrawer getProgressDrawer() {
        return this.progressDrawer;
    }

    @AnyThread
    public final ColorStateList getProgressTint() {
        return this.progressTint;
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public SavedStateRegistry getSavedStateRegistry() {
        return this.savedStateRegistry;
    }

    @AnyThread
    public final SmoothChangeListener getSmoothChangeListener() {
        return this.smoothChangeListener;
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore getViewModelStore() {
        return this.viewModelStore;
    }

    @AnyThread
    public final boolean isAnimationRunning() {
        return this.mValueAnimator.isRunning();
    }

    @Override // android.view.ViewGroup, android.view.View
    @CallSuper
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getLifecycle().setCurrentState(Lifecycle.State.STARTED);
        getLifecycle().setCurrentState(Lifecycle.State.RESUMED);
        ValueAnimator valueAnimator = this.mPendingValueAnimator;
        if (valueAnimator != null) {
            Log.i(TAG, "Starting transition animation when attach to window.");
            valueAnimator.start();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @CallSuper
    public void onDetachedFromWindow() {
        getLifecycle().setCurrentState(Lifecycle.State.DESTROYED);
        if (isAnimationRunning()) {
            Log.i(TAG, "Stopping transition animation when detach from window.");
            this.mValueAnimator.cancel();
            SmoothChangeListener smoothChangeListener = this.smoothChangeListener;
            if (smoothChangeListener != null) {
                smoothChangeListener.onSmoothUpdate(this.progress);
            }
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    @CallSuper
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        this.mComposeAdapter.layout(0, 0, getWidth(), getHeight());
    }

    @Override // android.view.View
    @CallSuper
    public void onMeasure(int i2, int i3) {
        this.mComposeAdapter.measure(i2, i3);
        setMeasuredDimension(this.mComposeAdapter.getMeasuredWidth(), this.mComposeAdapter.getMeasuredHeight());
    }

    @AnyThread
    public final void setDuration(long j2) {
        if (j2 < 0) {
            throw new IllegalArgumentException("duration must be greater than 0.");
        }
        this.duration = j2;
    }

    @AnyThread
    public final void setInterpolator(LinearInterpolator linearInterpolator) {
        n.g(linearInterpolator, "<set-?>");
        this.interpolator = linearInterpolator;
    }

    @UiThread
    public final void setMax(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("max must be greater than 0.");
        }
        if (i2 < this.min) {
            throw new IllegalArgumentException("max must be greater than min.");
        }
        this.max = i2;
    }

    @UiThread
    public final void setMin(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("min must be greater or equal than 0.");
        }
        if (i2 >= this.max) {
            throw new IllegalArgumentException("min must be less than max.");
        }
        this.min = i2;
    }

    @UiThread
    public final void setProgress(int i2) {
        if (i2 < this.min || i2 > this.max) {
            throw new IllegalArgumentException("progress must be between " + this.min + " and " + this.max + ".");
        }
        int i3 = this.progress;
        if (i3 != i2) {
            ProgressChangeListener progressChangeListener = this.progressChangeListener;
            if (progressChangeListener != null) {
                progressChangeListener.onProgressChanged(i3, i2);
            }
            if (isAnimationRunning()) {
                this.mValueAnimator.cancel();
                updateProgress(this.mVisualProgress.getIntValue(), i2);
            } else {
                updateProgress(this.progress, i2);
            }
        }
        this.progress = i2;
    }

    @UiThread
    public final void setProgressChangeListener(ProgressChangeListener progressChangeListener) {
        this.progressChangeListener = progressChangeListener;
    }

    @UiThread
    public final void setProgressDrawable(Drawable drawable) {
        if (this.progressDrawer != null && drawable != null) {
            throw new IllegalArgumentException("progressDrawable and progressDrawer can not be set the same time.");
        }
        this.progressDrawable = drawable;
        if (drawable != null) {
            drawable.setTintList(this.progressTint);
        }
        this._progressDrawable.setValue(drawable);
    }

    @UiThread
    public final void setProgressDrawer(SmoothDrawer smoothDrawer) {
        if (this.progressDrawable != null && smoothDrawer != null) {
            throw new IllegalArgumentException("progressDrawable and progressDrawer can not be set the same time.");
        }
        this.progressDrawer = smoothDrawer;
        this._progressDrawer.setValue(smoothDrawer);
    }

    @UiThread
    public final void setProgressTint(ColorStateList colorStateList) {
        this.progressTint = colorStateList;
        if (colorStateList != null) {
            this._progressTint.setValue(colorStateList);
        }
    }

    @UiThread
    public final void setSmoothChangeListener(SmoothChangeListener smoothChangeListener) {
        if (isAnimationRunning()) {
            throw new IllegalStateException("can't change smoothChangeListener when transition animation is running.");
        }
        this.smoothChangeListener = smoothChangeListener;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SmoothProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        n.g(context, "context");
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public LifecycleRegistry getLifecycle() {
        return this.lifecycle;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SmoothProgressBar(Context context, AttributeSet attributeSet, @AttrRes int i2) {
        this(context, attributeSet, i2, 0, 8, null);
        n.g(context, "context");
    }

    public /* synthetic */ SmoothProgressBar(Context context, AttributeSet attributeSet, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i4 & 2) != 0 ? null : attributeSet, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0 : i3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmoothProgressBar(Context context, AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        super(context, attributeSet, i2, i3);
        n.g(context, "context");
        this._progressDrawable = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
        this._progressDrawer = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
        this._progressTint = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
        SavedStateRegistryController savedStateRegistryControllerCreate = SavedStateRegistryController.Companion.create(this);
        this.mSavedStateController = savedStateRegistryControllerCreate;
        this.lifecycle = new LifecycleRegistry(this);
        this.viewModelStore = new ViewModelStore();
        this.savedStateRegistry = savedStateRegistryControllerCreate.getSavedStateRegistry();
        this.max = 100;
        this.progress = this.min;
        this.interpolator = new LinearInterpolator();
        this.duration = 1000L;
        ComposeView composeView = new ComposeView(context, (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
        this.mComposeAdapter = composeView;
        ValueAnimator valueAnimator = new ValueAnimator();
        this.mValueAnimator = valueAnimator;
        this.mVisualProgress = SnapshotIntStateKt.mutableIntStateOf(0);
        composeView.setContent(ComposableLambdaKt.composableLambdaInstance(759267892, true, new a()));
        setId(R.id.content);
        ViewTreeLifecycleOwner.set(this, this);
        ViewTreeViewModelStoreOwner.set(this, this);
        ViewTreeSavedStateRegistryOwner.set(this, this);
        savedStateRegistryControllerCreate.performRestore(null);
        addView((View) composeView, -1, -1);
        getLifecycle().setCurrentState(Lifecycle.State.INITIALIZED);
        getLifecycle().setCurrentState(Lifecycle.State.CREATED);
        setProgressDrawer(new g());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.mi.widget.view.e
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                SmoothProgressBar._init_$lambda$1(this.f2455a, valueAnimator2);
            }
        });
    }
}
