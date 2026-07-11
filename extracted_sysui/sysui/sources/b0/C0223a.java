package b0;

import W.b;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: renamed from: b0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0223a {
    private final int hashCode;
    private final Class<Object> rawType;
    private final Type type;

    public C0223a() {
        Type typeB = b();
        this.type = typeB;
        this.rawType = b.k(typeB);
        this.hashCode = typeB.hashCode();
    }

    public static AssertionError a(Type type, Class... clsArr) {
        StringBuilder sb = new StringBuilder("Unexpected type. Expected one of: ");
        for (Class cls : clsArr) {
            sb.append(cls.getName());
            sb.append(", ");
        }
        sb.append("but got: ");
        sb.append(type.getClass().getName());
        sb.append(", for type token: ");
        sb.append(type.toString());
        sb.append('.');
        return new AssertionError(sb.toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r1v9 */
    public static boolean c(Type type, GenericArrayType genericArrayType) {
        Type genericComponentType = genericArrayType.getGenericComponentType();
        if (!(genericComponentType instanceof ParameterizedType)) {
            return true;
        }
        if (type instanceof GenericArrayType) {
            type = ((GenericArrayType) type).getGenericComponentType();
        } else if (type instanceof Class) {
            type = (Class) type;
            while (type.isArray()) {
                type = type.getComponentType();
            }
        }
        return d(type, (ParameterizedType) genericComponentType, new HashMap());
    }

    public static boolean d(Type type, ParameterizedType parameterizedType, Map map) {
        if (type == null) {
            return false;
        }
        if (parameterizedType.equals(type)) {
            return true;
        }
        Class clsK = b.k(type);
        ParameterizedType parameterizedType2 = type instanceof ParameterizedType ? (ParameterizedType) type : null;
        if (parameterizedType2 != null) {
            Type[] actualTypeArguments = parameterizedType2.getActualTypeArguments();
            TypeVariable[] typeParameters = clsK.getTypeParameters();
            for (int i2 = 0; i2 < actualTypeArguments.length; i2++) {
                Type type2 = actualTypeArguments[i2];
                TypeVariable typeVariable = typeParameters[i2];
                while (type2 instanceof TypeVariable) {
                    type2 = (Type) map.get(((TypeVariable) type2).getName());
                }
                map.put(typeVariable.getName(), type2);
            }
            if (f(parameterizedType2, parameterizedType, map)) {
                return true;
            }
        }
        for (Type type3 : clsK.getGenericInterfaces()) {
            if (d(type3, parameterizedType, new HashMap(map))) {
                return true;
            }
        }
        return d(clsK.getGenericSuperclass(), parameterizedType, new HashMap(map));
    }

    public static boolean e(Type type, Type type2, Map map) {
        return type2.equals(type) || ((type instanceof TypeVariable) && type2.equals(map.get(((TypeVariable) type).getName())));
    }

    public static boolean f(ParameterizedType parameterizedType, ParameterizedType parameterizedType2, Map map) {
        if (!parameterizedType.getRawType().equals(parameterizedType2.getRawType())) {
            return false;
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Type[] actualTypeArguments2 = parameterizedType2.getActualTypeArguments();
        for (int i2 = 0; i2 < actualTypeArguments.length; i2++) {
            if (!e(actualTypeArguments[i2], actualTypeArguments2[i2], map)) {
                return false;
            }
        }
        return true;
    }

    public static C0223a get(Type type) {
        return new C0223a(type);
    }

    public static C0223a getArray(Type type) {
        return new C0223a(b.a(type));
    }

    public static C0223a getParameterized(Type type, Type... typeArr) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(typeArr);
        if (!(type instanceof Class)) {
            throw new IllegalArgumentException("rawType must be of type Class, but was " + type);
        }
        Class cls = (Class) type;
        TypeVariable[] typeParameters = cls.getTypeParameters();
        int length = typeParameters.length;
        int length2 = typeArr.length;
        if (length2 != length) {
            throw new IllegalArgumentException(cls.getName() + " requires " + length + " type arguments, but got " + length2);
        }
        for (int i2 = 0; i2 < length; i2++) {
            Type type2 = typeArr[i2];
            Class<?> clsK = b.k(type2);
            TypeVariable typeVariable = typeParameters[i2];
            for (Type type3 : typeVariable.getBounds()) {
                if (!b.k(type3).isAssignableFrom(clsK)) {
                    throw new IllegalArgumentException("Type argument " + type2 + " does not satisfy bounds for type variable " + typeVariable + " declared by " + type);
                }
            }
        }
        return new C0223a(b.n(null, type, typeArr));
    }

    public final Type b() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            if (parameterizedType.getRawType() == C0223a.class) {
                return b.b(parameterizedType.getActualTypeArguments()[0]);
            }
        } else if (genericSuperclass == C0223a.class) {
            throw new IllegalStateException("TypeToken must be created with a type argument: new TypeToken<...>() {}; When using code shrinkers (ProGuard, R8, ...) make sure that generic signatures are preserved.");
        }
        throw new IllegalStateException("Must only create direct subclasses of TypeToken");
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C0223a) && b.f(this.type, ((C0223a) obj).type);
    }

    public final Class<Object> getRawType() {
        return this.rawType;
    }

    public final Type getType() {
        return this.type;
    }

    public final int hashCode() {
        return this.hashCode;
    }

    @Deprecated
    public boolean isAssignableFrom(Class<?> cls) {
        return isAssignableFrom((Type) cls);
    }

    public final String toString() {
        return b.t(this.type);
    }

    public static <T> C0223a get(Class<T> cls) {
        return new C0223a(cls);
    }

    @Deprecated
    public boolean isAssignableFrom(Type type) {
        if (type == null) {
            return false;
        }
        if (this.type.equals(type)) {
            return true;
        }
        Type type2 = this.type;
        if (type2 instanceof Class) {
            return this.rawType.isAssignableFrom(b.k(type));
        }
        if (type2 instanceof ParameterizedType) {
            return d(type, (ParameterizedType) type2, new HashMap());
        }
        if (type2 instanceof GenericArrayType) {
            return this.rawType.isAssignableFrom(b.k(type)) && c(type, (GenericArrayType) this.type);
        }
        throw a(type2, Class.class, ParameterizedType.class, GenericArrayType.class);
    }

    public C0223a(Type type) {
        Objects.requireNonNull(type);
        Type typeB = b.b(type);
        this.type = typeB;
        this.rawType = b.k(typeB);
        this.hashCode = typeB.hashCode();
    }

    @Deprecated
    public boolean isAssignableFrom(C0223a c0223a) {
        return isAssignableFrom(c0223a.getType());
    }
}
