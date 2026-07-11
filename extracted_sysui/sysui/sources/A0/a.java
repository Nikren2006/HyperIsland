package A0;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static a f28e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f30b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Method f31c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f29a = 10;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f32d = 0;

    public a() {
        try {
            Class<?> cls = Class.forName("miui.scenariorecognition.ScenarioRecognitionManager");
            this.f30b = cls.getDeclaredMethod("getInstance", null).invoke(null, null);
            Class cls2 = Long.TYPE;
            this.f31c = cls.getDeclaredMethod("setScenarioState", cls2, cls2, Boolean.TYPE);
        } catch (Exception unused) {
            System.out.println("CoreScenarioRecognition reflect fail");
            this.f32d++;
        }
    }

    public static synchronized a a() {
        try {
            if (f28e == null) {
                f28e = new a();
            }
        } catch (Throwable th) {
            throw th;
        }
        return f28e;
    }

    public void b(long j2, long j3, boolean z2) {
        Method method;
        if (this.f32d > this.f29a) {
            return;
        }
        Object obj = this.f30b;
        if (obj == null || (method = this.f31c) == null) {
            System.out.println("CoreScenarioRecognition instance is null!");
            this.f32d++;
        } else {
            try {
                method.invoke(obj, Long.valueOf(j2), Long.valueOf(j3), Boolean.valueOf(z2));
            } catch (Exception unused) {
                System.out.println("CoreScenarioRecognition reflect fail");
                this.f32d++;
            }
        }
    }
}
