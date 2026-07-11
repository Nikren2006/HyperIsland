package androidx.constraintlayout.core.parser;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class CLKey extends CLContainer {
    private static ArrayList<String> sSections;

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        sSections = arrayList;
        arrayList.add("ConstraintSets");
        sSections.add("Variables");
        sSections.add("Generate");
        sSections.add(TypedValues.TransitionType.NAME);
        sSections.add("KeyFrames");
        sSections.add(TypedValues.AttributesType.NAME);
        sSections.add("KeyPositions");
        sSections.add("KeyCycles");
    }

    public CLKey(char[] cArr) {
        super(cArr);
    }

    public static CLElement allocate(char[] cArr) {
        return new CLKey(cArr);
    }

    @Override // androidx.constraintlayout.core.parser.CLContainer, androidx.constraintlayout.core.parser.CLElement
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CLKey) || Objects.equals(getName(), ((CLKey) obj).getName())) {
            return super.equals(obj);
        }
        return false;
    }

    public String getName() {
        return content();
    }

    public CLElement getValue() {
        if (this.mElements.size() > 0) {
            return this.mElements.get(0);
        }
        return null;
    }

    @Override // androidx.constraintlayout.core.parser.CLContainer, androidx.constraintlayout.core.parser.CLElement
    public int hashCode() {
        return super.hashCode();
    }

    public void set(CLElement cLElement) {
        if (this.mElements.size() > 0) {
            this.mElements.set(0, cLElement);
        } else {
            this.mElements.add(cLElement);
        }
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toFormattedJSON(int i2, int i3) {
        StringBuilder sb = new StringBuilder(getDebugName());
        addIndent(sb, i2);
        String strContent = content();
        if (this.mElements.size() <= 0) {
            return strContent + ": <> ";
        }
        sb.append(strContent);
        sb.append(": ");
        if (sSections.contains(strContent)) {
            i3 = 3;
        }
        if (i3 > 0) {
            sb.append(this.mElements.get(0).toFormattedJSON(i2, i3 - 1));
        } else {
            String json = this.mElements.get(0).toJSON();
            if (json.length() + i2 < CLElement.sMaxLine) {
                sb.append(json);
            } else {
                sb.append(this.mElements.get(0).toFormattedJSON(i2, i3 - 1));
            }
        }
        return sb.toString();
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toJSON() {
        if (this.mElements.size() <= 0) {
            return getDebugName() + content() + ": <> ";
        }
        return getDebugName() + content() + ": " + this.mElements.get(0).toJSON();
    }

    public static CLElement allocate(String str, CLElement cLElement) {
        CLKey cLKey = new CLKey(str.toCharArray());
        cLKey.setStart(0L);
        cLKey.setEnd(str.length() - 1);
        cLKey.set(cLElement);
        return cLKey;
    }
}
