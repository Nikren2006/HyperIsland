package miui.systemui.quicksettings;

import android.content.Context;
import android.media.AudioManager;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.PluginDependency;
import com.android.systemui.plugins.annotations.Requirements;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.qs.MiuiQSTile;
import com.android.systemui.plugins.miui.qs.MiuiQSTilePlugin;
import com.miui.miplay.audio.data.DeviceInfo;
import com.xiaomi.onetrack.util.aa;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import miui.systemui.controlcenter.qs.TileSpecsKt;
import miui.systemui.dagger.PluginComponentInitializer;
import miui.systemui.flashlight.MiFlashlightOperationReceiver;
import miui.systemui.plugins.PluginBase;
import miui.systemui.quicksettings.hearingassist.HearingAssistTile;
import miui.systemui.quicksettings.soundeffect.DolbyAtomsSoundEffectTile;
import miui.systemui.quicksettings.wireless.MiuiWirelessPowerTile;
import miui.systemui.util.DeviceUtils;
import miui.systemui.util.MiLinkController;

/* JADX INFO: loaded from: classes4.dex */
@Requirements({@Requires(target = MiuiQSTilePlugin.class, version = 1), @Requires(target = ActivityStarter.class, version = 2)})
public class LocalMiuiQSTilePlugin extends PluginBase implements MiuiQSTilePlugin {
    private static final String TAG = "LocalMiuiQSTilePlugin";
    private ActivityStarter mActivityStarter;
    private final E0.a mActivityStarterLazy = new E0.a() { // from class: miui.systemui.quicksettings.e
        @Override // E0.a
        public final Object get() {
            return this.f5882a.lambda$new$0();
        }
    };
    HashMap<String, MiuiQSTile> mAllMiuiTilesMap;
    MiFlashlightOperationReceiver mMiFlashlightOperationReceiver;
    MiLinkController mMiLinkController;
    private Context mPluginContext;
    private Context mSysUIContext;

    private void initActivityStarter() {
        if (this.mActivityStarter == null) {
            try {
                this.mActivityStarter = (ActivityStarter) PluginDependency.get(this, ActivityStarter.class);
            } catch (Throwable th) {
                Log.e(TAG, "failed to get ActivityStarter", th);
            }
        }
    }

    private void initAllMiuiTilesPlugin() {
        revertInternetTile();
        this.mAllMiuiTilesMap = new HashMap<>();
        FreeFormHangTile freeFormHangTile = new FreeFormHangTile(this.mSysUIContext, this.mPluginContext);
        this.mAllMiuiTilesMap.put(freeFormHangTile.getTileSpec(), freeFormHangTile);
        ScannerTile scannerTile = new ScannerTile(this.mSysUIContext, this.mPluginContext, this.mActivityStarterLazy);
        this.mAllMiuiTilesMap.put(scannerTile.getTileSpec(), scannerTile);
        AiTranslateTile aiTranslateTile = new AiTranslateTile(this.mSysUIContext, this.mPluginContext);
        this.mAllMiuiTilesMap.put(aiTranslateTile.getTileSpec(), aiTranslateTile);
        AiSubtitlesTile aiSubtitlesTile = new AiSubtitlesTile(this.mSysUIContext, this.mPluginContext);
        this.mAllMiuiTilesMap.put(aiSubtitlesTile.getTileSpec(), aiSubtitlesTile);
        VoiceTransTile voiceTransTile = new VoiceTransTile(this.mSysUIContext, this.mPluginContext, this.mActivityStarterLazy);
        this.mAllMiuiTilesMap.put(voiceTransTile.getTileSpec(), voiceTransTile);
        MiuiWirelessPowerTile miuiWirelessPowerTile = new MiuiWirelessPowerTile(this.mSysUIContext, this.mPluginContext);
        this.mAllMiuiTilesMap.put(miuiWirelessPowerTile.getTileSpec(), miuiWirelessPowerTile);
        if (DeviceUtils.isSupportCameraHandle()) {
            CameraHandleTile cameraHandleTile = new CameraHandleTile(this.mSysUIContext, this.mPluginContext);
            this.mAllMiuiTilesMap.put(cameraHandleTile.getTileSpec(), cameraHandleTile);
        }
        CarSicknessTile carSicknessTile = new CarSicknessTile(this.mSysUIContext, this.mPluginContext);
        this.mAllMiuiTilesMap.put(carSicknessTile.getTileSpec(), carSicknessTile);
        if (UwbSmartHomeTile.canUseUwb(this.mSysUIContext)) {
            UwbSmartHomeTile uwbSmartHomeTile = new UwbSmartHomeTile(this.mSysUIContext, this.mPluginContext);
            this.mAllMiuiTilesMap.put(uwbSmartHomeTile.getTileSpec(), uwbSmartHomeTile);
        }
        if (SystemProperties.getBoolean("ro.vendor.audio.dolby.dax.support", false)) {
            DolbyAtomsSoundEffectTile dolbyAtomsSoundEffectTile = new DolbyAtomsSoundEffectTile(this.mSysUIContext, this.mPluginContext);
            this.mAllMiuiTilesMap.put(dolbyAtomsSoundEffectTile.getTileSpec(), dolbyAtomsSoundEffectTile);
        }
        if (TextUtils.equals("sound_transmit_ha_support=true", ((AudioManager) this.mPluginContext.getSystemService(DeviceInfo.AUDIO_SUPPORT)).getParameters("sound_transmit_ha_support"))) {
            HearingAssistTile hearingAssistTile = new HearingAssistTile(this.mSysUIContext, this.mPluginContext);
            this.mAllMiuiTilesMap.put(hearingAssistTile.getTileSpec(), hearingAssistTile);
        }
        MiuiDesktopModeTile miuiDesktopModeTile = new MiuiDesktopModeTile(this.mSysUIContext, this.mPluginContext);
        this.mAllMiuiTilesMap.put(miuiDesktopModeTile.getTileSpec(), miuiDesktopModeTile);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ActivityStarter lambda$new$0() {
        initActivityStarter();
        return this.mActivityStarter;
    }

    public Map<String, MiuiQSTile> getAllPluginTiles() {
        return this.mAllMiuiTilesMap;
    }

    public String getDefaultTileWithOrder() {
        return this.mPluginContext.getString(miui.systemui.plugin.R.string.quick_settings_tiles_default);
    }

    public String getStockTileWithOrder() {
        return this.mPluginContext.getString(miui.systemui.plugin.R.string.quick_settings_tiles_stock);
    }

    @Override // miui.systemui.plugins.PluginBase
    public void onCreated() {
        PluginComponentInitializer.getPluginComponent().inject(this);
        this.mSysUIContext = getSysuiContext();
        this.mPluginContext = getPluginContext();
        initActivityStarter();
        this.mMiLinkController.onPluginCreated();
        initAllMiuiTilesPlugin();
        this.mMiFlashlightOperationReceiver.register(this.mPluginContext);
    }

    @Override // miui.systemui.plugins.PluginBase
    public void onDestroyed() {
        this.mMiLinkController.onPluginDestroyed();
        this.mMiFlashlightOperationReceiver.unRegister(this.mPluginContext);
    }

    public void revertInternetTile() {
        String string = Settings.Secure.getString(this.mSysUIContext.getContentResolver(), "sysui_qs_tiles");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(string.split(aa.f3429b)));
        int iIndexOf = arrayList.indexOf("internet");
        if (iIndexOf == -1 || arrayList.contains(TileSpecsKt.TILE_SPEC_WIFI) || arrayList.contains("cell")) {
            return;
        }
        arrayList.set(iIndexOf, TileSpecsKt.TILE_SPEC_WIFI);
        arrayList.add(iIndexOf, "cell");
        Log.d(TAG, "revert InternetTile!");
        Settings.Secure.putString(this.mSysUIContext.getContentResolver(), "sysui_qs_tiles", TextUtils.join(aa.f3429b, arrayList));
    }
}
