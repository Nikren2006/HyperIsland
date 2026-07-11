package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import com.mi.widget.view.ChargerView;
import com.mi.widget.view.PowerSaveView;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.model.BigIslandArea;
import miui.systemui.dynamicisland.model.ImageTextInfo;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.model.PicInfo;
import miui.systemui.dynamicisland.model.TextInfo;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandImageTextView4Holder extends BaseIslandModuleViewHolder {
    private ChargerView chargeView;
    private ImageView icon;
    private PicInfo picInfo;
    private PowerSaveView powerSaveView;
    private TextInfo textInfo;

    public interface Factory {
        IslandImageTextView4Holder create(ViewGroup viewGroup, Function2 function2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IslandImageTextView4Holder(Context context, ViewGroup rootView, Function2 emitEvent) {
        super(context, rootView, emitEvent);
        n.g(context, "context");
        n.g(rootView, "rootView");
        n.g(emitEvent, "emitEvent");
    }

    private final void showShaderIcon() {
        PicInfo picInfo = this.picInfo;
        String pic = picInfo != null ? picInfo.getPic() : null;
        if (pic != null) {
            int iHashCode = pic.hashCode();
            if (iHashCode != 657873003) {
                if (iHashCode != 1347797526) {
                    if (iHashCode == 2135799044 && pic.equals("charger_mode")) {
                        PowerSaveView powerSaveView = this.powerSaveView;
                        if (powerSaveView != null) {
                            powerSaveView.setVisibility(8);
                        }
                        ChargerView chargerView = this.chargeView;
                        if (chargerView != null) {
                            chargerView.setVisibility(0);
                        }
                        ChargerView chargerView2 = this.chargeView;
                        if (chargerView2 == null) {
                            return;
                        }
                        chargerView2.setChargerType(ChargerView.ChargerType.NORMAL);
                        return;
                    }
                } else if (pic.equals("quick_charger_mode")) {
                    PowerSaveView powerSaveView2 = this.powerSaveView;
                    if (powerSaveView2 != null) {
                        powerSaveView2.setVisibility(8);
                    }
                    ChargerView chargerView3 = this.chargeView;
                    if (chargerView3 != null) {
                        chargerView3.setVisibility(0);
                    }
                    ChargerView chargerView4 = this.chargeView;
                    if (chargerView4 == null) {
                        return;
                    }
                    chargerView4.setChargerType(ChargerView.ChargerType.FAST);
                    return;
                }
            } else if (pic.equals("power_save_mode")) {
                ChargerView chargerView5 = this.chargeView;
                if (chargerView5 != null) {
                    chargerView5.setVisibility(8);
                }
                PowerSaveView powerSaveView3 = this.powerSaveView;
                if (powerSaveView3 == null) {
                    return;
                }
                powerSaveView3.setVisibility(0);
                return;
            }
        }
        ChargerView chargerView6 = this.chargeView;
        if (chargerView6 != null) {
            chargerView6.setVisibility(8);
        }
        PowerSaveView powerSaveView4 = this.powerSaveView;
        if (powerSaveView4 == null) {
            return;
        }
        powerSaveView4.setVisibility(8);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void bind(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        TextView textView;
        ImageTextInfo imageTextInfoRight;
        ImageTextInfo imageTextInfoRight2;
        n.g(template, "template");
        super.bind(template, dynamicIslandData);
        BigIslandArea bigIslandArea = template.getBigIslandArea();
        this.picInfo = (bigIslandArea == null || (imageTextInfoRight2 = bigIslandArea.getImageTextInfoRight()) == null) ? null : imageTextInfoRight2.getPicInfo();
        BigIslandArea bigIslandArea2 = template.getBigIslandArea();
        this.textInfo = (bigIslandArea2 == null || (imageTextInfoRight = bigIslandArea2.getImageTextInfoRight()) == null) ? null : imageTextInfoRight.getTextInfo();
        float fB = Y0.b.b(14 * getContext().getResources().getDisplayMetrics().density);
        PowerSaveView powerSaveView = this.powerSaveView;
        if (powerSaveView != null) {
            TextInfo textInfo = this.textInfo;
            powerSaveView.setText(String.valueOf(textInfo != null ? textInfo.getTitle() : null));
            powerSaveView.setTextSize(fB);
            TextInfo textInfo2 = this.textInfo;
            if (textInfo2 != null ? n.c(textInfo2.getShowHighlightColor(), Boolean.TRUE) : false) {
                powerSaveView.setTextColor(Color.parseColor(template.getHighlightColor()));
            }
        }
        ChargerView chargerView = this.chargeView;
        if (chargerView != null && (textView = chargerView.getTextView()) != null) {
            TextInfo textInfo3 = this.textInfo;
            textView.setText(String.valueOf(textInfo3 != null ? textInfo3.getTitle() : null));
            textView.setTextSize(0, fB);
            TextInfo textInfo4 = this.textInfo;
            if (textInfo4 != null ? n.c(textInfo4.getShowHighlightColor(), Boolean.TRUE) : false) {
                textView.setTextColor(Color.parseColor(template.getHighlightColor()));
            }
        }
        showShaderIcon();
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void diff(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        super.diff(template, dynamicIslandData);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void initView(String module) {
        n.g(module, "module");
        super.initView(module);
        View view = getView();
        this.icon = view != null ? (ImageView) view.findViewById(R.id.island_icon) : null;
        View view2 = getView();
        this.powerSaveView = view2 != null ? (PowerSaveView) view2.findViewById(R.id.power_save_view) : null;
        View view3 = getView();
        this.chargeView = view3 != null ? (ChargerView) view3.findViewById(R.id.charge_view) : null;
        PowerSaveView powerSaveView = this.powerSaveView;
        if (powerSaveView != null) {
            powerSaveView.setVisibility(8);
        }
        ChargerView chargerView = this.chargeView;
        if (chargerView == null) {
            return;
        }
        chargerView.setVisibility(8);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void onDetach() {
        super.onDetach();
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updatePartial(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        super.updatePartial(template, dynamicIslandData);
        bind(template, dynamicIslandData);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updateWidth(int i2) {
    }
}
