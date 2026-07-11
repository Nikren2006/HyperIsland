package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class StateSet {
    private static final boolean DEBUG = false;
    public static final String TAG = "ConstraintLayoutStates";
    int mDefaultState = -1;
    int mCurrentStateId = -1;
    int mCurrentConstraintNumber = -1;
    private SparseArray<State> mStateList = new SparseArray<>();
    private ConstraintsChangedListener mConstraintsChangedListener = null;

    public static class State {
        int mConstraintID;
        int mId;
        boolean mIsLayout;
        ArrayList<Variant> mVariants = new ArrayList<>();

        public State(Context context, XmlPullParser xmlPullParser) {
            this.mConstraintID = -1;
            this.mIsLayout = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.State);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.State_android_id) {
                    this.mId = typedArrayObtainStyledAttributes.getResourceId(index, this.mId);
                } else if (index == R.styleable.State_constraints) {
                    this.mConstraintID = typedArrayObtainStyledAttributes.getResourceId(index, this.mConstraintID);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(resourceTypeName)) {
                        this.mIsLayout = true;
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public void add(Variant variant) {
            this.mVariants.add(variant);
        }

        public int findMatch(float f2, float f3) {
            for (int i2 = 0; i2 < this.mVariants.size(); i2++) {
                if (this.mVariants.get(i2).match(f2, f3)) {
                    return i2;
                }
            }
            return -1;
        }
    }

    public static class Variant {
        int mConstraintID;
        int mId;
        boolean mIsLayout;
        float mMaxHeight;
        float mMaxWidth;
        float mMinHeight;
        float mMinWidth;

        public Variant(Context context, XmlPullParser xmlPullParser) {
            this.mMinWidth = Float.NaN;
            this.mMinHeight = Float.NaN;
            this.mMaxWidth = Float.NaN;
            this.mMaxHeight = Float.NaN;
            this.mConstraintID = -1;
            this.mIsLayout = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.Variant_constraints) {
                    this.mConstraintID = typedArrayObtainStyledAttributes.getResourceId(index, this.mConstraintID);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(resourceTypeName)) {
                        this.mIsLayout = true;
                    }
                } else if (index == R.styleable.Variant_region_heightLessThan) {
                    this.mMaxHeight = typedArrayObtainStyledAttributes.getDimension(index, this.mMaxHeight);
                } else if (index == R.styleable.Variant_region_heightMoreThan) {
                    this.mMinHeight = typedArrayObtainStyledAttributes.getDimension(index, this.mMinHeight);
                } else if (index == R.styleable.Variant_region_widthLessThan) {
                    this.mMaxWidth = typedArrayObtainStyledAttributes.getDimension(index, this.mMaxWidth);
                } else if (index == R.styleable.Variant_region_widthMoreThan) {
                    this.mMinWidth = typedArrayObtainStyledAttributes.getDimension(index, this.mMinWidth);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public boolean match(float f2, float f3) {
            if (!Float.isNaN(this.mMinWidth) && f2 < this.mMinWidth) {
                return false;
            }
            if (!Float.isNaN(this.mMinHeight) && f3 < this.mMinHeight) {
                return false;
            }
            if (Float.isNaN(this.mMaxWidth) || f2 <= this.mMaxWidth) {
                return Float.isNaN(this.mMaxHeight) || f3 <= this.mMaxHeight;
            }
            return false;
        }
    }

    public StateSet(Context context, XmlPullParser xmlPullParser) {
        load(context, xmlPullParser);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:39:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void load(android.content.Context r11, org.xmlpull.v1.XmlPullParser r12) {
        /*
            r10 = this;
            java.lang.String r0 = "Error parsing XML resource"
            java.lang.String r1 = "ConstraintLayoutStates"
            android.util.AttributeSet r2 = android.util.Xml.asAttributeSet(r12)
            int[] r3 = androidx.constraintlayout.widget.R.styleable.StateSet
            android.content.res.TypedArray r2 = r11.obtainStyledAttributes(r2, r3)
            int r3 = r2.getIndexCount()
            r4 = 0
            r5 = r4
        L14:
            if (r5 >= r3) goto L29
            int r6 = r2.getIndex(r5)
            int r7 = androidx.constraintlayout.widget.R.styleable.StateSet_defaultState
            if (r6 != r7) goto L26
            int r7 = r10.mDefaultState
            int r6 = r2.getResourceId(r6, r7)
            r10.mDefaultState = r6
        L26:
            int r5 = r5 + 1
            goto L14
        L29:
            r2.recycle()
            int r2 = r12.getEventType()     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            r3 = 0
        L31:
            r5 = 1
            if (r2 == r5) goto La7
            java.lang.String r6 = "StateSet"
            r7 = 3
            r8 = 2
            if (r2 == r8) goto L4d
            if (r2 == r7) goto L3e
            goto L9b
        L3e:
            java.lang.String r2 = r12.getName()     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            boolean r2 = r6.equals(r2)     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            if (r2 == 0) goto L9b
            return
        L49:
            r10 = move-exception
            goto La0
        L4b:
            r10 = move-exception
            goto La4
        L4d:
            java.lang.String r2 = r12.getName()     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            int r9 = r2.hashCode()     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            switch(r9) {
                case 80204913: goto L74;
                case 1301459538: goto L6a;
                case 1382829617: goto L63;
                case 1901439077: goto L59;
                default: goto L58;
            }     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
        L58:
            goto L7e
        L59:
            java.lang.String r5 = "Variant"
            boolean r2 = r2.equals(r5)     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            if (r2 == 0) goto L7e
            r5 = r7
            goto L7f
        L63:
            boolean r2 = r2.equals(r6)     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            if (r2 == 0) goto L7e
            goto L7f
        L6a:
            java.lang.String r5 = "LayoutDescription"
            boolean r2 = r2.equals(r5)     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            if (r2 == 0) goto L7e
            r5 = r4
            goto L7f
        L74:
            java.lang.String r5 = "State"
            boolean r2 = r2.equals(r5)     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            if (r2 == 0) goto L7e
            r5 = r8
            goto L7f
        L7e:
            r5 = -1
        L7f:
            if (r5 == r8) goto L8f
            if (r5 == r7) goto L84
            goto L9b
        L84:
            androidx.constraintlayout.widget.StateSet$Variant r2 = new androidx.constraintlayout.widget.StateSet$Variant     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            r2.<init>(r11, r12)     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            if (r3 == 0) goto L9b
            r3.add(r2)     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            goto L9b
        L8f:
            androidx.constraintlayout.widget.StateSet$State r3 = new androidx.constraintlayout.widget.StateSet$State     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            r3.<init>(r11, r12)     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            android.util.SparseArray<androidx.constraintlayout.widget.StateSet$State> r2 = r10.mStateList     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            int r5 = r3.mId     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            r2.put(r5, r3)     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
        L9b:
            int r2 = r12.next()     // Catch: java.io.IOException -> L49 org.xmlpull.v1.XmlPullParserException -> L4b
            goto L31
        La0:
            android.util.Log.e(r1, r0, r10)
            goto La7
        La4:
            android.util.Log.e(r1, r0, r10)
        La7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.StateSet.load(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public int convertToConstraintSet(int i2, int i3, float f2, float f3) {
        State state = this.mStateList.get(i3);
        if (state == null) {
            return i3;
        }
        if (f2 == -1.0f || f3 == -1.0f) {
            if (state.mConstraintID == i2) {
                return i2;
            }
            Iterator<Variant> it = state.mVariants.iterator();
            while (it.hasNext()) {
                if (i2 == it.next().mConstraintID) {
                    return i2;
                }
            }
            return state.mConstraintID;
        }
        Variant variant = null;
        for (Variant variant2 : state.mVariants) {
            if (variant2.match(f2, f3)) {
                if (i2 == variant2.mConstraintID) {
                    return i2;
                }
                variant = variant2;
            }
        }
        return variant != null ? variant.mConstraintID : state.mConstraintID;
    }

    public boolean needsToChange(int i2, float f2, float f3) {
        int i3 = this.mCurrentStateId;
        if (i3 != i2) {
            return true;
        }
        State stateValueAt = i2 == -1 ? this.mStateList.valueAt(0) : this.mStateList.get(i3);
        int i4 = this.mCurrentConstraintNumber;
        return (i4 == -1 || !stateValueAt.mVariants.get(i4).match(f2, f3)) && this.mCurrentConstraintNumber != stateValueAt.findMatch(f2, f3);
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener) {
        this.mConstraintsChangedListener = constraintsChangedListener;
    }

    public int stateGetConstraintID(int i2, int i3, int i4) {
        return updateConstraints(-1, i2, i3, i4);
    }

    public int updateConstraints(int i2, int i3, float f2, float f3) {
        int iFindMatch;
        if (i2 == i3) {
            State stateValueAt = i3 == -1 ? this.mStateList.valueAt(0) : this.mStateList.get(this.mCurrentStateId);
            if (stateValueAt == null) {
                return -1;
            }
            return ((this.mCurrentConstraintNumber == -1 || !stateValueAt.mVariants.get(i2).match(f2, f3)) && i2 != (iFindMatch = stateValueAt.findMatch(f2, f3))) ? iFindMatch == -1 ? stateValueAt.mConstraintID : stateValueAt.mVariants.get(iFindMatch).mConstraintID : i2;
        }
        State state = this.mStateList.get(i3);
        if (state == null) {
            return -1;
        }
        int iFindMatch2 = state.findMatch(f2, f3);
        return iFindMatch2 == -1 ? state.mConstraintID : state.mVariants.get(iFindMatch2).mConstraintID;
    }
}
