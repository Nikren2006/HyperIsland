package miuix.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.ViewGroup;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.transition.MiuixTransitionManager;
import androidx.transition.Scene;
import com.xiaomi.onetrack.api.ah;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes5.dex */
public class TransitionInflater {
    private static final Class<?>[] sConstructorSignature = {Context.class, AttributeSet.class};
    private static final ArrayMap<String, Constructor> sConstructors = new ArrayMap<>();
    private Context mContext;

    private TransitionInflater(Context context) {
        this.mContext = context;
    }

    private Object createCustom(AttributeSet attributeSet, Class cls, String str) {
        Object objNewInstance;
        Class<? extends U> clsAsSubclass;
        String attributeValue = attributeSet.getAttributeValue(null, ah.f2838r);
        if (attributeValue == null) {
            throw new InflateException(str + " tag must have a 'class' attribute");
        }
        try {
            ArrayMap<String, Constructor> arrayMap = sConstructors;
            synchronized (arrayMap) {
                try {
                    Constructor constructor = arrayMap.get(attributeValue);
                    if (constructor == null && (clsAsSubclass = this.mContext.getClassLoader().loadClass(attributeValue).asSubclass(cls)) != 0) {
                        constructor = clsAsSubclass.getConstructor(sConstructorSignature);
                        constructor.setAccessible(true);
                        arrayMap.put(attributeValue, constructor);
                    }
                    objNewInstance = constructor != null ? constructor.newInstance(this.mContext, attributeSet) : null;
                } finally {
                }
            }
            return objNewInstance;
        } catch (ClassNotFoundException e2) {
            throw new InflateException("Could not instantiate " + cls + " class " + attributeValue, e2);
        } catch (IllegalAccessException e3) {
            throw new InflateException("Could not instantiate " + cls + " class " + attributeValue, e3);
        } catch (InstantiationException e4) {
            throw new InflateException("Could not instantiate " + cls + " class " + attributeValue, e4);
        } catch (NoSuchMethodException e5) {
            throw new InflateException("Could not instantiate " + cls + " class " + attributeValue, e5);
        } catch (InvocationTargetException e6) {
            throw new InflateException("Could not instantiate " + cls + " class " + attributeValue, e6);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x00dd, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private miuix.transition.MiuixTransition createTransitionFromXml(org.xmlpull.v1.XmlPullParser r8, android.util.AttributeSet r9, miuix.transition.MiuixTransition r10) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.transition.TransitionInflater.createTransitionFromXml(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, miuix.transition.MiuixTransition):miuix.transition.MiuixTransition");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0054, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private androidx.transition.MiuixTransitionManager createTransitionManagerFromXml(org.xmlpull.v1.XmlPullParser r5, android.util.AttributeSet r6, android.view.ViewGroup r7) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r4 = this;
            int r0 = r5.getDepth()
            r1 = 0
        L5:
            int r2 = r5.next()
            r3 = 3
            if (r2 != r3) goto L12
            int r3 = r5.getDepth()
            if (r3 <= r0) goto L54
        L12:
            r3 = 1
            if (r2 == r3) goto L54
            r3 = 2
            if (r2 == r3) goto L19
            goto L5
        L19:
            java.lang.String r2 = r5.getName()
            java.lang.String r3 = "transitionManager"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L2b
            androidx.transition.MiuixTransitionManager r1 = new androidx.transition.MiuixTransitionManager
            r1.<init>()
            goto L5
        L2b:
            java.lang.String r3 = "transition"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L39
            if (r1 == 0) goto L39
            r4.loadTransition(r6, r7, r1)
            goto L5
        L39:
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Unknown scene name: "
            r6.append(r7)
            java.lang.String r5 = r5.getName()
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            r4.<init>(r5)
            throw r4
        L54:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.transition.TransitionInflater.createTransitionManagerFromXml(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.ViewGroup):androidx.transition.MiuixTransitionManager");
    }

    public static TransitionInflater from(Context context) {
        return new TransitionInflater(context);
    }

    private void getTargetIds(XmlPullParser xmlPullParser, AttributeSet attributeSet, MiuixTransition miuixTransition) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if ((next == 3 && xmlPullParser.getDepth() <= depth) || next == 1) {
                return;
            }
            if (next == 2) {
                if (!xmlPullParser.getName().equals(TypedValues.AttributesType.S_TARGET)) {
                    throw new RuntimeException("Unknown scene name: " + xmlPullParser.getName());
                }
                TypedArray typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.TransitionTarget);
                int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.TransitionTarget_targetId, 0);
                if (resourceId != 0) {
                    miuixTransition.addTarget(resourceId);
                } else {
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.TransitionTarget_excludeId, 0);
                    if (resourceId2 != 0) {
                        miuixTransition.excludeTarget(resourceId2, true);
                    } else {
                        String string = typedArrayObtainStyledAttributes.getString(R.styleable.TransitionTarget_targetName);
                        if (string != null) {
                            miuixTransition.addTarget(string);
                        } else {
                            String string2 = typedArrayObtainStyledAttributes.getString(R.styleable.TransitionTarget_excludeName);
                            if (string2 != null) {
                                miuixTransition.excludeTarget(string2, true);
                            } else {
                                String string3 = typedArrayObtainStyledAttributes.getString(R.styleable.TransitionTarget_excludeClass);
                                if (string3 != null) {
                                    try {
                                        miuixTransition.excludeTarget(Class.forName(string3), true);
                                    } catch (ClassNotFoundException e2) {
                                        typedArrayObtainStyledAttributes.recycle();
                                        throw new RuntimeException("Could not create " + string3, e2);
                                    }
                                } else {
                                    String string4 = typedArrayObtainStyledAttributes.getString(R.styleable.TransitionTarget_targetClass);
                                    if (string4 != null) {
                                        miuixTransition.addTarget(Class.forName(string4));
                                    }
                                }
                            }
                        }
                    }
                }
                typedArrayObtainStyledAttributes.recycle();
            }
        }
    }

    private void loadTransition(AttributeSet attributeSet, ViewGroup viewGroup, MiuixTransitionManager miuixTransitionManager) {
        MiuixTransition miuixTransitionInflateTransition;
        TypedArray typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.TransitionManager);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.TransitionManager_transition, -1);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.TransitionManager_fromScene, -1);
        Scene sceneForLayout = resourceId2 < 0 ? null : Scene.getSceneForLayout(viewGroup, resourceId2, this.mContext);
        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.TransitionManager_toScene, -1);
        Scene sceneForLayout2 = resourceId3 >= 0 ? Scene.getSceneForLayout(viewGroup, resourceId3, this.mContext) : null;
        if (resourceId >= 0 && (miuixTransitionInflateTransition = inflateTransition(resourceId)) != null) {
            if (sceneForLayout2 == null) {
                throw new RuntimeException("No toScene for transition ID " + resourceId);
            }
            if (sceneForLayout == null) {
                miuixTransitionManager.setTransition(sceneForLayout2, miuixTransitionInflateTransition);
            } else {
                miuixTransitionManager.setTransition(sceneForLayout, sceneForLayout2, miuixTransitionInflateTransition);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public MiuixTransition inflateTransition(int i2) {
        XmlResourceParser xml = this.mContext.getResources().getXml(i2);
        try {
            try {
                return createTransitionFromXml(xml, Xml.asAttributeSet(xml), null);
            } catch (IOException e2) {
                InflateException inflateException = new InflateException(xml.getPositionDescription() + ": " + e2.getMessage());
                inflateException.initCause(e2);
                throw inflateException;
            } catch (XmlPullParserException e3) {
                InflateException inflateException2 = new InflateException(e3.getMessage());
                inflateException2.initCause(e3);
                throw inflateException2;
            }
        } finally {
            xml.close();
        }
    }

    public MiuixTransitionManager inflateTransitionManager(int i2, ViewGroup viewGroup) {
        XmlResourceParser xml = this.mContext.getResources().getXml(i2);
        try {
            try {
                return createTransitionManagerFromXml(xml, Xml.asAttributeSet(xml), viewGroup);
            } catch (IOException e2) {
                InflateException inflateException = new InflateException(xml.getPositionDescription() + ": " + e2.getMessage());
                inflateException.initCause(e2);
                throw inflateException;
            } catch (XmlPullParserException e3) {
                InflateException inflateException2 = new InflateException(e3.getMessage());
                inflateException2.initCause(e3);
                throw inflateException2;
            }
        } finally {
            xml.close();
        }
    }
}
