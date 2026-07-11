package miui.systemui.controlcenter.panel.main.devicecenter.entry;

import android.content.Context;
import android.util.Log;
import android.view.View;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.databinding.DeviceCenterEntryItemBinding;
import miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceViewHolderKt;
import miui.systemui.controlcenter.panel.main.recyclerview.ScaleItemViewHolder;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.property.ViewProperty;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceCenterEntryViewHolder extends ScaleItemViewHolder {
    public static final Companion Companion = new Companion(null);
    private IStateStyle animator;
    private IStateStyle animatorRecyclerView;
    private final DeviceCenterEntryItemBinding binding;
    private boolean listening;
    private Mode mode;

    public static final class Companion {

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Mode.values().length];
                try {
                    iArr[Mode.MODE_COLLAPSED.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Mode.MODE_1_ROW.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Mode.MODE_2_ROWS.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getDeviceCenterEntryHeight(Context context, Mode mode) {
            n.g(context, "<this>");
            n.g(mode, "mode");
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.device_center_item_height);
            int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.device_center_entry_padding_vertical);
            int i2 = WhenMappings.$EnumSwitchMapping$0[mode.ordinal()];
            if (i2 != 1 && i2 != 2) {
                if (i2 != 3) {
                    throw new H0.g();
                }
                dimensionPixelSize *= 2;
            }
            return dimensionPixelSize + (dimensionPixelSize2 * 2);
        }

        private Companion() {
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Mode {
        private static final /* synthetic */ O0.a $ENTRIES;
        private static final /* synthetic */ Mode[] $VALUES;
        public static final Mode MODE_COLLAPSED = new Mode("MODE_COLLAPSED", 0);
        public static final Mode MODE_1_ROW = new Mode("MODE_1_ROW", 1);
        public static final Mode MODE_2_ROWS = new Mode("MODE_2_ROWS", 2);

        private static final /* synthetic */ Mode[] $values() {
            return new Mode[]{MODE_COLLAPSED, MODE_1_ROW, MODE_2_ROWS};
        }

        static {
            Mode[] modeArr$values = $values();
            $VALUES = modeArr$values;
            $ENTRIES = O0.b.a(modeArr$values);
        }

        private Mode(String str, int i2) {
        }

        public static O0.a getEntries() {
            return $ENTRIES;
        }

        public static Mode valueOf(String str) {
            return (Mode) Enum.valueOf(Mode.class, str);
        }

        public static Mode[] values() {
            return (Mode[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public DeviceCenterEntryViewHolder(DeviceCenterEntryItemBinding binding) {
        n.g(binding, "binding");
        DeviceCenterEntryFrameLayout root = binding.getRoot();
        n.f(root, "getRoot(...)");
        super(root);
        this.binding = binding;
        this.mode = Mode.MODE_COLLAPSED;
        this.itemView.setOnTouchListener(this);
    }

    public static /* synthetic */ void changeMode$default(DeviceCenterEntryViewHolder deviceCenterEntryViewHolder, Mode mode, boolean z2, boolean z3, boolean z4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            mode = deviceCenterEntryViewHolder.mode;
        }
        if ((i2 & 4) != 0) {
            z3 = false;
        }
        if ((i2 & 8) != 0) {
            z4 = false;
        }
        deviceCenterEntryViewHolder.changeMode(mode, z2, z3, z4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void changeMode$lambda$5(DeviceCenterEntryViewHolder this$0, boolean z2, int i2) {
        n.g(this$0, "this$0");
        changeMode$updateHeight(this$0, z2, i2);
    }

    private static final void changeMode$updateHeight(DeviceCenterEntryViewHolder deviceCenterEntryViewHolder, boolean z2, int i2) {
        IStateStyle iStateStyle = deviceCenterEntryViewHolder.animator;
        if ((iStateStyle != null ? z2 ? iStateStyle.to(ViewProperty.HEIGHT, Integer.valueOf(i2)) : iStateStyle.setTo(ViewProperty.HEIGHT, Integer.valueOf(i2)) : null) == null) {
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            View itemView = deviceCenterEntryViewHolder.itemView;
            n.f(itemView, "itemView");
            CommonUtils.setLayoutHeight$default(commonUtils, itemView, i2, false, 2, null);
        }
        IStateStyle iStateStyle2 = deviceCenterEntryViewHolder.animatorRecyclerView;
        if (iStateStyle2 != null) {
            if (z2) {
                iStateStyle2.to(ViewProperty.HEIGHT, Integer.valueOf(i2));
            } else {
                iStateStyle2.setTo(ViewProperty.HEIGHT, Integer.valueOf(i2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onConfigurationChanged$lambda$1(DeviceCenterEntryViewHolder this$0, int i2, int i3) {
        n.g(this$0, "this$0");
        Log.i("DeviceCenterEntryViewHolder", "dpi: " + this$0.getContext().getResources().getDisplayMetrics().density + "+ \npixel-width: " + this$0.itemView.getWidth() + " + \npixel-height: " + this$0.itemView.getHeight() + " + \npixel-listPaddingHorizontal: " + i2 + " + pixel-listPaddingVertical: " + i3);
        int width = this$0.itemView.getWidth();
        Context context = this$0.itemView.getContext();
        n.f(context, "getContext(...)");
        float fPxToDp = DeviceViewHolderKt.pxToDp(width, context);
        StringBuilder sb = new StringBuilder();
        sb.append("recyclerView,  width : ");
        sb.append(fPxToDp);
        Log.i("DeviceCenterEntryViewHolder", sb.toString());
    }

    public final void changeMode(Mode mode, final boolean z2, boolean z3, boolean z4) {
        n.g(mode, "mode");
        Log.i("DeviceCenterEntryViewHolder_changeMode", "dpi: " + getContext().getResources().getDisplayMetrics().density + "+ \npixel-width: " + this.itemView.getWidth() + "  \npixel-height: " + this.itemView.getHeight() + " \n");
        int width = this.itemView.getWidth();
        Context context = this.itemView.getContext();
        n.f(context, "getContext(...)");
        float fPxToDp = DeviceViewHolderKt.pxToDp(width, context);
        StringBuilder sb = new StringBuilder();
        sb.append("recyclerView,  width : ");
        sb.append(fPxToDp);
        Log.i("DeviceCenterEntryViewHolder_changeMode", sb.toString());
        if (this.mode != mode || z3) {
            this.mode = mode;
            Companion companion = Companion;
            Context context2 = this.itemView.getContext();
            n.f(context2, "getContext(...)");
            final int deviceCenterEntryHeight = companion.getDeviceCenterEntryHeight(context2, mode);
            if (z4) {
                this.itemView.post(new Runnable() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.entry.g
                    @Override // java.lang.Runnable
                    public final void run() {
                        DeviceCenterEntryViewHolder.changeMode$lambda$5(this.f5382a, z2, deviceCenterEntryHeight);
                    }
                });
            } else {
                changeMode$updateHeight(this, z2, deviceCenterEntryHeight);
            }
        }
    }

    public final DeviceCenterEntryItemBinding getBinding() {
        return this.binding;
    }

    public final boolean getListening() {
        return this.listening;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
    public void onConfigurationChanged(int i2) {
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        boolean zDimensionsChanged = configUtils.dimensionsChanged(i2);
        if (zDimensionsChanged) {
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            View itemView = this.itemView;
            n.f(itemView, "itemView");
            CommonUtils.setMargins$default(commonUtils, itemView, getResources().getDimensionPixelSize(R.dimen.control_center_universal_margin), false, 2, null);
            final int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.device_center_entry_padding_vertical);
            final int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.device_center_entry_padding_horizontal);
            this.binding.list.setPadding(dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize);
            this.itemView.post(new Runnable() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.entry.h
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceCenterEntryViewHolder.onConfigurationChanged$lambda$1(this.f5385a, dimensionPixelSize2, dimensionPixelSize);
                }
            });
            changeMode(this.mode, false, true, true);
        }
        if (zDimensionsChanged || configUtils.colorsChanged(i2) || configUtils.blurChanged(i2)) {
            CommonUtils commonUtils2 = CommonUtils.INSTANCE;
            View itemView2 = this.itemView;
            n.f(itemView2, "itemView");
            CommonUtils.setBackgroundResourceEx$default(commonUtils2, itemView2, R.drawable.external_entry_background, false, 2, null);
        }
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder, miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
    public void onViewAttachedToWindow() {
        super.onViewAttachedToWindow();
        IStateStyle iStateStyleState = Folme.useAt(this.itemView).state();
        this.animator = iStateStyleState;
        if (iStateStyleState != null) {
            iStateStyleState.setTo(ViewProperty.HEIGHT, Integer.valueOf(this.binding.list.getHeight()));
        }
        IStateStyle iStateStyleState2 = Folme.useAt(this.binding.list).state();
        this.animatorRecyclerView = iStateStyleState2;
        if (iStateStyleState2 != null) {
            iStateStyleState2.setTo(ViewProperty.HEIGHT, Integer.valueOf(this.binding.list.getHeight()));
        }
        changeMode$default(this, null, false, true, false, 9, null);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
    public void onViewDetachedFromWindow() {
        super.onViewDetachedFromWindow();
        Folme.clean(this.itemView);
        Folme.clean(this.binding.list);
    }

    public final void setListening(boolean z2) {
        if (this.listening == z2) {
            return;
        }
        this.listening = z2;
        if (z2) {
            changeMode$default(this, this.mode, false, true, false, 8, null);
        }
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
    public AnimConfig updateAnimConfig(boolean z2, boolean z3, int i2, int i3) {
        return getAnimatorConfigHelper().updateAnimEase(this, z2, z3, i2, i3);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
    public void updateBlendBlur() {
        View view = this.itemView;
        Context context = view.getContext();
        n.f(context, "getContext(...)");
        if (ControlCenterUtils.getBackgroundBlurOpenedInDefaultTheme(context)) {
            n.d(view);
            MiBlurCompat.setMiViewBlurModeCompat(view, 1);
            MiBlurCompat.setMiBackgroundBlendColors(view, MiuiColorBlendToken.INSTANCE.getCC_TILE_DEFAULT_BLEND_COLORS());
        } else {
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            n.d(view);
            commonUtils.clearMiBlurBlendEffect(view);
        }
    }
}
