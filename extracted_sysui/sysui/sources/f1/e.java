package f1;

import I0.AbstractC0184l;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes2.dex */
public final class e implements Serializable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f4254b = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Pattern f4255a;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }

    public e(Pattern nativePattern) {
        kotlin.jvm.internal.n.g(nativePattern, "nativePattern");
        this.f4255a = nativePattern;
    }

    public final boolean a(CharSequence input) {
        kotlin.jvm.internal.n.g(input, "input");
        return this.f4255a.matcher(input).matches();
    }

    public final List b(CharSequence input, int i2) {
        kotlin.jvm.internal.n.g(input, "input");
        o.Q(i2);
        Matcher matcher = this.f4255a.matcher(input);
        if (i2 == 1 || !matcher.find()) {
            return AbstractC0184l.d(input.toString());
        }
        ArrayList arrayList = new ArrayList(i2 > 0 ? c1.f.f(i2, 10) : 10);
        int i3 = i2 - 1;
        int iEnd = 0;
        do {
            arrayList.add(input.subSequence(iEnd, matcher.start()).toString());
            iEnd = matcher.end();
            if (i3 >= 0 && arrayList.size() == i3) {
                break;
            }
        } while (matcher.find());
        arrayList.add(input.subSequence(iEnd, input.length()).toString());
        return arrayList;
    }

    public String toString() {
        String string = this.f4255a.toString();
        kotlin.jvm.internal.n.f(string, "toString(...)");
        return string;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public e(String pattern) {
        kotlin.jvm.internal.n.g(pattern, "pattern");
        Pattern patternCompile = Pattern.compile(pattern);
        kotlin.jvm.internal.n.f(patternCompile, "compile(...)");
        this(patternCompile);
    }
}
