package androidx.constraintlayout.core.dsl;

import androidx.constraintlayout.core.dsl.Chain;
import androidx.constraintlayout.core.dsl.Constraint;
import androidx.constraintlayout.core.dsl.Helper;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class HChain extends Chain {
    private HAnchor mEnd;
    private HAnchor mLeft;
    private HAnchor mRight;
    private HAnchor mStart;

    public class HAnchor extends Chain.Anchor {
        public HAnchor(Constraint.HSide hSide) {
            super(Constraint.Side.valueOf(hSide.name()));
        }
    }

    public HChain(String str) {
        super(str);
        this.mLeft = new HAnchor(Constraint.HSide.LEFT);
        this.mRight = new HAnchor(Constraint.HSide.RIGHT);
        this.mStart = new HAnchor(Constraint.HSide.START);
        this.mEnd = new HAnchor(Constraint.HSide.END);
        this.type = new Helper.HelperType(Helper.typeMap.get(Helper.Type.HORIZONTAL_CHAIN));
    }

    public HAnchor getEnd() {
        return this.mEnd;
    }

    public HAnchor getLeft() {
        return this.mLeft;
    }

    public HAnchor getRight() {
        return this.mRight;
    }

    public HAnchor getStart() {
        return this.mStart;
    }

    public void linkToEnd(Constraint.HAnchor hAnchor) {
        linkToEnd(hAnchor, 0);
    }

    public void linkToLeft(Constraint.HAnchor hAnchor) {
        linkToLeft(hAnchor, 0);
    }

    public void linkToRight(Constraint.HAnchor hAnchor) {
        linkToRight(hAnchor, 0);
    }

    public void linkToStart(Constraint.HAnchor hAnchor) {
        linkToStart(hAnchor, 0);
    }

    public void linkToEnd(Constraint.HAnchor hAnchor, int i2) {
        linkToEnd(hAnchor, i2, Integer.MIN_VALUE);
    }

    public void linkToLeft(Constraint.HAnchor hAnchor, int i2) {
        linkToLeft(hAnchor, i2, Integer.MIN_VALUE);
    }

    public void linkToRight(Constraint.HAnchor hAnchor, int i2) {
        linkToRight(hAnchor, i2, Integer.MIN_VALUE);
    }

    public void linkToStart(Constraint.HAnchor hAnchor, int i2) {
        linkToStart(hAnchor, i2, Integer.MIN_VALUE);
    }

    public void linkToEnd(Constraint.HAnchor hAnchor, int i2, int i3) {
        HAnchor hAnchor2 = this.mEnd;
        hAnchor2.mConnection = hAnchor;
        hAnchor2.mMargin = i2;
        hAnchor2.mGoneMargin = i3;
        this.configMap.put("end", hAnchor2.toString());
    }

    public void linkToLeft(Constraint.HAnchor hAnchor, int i2, int i3) {
        HAnchor hAnchor2 = this.mLeft;
        hAnchor2.mConnection = hAnchor;
        hAnchor2.mMargin = i2;
        hAnchor2.mGoneMargin = i3;
        this.configMap.put("left", hAnchor2.toString());
    }

    public void linkToRight(Constraint.HAnchor hAnchor, int i2, int i3) {
        HAnchor hAnchor2 = this.mRight;
        hAnchor2.mConnection = hAnchor;
        hAnchor2.mMargin = i2;
        hAnchor2.mGoneMargin = i3;
        this.configMap.put("right", hAnchor2.toString());
    }

    public void linkToStart(Constraint.HAnchor hAnchor, int i2, int i3) {
        HAnchor hAnchor2 = this.mStart;
        hAnchor2.mConnection = hAnchor;
        hAnchor2.mMargin = i2;
        hAnchor2.mGoneMargin = i3;
        this.configMap.put("start", hAnchor2.toString());
    }

    public HChain(String str, String str2) {
        super(str);
        this.mLeft = new HAnchor(Constraint.HSide.LEFT);
        this.mRight = new HAnchor(Constraint.HSide.RIGHT);
        this.mStart = new HAnchor(Constraint.HSide.START);
        this.mEnd = new HAnchor(Constraint.HSide.END);
        this.config = str2;
        this.type = new Helper.HelperType(Helper.typeMap.get(Helper.Type.HORIZONTAL_CHAIN));
        Map<String, String> mapConvertConfigToMap = convertConfigToMap();
        this.configMap = mapConvertConfigToMap;
        if (mapConvertConfigToMap.containsKey("contains")) {
            Ref.addStringToReferences(this.configMap.get("contains"), this.references);
        }
    }
}
