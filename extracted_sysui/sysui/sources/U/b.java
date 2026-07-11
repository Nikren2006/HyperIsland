package U;

import java.lang.reflect.Field;
import java.util.Locale;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public abstract class b implements U.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f700a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f701b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b f702c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b f703d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b f704e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b f705f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final b f706g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ b[] f707h;

    public enum a extends b {
        public a(String str, int i2) {
            super(str, i2, null);
        }

        @Override // U.c
        public String a(Field field) {
            return field.getName();
        }
    }

    static {
        a aVar = new a("IDENTITY", 0);
        f700a = aVar;
        b bVar = new b("UPPER_CAMEL_CASE", 1) { // from class: U.b.b
            {
                a aVar2 = null;
            }

            @Override // U.c
            public String a(Field field) {
                return b.c(field.getName());
            }
        };
        f701b = bVar;
        b bVar2 = new b("UPPER_CAMEL_CASE_WITH_SPACES", 2) { // from class: U.b.c
            {
                a aVar2 = null;
            }

            @Override // U.c
            public String a(Field field) {
                return b.c(b.b(field.getName(), ' '));
            }
        };
        f702c = bVar2;
        b bVar3 = new b("UPPER_CASE_WITH_UNDERSCORES", 3) { // from class: U.b.d
            {
                a aVar2 = null;
            }

            @Override // U.c
            public String a(Field field) {
                return b.b(field.getName(), '_').toUpperCase(Locale.ENGLISH);
            }
        };
        f703d = bVar3;
        b bVar4 = new b("LOWER_CASE_WITH_UNDERSCORES", 4) { // from class: U.b.e
            {
                a aVar2 = null;
            }

            @Override // U.c
            public String a(Field field) {
                return b.b(field.getName(), '_').toLowerCase(Locale.ENGLISH);
            }
        };
        f704e = bVar4;
        b bVar5 = new b("LOWER_CASE_WITH_DASHES", 5) { // from class: U.b.f
            {
                a aVar2 = null;
            }

            @Override // U.c
            public String a(Field field) {
                return b.b(field.getName(), '-').toLowerCase(Locale.ENGLISH);
            }
        };
        f705f = bVar5;
        b bVar6 = new b("LOWER_CASE_WITH_DOTS", 6) { // from class: U.b.g
            {
                a aVar2 = null;
            }

            @Override // U.c
            public String a(Field field) {
                return b.b(field.getName(), '.').toLowerCase(Locale.ENGLISH);
            }
        };
        f706g = bVar6;
        f707h = new b[]{aVar, bVar, bVar2, bVar3, bVar4, bVar5, bVar6};
    }

    public b(String str, int i2) {
    }

    public static String b(String str, char c2) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (Character.isUpperCase(cCharAt) && sb.length() != 0) {
                sb.append(c2);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    public static String c(String str) {
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (Character.isLetter(cCharAt)) {
                if (Character.isUpperCase(cCharAt)) {
                    return str;
                }
                char upperCase = Character.toUpperCase(cCharAt);
                if (i2 == 0) {
                    return upperCase + str.substring(1);
                }
                return str.substring(0, i2) + upperCase + str.substring(i2 + 1);
            }
        }
        return str;
    }

    public static b valueOf(String str) {
        return (b) Enum.valueOf(b.class, str);
    }

    public static b[] values() {
        return (b[]) f707h.clone();
    }

    public /* synthetic */ b(String str, int i2, a aVar) {
        this(str, i2);
    }
}
