package miuix.graphics.gif;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import miuix.io.ResettableInputStream;

/* JADX INFO: loaded from: classes3.dex */
public class DecodeGifImageHelper {
    public static final int MESSAGE_WHAT_DECODE_FRAMES = 1;
    public Handler mDecodeFrameHandler;
    private DecodeGifFrames mDecodeGifFrames;
    public boolean mDecodedAllFrames;
    public ResettableInputStream mGifSource;
    private int mMaxFrames;
    public int mRealFrameCount;
    public long mMaxDecodeSize = 1048576;
    public List<GifFrame> mFrames = new ArrayList();

    public static class GifDecodeResult {
        public GifDecoder mGifDecoder;
        public boolean mIsDecodeOk;
    }

    public static class GifFrame {
        public int mDuration;
        public Bitmap mImage;
        public int mIndex;

        public GifFrame(Bitmap bitmap, int i2, int i3) {
            this.mImage = bitmap;
            this.mDuration = i2;
            this.mIndex = i3;
        }
    }

    private int calcFrameIndex(int i2) {
        int i3 = this.mRealFrameCount;
        return i3 == 0 ? i2 : i2 % i3;
    }

    public static GifDecodeResult decode(ResettableInputStream resettableInputStream, long j2, int i2) {
        GifDecodeResult gifDecodeResult = new GifDecodeResult();
        gifDecodeResult.mGifDecoder = null;
        gifDecodeResult.mIsDecodeOk = false;
        try {
            resettableInputStream.reset();
            GifDecoder gifDecoder = new GifDecoder();
            gifDecodeResult.mGifDecoder = gifDecoder;
            gifDecoder.setStartFrame(i2);
            gifDecoder.setMaxDecodeSize(j2);
            gifDecodeResult.mIsDecodeOk = gifDecoder.read(resettableInputStream) == 0;
            resettableInputStream.close();
        } catch (IOException unused) {
        }
        return gifDecodeResult;
    }

    private int getLastFrameIndex() {
        return this.mFrames.get(r1.size() - 1).mIndex;
    }

    public GifDecodeResult decodeFrom(int i2) {
        return decode(this.mGifSource, this.mMaxDecodeSize, i2);
    }

    public void decodeNextFrames() {
        int size = this.mFrames.size();
        int i2 = this.mMaxFrames;
        if (i2 <= 3) {
            if (size > 2) {
                return;
            }
        } else if (size > i2 / 2) {
            return;
        }
        this.mDecodeGifFrames.decode(calcFrameIndex(getLastFrameIndex() + 1));
    }

    public void destroy() {
        DecodeGifFrames decodeGifFrames = this.mDecodeGifFrames;
        if (decodeGifFrames != null) {
            decodeGifFrames.destroy();
        }
    }

    public void firstDecodeNextFrames() {
        Handler handler = new Handler(Looper.getMainLooper()) { // from class: miuix.graphics.gif.DecodeGifImageHelper.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                DecodeGifImageHelper decodeGifImageHelper = DecodeGifImageHelper.this;
                if (decodeGifImageHelper.handleDecodeFramesResult(decodeGifImageHelper.mDecodeGifFrames.getAndClearDecodeResult())) {
                    DecodeGifImageHelper.this.decodeNextFrames();
                }
            }
        };
        this.mDecodeFrameHandler = handler;
        this.mDecodeGifFrames = DecodeGifFrames.createInstance(this.mGifSource, this.mMaxDecodeSize, handler);
        this.mMaxFrames = this.mFrames.size();
        decodeNextFrames();
    }

    public boolean handleDecodeFramesResult(GifDecodeResult gifDecodeResult) {
        GifDecoder gifDecoder;
        if (!gifDecodeResult.mIsDecodeOk || (gifDecoder = gifDecodeResult.mGifDecoder) == null) {
            return false;
        }
        Log.d("dumpFrameIndex", String.format("Thread#%d: decoded %d frames [%s] [%d]", Long.valueOf(Thread.currentThread().getId()), Integer.valueOf(gifDecodeResult.mGifDecoder.getFrameCount()), Boolean.valueOf(gifDecodeResult.mIsDecodeOk), Integer.valueOf(this.mRealFrameCount)));
        if (gifDecoder.isDecodeToTheEnd()) {
            this.mRealFrameCount = gifDecoder.getRealFrameCount();
        }
        int frameCount = gifDecoder.getFrameCount();
        if (frameCount > 0) {
            int lastFrameIndex = getLastFrameIndex();
            for (int i2 = 0; i2 < frameCount; i2++) {
                this.mFrames.add(new GifFrame(gifDecoder.getFrame(i2), gifDecoder.getDelay(i2), calcFrameIndex(lastFrameIndex + 1 + i2)));
            }
        }
        return true;
    }
}
