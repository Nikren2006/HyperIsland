package com.android.systemui.miui.volume;

import android.content.res.ColorStateList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import miui.systemui.util.MiBlurCompat;

/* JADX INFO: loaded from: classes2.dex */
public final class VolumeColumn$iconColorTransition$2 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ VolumeColumn this$0;

    /* JADX INFO: renamed from: com.android.systemui.miui.volume.VolumeColumn$iconColorTransition$2$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function3 {
        final /* synthetic */ VolumeColumn this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(VolumeColumn volumeColumn) {
            super(3);
            this.this$0 = volumeColumn;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((ColorStateList) obj, (ColorStateList) obj2, ((Number) obj3).floatValue());
            return H0.s.f314a;
        }

        public final void invoke(ColorStateList from, ColorStateList to, float f2) {
            kotlin.jvm.internal.n.g(from, "from");
            kotlin.jvm.internal.n.g(to, "to");
            this.this$0.getIcon().setImageTintList(ColorStateList.valueOf(MiBlurCompat.INSTANCE.colorTranslateTo(from.getDefaultColor(), to.getDefaultColor(), f2)));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VolumeColumn$iconColorTransition$2(VolumeColumn volumeColumn) {
        super(0);
        this.this$0 = volumeColumn;
    }

    @Override // kotlin.jvm.functions.Function0
    public final MiuiColorTransitionAnim<ColorStateList> invoke() {
        return new MiuiColorTransitionAnim<>(new AnonymousClass1(this.this$0));
    }
}
