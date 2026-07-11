package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import miui.systemui.miplay.MiPlayDetailActivity;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_miplay_systemui_expose")
public final class MiPlayExposeEvent implements BaseMiPlayEvent {

    @EventKey(key = "content_type")
    private final String content_type;
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

    public MiPlayExposeEvent(String page, String str, boolean z2, String str2, String str3, String str4, String str5) {
        n.g(page, "page");
        this.page = page;
        this.ref = str;
        this.music_program = z2;
        this.content_type = str2;
        this.source_package = str3;
        this.phone_type = str4;
        this.screen_type = str5;
    }

    public static /* synthetic */ MiPlayExposeEvent copy$default(MiPlayExposeEvent miPlayExposeEvent, String str, String str2, boolean z2, String str3, String str4, String str5, String str6, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = miPlayExposeEvent.page;
        }
        if ((i2 & 2) != 0) {
            str2 = miPlayExposeEvent.ref;
        }
        String str7 = str2;
        if ((i2 & 4) != 0) {
            z2 = miPlayExposeEvent.music_program;
        }
        boolean z3 = z2;
        if ((i2 & 8) != 0) {
            str3 = miPlayExposeEvent.content_type;
        }
        String str8 = str3;
        if ((i2 & 16) != 0) {
            str4 = miPlayExposeEvent.source_package;
        }
        String str9 = str4;
        if ((i2 & 32) != 0) {
            str5 = miPlayExposeEvent.phone_type;
        }
        String str10 = str5;
        if ((i2 & 64) != 0) {
            str6 = miPlayExposeEvent.screen_type;
        }
        return miPlayExposeEvent.copy(str, str7, z3, str8, str9, str10, str6);
    }

    public final String component1() {
        return this.page;
    }

    public final String component2() {
        return this.ref;
    }

    public final boolean component3() {
        return this.music_program;
    }

    public final String component4() {
        return this.content_type;
    }

    public final String component5() {
        return this.source_package;
    }

    public final String component6() {
        return this.phone_type;
    }

    public final String component7() {
        return this.screen_type;
    }

    public final MiPlayExposeEvent copy(String page, String str, boolean z2, String str2, String str3, String str4, String str5) {
        n.g(page, "page");
        return new MiPlayExposeEvent(page, str, z2, str2, str3, str4, str5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MiPlayExposeEvent)) {
            return false;
        }
        MiPlayExposeEvent miPlayExposeEvent = (MiPlayExposeEvent) obj;
        return n.c(this.page, miPlayExposeEvent.page) && n.c(this.ref, miPlayExposeEvent.ref) && this.music_program == miPlayExposeEvent.music_program && n.c(this.content_type, miPlayExposeEvent.content_type) && n.c(this.source_package, miPlayExposeEvent.source_package) && n.c(this.phone_type, miPlayExposeEvent.phone_type) && n.c(this.screen_type, miPlayExposeEvent.screen_type);
    }

    public final String getContent_type() {
        return this.content_type;
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

    public int hashCode() {
        int iHashCode = this.page.hashCode() * 31;
        String str = this.ref;
        int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Boolean.hashCode(this.music_program)) * 31;
        String str2 = this.content_type;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.source_package;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.phone_type;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.screen_type;
        return iHashCode5 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        return "MiPlayExposeEvent(page=" + this.page + ", ref=" + this.ref + ", music_program=" + this.music_program + ", content_type=" + this.content_type + ", source_package=" + this.source_package + ", phone_type=" + this.phone_type + ", screen_type=" + this.screen_type + ")";
    }
}
