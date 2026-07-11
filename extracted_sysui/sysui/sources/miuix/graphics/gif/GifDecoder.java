package miuix.graphics.gif;

import android.graphics.Bitmap;
import androidx.core.view.ViewCompat;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Vector;
import miuix.animation.internal.TransitionInfo;

/* JADX INFO: loaded from: classes3.dex */
public class GifDecoder {
    public static final int MAX_DECODE_SIZE = 1048576;
    protected static final int MAX_STACK_SIZE = 4096;
    public static final int STATUS_DECODE_CANCEL = 3;
    public static final int STATUS_FORMAT_ERROR = 1;
    public static final int STATUS_OK = 0;
    public static final int STATUS_OPEN_ERROR = 2;
    protected int[] act;
    protected int bgColor;
    protected int bgIndex;
    private int[] dest;
    protected Vector<GifFrame> frames;
    protected int[] gct;
    protected boolean gctFlag;
    protected int gctSize;
    private int height;
    protected int ih;
    protected Bitmap image;
    protected BufferedInputStream in;
    protected boolean interlace;
    protected int iw;
    protected int ix;
    protected int iy;
    protected int lastBgColor;
    protected Bitmap lastBitmap;
    protected int[] lct;
    protected boolean lctFlag;
    protected int lctSize;
    protected int lrh;
    protected int lrw;
    protected int lrx;
    protected int lry;
    private long mDecodeBmSize;
    private boolean mDecodeToTheEnd;
    private int mDecodedFrames;
    private int mStartFrame;
    protected int pixelAspect;
    protected byte[] pixelStack;
    protected byte[] pixels;
    protected short[] prefix;
    protected int status;
    protected byte[] suffix;
    protected int transIndex;
    private int width;
    private long mMaxDecodeSize = 1048576;
    protected int loopCount = 1;
    protected byte[] block = new byte[256];
    protected int blockSize = 0;
    protected int dispose = 0;
    protected int lastDispose = 0;
    protected boolean transparency = false;
    protected int delay = 0;
    private boolean mCancel = false;
    private boolean calledOnce = false;

    public static class GifFrame {
        public int delay;
        public Bitmap image;

        public GifFrame(Bitmap bitmap, int i2) {
            this.image = bitmap;
            this.delay = i2;
        }

        public void recycle() {
            Bitmap bitmap = this.image;
            if (bitmap == null || bitmap.isRecycled()) {
                return;
            }
            this.image.recycle();
        }
    }

    public static boolean isGifStream(InputStream inputStream) {
        int oneByte;
        if (inputStream == null) {
            return false;
        }
        String str = "";
        for (int i2 = 0; i2 < 6 && (oneByte = readOneByte(inputStream)) != -1; i2++) {
            str = str + ((char) oneByte);
        }
        return str.startsWith("GIF");
    }

    public static int readOneByte(InputStream inputStream) {
        try {
            return inputStream.read();
        } catch (Exception unused) {
            return -1;
        }
    }

    private void requestCancel() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v25, types: [short] */
    /* JADX WARN: Type inference failed for: r2v27 */
    public void decodeBitmapData() {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        short s2;
        int i7 = this.iw * this.ih;
        byte[] bArr = this.pixels;
        if (bArr == null || bArr.length < i7) {
            this.pixels = new byte[i7];
        }
        if (this.prefix == null) {
            this.prefix = new short[4096];
        }
        if (this.suffix == null) {
            this.suffix = new byte[4096];
        }
        if (this.pixelStack == null) {
            this.pixelStack = new byte[4097];
        }
        int i8 = read();
        int i9 = 1 << i8;
        int i10 = i9 + 1;
        int i11 = i9 + 2;
        int i12 = i8 + 1;
        int i13 = (1 << i12) - 1;
        for (int i14 = 0; i14 < i9; i14++) {
            this.prefix[i14] = 0;
            this.suffix[i14] = (byte) i14;
        }
        int i15 = i12;
        int i16 = i13;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int block = 0;
        int i21 = 0;
        int i22 = 0;
        int i23 = 0;
        int i24 = -1;
        int i25 = i11;
        while (i17 < i7) {
            if (i18 != 0) {
                i2 = i12;
                i3 = i10;
                int i26 = i22;
                i4 = i9;
                i5 = i26;
            } else if (i19 < i15) {
                if (block == 0) {
                    block = readBlock();
                    if (block <= 0) {
                        break;
                    } else {
                        i21 = 0;
                    }
                }
                i20 += (this.block[i21] & TransitionInfo.INIT) << i19;
                i19 += 8;
                i21++;
                block--;
            } else {
                int i27 = i20 & i16;
                i20 >>= i15;
                i19 -= i15;
                if (i27 > i25 || i27 == i10) {
                    break;
                }
                if (i27 == i9) {
                    i15 = i12;
                    i25 = i11;
                    i16 = i13;
                    i24 = -1;
                } else if (i24 == -1) {
                    this.pixelStack[i18] = this.suffix[i27];
                    i24 = i27;
                    i22 = i24;
                    i18++;
                    i12 = i12;
                } else {
                    i2 = i12;
                    if (i27 == i25) {
                        i6 = i27;
                        this.pixelStack[i18] = (byte) i22;
                        s2 = i24;
                        i18++;
                    } else {
                        i6 = i27;
                        s2 = i6;
                    }
                    while (s2 > i9) {
                        this.pixelStack[i18] = this.suffix[s2];
                        s2 = this.prefix[s2];
                        i18++;
                        i9 = i9;
                    }
                    i4 = i9;
                    byte[] bArr2 = this.suffix;
                    i5 = bArr2[s2] & TransitionInfo.INIT;
                    if (i25 >= 4096) {
                        break;
                    }
                    int i28 = i18 + 1;
                    i3 = i10;
                    byte b2 = (byte) i5;
                    this.pixelStack[i18] = b2;
                    this.prefix[i25] = (short) i24;
                    bArr2[i25] = b2;
                    i25++;
                    if ((i25 & i16) == 0 && i25 < 4096) {
                        i15++;
                        i16 += i25;
                    }
                    i18 = i28;
                    i24 = i6;
                }
            }
            i18--;
            this.pixels[i23] = this.pixelStack[i18];
            i17++;
            i23++;
            i9 = i4;
            i10 = i3;
            i22 = i5;
            i12 = i2;
        }
        for (int i29 = i23; i29 < i7; i29++) {
            this.pixels[i29] = 0;
        }
    }

    public boolean err() {
        return this.status != 0;
    }

    public Bitmap getBitmap() {
        return getFrame(0);
    }

    public int getDelay(int i2) {
        this.delay = -1;
        int frameCount = getFrameCount();
        if (i2 >= 0 && i2 < frameCount) {
            this.delay = this.frames.elementAt(i2).delay;
        }
        return this.delay;
    }

    public Bitmap getFrame(int i2) {
        int frameCount = getFrameCount();
        if (frameCount <= 0) {
            return null;
        }
        return this.frames.elementAt(i2 % frameCount).image;
    }

    public int getFrameCount() {
        Vector<GifFrame> vector = this.frames;
        if (vector == null) {
            return 0;
        }
        return vector.size();
    }

    public int getHeight() {
        return this.height;
    }

    public int getLoopCount() {
        return this.loopCount;
    }

    public int getRealFrameCount() {
        if (this.mDecodeToTheEnd) {
            return this.mDecodedFrames;
        }
        return 0;
    }

    public int getWidth() {
        return this.width;
    }

    public void init() {
        this.status = 0;
        this.frames = new Vector<>();
        this.gct = null;
        this.lct = null;
    }

    public boolean isDecodeToTheEnd() {
        return this.mDecodeToTheEnd;
    }

    public int read(InputStream inputStream) {
        this.mDecodeToTheEnd = false;
        if (this.calledOnce) {
            throw new IllegalStateException("decoder cannot be called more than once");
        }
        this.calledOnce = true;
        init();
        if (inputStream != null) {
            this.in = new BufferedInputStream(inputStream);
            try {
                readHeader();
                if (!this.mCancel && !err()) {
                    readContents();
                    if (getFrameCount() < 0) {
                        this.status = 1;
                    }
                }
            } catch (OutOfMemoryError unused) {
                this.status = 2;
                recycle();
            }
        } else {
            this.status = 2;
        }
        if (this.mCancel) {
            recycle();
            this.status = 3;
        }
        return this.status;
    }

    public void readBitmap() {
        this.ix = readShort();
        this.iy = readShort();
        this.iw = readShort();
        this.ih = readShort();
        int i2 = read();
        int i3 = 0;
        boolean z2 = (i2 & 128) != 0;
        this.lctFlag = z2;
        int i4 = 2 << (i2 & 7);
        this.lctSize = i4;
        this.interlace = (i2 & 64) != 0;
        if (z2) {
            int[] colorTable = readColorTable(i4);
            this.lct = colorTable;
            this.act = colorTable;
        } else {
            this.act = this.gct;
            if (this.bgIndex == this.transIndex) {
                this.bgColor = 0;
            }
        }
        if (this.transparency) {
            int[] iArr = this.act;
            int i5 = this.transIndex;
            int i6 = iArr[i5];
            iArr[i5] = 0;
            i3 = i6;
        }
        if (this.act == null) {
            this.status = 1;
        }
        if (err()) {
            return;
        }
        decodeBitmapData();
        skip();
        if (err() || this.mCancel) {
            return;
        }
        setPixels();
        if (this.mDecodedFrames >= this.mStartFrame) {
            this.frames.addElement(new GifFrame(this.image, this.delay));
        }
        this.mDecodedFrames++;
        if (this.transparency) {
            this.act[this.transIndex] = i3;
        }
        resetFrame();
    }

    public int readBlock() {
        int i2 = read();
        this.blockSize = i2;
        int i3 = 0;
        if (i2 > 0) {
            while (true) {
                try {
                    int i4 = this.blockSize;
                    if (i3 >= i4) {
                        break;
                    }
                    int i5 = this.in.read(this.block, i3, i4 - i3);
                    if (i5 == -1) {
                        break;
                    }
                    i3 += i5;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (i3 < this.blockSize) {
                this.status = 1;
            }
        }
        return i3;
    }

    public int[] readColorTable(int i2) {
        int i3;
        int i4 = i2 * 3;
        byte[] bArr = new byte[i4];
        try {
            i3 = this.in.read(bArr, 0, i4);
        } catch (Exception e2) {
            e2.printStackTrace();
            i3 = 0;
        }
        if (i3 < i4) {
            this.status = 1;
            return null;
        }
        int[] iArr = new int[256];
        int i5 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = bArr[i5] & TransitionInfo.INIT;
            int i8 = i5 + 2;
            int i9 = bArr[i5 + 1] & TransitionInfo.INIT;
            i5 += 3;
            iArr[i6] = (i9 << 8) | (i7 << 16) | ViewCompat.MEASURED_STATE_MASK | (bArr[i8] & TransitionInfo.INIT);
        }
        return iArr;
    }

    public void readContents() {
        this.mDecodedFrames = 0;
        boolean z2 = false;
        while (!z2 && !err() && !this.mCancel) {
            int i2 = read();
            if (i2 == 33) {
                int i3 = read();
                if (i3 == 1) {
                    skip();
                } else if (i3 == 249) {
                    readGraphicControlExt();
                } else if (i3 == 254) {
                    skip();
                } else if (i3 != 255) {
                    skip();
                } else {
                    readBlock();
                    String str = "";
                    for (int i4 = 0; i4 < 11; i4++) {
                        str = str + ((char) this.block[i4]);
                    }
                    if (str.equals("NETSCAPE2.0")) {
                        readNetscapeExt();
                    } else {
                        skip();
                    }
                }
            } else if (i2 == 44) {
                int size = this.frames.size();
                readBitmap();
                if (this.frames.size() > size) {
                    this.mDecodeBmSize += (long) (this.image.getRowBytes() * this.image.getHeight());
                }
                if (this.mDecodeBmSize > this.mMaxDecodeSize) {
                    z2 = true;
                }
            } else if (i2 != 59) {
                this.status = 1;
            } else {
                this.mDecodeToTheEnd = true;
                z2 = true;
            }
        }
    }

    public void readGraphicControlExt() {
        read();
        int i2 = read();
        int i3 = (i2 & 28) >> 2;
        this.dispose = i3;
        if (i3 == 0) {
            this.dispose = 1;
        }
        this.transparency = (i2 & 1) != 0;
        int i4 = readShort() * 10;
        this.delay = i4;
        if (i4 <= 0) {
            this.delay = 100;
        }
        this.transIndex = read();
        read();
    }

    public void readHeader() {
        if (this.mCancel) {
            return;
        }
        String str = "";
        for (int i2 = 0; i2 < 6; i2++) {
            str = str + ((char) read());
        }
        if (!str.startsWith("GIF")) {
            this.status = 1;
            return;
        }
        readLSD();
        if (!this.gctFlag || err()) {
            return;
        }
        int[] colorTable = readColorTable(this.gctSize);
        this.gct = colorTable;
        this.bgColor = colorTable[this.bgIndex];
    }

    public void readLSD() {
        this.width = readShort();
        this.height = readShort();
        int i2 = read();
        this.gctFlag = (i2 & 128) != 0;
        this.gctSize = 2 << (i2 & 7);
        this.bgIndex = read();
        this.pixelAspect = read();
    }

    public void readNetscapeExt() {
        do {
            readBlock();
            byte[] bArr = this.block;
            if (bArr[0] == 1) {
                this.loopCount = ((bArr[2] & TransitionInfo.INIT) << 8) | (bArr[1] & TransitionInfo.INIT);
            }
            if (this.blockSize <= 0) {
                return;
            }
        } while (!err());
    }

    public int readShort() {
        return (read() << 8) | read();
    }

    public void recycle() {
        Vector<GifFrame> vector = this.frames;
        if (vector != null) {
            int size = vector.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.frames.elementAt(i2).recycle();
            }
        }
    }

    public void requestCancelDecode() {
        this.mCancel = true;
        requestCancel();
    }

    public void resetFrame() {
        this.lastDispose = this.dispose;
        this.lrx = this.ix;
        this.lry = this.iy;
        this.lrw = this.iw;
        this.lrh = this.ih;
        this.lastBitmap = this.image;
        this.lastBgColor = this.bgColor;
        this.dispose = 0;
        this.transparency = false;
        this.delay = 0;
        this.lct = null;
    }

    public void setMaxDecodeSize(long j2) {
        this.mMaxDecodeSize = j2;
    }

    public void setPixels() {
        Bitmap bitmap;
        int i2;
        if (this.dest == null) {
            this.dest = new int[this.width * this.height];
        }
        int i3 = this.lastDispose;
        int i4 = 0;
        if (i3 > 0) {
            if (i3 == 3) {
                int frameCount = getFrameCount();
                if (frameCount - 2 > 0) {
                    Bitmap frame = getFrame(frameCount - 3);
                    if (!frame.equals(this.lastBitmap)) {
                        this.lastBitmap = frame;
                        int[] iArr = this.dest;
                        int i5 = this.width;
                        frame.getPixels(iArr, 0, i5, 0, 0, i5, this.height);
                    }
                } else {
                    this.lastBitmap = null;
                    this.dest = new int[this.width * this.height];
                }
            }
            if (this.lastBitmap != null && this.lastDispose == 2) {
                int i6 = !this.transparency ? this.lastBgColor : 0;
                int i7 = (this.lry * this.width) + this.lrx;
                for (int i8 = 0; i8 < this.lrh; i8++) {
                    int i9 = this.lrw + i7;
                    for (int i10 = i7; i10 < i9; i10++) {
                        this.dest[i10] = i6;
                    }
                    i7 += this.width;
                }
            }
        }
        int i11 = 1;
        int i12 = 8;
        int i13 = 0;
        while (true) {
            int i14 = this.ih;
            if (i4 >= i14) {
                break;
            }
            if (this.interlace) {
                if (i13 >= i14) {
                    i11++;
                    if (i11 == 2) {
                        i13 = 4;
                    } else if (i11 == 3) {
                        i13 = 2;
                        i12 = 4;
                    } else if (i11 == 4) {
                        i13 = 1;
                        i12 = 2;
                    }
                }
                i2 = i13 + i12;
            } else {
                i2 = i13;
                i13 = i4;
            }
            int i15 = i13 + this.iy;
            if (i15 < this.height) {
                int i16 = this.width;
                int i17 = i15 * i16;
                int i18 = this.ix + i17;
                int i19 = this.iw;
                int i20 = i18 + i19;
                if (i17 + i16 < i20) {
                    i20 = i17 + i16;
                }
                int i21 = i19 * i4;
                while (i18 < i20) {
                    int i22 = i21 + 1;
                    int i23 = this.act[this.pixels[i21] & TransitionInfo.INIT];
                    if (i23 != 0) {
                        this.dest[i18] = i23;
                    }
                    i18++;
                    i21 = i22;
                }
            }
            i4++;
            i13 = i2;
        }
        if (this.mDecodedFrames <= this.mStartFrame && (bitmap = this.image) != null && !bitmap.isRecycled()) {
            this.image.recycle();
        }
        this.image = Bitmap.createBitmap(this.dest, this.width, this.height, Bitmap.Config.ARGB_8888);
    }

    public void setStartFrame(int i2) {
        this.mStartFrame = i2;
    }

    public void skip() {
        do {
            readBlock();
            if (this.blockSize <= 0) {
                return;
            }
        } while (!err());
    }

    public int read() {
        try {
            return this.in.read();
        } catch (Exception unused) {
            this.status = 1;
            return 0;
        }
    }
}
