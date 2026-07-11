package W;

import c0.C0228c;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public abstract class m {
    public static void a(U.f fVar, C0228c c0228c) {
        X.m.f917V.d(c0228c, fVar);
    }

    public static Writer b(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new b(appendable);
    }

    public static final class b extends Writer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Appendable f827a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final a f828b = new a();

        public static class a implements CharSequence {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public char[] f829a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public String f830b;

            public a() {
            }

            public void a(char[] cArr) {
                this.f829a = cArr;
                this.f830b = null;
            }

            @Override // java.lang.CharSequence
            public char charAt(int i2) {
                return this.f829a[i2];
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.f829a.length;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i2, int i3) {
                return new String(this.f829a, i2, i3 - i2);
            }

            @Override // java.lang.CharSequence
            public String toString() {
                if (this.f830b == null) {
                    this.f830b = new String(this.f829a);
                }
                return this.f830b;
            }
        }

        public b(Appendable appendable) {
            this.f827a = appendable;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i2, int i3) throws IOException {
            this.f828b.a(cArr);
            this.f827a.append(this.f828b, i2, i3 + i2);
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(CharSequence charSequence) throws IOException {
            this.f827a.append(charSequence);
            return this;
        }

        @Override // java.io.Writer
        public void write(int i2) throws IOException {
            this.f827a.append((char) i2);
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(CharSequence charSequence, int i2, int i3) throws IOException {
            this.f827a.append(charSequence, i2, i3);
            return this;
        }

        @Override // java.io.Writer
        public void write(String str, int i2, int i3) throws IOException {
            Objects.requireNonNull(str);
            this.f827a.append(str, i2, i3 + i2);
        }
    }
}
