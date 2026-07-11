package a1;

import java.util.Random;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class b extends AbstractC0197a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f984c = new a();

    public static final class a extends ThreadLocal {
        @Override // java.lang.ThreadLocal
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Random initialValue() {
            return new Random();
        }
    }

    @Override // a1.AbstractC0197a
    public Random d() {
        Object obj = this.f984c.get();
        n.f(obj, "get(...)");
        return (Random) obj;
    }
}
