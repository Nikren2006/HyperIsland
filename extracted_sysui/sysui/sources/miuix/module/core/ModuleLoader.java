package miuix.module.core;

import android.content.Context;
import android.content.pm.PackageManager;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ModuleLoader {
    private Context mBaseContext;
    private ClassLoader mClassLoader;
    private Map<Class<?>, String> mClasses;
    private Context mModuleContext;

    public Context getBaseContext() {
        return this.mBaseContext;
    }

    public ClassLoader getClassLoader() {
        return this.mClassLoader;
    }

    public Context getModuleContext() {
        return this.mModuleContext;
    }

    public <I> I instantiate(Class<I> cls) {
        return (I) instantiate(cls, new Class[0], new Object[0]);
    }

    private ModuleLoader(Context context, Map<Class<?>, String> map, Context context2, String[] strArr) {
        this.mBaseContext = context;
        this.mModuleContext = context2;
        this.mClasses = map;
        DependencyLoader dependencyLoader = new DependencyLoader(context.getClassLoader());
        dependencyLoader.add(context2.getClassLoader(), strArr);
        Iterator<Map.Entry<Class<?>, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            dependencyLoader.ignore(it.next().getValue());
        }
        this.mClassLoader = ContextHelper.cloneClassLoader(context, dependencyLoader);
    }

    public <I> I instantiate(Class<I> cls, Class<?>[] clsArr, Object[] objArr) {
        try {
            String str = this.mClasses.get(cls);
            if (str != null) {
                return (I) this.mClassLoader.loadClass(str).asSubclass(cls).getConstructor(clsArr).newInstance(objArr);
            }
            throw new IllegalArgumentException("no implementation for " + cls);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static class Builder {
        private Context mBaseContext;
        private Context mModuleContext;
        private String mPackageName;
        private Set<String> mDependencies = new HashSet();
        private Map<Class<?>, String> mClasses = new HashMap();

        public Builder(Context context, String str) {
            this.mBaseContext = context;
            this.mPackageName = str;
        }

        public ModuleLoader build() {
            Context contextCreatePackageContext = this.mModuleContext;
            if (contextCreatePackageContext == null) {
                try {
                    contextCreatePackageContext = this.mBaseContext.createPackageContext(this.mPackageName, 3);
                } catch (PackageManager.NameNotFoundException e2) {
                    throw new RuntimeException(e2);
                }
            }
            return new ModuleLoader(this.mBaseContext, this.mClasses, contextCreatePackageContext, (String[]) this.mDependencies.toArray(new String[0]));
        }

        public Builder register(Class<?> cls, String str) {
            this.mClasses.put(cls, str);
            return this;
        }

        public Builder require(String str) {
            this.mDependencies.add(str);
            return this;
        }

        public Builder(Context context, Context context2) {
            this.mBaseContext = context;
            this.mModuleContext = context2;
        }
    }
}
