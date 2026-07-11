package U0;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import d1.InterfaceC0324c;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {
    public static final Class a(InterfaceC0324c interfaceC0324c) {
        n.g(interfaceC0324c, "<this>");
        Class clsB = ((e) interfaceC0324c).b();
        n.e(clsB, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        return clsB;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Class b(InterfaceC0324c interfaceC0324c) {
        n.g(interfaceC0324c, "<this>");
        Class clsB = ((e) interfaceC0324c).b();
        if (!clsB.isPrimitive()) {
            n.e(clsB, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaObjectType>>");
            return clsB;
        }
        String name = clsB.getName();
        switch (name.hashCode()) {
            case -1325958191:
                if (name.equals("double")) {
                    clsB = Double.class;
                }
                break;
            case 104431:
                if (name.equals("int")) {
                    clsB = Integer.class;
                }
                break;
            case 3039496:
                if (name.equals("byte")) {
                    clsB = Byte.class;
                }
                break;
            case 3052374:
                if (name.equals("char")) {
                    clsB = Character.class;
                }
                break;
            case 3327612:
                if (name.equals("long")) {
                    clsB = Long.class;
                }
                break;
            case 3625364:
                if (name.equals("void")) {
                    clsB = Void.class;
                }
                break;
            case 64711720:
                if (name.equals(TypedValues.Custom.S_BOOLEAN)) {
                    clsB = Boolean.class;
                }
                break;
            case 97526364:
                if (name.equals(TypedValues.Custom.S_FLOAT)) {
                    clsB = Float.class;
                }
                break;
            case 109413500:
                if (name.equals("short")) {
                    clsB = Short.class;
                }
                break;
        }
        n.e(clsB, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaObjectType>>");
        return clsB;
    }
}
