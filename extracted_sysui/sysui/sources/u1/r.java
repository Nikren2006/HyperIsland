package u1;

import com.miui.maml.elements.ScreenElementArray;

/* JADX INFO: loaded from: classes2.dex */
public final class r extends j {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f6894c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(s1.c primitive) {
        super(primitive, null);
        kotlin.jvm.internal.n.g(primitive, "primitive");
        this.f6894c = primitive.h() + ScreenElementArray.TAG_NAME;
    }

    @Override // s1.c
    public String h() {
        return this.f6894c;
    }
}
