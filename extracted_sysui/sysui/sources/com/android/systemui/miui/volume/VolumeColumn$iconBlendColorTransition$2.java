package com.android.systemui.miui.volume;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import miui.systemui.util.MiBlurCompat;
import miuix.theme.token.ColorBlendToken;

/* JADX INFO: loaded from: classes2.dex */
public final class VolumeColumn$iconBlendColorTransition$2 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ VolumeColumn this$0;

    /* JADX INFO: renamed from: com.android.systemui.miui.volume.VolumeColumn$iconBlendColorTransition$2$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function3 {
        final /* synthetic */ VolumeColumn this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(VolumeColumn volumeColumn) {
            super(3);
            this.this$0 = volumeColumn;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((ColorBlendToken) obj, (ColorBlendToken) obj2, ((Number) obj3).floatValue());
            return H0.s.f314a;
        }

        public final void invoke(ColorBlendToken from, ColorBlendToken to, float f2) {
            kotlin.jvm.internal.n.g(from, "from");
            kotlin.jvm.internal.n.g(to, "to");
            Util.setMiViewBlurAndBlendColor(this.this$0.getIcon(), 3, MiBlurCompat.INSTANCE.translateTo(from, to, f2));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VolumeColumn$iconBlendColorTransition$2(VolumeColumn volumeColumn) {
        super(0);
        this.this$0 = volumeColumn;
    }

    @Override // kotlin.jvm.functions.Function0
    public final MiuiColorTransitionAnim<ColorBlendToken> invoke() {
        return new MiuiColorTransitionAnim<>(new AnonymousClass1(this.this$0));
    }
}
