package androidx.constraintlayout.core.parser;

import androidx.annotation.NonNull;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class CLObject extends CLContainer implements Iterable<CLKey> {

    public static class CLObjectIterator implements Iterator<CLKey> {
        int mIndex = 0;
        CLObject mObject;

        public CLObjectIterator(CLObject cLObject) {
            this.mObject = cLObject;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.mIndex < this.mObject.size();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public CLKey next() {
            CLKey cLKey = (CLKey) this.mObject.mElements.get(this.mIndex);
            this.mIndex++;
            return cLKey;
        }
    }

    public CLObject(char[] cArr) {
        super(cArr);
    }

    public static CLObject allocate(char[] cArr) {
        return new CLObject(cArr);
    }

    @Override // java.lang.Iterable
    public Iterator<CLKey> iterator() {
        return new CLObjectIterator(this);
    }

    public String toFormattedJSON() {
        return toFormattedJSON(0, 0);
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toJSON() {
        StringBuilder sb = new StringBuilder(getDebugName() + "{ ");
        boolean z2 = true;
        for (CLElement cLElement : this.mElements) {
            if (z2) {
                z2 = false;
            } else {
                sb.append(", ");
            }
            sb.append(cLElement.toJSON());
        }
        sb.append(" }");
        return sb.toString();
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toFormattedJSON(int i2, int i3) {
        StringBuilder sb = new StringBuilder(getDebugName());
        sb.append("{\n");
        boolean z2 = true;
        for (CLElement cLElement : this.mElements) {
            if (z2) {
                z2 = false;
            } else {
                sb.append(",\n");
            }
            sb.append(cLElement.toFormattedJSON(CLElement.sBaseIndent + i2, i3 - 1));
        }
        sb.append("\n");
        addIndent(sb, i2);
        sb.append("}");
        return sb.toString();
    }

    @Override // androidx.constraintlayout.core.parser.CLContainer, androidx.constraintlayout.core.parser.CLElement
    @NonNull
    /* JADX INFO: renamed from: clone */
    public CLObject mo34clone() {
        return (CLObject) super.mo34clone();
    }
}
