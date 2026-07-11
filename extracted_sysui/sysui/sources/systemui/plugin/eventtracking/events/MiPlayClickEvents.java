package systemui.plugin.eventtracking.events;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import miui.systemui.miplay.MiPlayDetailActivity;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_miplay_systemui_click")
public final class MiPlayClickEvents implements BaseMiPlayEvent {

    @EventKey(key = "content_type")
    private final String content_type;

    @EventKey(key = "has_cast")
    private final boolean has_cast;
    private final boolean music_program;

    @EventKey(key = "page")
    private final String page;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.PHONE_TYPE)
    private final String phone_type;

    @EventKey(key = MiPlayDetailActivity.EXTRA_PARAM_REF)
    private final String ref;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screen_type;

    @EventKey(key = "source_package")
    private final String source_package;

    @EventKey(key = TypedValues.AttributesType.S_TARGET)
    private final String target;

    public MiPlayClickEvents(String page, String target, String str, boolean z2, String str2, String str3, boolean z3, String str4, String str5) {
        n.g(page, "page");
        n.g(target, "target");
        this.page = page;
        this.target = target;
        this.ref = str;
        this.music_program = z2;
        this.content_type = str2;
        this.source_package = str3;
        this.has_cast = z3;
        this.phone_type = str4;
        this.screen_type = str5;
    }

    public final String component1() {
        return this.page;
    }

    public final String component2() {
        return this.target;
    }

    public final String component3() {
        return this.ref;
    }

    public final boolean component4() {
        return this.music_program;
    }

    public final String component5() {
        return this.content_type;
    }

    public final String component6() {
        return this.source_package;
    }

    public final boolean component7() {
        return this.has_cast;
    }

    public final String component8() {
        return this.phone_type;
    }

    public final String component9() {
        return this.screen_type;
    }

    public final MiPlayClickEvents copy(String page, String target, String str, boolean z2, String str2, String str3, boolean z3, String str4, String str5) {
        n.g(page, "page");
        n.g(target, "target");
        return new MiPlayClickEvents(page, target, str, z2, str2, str3, z3, str4, str5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MiPlayClickEvents)) {
            return false;
        }
        MiPlayClickEvents miPlayClickEvents = (MiPlayClickEvents) obj;
        return n.c(this.page, miPlayClickEvents.page) && n.c(this.target, miPlayClickEvents.target) && n.c(this.ref, miPlayClickEvents.ref) && this.music_program == miPlayClickEvents.music_program && n.c(this.content_type, miPlayClickEvents.content_type) && n.c(this.source_package, miPlayClickEvents.source_package) && this.has_cast == miPlayClickEvents.has_cast && n.c(this.phone_type, miPlayClickEvents.phone_type) && n.c(this.screen_type, miPlayClickEvents.screen_type);
    }

    public final String getContent_type() {
        return this.content_type;
    }

    public final boolean getHas_cast() {
        return this.has_cast;
    }

    public final boolean getMusic_program() {
        return this.music_program;
    }

    public final String getPage() {
        return this.page;
    }

    public final String getPhone_type() {
        return this.phone_type;
    }

    public final String getRef() {
        return this.ref;
    }

    public final String getScreen_type() {
        return this.screen_type;
    }

    public final String getSource_package() {
        return this.source_package;
    }

    public final String getTarget() {
        return this.target;
    }

    public int hashCode() {
        int iHashCode = ((this.page.hashCode() * 31) + this.target.hashCode()) * 31;
        String str = this.ref;
        int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Boolean.hashCode(this.music_program)) * 31;
        String str2 = this.content_type;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.source_package;
        int iHashCode4 = (((iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31) + Boolean.hashCode(this.has_cast)) * 31;
        String str4 = this.phone_type;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.screen_type;
        return iHashCode5 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        return "MiPlayClickEvents(page=" + this.page + ", target=" + this.target + ", ref=" + this.ref + ", music_program=" + this.music_program + ", content_type=" + this.content_type + ", source_package=" + this.source_package + ", has_cast=" + this.has_cast + ", phone_type=" + this.phone_type + ", screen_type=" + this.screen_type + ")";
    }
}
