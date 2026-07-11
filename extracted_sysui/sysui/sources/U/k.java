package U;

import java.math.BigInteger;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class k extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f745a;

    public k(Boolean bool) {
        Objects.requireNonNull(bool);
        this.f745a = bool;
    }

    public static boolean l(k kVar) {
        Object obj = kVar.f745a;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || k.class != obj.getClass()) {
            return false;
        }
        k kVar = (k) obj;
        if (this.f745a == null) {
            return kVar.f745a == null;
        }
        if (l(this) && l(kVar)) {
            return i().longValue() == kVar.i().longValue();
        }
        Object obj2 = this.f745a;
        if (!(obj2 instanceof Number) || !(kVar.f745a instanceof Number)) {
            return obj2.equals(kVar.f745a);
        }
        double dDoubleValue = i().doubleValue();
        double dDoubleValue2 = kVar.i().doubleValue();
        if (dDoubleValue != dDoubleValue2) {
            return Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2);
        }
        return true;
    }

    public boolean h() {
        return k() ? ((Boolean) this.f745a).booleanValue() : Boolean.parseBoolean(j());
    }

    public int hashCode() {
        long jDoubleToLongBits;
        if (this.f745a == null) {
            return 31;
        }
        if (l(this)) {
            jDoubleToLongBits = i().longValue();
        } else {
            Object obj = this.f745a;
            if (!(obj instanceof Number)) {
                return obj.hashCode();
            }
            jDoubleToLongBits = Double.doubleToLongBits(i().doubleValue());
        }
        return (int) ((jDoubleToLongBits >>> 32) ^ jDoubleToLongBits);
    }

    public Number i() {
        Object obj = this.f745a;
        if (obj instanceof Number) {
            return (Number) obj;
        }
        if (obj instanceof String) {
            return new W.g((String) obj);
        }
        throw new UnsupportedOperationException("Primitive is neither a number nor a string");
    }

    public String j() {
        Object obj = this.f745a;
        if (obj instanceof String) {
            return (String) obj;
        }
        if (m()) {
            return i().toString();
        }
        if (k()) {
            return ((Boolean) this.f745a).toString();
        }
        throw new AssertionError("Unexpected value type: " + this.f745a.getClass());
    }

    public boolean k() {
        return this.f745a instanceof Boolean;
    }

    public boolean m() {
        return this.f745a instanceof Number;
    }

    public boolean n() {
        return this.f745a instanceof String;
    }

    public k(Number number) {
        Objects.requireNonNull(number);
        this.f745a = number;
    }

    public k(String str) {
        Objects.requireNonNull(str);
        this.f745a = str;
    }
}
