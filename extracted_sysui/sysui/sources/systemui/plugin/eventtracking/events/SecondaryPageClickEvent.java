package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "click")
public final class SecondaryPageClickEvent implements DeviceCenterEvent {

    @EventKey(key = "click_content")
    private final String clickContent;

    @EventKey(key = "group")
    private final String group;

    @EventKey(key = "page")
    private final String page;

    public SecondaryPageClickEvent(String clickContent, String page, String group) {
        n.g(clickContent, "clickContent");
        n.g(page, "page");
        n.g(group, "group");
        this.clickContent = clickContent;
        this.page = page;
        this.group = group;
    }

    public static /* synthetic */ SecondaryPageClickEvent copy$default(SecondaryPageClickEvent secondaryPageClickEvent, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = secondaryPageClickEvent.clickContent;
        }
        if ((i2 & 2) != 0) {
            str2 = secondaryPageClickEvent.page;
        }
        if ((i2 & 4) != 0) {
            str3 = secondaryPageClickEvent.group;
        }
        return secondaryPageClickEvent.copy(str, str2, str3);
    }

    public final String component1() {
        return this.clickContent;
    }

    public final String component2() {
        return this.page;
    }

    public final String component3() {
        return this.group;
    }

    public final SecondaryPageClickEvent copy(String clickContent, String page, String group) {
        n.g(clickContent, "clickContent");
        n.g(page, "page");
        n.g(group, "group");
        return new SecondaryPageClickEvent(clickContent, page, group);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SecondaryPageClickEvent)) {
            return false;
        }
        SecondaryPageClickEvent secondaryPageClickEvent = (SecondaryPageClickEvent) obj;
        return n.c(this.clickContent, secondaryPageClickEvent.clickContent) && n.c(this.page, secondaryPageClickEvent.page) && n.c(this.group, secondaryPageClickEvent.group);
    }

    public final String getClickContent() {
        return this.clickContent;
    }

    public final String getGroup() {
        return this.group;
    }

    public final String getPage() {
        return this.page;
    }

    public int hashCode() {
        return (((this.clickContent.hashCode() * 31) + this.page.hashCode()) * 31) + this.group.hashCode();
    }

    public String toString() {
        return "SecondaryPageClickEvent(clickContent=" + this.clickContent + ", page=" + this.page + ", group=" + this.group + ")";
    }

    public /* synthetic */ SecondaryPageClickEvent(String str, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? DeviceCenterEventsKt.PAGE_DEVICE_CENTER_EXPOSE : str2, (i2 & 4) != 0 ? DeviceCenterEventsKt.GROUP_SECONDARY_PAGE : str3);
    }
}
