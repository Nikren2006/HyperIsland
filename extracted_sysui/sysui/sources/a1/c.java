package a1;

import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f985a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f986b = P0.b.f586a.b();

    public static final class a extends c implements Serializable {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // a1.c
        public int b() {
            return c.f986b.b();
        }

        @Override // a1.c
        public int c(int i2) {
            return c.f986b.c(i2);
        }

        public a() {
        }
    }

    public abstract int b();

    public abstract int c(int i2);
}
