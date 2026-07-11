package miuix.mgl.android;

import android.content.Context;
import android.opengl.GLDebugHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes3.dex */
public class MGLSurfaceView extends SurfaceView implements SurfaceHolder.Callback2 {
    public static final int DEBUG_CHECK_GL_ERROR = 1;
    public static final int DEBUG_LOG_GL_CALLS = 2;
    private static final boolean LOG_ATTACH_DETACH = false;
    private static final boolean LOG_EGL = false;
    private static final boolean LOG_PAUSE_RESUME = false;
    private static final boolean LOG_RENDERER = false;
    private static final boolean LOG_RENDERER_DRAW_FRAME = false;
    private static final boolean LOG_SURFACE = false;
    private static final boolean LOG_THREADS = false;
    public static final int RENDERMODE_CONTINUOUSLY = 1;
    public static final int RENDERMODE_WHEN_DIRTY = 0;
    private static final String TAG = "MGLSurfaceView";
    private static final GLThreadManager sGLThreadManager = new GLThreadManager();
    private int mColorSpace;
    private int mDebugFlags;
    private boolean mDetached;
    private EGLConfigChooser mEGLConfigChooser;
    private int mEGLContextClientVersion;
    private EGLContextFactory mEGLContextFactory;
    private EGLWindowSurfaceFactory mEGLWindowSurfaceFactory;
    private GLThread mGLThread;
    private GLWrapper mGLWrapper;
    private boolean mPreserveEGLContextOnPause;
    private Renderer mRenderer;
    private final WeakReference<MGLSurfaceView> mThisWeakRef;

    public abstract class BaseConfigChooser implements EGLConfigChooser {
        protected int[] mConfigSpec;

        public BaseConfigChooser(int[] iArr) {
            this.mConfigSpec = filterConfigSpec(iArr);
        }

        private int[] filterConfigSpec(int[] iArr) {
            if (MGLSurfaceView.this.mEGLContextClientVersion != 2 && MGLSurfaceView.this.mEGLContextClientVersion != 3) {
                return iArr;
            }
            int length = iArr.length;
            int[] iArr2 = new int[length + 2];
            int i2 = length - 1;
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            iArr2[i2] = 12352;
            if (MGLSurfaceView.this.mEGLContextClientVersion == 2) {
                iArr2[length] = 4;
            } else {
                iArr2[length] = 64;
            }
            iArr2[length + 1] = 12344;
            return iArr2;
        }

        @Override // miuix.mgl.android.MGLSurfaceView.EGLConfigChooser
        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            if (!egl10.eglChooseConfig(eGLDisplay, this.mConfigSpec, null, 0, iArr)) {
                throw new IllegalArgumentException("eglChooseConfig failed");
            }
            int i2 = iArr[0];
            if (i2 <= 0) {
                throw new IllegalArgumentException("No configs match configSpec");
            }
            EGLConfig[] eGLConfigArr = new EGLConfig[i2];
            if (!egl10.eglChooseConfig(eGLDisplay, this.mConfigSpec, eGLConfigArr, i2, iArr)) {
                throw new IllegalArgumentException("eglChooseConfig#2 failed");
            }
            EGLConfig eGLConfigChooseConfig = chooseConfig(egl10, eGLDisplay, eGLConfigArr);
            if (eGLConfigChooseConfig != null) {
                return eGLConfigChooseConfig;
            }
            throw new IllegalArgumentException("No config chosen");
        }

        public abstract EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);
    }

    public class ComponentSizeChooser extends BaseConfigChooser {
        protected int mAlphaSize;
        protected int mBlueSize;
        protected int mDepthSize;
        protected int mGreenSize;
        protected int mRedSize;
        protected int mStencilSize;
        private int[] mValue;

        public ComponentSizeChooser(int i2, int i3, int i4, int i5, int i6, int i7) {
            super(new int[]{12324, i2, 12323, i3, 12322, i4, 12321, i5, 12325, i6, 12326, i7, 12344});
            this.mValue = new int[1];
            this.mRedSize = i2;
            this.mGreenSize = i3;
            this.mBlueSize = i4;
            this.mAlphaSize = i5;
            this.mDepthSize = i6;
            this.mStencilSize = i7;
        }

        private int findConfigAttrib(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i2, int i3) {
            return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i2, this.mValue) ? this.mValue[0] : i3;
        }

        @Override // miuix.mgl.android.MGLSurfaceView.BaseConfigChooser
        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int iFindConfigAttrib = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int iFindConfigAttrib2 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (iFindConfigAttrib >= this.mDepthSize && iFindConfigAttrib2 >= this.mStencilSize) {
                    int iFindConfigAttrib3 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int iFindConfigAttrib4 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int iFindConfigAttrib5 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    int iFindConfigAttrib6 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (iFindConfigAttrib3 == this.mRedSize && iFindConfigAttrib4 == this.mGreenSize && iFindConfigAttrib5 == this.mBlueSize && iFindConfigAttrib6 == this.mAlphaSize) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }
    }

    public class DefaultContextFactory implements EGLContextFactory {
        private int EGL_CONTEXT_CLIENT_VERSION;

        private DefaultContextFactory() {
            this.EGL_CONTEXT_CLIENT_VERSION = 12440;
        }

        @Override // miuix.mgl.android.MGLSurfaceView.EGLContextFactory
        public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = {this.EGL_CONTEXT_CLIENT_VERSION, MGLSurfaceView.this.mEGLContextClientVersion, 12344};
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (MGLSurfaceView.this.mEGLContextClientVersion == 0) {
                iArr = null;
            }
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
        }

        @Override // miuix.mgl.android.MGLSurfaceView.EGLContextFactory
        public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                return;
            }
            Log.e("DefaultContextFactory", "display:" + eGLDisplay + " context: " + eGLContext);
            EglHelper.throwEglException("eglDestroyContex", egl10.eglGetError());
        }
    }

    public static class DefaultWindowSurfaceFactory implements EGLWindowSurfaceFactory {
        private DefaultWindowSurfaceFactory() {
        }

        @Override // miuix.mgl.android.MGLSurfaceView.EGLWindowSurfaceFactory
        public EGLSurface createWindowSurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj, int i2) {
            int[] iArr;
            if (i2 > 0) {
                try {
                    iArr = new int[]{12445, i2, 12344};
                } catch (IllegalArgumentException e2) {
                    Log.e(MGLSurfaceView.TAG, "eglCreateWindowSurface", e2);
                    return null;
                }
            } else {
                iArr = null;
            }
            return egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, iArr);
        }

        @Override // miuix.mgl.android.MGLSurfaceView.EGLWindowSurfaceFactory
        public void destroySurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    public interface EGLConfigChooser {
        EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay);
    }

    public interface EGLContextFactory {
        EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig);

        void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext);
    }

    public interface EGLWindowSurfaceFactory {
        EGLSurface createWindowSurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj, int i2);

        void destroySurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface);
    }

    public static class EglHelper {
        EGL10 mEgl;
        EGLConfig mEglConfig;
        EGLContext mEglContext;
        EGLDisplay mEglDisplay;
        EGLSurface mEglSurface;
        private WeakReference<MGLSurfaceView> mGLSurfaceViewWeakRef;

        public EglHelper(WeakReference<MGLSurfaceView> weakReference) {
            this.mGLSurfaceViewWeakRef = weakReference;
        }

        private void destroySurfaceImp() {
            EGLSurface eGLSurface;
            EGLSurface eGLSurface2 = this.mEglSurface;
            if (eGLSurface2 == null || eGLSurface2 == (eGLSurface = EGL10.EGL_NO_SURFACE)) {
                return;
            }
            this.mEgl.eglMakeCurrent(this.mEglDisplay, eGLSurface, eGLSurface, EGL10.EGL_NO_CONTEXT);
            MGLSurfaceView mGLSurfaceView = this.mGLSurfaceViewWeakRef.get();
            if (mGLSurfaceView != null) {
                mGLSurfaceView.mEGLWindowSurfaceFactory.destroySurface(this.mEgl, this.mEglDisplay, this.mEglSurface);
            }
            this.mEglSurface = null;
        }

        public static String eglGetErrorString(int i2) {
            switch (i2) {
                case 12288:
                    return "EGL_SUCCESS";
                case 12289:
                    return "EGL_NOT_INITIALIZED";
                case 12290:
                    return "EGL_BAD_ACCESS";
                case 12291:
                    return "EGL_BAD_ALLOC";
                case 12292:
                    return "EGL_BAD_ATTRIBUTE";
                case 12293:
                    return "EGL_BAD_CONFIG";
                case 12294:
                    return "EGL_BAD_CONTEXT";
                case 12295:
                    return "EGL_BAD_CURRENT_SURFACE";
                case 12296:
                    return "EGL_BAD_DISPLAY";
                case 12297:
                    return "EGL_BAD_MATCH";
                case 12298:
                    return "EGL_BAD_NATIVE_PIXMAP";
                case 12299:
                    return "EGL_BAD_NATIVE_WINDOW";
                case 12300:
                    return "EGL_BAD_PARAMETER";
                case 12301:
                    return "EGL_BAD_SURFACE";
                case 12302:
                    return "EGL_CONTEXT_LOST";
                default:
                    return "0x" + Integer.toHexString(i2);
            }
        }

        public static String formatEglError(String str, int i2) {
            return str + " failed: " + eglGetErrorString(i2);
        }

        public static void logEglErrorAsWarning(String str, String str2, int i2) {
            Log.w(str, formatEglError(str2, i2));
        }

        private void throwEglException(String str) {
            throwEglException(str, this.mEgl.eglGetError());
        }

        public GL createGL() {
            GL gl = this.mEglContext.getGL();
            MGLSurfaceView mGLSurfaceView = this.mGLSurfaceViewWeakRef.get();
            if (mGLSurfaceView == null) {
                return gl;
            }
            if (mGLSurfaceView.mGLWrapper != null) {
                gl = mGLSurfaceView.mGLWrapper.wrap(gl);
            }
            if ((mGLSurfaceView.mDebugFlags & 3) != 0) {
                return GLDebugHelper.wrap(gl, (mGLSurfaceView.mDebugFlags & 1) == 0 ? 0 : 1, (mGLSurfaceView.mDebugFlags & 2) != 0 ? new LogWriter() : null);
            }
            return gl;
        }

        public boolean createSurface() {
            if (this.mEgl == null) {
                throw new RuntimeException("egl not initialized");
            }
            if (this.mEglDisplay == null) {
                throw new RuntimeException("eglDisplay not initialized");
            }
            if (this.mEglConfig == null) {
                throw new RuntimeException("mEglConfig not initialized");
            }
            destroySurfaceImp();
            MGLSurfaceView mGLSurfaceView = this.mGLSurfaceViewWeakRef.get();
            if (mGLSurfaceView != null) {
                this.mEglSurface = mGLSurfaceView.mEGLWindowSurfaceFactory.createWindowSurface(this.mEgl, this.mEglDisplay, this.mEglConfig, mGLSurfaceView.getHolder(), mGLSurfaceView.mColorSpace);
            } else {
                this.mEglSurface = null;
            }
            EGLSurface eGLSurface = this.mEglSurface;
            if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                if (this.mEgl.eglGetError() == 12299) {
                    Log.e("EglHelper", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                }
                return false;
            }
            if (this.mEgl.eglMakeCurrent(this.mEglDisplay, eGLSurface, eGLSurface, this.mEglContext)) {
                return true;
            }
            logEglErrorAsWarning("EGLHelper", "eglMakeCurrent", this.mEgl.eglGetError());
            return false;
        }

        public void destroySurface() {
            destroySurfaceImp();
        }

        public void finish() {
            if (this.mEglContext != null) {
                MGLSurfaceView mGLSurfaceView = this.mGLSurfaceViewWeakRef.get();
                if (mGLSurfaceView != null) {
                    mGLSurfaceView.mEGLContextFactory.destroyContext(this.mEgl, this.mEglDisplay, this.mEglContext);
                }
                this.mEglContext = null;
            }
            EGLDisplay eGLDisplay = this.mEglDisplay;
            if (eGLDisplay != null) {
                this.mEgl.eglTerminate(eGLDisplay);
                this.mEglDisplay = null;
            }
        }

        public void start() {
            EGL10 egl10 = (EGL10) EGLContext.getEGL();
            this.mEgl = egl10;
            EGLDisplay eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            this.mEglDisplay = eGLDisplayEglGetDisplay;
            if (eGLDisplayEglGetDisplay == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed");
            }
            if (!this.mEgl.eglInitialize(eGLDisplayEglGetDisplay, new int[2])) {
                throw new RuntimeException("eglInitialize failed");
            }
            MGLSurfaceView mGLSurfaceView = this.mGLSurfaceViewWeakRef.get();
            if (mGLSurfaceView == null) {
                this.mEglConfig = null;
                this.mEglContext = null;
            } else {
                this.mEglConfig = mGLSurfaceView.mEGLConfigChooser.chooseConfig(this.mEgl, this.mEglDisplay);
                this.mEglContext = mGLSurfaceView.mEGLContextFactory.createContext(this.mEgl, this.mEglDisplay, this.mEglConfig);
            }
            EGLContext eGLContext = this.mEglContext;
            if (eGLContext == null || eGLContext == EGL10.EGL_NO_CONTEXT) {
                this.mEglContext = null;
                throwEglException("createContext");
            }
            this.mEglSurface = null;
        }

        public int swap() {
            if (this.mEgl.eglSwapBuffers(this.mEglDisplay, this.mEglSurface)) {
                return 12288;
            }
            return this.mEgl.eglGetError();
        }

        public static void throwEglException(String str, int i2) {
            throw new RuntimeException(formatEglError(str, i2));
        }
    }

    public static class GLThread extends Thread {
        private EglHelper mEglHelper;
        private boolean mExited;
        private boolean mFinishedCreatingEglSurface;
        private WeakReference<MGLSurfaceView> mGLSurfaceViewWeakRef;
        private boolean mHasSurface;
        private boolean mHaveEglContext;
        private boolean mHaveEglSurface;
        private boolean mPaused;
        private boolean mRenderComplete;
        private boolean mRequestPaused;
        private boolean mShouldExit;
        private boolean mShouldReleaseEglContext;
        private boolean mSurfaceIsBad;
        private boolean mWaitingForSurface;
        private ArrayList<Runnable> mEventQueue = new ArrayList<>();
        private boolean mSizeChanged = true;
        private Runnable mFinishDrawingRunnable = null;
        private int mWidth = 0;
        private int mHeight = 0;
        private boolean mRequestRender = true;
        private int mRenderMode = 1;
        private boolean mWantRenderNotification = false;

        public GLThread(WeakReference<MGLSurfaceView> weakReference) {
            this.mGLSurfaceViewWeakRef = weakReference;
        }

        /* JADX WARN: Removed duplicated region for block: B:116:0x0184 A[Catch: all -> 0x014a, TryCatch #6 {all -> 0x014a, blocks: (B:3:0x001f, B:4:0x0023, B:90:0x0143, B:95:0x014f, B:97:0x0157, B:98:0x015b, B:105:0x016b, B:106:0x016c, B:107:0x0170, B:114:0x0181, B:116:0x0184, B:118:0x0190, B:120:0x019a, B:123:0x01a8, B:125:0x01b2, B:127:0x01ba, B:129:0x01c4, B:131:0x01cd, B:132:0x01d1, B:136:0x01df, B:137:0x01ec, B:144:0x01fb, B:154:0x0222, B:139:0x01ee, B:140:0x01f7, B:100:0x015d, B:101:0x0166, B:5:0x0024, B:7:0x0028, B:18:0x003c, B:20:0x0044, B:88:0x0140, B:21:0x0051, B:23:0x0057, B:25:0x0062, B:27:0x0066, B:29:0x0072, B:31:0x007b, B:33:0x007f, B:35:0x0084, B:37:0x0088, B:42:0x009a, B:40:0x0094, B:43:0x009d, B:45:0x00a1, B:47:0x00a5, B:49:0x00a9, B:50:0x00ac, B:51:0x00b9, B:53:0x00bd, B:55:0x00c1, B:57:0x00cd, B:58:0x00db, B:60:0x00df, B:62:0x00e5, B:64:0x00eb, B:68:0x00f3, B:70:0x00f9, B:72:0x0105, B:73:0x010c, B:74:0x010d, B:76:0x0111, B:78:0x0115, B:79:0x011b, B:81:0x011f, B:83:0x0123, B:85:0x0132, B:152:0x0217, B:151:0x020c, B:109:0x0172, B:110:0x017d), top: B:174:0x001f, inners: #3, #4, #5, #7 }] */
        /* JADX WARN: Removed duplicated region for block: B:118:0x0190 A[Catch: all -> 0x014a, TryCatch #6 {all -> 0x014a, blocks: (B:3:0x001f, B:4:0x0023, B:90:0x0143, B:95:0x014f, B:97:0x0157, B:98:0x015b, B:105:0x016b, B:106:0x016c, B:107:0x0170, B:114:0x0181, B:116:0x0184, B:118:0x0190, B:120:0x019a, B:123:0x01a8, B:125:0x01b2, B:127:0x01ba, B:129:0x01c4, B:131:0x01cd, B:132:0x01d1, B:136:0x01df, B:137:0x01ec, B:144:0x01fb, B:154:0x0222, B:139:0x01ee, B:140:0x01f7, B:100:0x015d, B:101:0x0166, B:5:0x0024, B:7:0x0028, B:18:0x003c, B:20:0x0044, B:88:0x0140, B:21:0x0051, B:23:0x0057, B:25:0x0062, B:27:0x0066, B:29:0x0072, B:31:0x007b, B:33:0x007f, B:35:0x0084, B:37:0x0088, B:42:0x009a, B:40:0x0094, B:43:0x009d, B:45:0x00a1, B:47:0x00a5, B:49:0x00a9, B:50:0x00ac, B:51:0x00b9, B:53:0x00bd, B:55:0x00c1, B:57:0x00cd, B:58:0x00db, B:60:0x00df, B:62:0x00e5, B:64:0x00eb, B:68:0x00f3, B:70:0x00f9, B:72:0x0105, B:73:0x010c, B:74:0x010d, B:76:0x0111, B:78:0x0115, B:79:0x011b, B:81:0x011f, B:83:0x0123, B:85:0x0132, B:152:0x0217, B:151:0x020c, B:109:0x0172, B:110:0x017d), top: B:174:0x001f, inners: #3, #4, #5, #7 }] */
        /* JADX WARN: Removed duplicated region for block: B:123:0x01a8 A[Catch: all -> 0x014a, TryCatch #6 {all -> 0x014a, blocks: (B:3:0x001f, B:4:0x0023, B:90:0x0143, B:95:0x014f, B:97:0x0157, B:98:0x015b, B:105:0x016b, B:106:0x016c, B:107:0x0170, B:114:0x0181, B:116:0x0184, B:118:0x0190, B:120:0x019a, B:123:0x01a8, B:125:0x01b2, B:127:0x01ba, B:129:0x01c4, B:131:0x01cd, B:132:0x01d1, B:136:0x01df, B:137:0x01ec, B:144:0x01fb, B:154:0x0222, B:139:0x01ee, B:140:0x01f7, B:100:0x015d, B:101:0x0166, B:5:0x0024, B:7:0x0028, B:18:0x003c, B:20:0x0044, B:88:0x0140, B:21:0x0051, B:23:0x0057, B:25:0x0062, B:27:0x0066, B:29:0x0072, B:31:0x007b, B:33:0x007f, B:35:0x0084, B:37:0x0088, B:42:0x009a, B:40:0x0094, B:43:0x009d, B:45:0x00a1, B:47:0x00a5, B:49:0x00a9, B:50:0x00ac, B:51:0x00b9, B:53:0x00bd, B:55:0x00c1, B:57:0x00cd, B:58:0x00db, B:60:0x00df, B:62:0x00e5, B:64:0x00eb, B:68:0x00f3, B:70:0x00f9, B:72:0x0105, B:73:0x010c, B:74:0x010d, B:76:0x0111, B:78:0x0115, B:79:0x011b, B:81:0x011f, B:83:0x0123, B:85:0x0132, B:152:0x0217, B:151:0x020c, B:109:0x0172, B:110:0x017d), top: B:174:0x001f, inners: #3, #4, #5, #7 }] */
        /* JADX WARN: Removed duplicated region for block: B:129:0x01c4 A[Catch: all -> 0x014a, TryCatch #6 {all -> 0x014a, blocks: (B:3:0x001f, B:4:0x0023, B:90:0x0143, B:95:0x014f, B:97:0x0157, B:98:0x015b, B:105:0x016b, B:106:0x016c, B:107:0x0170, B:114:0x0181, B:116:0x0184, B:118:0x0190, B:120:0x019a, B:123:0x01a8, B:125:0x01b2, B:127:0x01ba, B:129:0x01c4, B:131:0x01cd, B:132:0x01d1, B:136:0x01df, B:137:0x01ec, B:144:0x01fb, B:154:0x0222, B:139:0x01ee, B:140:0x01f7, B:100:0x015d, B:101:0x0166, B:5:0x0024, B:7:0x0028, B:18:0x003c, B:20:0x0044, B:88:0x0140, B:21:0x0051, B:23:0x0057, B:25:0x0062, B:27:0x0066, B:29:0x0072, B:31:0x007b, B:33:0x007f, B:35:0x0084, B:37:0x0088, B:42:0x009a, B:40:0x0094, B:43:0x009d, B:45:0x00a1, B:47:0x00a5, B:49:0x00a9, B:50:0x00ac, B:51:0x00b9, B:53:0x00bd, B:55:0x00c1, B:57:0x00cd, B:58:0x00db, B:60:0x00df, B:62:0x00e5, B:64:0x00eb, B:68:0x00f3, B:70:0x00f9, B:72:0x0105, B:73:0x010c, B:74:0x010d, B:76:0x0111, B:78:0x0115, B:79:0x011b, B:81:0x011f, B:83:0x0123, B:85:0x0132, B:152:0x0217, B:151:0x020c, B:109:0x0172, B:110:0x017d), top: B:174:0x001f, inners: #3, #4, #5, #7 }] */
        /* JADX WARN: Removed duplicated region for block: B:134:0x01db  */
        /* JADX WARN: Removed duplicated region for block: B:146:0x01ff  */
        /* JADX WARN: Removed duplicated region for block: B:149:0x0206  */
        /* JADX WARN: Removed duplicated region for block: B:163:0x0228 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:180:0x0147 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void guardedRun() {
            /*
                Method dump skipped, instruction units count: 563
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: miuix.mgl.android.MGLSurfaceView.GLThread.guardedRun():void");
        }

        private boolean readyToDraw() {
            return !this.mPaused && this.mHasSurface && !this.mSurfaceIsBad && this.mWidth > 0 && this.mHeight > 0 && (this.mRequestRender || this.mRenderMode == 1);
        }

        private void stopEglContextLocked() {
            if (this.mHaveEglContext) {
                this.mEglHelper.finish();
                this.mHaveEglContext = false;
                MGLSurfaceView.sGLThreadManager.releaseEglContextLocked(this);
            }
        }

        private void stopEglSurfaceLocked() {
            if (this.mHaveEglSurface) {
                this.mHaveEglSurface = false;
                this.mEglHelper.destroySurface();
            }
        }

        public boolean ableToDraw() {
            return this.mHaveEglContext && this.mHaveEglSurface && readyToDraw();
        }

        public int getRenderMode() {
            int i2;
            synchronized (MGLSurfaceView.sGLThreadManager) {
                i2 = this.mRenderMode;
            }
            return i2;
        }

        public void onPause() {
            synchronized (MGLSurfaceView.sGLThreadManager) {
                this.mRequestPaused = true;
                MGLSurfaceView.sGLThreadManager.notifyAll();
                while (!this.mExited && !this.mPaused) {
                    try {
                        MGLSurfaceView.sGLThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void onResume() {
            synchronized (MGLSurfaceView.sGLThreadManager) {
                this.mRequestPaused = false;
                this.mRequestRender = true;
                this.mRenderComplete = false;
                MGLSurfaceView.sGLThreadManager.notifyAll();
                while (!this.mExited && this.mPaused && !this.mRenderComplete) {
                    try {
                        MGLSurfaceView.sGLThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void onWindowResize(int i2, int i3) {
            synchronized (MGLSurfaceView.sGLThreadManager) {
                try {
                    this.mWidth = i2;
                    this.mHeight = i3;
                    this.mSizeChanged = true;
                    this.mRequestRender = true;
                    this.mRenderComplete = false;
                    if (Thread.currentThread() == this) {
                        return;
                    }
                    MGLSurfaceView.sGLThreadManager.notifyAll();
                    while (!this.mExited && !this.mPaused && !this.mRenderComplete && ableToDraw()) {
                        try {
                            MGLSurfaceView.sGLThreadManager.wait();
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public void queueEvent(Runnable runnable) {
            if (runnable == null) {
                throw new IllegalArgumentException("r must not be null");
            }
            synchronized (MGLSurfaceView.sGLThreadManager) {
                this.mEventQueue.add(runnable);
                MGLSurfaceView.sGLThreadManager.notifyAll();
            }
        }

        public void requestExitAndWait() {
            synchronized (MGLSurfaceView.sGLThreadManager) {
                this.mShouldExit = true;
                MGLSurfaceView.sGLThreadManager.notifyAll();
                while (!this.mExited) {
                    try {
                        MGLSurfaceView.sGLThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void requestReleaseEglContextLocked() {
            this.mShouldReleaseEglContext = true;
            MGLSurfaceView.sGLThreadManager.notifyAll();
        }

        public void requestRender() {
            synchronized (MGLSurfaceView.sGLThreadManager) {
                this.mRequestRender = true;
                MGLSurfaceView.sGLThreadManager.notifyAll();
            }
        }

        public void requestRenderAndNotify(Runnable runnable) {
            synchronized (MGLSurfaceView.sGLThreadManager) {
                try {
                    if (Thread.currentThread() == this) {
                        return;
                    }
                    this.mWantRenderNotification = true;
                    this.mRequestRender = true;
                    this.mRenderComplete = false;
                    this.mFinishDrawingRunnable = runnable;
                    MGLSurfaceView.sGLThreadManager.notifyAll();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setName("GLThread " + getId());
            try {
                guardedRun();
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                MGLSurfaceView.sGLThreadManager.threadExiting(this);
                throw th;
            }
            MGLSurfaceView.sGLThreadManager.threadExiting(this);
        }

        public void setRenderMode(int i2) {
            if (i2 < 0 || i2 > 1) {
                throw new IllegalArgumentException("renderMode");
            }
            synchronized (MGLSurfaceView.sGLThreadManager) {
                this.mRenderMode = i2;
                MGLSurfaceView.sGLThreadManager.notifyAll();
            }
        }

        public void surfaceCreated() {
            synchronized (MGLSurfaceView.sGLThreadManager) {
                this.mHasSurface = true;
                this.mFinishedCreatingEglSurface = false;
                MGLSurfaceView.sGLThreadManager.notifyAll();
                while (this.mWaitingForSurface && !this.mFinishedCreatingEglSurface && !this.mExited) {
                    try {
                        MGLSurfaceView.sGLThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void surfaceDestroyed() {
            synchronized (MGLSurfaceView.sGLThreadManager) {
                this.mHasSurface = false;
                MGLSurfaceView.sGLThreadManager.notifyAll();
                while (!this.mWaitingForSurface && !this.mExited) {
                    try {
                        MGLSurfaceView.sGLThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    public static class GLThreadManager {
        private static String TAG = "GLThreadManager";

        private GLThreadManager() {
        }

        public void releaseEglContextLocked(GLThread gLThread) {
            notifyAll();
        }

        public synchronized void threadExiting(GLThread gLThread) {
            gLThread.mExited = true;
            notifyAll();
        }
    }

    public interface GLWrapper {
        GL wrap(GL gl);
    }

    public static class LogWriter extends Writer {
        private StringBuilder mBuilder = new StringBuilder();

        private void flushBuilder() {
            if (this.mBuilder.length() > 0) {
                Log.v(MGLSurfaceView.TAG, this.mBuilder.toString());
                StringBuilder sb = this.mBuilder;
                sb.delete(0, sb.length());
            }
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            flushBuilder();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
            flushBuilder();
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i2, int i3) {
            for (int i4 = 0; i4 < i3; i4++) {
                char c2 = cArr[i2 + i4];
                if (c2 == '\n') {
                    flushBuilder();
                } else {
                    this.mBuilder.append(c2);
                }
            }
        }
    }

    public interface Renderer {
        void onDrawFrame(GL10 gl10);

        void onSurfaceChanged(GL10 gl10, int i2, int i3);

        void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig);
    }

    public class SimpleEGLConfigChooser extends ComponentSizeChooser {
        public SimpleEGLConfigChooser(boolean z2) {
            super(8, 8, 8, 0, z2 ? 16 : 0, 0);
        }
    }

    public MGLSurfaceView(Context context) {
        super(context);
        this.mThisWeakRef = new WeakReference<>(this);
        init();
    }

    private void checkRenderThreadState() {
        if (this.mGLThread != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    private void init() {
        getHolder().addCallback(this);
    }

    public void finalize() throws Throwable {
        try {
            GLThread gLThread = this.mGLThread;
            if (gLThread != null) {
                gLThread.requestExitAndWait();
            }
        } finally {
            super.finalize();
        }
    }

    public int getDebugFlags() {
        return this.mDebugFlags;
    }

    public boolean getPreserveEGLContextOnPause() {
        return this.mPreserveEGLContextOnPause;
    }

    public int getRenderMode() {
        return this.mGLThread.getRenderMode();
    }

    @Override // android.view.SurfaceView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mDetached && this.mRenderer != null) {
            GLThread gLThread = this.mGLThread;
            int renderMode = gLThread != null ? gLThread.getRenderMode() : 1;
            GLThread gLThread2 = new GLThread(this.mThisWeakRef);
            this.mGLThread = gLThread2;
            if (renderMode != 1) {
                gLThread2.setRenderMode(renderMode);
            }
            this.mGLThread.start();
        }
        this.mDetached = false;
    }

    @Override // android.view.SurfaceView, android.view.View
    public void onDetachedFromWindow() {
        GLThread gLThread = this.mGLThread;
        if (gLThread != null) {
            gLThread.requestExitAndWait();
        }
        this.mDetached = true;
        super.onDetachedFromWindow();
    }

    public void onPause() {
        this.mGLThread.onPause();
    }

    public void onResume() {
        this.mGLThread.onResume();
    }

    public void queueEvent(Runnable runnable) {
        this.mGLThread.queueEvent(runnable);
    }

    public void requestRender() {
        this.mGLThread.requestRender();
    }

    public void setColorSpace(int i2) {
        checkRenderThreadState();
        this.mColorSpace = i2;
    }

    public void setDebugFlags(int i2) {
        this.mDebugFlags = i2;
    }

    public void setEGLConfigChooser(EGLConfigChooser eGLConfigChooser) {
        checkRenderThreadState();
        this.mEGLConfigChooser = eGLConfigChooser;
    }

    public void setEGLContextClientVersion(int i2) {
        checkRenderThreadState();
        this.mEGLContextClientVersion = i2;
    }

    public void setEGLContextFactory(EGLContextFactory eGLContextFactory) {
        checkRenderThreadState();
        this.mEGLContextFactory = eGLContextFactory;
    }

    public void setEGLWindowSurfaceFactory(EGLWindowSurfaceFactory eGLWindowSurfaceFactory) {
        checkRenderThreadState();
        this.mEGLWindowSurfaceFactory = eGLWindowSurfaceFactory;
    }

    public void setGLWrapper(GLWrapper gLWrapper) {
        this.mGLWrapper = gLWrapper;
    }

    public void setPreserveEGLContextOnPause(boolean z2) {
        this.mPreserveEGLContextOnPause = z2;
    }

    public void setRenderMode(int i2) {
        this.mGLThread.setRenderMode(i2);
    }

    public void setRenderer(Renderer renderer) {
        checkRenderThreadState();
        if (this.mEGLConfigChooser == null) {
            this.mEGLConfigChooser = new SimpleEGLConfigChooser(true);
        }
        if (this.mEGLContextFactory == null) {
            this.mEGLContextFactory = new DefaultContextFactory();
        }
        if (this.mEGLWindowSurfaceFactory == null) {
            this.mEGLWindowSurfaceFactory = new DefaultWindowSurfaceFactory();
        }
        this.mRenderer = renderer;
        GLThread gLThread = new GLThread(this.mThisWeakRef);
        this.mGLThread = gLThread;
        gLThread.start();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        this.mGLThread.onWindowResize(i3, i4);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mGLThread.surfaceCreated();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mGLThread.surfaceDestroyed();
    }

    @Override // android.view.SurfaceHolder.Callback2
    @Deprecated
    public void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
    }

    @Override // android.view.SurfaceHolder.Callback2
    public void surfaceRedrawNeededAsync(SurfaceHolder surfaceHolder, Runnable runnable) {
        GLThread gLThread = this.mGLThread;
        if (gLThread != null) {
            gLThread.requestRenderAndNotify(runnable);
        }
    }

    public void setEGLConfigChooser(boolean z2) {
        setEGLConfigChooser(new SimpleEGLConfigChooser(z2));
    }

    public MGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mThisWeakRef = new WeakReference<>(this);
        init();
    }

    public void setEGLConfigChooser(int i2, int i3, int i4, int i5, int i6, int i7) {
        setEGLConfigChooser(new ComponentSizeChooser(i2, i3, i4, i5, i6, i7));
    }
}
