package w1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class j {
    public static final h a(s1.c keyDescriptor) {
        kotlin.jvm.internal.n.g(keyDescriptor, "keyDescriptor");
        return new h("Value of type '" + keyDescriptor.h() + "' can't be used in JSON as a key in the map. It should have either primitive or enum kind, but its kind is '" + keyDescriptor.c() + "'.\nUse 'allowStructuredMapKeys = true' in 'Json {}' builder to convert such maps to [key1, value1, key2, value2,...] arrays.");
    }

    public static final f b(int i2, String message) {
        kotlin.jvm.internal.n.g(message, "message");
        if (i2 >= 0) {
            message = "Unexpected JSON token at offset " + i2 + ": " + message;
        }
        return new f(message);
    }

    public static final f c(int i2, String message, CharSequence input) {
        kotlin.jvm.internal.n.g(message, "message");
        kotlin.jvm.internal.n.g(input, "input");
        return b(i2, message + "\nJSON input: " + ((Object) d(input, i2)));
    }

    public static final CharSequence d(CharSequence charSequence, int i2) {
        if (charSequence.length() < 200) {
            return charSequence;
        }
        if (i2 == -1) {
            int length = charSequence.length() - 60;
            if (length <= 0) {
                return charSequence;
            }
            return "....." + charSequence.subSequence(length, charSequence.length()).toString();
        }
        int i3 = i2 - 30;
        int i4 = i2 + 30;
        return (i3 <= 0 ? "" : ".....") + charSequence.subSequence(c1.f.c(i3, 0), c1.f.f(i4, charSequence.length())).toString() + (i4 >= charSequence.length() ? "" : ".....");
    }

    public static final Void e(a aVar, Number result) {
        kotlin.jvm.internal.n.g(aVar, "<this>");
        kotlin.jvm.internal.n.g(result, "result");
        a.r(aVar, "Unexpected special floating-point value " + result + ". By default, non-finite floating point values are prohibited because they do not conform JSON specification", 0, "It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'", 2, null);
        throw new H0.c();
    }
}
