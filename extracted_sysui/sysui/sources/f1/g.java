package f1;

import I0.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class g extends f {

    public static final class a extends kotlin.jvm.internal.o implements Function1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f4256a = new a();

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final String invoke(String line) {
            kotlin.jvm.internal.n.g(line, "line");
            return line;
        }
    }

    public static final class b extends kotlin.jvm.internal.o implements Function1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4257a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str) {
            super(1);
            this.f4257a = str;
        }

        @Override // kotlin.jvm.functions.Function1
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final String invoke(String line) {
            kotlin.jvm.internal.n.g(line, "line");
            return this.f4257a + line;
        }
    }

    public static final Function1 b(String str) {
        return str.length() == 0 ? a.f4256a : new b(str);
    }

    public static final int c(String str) {
        int length = str.length();
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            }
            if (!f1.a.c(str.charAt(i2))) {
                break;
            }
            i2++;
        }
        return i2 == -1 ? str.length() : i2;
    }

    public static final String d(String str, String newIndent) {
        String str2;
        kotlin.jvm.internal.n.g(str, "<this>");
        kotlin.jvm.internal.n.g(newIndent, "newIndent");
        List listM = o.M(str);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listM) {
            if (!n.n((String) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(I0.n.o(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(Integer.valueOf(c((String) it.next())));
        }
        Integer num = (Integer) u.X(arrayList2);
        int i2 = 0;
        int iIntValue = num != null ? num.intValue() : 0;
        int length = str.length() + (newIndent.length() * listM.size());
        Function1 function1B = b(newIndent);
        int i3 = I0.m.i(listM);
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : listM) {
            int i4 = i2 + 1;
            if (i2 < 0) {
                I0.m.n();
            }
            String str3 = (String) obj2;
            if ((i2 == 0 || i2 == i3) && n.n(str3)) {
                str3 = null;
            } else {
                String strE0 = q.e0(str3, iIntValue);
                if (strE0 != null && (str2 = (String) function1B.invoke(strE0)) != null) {
                    str3 = str2;
                }
            }
            if (str3 != null) {
                arrayList3.add(str3);
            }
            i2 = i4;
        }
        String string = ((StringBuilder) u.Q(arrayList3, new StringBuilder(length), (124 & 2) != 0 ? ", " : "\n", (124 & 4) != 0 ? "" : null, (124 & 8) == 0 ? null : "", (124 & 16) != 0 ? -1 : 0, (124 & 32) != 0 ? "..." : null, (124 & 64) != 0 ? null : null)).toString();
        kotlin.jvm.internal.n.f(string, "toString(...)");
        return string;
    }

    public static String e(String str) {
        kotlin.jvm.internal.n.g(str, "<this>");
        return d(str, "");
    }
}
