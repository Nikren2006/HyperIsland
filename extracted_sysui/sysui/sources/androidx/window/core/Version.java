package androidx.window.core;

import H0.d;
import H0.e;
import f1.n;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class Version implements Comparable<Version> {
    private static final Version CURRENT;
    public static final Companion Companion = new Companion(null);
    private static final Version UNKNOWN = new Version(0, 0, 0, "");
    private static final Version VERSION_0_1 = new Version(0, 1, 0, "");
    private static final Version VERSION_1_0;
    private static final String VERSION_PATTERN_STRING = "(\\d+)(?:\\.(\\d+))(?:\\.(\\d+))(?:-(.+))?";
    private final d bigInteger$delegate;
    private final String description;
    private final int major;
    private final int minor;
    private final int patch;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Version getCURRENT() {
            return Version.CURRENT;
        }

        public final Version getUNKNOWN() {
            return Version.UNKNOWN;
        }

        public final Version getVERSION_0_1() {
            return Version.VERSION_0_1;
        }

        public final Version getVERSION_1_0() {
            return Version.VERSION_1_0;
        }

        public final Version parse(String str) {
            if (str == null || n.n(str)) {
                return null;
            }
            Matcher matcher = Pattern.compile(Version.VERSION_PATTERN_STRING).matcher(str);
            if (!matcher.matches()) {
                return null;
            }
            String strGroup = matcher.group(1);
            Integer numValueOf = strGroup == null ? null : Integer.valueOf(Integer.parseInt(strGroup));
            if (numValueOf == null) {
                return null;
            }
            int iIntValue = numValueOf.intValue();
            String strGroup2 = matcher.group(2);
            Integer numValueOf2 = strGroup2 == null ? null : Integer.valueOf(Integer.parseInt(strGroup2));
            if (numValueOf2 == null) {
                return null;
            }
            int iIntValue2 = numValueOf2.intValue();
            String strGroup3 = matcher.group(3);
            Integer numValueOf3 = strGroup3 == null ? null : Integer.valueOf(Integer.parseInt(strGroup3));
            if (numValueOf3 == null) {
                return null;
            }
            int iIntValue3 = numValueOf3.intValue();
            String description = matcher.group(4) != null ? matcher.group(4) : "";
            kotlin.jvm.internal.n.f(description, "description");
            return new Version(iIntValue, iIntValue2, iIntValue3, description, null);
        }

        private Companion() {
        }
    }

    static {
        Version version = new Version(1, 0, 0, "");
        VERSION_1_0 = version;
        CURRENT = version;
    }

    public /* synthetic */ Version(int i2, int i3, int i4, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, i3, i4, str);
    }

    private final BigInteger getBigInteger() {
        Object value = this.bigInteger$delegate.getValue();
        kotlin.jvm.internal.n.f(value, "<get-bigInteger>(...)");
        return (BigInteger) value;
    }

    public static final Version parse(String str) {
        return Companion.parse(str);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Version)) {
            return false;
        }
        Version version = (Version) obj;
        return this.major == version.major && this.minor == version.minor && this.patch == version.patch;
    }

    public final String getDescription() {
        return this.description;
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final int getPatch() {
        return this.patch;
    }

    public int hashCode() {
        return ((((527 + this.major) * 31) + this.minor) * 31) + this.patch;
    }

    public String toString() {
        return this.major + '.' + this.minor + '.' + this.patch + (!n.n(this.description) ? kotlin.jvm.internal.n.o("-", this.description) : "");
    }

    private Version(int i2, int i3, int i4, String str) {
        this.major = i2;
        this.minor = i3;
        this.patch = i4;
        this.description = str;
        this.bigInteger$delegate = e.b(new Version$bigInteger$2(this));
    }

    @Override // java.lang.Comparable
    public int compareTo(Version other) {
        kotlin.jvm.internal.n.g(other, "other");
        return getBigInteger().compareTo(other.getBigInteger());
    }
}
