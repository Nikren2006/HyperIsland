package miui.systemui.animation.drawable;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
public final class SVGUtilsExt$supportVectorDrawableParams$2 extends o implements Function0 {
    public static final SVGUtilsExt$supportVectorDrawableParams$2 INSTANCE = new SVGUtilsExt$supportVectorDrawableParams$2();

    public SVGUtilsExt$supportVectorDrawableParams$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Boolean invoke() {
        SVGUtilsExt sVGUtilsExt = SVGUtilsExt.INSTANCE;
        return Boolean.valueOf((sVGUtilsExt.getVectorDrawableParamsClass() == null || sVGUtilsExt.getGetAnimatorSetMethod() == null || sVGUtilsExt.getGetVectorDrawableMethod() == null) ? false : true);
    }
}
