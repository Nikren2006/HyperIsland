package miui.systemui.notification.focus.templateV3;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
import com.android.systemui.miui.notification.R;
import com.android.systemui.plugins.miui.notification.FocusNotificationContent;
import g1.AbstractC0360b0;
import g1.AbstractC0367f;
import g1.AbstractC0369g;
import g1.E;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.coroutines.Dispatchers;
import miui.systemui.notification.NotificationUtil;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.FocusIconCache;
import miui.systemui.notification.focus.FocusNotifUtils;
import miui.systemui.notification.focus.FocusParamsException;
import miui.systemui.notification.focus.InflateAndAuthCallBack;
import miui.systemui.notification.focus.model.AnimTextInfo;
import miui.systemui.notification.focus.model.BaseInfo;
import miui.systemui.notification.focus.model.BgInfo;
import miui.systemui.notification.focus.model.ChatInfo;
import miui.systemui.notification.focus.model.CoverInfo;
import miui.systemui.notification.focus.model.HighlightInfo;
import miui.systemui.notification.focus.model.HintInfo;
import miui.systemui.notification.focus.model.IconTextInfo;
import miui.systemui.notification.focus.model.PicInfo;
import miui.systemui.notification.focus.model.ProgressInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.notification.focus.templateV3.TemplateBuilderV3;
import miui.systemui.notification.focus.templateV3.TemplateDecoBuilderV3;
import miui.systemui.notification.focus.templateV3.TemplateDecoLandBuilderV3;
import miui.systemui.notification.focus.templateV3.TemplateTinyBuilderV3;
import miui.systemui.util.FocusNotificationUtil;
import miui.systemui.util.concurrency.ConcurrencyModule;
import org.json.JSONObject;
import p1.a;

/* JADX INFO: loaded from: classes4.dex */
public final class TemplateFactoryV3 {
    private static final String AREA_A = "area_a";
    private static final String AREA_C = "area_c";
    private static final String AREA_D = "area_d";
    private static final String AREA_E = "area_e";
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "TemplateFactory";
    private final Map<String, TemplateDecoLandBuilderV3> builderDecoLandMap;
    private final Map<String, TemplateDecoBuilderV3> builderDecoMap;
    private final Map<String, TemplateBuilderV3> builderMap;
    private final Map<String, TemplateTinyBuilderV3> builderTinyMap;
    private final FocusNotifUtils focusNotifUtils;
    private boolean isFlipDevice;
    private final ConcurrentHashMap<String, a> keyLocks;
    private final TemplateBuilderV3.Factory templateBuilderV3Factory;
    private final TemplateDecoBuilderV3.Factory templateDecoBuilderV3Factory;
    private final TemplateDecoLandBuilderV3.Factory templateDecoLandBuilderV3Factory;
    private final Map<String, Template> templateMap;
    private final TemplateTinyBuilderV3.Factory templateTinyBuilderV3Factory;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isBgPicDownloadFailStatic(Template template, StatusBarNotification sbn) {
            Icon icon;
            n.g(template, "template");
            n.g(sbn, "sbn");
            Bundle bundle = sbn.getNotification().extras.getBundle("miui.focus.pics");
            BgInfo bgInfo = template.getBgInfo();
            String picBg = bgInfo != null ? bgInfo.getPicBg() : null;
            if (bundle == null || (icon = (Icon) bundle.getParcelable(picBg)) == null) {
                String key = sbn.getKey();
                icon = key != null ? FocusIconCache.INSTANCE.get(key, picBg) : null;
            }
            if (bundle != null) {
                BgInfo bgInfo2 = template.getBgInfo();
                if (bundle.containsKey(bgInfo2 != null ? bgInfo2.getPicBg() : null) && icon == null) {
                    return true;
                }
            }
            return false;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.templateV3.TemplateFactoryV3$createTemplate$1, reason: invalid class name */
    @f(c = "miui.systemui.notification.focus.templateV3.TemplateFactoryV3$createTemplate$1", f = "TemplateFactoryV3.kt", l = {944}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ FocusNotificationContent $focusContent;
        final /* synthetic */ InflateAndAuthCallBack $inflateCallBack;
        final /* synthetic */ boolean $isFlip;
        final /* synthetic */ StatusBarNotification $sbn;
        final /* synthetic */ Template $template;
        int label;

        /* JADX INFO: renamed from: miui.systemui.notification.focus.templateV3.TemplateFactoryV3$createTemplate$1$1, reason: invalid class name and collision with other inner class name */
        @f(c = "miui.systemui.notification.focus.templateV3.TemplateFactoryV3$createTemplate$1$1", f = "TemplateFactoryV3.kt", l = {1046, 950, 977}, m = "invokeSuspend")
        public static final class C01511 extends l implements Function2 {
            final /* synthetic */ FocusNotificationContent $focusContent;
            final /* synthetic */ InflateAndAuthCallBack $inflateCallBack;
            final /* synthetic */ boolean $isFlip;
            final /* synthetic */ StatusBarNotification $sbn;
            final /* synthetic */ Template $template;
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            boolean Z$0;
            int label;
            final /* synthetic */ TemplateFactoryV3 this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01511(TemplateFactoryV3 templateFactoryV3, StatusBarNotification statusBarNotification, Template template, FocusNotificationContent focusNotificationContent, InflateAndAuthCallBack inflateAndAuthCallBack, boolean z2, d dVar) {
                super(2, dVar);
                this.this$0 = templateFactoryV3;
                this.$sbn = statusBarNotification;
                this.$template = template;
                this.$focusContent = focusNotificationContent;
                this.$inflateCallBack = inflateAndAuthCallBack;
                this.$isFlip = z2;
            }

            @Override // N0.a
            public final d create(Object obj, d dVar) {
                return new C01511(this.this$0, this.$sbn, this.$template, this.$focusContent, this.$inflateCallBack, this.$isFlip, dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(E e2, d dVar) {
                return ((C01511) create(e2, dVar)).invokeSuspend(s.f314a);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r2v3, types: [p1.a] */
            /* JADX WARN: Type inference failed for: r2v4 */
            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                a aVar;
                FocusNotificationContent focusNotificationContent;
                StatusBarNotification statusBarNotification;
                Template template;
                TemplateFactoryV3 templateFactoryV3;
                InflateAndAuthCallBack inflateAndAuthCallBack;
                boolean z2;
                Object objPutIfAbsent;
                Template template2;
                TemplateFactoryV3 templateFactoryV32;
                StatusBarNotification statusBarNotification2;
                a aVar2;
                s sVar;
                ?? C2 = c.c();
                int i2 = this.label;
                try {
                    try {
                        try {
                            if (i2 == 0) {
                                k.b(obj);
                                ConcurrentHashMap concurrentHashMap = this.this$0.keyLocks;
                                String key = this.$sbn.getKey();
                                Object objB = concurrentHashMap.get(key);
                                if (objB == null && (objPutIfAbsent = concurrentHashMap.putIfAbsent(key, (objB = p1.c.b(false, 1, null)))) != null) {
                                    objB = objPutIfAbsent;
                                }
                                aVar = (a) objB;
                                n.d(aVar);
                                TemplateFactoryV3 templateFactoryV33 = this.this$0;
                                Template template3 = this.$template;
                                StatusBarNotification statusBarNotification3 = this.$sbn;
                                focusNotificationContent = this.$focusContent;
                                InflateAndAuthCallBack inflateAndAuthCallBack2 = this.$inflateCallBack;
                                boolean z3 = this.$isFlip;
                                this.L$0 = aVar;
                                this.L$1 = templateFactoryV33;
                                this.L$2 = template3;
                                this.L$3 = statusBarNotification3;
                                this.L$4 = focusNotificationContent;
                                this.L$5 = inflateAndAuthCallBack2;
                                this.Z$0 = z3;
                                this.label = 1;
                                if (aVar.b(null, this) == C2) {
                                    return C2;
                                }
                                statusBarNotification = statusBarNotification3;
                                template = template3;
                                templateFactoryV3 = templateFactoryV33;
                                inflateAndAuthCallBack = inflateAndAuthCallBack2;
                                z2 = z3;
                            } else {
                                if (i2 != 1) {
                                    if (i2 == 2) {
                                        aVar2 = (a) this.L$0;
                                        k.b(obj);
                                        sVar = s.f314a;
                                        aVar2.a(null);
                                        return sVar;
                                    }
                                    if (i2 != 3) {
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    aVar2 = (a) this.L$0;
                                    k.b(obj);
                                    sVar = s.f314a;
                                    aVar2.a(null);
                                    return sVar;
                                }
                                z2 = this.Z$0;
                                inflateAndAuthCallBack = (InflateAndAuthCallBack) this.L$5;
                                focusNotificationContent = (FocusNotificationContent) this.L$4;
                                StatusBarNotification statusBarNotification4 = (StatusBarNotification) this.L$3;
                                Template template4 = (Template) this.L$2;
                                TemplateFactoryV3 templateFactoryV34 = (TemplateFactoryV3) this.L$1;
                                a aVar3 = (a) this.L$0;
                                k.b(obj);
                                statusBarNotification = statusBarNotification4;
                                template = template4;
                                templateFactoryV3 = templateFactoryV34;
                                aVar = aVar3;
                            }
                            n.d(template);
                            if (templateFactoryV3.isBgPicDownloadFail(template, statusBarNotification)) {
                                Log.e(Const.TAG, "createTemplateError: BgPicDownloadFail");
                                this.L$0 = aVar;
                                this.L$1 = null;
                                this.L$2 = null;
                                this.L$3 = null;
                                this.L$4 = null;
                                this.L$5 = null;
                                this.label = 2;
                                if (templateFactoryV3.resetErrorNotification(focusNotificationContent, statusBarNotification, inflateAndAuthCallBack, this) == C2) {
                                    return C2;
                                }
                                aVar2 = aVar;
                                sVar = s.f314a;
                                aVar2.a(null);
                                return sVar;
                            }
                            if (z2) {
                                template2 = template;
                                templateFactoryV32 = templateFactoryV3;
                                statusBarNotification2 = statusBarNotification;
                                try {
                                    TemplateFactoryV3.createTinyTemplateView$default(templateFactoryV3, template, statusBarNotification, false, focusNotificationContent, 4, null);
                                    if (!template2.getHideDeco()) {
                                        TemplateFactoryV3.createDecoTemplateView$default(templateFactoryV32, template2, statusBarNotification2, false, focusNotificationContent, 4, null);
                                        TemplateFactoryV3.createDecoLandTemplateView$default(templateFactoryV32, template2, statusBarNotification2, false, focusNotificationContent, 4, null);
                                    }
                                } catch (Exception e2) {
                                    StatusBarNotification statusBarNotification5 = statusBarNotification2;
                                    e2.printStackTrace();
                                    this.L$0 = aVar;
                                    this.L$1 = null;
                                    this.L$2 = null;
                                    this.L$3 = null;
                                    this.L$4 = null;
                                    this.L$5 = null;
                                    this.label = 3;
                                    if (templateFactoryV32.resetErrorNotification(focusNotificationContent, statusBarNotification5, inflateAndAuthCallBack, this) == C2) {
                                        return C2;
                                    }
                                    aVar2 = aVar;
                                }
                            } else {
                                template2 = template;
                                templateFactoryV32 = templateFactoryV3;
                                statusBarNotification2 = statusBarNotification;
                            }
                            TemplateFactoryV3.createStandardTemplateView$default(templateFactoryV32, template2, statusBarNotification2, false, focusNotificationContent, inflateAndAuthCallBack, 4, null);
                            AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new TemplateFactoryV3$createTemplate$1$1$1$1(inflateAndAuthCallBack, statusBarNotification2, null), 3, null);
                            aVar.a(null);
                            this.this$0.keyLocks.remove(this.$sbn.getKey());
                            return s.f314a;
                        } catch (Throwable th) {
                            th = th;
                            C2 = aVar;
                            C2.a(null);
                            throw th;
                        }
                    } finally {
                        this.this$0.keyLocks.remove(this.$sbn.getKey());
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(StatusBarNotification statusBarNotification, Template template, FocusNotificationContent focusNotificationContent, InflateAndAuthCallBack inflateAndAuthCallBack, boolean z2, d dVar) {
            super(2, dVar);
            this.$sbn = statusBarNotification;
            this.$template = template;
            this.$focusContent = focusNotificationContent;
            this.$inflateCallBack = inflateAndAuthCallBack;
            this.$isFlip = z2;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return TemplateFactoryV3.this.new AnonymousClass1(this.$sbn, this.$template, this.$focusContent, this.$inflateCallBack, this.$isFlip, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                AbstractC0360b0 io = Dispatchers.INSTANCE.getIO();
                C01511 c01511 = new C01511(TemplateFactoryV3.this, this.$sbn, this.$template, this.$focusContent, this.$inflateCallBack, this.$isFlip, null);
                this.label = 1;
                if (AbstractC0367f.c(io, c01511, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            return s.f314a;
        }
    }

    public TemplateFactoryV3(FocusNotifUtils focusNotifUtils, TemplateBuilderV3.Factory templateBuilderV3Factory, TemplateDecoBuilderV3.Factory templateDecoBuilderV3Factory, TemplateDecoLandBuilderV3.Factory templateDecoLandBuilderV3Factory, TemplateTinyBuilderV3.Factory templateTinyBuilderV3Factory) {
        n.g(focusNotifUtils, "focusNotifUtils");
        n.g(templateBuilderV3Factory, "templateBuilderV3Factory");
        n.g(templateDecoBuilderV3Factory, "templateDecoBuilderV3Factory");
        n.g(templateDecoLandBuilderV3Factory, "templateDecoLandBuilderV3Factory");
        n.g(templateTinyBuilderV3Factory, "templateTinyBuilderV3Factory");
        this.focusNotifUtils = focusNotifUtils;
        this.templateBuilderV3Factory = templateBuilderV3Factory;
        this.templateDecoBuilderV3Factory = templateDecoBuilderV3Factory;
        this.templateDecoLandBuilderV3Factory = templateDecoLandBuilderV3Factory;
        this.templateTinyBuilderV3Factory = templateTinyBuilderV3Factory;
        this.templateMap = new LinkedHashMap();
        this.builderMap = new LinkedHashMap();
        this.builderTinyMap = new LinkedHashMap();
        this.builderDecoMap = new LinkedHashMap();
        this.builderDecoLandMap = new LinkedHashMap();
        this.keyLocks = new ConcurrentHashMap<>();
    }

    private final void checkAreaA(StatusBarNotification statusBarNotification, Template template) {
        if (n.c(chooseModule$default(this, template, AREA_A, null, 4, null), "")) {
            Log.d(Const.TAG, "areaAModule is empty");
            statusBarNotification.getNotification().extras.putBoolean(Const.Param.SHOW_NOTIFICATION, false);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final String chooseDecoModule(Template template, String str) throws FocusParamsException {
        PicInfo picInfo;
        switch (str.hashCode()) {
            case -1409553105:
                if (str.equals(AREA_A)) {
                    BaseInfo baseInfo = template.getBaseInfo();
                    HighlightInfo highlightInfo = template.getHighlightInfo();
                    ChatInfo chatInfo = template.getChatInfo();
                    AnimTextInfo animTextInfo = template.getAnimTextInfo();
                    CoverInfo coverInfo = template.getCoverInfo();
                    IconTextInfo iconTextInfo = template.getIconTextInfo();
                    if (animTextInfo != null) {
                        return Const.Module.MODULE_ANIMATION_TEXT;
                    }
                    if (coverInfo != null) {
                        return Const.Module.MODULE_COVER;
                    }
                    if (iconTextInfo != null) {
                        return Const.Module.MODULE_NEW_IMAGE_TEXT;
                    }
                    if (baseInfo != null) {
                        if (baseInfo.getType() == null || TextUtils.isEmpty(baseInfo.getTitle())) {
                            throw new FocusParamsException("baseInfo param is error");
                        }
                        Integer type = baseInfo.getType();
                        return (type != null && type.intValue() == 2) ? Const.Module.MODULE_TEXT_2 : Const.Module.MODULE_TEXT_1;
                    }
                    if (highlightInfo != null) {
                        return highlightInfo.getType() == 1 ? Const.Module.MODULE_IMAGE_TEXT_HIGHLIGHT_TIME : Const.Module.MODULE_IMAGE_TEXT_HIGHLIGHT_SPORT;
                    }
                    if (chatInfo == null) {
                        Log.e(TAG, "has no AREA_A params");
                        return "";
                    }
                    if (TextUtils.isEmpty(chatInfo.getTitle())) {
                        throw new FocusParamsException("chatInfo param is error");
                    }
                    return Const.Module.MODULE_IMAGE_TEXT_IM;
                }
                return "";
            case -1409553104:
            default:
                return "";
            case -1409553103:
                if (str.equals(AREA_C)) {
                    if (template.getActions() != null) {
                        return Const.Module.MODULE_BUTTON_1;
                    }
                    PicInfo picInfo2 = template.getPicInfo();
                    if (picInfo2 != null) {
                        if (picInfo2.getType() == null) {
                            NotificationUtil.debugLog(Const.TAG, "picInfo type error");
                            return "";
                        }
                        Integer type2 = picInfo2.getType();
                        if (type2 != null && type2.intValue() == 5) {
                            return Const.Module.MODULE_MARK_TEXT_IMAGE;
                        }
                        if (type2 != null && type2.intValue() == 4) {
                            if (picInfo2.getActionInfo() != null) {
                                return Const.Module.MODULE_MARK_4;
                            }
                            NotificationUtil.debugLog(Const.TAG, "picInfo actionInfo error");
                            return "";
                        }
                        if (type2 != null && type2.intValue() == 3) {
                            if (picInfo2.getPic() != null) {
                                return Const.Module.MODULE_MARK_3;
                            }
                            NotificationUtil.debugLog(Const.TAG, "picInfo 3 pic error");
                            return "";
                        }
                        if (type2 != null && type2.intValue() == 2) {
                            if (picInfo2.getPic() != null) {
                                return Const.Module.MODULE_MARK_2;
                            }
                            NotificationUtil.debugLog(Const.TAG, "picInfo  2 pic error");
                            return "";
                        }
                    }
                }
                return "";
            case -1409553102:
                if (str.equals(AREA_D)) {
                    if (template.getTextButton() != null) {
                        return Const.Module.MODULE_BUTTON_4;
                    }
                    if (template.getHighlightInfoV3() != null) {
                        return Const.Module.MODULE_BUTTON_5;
                    }
                    if (template.getMultiProgressInfo() != null) {
                        return Const.Module.MODULE_MULTI_PROGRESS;
                    }
                    ProgressInfo progressInfo = template.getProgressInfo();
                    HintInfo hintInfo = template.getHintInfo();
                    if (progressInfo != null) {
                        progressInfo.getProgress();
                        if (progressInfo.getProgress() >= 0 && progressInfo.getColorProgress() != null && !TextUtils.isEmpty(progressInfo.getColorProgress())) {
                            return Const.Module.MODULE_PROGRESS;
                        }
                        NotificationUtil.debugLog(Const.TAG, "progressInfo param error");
                        return "";
                    }
                    if (hintInfo == null) {
                        return "";
                    }
                    if (hintInfo.getType() == null) {
                        NotificationUtil.debugLog(Const.TAG, "hintActionInfo type error");
                        return "";
                    }
                    Integer type3 = hintInfo.getType();
                    if (type3 != null && type3.intValue() == 2) {
                        if (!TextUtils.isEmpty(hintInfo.getContent())) {
                            return Const.Module.MODULE_BUTTON_2;
                        }
                        NotificationUtil.debugLog(Const.TAG, "hintActionInfo content error");
                        return "";
                    }
                    Integer type4 = hintInfo.getType();
                    if (type4 != null && type4.intValue() == 1) {
                        if (!TextUtils.isEmpty(hintInfo.getTitle())) {
                            return Const.Module.MODULE_BUTTON_3;
                        }
                        NotificationUtil.debugLog(Const.TAG, "hintActionInfo title error");
                        return "";
                    }
                }
                return "";
            case -1409553101:
                if (str.equals(AREA_E) && (picInfo = template.getPicInfo()) != null) {
                    if (picInfo.getType() == null) {
                        NotificationUtil.debugLog(Const.TAG, "picInfo type error");
                        return "";
                    }
                    Integer type5 = picInfo.getType();
                    if (type5 != null && type5.intValue() == 1) {
                        return Const.Module.MODULE_MARK_1;
                    }
                }
                return "";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final String chooseModule(Template template, String str, Boolean bool) throws FocusParamsException {
        switch (str.hashCode()) {
            case -1409553105:
                if (str.equals(AREA_A)) {
                    BaseInfo baseInfo = template.getBaseInfo();
                    HighlightInfo highlightInfo = template.getHighlightInfo();
                    ChatInfo chatInfo = template.getChatInfo();
                    AnimTextInfo animTextInfo = template.getAnimTextInfo();
                    CoverInfo coverInfo = template.getCoverInfo();
                    IconTextInfo iconTextInfo = template.getIconTextInfo();
                    if (animTextInfo != null) {
                        return Const.Module.MODULE_ANIMATION_TEXT;
                    }
                    if (coverInfo != null) {
                        return Const.Module.MODULE_COVER;
                    }
                    if (iconTextInfo != null) {
                        return Const.Module.MODULE_NEW_IMAGE_TEXT;
                    }
                    if (baseInfo != null) {
                        if (baseInfo.getType() == null || TextUtils.isEmpty(baseInfo.getTitle())) {
                            throw new FocusParamsException("baseInfo param is error");
                        }
                        Integer type = baseInfo.getType();
                        return (type != null && type.intValue() == 2) ? Const.Module.MODULE_TEXT_2 : Const.Module.MODULE_TEXT_1;
                    }
                    if (highlightInfo != null) {
                        return n.c(bool, Boolean.TRUE) ? highlightInfo.getType() == 1 ? Const.Module.MODULE_IMAGE_TEXT_HIGHLIGHT_TIME : Const.Module.MODULE_IMAGE_TEXT_HIGHLIGHT_SPORT : Const.Module.MODULE_IMAGE_TEXT_HIGHLIGHT;
                    }
                    if (chatInfo == null) {
                        Log.e(TAG, "has no AREA_A params.");
                        return "";
                    }
                    if (TextUtils.isEmpty(chatInfo.getTitle())) {
                        throw new FocusParamsException("chatInfo param is error");
                    }
                    return Const.Module.MODULE_IMAGE_TEXT_IM;
                }
                return "";
            case -1409553104:
            default:
                return "";
            case -1409553103:
                if (str.equals(AREA_C)) {
                    if (template.getActions() != null) {
                        return Const.Module.MODULE_BUTTON_1;
                    }
                    PicInfo picInfo = template.getPicInfo();
                    if (picInfo != null) {
                        if (picInfo.getType() == null) {
                            NotificationUtil.debugLog(Const.TAG, "picInfo type error");
                            return "";
                        }
                        Integer type2 = picInfo.getType();
                        if (type2 != null && type2.intValue() == 5) {
                            return Const.Module.MODULE_MARK_TEXT_IMAGE;
                        }
                        if (type2 != null && type2.intValue() == 4) {
                            if (picInfo.getActionInfo() != null) {
                                return Const.Module.MODULE_MARK_4;
                            }
                            NotificationUtil.debugLog(Const.TAG, "picInfo actionInfo error");
                            return "";
                        }
                        if (type2 != null && type2.intValue() == 3) {
                            if (picInfo.getPic() != null) {
                                return Const.Module.MODULE_MARK_3;
                            }
                            NotificationUtil.debugLog(Const.TAG, "picInfo 3 pic error");
                            return "";
                        }
                        if (type2 == null || type2.intValue() != 2) {
                            return Const.Module.MODULE_MARK_1;
                        }
                        if (picInfo.getPic() != null) {
                            return Const.Module.MODULE_MARK_2;
                        }
                        NotificationUtil.debugLog(Const.TAG, "picInfo  2 pic error");
                        return "";
                    }
                }
                return "";
            case -1409553102:
                if (str.equals(AREA_D)) {
                    if (template.getTextButton() != null) {
                        return Const.Module.MODULE_BUTTON_4;
                    }
                    if (template.getHighlightInfoV3() != null) {
                        return Const.Module.MODULE_BUTTON_5;
                    }
                    if (template.getMultiProgressInfo() != null) {
                        return Const.Module.MODULE_MULTI_PROGRESS;
                    }
                    ProgressInfo progressInfo = template.getProgressInfo();
                    HintInfo hintInfo = template.getHintInfo();
                    if (progressInfo != null) {
                        progressInfo.getProgress();
                        if (progressInfo.getProgress() >= 0 && progressInfo.getColorProgress() != null && !TextUtils.isEmpty(progressInfo.getColorProgress())) {
                            return Const.Module.MODULE_PROGRESS;
                        }
                        NotificationUtil.debugLog(Const.TAG, "progressInfo param error");
                        return "";
                    }
                    if (hintInfo == null) {
                        return "";
                    }
                    if (hintInfo.getType() == null) {
                        NotificationUtil.debugLog(Const.TAG, "hintActionInfo type error");
                        return "";
                    }
                    Integer type3 = hintInfo.getType();
                    if (type3 != null && type3.intValue() == 2) {
                        if (!TextUtils.isEmpty(hintInfo.getContent())) {
                            return Const.Module.MODULE_BUTTON_2;
                        }
                        NotificationUtil.debugLog(Const.TAG, "hintActionInfo content error");
                        return "";
                    }
                    Integer type4 = hintInfo.getType();
                    if (type4 != null && type4.intValue() == 1) {
                        if (!TextUtils.isEmpty(hintInfo.getTitle())) {
                            return Const.Module.MODULE_BUTTON_3;
                        }
                        NotificationUtil.debugLog(Const.TAG, "hintActionInfo title error");
                        return "";
                    }
                }
                return "";
        }
    }

    public static /* synthetic */ String chooseModule$default(TemplateFactoryV3 templateFactoryV3, Template template, String str, Boolean bool, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            bool = Boolean.FALSE;
        }
        return templateFactoryV3.chooseModule(template, str, bool);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void createDecoLandTemplateView(miui.systemui.notification.focus.model.Template r19, android.service.notification.StatusBarNotification r20, boolean r21, com.android.systemui.plugins.miui.notification.FocusNotificationContent r22) throws miui.systemui.notification.focus.FocusParamsException {
        /*
            Method dump skipped, instruction units count: 378
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.templateV3.TemplateFactoryV3.createDecoLandTemplateView(miui.systemui.notification.focus.model.Template, android.service.notification.StatusBarNotification, boolean, com.android.systemui.plugins.miui.notification.FocusNotificationContent):void");
    }

    public static /* synthetic */ void createDecoLandTemplateView$default(TemplateFactoryV3 templateFactoryV3, Template template, StatusBarNotification statusBarNotification, boolean z2, FocusNotificationContent focusNotificationContent, int i2, Object obj) throws FocusParamsException {
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        templateFactoryV3.createDecoLandTemplateView(template, statusBarNotification, z2, focusNotificationContent);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void createDecoTemplateView(miui.systemui.notification.focus.model.Template r19, android.service.notification.StatusBarNotification r20, boolean r21, com.android.systemui.plugins.miui.notification.FocusNotificationContent r22) throws miui.systemui.notification.focus.FocusParamsException {
        /*
            Method dump skipped, instruction units count: 388
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.templateV3.TemplateFactoryV3.createDecoTemplateView(miui.systemui.notification.focus.model.Template, android.service.notification.StatusBarNotification, boolean, com.android.systemui.plugins.miui.notification.FocusNotificationContent):void");
    }

    public static /* synthetic */ void createDecoTemplateView$default(TemplateFactoryV3 templateFactoryV3, Template template, StatusBarNotification statusBarNotification, boolean z2, FocusNotificationContent focusNotificationContent, int i2, Object obj) throws FocusParamsException {
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        templateFactoryV3.createDecoTemplateView(template, statusBarNotification, z2, focusNotificationContent);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void createStandardTemplateView(miui.systemui.notification.focus.model.Template r21, android.service.notification.StatusBarNotification r22, boolean r23, com.android.systemui.plugins.miui.notification.FocusNotificationContent r24, miui.systemui.notification.focus.InflateAndAuthCallBack r25) {
        /*
            Method dump skipped, instruction units count: 493
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.templateV3.TemplateFactoryV3.createStandardTemplateView(miui.systemui.notification.focus.model.Template, android.service.notification.StatusBarNotification, boolean, com.android.systemui.plugins.miui.notification.FocusNotificationContent, miui.systemui.notification.focus.InflateAndAuthCallBack):void");
    }

    public static /* synthetic */ void createStandardTemplateView$default(TemplateFactoryV3 templateFactoryV3, Template template, StatusBarNotification statusBarNotification, boolean z2, FocusNotificationContent focusNotificationContent, InflateAndAuthCallBack inflateAndAuthCallBack, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        templateFactoryV3.createStandardTemplateView(template, statusBarNotification, z2, focusNotificationContent, inflateAndAuthCallBack);
    }

    private final void createTinyTemplateView(Template template, StatusBarNotification statusBarNotification, boolean z2, FocusNotificationContent focusNotificationContent) throws FocusParamsException {
        TemplateTinyBuilderV3 arecCenterGravity;
        TemplateTinyBuilderV3 templateTinyBuilderV3UpdateModuleView;
        TemplateTinyBuilderV3 templateTinyBuilderV3UpdateModuleView2;
        TemplateTinyBuilderV3 templateTinyBuilderV3UpdateModuleView3;
        TemplateTinyBuilderV3 templateTinyBuilderV3UpdateModuleView4;
        NotificationUtil.debugLog(Const.TAG, "createTinyTemplateView");
        Boolean bool = Boolean.TRUE;
        String strChooseModule = chooseModule(template, AREA_A, bool);
        if (n.c(strChooseModule, "")) {
            return;
        }
        String strChooseModule2 = chooseModule(template, AREA_C, bool);
        String strChooseModule3 = chooseModule(template, AREA_D, bool);
        boolean zIsSolidBackground = isSolidBackground(template, statusBarNotification);
        TemplateTinyBuilderV3 templateTinyBuilderV3 = this.builderTinyMap.get(statusBarNotification.getKey());
        boolean z3 = templateTinyBuilderV3 != null && templateTinyBuilderV3.isSameModuleA(strChooseModule) && templateTinyBuilderV3.isSameModuleC(strChooseModule2) && templateTinyBuilderV3.isSameModuleD(strChooseModule3);
        Log.d(Const.TAG, "isSameModule: " + z3);
        if (!z3) {
            removeTinyTemplateView(statusBarNotification, focusNotificationContent);
        }
        if (!this.builderTinyMap.containsKey(statusBarNotification.getKey())) {
            TemplateTinyBuilderV3 templateTinyBuilderV3Create = this.templateTinyBuilderV3Factory.create(z2);
            TemplateTinyBuilderV3 areaCenterGravity = templateTinyBuilderV3Create.setArecCenterGravity(n.c(strChooseModule2, Const.Module.MODULE_BUTTON_1)).setAreaCenterGravity(strChooseModule3.length() == 0);
            int i2 = R.id.area_bg;
            TemplateTinyBuilderV3 areaViewVisible = areaCenterGravity.setAreaViewVisible(i2, !zIsSolidBackground ? 0 : 8);
            int i3 = R.id.area_a;
            TemplateTinyBuilderV3 areaViewVisible2 = areaViewVisible.setAreaViewVisible(i3, !TextUtils.isEmpty(strChooseModule) ? 0 : 8);
            int i4 = R.id.area_c;
            TemplateTinyBuilderV3 areaViewVisible3 = areaViewVisible2.setAreaViewVisible(i4, TextUtils.isEmpty(strChooseModule2) ? 4 : 0);
            int i5 = R.id.area_d;
            areaViewVisible3.setAreaViewVisible(i5, TextUtils.isEmpty(strChooseModule3) ? 8 : 0).addModuleView(i2, Const.Module.MODULE_BACKGROUND, zIsSolidBackground, template, statusBarNotification).addModuleView(i5, strChooseModule3, zIsSolidBackground, template, statusBarNotification).addModuleView(i3, strChooseModule, zIsSolidBackground, template, statusBarNotification).addModuleView(i4, strChooseModule2, zIsSolidBackground, template, statusBarNotification).buildView(focusNotificationContent);
            Map<String, TemplateTinyBuilderV3> map = this.builderTinyMap;
            String key = statusBarNotification.getKey();
            n.f(key, "getKey(...)");
            map.put(key, templateTinyBuilderV3Create);
            return;
        }
        TemplateTinyBuilderV3 templateTinyBuilderV32 = this.builderTinyMap.get(statusBarNotification.getKey());
        if (templateTinyBuilderV32 == null || (arecCenterGravity = templateTinyBuilderV32.setArecCenterGravity(n.c(strChooseModule2, Const.Module.MODULE_BUTTON_1))) == null) {
            return;
        }
        TemplateTinyBuilderV3 areaCenterGravity2 = arecCenterGravity.setAreaCenterGravity(strChooseModule3.length() == 0);
        if (areaCenterGravity2 != null) {
            TemplateTinyBuilderV3 areaViewVisible4 = areaCenterGravity2.setAreaViewVisible(R.id.area_bg, !zIsSolidBackground ? 0 : 8);
            if (areaViewVisible4 != null) {
                TemplateTinyBuilderV3 areaViewVisible5 = areaViewVisible4.setAreaViewVisible(R.id.area_a, !TextUtils.isEmpty(strChooseModule) ? 0 : 8);
                if (areaViewVisible5 != null) {
                    TemplateTinyBuilderV3 areaViewVisible6 = areaViewVisible5.setAreaViewVisible(R.id.area_c, TextUtils.isEmpty(strChooseModule2) ? 4 : 0);
                    if (areaViewVisible6 != null) {
                        TemplateTinyBuilderV3 areaViewVisible7 = areaViewVisible6.setAreaViewVisible(R.id.area_d, TextUtils.isEmpty(strChooseModule3) ? 8 : 0);
                        if (areaViewVisible7 == null || (templateTinyBuilderV3UpdateModuleView = areaViewVisible7.updateModuleView(strChooseModule3, zIsSolidBackground, template, statusBarNotification)) == null || (templateTinyBuilderV3UpdateModuleView2 = templateTinyBuilderV3UpdateModuleView.updateModuleView(strChooseModule, zIsSolidBackground, template, statusBarNotification)) == null || (templateTinyBuilderV3UpdateModuleView3 = templateTinyBuilderV3UpdateModuleView2.updateModuleView(strChooseModule2, zIsSolidBackground, template, statusBarNotification)) == null || (templateTinyBuilderV3UpdateModuleView4 = templateTinyBuilderV3UpdateModuleView3.updateModuleView(Const.Module.MODULE_BACKGROUND, zIsSolidBackground, template, statusBarNotification)) == null) {
                            return;
                        }
                        templateTinyBuilderV3UpdateModuleView4.buildView(focusNotificationContent);
                    }
                }
            }
        }
    }

    public static /* synthetic */ void createTinyTemplateView$default(TemplateFactoryV3 templateFactoryV3, Template template, StatusBarNotification statusBarNotification, boolean z2, FocusNotificationContent focusNotificationContent, int i2, Object obj) throws FocusParamsException {
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        templateFactoryV3.createTinyTemplateView(template, statusBarNotification, z2, focusNotificationContent);
    }

    private final Bundle getBitmapBundle(StatusBarNotification statusBarNotification) {
        try {
            return statusBarNotification.getNotification().extras.getBundle("miui.focus.pics");
        } catch (Exception unused) {
            return null;
        }
    }

    private final Icon getIcon(String str, StatusBarNotification statusBarNotification, Bundle bundle) {
        String key;
        Icon icon;
        if (bundle != null && (icon = (Icon) bundle.getParcelable(str)) != null) {
            return icon;
        }
        if (statusBarNotification == null || (key = statusBarNotification.getKey()) == null) {
            return null;
        }
        return FocusIconCache.INSTANCE.get(key, str);
    }

    private final void handleAodAndStatusBar(StatusBarNotification statusBarNotification, Context context, String str, String str2, String str3, String str4, String str5, Bundle bundle) {
        statusBarNotification.getNotification().extras.putString(Const.Param.TICKER, str);
        statusBarNotification.getNotification().extras.putString(Const.Param.TICKER_PIC, str2);
        statusBarNotification.getNotification().extras.putString(Const.Param.TICKER_PIC_DARK, str3);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.focus_notification_template_aod_v2);
        if (str4 == null || TextUtils.isEmpty(str4)) {
            return;
        }
        setTextVisibleAndText(remoteViews, str4);
        int i2 = R.id.focus_aod_icon;
        remoteViews.setViewVisibility(i2, 0);
        Icon icon = bundle != null ? (Icon) bundle.getParcelable(str5) : null;
        if (bundle == null || !bundle.containsKey(str5) || icon == null) {
            statusBarNotification.getNotification().extras.putInt(Const.Param.AOD_ICON_VId, i2);
        } else {
            remoteViews.setImageViewIcon(i2, icon);
        }
        statusBarNotification.getNotification().extras.putParcelable(Const.Param.LAYOUT_AOD, remoteViews);
    }

    private final void handleTimeout(StatusBarNotification statusBarNotification, int i2) {
        long j2 = i2 < 0 ? 5000L : 60000 * ((long) i2);
        NotificationUtil.debugLog(Const.TAG, "handleTimeout " + j2);
        statusBarNotification.getNotification().extras.putLong(Const.Param.EXTRA_MIUI_TIMEOUT, j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isBgPicDownloadFail(Template template, StatusBarNotification statusBarNotification) {
        Bundle bitmapBundle = getBitmapBundle(statusBarNotification);
        BgInfo bgInfo = template.getBgInfo();
        Icon icon = getIcon(bgInfo != null ? bgInfo.getPicBg() : null, statusBarNotification, bitmapBundle);
        if (bitmapBundle != null) {
            BgInfo bgInfo2 = template.getBgInfo();
            if (bitmapBundle.containsKey(bgInfo2 != null ? bgInfo2.getPicBg() : null) && icon == null) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean isSolidBackground(miui.systemui.notification.focus.model.Template r5, android.service.notification.StatusBarNotification r6) {
        /*
            r4 = this;
            android.os.Bundle r0 = r4.getBitmapBundle(r6)
            miui.systemui.notification.focus.model.BgInfo r1 = r5.getBgInfo()
            r2 = 0
            if (r1 == 0) goto L10
            java.lang.String r1 = r1.getPicBg()
            goto L11
        L10:
            r1 = r2
        L11:
            android.graphics.drawable.Icon r4 = r4.getIcon(r1, r6, r0)
            r6 = 0
            r1 = 1
            if (r0 == 0) goto L2f
            miui.systemui.notification.focus.model.BgInfo r3 = r5.getBgInfo()
            if (r3 == 0) goto L24
            java.lang.String r3 = r3.getPicBg()
            goto L25
        L24:
            r3 = r2
        L25:
            boolean r0 = r0.containsKey(r3)
            if (r0 != r1) goto L2f
            if (r4 != 0) goto L2f
            r0 = r1
            goto L30
        L2f:
            r0 = r6
        L30:
            miui.systemui.notification.focus.model.BgInfo r5 = r5.getBgInfo()     // Catch: java.lang.Exception -> L44
            if (r5 == 0) goto L3b
            java.lang.String r5 = r5.getColorBg()     // Catch: java.lang.Exception -> L44
            goto L3c
        L3b:
            r5 = r2
        L3c:
            int r5 = android.graphics.Color.parseColor(r5)     // Catch: java.lang.Exception -> L44
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Exception -> L44
        L44:
            if (r0 != 0) goto L4a
            if (r4 != 0) goto L4b
            if (r2 != 0) goto L4b
        L4a:
            r6 = r1
        L4b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.templateV3.TemplateFactoryV3.isSolidBackground(miui.systemui.notification.focus.model.Template, android.service.notification.StatusBarNotification):boolean");
    }

    private final void removeDecoLandTemplateView(StatusBarNotification statusBarNotification, FocusNotificationContent focusNotificationContent) throws FocusParamsException {
        TemplateDecoLandBuilderV3 templateDecoLandBuilderV3RemoveModuleView;
        TemplateDecoLandBuilderV3 templateDecoLandBuilderV3RemoveModuleView2;
        TemplateDecoLandBuilderV3 templateDecoLandBuilderV3RemoveModuleView3;
        TemplateDecoLandBuilderV3 templateDecoLandBuilderV3RemoveModuleView4;
        if (this.builderDecoLandMap.containsKey(statusBarNotification.getKey())) {
            TemplateDecoLandBuilderV3 templateDecoLandBuilderV3 = this.builderDecoLandMap.get(statusBarNotification.getKey());
            Template template = this.templateMap.get(statusBarNotification.getKey());
            if (template != null) {
                String strChooseDecoModule = chooseDecoModule(template, AREA_A);
                String strChooseDecoModule2 = chooseDecoModule(template, AREA_C);
                String strChooseDecoModule3 = chooseDecoModule(template, AREA_E);
                String strChooseDecoModule4 = chooseDecoModule(template, AREA_D);
                if (templateDecoLandBuilderV3 != null && (templateDecoLandBuilderV3RemoveModuleView = templateDecoLandBuilderV3.removeModuleView(strChooseDecoModule)) != null && (templateDecoLandBuilderV3RemoveModuleView2 = templateDecoLandBuilderV3RemoveModuleView.removeModuleView(strChooseDecoModule2)) != null && (templateDecoLandBuilderV3RemoveModuleView3 = templateDecoLandBuilderV3RemoveModuleView2.removeModuleView(strChooseDecoModule3)) != null && (templateDecoLandBuilderV3RemoveModuleView4 = templateDecoLandBuilderV3RemoveModuleView3.removeModuleView(strChooseDecoModule4)) != null) {
                    templateDecoLandBuilderV3RemoveModuleView4.removeView(focusNotificationContent);
                }
            }
        }
        this.builderDecoLandMap.remove(statusBarNotification.getKey());
    }

    private final void removeDecoTemplateView(StatusBarNotification statusBarNotification, FocusNotificationContent focusNotificationContent) throws FocusParamsException {
        TemplateDecoBuilderV3 templateDecoBuilderV3RemoveModuleView;
        TemplateDecoBuilderV3 templateDecoBuilderV3RemoveModuleView2;
        TemplateDecoBuilderV3 templateDecoBuilderV3RemoveModuleView3;
        TemplateDecoBuilderV3 templateDecoBuilderV3RemoveModuleView4;
        if (this.builderDecoMap.containsKey(statusBarNotification.getKey())) {
            TemplateDecoBuilderV3 templateDecoBuilderV3 = this.builderDecoMap.get(statusBarNotification.getKey());
            Template template = this.templateMap.get(statusBarNotification.getKey());
            if (template != null) {
                String strChooseDecoModule = chooseDecoModule(template, AREA_A);
                String strChooseDecoModule2 = chooseDecoModule(template, AREA_C);
                String strChooseDecoModule3 = chooseDecoModule(template, AREA_E);
                String strChooseDecoModule4 = chooseDecoModule(template, AREA_D);
                if (templateDecoBuilderV3 != null && (templateDecoBuilderV3RemoveModuleView = templateDecoBuilderV3.removeModuleView(strChooseDecoModule)) != null && (templateDecoBuilderV3RemoveModuleView2 = templateDecoBuilderV3RemoveModuleView.removeModuleView(strChooseDecoModule2)) != null && (templateDecoBuilderV3RemoveModuleView3 = templateDecoBuilderV3RemoveModuleView2.removeModuleView(strChooseDecoModule3)) != null && (templateDecoBuilderV3RemoveModuleView4 = templateDecoBuilderV3RemoveModuleView3.removeModuleView(strChooseDecoModule4)) != null) {
                    templateDecoBuilderV3RemoveModuleView4.removeView(focusNotificationContent);
                }
            }
        }
        this.builderDecoMap.remove(statusBarNotification.getKey());
    }

    private final void removeStandardTemplateView(StatusBarNotification statusBarNotification, FocusNotificationContent focusNotificationContent) {
        TemplateBuilderV3 templateBuilderV3RemoveModuleView;
        TemplateBuilderV3 templateBuilderV3RemoveModuleView2;
        TemplateBuilderV3 templateBuilderV3RemoveModuleView3;
        TemplateBuilderV3 templateBuilderV3RemoveModuleView4;
        if (this.builderMap.containsKey(statusBarNotification.getKey())) {
            TemplateBuilderV3 templateBuilderV3 = this.builderMap.get(statusBarNotification.getKey());
            Template template = this.templateMap.get(statusBarNotification.getKey());
            if (template != null) {
                String strChooseModule$default = chooseModule$default(this, template, AREA_A, null, 4, null);
                String strChooseModule$default2 = chooseModule$default(this, template, AREA_C, null, 4, null);
                String strChooseModule$default3 = chooseModule$default(this, template, AREA_D, null, 4, null);
                if (templateBuilderV3 != null && (templateBuilderV3RemoveModuleView = templateBuilderV3.removeModuleView(strChooseModule$default3)) != null && (templateBuilderV3RemoveModuleView2 = templateBuilderV3RemoveModuleView.removeModuleView(strChooseModule$default)) != null && (templateBuilderV3RemoveModuleView3 = templateBuilderV3RemoveModuleView2.removeModuleView(strChooseModule$default2)) != null && (templateBuilderV3RemoveModuleView4 = templateBuilderV3RemoveModuleView3.removeModuleView(Const.Module.MODULE_BACKGROUND)) != null) {
                    templateBuilderV3RemoveModuleView4.removeView(focusNotificationContent);
                }
            }
        }
        this.builderMap.remove(statusBarNotification.getKey());
    }

    private final void removeTemplateView(StatusBarNotification statusBarNotification) {
        this.builderMap.remove(statusBarNotification.getKey());
        this.builderTinyMap.remove(statusBarNotification.getKey());
        this.builderDecoMap.remove(statusBarNotification.getKey());
        this.builderDecoLandMap.remove(statusBarNotification.getKey());
    }

    private final void removeTinyTemplateView(StatusBarNotification statusBarNotification, FocusNotificationContent focusNotificationContent) throws FocusParamsException {
        TemplateTinyBuilderV3 templateTinyBuilderV3RemoveModuleView;
        TemplateTinyBuilderV3 templateTinyBuilderV3RemoveModuleView2;
        TemplateTinyBuilderV3 templateTinyBuilderV3RemoveModuleView3;
        TemplateTinyBuilderV3 templateTinyBuilderV3RemoveModuleView4;
        if (this.builderTinyMap.containsKey(statusBarNotification.getKey())) {
            TemplateTinyBuilderV3 templateTinyBuilderV3 = this.builderTinyMap.get(statusBarNotification.getKey());
            Template template = this.templateMap.get(statusBarNotification.getKey());
            if (template != null) {
                Boolean bool = Boolean.TRUE;
                String strChooseModule = chooseModule(template, AREA_A, bool);
                String strChooseModule2 = chooseModule(template, AREA_C, bool);
                String strChooseModule3 = chooseModule(template, AREA_D, bool);
                if (templateTinyBuilderV3 != null && (templateTinyBuilderV3RemoveModuleView = templateTinyBuilderV3.removeModuleView(strChooseModule3)) != null && (templateTinyBuilderV3RemoveModuleView2 = templateTinyBuilderV3RemoveModuleView.removeModuleView(strChooseModule)) != null && (templateTinyBuilderV3RemoveModuleView3 = templateTinyBuilderV3RemoveModuleView2.removeModuleView(strChooseModule2)) != null && (templateTinyBuilderV3RemoveModuleView4 = templateTinyBuilderV3RemoveModuleView3.removeModuleView(Const.Module.MODULE_BACKGROUND)) != null) {
                    templateTinyBuilderV3RemoveModuleView4.removeView(focusNotificationContent);
                }
            }
        }
        this.builderTinyMap.remove(statusBarNotification.getKey());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resetErrorNotification(FocusNotificationContent focusNotificationContent, StatusBarNotification statusBarNotification, InflateAndAuthCallBack inflateAndAuthCallBack, d dVar) {
        focusNotificationContent.reset();
        statusBarNotification.getNotification().extras.putBoolean(Const.Param.IS_FOCUS_LAYOUT, false);
        String key = statusBarNotification.getKey();
        n.f(key, "getKey(...)");
        inflateAndAuthCallBack.onInflateFailed(key);
        FocusNotificationUtil.removeFocusNotification(statusBarNotification);
        return s.f314a;
    }

    private final void setTextVisibleAndText(RemoteViews remoteViews, String str) {
        int i2 = R.id.focus_aod_title;
        remoteViews.setViewVisibility(i2, 0);
        remoteViews.setTextViewText(i2, str);
    }

    private final void wrapNotification(Context context, Template template, StatusBarNotification statusBarNotification) {
        Set<String> setKeySet;
        Bundle bitmapBundle = getBitmapBundle(statusBarNotification);
        if (bitmapBundle != null && (setKeySet = bitmapBundle.keySet()) != null) {
            for (String str : setKeySet) {
                FocusIconCache focusIconCache = FocusIconCache.INSTANCE;
                String key = statusBarNotification.getKey();
                n.f(key, "getKey(...)");
                n.d(str);
                focusIconCache.put(key, str, (Icon) bitmapBundle.getParcelable(str));
            }
        }
        int timeout = template.getTimeout() != 0 ? template.getTimeout() : 720;
        String aodTitle = template.getAodTitle();
        String aodPic = template.getAodPic();
        handleTimeout(statusBarNotification, timeout);
        handleAodAndStatusBar(statusBarNotification, context, !TextUtils.isEmpty(template.getTicker()) ? template.getTicker() : NotificationUtil.DEBUG ? "noTicker" : "", template.getTickerPic(), template.getTickerPicDark(), aodTitle, aodPic, bitmapBundle);
        statusBarNotification.getNotification().extras.putBoolean(Const.Param.SHOW_NOTIFICATION, !n.c(template.isShowNotification(), Boolean.FALSE));
        boolean enableFloat = template.getEnableFloat();
        boolean updatable = template.getUpdatable();
        Boolean islandFirstFloat = template.getIslandFirstFloat();
        String reopen = template.getReopen();
        if (reopen == null) {
            reopen = Const.Param.REOPEN_FALSE;
        }
        statusBarNotification.getNotification().extras.putBoolean(Const.Param.EXTRA_CUSTOM_HIDE_BORDER, true);
        statusBarNotification.getNotification().extras.putBoolean(Const.Param.EXTRA_ENABLE_FLOAT, enableFloat);
        statusBarNotification.getNotification().extras.putBoolean(Const.Param.EXTRA_ISLAND_FIRST_FLOAT, islandFirstFloat != null ? islandFirstFloat.booleanValue() : true);
        if (updatable) {
            statusBarNotification.getNotification().extras.putString(Const.Param.EXTRA_FOCUS_REOPEN, reopen);
            statusBarNotification.getNotification().extras.putBoolean(Const.Param.EXTRA_FOCUS_ENABLE_ALERT, enableFloat);
            statusBarNotification.getNotification().extras.putBoolean(Const.Param.EXTRA_TICKER_AUTO_CLOSE, false);
            statusBarNotification.getNotification().flags &= -17;
        } else {
            statusBarNotification.getNotification().extras.remove(Const.Param.EXTRA_FOCUS_REOPEN);
            statusBarNotification.getNotification().extras.putBoolean(Const.Param.EXTRA_TICKER_AUTO_CLOSE, true);
            statusBarNotification.getNotification().flags |= 16;
        }
        statusBarNotification.getNotification().extras.putString("miui.effect.src", template.getOutEffectSrc());
        statusBarNotification.getNotification().extras.putString("miui.effect.color", template.getOutEffectColor());
    }

    public final void clean() {
        this.templateMap.clear();
        this.builderMap.clear();
        this.builderTinyMap.clear();
        this.builderDecoMap.clear();
        this.builderDecoLandMap.clear();
    }

    public final void createTemplate(Context context, Context sysuicontext, JSONObject focusParam, StatusBarNotification sbn, boolean z2, FocusNotificationContent focusContent, InflateAndAuthCallBack inflateCallBack) {
        n.g(context, "context");
        n.g(sysuicontext, "sysuicontext");
        n.g(focusParam, "focusParam");
        n.g(sbn, "sbn");
        n.g(focusContent, "focusContent");
        n.g(inflateCallBack, "inflateCallBack");
        Template template = (Template) new U.d().j(focusParam.toString(), Template.class);
        n.d(template);
        wrapNotification(context, template, sbn);
        checkAreaA(sbn, template);
        this.isFlipDevice = z2;
        AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new AnonymousClass1(sbn, template, focusContent, inflateCallBack, z2, null), 3, null);
        sbn.getNotification().extras.putBoolean(Const.Param.IS_FOCUS_LAYOUT, true);
    }

    public final void removeTemplate(StatusBarNotification sbn, FocusNotificationContent focusContent) {
        n.g(sbn, "sbn");
        n.g(focusContent, "focusContent");
        removeStandardTemplateView(sbn, focusContent);
        if (this.isFlipDevice) {
            removeTinyTemplateView(sbn, focusContent);
            removeDecoTemplateView(sbn, focusContent);
            removeDecoLandTemplateView(sbn, focusContent);
        }
        removeTemplateView(sbn);
    }
}
