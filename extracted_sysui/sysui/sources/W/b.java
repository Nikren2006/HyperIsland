package W;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Properties;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Type[] f760a = new Type[0];

    public static final class a implements GenericArrayType, Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Type f761a;

        public a(Type type) {
            Objects.requireNonNull(type);
            this.f761a = b.b(type);
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && b.f(this, (GenericArrayType) obj);
        }

        @Override // java.lang.reflect.GenericArrayType
        public Type getGenericComponentType() {
            return this.f761a;
        }

        public int hashCode() {
            return this.f761a.hashCode();
        }

        public String toString() {
            return b.t(this.f761a) + "[]";
        }
    }

    /* JADX INFO: renamed from: W.b$b, reason: collision with other inner class name */
    public static final class C0022b implements ParameterizedType, Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Type f762a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Type f763b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Type[] f764c;

        public C0022b(Type type, Type type2, Type... typeArr) {
            Objects.requireNonNull(type2);
            if (type2 instanceof Class) {
                Class cls = (Class) type2;
                boolean z2 = true;
                boolean z3 = Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null;
                if (type == null && !z3) {
                    z2 = false;
                }
                W.a.a(z2);
            }
            this.f762a = type == null ? null : b.b(type);
            this.f763b = b.b(type2);
            Type[] typeArr2 = (Type[]) typeArr.clone();
            this.f764c = typeArr2;
            int length = typeArr2.length;
            for (int i2 = 0; i2 < length; i2++) {
                Objects.requireNonNull(this.f764c[i2]);
                b.c(this.f764c[i2]);
                Type[] typeArr3 = this.f764c;
                typeArr3[i2] = b.b(typeArr3[i2]);
            }
        }

        public static int a(Object obj) {
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && b.f(this, (ParameterizedType) obj);
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return (Type[]) this.f764c.clone();
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return this.f762a;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return this.f763b;
        }

        public int hashCode() {
            return a(this.f762a) ^ (Arrays.hashCode(this.f764c) ^ this.f763b.hashCode());
        }

        public String toString() {
            int length = this.f764c.length;
            if (length == 0) {
                return b.t(this.f763b);
            }
            StringBuilder sb = new StringBuilder((length + 1) * 30);
            sb.append(b.t(this.f763b));
            sb.append("<");
            sb.append(b.t(this.f764c[0]));
            for (int i2 = 1; i2 < length; i2++) {
                sb.append(", ");
                sb.append(b.t(this.f764c[i2]));
            }
            sb.append(">");
            return sb.toString();
        }
    }

    public static final class c implements WildcardType, Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Type f765a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Type f766b;

        public c(Type[] typeArr, Type[] typeArr2) {
            W.a.a(typeArr2.length <= 1);
            W.a.a(typeArr.length == 1);
            if (typeArr2.length != 1) {
                Objects.requireNonNull(typeArr[0]);
                b.c(typeArr[0]);
                this.f766b = null;
                this.f765a = b.b(typeArr[0]);
                return;
            }
            Objects.requireNonNull(typeArr2[0]);
            b.c(typeArr2[0]);
            W.a.a(typeArr[0] == Object.class);
            this.f766b = b.b(typeArr2[0]);
            this.f765a = Object.class;
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && b.f(this, (WildcardType) obj);
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getLowerBounds() {
            Type type = this.f766b;
            return type != null ? new Type[]{type} : b.f760a;
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getUpperBounds() {
            return new Type[]{this.f765a};
        }

        public int hashCode() {
            Type type = this.f766b;
            return (this.f765a.hashCode() + 31) ^ (type != null ? type.hashCode() + 31 : 1);
        }

        public String toString() {
            if (this.f766b != null) {
                return "? super " + b.t(this.f766b);
            }
            if (this.f765a == Object.class) {
                return "?";
            }
            return "? extends " + b.t(this.f765a);
        }
    }

    public static GenericArrayType a(Type type) {
        return new a(type);
    }

    public static Type b(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            return cls.isArray() ? new a(b(cls.getComponentType())) : cls;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new C0022b(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            return new a(((GenericArrayType) type).getGenericComponentType());
        }
        if (!(type instanceof WildcardType)) {
            return type;
        }
        WildcardType wildcardType = (WildcardType) type;
        return new c(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
    }

    public static void c(Type type) {
        W.a.a(((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true);
    }

    public static Class d(TypeVariable typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class) genericDeclaration;
        }
        return null;
    }

    public static boolean e(Object obj, Object obj2) {
        return Objects.equals(obj, obj2);
    }

    public static boolean f(Type type, Type type2) {
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            return e(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            if (type2 instanceof GenericArrayType) {
                return f(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
            }
            return false;
        }
        if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            return Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds());
        }
        if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        }
        TypeVariable typeVariable = (TypeVariable) type;
        TypeVariable typeVariable2 = (TypeVariable) type2;
        return typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName());
    }

    public static Type g(Type type) {
        return type instanceof GenericArrayType ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
    }

    public static Type h(Type type, Class cls) {
        Type typeL = l(type, cls, Collection.class);
        return typeL instanceof ParameterizedType ? ((ParameterizedType) typeL).getActualTypeArguments()[0] : Object.class;
    }

    public static Type i(Type type, Class cls, Class cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i2 = 0; i2 < length; i2++) {
                Class<?> cls3 = interfaces[i2];
                if (cls3 == cls2) {
                    return cls.getGenericInterfaces()[i2];
                }
                if (cls2.isAssignableFrom(cls3)) {
                    return i(cls.getGenericInterfaces()[i2], interfaces[i2], cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<?> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return i(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    public static Type[] j(Type type, Class cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type typeL = l(type, cls, Map.class);
        return typeL instanceof ParameterizedType ? ((ParameterizedType) typeL).getActualTypeArguments() : new Type[]{Object.class, Object.class};
    }

    public static Class k(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            W.a.a(rawType instanceof Class);
            return (Class) rawType;
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance((Class<?>) k(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return k(((WildcardType) type).getUpperBounds()[0]);
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
    }

    public static Type l(Type type, Class cls, Class cls2) {
        if (type instanceof WildcardType) {
            type = ((WildcardType) type).getUpperBounds()[0];
        }
        W.a.a(cls2.isAssignableFrom(cls));
        return o(type, cls, i(type, cls, cls2));
    }

    public static int m(Object[] objArr, Object obj) {
        int length = objArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (obj.equals(objArr[i2])) {
                return i2;
            }
        }
        throw new NoSuchElementException();
    }

    public static ParameterizedType n(Type type, Type type2, Type... typeArr) {
        return new C0022b(type, type2, typeArr);
    }

    public static Type o(Type type, Class cls, Type type2) {
        return p(type, cls, type2, new HashMap());
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x00dc, code lost:
    
        if (r0 == null) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00de, code lost:
    
        r12.put(r0, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00e1, code lost:
    
        return r11;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004c  */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r11v10, types: [java.lang.Object, java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r11v13, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r11v2, types: [java.lang.reflect.WildcardType] */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.lang.reflect.WildcardType] */
    /* JADX WARN: Type inference failed for: r11v4, types: [java.lang.reflect.WildcardType] */
    /* JADX WARN: Type inference failed for: r11v5, types: [java.lang.reflect.ParameterizedType] */
    /* JADX WARN: Type inference failed for: r11v6, types: [java.lang.reflect.GenericArrayType] */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.reflect.Type p(java.lang.reflect.Type r9, java.lang.Class r10, java.lang.reflect.Type r11, java.util.Map r12) {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: W.b.p(java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type, java.util.Map):java.lang.reflect.Type");
    }

    public static Type q(Type type, Class cls, TypeVariable typeVariable) {
        Class clsD = d(typeVariable);
        if (clsD == null) {
            return typeVariable;
        }
        Type typeI = i(type, cls, clsD);
        if (!(typeI instanceof ParameterizedType)) {
            return typeVariable;
        }
        return ((ParameterizedType) typeI).getActualTypeArguments()[m(clsD.getTypeParameters(), typeVariable)];
    }

    public static WildcardType r(Type type) {
        return new c(type instanceof WildcardType ? ((WildcardType) type).getUpperBounds() : new Type[]{type}, f760a);
    }

    public static WildcardType s(Type type) {
        return new c(new Type[]{Object.class}, type instanceof WildcardType ? ((WildcardType) type).getLowerBounds() : new Type[]{type});
    }

    public static String t(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }
}
