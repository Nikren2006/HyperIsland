package miuix.pickerwidget.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.ViewUtils;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import miui.systemui.notification.NotificationUtil;
import miuix.appcompat.internal.app.widget.SearchActionModeView;
import miuix.internal.util.ReflectUtil;
import miuix.pickerwidget.R;
import miuix.pickerwidget.internal.util.SimpleNumberFormatter;
import miuix.pickerwidget.internal.util.async.WorkerThreads;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes5.dex */
public class NumberPicker extends LinearLayout {
    private static final int BACKGROUND_PADDING = 10;
    private static final long DEFAULT_LONG_PRESS_UPDATE_INTERVAL = 300;
    private static final int MAX_HEIGHT = 202;
    private static final float SELECTION_DIVIDERS_DISTANCE = 45.0f;
    private static final int SELECTOR_ADJUSTMENT_DURATION_MILLIS = 800;
    private static final int SELECTOR_MAX_FLING_VELOCITY_ADJUSTMENT = 8;
    private static final int SELECTOR_MIDDLE_ITEM_INDEX = 1;
    static final int SELECTOR_WHEEL_ITEM_COUNT = 3;
    private static final int SIZE_UNSPECIFIED = -1;
    private static final int SNAP_SCROLL_DURATION = 300;
    private static final String SOUND_PLAY_THREAD = "NumberPicker_sound_play";
    private static final float TOP_AND_BOTTOM_FADING_EDGE_STRENGTH = 0.9f;
    private static final int UNSCALED_DEFAULT_SELECTION_DIVIDERS_DISTANCE = 48;
    private static final int UNSCALED_DEFAULT_SELECTION_DIVIDER_HEIGHT = 2;
    private int MARGIN_LABEL_LEFT;
    private int MARGIN_LABEL_TOP;
    private AccessibilityNodeProviderImpl mAccessibilityNodeProvider;
    private final Scroller mAdjustScroller;
    private BeginSoftInputOnLongPressCommand mBeginSoftInputOnLongPressCommand;
    private int mBottomSelectionDividerBottom;
    private ChangeCurrentByOneFromLongPressCommand mChangeCurrentByOneFromLongPressCommand;
    private final boolean mComputeMaxWidth;
    private int mCurrentScrollOffset;
    private boolean mDecrementVirtualButtonPressed;
    private String mDisplayedMaxText;
    private float mDisplayedMaxTextWidth;
    private String[] mDisplayedValues;
    private final Scroller mFlingScroller;
    private Formatter mFormatter;
    private final boolean mHasSelectorWheel;
    private final int mId;
    private boolean mIgnoreMoveEvents;
    private boolean mIncrementVirtualButtonPressed;
    private int mInitialScrollOffset;
    private final EditText mInputText;
    private CharSequence mLabel;
    private Paint mLabelPaint;
    private int mLabelTextColor;
    private int mLabelTextSize;
    private float mLabelTextSizeThreshold;
    private float mLabelTextSizeTrimFactor;
    private long mLastDownEventTime;
    private float mLastDownEventY;
    private float mLastDownOrMoveEventY;
    private int mLastHandledDownDpadKeyCode;
    private int mLastHoveredChildVirtualViewId;
    private long mLongPressUpdateInterval;
    private float mMaxFlingSpeedFactor;
    private final int mMaxHeight;
    private int mMaxValue;
    private int mMaxWidth;
    private int mMaximumFlingVelocity;
    private boolean mMeasureBackgroundEnabled;
    private final int mMinHeight;
    private int mMinValue;
    private final int mMinWidth;
    private int mMinimumFlingVelocity;
    private String mModDevice;
    private OnScrollListener mOnScrollListener;
    private OnValueChangeListener mOnValueChangeListener;
    private int mOriginLabelTextSize;
    private int mOriginTextSizeHighlight;
    private int mOriginTextSizeHint;
    private final PressedStateHelper mPressedStateHelper;
    private int mPreviousScrollerY;
    private int mScrollState;
    private final int mSelectionDividerHeight;
    private final int mSelectionDividersDistance;
    private int mSelectorElementHeight;
    private final SparseArray<String> mSelectorIndexToStringCache;
    private final int[] mSelectorIndices;
    private int mSelectorTextGapHeight;
    private final Paint mSelectorWheelPaint;
    private SetSelectionCommand mSetSelectionCommand;
    private boolean mShowSoftInputOnTap;
    private SoundPlayHandler mSoundPlayHandler;
    private int mTextColorHighlight;
    private int mTextColorHint;
    private int mTextPadding;
    private final int mTextSize;
    private int mTextSizeHighlight;
    private int mTextSizeHint;
    private float mTextSizeThreshold;
    private float mTextSizeTrimFactor;
    private int mTopSelectionDividerTop;
    private int mTouchSlop;
    private String mUpdateText;
    private int mValue;
    private VelocityTracker mVelocityTracker;
    private boolean mWrapSelectorWheel;
    private static final int DEFAULT_LAYOUT_RESOURCE_ID = R.layout.miuix_appcompat_number_picker_layout;
    private static final AtomicInteger sIdGenerator = new AtomicInteger(0);
    static final Formatter TWO_DIGIT_FORMATTER = new NumberFormatter(2);
    private static final int[] PRESSED_STATE_SET = {android.R.attr.state_pressed};
    private static final char[] DIGIT_CHARACTERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public class AccessibilityNodeProviderImpl extends AccessibilityNodeProvider {
        private static final int UNDEFINED = Integer.MIN_VALUE;
        private static final int VIRTUAL_VIEW_ID_DECREMENT = 3;
        private static final int VIRTUAL_VIEW_ID_INCREMENT = 1;
        private static final int VIRTUAL_VIEW_ID_INPUT = 2;
        private final Rect mTempRect = new Rect();
        private final int[] mTempArray = new int[2];
        private int mAccessibilityFocusedView = Integer.MIN_VALUE;

        public AccessibilityNodeProviderImpl() {
        }

        private AccessibilityNodeInfo createAccessibilityNodeInfoForNumberPicker(int i2, int i3, int i4, int i5) {
            AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain();
            accessibilityNodeInfoObtain.setClassName(NumberPicker.class.getName());
            accessibilityNodeInfoObtain.setPackageName(NumberPicker.this.getContext().getPackageName());
            accessibilityNodeInfoObtain.setSource(NumberPicker.this);
            if (hasVirtualDecrementButton()) {
                accessibilityNodeInfoObtain.addChild(NumberPicker.this, 3);
            }
            accessibilityNodeInfoObtain.addChild(NumberPicker.this, 2);
            if (hasVirtualIncrementButton()) {
                accessibilityNodeInfoObtain.addChild(NumberPicker.this, 1);
            }
            accessibilityNodeInfoObtain.setParent((View) NumberPicker.this.getParentForAccessibility());
            accessibilityNodeInfoObtain.setEnabled(NumberPicker.this.isEnabled());
            accessibilityNodeInfoObtain.setScrollable(true);
            Rect rect = this.mTempRect;
            rect.set(i2, i3, i4, i5);
            accessibilityNodeInfoObtain.setBoundsInParent(rect);
            accessibilityNodeInfoObtain.setVisibleToUser(NumberPicker.this.getVisibility() == 0);
            int[] iArr = this.mTempArray;
            NumberPicker.this.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            accessibilityNodeInfoObtain.setBoundsInScreen(rect);
            if (this.mAccessibilityFocusedView != -1) {
                accessibilityNodeInfoObtain.addAction(64);
            }
            if (this.mAccessibilityFocusedView == -1) {
                accessibilityNodeInfoObtain.addAction(128);
            }
            if (NumberPicker.this.isEnabled()) {
                if (NumberPicker.this.getWrapSelectorWheel() || NumberPicker.this.getValue() < NumberPicker.this.getMaxValue()) {
                    accessibilityNodeInfoObtain.addAction(4096);
                }
                if (NumberPicker.this.getWrapSelectorWheel() || NumberPicker.this.getValue() > NumberPicker.this.getMinValue()) {
                    accessibilityNodeInfoObtain.addAction(8192);
                }
            }
            return accessibilityNodeInfoObtain;
        }

        private AccessibilityNodeInfo createAccessibilityNodeInfoForVirtualButton(int i2, String str, int i3, int i4, int i5, int i6) {
            AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain();
            accessibilityNodeInfoObtain.setClassName(Button.class.getName());
            accessibilityNodeInfoObtain.setPackageName(NumberPicker.this.getContext().getPackageName());
            accessibilityNodeInfoObtain.setSource(NumberPicker.this, i2);
            accessibilityNodeInfoObtain.setParent(NumberPicker.this);
            accessibilityNodeInfoObtain.setText(str);
            accessibilityNodeInfoObtain.setClickable(true);
            accessibilityNodeInfoObtain.setLongClickable(true);
            accessibilityNodeInfoObtain.setEnabled(NumberPicker.this.isEnabled());
            Rect rect = this.mTempRect;
            rect.set(i3, i4, i5, i6);
            accessibilityNodeInfoObtain.setVisibleToUser(NumberPicker.this.getVisibility() == 0);
            accessibilityNodeInfoObtain.setBoundsInParent(rect);
            int[] iArr = this.mTempArray;
            NumberPicker.this.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            accessibilityNodeInfoObtain.setBoundsInScreen(rect);
            if (this.mAccessibilityFocusedView != i2) {
                accessibilityNodeInfoObtain.addAction(64);
            }
            if (this.mAccessibilityFocusedView == i2) {
                accessibilityNodeInfoObtain.addAction(128);
            }
            if (NumberPicker.this.isEnabled()) {
                accessibilityNodeInfoObtain.addAction(16);
            }
            return accessibilityNodeInfoObtain;
        }

        private AccessibilityNodeInfo createAccessibiltyNodeInfoForInputText(int i2, int i3, int i4, int i5) {
            AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo = NumberPicker.this.mInputText.createAccessibilityNodeInfo();
            accessibilityNodeInfoCreateAccessibilityNodeInfo.setSource(NumberPicker.this, 2);
            if (this.mAccessibilityFocusedView != 2) {
                accessibilityNodeInfoCreateAccessibilityNodeInfo.addAction(64);
            }
            if (this.mAccessibilityFocusedView == 2) {
                accessibilityNodeInfoCreateAccessibilityNodeInfo.addAction(128);
            }
            Rect rect = this.mTempRect;
            rect.set(i2, i3, i4, i5);
            accessibilityNodeInfoCreateAccessibilityNodeInfo.setVisibleToUser(NumberPicker.this.getVisibility() == 0);
            accessibilityNodeInfoCreateAccessibilityNodeInfo.setBoundsInParent(rect);
            int[] iArr = this.mTempArray;
            NumberPicker.this.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            accessibilityNodeInfoCreateAccessibilityNodeInfo.setBoundsInScreen(rect);
            return accessibilityNodeInfoCreateAccessibilityNodeInfo;
        }

        private void findAccessibilityNodeInfosByTextInChild(String str, int i2, List<AccessibilityNodeInfo> list) {
            if (i2 == 1) {
                String virtualIncrementButtonText = getVirtualIncrementButtonText();
                if (TextUtils.isEmpty(virtualIncrementButtonText) || !virtualIncrementButtonText.toString().toLowerCase().contains(str)) {
                    return;
                }
                list.add(createAccessibilityNodeInfo(1));
                return;
            }
            if (i2 != 2) {
                if (i2 != 3) {
                    return;
                }
                String virtualDecrementButtonText = getVirtualDecrementButtonText();
                if (TextUtils.isEmpty(virtualDecrementButtonText) || !virtualDecrementButtonText.toString().toLowerCase().contains(str)) {
                    return;
                }
                list.add(createAccessibilityNodeInfo(3));
                return;
            }
            Editable text = NumberPicker.this.mInputText.getText();
            if (!TextUtils.isEmpty(text) && text.toString().toLowerCase().contains(str)) {
                list.add(createAccessibilityNodeInfo(2));
                return;
            }
            Editable text2 = NumberPicker.this.mInputText.getText();
            if (TextUtils.isEmpty(text2) || !text2.toString().toLowerCase().contains(str)) {
                return;
            }
            list.add(createAccessibilityNodeInfo(2));
        }

        private String getVirtualDecrementButtonText() {
            int wrappedSelectorIndex = NumberPicker.this.mValue - 1;
            if (NumberPicker.this.mWrapSelectorWheel) {
                wrappedSelectorIndex = NumberPicker.this.getWrappedSelectorIndex(wrappedSelectorIndex);
            }
            if (wrappedSelectorIndex >= NumberPicker.this.mMinValue) {
                return NumberPicker.this.mDisplayedValues == null ? NumberPicker.this.formatNumber(wrappedSelectorIndex) : NumberPicker.this.mDisplayedValues[wrappedSelectorIndex - NumberPicker.this.mMinValue];
            }
            return null;
        }

        private String getVirtualIncrementButtonText() {
            int wrappedSelectorIndex = NumberPicker.this.mValue + 1;
            if (NumberPicker.this.mWrapSelectorWheel) {
                wrappedSelectorIndex = NumberPicker.this.getWrappedSelectorIndex(wrappedSelectorIndex);
            }
            if (wrappedSelectorIndex <= NumberPicker.this.mMaxValue) {
                return NumberPicker.this.mDisplayedValues == null ? NumberPicker.this.formatNumber(wrappedSelectorIndex) : NumberPicker.this.mDisplayedValues[wrappedSelectorIndex - NumberPicker.this.mMinValue];
            }
            return null;
        }

        private boolean hasVirtualDecrementButton() {
            return NumberPicker.this.getWrapSelectorWheel() || NumberPicker.this.getValue() > NumberPicker.this.getMinValue();
        }

        private boolean hasVirtualIncrementButton() {
            return NumberPicker.this.getWrapSelectorWheel() || NumberPicker.this.getValue() < NumberPicker.this.getMaxValue();
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        private void sendAccessibilityEventForVirtualButton(int i2, int i3, String str) {
            if (((AccessibilityManager) NumberPicker.this.getContext().getSystemService("accessibility")).isEnabled()) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i3);
                accessibilityEventObtain.setClassName(Button.class.getName());
                accessibilityEventObtain.setPackageName(NumberPicker.this.getContext().getPackageName());
                accessibilityEventObtain.getText().add(str);
                accessibilityEventObtain.setEnabled(NumberPicker.this.isEnabled());
                accessibilityEventObtain.setSource(NumberPicker.this, i2);
                NumberPicker numberPicker = NumberPicker.this;
                numberPicker.requestSendAccessibilityEvent(numberPicker, accessibilityEventObtain);
            }
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        private void sendAccessibilityEventForVirtualText(int i2) {
            if (((AccessibilityManager) NumberPicker.this.getContext().getSystemService("accessibility")).isEnabled()) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i2);
                NumberPicker.this.mInputText.onInitializeAccessibilityEvent(accessibilityEventObtain);
                NumberPicker.this.mInputText.onPopulateAccessibilityEvent(accessibilityEventObtain);
                accessibilityEventObtain.setSource(NumberPicker.this, 2);
                NumberPicker numberPicker = NumberPicker.this;
                numberPicker.requestSendAccessibilityEvent(numberPicker, accessibilityEventObtain);
            }
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i2) {
            return i2 != -1 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? super.createAccessibilityNodeInfo(i2) : createAccessibilityNodeInfoForVirtualButton(3, getVirtualDecrementButtonText(), NumberPicker.this.getScrollX(), NumberPicker.this.getScrollY(), NumberPicker.this.getScrollX() + (NumberPicker.this.getRight() - NumberPicker.this.getLeft()), NumberPicker.this.mTopSelectionDividerTop + NumberPicker.this.mSelectionDividerHeight) : createAccessibiltyNodeInfoForInputText(NumberPicker.this.getScrollX(), NumberPicker.this.mTopSelectionDividerTop + NumberPicker.this.mSelectionDividerHeight, NumberPicker.this.getScrollX() + (NumberPicker.this.getRight() - NumberPicker.this.getLeft()), NumberPicker.this.mBottomSelectionDividerBottom - NumberPicker.this.mSelectionDividerHeight) : createAccessibilityNodeInfoForVirtualButton(1, getVirtualIncrementButtonText(), NumberPicker.this.getScrollX(), NumberPicker.this.mBottomSelectionDividerBottom - NumberPicker.this.mSelectionDividerHeight, NumberPicker.this.getScrollX() + (NumberPicker.this.getRight() - NumberPicker.this.getLeft()), NumberPicker.this.getScrollY() + (NumberPicker.this.getBottom() - NumberPicker.this.getTop())) : createAccessibilityNodeInfoForNumberPicker(NumberPicker.this.getScrollX(), NumberPicker.this.getScrollY(), NumberPicker.this.getScrollX() + (NumberPicker.this.getRight() - NumberPicker.this.getLeft()), NumberPicker.this.getScrollY() + (NumberPicker.this.getBottom() - NumberPicker.this.getTop()));
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i2) {
            if (TextUtils.isEmpty(str)) {
                return Collections.emptyList();
            }
            String lowerCase = str.toLowerCase();
            ArrayList arrayList = new ArrayList();
            if (i2 == -1) {
                findAccessibilityNodeInfosByTextInChild(lowerCase, 3, arrayList);
                findAccessibilityNodeInfosByTextInChild(lowerCase, 2, arrayList);
                findAccessibilityNodeInfosByTextInChild(lowerCase, 1, arrayList);
                return arrayList;
            }
            if (i2 != 1 && i2 != 2 && i2 != 3) {
                return super.findAccessibilityNodeInfosByText(str, i2);
            }
            findAccessibilityNodeInfosByTextInChild(lowerCase, i2, arrayList);
            return arrayList;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i2, int i3, Bundle bundle) {
            if (i2 != -1) {
                if (i2 == 1) {
                    if (i3 == 16) {
                        if (!NumberPicker.this.isEnabled()) {
                            return false;
                        }
                        NumberPicker.this.changeValueByOne(true);
                        sendAccessibilityEventForVirtualView(i2, 1);
                        return true;
                    }
                    if (i3 == 64) {
                        if (this.mAccessibilityFocusedView == i2) {
                            return false;
                        }
                        this.mAccessibilityFocusedView = i2;
                        sendAccessibilityEventForVirtualView(i2, 32768);
                        NumberPicker numberPicker = NumberPicker.this;
                        numberPicker.invalidate(0, numberPicker.mBottomSelectionDividerBottom, NumberPicker.this.getRight(), NumberPicker.this.getBottom());
                        return true;
                    }
                    if (i3 != 128 || this.mAccessibilityFocusedView != i2) {
                        return false;
                    }
                    this.mAccessibilityFocusedView = Integer.MIN_VALUE;
                    sendAccessibilityEventForVirtualView(i2, 65536);
                    NumberPicker numberPicker2 = NumberPicker.this;
                    numberPicker2.invalidate(0, numberPicker2.mBottomSelectionDividerBottom, NumberPicker.this.getRight(), NumberPicker.this.getBottom());
                    return true;
                }
                if (i2 == 2) {
                    if (i3 == 1) {
                        if (!NumberPicker.this.isEnabled() || NumberPicker.this.mInputText.isFocused()) {
                            return false;
                        }
                        return NumberPicker.this.mInputText.requestFocus();
                    }
                    if (i3 == 2) {
                        if (!NumberPicker.this.isEnabled() || !NumberPicker.this.mInputText.isFocused()) {
                            return false;
                        }
                        NumberPicker.this.mInputText.clearFocus();
                        return true;
                    }
                    if (i3 == 16) {
                        return NumberPicker.this.isEnabled();
                    }
                    if (i3 == 64) {
                        if (this.mAccessibilityFocusedView == i2) {
                            return false;
                        }
                        this.mAccessibilityFocusedView = i2;
                        sendAccessibilityEventForVirtualView(i2, 32768);
                        NumberPicker.this.mInputText.invalidate();
                        return true;
                    }
                    if (i3 != 128) {
                        return NumberPicker.this.mInputText.performAccessibilityAction(i3, bundle);
                    }
                    if (this.mAccessibilityFocusedView != i2) {
                        return false;
                    }
                    this.mAccessibilityFocusedView = Integer.MIN_VALUE;
                    sendAccessibilityEventForVirtualView(i2, 65536);
                    NumberPicker.this.mInputText.invalidate();
                    return true;
                }
                if (i2 == 3) {
                    if (i3 == 16) {
                        if (!NumberPicker.this.isEnabled()) {
                            return false;
                        }
                        NumberPicker.this.changeValueByOne(i2 == 1);
                        sendAccessibilityEventForVirtualView(i2, 1);
                        return true;
                    }
                    if (i3 == 64) {
                        if (this.mAccessibilityFocusedView == i2) {
                            return false;
                        }
                        this.mAccessibilityFocusedView = i2;
                        sendAccessibilityEventForVirtualView(i2, 32768);
                        NumberPicker numberPicker3 = NumberPicker.this;
                        numberPicker3.invalidate(0, 0, numberPicker3.getRight(), NumberPicker.this.mTopSelectionDividerTop);
                        return true;
                    }
                    if (i3 != 128 || this.mAccessibilityFocusedView != i2) {
                        return false;
                    }
                    this.mAccessibilityFocusedView = Integer.MIN_VALUE;
                    sendAccessibilityEventForVirtualView(i2, 65536);
                    NumberPicker numberPicker4 = NumberPicker.this;
                    numberPicker4.invalidate(0, 0, numberPicker4.getRight(), NumberPicker.this.mTopSelectionDividerTop);
                    return true;
                }
            } else {
                if (i3 == 64) {
                    if (this.mAccessibilityFocusedView == i2) {
                        return false;
                    }
                    this.mAccessibilityFocusedView = i2;
                    return true;
                }
                if (i3 == 128) {
                    if (this.mAccessibilityFocusedView != i2) {
                        return false;
                    }
                    this.mAccessibilityFocusedView = Integer.MIN_VALUE;
                    return true;
                }
                if (i3 == 4096) {
                    if (!NumberPicker.this.isEnabled() || (!NumberPicker.this.getWrapSelectorWheel() && NumberPicker.this.getValue() >= NumberPicker.this.getMaxValue())) {
                        return false;
                    }
                    NumberPicker.this.changeValueByOne(true);
                    return true;
                }
                if (i3 == 8192) {
                    if (!NumberPicker.this.isEnabled() || (!NumberPicker.this.getWrapSelectorWheel() && NumberPicker.this.getValue() <= NumberPicker.this.getMinValue())) {
                        return false;
                    }
                    NumberPicker.this.changeValueByOne(false);
                    return true;
                }
            }
            return super.performAction(i2, i3, bundle);
        }

        public void sendAccessibilityEventForVirtualView(int i2, int i3) {
            if (i2 == 1) {
                if (hasVirtualIncrementButton()) {
                    sendAccessibilityEventForVirtualButton(i2, i3, getVirtualIncrementButtonText());
                }
            } else if (i2 == 2) {
                sendAccessibilityEventForVirtualText(i3);
            } else if (i2 == 3 && hasVirtualDecrementButton()) {
                sendAccessibilityEventForVirtualButton(i2, i3, getVirtualDecrementButtonText());
            }
        }
    }

    public class BeginSoftInputOnLongPressCommand implements Runnable {
        public BeginSoftInputOnLongPressCommand() {
        }

        @Override // java.lang.Runnable
        public void run() {
            NumberPicker.this.mIgnoreMoveEvents = true;
        }
    }

    public class ChangeCurrentByOneFromLongPressCommand implements Runnable {
        private boolean mIncrement;

        public ChangeCurrentByOneFromLongPressCommand() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStep(boolean z2) {
            this.mIncrement = z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            NumberPicker.this.changeValueByOne(this.mIncrement);
            NumberPicker numberPicker = NumberPicker.this;
            numberPicker.postDelayed(this, numberPicker.mLongPressUpdateInterval);
        }
    }

    public static class CustomEditText extends AppCompatEditText {
        public CustomEditText(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // android.widget.TextView
        public void onEditorAction(int i2) {
            super.onEditorAction(i2);
            if (i2 == 6) {
                clearFocus();
            }
        }
    }

    public interface Formatter {
        String format(int i2);
    }

    public class InputTextFilter extends NumberKeyListener {
        public InputTextFilter() {
        }

        @Override // android.text.method.NumberKeyListener, android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
            if (NumberPicker.this.mDisplayedValues == null) {
                CharSequence charSequenceFilter = super.filter(charSequence, i2, i3, spanned, i4, i5);
                if (charSequenceFilter == null) {
                    charSequenceFilter = charSequence.subSequence(i2, i3);
                }
                String str = String.valueOf(spanned.subSequence(0, i4)) + ((Object) charSequenceFilter) + ((Object) spanned.subSequence(i5, spanned.length()));
                return "".equals(str) ? str : (NumberPicker.this.getSelectedPos(str) > NumberPicker.this.mMaxValue || str.length() > String.valueOf(NumberPicker.this.mMaxValue).length()) ? "" : charSequenceFilter;
            }
            String strValueOf = String.valueOf(charSequence.subSequence(i2, i3));
            if (TextUtils.isEmpty(strValueOf)) {
                return "";
            }
            String str2 = String.valueOf(spanned.subSequence(0, i4)) + ((Object) strValueOf) + ((Object) spanned.subSequence(i5, spanned.length()));
            String lowerCase = String.valueOf(str2).toLowerCase();
            for (String str3 : NumberPicker.this.mDisplayedValues) {
                if (str3.toLowerCase().startsWith(lowerCase)) {
                    NumberPicker.this.postSetSelectionCommand(str2.length(), str3.length());
                    return str3.subSequence(i4, str3.length());
                }
            }
            return "";
        }

        @Override // android.text.method.NumberKeyListener
        public char[] getAcceptedChars() {
            return NumberPicker.DIGIT_CHARACTERS;
        }

        @Override // android.text.method.KeyListener
        public int getInputType() {
            return 1;
        }
    }

    public interface OnScrollListener {
        public static final int SCROLL_STATE_FLING = 2;
        public static final int SCROLL_STATE_IDLE = 0;
        public static final int SCROLL_STATE_TOUCH_SCROLL = 1;

        void onScrollStateChange(NumberPicker numberPicker, int i2);
    }

    public interface OnValueChangeListener {
        void onValueChange(NumberPicker numberPicker, int i2, int i3);
    }

    public class PressedStateHelper implements Runnable {
        public static final int BUTTON_DECREMENT = 2;
        public static final int BUTTON_INCREMENT = 1;
        private final int MODE_PRESS = 1;
        private final int MODE_TAPPED = 2;
        private int mManagedButton;
        private int mMode;

        public PressedStateHelper() {
        }

        public void buttonPressDelayed(int i2) {
            cancel();
            this.mMode = 1;
            this.mManagedButton = i2;
            NumberPicker.this.postDelayed(this, ViewConfiguration.getTapTimeout());
        }

        public void buttonTapped(int i2) {
            cancel();
            this.mMode = 2;
            this.mManagedButton = i2;
            NumberPicker.this.post(this);
        }

        public void cancel() {
            this.mMode = 0;
            this.mManagedButton = 0;
            NumberPicker.this.removeCallbacks(this);
            if (NumberPicker.this.mIncrementVirtualButtonPressed) {
                NumberPicker.this.mIncrementVirtualButtonPressed = false;
                NumberPicker numberPicker = NumberPicker.this;
                numberPicker.invalidate(0, numberPicker.mBottomSelectionDividerBottom, NumberPicker.this.getRight(), NumberPicker.this.getBottom());
            }
            if (NumberPicker.this.mDecrementVirtualButtonPressed) {
                NumberPicker.this.mDecrementVirtualButtonPressed = false;
                NumberPicker numberPicker2 = NumberPicker.this;
                numberPicker2.invalidate(0, 0, numberPicker2.getRight(), NumberPicker.this.mTopSelectionDividerTop);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2 = this.mMode;
            if (i2 == 1) {
                int i3 = this.mManagedButton;
                if (i3 == 1) {
                    NumberPicker.this.mIncrementVirtualButtonPressed = true;
                    NumberPicker numberPicker = NumberPicker.this;
                    numberPicker.invalidate(0, numberPicker.mBottomSelectionDividerBottom, NumberPicker.this.getRight(), NumberPicker.this.getBottom());
                    return;
                } else {
                    if (i3 != 2) {
                        return;
                    }
                    NumberPicker.this.mDecrementVirtualButtonPressed = true;
                    NumberPicker numberPicker2 = NumberPicker.this;
                    numberPicker2.invalidate(0, 0, numberPicker2.getRight(), NumberPicker.this.mTopSelectionDividerTop);
                    return;
                }
            }
            if (i2 != 2) {
                return;
            }
            int i4 = this.mManagedButton;
            if (i4 == 1) {
                if (!NumberPicker.this.mIncrementVirtualButtonPressed) {
                    NumberPicker.this.postDelayed(this, ViewConfiguration.getPressedStateDuration());
                }
                NumberPicker.access$1180(NumberPicker.this, 1);
                NumberPicker numberPicker3 = NumberPicker.this;
                numberPicker3.invalidate(0, numberPicker3.mBottomSelectionDividerBottom, NumberPicker.this.getRight(), NumberPicker.this.getBottom());
                return;
            }
            if (i4 != 2) {
                return;
            }
            if (!NumberPicker.this.mDecrementVirtualButtonPressed) {
                NumberPicker.this.postDelayed(this, ViewConfiguration.getPressedStateDuration());
            }
            NumberPicker.access$1380(NumberPicker.this, 1);
            NumberPicker numberPicker4 = NumberPicker.this;
            numberPicker4.invalidate(0, 0, numberPicker4.getRight(), NumberPicker.this.mTopSelectionDividerTop);
        }
    }

    public class SetSelectionCommand implements Runnable {
        private int mSelectionEnd;
        private int mSelectionStart;

        public SetSelectionCommand() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mSelectionEnd < NumberPicker.this.mInputText.length()) {
                NumberPicker.this.mInputText.setSelection(this.mSelectionStart, this.mSelectionEnd);
            }
        }
    }

    public static class SoundPlayHandler extends Handler {
        private static final int MSG_INIT = 0;
        private static final int MSG_PLAY = 1;
        private static final int MSG_RELEASE = 2;
        private static final SoundPlayerContainer sPlayerContainer = new SoundPlayerContainer();

        public static class SoundPlayerContainer {
            private static final long INTERVAL = 50;
            private long mPrevPlayTime;
            private final Set<Integer> mRefs;
            private int mSoundId;
            private SoundPool mSoundPlayer;

            private SoundPlayerContainer() {
                this.mRefs = new CopyOnWriteArraySet();
            }

            public void init(Context context, int i2) {
                if (this.mSoundPlayer == null) {
                    SoundPool soundPool = new SoundPool(1, 1, 0);
                    this.mSoundPlayer = soundPool;
                    this.mSoundId = soundPool.load(context, R.raw.number_picker_value_change, 1);
                }
                this.mRefs.add(Integer.valueOf(i2));
            }

            public void play() {
                long jCurrentTimeMillis = System.currentTimeMillis();
                SoundPool soundPool = this.mSoundPlayer;
                if (soundPool == null || jCurrentTimeMillis - this.mPrevPlayTime <= INTERVAL) {
                    return;
                }
                soundPool.play(this.mSoundId, 1.0f, 1.0f, 0, 0, 1.0f);
                this.mPrevPlayTime = jCurrentTimeMillis;
            }

            public void release(int i2) {
                SoundPool soundPool;
                if (this.mRefs.remove(Integer.valueOf(i2)) && this.mRefs.isEmpty() && (soundPool = this.mSoundPlayer) != null) {
                    soundPool.release();
                    this.mSoundPlayer = null;
                }
            }
        }

        public SoundPlayHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i2 = message.what;
            if (i2 == 0) {
                sPlayerContainer.init((Context) message.obj, message.arg1);
            } else if (i2 == 1) {
                sPlayerContainer.play();
            } else {
                if (i2 != 2) {
                    return;
                }
                sPlayerContainer.release(message.arg1);
            }
        }

        public void init(Context context, int i2) {
            Message messageObtainMessage = obtainMessage(0, i2, 0);
            messageObtainMessage.obj = context;
            sendMessage(messageObtainMessage);
        }

        public void play() {
            sendMessage(obtainMessage(1));
        }

        public void release(int i2) {
            sendMessage(obtainMessage(2, i2, 0));
        }

        public void stop() {
            removeMessages(1);
        }
    }

    public NumberPicker(Context context) {
        this(context, null);
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [boolean, byte] */
    public static /* synthetic */ boolean access$1180(NumberPicker numberPicker, int i2) {
        ?? r2 = (byte) (i2 ^ (numberPicker.mIncrementVirtualButtonPressed ? 1 : 0));
        numberPicker.mIncrementVirtualButtonPressed = r2;
        return r2;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [boolean, byte] */
    public static /* synthetic */ boolean access$1380(NumberPicker numberPicker, int i2) {
        ?? r2 = (byte) (i2 ^ (numberPicker.mDecrementVirtualButtonPressed ? 1 : 0));
        numberPicker.mDecrementVirtualButtonPressed = r2;
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeValueByOne(boolean z2) {
        if (!this.mHasSelectorWheel) {
            if (z2) {
                setValueInternal(this.mValue + 1, true);
                return;
            } else {
                setValueInternal(this.mValue - 1, true);
                return;
            }
        }
        this.mInputText.setVisibility(4);
        if (!moveToFinalScrollerPosition(this.mFlingScroller)) {
            moveToFinalScrollerPosition(this.mAdjustScroller);
        }
        this.mPreviousScrollerY = 0;
        if (z2) {
            this.mFlingScroller.startScroll(0, 0, 0, -this.mSelectorElementHeight, 300);
        } else {
            this.mFlingScroller.startScroll(0, 0, 0, this.mSelectorElementHeight, 300);
        }
        invalidate();
    }

    private void decrementSelectorIndices(int[] iArr) {
        if (iArr.length - 1 >= 0) {
            System.arraycopy(iArr, 0, iArr, 1, iArr.length - 1);
        }
        int i2 = iArr[1] - 1;
        if (this.mWrapSelectorWheel && i2 < this.mMinValue) {
            i2 = this.mMaxValue;
        }
        iArr[0] = i2;
        ensureCachedScrollSelectorValue(i2);
    }

    private void drawLabelText(Canvas canvas, float f2, float f3, float f4) {
        if (TextUtils.isEmpty(this.mLabel) || isInternationalBuild()) {
            return;
        }
        float fMeasureText = this.mLabelPaint.measureText(this.mLabel.toString());
        canvas.drawText(this.mLabel.toString(), ViewUtils.isLayoutRtl(this) ? Math.max(((f2 - (this.mDisplayedMaxTextWidth / 2.0f)) - this.MARGIN_LABEL_LEFT) - fMeasureText, 0.0f) : Math.min(f2 + (this.mDisplayedMaxTextWidth / 2.0f) + this.MARGIN_LABEL_LEFT, getWidth() - fMeasureText), (f3 - (this.mTextSizeHighlight / 2.0f)) + (this.mLabelTextSize / 2.0f) + this.MARGIN_LABEL_TOP, this.mLabelPaint);
    }

    private float drawScrollValue(Canvas canvas, float f2, float f3) {
        float f4 = this.mCurrentScrollOffset;
        SparseArray<String> sparseArray = this.mSelectorIndexToStringCache;
        for (int i2 : this.mSelectorIndices) {
            String str = sparseArray.get(i2);
            float fAbs = Math.abs(f3 - f4) / this.mSelectorElementHeight;
            int i3 = this.mTextSizeHighlight;
            float f5 = i3;
            float f6 = this.mTextSizeThreshold;
            if (f5 > f6) {
                i3 = (int) f6;
            } else {
                float width = getWidth() / this.mSelectorWheelPaint.measureText(str);
                if (width < 1.0f) {
                    i3 = (int) (this.mTextSizeHighlight * width);
                }
            }
            int i4 = this.mTextSizeHint;
            int i5 = (int) (i3 * 0.85f);
            if (i4 > i5) {
                i4 = i5;
            }
            float textSize = getTextSize(fAbs, i3, i4);
            this.mSelectorWheelPaint.setTextSize(textSize);
            this.mSelectorWheelPaint.setColor(getAlphaGradient(fAbs, this.mTextColorHint, false));
            float f7 = ((textSize - i4) / 2.0f) + f4;
            canvas.drawText(str, f2, f7, this.mSelectorWheelPaint);
            if (fAbs < 1.0f) {
                this.mSelectorWheelPaint.setColor(getAlphaGradient(fAbs, this.mTextColorHighlight, true));
                canvas.drawText(str, f2, f7, this.mSelectorWheelPaint);
            }
            f4 += this.mSelectorElementHeight;
        }
        return f4;
    }

    private void ensureCachedScrollSelectorValue(int i2) {
        String number;
        SparseArray<String> sparseArray = this.mSelectorIndexToStringCache;
        if (sparseArray.get(i2) != null) {
            return;
        }
        int i3 = this.mMinValue;
        if (i2 < i3 || i2 > this.mMaxValue) {
            number = "";
        } else {
            String[] strArr = this.mDisplayedValues;
            number = strArr != null ? strArr[i2 - i3] : formatNumber(i2);
        }
        sparseArray.put(i2, number);
    }

    private boolean ensureScrollWheelAdjusted() {
        int i2 = this.mInitialScrollOffset - this.mCurrentScrollOffset;
        if (i2 == 0) {
            return false;
        }
        this.mPreviousScrollerY = 0;
        int iAbs = Math.abs(i2);
        int i3 = this.mSelectorElementHeight;
        if (iAbs > i3 / 2) {
            if (i2 > 0) {
                i3 = -i3;
            }
            i2 += i3;
        }
        this.mAdjustScroller.startScroll(0, 0, 0, i2, 800);
        invalidate();
        return true;
    }

    private void fling(int i2) {
        this.mPreviousScrollerY = 0;
        if (i2 > 0) {
            this.mFlingScroller.fling(0, 0, 0, i2, 0, 0, 0, Integer.MAX_VALUE);
        } else {
            this.mFlingScroller.fling(0, Integer.MAX_VALUE, 0, i2, 0, 0, 0, Integer.MAX_VALUE);
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String formatNumber(int i2) {
        Formatter formatter = this.mFormatter;
        return formatter != null ? formatter.format(i2) : SimpleNumberFormatter.format(i2);
    }

    private int getAlphaGradient(float f2, int i2, boolean z2) {
        if (f2 >= 1.0f) {
            return i2;
        }
        return ((z2 ? (int) (((-f2) * Color.alpha(i2)) + Color.alpha(i2)) : (int) (f2 * Color.alpha(i2))) << 24) | (16777215 & i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getSelectedPos(String str) {
        try {
            if (this.mDisplayedValues == null) {
                return Integer.parseInt(str);
            }
            for (int i2 = 0; i2 < this.mDisplayedValues.length; i2++) {
                str = str.toLowerCase();
                if (this.mDisplayedValues[i2].toLowerCase().startsWith(str)) {
                    return this.mMinValue + i2;
                }
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return this.mMinValue;
        }
    }

    private float getTextSize(float f2, int i2, int i3) {
        return f2 >= 1.0f ? i3 : (f2 * (i3 - i2)) + i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getWrappedSelectorIndex(int i2) {
        int i3 = this.mMaxValue;
        if (i2 > i3) {
            int i4 = this.mMinValue;
            return (i4 + ((i2 - i3) % (i3 - i4))) - 1;
        }
        int i5 = this.mMinValue;
        return i2 < i5 ? (i3 - ((i5 - i2) % (i3 - i5))) + 1 : i2;
    }

    private void incrementSelectorIndices(int[] iArr) {
        if (iArr.length - 1 >= 0) {
            System.arraycopy(iArr, 1, iArr, 0, iArr.length - 1);
        }
        int i2 = iArr[iArr.length - 2] + 1;
        if (this.mWrapSelectorWheel && i2 > this.mMaxValue) {
            i2 = this.mMinValue;
        }
        iArr[iArr.length - 1] = i2;
        ensureCachedScrollSelectorValue(i2);
    }

    private void initInputText() {
        this.mInputText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: miuix.pickerwidget.widget.NumberPicker.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z2) {
                if (z2) {
                    NumberPicker.this.mInputText.selectAll();
                } else {
                    NumberPicker.this.mInputText.setSelection(0, 0);
                    NumberPicker.this.validateInputTextView(view);
                }
            }
        });
        this.mInputText.setFilters(new InputFilter[]{new InputTextFilter()});
        this.mInputText.setRawInputType(2);
        this.mInputText.setImeOptions(6);
        this.mInputText.setVisibility(4);
        this.mInputText.setGravity(8388611);
        this.mInputText.setScaleX(0.0f);
        this.mInputText.setSaveEnabled(false);
        EditText editText = this.mInputText;
        editText.setPadding(this.mTextPadding, editText.getPaddingTop(), this.mTextPadding, this.mInputText.getPaddingRight());
    }

    private void initLabelPaint() {
        Paint paint = new Paint();
        this.mLabelPaint = paint;
        paint.setAntiAlias(true);
        this.mLabelPaint.setFakeBoldText(true);
        this.mLabelPaint.setColor(this.mLabelTextColor);
        this.mLabelPaint.setTextSize(this.mLabelTextSize);
    }

    private Paint initSelectorWheelPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(this.mTextSizeHighlight);
        paint.setTypeface(this.mInputText.getTypeface());
        paint.setColor(this.mInputText.getTextColors().getColorForState(LinearLayout.ENABLED_STATE_SET, -1));
        return paint;
    }

    private void initSoundPlayer() {
        if (this.mSoundPlayHandler == null) {
            SoundPlayHandler soundPlayHandler = new SoundPlayHandler(WorkerThreads.acquireWorker(SOUND_PLAY_THREAD));
            this.mSoundPlayHandler = soundPlayHandler;
            soundPlayHandler.init(getContext().getApplicationContext(), this.mId);
        }
    }

    private void initThreshHolds() {
        this.mLabelTextSizeThreshold = getContext().getResources().getDimensionPixelSize(R.dimen.miuix_label_text_size_small);
        this.mTextSizeThreshold = getContext().getResources().getDimensionPixelSize(R.dimen.miuix_text_size_small);
    }

    private void initializeFadingEdges() {
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength(((getBottom() - getTop()) - this.mTextSize) / 2);
    }

    private void initializeSelectorWheel() {
        initializeSelectorWheelIndices();
        float bottom = (getBottom() - getTop()) - (this.mSelectorIndices.length * this.mTextSize);
        if (bottom < 0.0f) {
            bottom = 0.0f;
        }
        int length = (int) ((bottom / r0.length) + 0.5f);
        this.mSelectorTextGapHeight = length;
        this.mSelectorElementHeight = this.mTextSize + length;
        int baseline = (this.mInputText.getBaseline() + this.mInputText.getTop()) - this.mSelectorElementHeight;
        this.mInitialScrollOffset = baseline;
        this.mCurrentScrollOffset = baseline;
        updateInputTextView();
    }

    private void initializeSelectorWheelIndices() {
        this.mSelectorIndexToStringCache.clear();
        int[] iArr = this.mSelectorIndices;
        int value = getValue();
        for (int i2 = 0; i2 < this.mSelectorIndices.length; i2++) {
            int wrappedSelectorIndex = (i2 - 1) + value;
            if (this.mWrapSelectorWheel) {
                wrappedSelectorIndex = getWrappedSelectorIndex(wrappedSelectorIndex);
            }
            iArr[i2] = wrappedSelectorIndex;
            ensureCachedScrollSelectorValue(wrappedSelectorIndex);
        }
    }

    private boolean isInternationalBuild() {
        if (this.mModDevice == null) {
            this.mModDevice = (String) ReflectUtil.callStaticObjectMethod(ReflectUtil.getClass("android.os.SystemProperties"), String.class, "get", new Class[]{String.class, String.class}, NotificationUtil.PRODUCT_MODE_DEVICE, "");
        }
        return this.mModDevice.endsWith("_global");
    }

    private int makeMeasureSpec(int i2, int i3) {
        if (i3 == -1) {
            return i2;
        }
        int size = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i3), BasicMeasure.EXACTLY);
        }
        if (mode == 0) {
            return View.MeasureSpec.makeMeasureSpec(i3, BasicMeasure.EXACTLY);
        }
        if (mode == 1073741824) {
            return i2;
        }
        throw new IllegalArgumentException("Unknown measure mode: " + mode);
    }

    private boolean moveToFinalScrollerPosition(Scroller scroller) {
        scroller.forceFinished(true);
        int finalY = scroller.getFinalY() - scroller.getCurrY();
        int i2 = this.mInitialScrollOffset - ((this.mCurrentScrollOffset + finalY) % this.mSelectorElementHeight);
        if (i2 == 0) {
            return false;
        }
        int iAbs = Math.abs(i2);
        int i3 = this.mSelectorElementHeight;
        if (iAbs > i3 / 2) {
            i2 = i2 > 0 ? i2 - i3 : i2 + i3;
        }
        scrollBy(0, finalY + i2);
        return true;
    }

    private void notifyChange(int i2) {
        sendAccessibilityEvent(4);
        playSound();
        HapticCompat.performHapticFeedback(this, HapticFeedbackConstants.MIUI_GEAR_HEAVY, HapticFeedbackConstants.MIUI_MESH_NORMAL);
        OnValueChangeListener onValueChangeListener = this.mOnValueChangeListener;
        if (onValueChangeListener != null) {
            onValueChangeListener.onValueChange(this, i2, this.mValue);
        }
    }

    private void onScrollStateChange(int i2) {
        if (this.mScrollState == i2) {
            return;
        }
        if (i2 == 0) {
            String str = this.mUpdateText;
            if (str != null && !str.equals(this.mInputText.getText().toString())) {
                this.mInputText.setText(this.mUpdateText);
            }
            this.mUpdateText = null;
            stopSoundPlay();
        }
        this.mScrollState = i2;
        OnScrollListener onScrollListener = this.mOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChange(this, i2);
        }
    }

    private void onScrollerFinished(Scroller scroller) {
        if (scroller == this.mFlingScroller) {
            if (!ensureScrollWheelAdjusted()) {
                updateInputTextView();
            }
            onScrollStateChange(0);
        } else if (this.mScrollState != 1) {
            updateInputTextView();
        }
    }

    private void parseStyle(AttributeSet attributeSet, int i2) {
        Resources resources = getResources();
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.NumberPicker, i2, R.style.Widget_NumberPicker_DayNight);
        this.mLabel = typedArrayObtainStyledAttributes.getText(R.styleable.NumberPicker_android_text);
        this.mTextSizeHighlight = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.NumberPicker_textSizeHighlight, resources.getDimensionPixelOffset(R.dimen.miuix_appcompat_number_picker_text_size_highlight_normal));
        this.mTextSizeHint = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.NumberPicker_textSizeHint, resources.getDimensionPixelOffset(R.dimen.miuix_appcompat_number_picker_text_size_hint_normal));
        this.mLabelTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.NumberPicker_android_labelTextSize, resources.getDimensionPixelOffset(R.dimen.miuix_appcompat_number_picker_label_text_size));
        this.mTextColorHighlight = typedArrayObtainStyledAttributes.getColor(R.styleable.NumberPicker_android_textColorHighlight, resources.getColor(R.color.miuix_appcompat_default_number_picker_highlight_color));
        this.mTextColorHint = typedArrayObtainStyledAttributes.getColor(R.styleable.NumberPicker_android_textColorHint, resources.getColor(R.color.miuix_appcompat_default_number_picker_hint_color));
        this.mLabelTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.NumberPicker_labelTextColor, resources.getColor(R.color.miuix_appcompat_number_picker_label_color));
        this.mTextPadding = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.NumberPicker_labelPadding, resources.getDimensionPixelOffset(R.dimen.miuix_appcompat_number_picker_label_padding));
        typedArrayObtainStyledAttributes.recycle();
        this.mOriginLabelTextSize = this.mLabelTextSize;
        this.mOriginTextSizeHighlight = this.mTextSizeHighlight;
        this.mOriginTextSizeHint = this.mTextSizeHint;
    }

    private void playSound() {
        SoundPlayHandler soundPlayHandler = this.mSoundPlayHandler;
        if (soundPlayHandler != null) {
            soundPlayHandler.play();
        }
    }

    private void postBeginSoftInputOnLongPressCommand() {
        BeginSoftInputOnLongPressCommand beginSoftInputOnLongPressCommand = this.mBeginSoftInputOnLongPressCommand;
        if (beginSoftInputOnLongPressCommand == null) {
            this.mBeginSoftInputOnLongPressCommand = new BeginSoftInputOnLongPressCommand();
        } else {
            removeCallbacks(beginSoftInputOnLongPressCommand);
        }
        postDelayed(this.mBeginSoftInputOnLongPressCommand, ViewConfiguration.getLongPressTimeout());
    }

    private void postChangeCurrentByOneFromLongPress(boolean z2, long j2) {
        ChangeCurrentByOneFromLongPressCommand changeCurrentByOneFromLongPressCommand = this.mChangeCurrentByOneFromLongPressCommand;
        if (changeCurrentByOneFromLongPressCommand == null) {
            this.mChangeCurrentByOneFromLongPressCommand = new ChangeCurrentByOneFromLongPressCommand();
        } else {
            removeCallbacks(changeCurrentByOneFromLongPressCommand);
        }
        this.mChangeCurrentByOneFromLongPressCommand.setStep(z2);
        postDelayed(this.mChangeCurrentByOneFromLongPressCommand, j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postSetSelectionCommand(int i2, int i3) {
        SetSelectionCommand setSelectionCommand = this.mSetSelectionCommand;
        if (setSelectionCommand == null) {
            this.mSetSelectionCommand = new SetSelectionCommand();
        } else {
            removeCallbacks(setSelectionCommand);
        }
        this.mSetSelectionCommand.mSelectionStart = i2;
        this.mSetSelectionCommand.mSelectionEnd = i3;
        post(this.mSetSelectionCommand);
    }

    private void refreshWheel() {
        initializeSelectorWheelIndices();
        invalidate();
    }

    private void releaseSoundPlayer() {
        SoundPlayHandler soundPlayHandler = this.mSoundPlayHandler;
        if (soundPlayHandler != null) {
            soundPlayHandler.release(this.mId);
            this.mSoundPlayHandler = null;
        }
    }

    private void removeAllCallbacks() {
        ChangeCurrentByOneFromLongPressCommand changeCurrentByOneFromLongPressCommand = this.mChangeCurrentByOneFromLongPressCommand;
        if (changeCurrentByOneFromLongPressCommand != null) {
            removeCallbacks(changeCurrentByOneFromLongPressCommand);
        }
        SetSelectionCommand setSelectionCommand = this.mSetSelectionCommand;
        if (setSelectionCommand != null) {
            removeCallbacks(setSelectionCommand);
        }
        BeginSoftInputOnLongPressCommand beginSoftInputOnLongPressCommand = this.mBeginSoftInputOnLongPressCommand;
        if (beginSoftInputOnLongPressCommand != null) {
            removeCallbacks(beginSoftInputOnLongPressCommand);
        }
        this.mPressedStateHelper.cancel();
    }

    private void removeBeginSoftInputCommand() {
        BeginSoftInputOnLongPressCommand beginSoftInputOnLongPressCommand = this.mBeginSoftInputOnLongPressCommand;
        if (beginSoftInputOnLongPressCommand != null) {
            removeCallbacks(beginSoftInputOnLongPressCommand);
        }
    }

    private void removeChangeCurrentByOneFromLongPress() {
        ChangeCurrentByOneFromLongPressCommand changeCurrentByOneFromLongPressCommand = this.mChangeCurrentByOneFromLongPressCommand;
        if (changeCurrentByOneFromLongPressCommand != null) {
            removeCallbacks(changeCurrentByOneFromLongPressCommand);
        }
    }

    private int resolveSizeAndStateRespectingMinSize(int i2, int i3, int i4) {
        return i2 != -1 ? LinearLayout.resolveSizeAndState(Math.max(i2, i3), i4, 0) : i3;
    }

    private void setValueInternal(int i2, boolean z2) {
        int wrappedSelectorIndex = this.mWrapSelectorWheel ? getWrappedSelectorIndex(i2) : Math.min(Math.max(i2, this.mMinValue), this.mMaxValue);
        int i3 = this.mValue;
        if (i3 == wrappedSelectorIndex) {
            return;
        }
        this.mValue = wrappedSelectorIndex;
        updateInputTextView();
        if (z2) {
            notifyChange(i3);
        }
        initializeSelectorWheelIndices();
        invalidate();
    }

    private void stopSoundPlay() {
        SoundPlayHandler soundPlayHandler = this.mSoundPlayHandler;
        if (soundPlayHandler != null) {
            soundPlayHandler.stop();
        }
    }

    private void trimLabelTextSize(float f2) {
        if (getLabelWidth() > 0.0f) {
            int i2 = this.mOriginLabelTextSize;
            this.mLabelTextSize = i2;
            this.mLabelPaint.setTextSize(i2);
            while ((this.mDisplayedMaxTextWidth / 2.0f) + f2 + this.MARGIN_LABEL_LEFT + getLabelWidth() > getWidth()) {
                int i3 = this.mLabelTextSize;
                if (i3 <= this.mLabelTextSizeThreshold) {
                    return;
                }
                int i4 = (int) (i3 * this.mLabelTextSizeTrimFactor);
                this.mLabelTextSize = i4;
                this.mLabelPaint.setTextSize(i4);
            }
        }
    }

    private void tryComputeMaxWidth() {
        String str;
        float f2;
        if (this.mComputeMaxWidth) {
            this.mSelectorWheelPaint.setTextSize(this.mTextSizeHighlight);
            String[] strArr = this.mDisplayedValues;
            int i2 = 0;
            if (strArr == null) {
                float f3 = 0.0f;
                int i3 = 0;
                while (i2 < 9) {
                    float fMeasureText = this.mSelectorWheelPaint.measureText(String.valueOf(i2));
                    if (fMeasureText > f3) {
                        i3 = i2;
                        f3 = fMeasureText;
                    }
                    i2++;
                }
                int length = formatNumber(this.mMaxValue).length();
                f2 = (int) (length * f3);
                char[] cArr = new char[length];
                Arrays.fill(cArr, (char) (i3 + 48));
                str = new String(cArr);
            } else {
                int length2 = strArr.length;
                str = null;
                float f4 = -1.0f;
                while (i2 < length2) {
                    String str2 = this.mDisplayedValues[i2];
                    float fMeasureText2 = this.mSelectorWheelPaint.measureText(str2);
                    if (fMeasureText2 > f4) {
                        str = str2;
                        f4 = fMeasureText2;
                    }
                    i2++;
                }
                f2 = f4;
            }
            this.mDisplayedMaxTextWidth = f2;
            this.mDisplayedMaxText = str;
            float paddingLeft = f2 + this.mInputText.getPaddingLeft() + this.mInputText.getPaddingRight() + getPaddingLeft() + getPaddingRight();
            if (this.mMaxWidth != paddingLeft) {
                int i4 = this.mMinWidth;
                if (paddingLeft > i4) {
                    this.mMaxWidth = (int) paddingLeft;
                } else {
                    this.mMaxWidth = i4;
                }
            }
        }
    }

    private boolean updateInputTextView() {
        String displayedMaxText = getDisplayedMaxText();
        if (TextUtils.isEmpty(displayedMaxText)) {
            return false;
        }
        if (this.mScrollState != 0) {
            this.mUpdateText = displayedMaxText;
            return true;
        }
        if (displayedMaxText.equals(this.mInputText.getText().toString())) {
            return true;
        }
        this.mInputText.setText(displayedMaxText);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void validateInputTextView(View view) {
        String strValueOf = String.valueOf(((TextView) view).getText());
        if (TextUtils.isEmpty(strValueOf)) {
            updateInputTextView();
        } else {
            setValueInternal(getSelectedPos(strValueOf.toString()), true);
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        Scroller scroller = this.mFlingScroller;
        if (scroller.isFinished()) {
            scroller = this.mAdjustScroller;
            if (scroller.isFinished()) {
                return;
            }
        }
        scroller.computeScrollOffset();
        int currY = scroller.getCurrY();
        if (this.mPreviousScrollerY == 0) {
            this.mPreviousScrollerY = scroller.getStartY();
        }
        scrollBy(0, currY - this.mPreviousScrollerY);
        this.mPreviousScrollerY = currY;
        if (scroller.isFinished()) {
            onScrollerFinished(scroller);
        } else {
            invalidate();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x004c, code lost:
    
        requestFocus();
        r5.mLastHandledDownDpadKeyCode = r0;
        removeAllCallbacks();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x005a, code lost:
    
        if (r5.mFlingScroller.isFinished() == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x005c, code lost:
    
        if (r0 != 20) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005e, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0060, code lost:
    
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0061, code lost:
    
        changeValueByOne(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0064, code lost:
    
        return true;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean dispatchKeyEvent(android.view.KeyEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getKeyCode()
            r1 = 19
            r2 = 20
            if (r0 == r1) goto L19
            if (r0 == r2) goto L19
            r1 = 23
            if (r0 == r1) goto L15
            r1 = 66
            if (r0 == r1) goto L15
            goto L65
        L15:
            r5.removeAllCallbacks()
            goto L65
        L19:
            boolean r1 = r5.mHasSelectorWheel
            if (r1 != 0) goto L1e
            goto L65
        L1e:
            int r1 = r6.getAction()
            r3 = 1
            if (r1 == 0) goto L30
            if (r1 == r3) goto L28
            goto L65
        L28:
            int r1 = r5.mLastHandledDownDpadKeyCode
            if (r1 != r0) goto L65
            r6 = -1
            r5.mLastHandledDownDpadKeyCode = r6
            return r3
        L30:
            boolean r1 = r5.mWrapSelectorWheel
            if (r1 != 0) goto L42
            if (r0 != r2) goto L37
            goto L42
        L37:
            int r1 = r5.getValue()
            int r4 = r5.getMinValue()
            if (r1 <= r4) goto L65
            goto L4c
        L42:
            int r1 = r5.getValue()
            int r4 = r5.getMaxValue()
            if (r1 >= r4) goto L65
        L4c:
            r5.requestFocus()
            r5.mLastHandledDownDpadKeyCode = r0
            r5.removeAllCallbacks()
            android.widget.Scroller r6 = r5.mFlingScroller
            boolean r6 = r6.isFinished()
            if (r6 == 0) goto L64
            if (r0 != r2) goto L60
            r6 = r3
            goto L61
        L60:
            r6 = 0
        L61:
            r5.changeValueByOne(r6)
        L64:
            return r3
        L65:
            boolean r5 = super.dispatchKeyEvent(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.pickerwidget.widget.NumberPicker.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        getParent().requestDisallowInterceptTouchEvent(true);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            removeAllCallbacks();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            removeAllCallbacks();
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        tryComputeMaxWidth();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return NumberPicker.class.getName();
    }

    @Override // android.view.View
    public float getBottomFadingEdgeStrength() {
        return 0.9f;
    }

    public String getDisplayedMaxText() {
        String str = this.mDisplayedMaxText;
        return str == null ? "" : str;
    }

    public float getDisplayedMaxTextWidth() {
        float textSize = this.mSelectorWheelPaint.getTextSize();
        this.mSelectorWheelPaint.setTextSize(this.mOriginTextSizeHighlight);
        float fMeasureText = this.mSelectorWheelPaint.measureText(getDisplayedMaxText());
        this.mSelectorWheelPaint.setTextSize(textSize);
        return fMeasureText;
    }

    public String[] getDisplayedValues() {
        return this.mDisplayedValues;
    }

    public float getLabelWidth() {
        if (TextUtils.isEmpty(this.mLabel) || isInternationalBuild()) {
            return 0.0f;
        }
        return this.mLabelPaint.measureText(this.mLabel.toString());
    }

    public int getMarginLabelLeft() {
        return this.MARGIN_LABEL_LEFT;
    }

    public int getMaxValue() {
        return this.mMaxValue;
    }

    public int getMinValue() {
        return this.mMinValue;
    }

    public int getOriginTextSizeHighlight() {
        return this.mOriginTextSizeHighlight;
    }

    public int getOriginTextSizeHint() {
        return this.mOriginTextSizeHint;
    }

    public float getOriginalLabelWidth() {
        if (TextUtils.isEmpty(this.mLabel) || isInternationalBuild()) {
            return 0.0f;
        }
        float textSize = this.mLabelPaint.getTextSize();
        this.mLabelPaint.setTextSize(this.mOriginLabelTextSize);
        float fMeasureText = this.mLabelPaint.measureText(this.mLabel.toString());
        this.mLabelPaint.setTextSize(textSize);
        return fMeasureText;
    }

    public int getTextSizeHighlight() {
        return this.mTextSizeHighlight;
    }

    public int getTextSizeHint() {
        return this.mTextSizeHint;
    }

    @Override // android.view.View
    public float getTopFadingEdgeStrength() {
        return 0.9f;
    }

    public int getValue() {
        return this.mValue;
    }

    public boolean getWrapSelectorWheel() {
        return this.mWrapSelectorWheel;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        initSoundPlayer();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        initThreshHolds();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        releaseSoundPlayer();
        removeAllCallbacks();
        WorkerThreads.releaseWorker(SOUND_PLAY_THREAD);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas canvas) {
        if (!this.mHasSelectorWheel) {
            super.onDraw(canvas);
            return;
        }
        float right = (((getRight() - getLeft()) + getPaddingLeft()) - getPaddingRight()) / 2;
        float f2 = this.mInitialScrollOffset + this.mSelectorElementHeight;
        drawLabelText(canvas, right, f2, drawScrollValue(canvas, right, f2));
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (isEnabled()) {
            accessibilityNodeInfo.setScrollable(true);
            accessibilityNodeInfo.addAction(8192);
            accessibilityNodeInfo.addAction(4096);
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS);
            accessibilityNodeInfo.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(0, this.mMinValue - 1, this.mMaxValue + 1, this.mValue));
            StringBuilder sb = new StringBuilder();
            String[] strArr = this.mDisplayedValues;
            sb.append(strArr == null ? formatNumber(this.mValue) : strArr[this.mValue - this.mMinValue]);
            sb.append(TextUtils.isEmpty(this.mLabel) ? "" : this.mLabel);
            accessibilityNodeInfo.setContentDescription(sb.toString());
            accessibilityNodeInfo.setStateDescription(getContext().getString(R.string.miuix_access_state_desc));
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.mHasSelectorWheel || !isEnabled() || motionEvent.getActionMasked() != 0) {
            return false;
        }
        removeAllCallbacks();
        this.mInputText.setVisibility(4);
        float y2 = motionEvent.getY();
        this.mLastDownEventY = y2;
        this.mLastDownOrMoveEventY = y2;
        this.mLastDownEventTime = motionEvent.getEventTime();
        this.mIgnoreMoveEvents = false;
        this.mShowSoftInputOnTap = false;
        float f2 = this.mLastDownEventY;
        if (f2 < this.mTopSelectionDividerTop) {
            if (this.mScrollState == 0) {
                this.mPressedStateHelper.buttonPressDelayed(2);
            }
        } else if (f2 > this.mBottomSelectionDividerBottom && this.mScrollState == 0) {
            this.mPressedStateHelper.buttonPressDelayed(1);
        }
        if (!this.mFlingScroller.isFinished()) {
            this.mFlingScroller.forceFinished(true);
            this.mAdjustScroller.forceFinished(true);
            onScrollStateChange(0);
        } else if (this.mAdjustScroller.isFinished()) {
            float f3 = this.mLastDownEventY;
            if (f3 < this.mTopSelectionDividerTop) {
                postChangeCurrentByOneFromLongPress(false, ViewConfiguration.getLongPressTimeout());
            } else if (f3 > this.mBottomSelectionDividerBottom) {
                postChangeCurrentByOneFromLongPress(true, ViewConfiguration.getLongPressTimeout());
            } else {
                this.mShowSoftInputOnTap = true;
                postBeginSoftInputOnLongPressCommand();
            }
        } else {
            this.mFlingScroller.forceFinished(true);
            this.mAdjustScroller.forceFinished(true);
        }
        return true;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        if (!this.mHasSelectorWheel) {
            super.onLayout(z2, i2, i3, i4, i5);
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int measuredWidth2 = this.mInputText.getMeasuredWidth();
        int measuredHeight2 = this.mInputText.getMeasuredHeight();
        int i6 = (measuredWidth - measuredWidth2) / 2;
        int i7 = (measuredHeight - measuredHeight2) / 2;
        this.mInputText.layout(i6, i7, measuredWidth2 + i6, measuredHeight2 + i7);
        if (z2) {
            initializeSelectorWheel();
            initializeFadingEdges();
            int height = getHeight();
            int i8 = this.mSelectionDividersDistance;
            int i9 = this.mSelectionDividerHeight;
            int i10 = ((height - i8) / 2) - i9;
            this.mTopSelectionDividerTop = i10;
            this.mBottomSelectionDividerBottom = i10 + (i9 * 2) + i8;
        }
        trimLabelTextSize((((getRight() - getLeft()) + getPaddingLeft()) - getPaddingRight()) / 2.0f);
        Drawable background = getBackground();
        int i11 = this.mMaxWidth + 20;
        if (this.mMeasureBackgroundEnabled && (background instanceof StateListDrawable)) {
            StateListDrawable stateListDrawable = (StateListDrawable) background;
            int stateCount = stateListDrawable.getStateCount();
            for (int i12 = 0; i12 < stateCount; i12++) {
                Drawable stateDrawable = stateListDrawable.getStateDrawable(i12);
                if (stateDrawable instanceof LayerDrawable) {
                    LayerDrawable layerDrawable = (LayerDrawable) stateDrawable;
                    int numberOfLayers = layerDrawable.getNumberOfLayers();
                    for (int i13 = 0; i13 < numberOfLayers; i13++) {
                        Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(layerDrawable.getId(i13));
                        if (drawableFindDrawableByLayerId instanceof GradientDrawable) {
                            ((GradientDrawable) drawableFindDrawableByLayerId).setSize(getWidth() > i11 ? i11 : getWidth(), getHeight());
                        }
                    }
                }
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (!this.mHasSelectorWheel) {
            super.onMeasure(i2, i3);
        } else {
            super.onMeasure(makeMeasureSpec(i2, this.mMaxWidth), makeMeasureSpec(i3, this.mMaxHeight));
            setMeasuredDimension(resolveSizeAndStateRespectingMinSize(this.mMinWidth, getMeasuredWidth(), i2), resolveSizeAndStateRespectingMinSize(this.mMinHeight, getMeasuredHeight(), i3));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005c  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instruction units count: 237
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.pickerwidget.widget.NumberPicker.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int i2, Bundle bundle) {
        if (super.performAccessibilityAction(i2, bundle)) {
            return true;
        }
        if (!isEnabled()) {
            return false;
        }
        if (i2 != 4096 && i2 != 8192) {
            return false;
        }
        changeValueByOne(i2 == 4096);
        return true;
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        int[] iArr = this.mSelectorIndices;
        boolean z2 = this.mWrapSelectorWheel;
        if (!z2 && i3 > 0 && iArr[1] <= this.mMinValue) {
            this.mCurrentScrollOffset = this.mInitialScrollOffset;
            return;
        }
        if (!z2 && i3 < 0 && iArr[1] >= this.mMaxValue) {
            this.mCurrentScrollOffset = this.mInitialScrollOffset;
            return;
        }
        this.mCurrentScrollOffset += i3;
        while (i3 > 0) {
            int i4 = this.mCurrentScrollOffset;
            if (i4 - this.mInitialScrollOffset <= this.mSelectorTextGapHeight) {
                break;
            }
            this.mCurrentScrollOffset = i4 - this.mSelectorElementHeight;
            decrementSelectorIndices(iArr);
            setValueInternal(iArr[1], true);
            if (!this.mWrapSelectorWheel && iArr[1] <= this.mMinValue) {
                this.mCurrentScrollOffset = this.mInitialScrollOffset;
            }
        }
        while (i3 < 0) {
            int i5 = this.mCurrentScrollOffset;
            if (i5 - this.mInitialScrollOffset >= (-this.mSelectorTextGapHeight)) {
                return;
            }
            this.mCurrentScrollOffset = i5 + this.mSelectorElementHeight;
            incrementSelectorIndices(iArr);
            setValueInternal(iArr[1], true);
            if (!this.mWrapSelectorWheel && iArr[1] >= this.mMaxValue) {
                this.mCurrentScrollOffset = this.mInitialScrollOffset;
            }
        }
    }

    public void setDisplayedValues(String[] strArr) {
        if (this.mDisplayedValues == strArr) {
            return;
        }
        this.mDisplayedValues = strArr;
        if (strArr != null) {
            this.mInputText.setRawInputType(524289);
        } else {
            this.mInputText.setRawInputType(2);
        }
        updateInputTextView();
        initializeSelectorWheelIndices();
        tryComputeMaxWidth();
    }

    public void setFormatter(Formatter formatter) {
        if (formatter == this.mFormatter) {
            return;
        }
        this.mFormatter = formatter;
        initializeSelectorWheelIndices();
        updateInputTextView();
    }

    public void setLabel(String str) {
        CharSequence charSequence = this.mLabel;
        if ((charSequence != null || str == null) && (charSequence == null || charSequence.equals(str))) {
            return;
        }
        this.mLabel = str;
        invalidate();
    }

    public void setLabelTextSizeThreshold(float f2) {
        this.mLabelTextSizeThreshold = Math.max(f2, 0.0f);
    }

    public void setLabelTextSizeTrimFactor(float f2) {
        if (f2 <= 0.0f || f2 >= 1.0f) {
            return;
        }
        this.mLabelTextSizeTrimFactor = f2;
    }

    public void setMaxFlingSpeedFactor(float f2) {
        if (f2 >= 0.0f) {
            this.mMaxFlingSpeedFactor = f2;
        }
    }

    public void setMaxValue(int i2) {
        if (this.mMaxValue == i2) {
            return;
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("maxValue must be >= 0");
        }
        this.mMaxValue = i2;
        if (i2 < this.mValue) {
            this.mValue = i2;
        }
        setWrapSelectorWheel(i2 - this.mMinValue > this.mSelectorIndices.length);
        initializeSelectorWheelIndices();
        updateInputTextView();
        tryComputeMaxWidth();
        invalidate();
    }

    public void setMeasureBackgroundEnabled(boolean z2) {
        this.mMeasureBackgroundEnabled = z2;
    }

    public void setMinValue(int i2) {
        if (this.mMinValue == i2) {
            return;
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("minValue must be >= 0");
        }
        this.mMinValue = i2;
        if (i2 > this.mValue) {
            this.mValue = i2;
        }
        setWrapSelectorWheel(this.mMaxValue - i2 > this.mSelectorIndices.length);
        initializeSelectorWheelIndices();
        updateInputTextView();
        tryComputeMaxWidth();
        invalidate();
    }

    public void setOnLongPressUpdateInterval(long j2) {
        this.mLongPressUpdateInterval = j2;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }

    public void setOnValueChangedListener(OnValueChangeListener onValueChangeListener) {
        this.mOnValueChangeListener = onValueChangeListener;
    }

    public void setTextSizeHighlight(int i2) {
        this.mTextSizeHighlight = i2;
        this.mSelectorWheelPaint.setTextSize(i2);
        this.mDisplayedMaxTextWidth = this.mSelectorWheelPaint.measureText(this.mDisplayedMaxText);
        initializeSelectorWheel();
        invalidate();
    }

    public void setTextSizeHint(int i2) {
        this.mTextSizeHint = i2;
        invalidate();
    }

    public void setTextSizeTrimFactor(float f2) {
        if (f2 <= 0.0f || f2 >= 1.0f) {
            return;
        }
        this.mTextSizeTrimFactor = f2;
    }

    public void setValue(int i2) {
        setValueInternal(i2, false);
    }

    public void setWrapSelectorWheel(boolean z2) {
        boolean z3 = this.mMaxValue - this.mMinValue >= this.mSelectorIndices.length;
        if ((!z2 || z3) && z2 != this.mWrapSelectorWheel) {
            this.mWrapSelectorWheel = z2;
        }
        refreshWheel();
    }

    public static class NumberFormatter implements Formatter {
        private final int iWidth;

        public NumberFormatter() {
            this.iWidth = -1;
        }

        @Override // miuix.pickerwidget.widget.NumberPicker.Formatter
        public String format(int i2) {
            return SimpleNumberFormatter.format(this.iWidth, i2);
        }

        public NumberFormatter(int i2) {
            this.iWidth = i2;
        }
    }

    public NumberPicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.numberPickerStyle);
    }

    public NumberPicker(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mId = sIdGenerator.incrementAndGet();
        this.MARGIN_LABEL_LEFT = 1;
        this.MARGIN_LABEL_TOP = 2;
        this.mMaxWidth = SearchActionModeView.ANIMATION_DURATION;
        this.mLongPressUpdateInterval = 300L;
        this.mSelectorIndexToStringCache = new SparseArray<>();
        this.mSelectorIndices = new int[3];
        this.mInitialScrollOffset = Integer.MIN_VALUE;
        this.mScrollState = 0;
        this.mLastHandledDownDpadKeyCode = -1;
        this.mTextSizeTrimFactor = 0.95f;
        this.mLabelTextSizeTrimFactor = 0.8f;
        this.mMaxFlingSpeedFactor = 1.0f;
        this.mMeasureBackgroundEnabled = true;
        float f2 = getResources().getDisplayMetrics().density;
        this.MARGIN_LABEL_LEFT = getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_number_picker_label_margin_left);
        this.MARGIN_LABEL_TOP = getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_number_picker_label_margin_top);
        parseStyle(attributeSet, i2);
        initSoundPlayer();
        this.mHasSelectorWheel = true;
        this.mSelectionDividerHeight = (int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics());
        this.mSelectionDividersDistance = (int) (45.0f * f2);
        this.mMinHeight = -1;
        this.mMaxHeight = (int) (f2 * 202.0f);
        this.mMinWidth = -1;
        this.mMaxWidth = -1;
        this.mComputeMaxWidth = true;
        this.mPressedStateHelper = new PressedStateHelper();
        setWillNotDraw(!true);
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.miuix_appcompat_number_picker_layout, (ViewGroup) this, true);
        EditText editText = (EditText) findViewById(R.id.number_picker_input);
        this.mInputText = editText;
        initInputText();
        initThreshHolds();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity() / 8;
        this.mTextSize = (int) editText.getTextSize();
        this.mSelectorWheelPaint = initSelectorWheelPaint();
        initLabelPaint();
        this.mFlingScroller = new Scroller(getContext(), null, true);
        this.mAdjustScroller = new Scroller(getContext(), new DecelerateInterpolator(2.5f));
        updateInputTextView();
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }
}
