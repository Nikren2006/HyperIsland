package miui.systemui.util;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import g1.E;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dagger.qualifiers.Main;

/* JADX INFO: loaded from: classes4.dex */
public final class TalkBackUtils extends ContentObserver {
    public static final Companion Companion = new Companion(null);
    public static final String TALKBACK_NAME_1 = "com.google.android.marvin.talkback/com.google.android.marvin.talkback.TalkBackService";
    public static final String TALKBACK_NAME_2 = "com.google.android.marvin.talkback/.TalkBackService";
    private final Context context;
    private Boolean isTalkBackEnabled;
    private final Handler mainHandler;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.util.TalkBackUtils$getTalkBackEnabled$1, reason: invalid class name */
    @N0.f(c = "miui.systemui.util.TalkBackUtils", f = "TalkBackUtils.kt", l = {32}, m = "getTalkBackEnabled")
    public static final class AnonymousClass1 extends N0.d {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(L0.d dVar) {
            super(dVar);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TalkBackUtils.this.getTalkBackEnabled(this);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.util.TalkBackUtils$getTalkBackEnabled$2, reason: invalid class name */
    @N0.f(c = "miui.systemui.util.TalkBackUtils$getTalkBackEnabled$2", f = "TalkBackUtils.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends N0.l implements Function2 {
        int label;

        public AnonymousClass2(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return TalkBackUtils.this.new AnonymousClass2(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass2) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            TalkBackUtils talkBackUtils = TalkBackUtils.this;
            return N0.b.a(talkBackUtils.isTalkBackEnabled(talkBackUtils.context));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TalkBackUtils(Context context, @Main Handler mainHandler) {
        super(mainHandler);
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(mainHandler, "mainHandler");
        this.context = context;
        this.mainHandler = mainHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isTalkBackEnabled(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services");
        if (string == null) {
            return false;
        }
        return f1.o.v(string, TALKBACK_NAME_2, false, 2, null) | f1.o.v(string, TALKBACK_NAME_1, false, 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getTalkBackEnabled(L0.d r6) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r6 instanceof miui.systemui.util.TalkBackUtils.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            miui.systemui.util.TalkBackUtils$getTalkBackEnabled$1 r0 = (miui.systemui.util.TalkBackUtils.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            miui.systemui.util.TalkBackUtils$getTalkBackEnabled$1 r0 = new miui.systemui.util.TalkBackUtils$getTalkBackEnabled$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = M0.c.c()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r5 = r0.L$1
            miui.systemui.util.TalkBackUtils r5 = (miui.systemui.util.TalkBackUtils) r5
            java.lang.Object r0 = r0.L$0
            miui.systemui.util.TalkBackUtils r0 = (miui.systemui.util.TalkBackUtils) r0
            H0.k.b(r6)
            goto L5a
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            H0.k.b(r6)
            java.lang.Boolean r6 = r5.isTalkBackEnabled
            if (r6 != 0) goto L5f
            miui.systemui.coroutines.Dispatchers r6 = miui.systemui.coroutines.Dispatchers.INSTANCE
            g1.b0 r6 = r6.getIO()
            miui.systemui.util.TalkBackUtils$getTalkBackEnabled$2 r2 = new miui.systemui.util.TalkBackUtils$getTalkBackEnabled$2
            r4 = 0
            r2.<init>(r4)
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = g1.AbstractC0367f.c(r6, r2, r0)
            if (r6 != r1) goto L59
            return r1
        L59:
            r0 = r5
        L5a:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            r5.isTalkBackEnabled = r6
            r5 = r0
        L5f:
            java.lang.Boolean r5 = r5.isTalkBackEnabled
            java.lang.Boolean r6 = N0.b.a(r3)
            boolean r5 = kotlin.jvm.internal.n.c(r5, r6)
            java.lang.Boolean r5 = N0.b.a(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.util.TalkBackUtils.getTalkBackEnabled(L0.d):java.lang.Object");
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z2) {
        super.onChange(z2);
        this.isTalkBackEnabled = null;
    }

    public final void register() {
        Uri uriFor = Settings.Secure.getUriFor("enabled_accessibility_services");
        kotlin.jvm.internal.n.f(uriFor, "getUriFor(...)");
        this.context.getContentResolver().registerContentObserver(uriFor, false, this);
    }

    public final void unregister() {
        this.context.getContentResolver().unregisterContentObserver(this);
        this.isTalkBackEnabled = null;
    }
}
