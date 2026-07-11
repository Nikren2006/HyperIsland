package g;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class o extends g {

    public class a extends com.airbnb.lottie.value.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ com.airbnb.lottie.value.b f4305a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ com.airbnb.lottie.value.c f4306b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ i.b f4307c;

        public a(com.airbnb.lottie.value.b bVar, com.airbnb.lottie.value.c cVar, i.b bVar2) {
            this.f4305a = bVar;
            this.f4306b = cVar;
            this.f4307c = bVar2;
        }

        @Override // com.airbnb.lottie.value.c
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public i.b getValue(com.airbnb.lottie.value.b bVar) {
            this.f4305a.h(bVar.f(), bVar.a(), ((i.b) bVar.g()).f4498a, ((i.b) bVar.b()).f4498a, bVar.d(), bVar.c(), bVar.e());
            String str = (String) this.f4306b.getValue(this.f4305a);
            i.b bVar2 = (i.b) (bVar.c() == 1.0f ? bVar.b() : bVar.g());
            this.f4307c.a(str, bVar2.f4499b, bVar2.f4500c, bVar2.f4501d, bVar2.f4502e, bVar2.f4503f, bVar2.f4504g, bVar2.f4505h, bVar2.f4506i, bVar2.f4507j, bVar2.f4508k);
            return this.f4307c;
        }
    }

    public o(List list) {
        super(list);
    }

    @Override // g.AbstractC0355a
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public i.b i(com.airbnb.lottie.value.a aVar, float f2) {
        Object obj;
        com.airbnb.lottie.value.c cVar = this.f4265e;
        if (cVar == null) {
            return (f2 != 1.0f || (obj = aVar.f1394c) == null) ? (i.b) aVar.f1393b : (i.b) obj;
        }
        float f3 = aVar.f1398g;
        Float f4 = aVar.f1399h;
        float fFloatValue = f4 == null ? Float.MAX_VALUE : f4.floatValue();
        Object obj2 = aVar.f1393b;
        i.b bVar = (i.b) obj2;
        Object obj3 = aVar.f1394c;
        return (i.b) cVar.getValueInternal(f3, fFloatValue, bVar, obj3 == null ? (i.b) obj2 : (i.b) obj3, f2, d(), f());
    }

    public void q(com.airbnb.lottie.value.c cVar) {
        super.n(new a(new com.airbnb.lottie.value.b(), cVar, new i.b()));
    }
}
