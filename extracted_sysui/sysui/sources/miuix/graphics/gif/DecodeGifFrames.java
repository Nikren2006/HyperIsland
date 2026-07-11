package miuix.graphics.gif;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import miuix.graphics.gif.DecodeGifImageHelper;
import miuix.io.ResettableInputStream;

/* JADX INFO: loaded from: classes3.dex */
class DecodeGifFrames extends Handler {
    private static final int MESSAGE_WHAT_START = 1;
    protected static final String TAG = "DecodeGifFrames";
    private Handler mCallerHandler;
    DecodeGifImageHelper.GifDecodeResult mDecodeResult;
    private ResettableInputStream mGifSource;
    HandlerThread mHandlerThread;
    private long mMaxDecodeSize;

    public DecodeGifFrames(HandlerThread handlerThread, ResettableInputStream resettableInputStream, long j2, Handler handler) {
        super(handlerThread.getLooper());
        this.mHandlerThread = handlerThread;
        this.mMaxDecodeSize = j2;
        this.mGifSource = resettableInputStream;
        this.mCallerHandler = handler;
    }

    public static DecodeGifFrames createInstance(ResettableInputStream resettableInputStream, long j2, Handler handler) {
        HandlerThread handlerThread = new HandlerThread("handler thread to decode GIF frames");
        handlerThread.start();
        return new DecodeGifFrames(handlerThread, resettableInputStream, j2, handler);
    }

    public void decode(int i2) {
        if (this.mDecodeResult != null) {
            return;
        }
        this.mDecodeResult = new DecodeGifImageHelper.GifDecodeResult();
        sendMessage(obtainMessage(1, i2, 0));
    }

    public void destroy() {
        this.mHandlerThread.quit();
    }

    public void finalize() throws Throwable {
        this.mHandlerThread.quit();
        super.finalize();
    }

    public DecodeGifImageHelper.GifDecodeResult getAndClearDecodeResult() {
        DecodeGifImageHelper.GifDecodeResult gifDecodeResult = this.mDecodeResult;
        this.mDecodeResult = null;
        return gifDecodeResult;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what == 1) {
            DecodeGifImageHelper.GifDecodeResult gifDecodeResultDecode = DecodeGifImageHelper.decode(this.mGifSource, this.mMaxDecodeSize, message.arg1);
            DecodeGifImageHelper.GifDecodeResult gifDecodeResult = this.mDecodeResult;
            gifDecodeResult.mGifDecoder = gifDecodeResultDecode.mGifDecoder;
            gifDecodeResult.mIsDecodeOk = gifDecodeResultDecode.mIsDecodeOk;
            this.mCallerHandler.sendEmptyMessage(1);
        }
    }
}
