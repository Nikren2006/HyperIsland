package z0;

/* JADX INFO: loaded from: classes2.dex */
public class h {

    public interface a {
        void invoke();
    }

    public interface b {
        Object invoke();
    }

    public Object a(String str, String str2, Object obj, b bVar) {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            Object objInvoke = bVar.invoke();
            e.c("SafeBinderCall", str2 + " binder cost:" + (System.currentTimeMillis() - jCurrentTimeMillis));
            return objInvoke;
        } catch (Exception e2) {
            e.b(str, str2, e2);
            return obj;
        }
    }

    public void b(String str, String str2, a aVar) {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            aVar.invoke();
            e.c("SafeBinderCall", str2 + " binder cost:" + (System.currentTimeMillis() - jCurrentTimeMillis));
        } catch (Exception e2) {
            e.b(str, str2, e2);
        }
    }
}
