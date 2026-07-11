package U;

import c0.C0228c;
import java.io.IOException;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes2.dex */
public abstract class f {
    public e a() {
        if (d()) {
            return (e) this;
        }
        throw new IllegalStateException("Not a JSON Array: " + this);
    }

    public i b() {
        if (f()) {
            return (i) this;
        }
        throw new IllegalStateException("Not a JSON Object: " + this);
    }

    public k c() {
        if (g()) {
            return (k) this;
        }
        throw new IllegalStateException("Not a JSON Primitive: " + this);
    }

    public boolean d() {
        return this instanceof e;
    }

    public boolean e() {
        return this instanceof h;
    }

    public boolean f() {
        return this instanceof i;
    }

    public boolean g() {
        return this instanceof k;
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            C0228c c0228c = new C0228c(stringWriter);
            c0228c.H(true);
            W.m.a(this, c0228c);
            return stringWriter.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
