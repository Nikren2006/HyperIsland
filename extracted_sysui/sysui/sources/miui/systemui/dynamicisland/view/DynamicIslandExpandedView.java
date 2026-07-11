package miui.systemui.dynamicisland.view;

import H0.s;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.AttachedSurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.window.SurfaceSyncGroup;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandGlowEffectLayer;
import com.mi.widget.core.Origin;
import com.mi.widget.shader.CallingShader;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.R;
import miui.systemui.util.ShaderUtil;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandExpandedView extends DynamicGlowEffectView {
    private static final Companion Companion = new Companion(null);
    private static final String LOG_TAG = "DynamicIslandExpandedView";
    private AttachedSurfaceControl glowEffectLayerSurfaceSyncer;
    private boolean mAttachInterruptedAnim;
    private View mContentView;
    private DynamicIslandGlowEffectLayer mGlowEffectLayer;
    private boolean mStartCallShaderInVisible;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DynamicIslandExpandedView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        n.g(context, "context");
    }

    private final void detachFromInterruptedAnim(String str) {
        if (!this.mAttachInterruptedAnim) {
            Log.w(LOG_TAG, "detachFromInterruptedAnim: Not attach to any interrupted anim, packageName=" + str);
            return;
        }
        Log.i(LOG_TAG, "detachFromInterruptedAnim: Detach from interrupted anim packageName=" + str);
        this.mAttachInterruptedAnim = false;
        View mGlowEffectBottomView = getMGlowEffectBottomView();
        Object parent = mGlowEffectBottomView != null ? mGlowEffectBottomView.getParent() : null;
        View view = parent instanceof View ? (View) parent : null;
        this.glowEffectLayerSurfaceSyncer = view != null ? view.getRootSurfaceControl() : null;
        View mGlowEffectBottomView2 = getMGlowEffectBottomView();
        ViewParent parent2 = mGlowEffectBottomView2 != null ? mGlowEffectBottomView2.getParent() : null;
        ViewGroup viewGroup = parent2 instanceof ViewGroup ? (ViewGroup) parent2 : null;
        if (viewGroup != null) {
            viewGroup.removeView(getMGlowEffectBottomView());
        }
        ViewGroup mGlowEffectBottomContainer = getMGlowEffectBottomContainer();
        if (mGlowEffectBottomContainer != null) {
            mGlowEffectBottomContainer.addView(getMGlowEffectBottomView());
        }
        getMContainer().invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detachFromInterruptedAnimWithSync$lambda$2(DynamicIslandExpandedView this$0, String packageName) {
        n.g(this$0, "this$0");
        n.g(packageName, "$packageName");
        View mGlowEffectUpperView = this$0.getMGlowEffectUpperView();
        ViewParent parent = mGlowEffectUpperView != null ? mGlowEffectUpperView.getParent() : null;
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(this$0.getMGlowEffectUpperView());
        }
        ViewGroup mGlowEffectUpperContainer = this$0.getMGlowEffectUpperContainer();
        if (mGlowEffectUpperContainer != null) {
            mGlowEffectUpperContainer.addView(this$0.getMGlowEffectUpperView());
        }
        this$0.getMContainer().invalidate();
        DynamicIslandGlowEffectLayer dynamicIslandGlowEffectLayer = this$0.mGlowEffectLayer;
        if (dynamicIslandGlowEffectLayer != null) {
            SurfaceSyncGroup surfaceSyncGroup = new SurfaceSyncGroup("GlowEffectSSG");
            surfaceSyncGroup.add(this$0.getRootSurfaceControl(), (Runnable) null);
            surfaceSyncGroup.markSyncReady();
            s sVar = s.f314a;
            dynamicIslandGlowEffectLayer.hideGlowEffectLayer(packageName, surfaceSyncGroup);
        }
    }

    public final void attachGlowViewToInterruptedAnim$miui_dynamicisland_release(String packageName, DynamicIslandGlowEffectLayer glowEffectSurfaceLayer) {
        n.g(packageName, "packageName");
        n.g(glowEffectSurfaceLayer, "glowEffectSurfaceLayer");
        if (this.mAttachInterruptedAnim) {
            Log.w(LOG_TAG, "attachGlowViewToInterruptedAnim: Already attach to interrupted anim");
            return;
        }
        if (getMGlowEffectBottomView() == null || getMGlowEffectUpperView() == null) {
            Log.w(LOG_TAG, "attachGlowViewToInterruptedAnim: UpperGlowView=" + getMGlowEffectUpperView() + ", BottomGlowView=" + getMGlowEffectBottomView());
            return;
        }
        Log.i(LOG_TAG, "attachGlowViewToInterruptedAnim: Attach to interrupted anim packageName=" + packageName);
        this.mAttachInterruptedAnim = true;
        ViewGroup mGlowEffectBottomContainer = getMGlowEffectBottomContainer();
        if (mGlowEffectBottomContainer == null) {
            throw new IllegalStateException("Required value was null.");
        }
        mGlowEffectBottomContainer.removeView(getMGlowEffectBottomView());
        ViewGroup mGlowEffectUpperContainer = getMGlowEffectUpperContainer();
        if (mGlowEffectUpperContainer == null) {
            throw new IllegalStateException("Required value was null.");
        }
        mGlowEffectUpperContainer.removeView(getMGlowEffectUpperView());
        this.mGlowEffectLayer = glowEffectSurfaceLayer;
        View mGlowEffectUpperView = getMGlowEffectUpperView();
        if (mGlowEffectUpperView == null) {
            throw new IllegalStateException("Required value was null.");
        }
        View mGlowEffectBottomView = getMGlowEffectBottomView();
        if (mGlowEffectBottomView == null) {
            throw new IllegalStateException("Required value was null.");
        }
        glowEffectSurfaceLayer.showGlowEffectLayer(packageName, mGlowEffectUpperView, mGlowEffectBottomView);
    }

    public final void detachFromInterruptedAnimWithStopGlowEffect$miui_dynamicisland_release(String packageName) {
        n.g(packageName, "packageName");
        if (!this.mAttachInterruptedAnim) {
            Log.w(LOG_TAG, "detachFromInterruptedAnimWithStopGlowEffect: Not attach to any interrupted anim, packageName=" + packageName);
            return;
        }
        detachFromInterruptedAnim(packageName);
        View mGlowEffectUpperView = getMGlowEffectUpperView();
        ViewParent parent = mGlowEffectUpperView != null ? mGlowEffectUpperView.getParent() : null;
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(getMGlowEffectUpperView());
        }
        ViewGroup mGlowEffectUpperContainer = getMGlowEffectUpperContainer();
        if (mGlowEffectUpperContainer != null) {
            mGlowEffectUpperContainer.addView(getMGlowEffectUpperView());
        }
        getMContainer().stop(true);
    }

    public final void detachFromInterruptedAnimWithSync$miui_dynamicisland_release(final String packageName) {
        n.g(packageName, "packageName");
        if (this.mAttachInterruptedAnim) {
            detachFromInterruptedAnim(packageName);
            postOnAnimation(new Runnable() { // from class: miui.systemui.dynamicisland.view.b
                @Override // java.lang.Runnable
                public final void run() {
                    DynamicIslandExpandedView.detachFromInterruptedAnimWithSync$lambda$2(this.f5731a, packageName);
                }
            });
        } else {
            Log.w(LOG_TAG, "detachFromInterruptedAnimWithSync: Not attach to any interrupted anim, packageName=" + packageName);
        }
    }

    @Override // android.view.View
    public void onVisibilityChanged(View changedView, int i2) {
        n.g(changedView, "changedView");
        super.onVisibilityChanged(changedView, i2);
        View view = this.mContentView;
        if (view == null) {
            return;
        }
        if (view == null) {
            throw new IllegalStateException("Required value was null.");
        }
        Object tag = view.getTag(R.id.custom_view_shader);
        if (tag instanceof CallingShader) {
            if (!isShown()) {
                ((CallingShader) tag).stop();
            } else if (this.mStartCallShaderInVisible) {
                ((CallingShader) tag).start();
            }
            this.mStartCallShaderInVisible = false;
        }
    }

    public final s removeContentView$miui_dynamicisland_release() {
        View view = this.mContentView;
        if (view == null) {
            return null;
        }
        getMContainer().removeView(view);
        return s.f314a;
    }

    public final void setContentView$miui_dynamicisland_release(View view) {
        this.mContentView = view;
        getMContainer().addView(view);
    }

    public final void startCallingEffectIfNeeded$miui_dynamicisland_release() {
        View view = this.mContentView;
        if (view != null && (view instanceof ViewGroup)) {
            Object tag = view.getTag(R.id.custom_view_shader_id);
            Object tag2 = view.getTag(R.id.custom_view_shader_type);
            if ((tag instanceof Integer) && (tag2 instanceof String)) {
                View view2 = this.mContentView;
                if (view2 == null) {
                    throw new IllegalStateException("Required value was null.");
                }
                View viewFindViewById = view2.findViewById(((Number) tag).intValue());
                if (viewFindViewById == null) {
                    return;
                }
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                viewFindViewById.getLocationOnScreen(iArr2);
                ViewGroup viewGroup = (ViewGroup) view;
                int width = (viewGroup.getWidth() - viewFindViewById.getWidth()) - (iArr2[0] - iArr[0]);
                int i2 = R.id.custom_view_shader;
                Object tag3 = view.getTag(i2);
                if (!(tag3 instanceof CallingShader)) {
                    View childAt = viewGroup.getChildAt(0);
                    n.f(childAt, "getChildAt(...)");
                    tag3 = ShaderUtil.setShader$default(ShaderUtil.INSTANCE, (String) tag2, childAt, viewFindViewById.getWidth(), width, Origin.RIGHT, false, 32, null);
                    view.setTag(i2, tag3);
                }
                if (!isShown()) {
                    this.mStartCallShaderInVisible = true;
                } else {
                    n.e(tag3, "null cannot be cast to non-null type com.mi.widget.shader.CallingShader<*>");
                    ((CallingShader) tag3).start();
                }
            }
        }
    }

    public final void stopCallingEffectIfNeeded$miui_dynamicisland_release() {
        View view = this.mContentView;
        if (view == null) {
            throw new IllegalStateException("Required value was null.");
        }
        Object tag = view.getTag(R.id.custom_view_shader);
        if (tag instanceof CallingShader) {
            if (this.mStartCallShaderInVisible) {
                this.mStartCallShaderInVisible = false;
            } else {
                ((CallingShader) tag).stop();
            }
        }
    }

    public final void syncInterruptAnimWithSwitchFromFakeToReal$miui_dynamicisland_release(SurfaceSyncGroup ssg) {
        n.g(ssg, "ssg");
        AttachedSurfaceControl attachedSurfaceControl = this.glowEffectLayerSurfaceSyncer;
        if (attachedSurfaceControl != null) {
            ssg.add(attachedSurfaceControl, (Runnable) null);
        }
        this.glowEffectLayerSurfaceSyncer = null;
        ssg.markSyncReady();
    }

    public final void updateGlowEffectAnim$miui_dynamicisland_release(float f2, float f3, float f4, float f5, float f6) {
        if (this.mAttachInterruptedAnim) {
            return;
        }
        setGlowEffectPosition(f2, f3, f4, f5, f6);
    }

    public final void updateInterruptedAnim$miui_dynamicisland_release(float f2, float f3, float f4, float f5, float f6) {
        if (this.mAttachInterruptedAnim) {
            setGlowEffectPosition(f2, f3, f4, f5, f6);
        }
    }

    public /* synthetic */ DynamicIslandExpandedView(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandExpandedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.g(context, "context");
    }
}
