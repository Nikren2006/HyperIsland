package miui.systemui.widget;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.opengl.GLES20;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.MediaController;
import com.xiaomi.onetrack.util.aa;
import java.io.IOException;
import java.util.Map;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

/* JADX INFO: loaded from: classes4.dex */
public class TextureVideoView extends TextureView implements MediaController.MediaPlayerControl {
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PREPARING = 1;
    private static final String TAG = "TextureVideoView";
    private int mAudioSession;
    private final MediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener;
    private boolean mCanPause;
    private boolean mCanSeekBack;
    private boolean mCanSeekForward;
    private final MediaPlayer.OnCompletionListener mCompletionListener;
    private int mCurrentBufferPercentage;
    private int mCurrentState;
    private final MediaPlayer.OnErrorListener mErrorListener;
    private Map<String, String> mHeaders;
    private final MediaPlayer.OnInfoListener mInfoListener;
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;
    private MediaPlayer.OnErrorListener mOnErrorListener;
    private MediaPlayer.OnInfoListener mOnInfoListener;
    private MediaPlayer.OnPreparedListener mOnPreparedListener;
    final MediaPlayer.OnPreparedListener mPreparedListener;
    private int mSeekWhenPrepared;
    final MediaPlayer.OnVideoSizeChangedListener mSizeChangedListener;
    private Surface mSurface;
    final TextureView.SurfaceTextureListener mSurfaceTextureListener;
    private int mTargetState;
    private Uri mUri;
    private int mVideoHeight;
    private int mVideoWidth;

    public TextureVideoView(Context context) {
        this(context, null);
    }

    private void clearSurface() {
        if (this.mSurface == null) {
            return;
        }
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        egl10.eglInitialize(eGLDisplayEglGetDisplay, null);
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        egl10.eglChooseConfig(eGLDisplayEglGetDisplay, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, 12344, 0, 12344}, eGLConfigArr, 1, new int[1]);
        EGLConfig eGLConfig = eGLConfigArr[0];
        EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
        EGLContext eGLContextEglCreateContext = egl10.eglCreateContext(eGLDisplayEglGetDisplay, eGLConfig, eGLContext, new int[]{12440, 2, 12344});
        EGLSurface eGLSurfaceEglCreateWindowSurface = egl10.eglCreateWindowSurface(eGLDisplayEglGetDisplay, eGLConfig, this.mSurface, new int[]{12344});
        egl10.eglMakeCurrent(eGLDisplayEglGetDisplay, eGLSurfaceEglCreateWindowSurface, eGLSurfaceEglCreateWindowSurface, eGLContextEglCreateContext);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(16384);
        egl10.eglSwapBuffers(eGLDisplayEglGetDisplay, eGLSurfaceEglCreateWindowSurface);
        egl10.eglDestroySurface(eGLDisplayEglGetDisplay, eGLSurfaceEglCreateWindowSurface);
        EGLSurface eGLSurface = EGL10.EGL_NO_SURFACE;
        egl10.eglMakeCurrent(eGLDisplayEglGetDisplay, eGLSurface, eGLSurface, eGLContext);
        egl10.eglDestroyContext(eGLDisplayEglGetDisplay, eGLContextEglCreateContext);
        egl10.eglTerminate(eGLDisplayEglGetDisplay);
    }

    private boolean isInPlaybackState() {
        int i2;
        return (this.mMediaPlayer == null || (i2 = this.mCurrentState) == -1 || i2 == 0 || i2 == 1) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$0(MediaPlayer mediaPlayer, int i2, int i3) {
        Log.i(TAG, "onInfo, what=" + i2 + ", extra=" + i3);
        MediaPlayer.OnInfoListener onInfoListener = this.mOnInfoListener;
        if (onInfoListener == null) {
            return true;
        }
        onInfoListener.onInfo(mediaPlayer, i2, i3);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openVideo() {
        if (this.mUri == null || this.mSurface == null) {
            return;
        }
        release(false);
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mMediaPlayer = mediaPlayer;
            int i2 = this.mAudioSession;
            if (i2 != 0) {
                mediaPlayer.setAudioSessionId(i2);
            } else {
                this.mAudioSession = mediaPlayer.getAudioSessionId();
            }
            this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
            this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
            this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
            this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
            this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
            this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
            this.mCurrentBufferPercentage = 0;
            this.mMediaPlayer.setDataSource(getContext(), this.mUri, this.mHeaders);
            this.mMediaPlayer.setSurface(this.mSurface);
            this.mMediaPlayer.setScreenOnWhilePlaying(true);
            this.mMediaPlayer.prepareAsync();
            this.mCurrentState = 1;
        } catch (IOException | IllegalArgumentException | IllegalStateException e2) {
            Log.w(TAG, "Unable to open content: " + this.mUri, e2);
            this.mCurrentState = -1;
            this.mTargetState = -1;
            this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release(boolean z2) {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            if (z2) {
                this.mTargetState = 0;
            }
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return this.mCanPause;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return this.mCanSeekBack;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return this.mCanSeekForward;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        if (this.mAudioSession == 0) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mAudioSession = mediaPlayer.getAudioSessionId();
            mediaPlayer.release();
        }
        return this.mAudioSession;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.mCurrentBufferPercentage;
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getDuration();
        }
        return -1;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(TextureVideoView.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(TextureVideoView.class.getName());
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        int i4;
        int defaultSize = TextureView.getDefaultSize(this.mVideoWidth, i2);
        int defaultSize2 = TextureView.getDefaultSize(this.mVideoHeight, i3);
        if (this.mVideoWidth > 0 && this.mVideoHeight > 0) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            int size2 = View.MeasureSpec.getSize(i3);
            if (mode == 1073741824 && mode2 == 1073741824) {
                int i5 = this.mVideoWidth;
                int i6 = i5 * size2;
                int i7 = this.mVideoHeight;
                if (i6 < size * i7) {
                    defaultSize = (i5 * size2) / i7;
                    defaultSize2 = size2;
                } else {
                    if (i5 * size2 > size * i7) {
                        defaultSize2 = (i7 * size) / i5;
                        defaultSize = size;
                    }
                    defaultSize = size;
                    defaultSize2 = size2;
                }
            } else if (mode == 1073741824) {
                int i8 = (this.mVideoHeight * size) / this.mVideoWidth;
                if (mode2 != Integer.MIN_VALUE || i8 <= size2) {
                    defaultSize2 = i8;
                    defaultSize = size;
                }
                defaultSize = size;
                defaultSize2 = size2;
            } else {
                if (mode2 == 1073741824) {
                    i4 = (this.mVideoWidth * size2) / this.mVideoHeight;
                    if (mode == Integer.MIN_VALUE && i4 > size) {
                        defaultSize = size;
                    }
                    defaultSize2 = size2;
                } else {
                    int i9 = this.mVideoWidth;
                    int i10 = this.mVideoHeight;
                    if (mode2 != Integer.MIN_VALUE || i10 <= size2) {
                        i4 = i9;
                        size2 = i10;
                    } else {
                        i4 = (size2 * i9) / i10;
                    }
                    if (mode == Integer.MIN_VALUE && i4 > size) {
                        defaultSize2 = (i10 * size) / i9;
                        defaultSize = size;
                    }
                }
                defaultSize = i4;
                defaultSize2 = size2;
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentState = 4;
        }
        this.mTargetState = 4;
    }

    public void resume() {
        openVideo();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i2) {
        if (!isInPlaybackState()) {
            this.mSeekWhenPrepared = i2;
        } else {
            this.mMediaPlayer.seekTo(i2);
            this.mSeekWhenPrepared = 0;
        }
    }

    public void setAudioFocusRequest(int i2) {
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    public void setVideoURI(Uri uri) {
        Log.i(TAG, "setVideoURI " + uri);
        setVideoURI(uri, null);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void start() {
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
        }
        this.mTargetState = 3;
    }

    public void stopPlayback() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            this.mTargetState = 0;
        }
        clearSurface();
    }

    public void suspend() {
        release(false);
    }

    public TextureVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextureVideoView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mSurface = null;
        this.mMediaPlayer = null;
        this.mSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: miui.systemui.widget.TextureVideoView.1
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i3, int i4) {
                TextureVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                TextureVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                if (TextureVideoView.this.mVideoWidth == 0 || TextureVideoView.this.mVideoHeight == 0) {
                    return;
                }
                TextureVideoView.this.getSurfaceTexture().setDefaultBufferSize(TextureVideoView.this.mVideoWidth, TextureVideoView.this.mVideoHeight);
                TextureVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: miui.systemui.widget.TextureVideoView.2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                TextureVideoView.this.mCurrentState = 2;
                TextureVideoView textureVideoView = TextureVideoView.this;
                textureVideoView.mCanSeekForward = true;
                textureVideoView.mCanSeekBack = true;
                textureVideoView.mCanPause = true;
                if (TextureVideoView.this.mOnPreparedListener != null) {
                    TextureVideoView.this.mOnPreparedListener.onPrepared(TextureVideoView.this.mMediaPlayer);
                }
                TextureVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                TextureVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                int i3 = TextureVideoView.this.mSeekWhenPrepared;
                if (i3 != 0) {
                    TextureVideoView.this.seekTo(i3);
                }
                if (TextureVideoView.this.mVideoWidth != 0 && TextureVideoView.this.mVideoHeight != 0) {
                    TextureVideoView.this.getSurfaceTexture().setDefaultBufferSize(TextureVideoView.this.mVideoWidth, TextureVideoView.this.mVideoHeight);
                }
                if (TextureVideoView.this.mTargetState == 3) {
                    TextureVideoView.this.start();
                }
            }
        };
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: miui.systemui.widget.TextureVideoView.3
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                TextureVideoView.this.mCurrentState = 5;
                TextureVideoView.this.mTargetState = 5;
                if (TextureVideoView.this.mOnCompletionListener != null) {
                    TextureVideoView.this.mOnCompletionListener.onCompletion(TextureVideoView.this.mMediaPlayer);
                }
            }
        };
        this.mInfoListener = new MediaPlayer.OnInfoListener() { // from class: miui.systemui.widget.g
            @Override // android.media.MediaPlayer.OnInfoListener
            public final boolean onInfo(MediaPlayer mediaPlayer, int i3, int i4) {
                return this.f5931a.lambda$new$0(mediaPlayer, i3, i4);
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() { // from class: miui.systemui.widget.TextureVideoView.4
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i3, int i4) {
                Log.w(TextureVideoView.TAG, "Error: " + i3 + aa.f3429b + i4);
                TextureVideoView.this.mCurrentState = -1;
                TextureVideoView.this.mTargetState = -1;
                if (TextureVideoView.this.mOnErrorListener != null) {
                    TextureVideoView.this.mOnErrorListener.onError(TextureVideoView.this.mMediaPlayer, i3, i4);
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: miui.systemui.widget.TextureVideoView.5
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i3) {
                TextureVideoView.this.mCurrentBufferPercentage = i3;
            }
        };
        TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() { // from class: miui.systemui.widget.TextureVideoView.6
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i3, int i4) {
                TextureVideoView.this.mSurface = new Surface(surfaceTexture);
                TextureVideoView.this.openVideo();
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                if (TextureVideoView.this.mSurface != null) {
                    TextureVideoView.this.mSurface.release();
                    TextureVideoView.this.mSurface = null;
                }
                TextureVideoView.this.release(true);
                return true;
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i3, int i4) {
                boolean z2 = false;
                boolean z3 = TextureVideoView.this.mTargetState == 3;
                if (i3 > 0 && i4 > 0) {
                    z2 = true;
                }
                if (TextureVideoView.this.mMediaPlayer != null && z3 && z2) {
                    if (TextureVideoView.this.mSeekWhenPrepared != 0) {
                        TextureVideoView textureVideoView = TextureVideoView.this;
                        textureVideoView.seekTo(textureVideoView.mSeekWhenPrepared);
                    }
                    TextureVideoView.this.start();
                }
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }
        };
        this.mSurfaceTextureListener = surfaceTextureListener;
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        setSurfaceTextureListener(surfaceTextureListener);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.mCurrentState = 0;
        this.mTargetState = 0;
    }

    public void setVideoURI(Uri uri, Map<String, String> map) {
        this.mUri = uri;
        this.mHeaders = map;
        this.mSeekWhenPrepared = 0;
        openVideo();
        requestLayout();
        invalidate();
    }
}
