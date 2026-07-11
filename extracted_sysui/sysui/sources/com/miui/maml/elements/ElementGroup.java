package com.miui.maml.elements;

import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.view.MotionEvent;
import com.miui.circulate.device.api.Constant;
import com.miui.maml.ScreenElementRoot;
import com.miui.maml.data.IndexedVariable;
import com.miui.maml.folme.AnimatedProperty;
import com.miui.maml.util.MamlLog;
import java.util.ArrayList;
import miuix.animation.FolmeEase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: classes2.dex */
public class ElementGroup extends AnimatedScreenElement {
    private static final String LOG_TAG = "MAML_ElementGroup";
    public static final String TAG_NAME = "ElementGroup";
    public static final String TAG_NAME1 = "Group";
    protected boolean mClip;
    protected ArrayList<ScreenElement> mElements;
    private boolean mHovered;
    private IndexedVariable mIndexVar;
    private boolean mLayered;
    private LinearDirection mLinearDirection;
    private boolean mTouched;

    /* JADX INFO: renamed from: com.miui.maml.elements.ElementGroup$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$maml$elements$ElementGroup$LinearDirection;

        static {
            int[] iArr = new int[LinearDirection.values().length];
            $SwitchMap$com$miui$maml$elements$ElementGroup$LinearDirection = iArr;
            try {
                iArr[LinearDirection.Horizontal.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$miui$maml$elements$ElementGroup$LinearDirection[LinearDirection.Vertical.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public enum LinearDirection {
        None,
        Horizontal,
        Vertical
    }

    public ElementGroup(Element element, ScreenElementRoot screenElementRoot) {
        super(element, screenElementRoot);
        this.mElements = new ArrayList<>();
        this.mLinearDirection = LinearDirection.None;
        load(element);
    }

    public static ElementGroup createArrayGroup(ScreenElementRoot screenElementRoot, IndexedVariable indexedVariable) {
        return new ElementGroup(screenElementRoot, indexedVariable);
    }

    public static boolean isArrayGroup(ScreenElement screenElement) {
        return (screenElement instanceof ElementGroup) && ((ElementGroup) screenElement).isArray();
    }

    private void load(Element element) {
        if (element == null) {
            return;
        }
        this.mClip = Boolean.parseBoolean(getAttr(element, "clip"));
        this.mLayered = Boolean.parseBoolean(getAttr(element, "layered"));
        String attr = getAttr(element, FolmeEase.LINEAR);
        if (AnimatedProperty.PROPERTY_NAME_H.equalsIgnoreCase(attr)) {
            this.mLinearDirection = LinearDirection.Horizontal;
        } else if (Constant.KeyValue.VALUE_COLUMN.equalsIgnoreCase(attr)) {
            this.mLinearDirection = LinearDirection.Vertical;
        }
        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        for (int i2 = 0; i2 < length; i2++) {
            if (childNodes.item(i2).getNodeType() == 1) {
                addElement(onCreateChild((Element) childNodes.item(i2)));
            }
        }
    }

    @Override // com.miui.maml.elements.ScreenElement
    public void acceptVisitor(ScreenElementVisitor screenElementVisitor) {
        super.acceptVisitor(screenElementVisitor);
        int size = this.mElements.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mElements.get(i2).acceptVisitor(screenElementVisitor);
        }
    }

    public void addElement(ScreenElement screenElement) {
        if (screenElement != null) {
            screenElement.setParent(this);
            this.mElements.add(screenElement);
        }
    }

    @Override // com.miui.maml.elements.ScreenElement
    public void doRender(Canvas canvas) {
        float width = getWidth();
        float height = getHeight();
        float left = getLeft(0.0f, width);
        float top = getTop(0.0f, height);
        int iSave = (!this.mLayered || width <= 0.0f || height <= 0.0f) ? canvas.save() : canvas.saveLayerAlpha(left, top, left + width, top + height, getAlpha(), 31);
        canvas.translate(left, top);
        if (width > 0.0f && height > 0.0f && this.mClip) {
            canvas.clipRect(0.0f, 0.0f, width, height);
        }
        doRenderChildren(canvas);
        canvas.restoreToCount(iSave);
    }

    public void doRenderChildren(Canvas canvas) {
        int size = this.mElements.size();
        for (int i2 = 0; i2 < size; i2++) {
            ScreenElement screenElement = this.mElements.get(i2);
            IndexedVariable indexedVariable = this.mIndexVar;
            if (indexedVariable != null) {
                indexedVariable.set(i2);
            }
            screenElement.render(canvas);
        }
    }

    @Override // com.miui.maml.elements.AnimatedScreenElement, com.miui.maml.elements.ScreenElement
    public void doTick(long j2) {
        super.doTick(j2);
        doTickChildren(j2);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005d A[PHI: r1 r4
      0x005d: PHI (r1v5 float) = (r1v4 float), (r1v7 float) binds: [B:22:0x0078, B:19:0x005b] A[DONT_GENERATE, DONT_INLINE]
      0x005d: PHI (r4v6 float) = (r4v5 float), (r4v11 float) binds: [B:22:0x0078, B:19:0x005b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void doTickChildren(long r11) {
        /*
            r10 = this;
            java.util.ArrayList<com.miui.maml.elements.ScreenElement> r0 = r10.mElements
            int r0 = r0.size()
            r1 = 0
            r2 = 0
            r3 = r2
            r2 = r1
        La:
            r4 = 2
            r5 = 1
            if (r3 >= r0) goto L7e
            java.util.ArrayList<com.miui.maml.elements.ScreenElement> r6 = r10.mElements
            java.lang.Object r6 = r6.get(r3)
            com.miui.maml.elements.ScreenElement r6 = (com.miui.maml.elements.ScreenElement) r6
            com.miui.maml.data.IndexedVariable r7 = r10.mIndexVar
            if (r7 == 0) goto L1e
            double r8 = (double) r3
            r7.set(r8)
        L1e:
            r6.tick(r11)
            com.miui.maml.elements.ElementGroup$LinearDirection r7 = r10.mLinearDirection
            com.miui.maml.elements.ElementGroup$LinearDirection r8 = com.miui.maml.elements.ElementGroup.LinearDirection.None
            if (r7 == r8) goto L7b
            boolean r7 = r6 instanceof com.miui.maml.elements.AnimatedScreenElement
            if (r7 == 0) goto L7b
            boolean r7 = r6.isVisible()
            if (r7 == 0) goto L7b
            com.miui.maml.elements.AnimatedScreenElement r6 = (com.miui.maml.elements.AnimatedScreenElement) r6
            int[] r7 = com.miui.maml.elements.ElementGroup.AnonymousClass1.$SwitchMap$com$miui$maml$elements$ElementGroup$LinearDirection
            com.miui.maml.elements.ElementGroup$LinearDirection r8 = r10.mLinearDirection
            int r8 = r8.ordinal()
            r7 = r7[r8]
            if (r7 == r5) goto L5f
            if (r7 == r4) goto L42
            goto L7b
        L42:
            float r4 = r6.getMarginTop()
            float r1 = r1 + r4
            double r4 = (double) r1
            r6.setY(r4)
            float r4 = r6.getHeight()
            float r5 = r6.getMarginBottom()
            float r4 = r4 + r5
            float r1 = r1 + r4
            float r4 = r6.getWidth()
            int r5 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r5 >= 0) goto L7b
        L5d:
            r2 = r4
            goto L7b
        L5f:
            float r4 = r6.getMarginLeft()
            float r1 = r1 + r4
            double r4 = (double) r1
            r6.setX(r4)
            float r4 = r6.getWidth()
            float r5 = r6.getMarginRight()
            float r4 = r4 + r5
            float r1 = r1 + r4
            float r4 = r6.getHeight()
            int r5 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r5 >= 0) goto L7b
            goto L5d
        L7b:
            int r3 = r3 + 1
            goto La
        L7e:
            int[] r11 = com.miui.maml.elements.ElementGroup.AnonymousClass1.$SwitchMap$com$miui$maml$elements$ElementGroup$LinearDirection
            com.miui.maml.elements.ElementGroup$LinearDirection r12 = r10.mLinearDirection
            int r12 = r12.ordinal()
            r11 = r11[r12]
            if (r11 == r5) goto La4
            if (r11 == r4) goto L8d
            goto Lba
        L8d:
            double r11 = (double) r1
            r10.setHeight(r11)
            double r0 = (double) r2
            r10.setWidth(r0)
            double r11 = r10.descale(r11)
            r10.setActualHeight(r11)
            double r11 = r10.descale(r0)
            r10.setActualWidth(r11)
            goto Lba
        La4:
            double r11 = (double) r1
            r10.setWidth(r11)
            double r0 = (double) r2
            r10.setHeight(r0)
            double r11 = r10.descale(r11)
            r10.setActualWidth(r11)
            double r11 = r10.descale(r0)
            r10.setActualHeight(r11)
        Lba:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.maml.elements.ElementGroup.doTickChildren(long):void");
    }

    @Override // com.miui.maml.elements.ScreenElement
    public ScreenElement findElement(String str) {
        ScreenElement screenElementFindElement = super.findElement(str);
        if (screenElementFindElement != null) {
            return screenElementFindElement;
        }
        int size = this.mElements.size();
        for (int i2 = 0; i2 < size; i2++) {
            ScreenElement screenElementFindElement2 = this.mElements.get(i2).findElement(str);
            if (screenElementFindElement2 != null) {
                return screenElementFindElement2;
            }
        }
        return null;
    }

    @Override // com.miui.maml.elements.AnimatedScreenElement, com.miui.maml.elements.ScreenElement
    public void finish() {
        super.finish();
        int size = this.mElements.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                this.mElements.get(i2).finish();
            } catch (Exception e2) {
                MamlLog.e(LOG_TAG, e2.toString());
                e2.printStackTrace();
            }
        }
    }

    public ScreenElement getChild(int i2) {
        if (i2 < 0 || i2 >= this.mElements.size()) {
            return null;
        }
        return this.mElements.get(i2);
    }

    public ArrayList<ScreenElement> getElements() {
        return this.mElements;
    }

    public float getParentLeft() {
        float left = getLeft();
        ElementGroup elementGroup = this.mParent;
        return left + (elementGroup == null ? 0.0f : elementGroup.getParentLeft());
    }

    public float getParentTop() {
        float top = getTop();
        ElementGroup elementGroup = this.mParent;
        return top + (elementGroup == null ? 0.0f : elementGroup.getParentTop());
    }

    public int getSize() {
        return this.mElements.size();
    }

    @Override // com.miui.maml.elements.AnimatedScreenElement, com.miui.maml.elements.ScreenElement
    public void init() {
        super.init();
        int size = this.mElements.size();
        for (int i2 = 0; i2 < size; i2++) {
            IndexedVariable indexedVariable = this.mIndexVar;
            if (indexedVariable != null) {
                indexedVariable.set(i2);
            }
            this.mElements.get(i2).init();
        }
    }

    public boolean isArray() {
        return this.mIndexVar != null;
    }

    public boolean isLayered() {
        return this.mLayered;
    }

    @Override // com.miui.maml.elements.ScreenElement
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int size = this.mElements.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mElements.get(i2).onConfigurationChanged(configuration);
        }
    }

    public ScreenElement onCreateChild(Element element) {
        return getContext().mFactory.createInstance(element, this.mRoot);
    }

    @Override // com.miui.maml.elements.AnimatedScreenElement, com.miui.maml.elements.ScreenElement
    public boolean onHover(MotionEvent motionEvent) {
        if (!isVisible()) {
            return false;
        }
        boolean z2 = touched(motionEvent.getX(), motionEvent.getY());
        if (this.mClip && !z2) {
            if (!this.mHovered) {
                return false;
            }
            motionEvent.setAction(10);
        }
        boolean zOnHover = true;
        int size = this.mElements.size() - 1;
        while (true) {
            if (size < 0) {
                zOnHover = super.onHover(motionEvent);
                break;
            }
            ScreenElement screenElement = this.mElements.get(size);
            IndexedVariable indexedVariable = this.mIndexVar;
            if (indexedVariable != null) {
                indexedVariable.set(size);
            }
            if (screenElement.onHover(motionEvent)) {
                break;
            }
            size--;
        }
        this.mHovered = zOnHover;
        return zOnHover;
    }

    @Override // com.miui.maml.elements.AnimatedScreenElement, com.miui.maml.elements.ScreenElement
    public boolean onTouch(MotionEvent motionEvent) {
        boolean z2 = false;
        if (!isVisible()) {
            return false;
        }
        int action = motionEvent.getAction();
        boolean z3 = touched(motionEvent.getX(), motionEvent.getY());
        if (this.mClip && !z3) {
            if (!this.mTouched) {
                return false;
            }
            motionEvent.setAction(3);
        }
        int size = this.mElements.size();
        if (this.mRoot.version() >= 2) {
            for (int i2 = size - 1; i2 >= 0; i2--) {
                ScreenElement screenElement = this.mElements.get(i2);
                IndexedVariable indexedVariable = this.mIndexVar;
                if (indexedVariable != null) {
                    indexedVariable.set(i2);
                }
                if (screenElement.onTouch(motionEvent)) {
                    z2 = true;
                    break;
                }
            }
        } else {
            for (int i3 = 0; i3 < size; i3++) {
                ScreenElement screenElement2 = this.mElements.get(i3);
                IndexedVariable indexedVariable2 = this.mIndexVar;
                if (indexedVariable2 != null) {
                    indexedVariable2.set(i3);
                }
                if (screenElement2.onTouch(motionEvent)) {
                    z2 = true;
                    break;
                }
            }
        }
        motionEvent.setAction(action);
        boolean zOnTouch = z2 ? true : super.onTouch(motionEvent);
        this.mTouched = zOnTouch;
        return zOnTouch;
    }

    @Override // com.miui.maml.elements.AnimatedScreenElement, com.miui.maml.elements.ScreenElement
    public void onVisibilityChange(boolean z2) {
        super.onVisibilityChange(z2);
        int size = this.mElements.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mElements.get(i2).updateVisibility();
        }
    }

    @Override // com.miui.maml.elements.AnimatedScreenElement, com.miui.maml.elements.ScreenElement
    public void pause() {
        super.pause();
        int size = this.mElements.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mElements.get(i2).pause();
        }
    }

    @Override // com.miui.maml.elements.ScreenElement
    public void pauseAnim(long j2) {
        super.pauseAnim(j2);
        int size = this.mElements.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mElements.get(i2).pauseAnim(j2);
        }
    }

    @Override // com.miui.maml.elements.ScreenElement
    public void playAnim(long j2, long j3, long j4, boolean z2, boolean z3) {
        super.playAnim(j2, j3, j4, z2, z3);
        int size = this.mElements.size();
        for (int i2 = 0; i2 < size; i2++) {
            IndexedVariable indexedVariable = this.mIndexVar;
            if (indexedVariable != null) {
                indexedVariable.set(i2);
            }
            this.mElements.get(i2).playAnim(j2, j3, j4, z2, z3);
        }
    }

    public void removeAllElements() {
        this.mElements.clear();
        requestUpdate();
    }

    public void removeElement(ScreenElement screenElement) {
        this.mElements.remove(screenElement);
        requestUpdate();
    }

    @Override // com.miui.maml.elements.ScreenElement
    public void reset(long j2) {
        super.reset(j2);
        int size = this.mElements.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mElements.get(i2).reset(j2);
        }
    }

    @Override // com.miui.maml.elements.ScreenElement
    public void resume() {
        super.resume();
        int size = this.mElements.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mElements.get(i2).resume();
        }
    }

    @Override // com.miui.maml.elements.ScreenElement
    public void resumeAnim(long j2) {
        super.resumeAnim(j2);
        int size = this.mElements.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mElements.get(i2).resumeAnim(j2);
        }
    }

    @Override // com.miui.maml.elements.ScreenElement
    public void setAnim(String[] strArr) {
        super.setAnim(strArr);
        int size = this.mElements.size();
        for (int i2 = 0; i2 < size; i2++) {
            IndexedVariable indexedVariable = this.mIndexVar;
            if (indexedVariable != null) {
                indexedVariable.set(i2);
            }
            this.mElements.get(i2).setAnim(strArr);
        }
    }

    public void setClip(boolean z2) {
        this.mClip = z2;
    }

    @Override // com.miui.maml.elements.AnimatedScreenElement, com.miui.maml.elements.ScreenElement
    public void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
        int size = this.mElements.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mElements.get(i2).setColorFilter(colorFilter);
        }
    }

    @Override // com.miui.maml.elements.ScreenElement
    public void showCategory(String str, boolean z2) {
        super.showCategory(str, z2);
        int size = this.mElements.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mElements.get(i2).showCategory(str, z2);
        }
    }

    private ElementGroup(ScreenElementRoot screenElementRoot, IndexedVariable indexedVariable) {
        super(null, screenElementRoot);
        this.mElements = new ArrayList<>();
        this.mLinearDirection = LinearDirection.None;
        this.mIndexVar = indexedVariable;
    }
}
