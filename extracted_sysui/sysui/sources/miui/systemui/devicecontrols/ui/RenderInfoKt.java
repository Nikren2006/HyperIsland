package miui.systemui.devicecontrols.ui;

import java.util.Map;
import miui.systemui.devicecontrols.R;

/* JADX INFO: loaded from: classes3.dex */
public final class RenderInfoKt {
    private static final int BUCKET_SIZE = 1000;
    private static final int THERMOSTAT_RANGE = 49000;
    private static final Map<Integer, H0.i> deviceColorMap = I0.E.b(I0.G.h(H0.o.a(49001, new H0.i(Integer.valueOf(R.color.device_controls_default_foreground), Integer.valueOf(R.color.device_controls_default_background_themed_color))), H0.o.a(49002, new H0.i(Integer.valueOf(R.color.thermo_heat_foreground), Integer.valueOf(R.color.control_enabled_thermo_heat_background))), H0.o.a(49003, new H0.i(Integer.valueOf(R.color.thermo_cool_foreground), Integer.valueOf(R.color.control_enabled_thermo_cool_background))), H0.o.a(13, new H0.i(Integer.valueOf(R.color.light_foreground), Integer.valueOf(R.color.control_enabled_light_background))), H0.o.a(50, new H0.i(Integer.valueOf(R.color.control_foreground), Integer.valueOf(R.color.control_enabled_default_background)))), RenderInfoKt$deviceColorMap$1.INSTANCE);
    private static final Map<Integer, Integer> deviceIconMap;

    static {
        int i2 = R.drawable.ic_device_thermostat_off;
        H0.i iVarA = H0.o.a(49001, Integer.valueOf(i2));
        int i3 = R.drawable.ic_device_thermostat;
        H0.i iVarA2 = H0.o.a(49002, Integer.valueOf(i3));
        H0.i iVarA3 = H0.o.a(49003, Integer.valueOf(i3));
        H0.i iVarA4 = H0.o.a(49004, Integer.valueOf(i3));
        H0.i iVarA5 = H0.o.a(49005, Integer.valueOf(i2));
        H0.i iVarA6 = H0.o.a(49, Integer.valueOf(i3));
        H0.i iVarA7 = H0.o.a(13, Integer.valueOf(R.drawable.ic_device_light));
        H0.i iVarA8 = H0.o.a(50, Integer.valueOf(R.drawable.ic_device_camera));
        H0.i iVarA9 = H0.o.a(45, Integer.valueOf(R.drawable.ic_device_lock));
        H0.i iVarA10 = H0.o.a(21, Integer.valueOf(R.drawable.ic_device_switch));
        H0.i iVarA11 = H0.o.a(15, Integer.valueOf(R.drawable.ic_device_outlet));
        H0.i iVarA12 = H0.o.a(32, Integer.valueOf(R.drawable.ic_device_vacuum));
        H0.i iVarA13 = H0.o.a(26, Integer.valueOf(R.drawable.ic_device_mop));
        int i4 = R.drawable.ic_device_air_freshener;
        H0.i iVarA14 = H0.o.a(3, Integer.valueOf(i4));
        H0.i iVarA15 = H0.o.a(4, Integer.valueOf(R.drawable.ic_device_air_purifier));
        H0.i iVarA16 = H0.o.a(8, Integer.valueOf(R.drawable.ic_device_fan));
        H0.i iVarA17 = H0.o.a(10, Integer.valueOf(R.drawable.ic_device_hood));
        int i5 = R.drawable.ic_device_kettle;
        H0.i iVarA18 = H0.o.a(12, Integer.valueOf(i5));
        H0.i iVarA19 = H0.o.a(14, Integer.valueOf(R.drawable.ic_device_microwave));
        H0.i iVarA20 = H0.o.a(17, Integer.valueOf(R.drawable.ic_device_remote_control));
        H0.i iVarA21 = H0.o.a(18, Integer.valueOf(R.drawable.ic_device_set_top));
        H0.i iVarA22 = H0.o.a(20, Integer.valueOf(R.drawable.ic_device_styler));
        H0.i iVarA23 = H0.o.a(22, Integer.valueOf(R.drawable.ic_device_tv));
        H0.i iVarA24 = H0.o.a(23, Integer.valueOf(R.drawable.ic_device_water_heater));
        H0.i iVarA25 = H0.o.a(24, Integer.valueOf(R.drawable.ic_device_dishwasher));
        H0.i iVarA26 = H0.o.a(28, Integer.valueOf(R.drawable.ic_device_multicooker));
        H0.i iVarA27 = H0.o.a(30, Integer.valueOf(R.drawable.ic_device_sprinkler));
        int i6 = R.drawable.ic_device_washer;
        H0.i iVarA28 = H0.o.a(31, Integer.valueOf(i6));
        int i7 = R.drawable.ic_device_blinds;
        H0.i iVarA29 = H0.o.a(34, Integer.valueOf(i7));
        int i8 = R.drawable.ic_device_drawer;
        H0.i iVarA30 = H0.o.a(38, Integer.valueOf(i8));
        H0.i iVarA31 = H0.o.a(39, Integer.valueOf(R.drawable.ic_device_garage));
        H0.i iVarA32 = H0.o.a(40, Integer.valueOf(R.drawable.ic_device_gate));
        int i9 = R.drawable.ic_device_pergola;
        H0.i iVarA33 = H0.o.a(41, Integer.valueOf(i9));
        int i10 = R.drawable.ic_device_window;
        deviceIconMap = I0.E.b(I0.G.h(iVarA, iVarA2, iVarA3, iVarA4, iVarA5, iVarA6, iVarA7, iVarA8, iVarA9, iVarA10, iVarA11, iVarA12, iVarA13, iVarA14, iVarA15, iVarA16, iVarA17, iVarA18, iVarA19, iVarA20, iVarA21, iVarA22, iVarA23, iVarA24, iVarA25, iVarA26, iVarA27, iVarA28, iVarA29, iVarA30, iVarA31, iVarA32, iVarA33, H0.o.a(43, Integer.valueOf(i10)), H0.o.a(44, Integer.valueOf(R.drawable.ic_device_valve)), H0.o.a(46, Integer.valueOf(R.drawable.ic_device_security_system)), H0.o.a(48, Integer.valueOf(R.drawable.ic_device_refrigerator)), H0.o.a(51, Integer.valueOf(R.drawable.ic_device_doorbell)), H0.o.a(52, -1), H0.o.a(1, Integer.valueOf(i3)), H0.o.a(2, Integer.valueOf(i3)), H0.o.a(5, Integer.valueOf(i5)), H0.o.a(6, Integer.valueOf(i4)), H0.o.a(16, Integer.valueOf(i3)), H0.o.a(19, Integer.valueOf(R.drawable.ic_device_cooking)), H0.o.a(7, Integer.valueOf(R.drawable.ic_device_display)), H0.o.a(25, Integer.valueOf(i6)), H0.o.a(27, Integer.valueOf(R.drawable.ic_device_outdoor_garden)), H0.o.a(29, Integer.valueOf(R.drawable.ic_device_water)), H0.o.a(33, Integer.valueOf(i9)), H0.o.a(35, Integer.valueOf(i8)), H0.o.a(36, Integer.valueOf(i7)), H0.o.a(37, Integer.valueOf(R.drawable.ic_device_door)), H0.o.a(42, Integer.valueOf(i10)), H0.o.a(47, Integer.valueOf(i3)), H0.o.a(-1000, Integer.valueOf(R.drawable.ic_error_outline))), RenderInfoKt$deviceIconMap$1.INSTANCE);
    }
}
