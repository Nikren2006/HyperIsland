package n;

import android.graphics.Color;
import android.graphics.PointF;
import com.miui.maml.folme.AnimatedProperty;
import java.util.ArrayList;
import java.util.List;
import o.AbstractC0715c;

/* JADX INFO: loaded from: classes.dex */
public abstract class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6219a = AbstractC0715c.a.a(AnimatedProperty.PROPERTY_NAME_X, AnimatedProperty.PROPERTY_NAME_Y);

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f6220a;

        static {
            int[] iArr = new int[AbstractC0715c.b.values().length];
            f6220a = iArr;
            try {
                iArr[AbstractC0715c.b.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6220a[AbstractC0715c.b.BEGIN_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6220a[AbstractC0715c.b.BEGIN_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static PointF a(AbstractC0715c abstractC0715c, float f2) {
        abstractC0715c.c();
        float fT = (float) abstractC0715c.t();
        float fT2 = (float) abstractC0715c.t();
        while (abstractC0715c.A() != AbstractC0715c.b.END_ARRAY) {
            abstractC0715c.E();
        }
        abstractC0715c.e();
        return new PointF(fT * f2, fT2 * f2);
    }

    public static PointF b(AbstractC0715c abstractC0715c, float f2) {
        float fT = (float) abstractC0715c.t();
        float fT2 = (float) abstractC0715c.t();
        while (abstractC0715c.n()) {
            abstractC0715c.E();
        }
        return new PointF(fT * f2, fT2 * f2);
    }

    public static PointF c(AbstractC0715c abstractC0715c, float f2) {
        abstractC0715c.d();
        float fG = 0.0f;
        float fG2 = 0.0f;
        while (abstractC0715c.n()) {
            int iC = abstractC0715c.C(f6219a);
            if (iC == 0) {
                fG = g(abstractC0715c);
            } else if (iC != 1) {
                abstractC0715c.D();
                abstractC0715c.E();
            } else {
                fG2 = g(abstractC0715c);
            }
        }
        abstractC0715c.f();
        return new PointF(fG * f2, fG2 * f2);
    }

    public static int d(AbstractC0715c abstractC0715c) {
        abstractC0715c.c();
        int iT = (int) (abstractC0715c.t() * 255.0d);
        int iT2 = (int) (abstractC0715c.t() * 255.0d);
        int iT3 = (int) (abstractC0715c.t() * 255.0d);
        while (abstractC0715c.n()) {
            abstractC0715c.E();
        }
        abstractC0715c.e();
        return Color.argb(255, iT, iT2, iT3);
    }

    public static PointF e(AbstractC0715c abstractC0715c, float f2) {
        int i2 = a.f6220a[abstractC0715c.A().ordinal()];
        if (i2 == 1) {
            return b(abstractC0715c, f2);
        }
        if (i2 == 2) {
            return a(abstractC0715c, f2);
        }
        if (i2 == 3) {
            return c(abstractC0715c, f2);
        }
        throw new IllegalArgumentException("Unknown point starts with " + abstractC0715c.A());
    }

    public static List f(AbstractC0715c abstractC0715c, float f2) {
        ArrayList arrayList = new ArrayList();
        abstractC0715c.c();
        while (abstractC0715c.A() == AbstractC0715c.b.BEGIN_ARRAY) {
            abstractC0715c.c();
            arrayList.add(e(abstractC0715c, f2));
            abstractC0715c.e();
        }
        abstractC0715c.e();
        return arrayList;
    }

    public static float g(AbstractC0715c abstractC0715c) {
        AbstractC0715c.b bVarA = abstractC0715c.A();
        int i2 = a.f6220a[bVarA.ordinal()];
        if (i2 == 1) {
            return (float) abstractC0715c.t();
        }
        if (i2 != 2) {
            throw new IllegalArgumentException("Unknown value for token of type " + bVarA);
        }
        abstractC0715c.c();
        float fT = (float) abstractC0715c.t();
        while (abstractC0715c.n()) {
            abstractC0715c.E();
        }
        abstractC0715c.e();
        return fT;
    }
}
