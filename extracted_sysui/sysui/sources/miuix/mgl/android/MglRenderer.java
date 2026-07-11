package miuix.mgl.android;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Iterator;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;
import miuix.mgl.MglContext;
import miuix.mgl.RenderTexture;
import miuix.mgl.android.FrameSync;
import miuix.mgl.android.GLTextureView;
import miuix.mgl.android.MGLSurfaceView;
import miuix.mgl.math.Math;

/* JADX INFO: loaded from: classes3.dex */
public abstract class MglRenderer {
    private static final String TAG = "mgl_native";
    private Context mContext;
    private boolean mIsOpaque;
    private int mMsaa;
    private RenderContext mRenderContext;
    GLViewHolder mView;
    private final FrameSync mFrameSync = new FrameSync(new FrameListener());
    private DepthSize mDepthSize = DepthSize.NO_DEPTH;
    private float mBufferScale = 1.0f;
    private int mColorSpace = ColorSpace.DEFAULT.color;
    private boolean mPause = true;
    private boolean mFirstCreate = true;
    private boolean mEnableStencil = false;
    int mLastWidth = -1;
    int mLastHeight = -1;
    private final Object mEventQueueLock = new Object();
    private ArrayList<Runnable> mEventQueue = new ArrayList<>();
    private ArrayList<Runnable> mRunEventQueue = new ArrayList<>();

    public enum ColorSpace {
        DEFAULT(0),
        DISPLAY_P3_EXT(13155),
        DISPLAY_P3_LINEAR_EXT(13154),
        SCRGB_EXT(13137),
        SCRGB_LINEAR_EXT(13136),
        SRGB_KHR(12425),
        LINEAR_KHR(12426),
        BT2020_LINEAR_EXT(13119),
        BT2020_PQ_EXT(13120),
        DISPLAY_P3_PASSTHROUGH_EXT(13456);

        int color;

        ColorSpace(int i2) {
            this.color = i2;
        }
    }

    public enum DepthSize {
        NO_DEPTH(0),
        DEPTH_16(16),
        DEPTH_24(24);

        int size;

        DepthSize(int i2) {
            this.size = i2;
        }
    }

    public class FrameListener implements FrameSync.IFrameListener {
        public FrameListener() {
        }

        @Override // miuix.mgl.android.FrameSync.IFrameListener
        public void onTick(float f2, float f3, long j2) {
            GLViewHolder gLViewHolder = MglRenderer.this.mView;
            if (gLViewHolder != null) {
                gLViewHolder.requestRender();
            }
        }
    }

    public class GLSurfaceViewConfigChooser implements GLSurfaceView.EGLConfigChooser {
        public GLSurfaceViewConfigChooser() {
        }

        @Override // android.opengl.GLSurfaceView.EGLConfigChooser
        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            return MglRenderer.this.createEGLConfig(egl10, eGLDisplay);
        }
    }

    public class GLSurfaceViewHolder extends GLViewHolder {
        private GLSurfaceView mGLSurfaceView;

        public GLSurfaceViewHolder(@NonNull GLSurfaceView gLSurfaceView) {
            super();
            this.mGLSurfaceView = gLSurfaceView;
            MglRenderer.this.initGLSurfaceView(gLSurfaceView);
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void detach() {
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public View getView() {
            return this.mGLSurfaceView;
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void pause() {
            this.mGLSurfaceView.onPause();
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void queueEvent(Runnable runnable) {
            this.mGLSurfaceView.queueEvent(runnable);
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void requestRender() {
            this.mGLSurfaceView.requestRender();
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void resume() {
            this.mGLSurfaceView.onResume();
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void setBufferSize(int i2, int i3) {
            if (this.mGLSurfaceView.getHolder() != null) {
                this.mGLSurfaceView.getHolder().setFixedSize(i2, i3);
            }
        }
    }

    public class GLSurfaceViewListener implements GLSurfaceView.Renderer {
        public GLSurfaceViewListener() {
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public void onDrawFrame(GL10 gl10) {
            MglRenderer.this.drawFrame();
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public void onSurfaceChanged(GL10 gl10, int i2, int i3) {
            MglRenderer.this.surfaceChanged(i2, i3);
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            MglRenderer.this.surfaceCreated(eGLConfig);
        }
    }

    public class GLTextureViewConfigChooser implements GLTextureView.EGLConfigChooser {
        public GLTextureViewConfigChooser() {
        }

        @Override // miuix.mgl.android.GLTextureView.EGLConfigChooser
        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            return MglRenderer.this.createEGLConfig(egl10, eGLDisplay);
        }
    }

    public class GLTextureViewHolder extends GLViewHolder {
        private GLTextureView mGLTextureView;

        public GLTextureViewHolder(@NonNull GLTextureView gLTextureView) {
            super();
            this.mGLTextureView = gLTextureView;
            MglRenderer.this.initGLTextureView(gLTextureView);
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void detach() {
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public View getView() {
            return this.mGLTextureView;
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void pause() {
            this.mGLTextureView.onPause();
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void queueEvent(Runnable runnable) {
            this.mGLTextureView.queueEvent(runnable);
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void requestRender() {
            this.mGLTextureView.requestRender();
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void resume() {
            this.mGLTextureView.onResume();
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void setBufferSize(int i2, int i3) {
            if (this.mGLTextureView.getSurfaceTexture() != null) {
                this.mGLTextureView.getSurfaceTexture().setDefaultBufferSize(i2, i3);
            }
        }
    }

    public class GLTextureViewListener implements GLTextureView.Renderer {
        public GLTextureViewListener() {
        }

        @Override // miuix.mgl.android.GLTextureView.Renderer
        public boolean onDrawFrame(GL10 gl10, Object obj) {
            MglRenderer.this.drawFrame();
            return true;
        }

        @Override // miuix.mgl.android.GLTextureView.Renderer
        public void onSurfaceChanged(GL10 gl10, int i2, int i3) {
            MglRenderer.this.surfaceChanged(i2, i3);
        }

        @Override // miuix.mgl.android.GLTextureView.Renderer
        public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            MglRenderer.this.surfaceCreated(eGLConfig);
        }

        @Override // miuix.mgl.android.GLTextureView.Renderer
        public void onSurfaceDestroyed() {
        }
    }

    public abstract class GLViewHolder {
        public GLViewHolder() {
        }

        public abstract void detach();

        public abstract View getView();

        public abstract void pause();

        public abstract void queueEvent(Runnable runnable);

        public abstract void requestRender();

        public abstract void resume();

        public abstract void setBufferSize(int i2, int i3);
    }

    public class LayoutChangeListener implements View.OnLayoutChangeListener {
        public LayoutChangeListener() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            if (MglRenderer.this.getViewHolder() != null) {
                MglRenderer.this.getViewHolder().setBufferSize((int) (view.getWidth() * MglRenderer.this.mBufferScale), (int) (view.getHeight() * MglRenderer.this.mBufferScale));
            }
        }
    }

    public class MGLSurfaceViewConfigChooser implements MGLSurfaceView.EGLConfigChooser {
        public MGLSurfaceViewConfigChooser() {
        }

        @Override // miuix.mgl.android.MGLSurfaceView.EGLConfigChooser
        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            return MglRenderer.this.createEGLConfig(egl10, eGLDisplay);
        }
    }

    public class MGLSurfaceViewHolder extends GLViewHolder {
        private MGLSurfaceView mGLSurfaceView;

        public MGLSurfaceViewHolder(@NonNull MGLSurfaceView mGLSurfaceView) {
            super();
            this.mGLSurfaceView = mGLSurfaceView;
            MglRenderer.this.initMGLSurfaceView(mGLSurfaceView);
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void detach() {
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public View getView() {
            return this.mGLSurfaceView;
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void pause() {
            this.mGLSurfaceView.onPause();
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void queueEvent(Runnable runnable) {
            this.mGLSurfaceView.queueEvent(runnable);
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void requestRender() {
            this.mGLSurfaceView.requestRender();
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void resume() {
            this.mGLSurfaceView.onResume();
        }

        @Override // miuix.mgl.android.MglRenderer.GLViewHolder
        public void setBufferSize(int i2, int i3) {
            if (this.mGLSurfaceView.getHolder() != null) {
                this.mGLSurfaceView.getHolder().setFixedSize(i2, i3);
            }
        }
    }

    public class MGLSurfaceViewListener implements MGLSurfaceView.Renderer {
        public MGLSurfaceViewListener() {
        }

        @Override // miuix.mgl.android.MGLSurfaceView.Renderer
        public void onDrawFrame(GL10 gl10) {
            MglRenderer.this.drawFrame();
        }

        @Override // miuix.mgl.android.MGLSurfaceView.Renderer
        public void onSurfaceChanged(GL10 gl10, int i2, int i3) {
            MglRenderer.this.surfaceChanged(i2, i3);
        }

        @Override // miuix.mgl.android.MGLSurfaceView.Renderer
        public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            MglRenderer.this.surfaceCreated(eGLConfig);
        }
    }

    public static class Options {
        Context context;
        int msaa;
        boolean isOpaque = true;
        float bufferScale = 1.0f;
        DepthSize depthSize = DepthSize.NO_DEPTH;

        public Options(@NonNull Context context) {
            this.context = context;
        }
    }

    public static class RenderContext {
        private Context mAndroidContext;
        private AssetRepo mAssetRepo;
        private float mDeltaTime;
        private boolean mIsOpaque;
        private long mLastGlobalTime;
        private RenderTexture mScreenRT;
        private float mSinTime;
        private float mTime;
        private int mWidth = 1;
        private int mHeight = 1;
        float mAspect = 1.0f;
        float mWh = 1.0f;
        float mHw = 1.0f;
        private MglContext mContext = new MglContext();

        public RenderContext(Context context) {
            this.mAndroidContext = context;
            AssetRepo assetRepo = new AssetRepo(context);
            this.mAssetRepo = assetRepo;
            assetRepo.initContext(this.mContext);
            this.mLastGlobalTime = System.nanoTime();
            this.mScreenRT = RenderTexture.Builder.create().width(100).height(100).isDefault(true).clearColor(0.0f, 0.0f, 0.0f, 0.0f).build(this.mContext);
        }

        public void bindScreen(int i2) {
            this.mScreenRT.active(i2);
        }

        public void clearNativeResource() {
            this.mAssetRepo.clearNativeResource();
            this.mContext.destroy();
            this.mScreenRT.destroy(false);
        }

        public float getAspect() {
            return this.mAspect;
        }

        public Context getContext() {
            return this.mAndroidContext;
        }

        public float getDeltaTime() {
            return this.mDeltaTime;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public float getHwRatio() {
            return this.mHw;
        }

        public MglContext getMglContext() {
            return this.mContext;
        }

        public RenderTexture getScreenRT() {
            return this.mScreenRT;
        }

        public float getSinTime() {
            return this.mSinTime;
        }

        public float getTime() {
            return this.mTime;
        }

        public float getWhRatio() {
            return this.mWh;
        }

        public int getWidth() {
            return this.mWidth;
        }

        public AssetRepo getmAssetRepo() {
            return this.mAssetRepo;
        }

        public boolean isOpaque() {
            return this.mIsOpaque;
        }

        public void pause() {
        }

        public void resetTime() {
            this.mLastGlobalTime = System.nanoTime();
            this.mTime = 0.0f;
            this.mSinTime = 0.0f;
            this.mDeltaTime = 0.0f;
        }

        public void resize(int i2, int i3) {
            if (i2 == 0) {
                Log.w(MglRenderer.TAG, "RenderContext resize width is 0");
                i2 = 1;
            }
            if (i3 == 0) {
                Log.w(MglRenderer.TAG, "RenderContext resize height is 0");
                i3 = 1;
            }
            this.mWidth = i2;
            this.mHeight = i3;
            float f2 = i2 / i3;
            this.mAspect = f2;
            this.mWh = f2;
            this.mHw = 1.0f / f2;
            this.mScreenRT.resize(i2, i3);
        }

        public void resume() {
            this.mLastGlobalTime = System.nanoTime();
        }

        public void setOpaque(boolean z2) {
            this.mIsOpaque = z2;
        }

        public void tick() {
            long jNanoTime = System.nanoTime();
            float f2 = (float) ((jNanoTime - this.mLastGlobalTime) * 1.0E-9d);
            this.mDeltaTime = f2;
            float f3 = this.mTime + f2;
            this.mTime = f3;
            this.mSinTime = f3 % 6.2831855f;
            this.mLastGlobalTime = jNanoTime;
        }
    }

    public MglRenderer(Options options) {
        init(options);
    }

    private void clearEvent() {
        synchronized (this.mEventQueueLock) {
            this.mEventQueue.clear();
            this.mRunEventQueue.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EGLConfig createEGLConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
        int i2 = this.mIsOpaque ? 0 : 8;
        int i3 = this.mDepthSize.size;
        int i4 = this.mEnableStencil ? 8 : 0;
        int i5 = this.mMsaa;
        int[] iArr = {12352, 4, 12351, 12430, 12324, 8, 12323, 8, 12322, 8, 12321, i2, 12325, i3, 12326, i4, 12338, i5 > 0 ? 1 : 0, 12337, i5, 12344};
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        int[] iArr2 = new int[1];
        egl10.eglChooseConfig(eGLDisplay, iArr, eGLConfigArr, 1, iArr2);
        if (iArr2[0] == 0) {
            return null;
        }
        return eGLConfigArr[0];
    }

    private void executeEvent() {
        ArrayList<Runnable> arrayList;
        synchronized (this.mEventQueueLock) {
            ArrayList<Runnable> arrayList2 = this.mRunEventQueue;
            arrayList = this.mEventQueue;
            this.mRunEventQueue = arrayList;
            this.mEventQueue = arrayList2;
        }
        Iterator<Runnable> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
        this.mRunEventQueue.clear();
    }

    private void init(Options options) {
        this.mContext = options.context;
        this.mIsOpaque = options.isOpaque;
        this.mMsaa = options.msaa;
        setBufferScale(options.bufferScale);
        this.mDepthSize = options.depthSize;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$resume$0() {
        RenderContext renderContext = this.mRenderContext;
        if (renderContext != null) {
            renderContext.resume();
        }
    }

    private void resetLastSize() {
        this.mLastWidth = -1;
        this.mLastHeight = -1;
    }

    public void attachToGLSurfaceView(GLSurfaceView gLSurfaceView) {
        if (this.mView == null && gLSurfaceView != null) {
            gLSurfaceView.addOnLayoutChangeListener(new LayoutChangeListener());
            this.mView = new GLSurfaceViewHolder(gLSurfaceView);
        }
    }

    public void attachToMGLSurfaceView(MGLSurfaceView mGLSurfaceView) {
        if (this.mView == null && mGLSurfaceView != null) {
            mGLSurfaceView.addOnLayoutChangeListener(new LayoutChangeListener());
            this.mView = new MGLSurfaceViewHolder(mGLSurfaceView);
        }
    }

    public void attachToTextureView(GLTextureView gLTextureView) {
        if (this.mView == null && gLTextureView != null) {
            gLTextureView.addOnLayoutChangeListener(new LayoutChangeListener());
            this.mView = new GLTextureViewHolder(gLTextureView);
        }
    }

    public void attachToView(View view) {
        if (view instanceof GLTextureView) {
            attachToTextureView((GLTextureView) view);
            return;
        }
        if (view instanceof GLSurfaceView) {
            attachToGLSurfaceView((GLSurfaceView) view);
        } else if (view instanceof MGLSurfaceView) {
            attachToMGLSurfaceView((MGLSurfaceView) view);
        } else {
            Log.w(TAG, "attachToView fail:invalid view");
        }
    }

    public void drawFrame() {
        this.mRenderContext.tick();
        executeEvent();
        onDrawFrame();
    }

    public void enableStencil(boolean z2) {
        this.mEnableStencil = z2;
    }

    public AssetRepo getAssetRepo() {
        return this.mRenderContext.getmAssetRepo();
    }

    public Context getContext() {
        return this.mContext;
    }

    public float getDeltaTime() {
        return this.mRenderContext.getDeltaTime();
    }

    public FrameSync getFrameSync() {
        return this.mFrameSync;
    }

    public GLSurfaceView getGLSurfaceView() {
        GLViewHolder gLViewHolder = this.mView;
        if (gLViewHolder instanceof GLSurfaceViewHolder) {
            return (GLSurfaceView) gLViewHolder.getView();
        }
        return null;
    }

    public GLTextureView getGLTextureView() {
        GLViewHolder gLViewHolder = this.mView;
        if (gLViewHolder instanceof GLTextureViewHolder) {
            return (GLTextureView) gLViewHolder.getView();
        }
        return null;
    }

    public MglContext getMglContext() {
        return this.mRenderContext.getMglContext();
    }

    public RenderContext getRenderContext() {
        return this.mRenderContext;
    }

    public RenderTexture getScreenRT() {
        return this.mRenderContext.getScreenRT();
    }

    public float getSinTime() {
        return this.mRenderContext.getSinTime();
    }

    public float getTime() {
        return this.mRenderContext.getTime();
    }

    public GLViewHolder getViewHolder() {
        return this.mView;
    }

    public void initGLSurfaceView(GLSurfaceView gLSurfaceView) {
        SurfaceHolder holder = gLSurfaceView.getHolder();
        if (this.mIsOpaque) {
            holder.setFormat(-1);
        } else {
            gLSurfaceView.setZOrderOnTop(true);
            holder.setFormat(-3);
        }
        gLSurfaceView.setEGLContextClientVersion(3);
        gLSurfaceView.setEGLConfigChooser(new GLSurfaceViewConfigChooser());
        gLSurfaceView.setRenderer(new GLSurfaceViewListener());
        gLSurfaceView.setRenderMode(0);
        if (this.mPause) {
            return;
        }
        gLSurfaceView.requestRender();
    }

    public void initGLTextureView(GLTextureView gLTextureView) {
        gLTextureView.setOpaque(this.mIsOpaque);
        gLTextureView.setColorSpace(this.mColorSpace);
        gLTextureView.setEGLContextClientVersion(3);
        gLTextureView.setEGLConfigChooser(new GLTextureViewConfigChooser());
        gLTextureView.setRenderer(new GLTextureViewListener());
        gLTextureView.setRenderMode(0);
        if (this.mPause) {
            return;
        }
        gLTextureView.requestRender();
    }

    public void initMGLSurfaceView(MGLSurfaceView mGLSurfaceView) {
        SurfaceHolder holder = mGLSurfaceView.getHolder();
        if (this.mIsOpaque) {
            holder.setFormat(-1);
        } else {
            mGLSurfaceView.setZOrderOnTop(true);
            holder.setFormat(-3);
        }
        mGLSurfaceView.setEGLContextClientVersion(3);
        mGLSurfaceView.setColorSpace(this.mColorSpace);
        mGLSurfaceView.setEGLConfigChooser(new MGLSurfaceViewConfigChooser());
        mGLSurfaceView.setRenderer(new MGLSurfaceViewListener());
        mGLSurfaceView.setRenderMode(0);
        if (this.mPause) {
            return;
        }
        mGLSurfaceView.requestRender();
    }

    public boolean isOpaque() {
        return this.mIsOpaque;
    }

    public boolean isPause() {
        return this.mPause;
    }

    public abstract void onDrawFrame();

    public abstract void onSurfaceChanged(int i2, int i3);

    public abstract void onSurfaceCreated(EGLConfig eGLConfig);

    public void pause() {
        this.mPause = true;
        this.mFrameSync.enable(false);
    }

    public void queueEvent(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("r must not be null");
        }
        synchronized (this.mEventQueueLock) {
            this.mEventQueue.add(runnable);
        }
    }

    public void resume() {
        this.mPause = false;
        this.mFrameSync.enable(true);
        GLViewHolder gLViewHolder = this.mView;
        if (gLViewHolder != null) {
            gLViewHolder.requestRender();
            this.mView.queueEvent(new Runnable() { // from class: miuix.mgl.android.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f6123a.lambda$resume$0();
                }
            });
        }
    }

    public void setBufferScale(float f2) {
        this.mBufferScale = f2;
        this.mBufferScale = Math.Companion.clamp(f2, 0.01f, 1.0f);
        GLViewHolder gLViewHolder = this.mView;
        if (gLViewHolder == null || gLViewHolder.getView().getWidth() <= 0 || this.mView.getView().getHeight() <= 0) {
            return;
        }
        this.mView.setBufferSize((int) (this.mView.getView().getWidth() * this.mBufferScale), (int) (this.mView.getView().getHeight() * this.mBufferScale));
    }

    public void setColorSpace(ColorSpace colorSpace) {
        this.mColorSpace = colorSpace.color;
    }

    public void surfaceChanged(int i2, int i3) {
        if (this.mLastWidth == i2 && this.mLastHeight == i3) {
            return;
        }
        this.mLastWidth = i2;
        this.mLastHeight = i3;
        this.mRenderContext.resize(i2, i3);
        onSurfaceChanged(i2, i3);
    }

    public void surfaceCreated(EGLConfig eGLConfig) {
        if (!this.mFirstCreate) {
            clearEvent();
        }
        resetLastSize();
        MglContext.getInstance().reset();
        RenderContext renderContext = this.mRenderContext;
        if (renderContext != null) {
            renderContext.clearNativeResource();
        }
        RenderContext renderContext2 = new RenderContext(this.mContext);
        this.mRenderContext = renderContext2;
        renderContext2.setOpaque(this.mIsOpaque);
        onSurfaceCreated(eGLConfig);
        this.mFirstCreate = false;
    }

    public MglRenderer(@NonNull Context context, boolean z2, int i2) {
        Options options = new Options(context);
        options.isOpaque = z2;
        options.msaa = i2;
        init(options);
    }

    public MglRenderer(@NonNull Context context, boolean z2, int i2, float f2) {
        Options options = new Options(context);
        options.isOpaque = z2;
        options.msaa = i2;
        options.bufferScale = f2;
        init(options);
    }

    public MglRenderer(@NonNull Context context, boolean z2, int i2, float f2, DepthSize depthSize) {
        Options options = new Options(context);
        options.isOpaque = z2;
        options.msaa = i2;
        options.bufferScale = f2;
        options.depthSize = depthSize;
        init(options);
    }
}
