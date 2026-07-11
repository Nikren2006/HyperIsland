package D1;

import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import miuix.animation.internal.TransitionInfo;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements c, b, Cloneable, ByteChannel {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final byte[] f76c = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public i f77a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f78b;

    /* JADX INFO: renamed from: D1.a$a, reason: collision with other inner class name */
    public class C0004a extends InputStream {
        public C0004a() {
        }

        @Override // java.io.InputStream
        public int available() {
            return (int) Math.min(a.this.f78b, 2147483647L);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.InputStream
        public int read() {
            a aVar = a.this;
            if (aVar.f78b > 0) {
                return aVar.readByte() & TransitionInfo.INIT;
            }
            return -1;
        }

        public String toString() {
            return a.this + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) {
            return a.this.read(bArr, i2, i3);
        }
    }

    public String A(long j2, Charset charset) {
        n.b(this.f78b, 0L, j2);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j2 > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j2);
        }
        if (j2 == 0) {
            return "";
        }
        i iVar = this.f77a;
        int i2 = iVar.f101b;
        if (((long) i2) + j2 > iVar.f102c) {
            return new String(u(j2), charset);
        }
        String str = new String(iVar.f100a, i2, (int) j2, charset);
        int i3 = (int) (((long) iVar.f101b) + j2);
        iVar.f101b = i3;
        this.f78b -= j2;
        if (i3 == iVar.f102c) {
            this.f77a = iVar.b();
            j.a(iVar);
        }
        return str;
    }

    public String B() {
        try {
            return A(this.f78b, n.f114a);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    public String C(long j2) {
        return A(j2, n.f114a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0055, code lost:
    
        if (r19 == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0057, code lost:
    
        return -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0058, code lost:
    
        return r10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int D(D1.f r18, boolean r19) {
        /*
            r17 = this;
            r0 = r18
            r1 = r17
            D1.i r1 = r1.f77a
            r2 = -2
            if (r1 != 0) goto L13
            if (r19 == 0) goto Lc
            return r2
        Lc:
            D1.d r1 = D1.d.f81e
            int r0 = r0.indexOf(r1)
            return r0
        L13:
            byte[] r3 = r1.f100a
            int r4 = r1.f101b
            int r5 = r1.f102c
            int[] r0 = r0.f89b
            r6 = 0
            r7 = -1
            r9 = r1
            r8 = r6
            r10 = r7
        L20:
            int r11 = r8 + 1
            r12 = r0[r8]
            int r8 = r8 + 2
            r11 = r0[r11]
            if (r11 == r7) goto L2b
            r10 = r11
        L2b:
            if (r9 != 0) goto L2e
            goto L55
        L2e:
            r11 = 0
            if (r12 >= 0) goto L72
            int r12 = r12 * (-1)
            int r13 = r8 + r12
        L35:
            int r12 = r4 + 1
            r4 = r3[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r14 = r8 + 1
            r8 = r0[r8]
            if (r4 == r8) goto L42
            return r10
        L42:
            if (r14 != r13) goto L46
            r4 = 1
            goto L47
        L46:
            r4 = r6
        L47:
            if (r12 != r5) goto L62
            D1.i r3 = r9.f105f
            int r5 = r3.f101b
            byte[] r8 = r3.f100a
            int r9 = r3.f102c
            if (r3 != r1) goto L5c
            if (r4 != 0) goto L59
        L55:
            if (r19 == 0) goto L58
            return r2
        L58:
            return r10
        L59:
            r3 = r8
            r8 = r11
            goto L65
        L5c:
            r16 = r8
            r8 = r3
            r3 = r16
            goto L65
        L62:
            r8 = r9
            r9 = r5
            r5 = r12
        L65:
            if (r4 == 0) goto L6d
            r4 = r0[r14]
            r13 = r5
            r5 = r9
            r9 = r8
            goto L94
        L6d:
            r4 = r5
            r5 = r9
            r9 = r8
            r8 = r14
            goto L35
        L72:
            int r13 = r4 + 1
            r4 = r3[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r14 = r8 + r12
        L7a:
            if (r8 != r14) goto L7d
            return r10
        L7d:
            r15 = r0[r8]
            if (r4 != r15) goto L9a
            int r8 = r8 + r12
            r4 = r0[r8]
            if (r13 != r5) goto L94
            D1.i r9 = r9.f105f
            int r3 = r9.f101b
            byte[] r5 = r9.f100a
            int r8 = r9.f102c
            r13 = r3
            r3 = r5
            r5 = r8
            if (r9 != r1) goto L94
            r9 = r11
        L94:
            if (r4 < 0) goto L97
            return r4
        L97:
            int r8 = -r4
            r4 = r13
            goto L20
        L9a:
            int r8 = r8 + 1
            goto L7a
        */
        throw new UnsupportedOperationException("Method not decompiled: D1.a.D(D1.f, boolean):int");
    }

    public final long E() {
        return this.f78b;
    }

    public void F(long j2) throws EOFException {
        while (j2 > 0) {
            if (this.f77a == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j2, r0.f102c - r0.f101b);
            long j3 = iMin;
            this.f78b -= j3;
            j2 -= j3;
            i iVar = this.f77a;
            int i2 = iVar.f101b + iMin;
            iVar.f101b = i2;
            if (i2 == iVar.f102c) {
                this.f77a = iVar.b();
                j.a(iVar);
            }
        }
    }

    public final d G() {
        long j2 = this.f78b;
        if (j2 <= 2147483647L) {
            return H((int) j2);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f78b);
    }

    public final d H(int i2) {
        return i2 == 0 ? d.f81e : new k(this, i2);
    }

    public i I(int i2) {
        if (i2 < 1 || i2 > 8192) {
            throw new IllegalArgumentException();
        }
        i iVar = this.f77a;
        if (iVar != null) {
            i iVar2 = iVar.f106g;
            return (iVar2.f102c + i2 > 8192 || !iVar2.f104e) ? iVar2.c(j.b()) : iVar2;
        }
        i iVarB = j.b();
        this.f77a = iVarB;
        iVarB.f106g = iVarB;
        iVarB.f105f = iVarB;
        return iVarB;
    }

    public void J(a aVar, long j2) {
        if (aVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (aVar == this) {
            throw new IllegalArgumentException("source == this");
        }
        n.b(aVar.f78b, 0L, j2);
        while (j2 > 0) {
            i iVar = aVar.f77a;
            if (j2 < iVar.f102c - iVar.f101b) {
                i iVar2 = this.f77a;
                i iVar3 = iVar2 != null ? iVar2.f106g : null;
                if (iVar3 != null && iVar3.f104e) {
                    if ((((long) iVar3.f102c) + j2) - ((long) (iVar3.f103d ? 0 : iVar3.f101b)) <= 8192) {
                        iVar.f(iVar3, (int) j2);
                        aVar.f78b -= j2;
                        this.f78b += j2;
                        return;
                    }
                }
                aVar.f77a = iVar.e((int) j2);
            }
            i iVar4 = aVar.f77a;
            long j3 = iVar4.f102c - iVar4.f101b;
            aVar.f77a = iVar4.b();
            i iVar5 = this.f77a;
            if (iVar5 == null) {
                this.f77a = iVar4;
                iVar4.f106g = iVar4;
                iVar4.f105f = iVar4;
            } else {
                iVar5.f106g.c(iVar4).a();
            }
            aVar.f78b -= j3;
            this.f78b += j3;
            j2 -= j3;
        }
    }

    @Override // D1.b
    /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
    public a o(int i2) {
        i iVarI = I(1);
        byte[] bArr = iVarI.f100a;
        int i3 = iVarI.f102c;
        iVarI.f102c = i3 + 1;
        bArr[i3] = (byte) i2;
        this.f78b++;
        return this;
    }

    public a L(int i2) {
        i iVarI = I(4);
        byte[] bArr = iVarI.f100a;
        int i3 = iVarI.f102c;
        bArr[i3] = (byte) ((i2 >>> 24) & 255);
        bArr[i3 + 1] = (byte) ((i2 >>> 16) & 255);
        bArr[i3 + 2] = (byte) ((i2 >>> 8) & 255);
        bArr[i3 + 3] = (byte) (i2 & 255);
        iVarI.f102c = i3 + 4;
        this.f78b += 4;
        return this;
    }

    @Override // D1.b
    /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
    public a i(String str) {
        return k(str, 0, str.length());
    }

    @Override // D1.b
    /* JADX INFO: renamed from: N, reason: merged with bridge method [inline-methods] */
    public a k(String str, int i2, int i3) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + i2);
        }
        if (i3 < i2) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i3 + " < " + i2);
        }
        if (i3 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i3 + " > " + str.length());
        }
        while (i2 < i3) {
            char cCharAt = str.charAt(i2);
            if (cCharAt < 128) {
                i iVarI = I(1);
                byte[] bArr = iVarI.f100a;
                int i4 = iVarI.f102c - i2;
                int iMin = Math.min(i3, 8192 - i4);
                int i5 = i2 + 1;
                bArr[i2 + i4] = (byte) cCharAt;
                while (i5 < iMin) {
                    char cCharAt2 = str.charAt(i5);
                    if (cCharAt2 >= 128) {
                        break;
                    }
                    bArr[i5 + i4] = (byte) cCharAt2;
                    i5++;
                }
                int i6 = iVarI.f102c;
                int i7 = (i4 + i5) - i6;
                iVarI.f102c = i6 + i7;
                this.f78b += (long) i7;
                i2 = i5;
            } else {
                if (cCharAt < 2048) {
                    o((cCharAt >> 6) | 192);
                    o((cCharAt & '?') | 128);
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    o((cCharAt >> '\f') | 224);
                    o(((cCharAt >> 6) & 63) | 128);
                    o((cCharAt & '?') | 128);
                } else {
                    int i8 = i2 + 1;
                    char cCharAt3 = i8 < i3 ? str.charAt(i8) : (char) 0;
                    if (cCharAt > 56319 || cCharAt3 < 56320 || cCharAt3 > 57343) {
                        o(63);
                        i2 = i8;
                    } else {
                        int i9 = (((cCharAt & 10239) << 10) | (9215 & cCharAt3)) + 65536;
                        o((i9 >> 18) | 240);
                        o(((i9 >> 12) & 63) | 128);
                        o(((i9 >> 6) & 63) | 128);
                        o((i9 & 63) | 128);
                        i2 += 2;
                    }
                }
                i2++;
            }
        }
        return this;
    }

    public final void a() {
        try {
            F(this.f78b);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // D1.c
    public a b() {
        return this;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public a clone() {
        a aVar = new a();
        if (this.f78b == 0) {
            return aVar;
        }
        i iVarD = this.f77a.d();
        aVar.f77a = iVarD;
        iVarD.f106g = iVarD;
        iVarD.f105f = iVarD;
        i iVar = this.f77a;
        while (true) {
            iVar = iVar.f105f;
            if (iVar == this.f77a) {
                aVar.f78b = this.f78b;
                return aVar;
            }
            aVar.f77a.f106g.c(iVar.d());
        }
    }

    @Override // D1.l, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() {
    }

    public final a d(a aVar, long j2, long j3) {
        if (aVar == null) {
            throw new IllegalArgumentException("out == null");
        }
        n.b(this.f78b, j2, j3);
        if (j3 == 0) {
            return this;
        }
        aVar.f78b += j3;
        i iVar = this.f77a;
        while (true) {
            int i2 = iVar.f102c;
            int i3 = iVar.f101b;
            if (j2 < i2 - i3) {
                break;
            }
            j2 -= (long) (i2 - i3);
            iVar = iVar.f105f;
        }
        while (j3 > 0) {
            i iVarD = iVar.d();
            int i4 = (int) (((long) iVarD.f101b) + j2);
            iVarD.f101b = i4;
            iVarD.f102c = Math.min(i4 + ((int) j3), iVarD.f102c);
            i iVar2 = aVar.f77a;
            if (iVar2 == null) {
                iVarD.f106g = iVarD;
                iVarD.f105f = iVarD;
                aVar.f77a = iVarD;
            } else {
                iVar2.f106g.c(iVarD);
            }
            j3 -= (long) (iVarD.f102c - iVarD.f101b);
            iVar = iVar.f105f;
            j2 = 0;
        }
        return this;
    }

    public boolean e() {
        return this.f78b == 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        long j2 = this.f78b;
        if (j2 != aVar.f78b) {
            return false;
        }
        long j3 = 0;
        if (j2 == 0) {
            return true;
        }
        i iVar = this.f77a;
        i iVar2 = aVar.f77a;
        int i2 = iVar.f101b;
        int i3 = iVar2.f101b;
        while (j3 < this.f78b) {
            long jMin = Math.min(iVar.f102c - i2, iVar2.f102c - i3);
            int i4 = 0;
            while (i4 < jMin) {
                int i5 = i2 + 1;
                int i6 = i3 + 1;
                if (iVar.f100a[i2] != iVar2.f100a[i3]) {
                    return false;
                }
                i4++;
                i2 = i5;
                i3 = i6;
            }
            if (i2 == iVar.f102c) {
                iVar = iVar.f105f;
                i2 = iVar.f101b;
            }
            if (i3 == iVar2.f102c) {
                iVar2 = iVar2.f105f;
                i3 = iVar2.f101b;
            }
            j3 += jMin;
        }
        return true;
    }

    public final byte f(long j2) {
        int i2;
        n.b(this.f78b, j2, 1L);
        long j3 = this.f78b;
        if (j3 - j2 <= j2) {
            long j4 = j2 - j3;
            i iVar = this.f77a;
            do {
                iVar = iVar.f106g;
                int i3 = iVar.f102c;
                i2 = iVar.f101b;
                j4 += (long) (i3 - i2);
            } while (j4 < 0);
            return iVar.f100a[i2 + ((int) j4)];
        }
        i iVar2 = this.f77a;
        while (true) {
            int i4 = iVar2.f102c;
            int i5 = iVar2.f101b;
            long j5 = i4 - i5;
            if (j2 < j5) {
                return iVar2.f100a[i5 + ((int) j2)];
            }
            j2 -= j5;
            iVar2 = iVar2.f105f;
        }
    }

    @Override // java.io.Flushable
    public void flush() {
    }

    @Override // D1.l
    public long g(a aVar, long j2) {
        if (aVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }
        long j3 = this.f78b;
        if (j3 == 0) {
            return -1L;
        }
        if (j2 > j3) {
            j2 = j3;
        }
        aVar.J(this, j2);
        return j2;
    }

    public int hashCode() {
        i iVar = this.f77a;
        if (iVar == null) {
            return 0;
        }
        int i2 = 1;
        do {
            int i3 = iVar.f102c;
            for (int i4 = iVar.f101b; i4 < i3; i4++) {
                i2 = (i2 * 31) + iVar.f100a[i4];
            }
            iVar = iVar.f105f;
        } while (iVar != this.f77a);
        return i2;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    public long l(d dVar, long j2) {
        byte[] bArr;
        if (dVar.j() == 0) {
            throw new IllegalArgumentException("bytes is empty");
        }
        long j3 = 0;
        if (j2 < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        i iVar = this.f77a;
        long j4 = -1;
        if (iVar == null) {
            return -1L;
        }
        long j5 = this.f78b;
        if (j5 - j2 < j2) {
            while (j5 > j2) {
                iVar = iVar.f106g;
                j5 -= (long) (iVar.f102c - iVar.f101b);
            }
        } else {
            while (true) {
                long j6 = ((long) (iVar.f102c - iVar.f101b)) + j3;
                if (j6 >= j2) {
                    break;
                }
                iVar = iVar.f105f;
                j3 = j6;
            }
            j5 = j3;
        }
        byte bD = dVar.d(0);
        int iJ = dVar.j();
        long j7 = 1 + (this.f78b - ((long) iJ));
        long j8 = j2;
        i iVar2 = iVar;
        long j9 = j5;
        while (j9 < j7) {
            byte[] bArr2 = iVar2.f100a;
            int iMin = (int) Math.min(iVar2.f102c, (((long) iVar2.f101b) + j7) - j9);
            int i2 = (int) ((((long) iVar2.f101b) + j8) - j9);
            while (i2 < iMin) {
                if (bArr2[i2] == bD) {
                    bArr = bArr2;
                    if (r(iVar2, i2 + 1, dVar, 1, iJ)) {
                        return ((long) (i2 - iVar2.f101b)) + j9;
                    }
                } else {
                    bArr = bArr2;
                }
                i2++;
                bArr2 = bArr;
            }
            j9 += (long) (iVar2.f102c - iVar2.f101b);
            iVar2 = iVar2.f105f;
            j8 = j9;
            j4 = -1;
        }
        return j4;
    }

    public long n(d dVar, long j2) {
        int i2;
        int i3;
        long j3 = 0;
        if (j2 < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        i iVar = this.f77a;
        if (iVar == null) {
            return -1L;
        }
        long j4 = this.f78b;
        if (j4 - j2 < j2) {
            while (j4 > j2) {
                iVar = iVar.f106g;
                j4 -= (long) (iVar.f102c - iVar.f101b);
            }
        } else {
            while (true) {
                long j5 = ((long) (iVar.f102c - iVar.f101b)) + j3;
                if (j5 >= j2) {
                    break;
                }
                iVar = iVar.f105f;
                j3 = j5;
            }
            j4 = j3;
        }
        if (dVar.j() == 2) {
            byte bD = dVar.d(0);
            byte bD2 = dVar.d(1);
            while (j4 < this.f78b) {
                byte[] bArr = iVar.f100a;
                i2 = (int) ((((long) iVar.f101b) + j2) - j4);
                int i4 = iVar.f102c;
                while (i2 < i4) {
                    byte b2 = bArr[i2];
                    if (b2 == bD || b2 == bD2) {
                        i3 = iVar.f101b;
                        return ((long) (i2 - i3)) + j4;
                    }
                    i2++;
                }
                j4 += (long) (iVar.f102c - iVar.f101b);
                iVar = iVar.f105f;
                j2 = j4;
            }
            return -1L;
        }
        byte[] bArrF = dVar.f();
        while (j4 < this.f78b) {
            byte[] bArr2 = iVar.f100a;
            i2 = (int) ((((long) iVar.f101b) + j2) - j4);
            int i5 = iVar.f102c;
            while (i2 < i5) {
                byte b3 = bArr2[i2];
                for (byte b4 : bArrF) {
                    if (b3 == b4) {
                        i3 = iVar.f101b;
                        return ((long) (i2 - i3)) + j4;
                    }
                }
                i2++;
            }
            j4 += (long) (iVar.f102c - iVar.f101b);
            iVar = iVar.f105f;
            j2 = j4;
        }
        return -1L;
    }

    @Override // D1.c
    public int p(f fVar) {
        int iD = D(fVar, false);
        if (iD == -1) {
            return -1;
        }
        try {
            F(fVar.f88a[iD].j());
            return iD;
        } catch (EOFException unused) {
            throw new AssertionError();
        }
    }

    @Override // D1.c
    public c peek() {
        return e.a(new g(this));
    }

    @Override // D1.c
    public long q(d dVar) {
        return l(dVar, 0L);
    }

    public final boolean r(i iVar, int i2, d dVar, int i3, int i4) {
        int i5 = iVar.f102c;
        byte[] bArr = iVar.f100a;
        while (i3 < i4) {
            if (i2 == i5) {
                i iVar2 = iVar.f105f;
                byte[] bArr2 = iVar2.f100a;
                i2 = iVar2.f101b;
                iVar = iVar2;
                i5 = iVar2.f102c;
                bArr = bArr2;
            }
            if (bArr[i2] != dVar.d(i3)) {
                return false;
            }
            i2++;
            i3++;
        }
        return true;
    }

    public int read(byte[] bArr, int i2, int i3) {
        n.b(bArr.length, i2, i3);
        i iVar = this.f77a;
        if (iVar == null) {
            return -1;
        }
        int iMin = Math.min(i3, iVar.f102c - iVar.f101b);
        System.arraycopy(iVar.f100a, iVar.f101b, bArr, i2, iMin);
        int i4 = iVar.f101b + iMin;
        iVar.f101b = i4;
        this.f78b -= (long) iMin;
        if (i4 == iVar.f102c) {
            this.f77a = iVar.b();
            j.a(iVar);
        }
        return iMin;
    }

    @Override // D1.c
    public byte readByte() {
        long j2 = this.f78b;
        if (j2 == 0) {
            throw new IllegalStateException("size == 0");
        }
        i iVar = this.f77a;
        int i2 = iVar.f101b;
        int i3 = iVar.f102c;
        int i4 = i2 + 1;
        byte b2 = iVar.f100a[i2];
        this.f78b = j2 - 1;
        if (i4 == i3) {
            this.f77a = iVar.b();
            j.a(iVar);
        } else {
            iVar.f101b = i4;
        }
        return b2;
    }

    @Override // D1.c
    public boolean request(long j2) {
        return this.f78b >= j2;
    }

    public byte[] t() {
        try {
            return u(this.f78b);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    public String toString() {
        return G().toString();
    }

    public byte[] u(long j2) throws EOFException {
        n.b(this.f78b, 0L, j2);
        if (j2 <= 2147483647L) {
            byte[] bArr = new byte[(int) j2];
            x(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j2);
    }

    @Override // D1.c
    public long v(d dVar) {
        return n(dVar, 0L);
    }

    public d w() {
        return new d(t());
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        int iRemaining = byteBuffer.remaining();
        int i2 = iRemaining;
        while (i2 > 0) {
            i iVarI = I(1);
            int iMin = Math.min(i2, 8192 - iVarI.f102c);
            byteBuffer.get(iVarI.f100a, iVarI.f102c, iMin);
            i2 -= iMin;
            iVarI.f102c += iMin;
        }
        this.f78b += (long) iRemaining;
        return iRemaining;
    }

    public void x(byte[] bArr) throws EOFException {
        int i2 = 0;
        while (i2 < bArr.length) {
            int i3 = read(bArr, i2, bArr.length - i2);
            if (i3 == -1) {
                throw new EOFException();
            }
            i2 += i3;
        }
    }

    @Override // D1.c
    public InputStream y() {
        return new C0004a();
    }

    public int z() {
        long j2 = this.f78b;
        if (j2 < 4) {
            throw new IllegalStateException("size < 4: " + this.f78b);
        }
        i iVar = this.f77a;
        int i2 = iVar.f101b;
        int i3 = iVar.f102c;
        if (i3 - i2 < 4) {
            return (readByte() & TransitionInfo.INIT) | ((readByte() & TransitionInfo.INIT) << 24) | ((readByte() & TransitionInfo.INIT) << 16) | ((readByte() & TransitionInfo.INIT) << 8);
        }
        byte[] bArr = iVar.f100a;
        int i4 = i2 + 3;
        int i5 = ((bArr[i2 + 1] & TransitionInfo.INIT) << 16) | ((bArr[i2] & TransitionInfo.INIT) << 24) | ((bArr[i2 + 2] & TransitionInfo.INIT) << 8);
        int i6 = i2 + 4;
        int i7 = (bArr[i4] & TransitionInfo.INIT) | i5;
        this.f78b = j2 - 4;
        if (i6 == i3) {
            this.f77a = iVar.b();
            j.a(iVar);
        } else {
            iVar.f101b = i6;
        }
        return i7;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        i iVar = this.f77a;
        if (iVar == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), iVar.f102c - iVar.f101b);
        byteBuffer.put(iVar.f100a, iVar.f101b, iMin);
        int i2 = iVar.f101b + iMin;
        iVar.f101b = i2;
        this.f78b -= (long) iMin;
        if (i2 == iVar.f102c) {
            this.f77a = iVar.b();
            j.a(iVar);
        }
        return iMin;
    }
}
