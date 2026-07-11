package miui.systemui.controlcenter.panel.main.qs;

import I0.m;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.os.Build;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.QsEditWordlessModeBinding;
import miui.systemui.controlcenter.databinding.QsEditWordlessModeLayoutBinding;
import miui.systemui.controlcenter.panel.main.MainPanelContent;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.MainPanelModeController;
import miui.systemui.controlcenter.panel.main.qs.QSListController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.controlcenter.utils.WordlessModeCompat;
import miui.systemui.controlcenter.widget.SlidingButton;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;
import miui.systemui.util.ViewOutlineProviderExt;
import miui.systemui.util.concurrency.DelayableExecutor;
import miui.systemui.widget.FrameLayout;
import miui.systemui.widget.TextView;
import miuix.animation.Folme;
import miuix.animation.ITouchStyle;
import miuix.animation.base.AnimConfig;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class WordlessModeController extends MainPanelListItem.SimpleController<ControlCenterWindowViewImpl> implements MainPanelContent {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "WordlessModeController";
    public static final int TYPE_SHOW_LABEL = 334833;
    private static final String WORDLESS_MODE_SETTINGS = "wordless_mode";
    private final Handler bgHandler;
    private final ContentResolver contentResolver;
    private final E0.a distributor;
    private boolean isWordlessMode;
    private final List<MainPanelListItem> listItems;
    private final E0.a mainPanelController;
    private final E0.a mainPanelModeController;
    private final int priority;
    private final E0.a qsListController;
    private final boolean rightOrLeft;
    private final WordlessModeController$settingsObserver$1 settingsObserver;
    private final int spanSize;
    private final int type;
    private final DelayableExecutor uiExecutor;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class WordlessModeViewHolder extends MainPanelItemViewHolder {
        private final QsEditWordlessModeLayoutBinding binding;

        /* JADX WARN: Illegal instructions before constructor call */
        public WordlessModeViewHolder(QsEditWordlessModeLayoutBinding binding) {
            n.g(binding, "binding");
            FrameLayout root = binding.getRoot();
            n.f(root, "getRoot(...)");
            super(root);
            this.binding = binding;
        }

        private final void updateResources() {
            this.binding.qsEditWordlessMode.toggle.updateResources();
        }

        public final QsEditWordlessModeLayoutBinding getBinding() {
            return this.binding;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
        public void onConfigurationChanged(int i2) {
            TextView wordlessModeTitle = this.binding.qsEditWordlessMode.wordlessModeTitle;
            n.f(wordlessModeTitle, "wordlessModeTitle");
            ConfigUtils configUtils = ConfigUtils.INSTANCE;
            if (configUtils.dimensionsChanged(i2) || configUtils.colorsChanged(i2)) {
                updateResources();
            }
            if (configUtils.textAppearanceChanged(i2)) {
                wordlessModeTitle.setTextAppearance(R.style.TextAppearance_QS_WordlessModeTitle);
            }
            if (configUtils.textsChanged(i2)) {
                wordlessModeTitle.setText(R.string.wordless_mode_title_text);
            }
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
        public void update(boolean z2, float f2) {
            getAnimatorConfigHelper().updateTranY(this, z2, f2);
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
        public AnimConfig updateAnimConfig(boolean z2, boolean z3, int i2, int i3) {
            return getAnimatorConfigHelper().updateAnimEase(this, z2, z3, i2, i3);
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
        public void updateBlendBlur() {
            if (!ControlCenterUtils.getBackgroundBlurOpenedInDefaultTheme(getContext())) {
                CommonUtils commonUtils = CommonUtils.INSTANCE;
                FrameLayout qsWordlessMode = this.binding.qsWordlessMode;
                n.f(qsWordlessMode, "qsWordlessMode");
                commonUtils.clearMiBlurBlendEffect(qsWordlessMode);
                this.binding.qsWordlessMode.setBackgroundResource(R.drawable.qs_wordless_mode_background);
                return;
            }
            this.binding.qsWordlessMode.setBackground(null);
            FrameLayout qsWordlessMode2 = this.binding.qsWordlessMode;
            n.f(qsWordlessMode2, "qsWordlessMode");
            MiBlurCompat.setMiViewBlurModeCompat(qsWordlessMode2, 1);
            FrameLayout qsWordlessMode3 = this.binding.qsWordlessMode;
            n.f(qsWordlessMode3, "qsWordlessMode");
            MiBlurCompat.setMiBackgroundBlendColors(qsWordlessMode3, MiuiColorBlendToken.INSTANCE.getCC_EDIT_BUTTON_BLEND_COLORS());
            this.binding.qsWordlessMode.setOutlineProvider(ViewOutlineProviderExt.INSTANCE.getOutlineProvider(getContext().getResources().getDimensionPixelSize(R.dimen.control_center_wordless_mode_corner_radius)));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v6, types: [miui.systemui.controlcenter.panel.main.qs.WordlessModeController$settingsObserver$1] */
    public WordlessModeController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, E0.a distributor, @Background final Handler bgHandler, @Main DelayableExecutor uiExecutor, E0.a qsListController, E0.a mainPanelController, E0.a mainPanelModeController) {
        super(windowView);
        n.g(windowView, "windowView");
        n.g(distributor, "distributor");
        n.g(bgHandler, "bgHandler");
        n.g(uiExecutor, "uiExecutor");
        n.g(qsListController, "qsListController");
        n.g(mainPanelController, "mainPanelController");
        n.g(mainPanelModeController, "mainPanelModeController");
        this.distributor = distributor;
        this.bgHandler = bgHandler;
        this.uiExecutor = uiExecutor;
        this.qsListController = qsListController;
        this.mainPanelController = mainPanelController;
        this.mainPanelModeController = mainPanelModeController;
        this.listItems = m.f(this);
        this.priority = 80;
        this.spanSize = 4;
        this.type = TYPE_SHOW_LABEL;
        this.settingsObserver = new ContentObserver(bgHandler) { // from class: miui.systemui.controlcenter.panel.main.qs.WordlessModeController$settingsObserver$1
            @Override // android.database.ContentObserver
            public void onChange(boolean z2) {
                if (z2) {
                    return;
                }
                if (!WordlessModeCompat.INSTANCE.isShowWordlessModeLabel()) {
                    WordlessModeController wordlessModeController = this.this$0;
                    wordlessModeController.updateTextMode(wordlessModeController.getSettings(0) == 0);
                } else {
                    boolean z3 = this.this$0.getSettings(0) == 0;
                    if (z3 != this.this$0.isWordlessMode) {
                        this.this$0.setWordlessMode(z3);
                    }
                }
            }
        };
        this.contentResolver = getContext().getContentResolver();
    }

    private final void accessibilityWordlessMode() {
        FrameLayout wordlessMode = getWordlessMode();
        if (wordlessMode == null) {
            wordlessMode = null;
        }
        if (wordlessMode == null) {
            return;
        }
        ViewCompat.setAccessibilityDelegate(wordlessMode, new AccessibilityDelegateCompat() { // from class: miui.systemui.controlcenter.panel.main.qs.WordlessModeController.accessibilityWordlessMode.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                n.g(host, "host");
                n.g(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(Switch.class.getName());
                info.setChecked(WordlessModeController.this.isWordlessMode);
                info.setCheckable(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getSettings(int i2) {
        return Settings.Secure.getInt(this.contentResolver, WORDLESS_MODE_SETTINGS, i2);
    }

    private final SlidingButton getToggle() {
        QsEditWordlessModeLayoutBinding binding;
        QsEditWordlessModeBinding qsEditWordlessModeBinding;
        MainPanelItemViewHolder holder = getHolder();
        WordlessModeViewHolder wordlessModeViewHolder = holder instanceof WordlessModeViewHolder ? (WordlessModeViewHolder) holder : null;
        if (wordlessModeViewHolder == null || (binding = wordlessModeViewHolder.getBinding()) == null || (qsEditWordlessModeBinding = binding.qsEditWordlessMode) == null) {
            return null;
        }
        return qsEditWordlessModeBinding.toggle;
    }

    private final FrameLayout getWordlessMode() {
        QsEditWordlessModeLayoutBinding binding;
        MainPanelItemViewHolder holder = getHolder();
        WordlessModeViewHolder wordlessModeViewHolder = holder instanceof WordlessModeViewHolder ? (WordlessModeViewHolder) holder : null;
        if (wordlessModeViewHolder == null || (binding = wordlessModeViewHolder.getBinding()) == null) {
            return null;
        }
        return binding.qsWordlessMode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindViewHolder$lambda$2$lambda$0(View view, MotionEvent motionEvent) {
        Folme.useAt(view).touch().setScale(1.0f, new ITouchStyle.TouchType[0]).setTint(0.12f, 0.0f, 0.0f, 0.0f).onMotionEvent(motionEvent);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$2$lambda$1(WordlessModeController this$0, View view) {
        n.g(this$0, "this$0");
        this$0.setWordlessMode(!this$0.isWordlessMode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$3(WordlessModeController this$0, CompoundButton compoundButton, boolean z2) {
        n.g(this$0, "this$0");
        this$0.setWordlessMode(z2);
    }

    private final boolean putSettings(int i2) {
        return Settings.Secure.putInt(this.contentResolver, WORDLESS_MODE_SETTINGS, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setWordlessMode(boolean z2) {
        if (this.isWordlessMode == z2) {
            return;
        }
        this.isWordlessMode = z2;
        SlidingButton toggle = getToggle();
        if (toggle != null) {
            toggle.setChecked(this.isWordlessMode);
        }
        updateTextMode(this.isWordlessMode);
        putSettings(!this.isWordlessMode ? 1 : 0);
    }

    private final void updateAccessibility() {
        FrameLayout wordlessMode = getWordlessMode();
        if (wordlessMode == null) {
            return;
        }
        wordlessMode.setContentDescription(getContext().getString(R.string.wordless_mode_title_text));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateTextMode(final boolean z2) {
        this.uiExecutor.execute(new Runnable() { // from class: miui.systemui.controlcenter.panel.main.qs.e
            @Override // java.lang.Runnable
            public final void run() {
                WordlessModeController.updateTextMode$lambda$4(this.f5428a, z2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateTextMode$lambda$4(WordlessModeController this$0, boolean z2) {
        n.g(this$0, "this$0");
        ((QSListController) this$0.qsListController.get()).setTextMode(z2 ? QSListController.TextMode.TEXT : QSListController.TextMode.NO_TEXT);
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean available(boolean z2) {
        return ((MainPanelModeController) this.mainPanelModeController.get()).getMode() == MainPanelController.Mode.EDIT && Build.IS_INTERNATIONAL_BUILD;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public MainPanelItemViewHolder createViewHolder(ViewGroup parent, int i2) {
        n.g(parent, "parent");
        if (i2 != 334833) {
            return null;
        }
        QsEditWordlessModeLayoutBinding qsEditWordlessModeLayoutBindingInflate = QsEditWordlessModeLayoutBinding.inflate(LayoutInflater.from(getContext()), parent, false);
        n.f(qsEditWordlessModeLayoutBindingInflate, "inflate(...)");
        return new WordlessModeViewHolder(qsEditWordlessModeLayoutBindingInflate);
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public List<MainPanelListItem> getListItems() {
        return this.listItems;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public int getPriority() {
        return this.priority;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean getRightOrLeft() {
        return this.rightOrLeft;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public int getSpanSize() {
        return this.spanSize;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public int getType() {
        return this.type;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    @SuppressLint({"ClickableViewAccessibility"})
    public void onBindViewHolder() {
        SlidingButton toggle = getToggle();
        if (toggle != null) {
            toggle.setChecked(this.isWordlessMode);
        }
        FrameLayout wordlessMode = getWordlessMode();
        if (wordlessMode != null) {
            wordlessMode.setOnTouchListener(new View.OnTouchListener() { // from class: miui.systemui.controlcenter.panel.main.qs.f
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return WordlessModeController.onBindViewHolder$lambda$2$lambda$0(view, motionEvent);
                }
            });
            wordlessMode.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.main.qs.g
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WordlessModeController.onBindViewHolder$lambda$2$lambda$1(this.f5430a, view);
                }
            });
            wordlessMode.setOnLongClickListener(null);
        }
        SlidingButton toggle2 = getToggle();
        if (toggle2 != null) {
            toggle2.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: miui.systemui.controlcenter.panel.main.qs.h
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                    WordlessModeController.onBindViewHolder$lambda$3(this.f5431a, compoundButton, z2);
                }
            });
        }
        accessibilityWordlessMode();
        updateAccessibility();
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        if (getSettings(-1) == -1) {
            putSettings(!CommonUtils.IS_ID_REGION ? 1 : 0);
        }
        this.contentResolver.registerContentObserver(Settings.Secure.getUriFor(WORDLESS_MODE_SETTINGS), true, this.settingsObserver);
        setWordlessMode(getSettings(0) == 0);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        this.contentResolver.unregisterContentObserver(this.settingsObserver);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public void onUnbindViewHolder() {
        View view;
        MainPanelItemViewHolder holder = getHolder();
        if (holder == null || (view = holder.itemView) == null) {
            return;
        }
        view.setOnClickListener(null);
        view.setOnLongClickListener(null);
    }
}
