package d0;

import android.content.Context;
import android.util.Log;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: d0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0320a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f3969a = "/system_ext/framework/MiuiBooster.jar";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Method f3970b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Method f3971c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Method f3972d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Method f3973e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Method f3974f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Method f3975g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Method f3976h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Method f3977i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Method f3978j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final Method f3979k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final Method f3980l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Method f3981m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Method f3982n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final Method f3983o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Method f3984p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final Method f3985q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final Method f3986r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final Method f3987s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final Method f3988t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final Class f3989u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final Class f3990v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final PathClassLoader f3991w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final Constructor f3992x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final Object f3993y;

    static {
        f3970b = null;
        f3971c = null;
        f3972d = null;
        f3973e = null;
        f3974f = null;
        f3975g = null;
        f3976h = null;
        f3977i = null;
        f3978j = null;
        f3979k = null;
        f3980l = null;
        f3981m = null;
        f3982n = null;
        f3983o = null;
        f3984p = null;
        f3985q = null;
        f3986r = null;
        f3987s = null;
        f3988t = null;
        f3990v = null;
        f3992x = null;
        f3993y = null;
        try {
            PathClassLoader pathClassLoader = new PathClassLoader(f3969a, ClassLoader.getSystemClassLoader());
            f3991w = pathClassLoader;
            Class<?> clsLoadClass = pathClassLoader.loadClass("com.miui.performance.MiuiBooster");
            f3989u = clsLoadClass;
            f3992x = clsLoadClass.getConstructor(null);
            try {
                f3970b = clsLoadClass.getDeclaredMethod("checkPermission", String.class, Integer.TYPE);
            } catch (Exception unused) {
                Log.e("MiBridge", "checkPermission no exit");
            }
            try {
                f3990v = f3991w.loadClass("com.miui.performance.IThermalEventCallBack");
            } catch (Exception unused2) {
                Log.e("MiBridge", "com.miui.performance.IThermalEventCallBack not exits!");
            }
            try {
                f3971c = f3989u.getDeclaredMethod("checkPermission", Context.class, String.class, Integer.TYPE, String.class);
            } catch (Exception unused3) {
                Log.e("MiBridge", "checkPermission_debug no exit");
            }
            try {
                Class cls = Integer.TYPE;
                f3972d = f3989u.getDeclaredMethod("requestCpuHighFreq", cls, cls, cls);
            } catch (Exception unused4) {
                Log.e("MiBridge", "requestCpuHighFreq no exit");
            }
            try {
                f3973e = f3989u.getDeclaredMethod("cancelCpuHighFreq", Integer.TYPE);
            } catch (Exception unused5) {
                Log.e("MiBridge", "cancelCpuHighFreq no exit");
            }
            try {
                Class cls2 = Integer.TYPE;
                f3974f = f3989u.getDeclaredMethod("requestThreadPriority", cls2, cls2, cls2);
            } catch (Exception unused6) {
                Log.e("MiBridge", "requestThreadPriority no exit");
            }
            try {
                Class cls3 = Integer.TYPE;
                f3975g = f3989u.getDeclaredMethod("cancelThreadPriority", cls3, cls3);
            } catch (Exception unused7) {
                Log.e("MiBridge", "cancelThreadPriority no exit");
            }
            try {
                Class cls4 = Integer.TYPE;
                f3976h = f3989u.getDeclaredMethod("requestGpuHighFreq", cls4, cls4, cls4);
            } catch (Exception unused8) {
                Log.e("MiBridge", "requestGpuHighFreq no exit");
            }
            try {
                f3977i = f3989u.getDeclaredMethod("cancelGpuHighFreq", Integer.TYPE);
            } catch (Exception unused9) {
                Log.e("MiBridge", "cancelGpuHighFreq no exit");
            }
            try {
                Class cls5 = Integer.TYPE;
                f3979k = f3989u.getDeclaredMethod("requestDdrHighFreq", cls5, cls5, cls5);
            } catch (Exception unused10) {
                Log.e("MiBridge", "requestDdrHighFreq no exit");
            }
            try {
                f3980l = f3989u.getDeclaredMethod("cancelDdrHighFreq", Integer.TYPE);
            } catch (Exception unused11) {
                Log.e("MiBridge", "cancelDdrHighFreq no exit");
            }
            try {
                Class cls6 = Integer.TYPE;
                f3981m = f3989u.getDeclaredMethod("requestBindCore", cls6, int[].class, cls6, cls6);
            } catch (Exception unused12) {
                Log.e("MiBridge", "requestBindCore no exit");
            }
            try {
                Class cls7 = Integer.TYPE;
                f3982n = f3989u.getDeclaredMethod("cancelBindCore", cls7, cls7);
            } catch (Exception unused13) {
                Log.e("MiBridge", "cancelBindCore no exit");
            }
            try {
                f3978j = f3989u.getDeclaredMethod("requestIOPrefetch", Integer.TYPE, String.class);
            } catch (Exception unused14) {
                Log.e("MiBridge", "requestIOPrefetch no exit");
            }
            try {
                Class cls8 = Integer.TYPE;
                f3983o = f3989u.getDeclaredMethod("getSystemState", cls8, Context.class, cls8);
            } catch (Exception unused15) {
                Log.e("MiBridge", "getSystemState no exit");
            }
            try {
                Class cls9 = Integer.TYPE;
                f3986r = f3989u.getDeclaredMethod("setDynamicRefreshRateScene", cls9, String.class, cls9);
            } catch (Exception unused16) {
                Log.e("MiBridge", "setDynamicRefreshRateScene no exit");
            }
            try {
                f3984p = f3989u.getDeclaredMethod("registerThermalEventCallback", Integer.TYPE, f3990v);
            } catch (Exception e2) {
                Log.e("MiBridge", "registerThermalEventCallback no exit, " + e2);
            }
            try {
                f3985q = f3989u.getDeclaredMethod("unRegisterThermalEventCallback", Integer.TYPE, f3990v);
            } catch (Exception unused17) {
                Log.e("MiBridge", "UnRegisterThermalEventCallback no exit");
            }
            try {
                Class cls10 = Integer.TYPE;
                f3987s = f3989u.getDeclaredMethod("requestThreadLevelPro", cls10, int[].class, cls10, cls10);
            } catch (Exception unused18) {
                Log.e("MiBridge", "requestThreadLevelPro no exit");
            }
            try {
                Class cls11 = Integer.TYPE;
                f3988t = f3989u.getDeclaredMethod("cancelThreadLevelPro", cls11, cls11);
            } catch (Exception unused19) {
                Log.e("MiBridge", "requestThreadLevelPro no exit");
            }
        } catch (Exception e3) {
            Log.e("MiBridge", "MiBridge() : Load Class Exception: " + e3);
        }
        try {
            Constructor constructor = f3992x;
            if (constructor != null) {
                f3993y = constructor.newInstance(null);
            }
        } catch (Exception e4) {
            Log.e("MiBridge", "MiBridge() : newInstance Exception:" + e4);
        }
    }

    public static boolean a(String str, int i2) {
        try {
            return ((Boolean) f3970b.invoke(f3993y, str, Integer.valueOf(i2))).booleanValue();
        } catch (Exception e2) {
            AbstractC0321b.a(e2, new StringBuilder("check permission failed , e:"), "MiBridge");
            return false;
        }
    }

    public static int b(int i2, int[] iArr, int i3, int i4) {
        try {
            return ((Integer) f3981m.invoke(f3993y, Integer.valueOf(i2), iArr, Integer.valueOf(i3), Integer.valueOf(i4))).intValue();
        } catch (Exception e2) {
            AbstractC0321b.a(e2, new StringBuilder("request BindCore failed , e:"), "MiBridge");
            return -1;
        }
    }

    public static int c(int i2, int i3, int i4) {
        try {
            return ((Integer) f3972d.invoke(f3993y, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4))).intValue();
        } catch (Exception e2) {
            AbstractC0321b.a(e2, new StringBuilder("request cpu high failed , e:"), "MiBridge");
            return -1;
        }
    }

    public static int d(int i2, int i3, int i4) {
        try {
            return ((Integer) f3976h.invoke(f3993y, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4))).intValue();
        } catch (Exception e2) {
            AbstractC0321b.a(e2, new StringBuilder("request Gpu high failed , e:"), "MiBridge");
            return -1;
        }
    }

    public static int e(int i2, int[] iArr, int i3, int i4) {
        try {
            return ((Integer) f3987s.invoke(f3993y, Integer.valueOf(i2), iArr, Integer.valueOf(i3), Integer.valueOf(i4))).intValue();
        } catch (Exception e2) {
            AbstractC0321b.a(e2, new StringBuilder("set request thread level failed , e:"), "MiBridge");
            return -1;
        }
    }
}
