package com.android.systemui.miui.volume.widget;

import android.widget.ImageView;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.animation.drawable.VectorDrawableSetParams;
import miuix.animation.property.FloatProperty;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaIconAnim$property$2 extends o implements Function0 {
    final /* synthetic */ MediaIconAnim this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaIconAnim$property$2(MediaIconAnim mediaIconAnim) {
        super(0);
        this.this$0 = mediaIconAnim;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.miui.volume.widget.MediaIconAnim$property$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        final MediaIconAnim mediaIconAnim = this.this$0;
        return new FloatProperty<ImageView>() { // from class: com.android.systemui.miui.volume.widget.MediaIconAnim$property$2.1
            {
                super("vp_media_icon");
            }

            @Override // miuix.animation.property.FloatProperty
            public float getValue(ImageView icon) {
                n.g(icon, "icon");
                if (mediaIconAnim.mediaIconAnimParams == null) {
                    return 0.0f;
                }
                VectorDrawableSetParams vectorDrawableSetParams = mediaIconAnim.mediaIconAnimParams;
                n.d(vectorDrawableSetParams);
                return vectorDrawableSetParams.getFraction();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(ImageView icon, float f2) {
                n.g(icon, "icon");
                if (mediaIconAnim.mediaIconAnimParams != null) {
                    VectorDrawableSetParams vectorDrawableSetParams = mediaIconAnim.mediaIconAnimParams;
                    n.d(vectorDrawableSetParams);
                    vectorDrawableSetParams.setFraction(f2);
                    icon.invalidate();
                }
            }
        };
    }
}
