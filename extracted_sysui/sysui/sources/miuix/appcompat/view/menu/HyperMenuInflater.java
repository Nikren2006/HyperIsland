package miuix.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.widget.TintTypedArray;
import java.io.IOException;
import miuix.appcompat.R;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes3.dex */
public class HyperMenuInflater extends SupportMenuInflater {
    public static final String DEBUG_TAG = "HyperMenuInflater";
    private static final String XML_GROUP = "group";
    private static final String XML_ITEM = "item";
    private static final String XML_MENU = "menu";
    Context mContext;

    public HyperMenuInflater(Context context) {
        super(context);
        this.mContext = context;
    }

    private void appendExtraDataToGroup(AttributeSet attributeSet, Menu menu, int i2) {
        TintTypedArray tintTypedArrayObtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.mContext, attributeSet, R.styleable.HyperMenuItem);
        int i3 = tintTypedArrayObtainStyledAttributes.getInt(R.styleable.HyperMenuItem_hyperMenuGroupForeignKey, -1);
        tintTypedArrayObtainStyledAttributes.recycle();
        if (i3 != -1) {
            MenuItem item = menu.getItem(i2);
            Intent intent = item.getIntent();
            if (intent == null) {
                intent = new Intent();
            }
            intent.putExtra(HyperMenuContract.HYPER_MENU_GROUP_FOREIGN_KEY, i3);
            item.setIntent(intent);
        }
    }

    private void appendExtraDataToItem(AttributeSet attributeSet, Menu menu, int i2) {
        TintTypedArray tintTypedArrayObtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.mContext, attributeSet, R.styleable.HyperMenuItem);
        int i3 = tintTypedArrayObtainStyledAttributes.getInt(R.styleable.HyperMenuItem_hyperMenuItemGroupId, -1);
        int i4 = tintTypedArrayObtainStyledAttributes.getInt(R.styleable.HyperMenuItem_hyperMenuItemForeignKey, -1);
        tintTypedArrayObtainStyledAttributes.recycle();
        MenuItem item = menu.getItem(i2);
        if (i3 != -1) {
            Intent intent = item.getIntent();
            if (intent == null) {
                intent = new Intent();
            }
            intent.putExtra(HyperMenuContract.HYPER_MENU_GROUP_ID, i3);
            item.setIntent(intent);
        }
        if (i4 != -1) {
            Intent intent2 = item.getIntent();
            if (intent2 == null) {
                intent2 = new Intent();
            }
            intent2.putExtra(HyperMenuContract.HYPER_MENU_ITEM_FOREIGN_KEY, i4);
            item.setIntent(intent2);
        }
    }

    private void parseHyperGroupMenu(XmlResourceParser xmlResourceParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
        int eventType = xmlResourceParser.getEventType();
        while (true) {
            if (eventType == 2) {
                String name = xmlResourceParser.getName();
                if (!name.equals(XML_MENU)) {
                    throw new RuntimeException("Expecting menu, got " + name);
                }
                eventType = xmlResourceParser.next();
            } else {
                eventType = xmlResourceParser.next();
                if (eventType == 1) {
                    break;
                }
            }
        }
        boolean z2 = false;
        boolean z3 = false;
        int i2 = 0;
        String str = null;
        while (!z2) {
            if (eventType == 1) {
                throw new RuntimeException("Unexpected end of document");
            }
            if (eventType != 2) {
                if (eventType == 3) {
                    String name2 = xmlResourceParser.getName();
                    if (z3 && name2.equals(str)) {
                        z3 = false;
                        str = null;
                    } else if (!name2.equals(XML_GROUP)) {
                        if (name2.equals("item")) {
                            i2++;
                        } else if (name2.equals(XML_MENU)) {
                            z2 = true;
                        }
                    }
                }
            } else if (!z3) {
                String name3 = xmlResourceParser.getName();
                if (name3.equals(XML_GROUP)) {
                    appendExtraDataToGroup(attributeSet, menu, i2);
                } else if (name3.equals("item")) {
                    appendExtraDataToItem(attributeSet, menu, i2);
                } else if (name3.equals(XML_MENU)) {
                    i2 = 0;
                } else {
                    str = name3;
                    z3 = true;
                }
            }
            eventType = xmlResourceParser.next();
        }
    }

    @Override // androidx.appcompat.view.SupportMenuInflater, android.view.MenuInflater
    public void inflate(int i2, Menu menu) {
        super.inflate(i2, menu);
        XmlResourceParser layout = null;
        try {
            try {
                layout = this.mContext.getResources().getLayout(i2);
                parseHyperGroupMenu(layout, Xml.asAttributeSet(layout), menu);
            } catch (IOException e2) {
                throw new InflateException("Error inflating menu XML", e2);
            } catch (XmlPullParserException e3) {
                throw new InflateException("Error inflating menu XML", e3);
            }
        } finally {
            if (layout != null) {
                layout.close();
            }
        }
    }
}
