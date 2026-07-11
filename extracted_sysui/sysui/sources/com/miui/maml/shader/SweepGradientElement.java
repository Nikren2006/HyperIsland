package com.miui.maml.shader;

import android.graphics.SweepGradient;
import com.miui.maml.ScreenElementRoot;
import com.miui.maml.data.Expression;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes2.dex */
public class SweepGradientElement extends ShaderElement {
    public static final String TAG_NAME = "SweepGradient";
    private float mAngle;
    private Expression mAngleExp;

    public SweepGradientElement(Element element, ScreenElementRoot screenElementRoot) {
        super(element, screenElementRoot);
        this.mAngleExp = Expression.build(getVariables(), element.getAttribute("rotation"));
        this.mGradientStops.update();
    }

    private final float getAngle() {
        Expression expression = this.mAngleExp;
        if (expression != null) {
            return (float) expression.evaluate();
        }
        return 0.0f;
    }

    @Override // com.miui.maml.shader.ShaderElement
    public void onGradientStopsChanged() {
        this.mX = 0.0f;
        this.mY = 0.0f;
        this.mAngle = 0.0f;
        this.mShader = new SweepGradient(0.0f, 0.0f, this.mGradientStops.getColors(), this.mGradientStops.getPositions());
    }

    @Override // com.miui.maml.shader.ShaderElement
    public boolean updateShaderMatrix() {
        float x2 = getX();
        float y2 = getY();
        float angle = getAngle();
        if (x2 == this.mX && y2 == this.mY && angle == this.mAngle) {
            return false;
        }
        this.mX = x2;
        this.mY = y2;
        this.mAngle = angle;
        this.mShaderMatrix.reset();
        this.mShaderMatrix.preTranslate(-x2, -y2);
        this.mShaderMatrix.setRotate(angle);
        this.mShaderMatrix.postTranslate(x2, y2);
        return true;
    }
}
