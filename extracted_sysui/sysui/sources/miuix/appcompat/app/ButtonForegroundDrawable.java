package miuix.appcompat.app;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import java.io.IOException;
import miuix.appcompat.R;
import miuix.core.util.RomUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class ButtonForegroundDrawable extends AlphaBlendingDrawable {
    private void init(Resources resources, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray typedArrayObtainStyledAttributes = theme != null ? theme.obtainStyledAttributes(attributeSet, R.styleable.AdaptRoundButtonDrawable, 0, 0) : resources.obtainAttributes(attributeSet, R.styleable.AdaptRoundButtonDrawable);
        float f2 = resources.getDisplayMetrics().density;
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.AdaptRoundButtonDrawable_buttonRadius, (int) (16.0f * f2));
        int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.AdaptRoundButtonDrawable_buttonCapsuleRadius, (int) (f2 * 36.0f));
        typedArrayObtainStyledAttributes.recycle();
        if (RomUtils.isMiuiXVSdkSupported()) {
            setRadius(dimensionPixelSize);
        } else {
            setRadius(dimensionPixelSize2);
        }
    }

    @Override // miuix.appcompat.app.AlphaBlendingDrawable, android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        init(resources, attributeSet, theme);
    }
}
