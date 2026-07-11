package miui.systemui.dynamicisland.template;

import U.d;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import g1.E;
import g1.F;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.coroutines.Dispatchers;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.IslandParamsException;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.model.BigIslandArea;
import miui.systemui.dynamicisland.model.ImageTextInfo;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.model.SmallIslandArea;
import miui.systemui.dynamicisland.template.IslandTemplateBuilder;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class IslandTemplateFactory {
    private static final String AREA_LEFT = "area_left";
    private static final String AREA_RIGHT = "area_right";
    private static final String AREA_SMALL = "area_small";
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "IslandTemplateFactory";
    private final Map<String, IslandTemplateBuilder> bigIslandBuilderMap;
    private final Map<String, IslandTemplateBuilder> fakeBigIslandBuilderMap;
    private final Map<String, IslandTemplateBuilder> fakeSmallIslandBuilderMap;
    private final IslandTemplateBuilder.Factory islandTemplateBuilderFactory;
    private final Map<String, IslandTemplateBuilder> smallIslandBuilderMap;
    private final Map<String, IslandTemplate> templateMap;
    private final E uiScope;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public IslandTemplateFactory(@DynamicIsland E scope, IslandTemplateBuilder.Factory islandTemplateBuilderFactory) {
        n.g(scope, "scope");
        n.g(islandTemplateBuilderFactory, "islandTemplateBuilderFactory");
        this.islandTemplateBuilderFactory = islandTemplateBuilderFactory;
        this.uiScope = F.g(scope, Dispatchers.INSTANCE.getMain());
        this.bigIslandBuilderMap = new LinkedHashMap();
        this.smallIslandBuilderMap = new LinkedHashMap();
        this.templateMap = new LinkedHashMap();
        this.fakeBigIslandBuilderMap = new LinkedHashMap();
        this.fakeSmallIslandBuilderMap = new LinkedHashMap();
    }

    private final String chooseModule(IslandTemplate islandTemplate, String str) throws IslandParamsException {
        Integer type;
        Integer type2;
        Integer type3;
        Integer type4;
        ImageTextInfo imageTextInfoRight;
        Integer type5;
        BigIslandArea bigIslandArea = islandTemplate.getBigIslandArea();
        SmallIslandArea smallIslandArea = islandTemplate.getSmallIslandArea();
        int iHashCode = str.hashCode();
        if (iHashCode == -100870023) {
            if (!str.equals(AREA_LEFT)) {
                return "";
            }
            ImageTextInfo imageTextInfoLeft = bigIslandArea != null ? bigIslandArea.getImageTextInfoLeft() : null;
            if ((imageTextInfoLeft != null ? imageTextInfoLeft.getType() : null) == null || (((type = imageTextInfoLeft.getType()) == null || type.intValue() != 1) && ((type2 = imageTextInfoLeft.getType()) == null || type2.intValue() != 5))) {
                throw new IslandParamsException("bigIslandArea.imageTextInfoLeft.type is null or not 1 or 5");
            }
            Integer type6 = imageTextInfoLeft.getType();
            if (type6 != null && type6.intValue() == 1) {
                return DynamicIslandConstants.MODULE_IMAGE_TEXT_1;
            }
            Integer type7 = imageTextInfoLeft.getType();
            return (type7 != null && type7.intValue() == 5) ? DynamicIslandConstants.MODULE_ICON_FIXED_WIDTH_TEXT : "";
        }
        if (iHashCode != 1173657578) {
            if (iHashCode == 1174694613 && str.equals(AREA_SMALL)) {
                return (smallIslandArea != null ? smallIslandArea.getCombinePicInfo() : null) != null ? DynamicIslandConstants.MODULE_COMBINE_PIC : (bigIslandArea == null || (imageTextInfoRight = bigIslandArea.getImageTextInfoRight()) == null || (type5 = imageTextInfoRight.getType()) == null || type5.intValue() != 6) ? DynamicIslandConstants.MODULE_PIC_SMALL_ISLAND : DynamicIslandConstants.MODULE_SMALL_TEXT_OVER_ICON;
            }
            return "";
        }
        if (!str.equals(AREA_RIGHT)) {
            return "";
        }
        ImageTextInfo imageTextInfoRight2 = bigIslandArea != null ? bigIslandArea.getImageTextInfoRight() : null;
        if (imageTextInfoRight2 != null && (((type3 = imageTextInfoRight2.getType()) == null || type3.intValue() != 1) && ((type4 = imageTextInfoRight2.getType()) == null || type4.intValue() != 5))) {
            ImageTextInfo imageTextInfoRight3 = bigIslandArea.getImageTextInfoRight();
            Integer type8 = imageTextInfoRight3 != null ? imageTextInfoRight3.getType() : null;
            if (type8 != null && type8.intValue() == 2) {
                return DynamicIslandConstants.MODULE_IMAGE_TEXT_2;
            }
            if (type8 != null && type8.intValue() == 3) {
                return DynamicIslandConstants.MODULE_IMAGE_TEXT_3;
            }
            if (type8 != null && type8.intValue() == 4) {
                return DynamicIslandConstants.MODULE_IMAGE_TEXT_4;
            }
            if (type8 != null && type8.intValue() == 6) {
                return DynamicIslandConstants.MODULE_TEXT_OVER_ICON;
            }
        }
        if ((bigIslandArea != null ? bigIslandArea.getFixedWidthDigitInfo() : null) != null) {
            return DynamicIslandConstants.MODULE_FIXED_WIDTH_DIGIT;
        }
        if ((bigIslandArea != null ? bigIslandArea.getSameWidthDigitInfo() : null) != null) {
            return DynamicIslandConstants.MODULE_SAME_WIDTH_DIGIT;
        }
        if ((bigIslandArea != null ? bigIslandArea.getProgressTextInfo() : null) != null) {
            return DynamicIslandConstants.MODULE_PROGRESS_TEXT;
        }
        if ((bigIslandArea != null ? bigIslandArea.getTextInfo() : null) != null) {
            return DynamicIslandConstants.MODULE_TEXT;
        }
        return (bigIslandArea != null ? bigIslandArea.getPicInfo() : null) != null ? DynamicIslandConstants.MODULE_PIC : "";
    }

    private final View createBigIslandTemplateView(Context context, IslandTemplate islandTemplate, DynamicIslandData dynamicIslandData, ViewGroup viewGroup, boolean z2, Function2 function2) throws IslandParamsException {
        String key;
        IslandTemplateBuilder islandTemplateBuilderUpdateModuleView;
        String key2;
        IslandTemplateBuilder islandTemplateBuilderUpdateModuleView2;
        String key3;
        String strChooseModule = chooseModule(islandTemplate, AREA_LEFT);
        String strChooseModule2 = chooseModule(islandTemplate, AREA_RIGHT);
        IslandTemplateBuilder islandTemplateBuilder = this.bigIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
        boolean z3 = islandTemplateBuilder != null && islandTemplateBuilder.isSameModule(strChooseModule) && islandTemplateBuilder.isSameModule(strChooseModule2);
        Log.d(TAG, "isSameModule: " + z3);
        if (islandTemplateBuilder != null && !z3 && dynamicIslandData != null && (key3 = dynamicIslandData.getKey()) != null) {
            removeTemplate(key3);
        }
        Map<String, IslandTemplate> map = this.templateMap;
        String key4 = dynamicIslandData != null ? dynamicIslandData.getKey() : null;
        n.d(key4);
        map.put(key4, islandTemplate);
        if (z2) {
            if (!this.fakeBigIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
                IslandTemplateBuilder islandTemplateBuilderCreate = this.islandTemplateBuilderFactory.create(viewGroup, true, true, function2);
                int i2 = R.id.fake_area_left;
                IslandTemplateBuilder islandAreaViewVisible = islandTemplateBuilderCreate.setIslandAreaViewVisible(i2, TextUtils.isEmpty(strChooseModule) ? 8 : 0, this.uiScope);
                int i3 = R.id.fake_area_right;
                islandAreaViewVisible.setIslandAreaViewVisible(i3, TextUtils.isEmpty(strChooseModule2) ? 4 : 0, this.uiScope).addIslandModuleView(i2, strChooseModule, islandTemplate, dynamicIslandData).addIslandModuleView(i3, strChooseModule2, islandTemplate, dynamicIslandData);
                if (dynamicIslandData != null && (key2 = dynamicIslandData.getKey()) != null) {
                    this.fakeBigIslandBuilderMap.put(key2, islandTemplateBuilderCreate);
                }
                return islandTemplateBuilderCreate.buildIslandView();
            }
            IslandTemplateBuilder islandTemplateBuilder2 = this.fakeBigIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
            if (islandTemplateBuilder2 != null) {
                IslandTemplateBuilder islandAreaViewVisible2 = islandTemplateBuilder2.setIslandAreaViewVisible(R.id.fake_area_left, TextUtils.isEmpty(strChooseModule) ? 8 : 0, this.uiScope);
                if (islandAreaViewVisible2 != null) {
                    IslandTemplateBuilder islandAreaViewVisible3 = islandAreaViewVisible2.setIslandAreaViewVisible(R.id.fake_area_right, TextUtils.isEmpty(strChooseModule2) ? 4 : 0, this.uiScope);
                    if (islandAreaViewVisible3 != null && (islandTemplateBuilderUpdateModuleView2 = islandAreaViewVisible3.updateModuleView(strChooseModule, islandTemplate, dynamicIslandData)) != null) {
                        islandTemplateBuilderUpdateModuleView2.updateModuleView(strChooseModule2, islandTemplate, dynamicIslandData);
                    }
                }
            }
            if (islandTemplateBuilder2 != null) {
                return islandTemplateBuilder2.buildIslandView();
            }
            return null;
        }
        if (!this.bigIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
            IslandTemplateBuilder islandTemplateBuilderCreate2 = this.islandTemplateBuilderFactory.create(viewGroup, true, false, function2);
            int i4 = R.id.area_left;
            IslandTemplateBuilder islandAreaViewVisible4 = islandTemplateBuilderCreate2.setIslandAreaViewVisible(i4, TextUtils.isEmpty(strChooseModule) ? 8 : 0, this.uiScope);
            int i5 = R.id.area_right;
            islandAreaViewVisible4.setIslandAreaViewVisible(i5, TextUtils.isEmpty(strChooseModule2) ? 4 : 0, this.uiScope).addIslandModuleView(i4, strChooseModule, islandTemplate, dynamicIslandData).addIslandModuleView(i5, strChooseModule2, islandTemplate, dynamicIslandData);
            if (dynamicIslandData != null && (key = dynamicIslandData.getKey()) != null) {
                this.bigIslandBuilderMap.put(key, islandTemplateBuilderCreate2);
            }
            return islandTemplateBuilderCreate2.buildIslandView();
        }
        IslandTemplateBuilder islandTemplateBuilder3 = this.bigIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
        if (islandTemplateBuilder3 != null) {
            IslandTemplateBuilder islandAreaViewVisible5 = islandTemplateBuilder3.setIslandAreaViewVisible(R.id.area_left, TextUtils.isEmpty(strChooseModule) ? 8 : 0, this.uiScope);
            if (islandAreaViewVisible5 != null) {
                IslandTemplateBuilder islandAreaViewVisible6 = islandAreaViewVisible5.setIslandAreaViewVisible(R.id.area_right, TextUtils.isEmpty(strChooseModule2) ? 4 : 0, this.uiScope);
                if (islandAreaViewVisible6 != null && (islandTemplateBuilderUpdateModuleView = islandAreaViewVisible6.updateModuleView(strChooseModule, islandTemplate, dynamicIslandData)) != null) {
                    islandTemplateBuilderUpdateModuleView.updateModuleView(strChooseModule2, islandTemplate, dynamicIslandData);
                }
            }
        }
        if (islandTemplateBuilder3 != null) {
            return islandTemplateBuilder3.buildIslandView();
        }
        return null;
    }

    private final View createSmallIslandTemplateView(Context context, IslandTemplate islandTemplate, DynamicIslandData dynamicIslandData, ViewGroup viewGroup, boolean z2, Function2 function2) throws IslandParamsException {
        String key;
        String key2;
        String key3;
        String strChooseModule = chooseModule(islandTemplate, AREA_SMALL);
        IslandTemplateBuilder islandTemplateBuilder = this.smallIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
        boolean z3 = islandTemplateBuilder != null && islandTemplateBuilder.isSameModule(strChooseModule);
        Log.d(TAG, "isSameModule: " + z3);
        if (islandTemplateBuilder != null && !z3 && dynamicIslandData != null && (key3 = dynamicIslandData.getKey()) != null) {
            removeTemplate(key3);
        }
        if (z2) {
            if (this.fakeSmallIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
                IslandTemplateBuilder islandTemplateBuilder2 = this.fakeSmallIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
                if (islandTemplateBuilder2 != null) {
                    islandTemplateBuilder2.updateModuleView(strChooseModule, islandTemplate, dynamicIslandData);
                }
                if (islandTemplateBuilder2 != null) {
                    return islandTemplateBuilder2.buildIslandView();
                }
                return null;
            }
            IslandTemplateBuilder islandTemplateBuilderCreate = this.islandTemplateBuilderFactory.create(viewGroup, false, true, function2);
            islandTemplateBuilderCreate.addIslandModuleView(R.id.fake_small_container, strChooseModule, islandTemplate, dynamicIslandData);
            if (dynamicIslandData != null && (key2 = dynamicIslandData.getKey()) != null) {
                this.fakeSmallIslandBuilderMap.put(key2, islandTemplateBuilderCreate);
            }
            return islandTemplateBuilderCreate.buildIslandView();
        }
        if (this.smallIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
            IslandTemplateBuilder islandTemplateBuilder3 = this.smallIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
            if (islandTemplateBuilder3 != null) {
                islandTemplateBuilder3.updateModuleView(strChooseModule, islandTemplate, dynamicIslandData);
            }
            if (islandTemplateBuilder3 != null) {
                return islandTemplateBuilder3.buildIslandView();
            }
            return null;
        }
        IslandTemplateBuilder islandTemplateBuilderCreate2 = this.islandTemplateBuilderFactory.create(viewGroup, false, false, function2);
        islandTemplateBuilderCreate2.addIslandModuleView(R.id.small_container, strChooseModule, islandTemplate, dynamicIslandData);
        if (dynamicIslandData != null && (key = dynamicIslandData.getKey()) != null) {
            this.smallIslandBuilderMap.put(key, islandTemplateBuilderCreate2);
        }
        return islandTemplateBuilderCreate2.buildIslandView();
    }

    public final View createBigIslandTemplate(Context context, DynamicIslandData dynamicIslandData, ViewGroup root, boolean z2, Function2 emitEvent) {
        n.g(context, "context");
        n.g(root, "root");
        n.g(emitEvent, "emitEvent");
        try {
            d dVar = new d();
            Log.d(TAG, "createBigIslandTemplate: " + CommonUtils.encodeDataToBase64(dynamicIslandData != null ? dynamicIslandData.getTickerData() : null));
            IslandTemplate islandTemplate = (IslandTemplate) dVar.j(dynamicIslandData != null ? dynamicIslandData.getTickerData() : null, IslandTemplate.class);
            n.d(islandTemplate);
            return createBigIslandTemplateView(context, islandTemplate, dynamicIslandData, root, z2, emitEvent);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public final View createSmallIslandTemplate(Context context, DynamicIslandData dynamicIslandData, ViewGroup root, boolean z2, Function2 emitEvent) {
        n.g(context, "context");
        n.g(root, "root");
        n.g(emitEvent, "emitEvent");
        IslandTemplate islandTemplate = (IslandTemplate) new d().j(dynamicIslandData != null ? dynamicIslandData.getTickerData() : null, IslandTemplate.class);
        n.d(islandTemplate);
        return createSmallIslandTemplateView(context, islandTemplate, dynamicIslandData, root, z2, emitEvent);
    }

    public final void hideView(DynamicIslandData dynamicIslandData, boolean z2) {
        Integer properties;
        Log.d(TAG, "hideView " + (dynamicIslandData != null ? dynamicIslandData.getKey() : null) + ", prop " + (dynamicIslandData != null ? dynamicIslandData.getProperties() : null));
        if (dynamicIslandData == null || (properties = dynamicIslandData.getProperties()) == null || properties.intValue() != 0) {
            if (z2) {
                if (this.fakeBigIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
                    IslandTemplateBuilder islandTemplateBuilder = this.fakeBigIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
                    if (islandTemplateBuilder != null) {
                        islandTemplateBuilder.hideModuleView();
                    }
                }
                if (this.fakeSmallIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
                    IslandTemplateBuilder islandTemplateBuilder2 = this.fakeSmallIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
                    if (islandTemplateBuilder2 != null) {
                        islandTemplateBuilder2.hideModuleView();
                        return;
                    }
                    return;
                }
                return;
            }
            if (this.bigIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
                IslandTemplateBuilder islandTemplateBuilder3 = this.bigIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
                if (islandTemplateBuilder3 != null) {
                    islandTemplateBuilder3.hideModuleView();
                }
            }
            if (this.smallIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
                IslandTemplateBuilder islandTemplateBuilder4 = this.smallIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
                if (islandTemplateBuilder4 != null) {
                    islandTemplateBuilder4.hideModuleView();
                }
            }
        }
    }

    public final void removeTemplate(String key) {
        IslandTemplateBuilder islandTemplateBuilderRemoveModuleView;
        IslandTemplateBuilder islandTemplateBuilderRemoveModuleView2;
        IslandTemplateBuilder islandTemplateBuilderRemoveModuleView3;
        IslandTemplateBuilder islandTemplateBuilderRemoveModuleView4;
        IslandTemplateBuilder islandTemplateBuilderRemoveModuleView5;
        IslandTemplateBuilder islandTemplateBuilderRemoveModuleView6;
        n.g(key, "key");
        if (this.bigIslandBuilderMap.containsKey(key)) {
            IslandTemplateBuilder islandTemplateBuilder = this.bigIslandBuilderMap.get(key);
            IslandTemplateBuilder islandTemplateBuilder2 = this.fakeBigIslandBuilderMap.get(key);
            IslandTemplate islandTemplate = this.templateMap.get(key);
            if (islandTemplate != null) {
                String strChooseModule = chooseModule(islandTemplate, AREA_LEFT);
                String strChooseModule2 = chooseModule(islandTemplate, AREA_RIGHT);
                if (islandTemplateBuilder != null && (islandTemplateBuilderRemoveModuleView5 = islandTemplateBuilder.removeModuleView(strChooseModule)) != null && (islandTemplateBuilderRemoveModuleView6 = islandTemplateBuilderRemoveModuleView5.removeModuleView(strChooseModule2)) != null) {
                    islandTemplateBuilderRemoveModuleView6.removeIslandView();
                }
                if (islandTemplateBuilder2 != null && (islandTemplateBuilderRemoveModuleView3 = islandTemplateBuilder2.removeModuleView(strChooseModule)) != null && (islandTemplateBuilderRemoveModuleView4 = islandTemplateBuilderRemoveModuleView3.removeModuleView(strChooseModule2)) != null) {
                    islandTemplateBuilderRemoveModuleView4.removeIslandView();
                }
            }
        }
        this.bigIslandBuilderMap.remove(key);
        this.fakeBigIslandBuilderMap.remove(key);
        if (this.smallIslandBuilderMap.containsKey(key)) {
            IslandTemplateBuilder islandTemplateBuilder3 = this.smallIslandBuilderMap.get(key);
            IslandTemplateBuilder islandTemplateBuilder4 = this.fakeSmallIslandBuilderMap.get(key);
            IslandTemplate islandTemplate2 = this.templateMap.get(key);
            if (islandTemplate2 != null) {
                String strChooseModule3 = chooseModule(islandTemplate2, AREA_SMALL);
                if (islandTemplateBuilder3 != null && (islandTemplateBuilderRemoveModuleView2 = islandTemplateBuilder3.removeModuleView(strChooseModule3)) != null) {
                    islandTemplateBuilderRemoveModuleView2.removeIslandView();
                }
                if (islandTemplateBuilder4 != null && (islandTemplateBuilderRemoveModuleView = islandTemplateBuilder4.removeModuleView(strChooseModule3)) != null) {
                    islandTemplateBuilderRemoveModuleView.removeIslandView();
                }
            }
        }
        this.smallIslandBuilderMap.remove(key);
        this.fakeSmallIslandBuilderMap.remove(key);
        this.templateMap.remove(key);
    }

    public final void showView(DynamicIslandData dynamicIslandData, boolean z2) {
        Integer properties;
        Log.d(TAG, "showView " + (dynamicIslandData != null ? dynamicIslandData.getKey() : null) + ", prop " + (dynamicIslandData != null ? dynamicIslandData.getProperties() : null));
        if (dynamicIslandData == null || (properties = dynamicIslandData.getProperties()) == null || properties.intValue() != 0) {
            if (z2) {
                if (this.fakeBigIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
                    IslandTemplateBuilder islandTemplateBuilder = this.fakeBigIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
                    if (islandTemplateBuilder != null) {
                        islandTemplateBuilder.showModuleView();
                    }
                }
                if (this.fakeSmallIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
                    IslandTemplateBuilder islandTemplateBuilder2 = this.fakeSmallIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
                    if (islandTemplateBuilder2 != null) {
                        islandTemplateBuilder2.showModuleView();
                        return;
                    }
                    return;
                }
                return;
            }
            if (this.bigIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
                IslandTemplateBuilder islandTemplateBuilder3 = this.bigIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
                if (islandTemplateBuilder3 != null) {
                    islandTemplateBuilder3.showModuleView();
                }
            }
            if (this.smallIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
                IslandTemplateBuilder islandTemplateBuilder4 = this.smallIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
                if (islandTemplateBuilder4 != null) {
                    islandTemplateBuilder4.showModuleView();
                }
            }
        }
    }

    public final void updateCutoutWidth(DynamicIslandData dynamicIslandData, int i2, boolean z2) {
        Log.d(TAG, "updateCutoutWidth " + i2 + " " + z2);
        if (z2) {
            if (this.fakeBigIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
                IslandTemplateBuilder islandTemplateBuilder = this.fakeBigIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
                if (islandTemplateBuilder != null) {
                    islandTemplateBuilder.updateCutoutWidth(i2);
                    return;
                }
                return;
            }
            return;
        }
        if (this.bigIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
            IslandTemplateBuilder islandTemplateBuilder2 = this.bigIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
            if (islandTemplateBuilder2 != null) {
                islandTemplateBuilder2.updateCutoutWidth(i2);
            }
        }
    }

    public final void updateLeftWidth(DynamicIslandData dynamicIslandData, int i2, boolean z2) {
        Log.d(TAG, "updateLeftWidth " + i2);
        if (z2) {
            if (this.fakeBigIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
                IslandTemplateBuilder islandTemplateBuilder = this.fakeBigIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
                if (islandTemplateBuilder != null) {
                    islandTemplateBuilder.updateLeftWidth(i2);
                    return;
                }
                return;
            }
            return;
        }
        if (this.bigIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
            IslandTemplateBuilder islandTemplateBuilder2 = this.bigIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
            if (islandTemplateBuilder2 != null) {
                islandTemplateBuilder2.updateLeftWidth(i2);
            }
        }
    }

    public final void updateRightWidth(DynamicIslandData dynamicIslandData, int i2, boolean z2) {
        Log.d(TAG, "updateRightWidth " + i2);
        if (z2) {
            if (this.fakeBigIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
                IslandTemplateBuilder islandTemplateBuilder = this.fakeBigIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
                if (islandTemplateBuilder != null) {
                    islandTemplateBuilder.updateRightWidth(i2);
                    return;
                }
                return;
            }
            return;
        }
        if (this.bigIslandBuilderMap.containsKey(dynamicIslandData != null ? dynamicIslandData.getKey() : null)) {
            IslandTemplateBuilder islandTemplateBuilder2 = this.bigIslandBuilderMap.get(dynamicIslandData != null ? dynamicIslandData.getKey() : null);
            if (islandTemplateBuilder2 != null) {
                islandTemplateBuilder2.updateRightWidth(i2);
            }
        }
    }
}
