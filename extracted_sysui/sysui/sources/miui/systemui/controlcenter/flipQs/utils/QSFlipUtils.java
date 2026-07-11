package miui.systemui.controlcenter.flipQs.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.flipQs.adapter.QSFlipAdapter;
import miui.systemui.controlcenter.flipQs.adapter.QSFlipHolderStrategy;
import miui.systemui.controlcenter.flipQs.wrap.QSFlipTileWrap;

/* JADX INFO: loaded from: classes.dex */
public final class QSFlipUtils {
    public static final QSFlipUtils INSTANCE = new QSFlipUtils();
    public static final int QS_FLIP_SETTING_FOOTER_COUNT = 1;
    public static final int QS_FLIP_SETTING_HEADER_COUNT = 1;
    public static final int QS_FLIP_SETTING_LEAST_COUNT = 6;
    public static final int QS_FLIP_SETTING_RECYCLERVIEW_SPAN_COUNT = 3;
    public static final int QS_FLIP_SETTING_RECYCLERVIEW_TILE_COUNT = 1;
    public static final int QS_FLIP_SETTING_VIEW_TILE_ADDED = 10;
    public static final int QS_FLIP_SETTING_VIEW_TILE_NOT_ADDED = 11;
    public static final int QS_FLIP_SETTING_VIEW_TYPE_FOOTER = 2;
    public static final int QS_FLIP_SETTING_VIEW_TYPE_HEADER = 0;
    public static final int QS_FLIP_SETTING_VIEW_TYPE_TILE_ITEM = 1;
    public static final String SYSTEM_UI_FLIP_QS_TILES = "sysui_flip_qs_tiles";
    public static final String SYSTEM_UI_SUPPORT_FLIP_QS_TILES = "sysui_flip_support_qs_tiles";
    private static final String TAG = "QSFlipUtils";
    public static final String TILE_TYPE_AIRPLANE = "airplane";
    public static final String TILE_TYPE_AUTO_BRIGHTNESS = "autobrightness";
    public static final String TILE_TYPE_BATTERY_SAVER = "batterysaver";
    public static final String TILE_TYPE_BT = "bt";
    public static final String TILE_TYPE_FLASHLIGHT = "flashlight";
    public static final String TILE_TYPE_GPS = "gps";
    public static final String TILE_TYPE_HOTSPOT = "hotspot";
    public static final String TILE_TYPE_MUTE = "mute";
    public static final String TILE_TYPE_NFC = "nfc";
    public static final String TILE_TYPE_NIGHT = "night";
    public static final String TILE_TYPE_PAPER_MODE = "papermode";
    public static final String TILE_TYPE_QUIET_MODE = "quietmode";
    public static final String TILE_TYPE_ROTATION = "rotation";
    public static final String TILE_TYPE_SCREENSHOT = "screenshot";
    public static final String TILE_TYPE_SCREEN_LOCK = "screenlock";
    public static final String TILE_TYPE_SCREEN_RECORD = "custom(com.miui.screenrecorder/.service.QuickService)";
    private static final String TILE_TYPE_UNKNOWN = "unknown";
    public static final String TILE_TYPE_VIBRATE = "vibrate";
    private static Context context;

    private QSFlipUtils() {
    }

    private final void addGroupFooter(List<QSFlipTileWrap> list, QSFlipTileWrap qSFlipTileWrap) {
        list.add(qSFlipTileWrap);
    }

    private final void addGroupHeader(List<QSFlipTileWrap> list, QSFlipTileWrap qSFlipTileWrap) {
        list.add(qSFlipTileWrap);
    }

    private final void addShortcutInfoList(List<QSFlipTileWrap> list, int i2, List<QSFlipTileWrap> list2) {
        for (QSFlipTileWrap qSFlipTileWrap : list2) {
            list.add(new QSFlipTileWrap(i2, 0, qSFlipTileWrap.getSpec(), qSFlipTileWrap.getName(), qSFlipTileWrap.getIconId(), 2, null));
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final int getTileIconId(String str) {
        switch (str.hashCode()) {
            case -1672707928:
                if (str.equals("batterysaver")) {
                    return R.drawable.flip_qs_battery_saver;
                }
                break;
            case -1183073498:
                if (str.equals("flashlight")) {
                    return R.drawable.flip_qs_flashlight;
                }
                break;
            case -919209152:
                if (str.equals("autobrightness")) {
                    return R.drawable.flip_qs_auto_brightness;
                }
                break;
            case -677011630:
                if (str.equals(TILE_TYPE_AIRPLANE)) {
                    return R.drawable.flip_qs_airplane;
                }
                break;
            case -416649321:
                if (str.equals(TILE_TYPE_SCREEN_LOCK)) {
                    return R.drawable.flip_qs_screen_lock;
                }
                break;
            case -416447130:
                if (str.equals(TILE_TYPE_SCREENSHOT)) {
                    return R.drawable.flip_qs_screen_shot;
                }
                break;
            case -40300674:
                if (str.equals("rotation")) {
                    return R.drawable.flip_qs_rotation_lock;
                }
                break;
            case 3154:
                if (str.equals("bt")) {
                    return R.drawable.flip_qs_bluetooth;
                }
                break;
            case 102570:
                if (str.equals(TILE_TYPE_GPS)) {
                    return R.drawable.flip_qs_gps;
                }
                break;
            case 108971:
                if (str.equals("nfc")) {
                    return R.drawable.flip_qs_nfc;
                }
                break;
            case 3363353:
                if (str.equals("mute")) {
                    return R.drawable.flip_qs_mute;
                }
                break;
            case 104817688:
                if (str.equals("night")) {
                    return R.drawable.flip_qs_night_mode;
                }
                break;
            case 298820911:
                if (str.equals("papermode")) {
                    return R.drawable.flip_qs_paper_mode;
                }
                break;
            case 451310959:
                if (str.equals(TILE_TYPE_VIBRATE)) {
                    return R.drawable.flip_qs_vibrate;
                }
                break;
            case 1099603663:
                if (str.equals(TILE_TYPE_HOTSPOT)) {
                    return R.drawable.flip_qs_hotspot;
                }
                break;
            case 1367090647:
                if (str.equals("quietmode")) {
                    return R.drawable.flip_qs_quiet;
                }
                break;
            case 1623569285:
                if (str.equals(TILE_TYPE_SCREEN_RECORD)) {
                    return R.drawable.flip_qs_screen_record;
                }
                break;
        }
        return R.drawable.flip_qs_nfc;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final int getTileName(String str) {
        switch (str.hashCode()) {
            case -1672707928:
                if (str.equals("batterysaver")) {
                    return R.string.qs_flip_tile_batterysaver;
                }
                break;
            case -1183073498:
                if (str.equals("flashlight")) {
                    return R.string.qs_flip_tile_flashlight;
                }
                break;
            case -919209152:
                if (str.equals("autobrightness")) {
                    return R.string.qs_flip_tile_autobrightness;
                }
                break;
            case -677011630:
                if (str.equals(TILE_TYPE_AIRPLANE)) {
                    return R.string.qs_flip_tile_airplane;
                }
                break;
            case -416649321:
                if (str.equals(TILE_TYPE_SCREEN_LOCK)) {
                    return R.string.qs_flip_tile_screenlock;
                }
                break;
            case -416447130:
                if (str.equals(TILE_TYPE_SCREENSHOT)) {
                    return R.string.qs_flip_tile_screenshot;
                }
                break;
            case -40300674:
                if (str.equals("rotation")) {
                    return R.string.qs_flip_tile_rotation;
                }
                break;
            case 3154:
                if (str.equals("bt")) {
                    return R.string.qs_flip_tile_bt;
                }
                break;
            case 102570:
                if (str.equals(TILE_TYPE_GPS)) {
                    return R.string.qs_flip_tile_gps;
                }
                break;
            case 108971:
                if (str.equals("nfc")) {
                    return R.string.qs_flip_tile_nfc;
                }
                break;
            case 3363353:
                if (str.equals("mute")) {
                    return R.string.qs_flip_tile_mute;
                }
                break;
            case 104817688:
                if (str.equals("night")) {
                    return R.string.qs_flip_tile_night;
                }
                break;
            case 298820911:
                if (str.equals("papermode")) {
                    return R.string.qs_flip_tile_papermode;
                }
                break;
            case 451310959:
                if (str.equals(TILE_TYPE_VIBRATE)) {
                    return R.string.qs_flip_tile_vibrate;
                }
                break;
            case 1099603663:
                if (str.equals(TILE_TYPE_HOTSPOT)) {
                    return R.string.qs_flip_tile_hotspot;
                }
                break;
            case 1367090647:
                if (str.equals("quietmode")) {
                    return R.string.qs_flip_tile_quietmode;
                }
                break;
            case 1623569285:
                if (str.equals(TILE_TYPE_SCREEN_RECORD)) {
                    return R.string.qs_flip_tile_screenrecorder;
                }
                break;
        }
        return R.string.qs_flip_tile_unknown;
    }

    public final List<QSFlipTileWrap> createTileWrap(List<String> tiles, boolean z2) {
        String string;
        n.g(tiles, "tiles");
        ArrayList arrayList = new ArrayList();
        int i2 = z2 ? 10 : 11;
        for (String str : tiles) {
            Context context2 = context;
            if (context2 != null) {
                n.d(context2);
                string = context2.getString(INSTANCE.getTileName(str));
            } else {
                string = "";
            }
            arrayList.add(new QSFlipTileWrap(i2, 0, str, string, Integer.valueOf(INSTANCE.getTileIconId(str)), 2, null));
        }
        return arrayList;
    }

    public final int getAddedHeaderPosition(List<QSFlipTileWrap> itemList) {
        n.g(itemList, "itemList");
        return itemList.indexOf(QSFlipHolderStrategy.Companion.getMAddedHeader());
    }

    public final int getFooterPosition(List<QSFlipTileWrap> itemList) {
        n.g(itemList, "itemList");
        return itemList.indexOf(QSFlipHolderStrategy.Companion.getMFooter());
    }

    public final int getNotAddedHeaderPosition(List<QSFlipTileWrap> itemList) {
        n.g(itemList, "itemList");
        return itemList.indexOf(QSFlipHolderStrategy.Companion.getMNotAddedHeader());
    }

    public final void init(Context mContext) {
        n.g(mContext, "mContext");
        context = mContext;
    }

    public final boolean isNightMode() {
        Context context2 = context;
        if (context2 == null) {
            Log.d(TAG, "isNightMode: context is null");
            return false;
        }
        n.d(context2);
        Configuration configuration = context2.getResources().getConfiguration();
        n.f(configuration, "getConfiguration(...)");
        return (configuration.uiMode & 48) == 32;
    }

    public final void notifyHeaderUnderLine(List<QSFlipTileWrap> itemList, QSFlipAdapter adapter) {
        n.g(itemList, "itemList");
        n.g(adapter, "adapter");
        if (getNotAddedHeaderPosition(itemList) != -1) {
            adapter.notifyItemChanged(getNotAddedHeaderPosition(itemList));
        }
    }

    public final void onDestroy() {
        context = null;
    }

    public final void onShowTilesList(List<QSFlipTileWrap> addedList, List<QSFlipTileWrap> notAddedList, List<QSFlipTileWrap> qsFlipTileList, QSFlipAdapter qSFlipAdapter, boolean z2) {
        n.g(addedList, "addedList");
        n.g(notAddedList, "notAddedList");
        n.g(qsFlipTileList, "qsFlipTileList");
        qsFlipTileList.clear();
        if (addedList.size() > 0) {
            addGroupHeader(qsFlipTileList, QSFlipHolderStrategy.Companion.getMAddedHeader());
        }
        addShortcutInfoList(qsFlipTileList, 10, addedList);
        if (notAddedList.size() > 0) {
            addGroupHeader(qsFlipTileList, QSFlipHolderStrategy.Companion.getMNotAddedHeader());
        }
        addShortcutInfoList(qsFlipTileList, 11, notAddedList);
        addGroupFooter(qsFlipTileList, QSFlipHolderStrategy.Companion.getMFooter());
        if (qSFlipAdapter != null) {
            qSFlipAdapter.refresh(qsFlipTileList, z2);
        }
    }

    public final void removeAndAddItem(List<QSFlipTileWrap> itemList, int i2, int i3, int i4, QSFlipAdapter adapter) {
        n.g(itemList, "itemList");
        n.g(adapter, "adapter");
        Log.d(TAG, "removeAndAddItem: fromIndex = " + i2 + ", toIndex = " + i3 + ", type = " + i4);
        if (i2 < 0 || i2 >= itemList.size() || i3 < 0 || i3 >= itemList.size()) {
            return;
        }
        QSFlipTileWrap qSFlipTileWrap = itemList.get(i2);
        qSFlipTileWrap.setType(i4);
        itemList.remove(i2);
        itemList.add(i3, qSFlipTileWrap);
        adapter.notifyItemMoved(i2, i3);
    }
}
