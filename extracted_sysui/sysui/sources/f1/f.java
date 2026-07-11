package f1;

import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class f {
    public static void a(Appendable appendable, Object obj, Function1 function1) {
        kotlin.jvm.internal.n.g(appendable, "<this>");
        if (function1 != null) {
            appendable.append((CharSequence) function1.invoke(obj));
            return;
        }
        if (obj == null ? true : obj instanceof CharSequence) {
            appendable.append((CharSequence) obj);
        } else if (obj instanceof Character) {
            appendable.append(((Character) obj).charValue());
        } else {
            appendable.append(String.valueOf(obj));
        }
    }
}
