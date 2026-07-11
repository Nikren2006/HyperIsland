package com.miui.maml.component;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.miui.maml.R;
import com.miui.maml.RendererController;
import com.miui.maml.ResourceLoader;
import com.miui.maml.ResourceManager;
import com.miui.maml.ScreenContext;
import com.miui.maml.ScreenElementRoot;
import com.miui.maml.data.VariableNames;
import com.miui.maml.data.Variables;
import com.miui.maml.util.AssetsResourceLoader;
import com.miui.maml.util.FolderResourceLoader;
import com.miui.maml.util.HideSdkDependencyUtils;
import com.miui.maml.util.MamlAccessHelper;
import com.miui.maml.util.MamlViewManager;
import com.miui.maml.util.SharedPreferenceHelper;
import com.miui.maml.util.Utils;
import com.miui.maml.util.ZipResourceLoader;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class MamlView extends FrameLayout implements RendererController.IRenderable, MamlViewManager {
    private static final String BLUR_VAR_NAME = "__blur_ratio";
    public static final int MODE_ASSETS_FOLDER = 2;
    public static final int MODE_FOLDER = 3;
    public static final int MODE_ZIP = 1;
    private static final String TAG = "MamlView";
    private static final String VERSION = "1.0.0";
    private static final int VERSION_CODE = 1;
    private boolean isEnsureViewSizeUpDate;
    private boolean mAutoFinishRoot;
    private boolean mAutoRemoveCache;
    private boolean mCanvasParamsChanged;
    private final ScreenElementRoot.OnExternCommandListener mCommandListener;
    private Config mConfig;
    private WeakReference<OnExternCommandListener> mExternCommandListener;
    private volatile boolean mFinished;
    private boolean mHasDelay;
    private int mLastBlurRatio;
    private WindowManager.LayoutParams mLp;
    private MamlAccessHelper mMamlAccessHelper;
    protected boolean mNeedDisallowInterceptTouchEvent;
    private int mPivotX;
    private int mPivotY;
    protected ScreenElementRoot mRoot;
    private float mScale;
    private InnerView mView;
    private String mWidgetType;
    private WindowManager mWindowManager;
    private float mX;
    private float mY;

    public static class Config {
        public static final int FLAG_WIDE_DEVICE = 1;
        public boolean autoRemoveCache;
        public String innerPath;
        public int mode;
        public String path;
        public float resizeScale;
        public int viewWidth = -1;
        public int viewHeight = -1;
        public boolean touchable = true;
        public int flags = -1;
    }

    public class InnerView extends View {
        public InnerView(Context context) {
            super(context);
        }

        @Override // android.view.View
        public void onDraw(Canvas canvas) {
            MamlView mamlView = MamlView.this;
            if (mamlView.mRoot == null) {
                return;
            }
            if (!mamlView.mCanvasParamsChanged) {
                MamlView.this.mRoot.render(canvas);
                return;
            }
            int iSave = canvas.save();
            canvas.translate(MamlView.this.mX, MamlView.this.mY);
            if (MamlView.this.mScale != 0.0f) {
                canvas.scale(MamlView.this.mScale, MamlView.this.mScale, MamlView.this.mPivotX, MamlView.this.mPivotY);
            }
            MamlView.this.mRoot.render(canvas);
            canvas.restoreToCount(iSave);
        }
    }

    public interface OnExternCommandListener {
        void onCommand(String str, Double d2, String str2);
    }

    public MamlView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAutoFinishRoot = true;
        this.mCommandListener = new ScreenElementRoot.OnExternCommandListener() { // from class: com.miui.maml.component.MamlView.1
            @Override // com.miui.maml.ScreenElementRoot.OnExternCommandListener
            public void onCommand(String str, Double d2, String str2) {
                OnExternCommandListener onExternCommandListener;
                if (MamlView.this.mExternCommandListener == null || (onExternCommandListener = (OnExternCommandListener) MamlView.this.mExternCommandListener.get()) == null) {
                    return;
                }
                onExternCommandListener.onCommand(str, d2, str2);
            }
        };
        this.isEnsureViewSizeUpDate = false;
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MamlView);
        String string = typedArrayObtainStyledAttributes.getString(R.styleable.MamlView_path);
        String string2 = typedArrayObtainStyledAttributes.getString(R.styleable.MamlView_innerPath);
        int i2 = typedArrayObtainStyledAttributes.getInt(R.styleable.MamlView_resMode, 2);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MamlView_autoRemoveCache, false);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MamlView_touchable, true);
        load(context, getResourceLoader(context, string, string2, i2), z2);
        setTouchable(z3);
    }

    private void blurBackground() {
        try {
            ScreenElementRoot screenElementRoot = this.mRoot;
            if (screenElementRoot == null || !screenElementRoot.isMamlBlurWindow() || this.mLp == null || !this.mRoot.getVariables().existsDouble(BLUR_VAR_NAME)) {
                return;
            }
            int i2 = (int) this.mRoot.getVariables().getDouble(BLUR_VAR_NAME);
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 > 100) {
                i2 = 100;
            }
            if (i2 != this.mLastBlurRatio) {
                this.mLastBlurRatio = i2;
                if (i2 == 0) {
                    this.mLp.flags &= -5;
                } else {
                    HideSdkDependencyUtils.WindowManager_LayoutParams_setLayoutParamsBlurRatio(this.mLp, (i2 * 1.0f) / 100.0f);
                    this.mLp.flags |= 4;
                }
                this.mWindowManager.updateViewLayout(this, this.mLp);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void ensureViewSize() {
        post(new Runnable() { // from class: com.miui.maml.component.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f2542a.lambda$ensureViewSize$2();
            }
        });
    }

    private void finish() {
        if (this.mFinished || !this.mAutoFinishRoot) {
            return;
        }
        this.mFinished = true;
        setOnTouchListener(null);
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.selfFinish();
            this.mRoot.detachFromVsync();
            this.mRoot.getVariables().reset();
            removeAccessHelperRef();
            if (this.mAutoRemoveCache) {
                removeRelatedBitmapsRef();
            }
            this.mRoot = null;
        }
    }

    @Nullable
    private ResourceLoader getResourceLoader(Context context, String str, String str2, int i2) {
        if (Utils.isEmpty(str)) {
            return null;
        }
        if (i2 == 1) {
            return new ZipResourceLoader(str, str2);
        }
        if (i2 == 2) {
            return new AssetsResourceLoader(context, str);
        }
        if (i2 != 3) {
            return null;
        }
        return new FolderResourceLoader(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$ensureViewSize$2() {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            Variables variables = screenElementRoot.getContext().mVariables;
            double d2 = variables.getDouble(VariableNames.VARIABLE_VIEW_WIDTH);
            double d3 = variables.getDouble(VariableNames.VARIABLE_VIEW_HEIGHT);
            double width = getWidth() / this.mRoot.getScale();
            double height = getHeight() / this.mRoot.getScale();
            if (width == 0.0d || height == 0.0d) {
                Log.d(TAG, "ensureViewSize viewWidth|viewHeight=0");
                return;
            }
            if (d2 == width && d3 == height) {
                return;
            }
            Log.d(TAG, "ensureViewSize changed: preViewWidth = " + d2 + ", viewWidth = " + width + ", preViewHeight = " + d3 + ", viewHeight = " + height);
            variables.put(VariableNames.VARIABLE_VIEW_WIDTH, width);
            variables.put(VariableNames.VARIABLE_VIEW_HEIGHT, height);
            this.mRoot.requestUpdate();
            this.isEnsureViewSizeUpDate = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setPathThenVisible$0() {
        setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setPathThenVisible$1() {
        setVisibility(0);
    }

    private void load(Context context, ResourceLoader resourceLoader, boolean z2) {
        load(context, resourceLoader, z2, 0.0f);
    }

    private void removeAccessHelperRef() {
        this.mRoot.setMamlAccessHelper(null);
        MamlAccessHelper mamlAccessHelper = this.mMamlAccessHelper;
        if (mamlAccessHelper != null) {
            mamlAccessHelper.removeRoot();
            this.mMamlAccessHelper = null;
        }
    }

    @Deprecated
    public void cleanUp() {
        cleanUp(false);
    }

    public void clearBitmapCache() {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            ResourceManager resourceManager = screenElementRoot.getContext().mResourceManager;
            ResourceManager.clear();
        }
    }

    public void clearPermanenceData() {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            String str = (String) screenElementRoot.getVariables().get("customEditLocalId");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            SharedPreferenceHelper.getInstance(getContext().getApplicationContext(), "sp_" + str).clearSharePreference();
        }
    }

    public boolean disableCutRoundCorner() {
        ScreenElementRoot screenElementRoot = this.mRoot;
        return screenElementRoot != null && screenElementRoot.isDisableCutRoundCorner();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        MamlAccessHelper mamlAccessHelper = this.mMamlAccessHelper;
        if (mamlAccessHelper == null || !mamlAccessHelper.dispatchHoverEvent(motionEvent)) {
            return super.dispatchHoverEvent(motionEvent);
        }
        return true;
    }

    @Override // com.miui.maml.RendererController.IRenderable
    public void doRender() {
        this.mView.postInvalidate();
        blurBackground();
    }

    public void finalize() throws Throwable {
        finish();
        super.finalize();
    }

    public void forceTick() {
        if (this.mRoot == null) {
            return;
        }
        this.mRoot.tick(SystemClock.elapsedRealtime());
    }

    public String getCornerCutType() {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            return screenElementRoot.getCornerCutType();
        }
        return null;
    }

    public int getMamlVersionCode() {
        return 1;
    }

    public String getMamlVersionName() {
        return VERSION;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            return (int) screenElementRoot.getHeight();
        }
        return -1;
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            return (int) screenElementRoot.getWidth();
        }
        return -1;
    }

    public double getVariableNumber(String str) {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            return Utils.getVariableNumber(str, screenElementRoot.getVariables());
        }
        return 0.0d;
    }

    public Object getVariableObject(String str) {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            return screenElementRoot.getVariables().get(str);
        }
        return null;
    }

    public String getVariableString(String str) {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            return Utils.getVariableString(str, screenElementRoot.getVariables());
        }
        return null;
    }

    @Override // com.miui.maml.util.MamlViewManager
    public Context getViewContext() {
        return getContext();
    }

    @Override // com.miui.maml.util.MamlViewManager
    public Rect getViewLocationOnScreen() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        int i2 = iArr[0];
        return new Rect(i2, iArr[1], getMeasuredWidth() + i2, iArr[1] + getMeasuredHeight());
    }

    public void init() {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.setConfiguration(getResources().getConfiguration(), false);
            this.mRoot.setMamlViewOnExternCommandListener(this.mCommandListener);
            this.mRoot.setRenderControllerRenderable(this);
            this.mRoot.attachToVsync();
            this.mRoot.selfInit();
            MamlAccessHelper mamlAccessHelper = new MamlAccessHelper(this.mRoot, this);
            this.mMamlAccessHelper = mamlAccessHelper;
            ViewCompat.setAccessibilityDelegate(this, mamlAccessHelper);
        }
    }

    public void initMamlview(Context context, ScreenElementRoot screenElementRoot) {
        setClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        screenElementRoot.getClass();
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mView = new InnerView(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        if (getChildCount() > 0) {
            removeAllViews();
        }
        addView(this.mView, layoutParams);
        this.mRoot = screenElementRoot;
        screenElementRoot.setViewManager(this);
        this.mRoot.setOnHoverChangeListener(new ScreenElementRoot.OnHoverChangeListener() { // from class: com.miui.maml.component.MamlView.2
            @Override // com.miui.maml.ScreenElementRoot.OnHoverChangeListener
            public void onHoverChange(String str) {
                if (!TextUtils.isEmpty(str)) {
                    MamlView.this.setContentDescription(str);
                }
                MamlView.this.sendAccessibilityEvent(32768);
            }
        });
        init();
    }

    public boolean isLoaded() {
        return this.mRoot != null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        onResume();
    }

    @Deprecated
    public void onCommand(String str) {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.onCommand(str);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.onConfigurationChanged(configuration);
        }
    }

    @Deprecated
    public void onDestory() {
        onDestroy();
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        finish();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onPause();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.onHover(motionEvent);
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            accessibilityNodeInfo.setText(screenElementRoot.getRawAttr("accessibilityText"));
        }
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            Variables variables = screenElementRoot.getContext().mVariables;
            variables.put(VariableNames.VARIABLE_VIEW_WIDTH, (i4 - i2) / this.mRoot.getScale());
            variables.put(VariableNames.VARIABLE_VIEW_HEIGHT, (i5 - i3) / this.mRoot.getScale());
            Object parent = getParent();
            while (parent instanceof View) {
                View view = (View) parent;
                i2 += view.getLeft() - view.getScrollX();
                i3 += view.getTop() - view.getScrollY();
                parent = view.getParent();
            }
            variables.put(VariableNames.VARIABLE_VIEW_X, i2 / this.mRoot.getScale());
            variables.put(VariableNames.VARIABLE_VIEW_Y, i3 / this.mRoot.getScale());
            if (this.isEnsureViewSizeUpDate) {
                this.isEnsureViewSizeUpDate = false;
                Log.i(TAG, "onLayout: put width exception resume");
                onResume();
            }
            this.mRoot.requestUpdate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        ScreenElementRoot screenElementRoot = this.mRoot;
        int width = screenElementRoot != null ? (int) screenElementRoot.getWidth() : 0;
        ScreenElementRoot screenElementRoot2 = this.mRoot;
        int height = screenElementRoot2 != null ? (int) screenElementRoot2.getHeight() : 0;
        if (mode == Integer.MIN_VALUE && width > 0) {
            size = Math.min(size, width);
        }
        if (mode2 == Integer.MIN_VALUE && height > 0) {
            size2 = Math.min(size2, height);
        }
        setMeasuredDimension(size, size2);
    }

    public void onPause() {
        Log.d(TAG, "onPause");
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.selfPause();
        }
    }

    public void onResume() {
        Log.d(TAG, "onResume");
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.selfResume();
            ensureViewSize();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zOnTouch;
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            boolean zNeedDisallowInterceptTouchEvent = screenElementRoot.needDisallowInterceptTouchEvent();
            if (this.mNeedDisallowInterceptTouchEvent != zNeedDisallowInterceptTouchEvent) {
                getParent().requestDisallowInterceptTouchEvent(zNeedDisallowInterceptTouchEvent);
                this.mNeedDisallowInterceptTouchEvent = zNeedDisallowInterceptTouchEvent;
            }
            zOnTouch = this.mRoot.onTouch(motionEvent);
        } else {
            zOnTouch = false;
        }
        return zOnTouch || super.onTouchEvent(motionEvent);
    }

    public void putVariableNumber(String str, double d2) {
        putVariableNumber(str, d2, 0);
    }

    public void putVariableObject(String str, Object obj) {
        putVariableObject(str, obj, 0);
    }

    public void putVariableString(String str, String str2) {
        putVariableString(str, str2, 0);
    }

    public void removeRelatedBitmapsRef() {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.getContext().mResourceManager.clearByKeys();
        }
    }

    public void requestUpdate() {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.requestUpdate();
        }
    }

    public void sendCommand(String str) {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.onCommand(str);
        }
    }

    public void setAutoDarkenWallpaper(boolean z2) {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.setAutoDarkenWallpaper(true);
        }
    }

    public void setAutoFinishRoot(boolean z2) {
        this.mAutoFinishRoot = z2;
    }

    public void setCacheSize(int i2) {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.getContext().mResourceManager.setCacheSize(i2);
        }
    }

    @Deprecated
    public final void setKeepResource(boolean z2) {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.setKeepResource(z2);
        }
    }

    public void setOnExternCommandListener(OnExternCommandListener onExternCommandListener) {
        this.mExternCommandListener = onExternCommandListener == null ? null : new WeakReference<>(onExternCommandListener);
    }

    public void setPath(@NonNull String str) {
        load(getContext(), getResourceLoader(getContext(), str, null, 1), this.mAutoRemoveCache);
    }

    public void setPathThenVisible(@NonNull String str) {
        setPath(str);
        post(new Runnable() { // from class: com.miui.maml.component.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f2541a.lambda$setPathThenVisible$0();
            }
        });
    }

    public void setSaveConfigOnlyInPause(boolean z2) {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.setSaveConfigViaProvider(z2);
        }
    }

    public void setSaveConfigViaProvider(boolean z2) {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.setSaveConfigViaProvider(z2);
        }
    }

    public void setScale(float f2, int i2, int i3) {
        this.mScale = f2;
        this.mPivotX = i2;
        this.mPivotY = i3;
        this.mCanvasParamsChanged = true;
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.requestUpdate();
        }
    }

    public void setTouchable(boolean z2) {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.setTouchable(z2);
        }
    }

    public void setTranslate(float f2, float f3) {
        this.mX = f2;
        this.mY = f3;
        this.mCanvasParamsChanged = true;
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.requestUpdate();
        }
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        if (i2 == 0) {
            onResume();
        } else if (i2 == 4 || i2 == 8) {
            onPause();
        }
    }

    @Deprecated
    public void setWindowLayoutParams(WindowManager.LayoutParams layoutParams) {
        this.mLp = layoutParams;
    }

    public boolean supportBackgroundBlur() {
        ScreenElementRoot screenElementRoot = this.mRoot;
        return screenElementRoot != null && screenElementRoot.supportBackgroundBlur();
    }

    private void load(Context context, ResourceLoader resourceLoader, boolean z2, float f2) {
        this.mAutoRemoveCache = z2;
        if (resourceLoader != null) {
            ScreenElementRoot screenElementRoot = new ScreenElementRoot(new ScreenContext(context.getApplicationContext(), new ResourceManager(resourceLoader)));
            screenElementRoot.setResizeRadio(f2);
            screenElementRoot.setWidgetType(this.mWidgetType);
            screenElementRoot.setMamlViewConfig(this.mConfig);
            if (screenElementRoot.load()) {
                screenElementRoot.setKeepResource(true);
                initMamlview(context, screenElementRoot);
            }
        }
    }

    @Deprecated
    public void cleanUp(boolean z2) {
        setOnTouchListener(null);
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.setKeepResource(z2);
            this.mRoot.selfFinish();
            if (!z2) {
                removeRelatedBitmapsRef();
            }
            Utils.triggerGC();
        }
    }

    public void putVariableNumber(String str, double d2, int i2) {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.getVariables().put(str, d2, i2);
        }
    }

    public void putVariableObject(String str, Object obj, int i2) {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.getVariables().put(str, obj, i2);
        }
    }

    public void putVariableString(String str, String str2, int i2) {
        ScreenElementRoot screenElementRoot = this.mRoot;
        if (screenElementRoot != null) {
            screenElementRoot.getVariables().put(str, str2, i2);
        }
    }

    public void setPath(@NonNull String str, float f2) {
        load(getContext(), getResourceLoader(getContext(), str, null, 1), this.mAutoRemoveCache, f2);
    }

    public void setPathThenVisible(@NonNull String str, float f2) {
        setPath(str, f2);
        post(new Runnable() { // from class: com.miui.maml.component.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f2540a.lambda$setPathThenVisible$1();
            }
        });
    }

    public void setPathThenVisible(@NonNull String str, String str2) {
        this.mWidgetType = str2;
        setPathThenVisible(str);
    }

    public void setPathThenVisible(@NonNull String str, String str2, float f2) {
        this.mWidgetType = str2;
        setPathThenVisible(str, f2);
    }

    public MamlView(Context context, String str, int i2) {
        this(context, str, (String) null, i2);
    }

    public MamlView(Context context, String str, int i2, boolean z2) {
        this(context, str, null, i2, z2);
    }

    public MamlView(Context context, String str, String str2, int i2) {
        this(context, str, str2, i2, false);
    }

    public MamlView(Context context, String str, String str2, int i2, boolean z2) {
        super(context);
        this.mAutoFinishRoot = true;
        this.mCommandListener = new ScreenElementRoot.OnExternCommandListener() { // from class: com.miui.maml.component.MamlView.1
            @Override // com.miui.maml.ScreenElementRoot.OnExternCommandListener
            public void onCommand(String str3, Double d2, String str22) {
                OnExternCommandListener onExternCommandListener;
                if (MamlView.this.mExternCommandListener == null || (onExternCommandListener = (OnExternCommandListener) MamlView.this.mExternCommandListener.get()) == null) {
                    return;
                }
                onExternCommandListener.onCommand(str3, d2, str22);
            }
        };
        this.isEnsureViewSizeUpDate = false;
        load(context, getResourceLoader(context, str, str2, i2), z2);
    }

    public MamlView(Context context, String str, String str2, int i2, boolean z2, String str3) {
        super(context);
        this.mAutoFinishRoot = true;
        this.mCommandListener = new ScreenElementRoot.OnExternCommandListener() { // from class: com.miui.maml.component.MamlView.1
            @Override // com.miui.maml.ScreenElementRoot.OnExternCommandListener
            public void onCommand(String str32, Double d2, String str22) {
                OnExternCommandListener onExternCommandListener;
                if (MamlView.this.mExternCommandListener == null || (onExternCommandListener = (OnExternCommandListener) MamlView.this.mExternCommandListener.get()) == null) {
                    return;
                }
                onExternCommandListener.onCommand(str32, d2, str22);
            }
        };
        this.isEnsureViewSizeUpDate = false;
        this.mWidgetType = str3;
        load(context, getResourceLoader(context, str, str2, i2), z2);
    }

    public MamlView(Context context, @NonNull Config config) {
        super(context);
        this.mAutoFinishRoot = true;
        this.mCommandListener = new ScreenElementRoot.OnExternCommandListener() { // from class: com.miui.maml.component.MamlView.1
            @Override // com.miui.maml.ScreenElementRoot.OnExternCommandListener
            public void onCommand(String str32, Double d2, String str22) {
                OnExternCommandListener onExternCommandListener;
                if (MamlView.this.mExternCommandListener == null || (onExternCommandListener = (OnExternCommandListener) MamlView.this.mExternCommandListener.get()) == null) {
                    return;
                }
                onExternCommandListener.onCommand(str32, d2, str22);
            }
        };
        this.isEnsureViewSizeUpDate = false;
        this.mConfig = config;
        load(context, getResourceLoader(context, config.path, config.innerPath, config.mode), config.autoRemoveCache, config.resizeScale);
        setTouchable(config.touchable);
    }

    public MamlView(Context context, ScreenElementRoot screenElementRoot) {
        this(context, screenElementRoot, 0L);
    }

    public MamlView(Context context, ScreenElementRoot screenElementRoot, boolean z2) {
        this(context, screenElementRoot, 0L);
        this.mAutoRemoveCache = z2;
    }

    @Deprecated
    public MamlView(Context context, ScreenElementRoot screenElementRoot, long j2) {
        super(context);
        this.mAutoFinishRoot = true;
        this.mCommandListener = new ScreenElementRoot.OnExternCommandListener() { // from class: com.miui.maml.component.MamlView.1
            @Override // com.miui.maml.ScreenElementRoot.OnExternCommandListener
            public void onCommand(String str32, Double d2, String str22) {
                OnExternCommandListener onExternCommandListener;
                if (MamlView.this.mExternCommandListener == null || (onExternCommandListener = (OnExternCommandListener) MamlView.this.mExternCommandListener.get()) == null) {
                    return;
                }
                onExternCommandListener.onCommand(str32, d2, str22);
            }
        };
        this.isEnsureViewSizeUpDate = false;
        initMamlview(context, screenElementRoot);
        this.mAutoFinishRoot = false;
    }
}
