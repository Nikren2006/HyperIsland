package systemui.plugin.eventtracking.utils;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import com.xiaomi.onetrack.OnMainThreadException;
import com.xiaomi.onetrack.OneTrack;
import g1.AbstractC0369g;
import g1.C0366e0;
import g1.E;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.coroutines.Dispatchers;

/* JADX INFO: loaded from: classes5.dex */
public final class OaidUtils {
    public static final OaidUtils INSTANCE = new OaidUtils();
    private static final String TAG = "OaidUtils";
    private static String sOAID;

    /* JADX INFO: renamed from: systemui.plugin.eventtracking.utils.OaidUtils$initOaid$1, reason: invalid class name */
    @f(c = "systemui.plugin.eventtracking.utils.OaidUtils$initOaid$1", f = "OaidUtils.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ Context $context;
        final /* synthetic */ OneTrack $oneTrack;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(OneTrack oneTrack, Context context, d dVar) {
            super(2, dVar);
            this.$oneTrack = oneTrack;
            this.$context = context;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return new AnonymousClass1(this.$oneTrack, this.$context, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            try {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                OaidUtils oaidUtils = OaidUtils.INSTANCE;
                OaidUtils.sOAID = this.$oneTrack.getOAID(this.$context);
                Log.i(OaidUtils.INSTANCE.getTAG(), "oaidutils cost:" + (SystemClock.elapsedRealtime() - jElapsedRealtime));
            } catch (OnMainThreadException e2) {
                e2.printStackTrace();
            }
            return s.f314a;
        }
    }

    private OaidUtils() {
    }

    public final String getOaid() {
        return sOAID;
    }

    public final String getTAG() {
        return TAG;
    }

    public final synchronized void initOaid(Context context, OneTrack oneTrack) {
        n.g(oneTrack, "oneTrack");
        AbstractC0369g.b(C0366e0.f4417a, Dispatchers.INSTANCE.getIO(), null, new AnonymousClass1(oneTrack, context, null), 2, null);
    }
}
