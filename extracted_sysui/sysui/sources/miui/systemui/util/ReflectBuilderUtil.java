package miui.systemui.util;

import android.os.UserHandle;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes4.dex */
public class ReflectBuilderUtil {

    public static class ReflAgent {
        private Class mClass;
        private Object mResult;

        private ReflAgent() {
        }

        public static ReflAgent getClass(String str) {
            ReflAgent reflAgent = new ReflAgent();
            try {
                reflAgent.mClass = Class.forName(str);
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
            return reflAgent;
        }

        public ReflAgent callStatic(String str, Class<?>[] clsArr, Object... objArr) {
            Class cls = this.mClass;
            if (cls != null) {
                try {
                    this.mResult = ReflectBuilderUtil.callStaticObjectMethod(cls, str, clsArr, objArr);
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                } catch (NoSuchMethodException e3) {
                    e3.printStackTrace();
                } catch (InvocationTargetException e4) {
                    e4.printStackTrace();
                }
            }
            return this;
        }

        public ReflAgent getStaticFiled(String str) {
            Class cls = this.mClass;
            if (cls != null) {
                try {
                    this.mResult = ReflectBuilderUtil.getStaticObjectField(cls, str);
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                } catch (NoSuchFieldException e3) {
                    e3.printStackTrace();
                }
            }
            return this;
        }

        public int intResult() {
            Object obj = this.mResult;
            if (obj == null) {
                return 0;
            }
            return ((Integer) obj).intValue();
        }
    }

    public static Object callObjectMethod(Object obj, String str, Class<?>[] clsArr, Object... objArr) {
        return obj.getClass().getMethod(str, clsArr).invoke(obj, objArr);
    }

    public static Object callStaticObjectMethod(Class<?> cls, String str, Class<?>[] clsArr, Object... objArr) throws NoSuchMethodException {
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(null, objArr);
    }

    public static int getCurrentUserId() {
        return ReflAgent.getClass("miui.securityspace.CrossUserUtils").callStatic("getCurrentUserId", null, new Object[0]).intResult();
    }

    public static Object getStaticObjectField(Class<?> cls, String str) throws NoSuchFieldException {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(null);
    }

    public static UserHandle getUserHandle(int i2) {
        try {
            return (UserHandle) UserHandle.class.getConstructor(Integer.TYPE).newInstance(Integer.valueOf(i2));
        } catch (Exception unused) {
            return null;
        }
    }
}
