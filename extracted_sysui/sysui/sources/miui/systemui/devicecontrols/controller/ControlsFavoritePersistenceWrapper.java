package miui.systemui.devicecontrols.controller;

import android.app.backup.BackupManager;
import android.content.ComponentName;
import android.util.AtomicFile;
import android.util.Log;
import android.util.Xml;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.io.IoUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsFavoritePersistenceWrapper {
    public static final String FILE_NAME = "controls_favorites.xml";
    private static final String TAG = "MiuiControlsFavoritePersistenceWrapper";
    private static final String TAG_COMPONENT = "component";
    private static final String TAG_CONTROL = "control";
    private static final String TAG_CONTROLS = "controls";
    private static final String TAG_ID = "id";
    private static final String TAG_STRUCTURE = "structure";
    private static final String TAG_STRUCTURES = "structures";
    private static final String TAG_SUBTITLE = "subtitle";
    private static final String TAG_TITLE = "title";
    private static final String TAG_TYPE = "type";
    private static final String TAG_VERSION = "version";
    private static final String TAG_ZONE = "zone";
    private static final int VERSION = 1;
    private BackupManager backupManager;
    private final Executor executor;
    private File file;
    public static final Companion Companion = new Companion(null);
    private static final Object controlsDataLock = new Object();

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Object getControlsDataLock() {
            return ControlsFavoritePersistenceWrapper.controlsDataLock;
        }

        private Companion() {
        }
    }

    public ControlsFavoritePersistenceWrapper(File file, Executor executor, BackupManager backupManager) {
        kotlin.jvm.internal.n.g(file, "file");
        kotlin.jvm.internal.n.g(executor, "executor");
        this.file = file;
        this.executor = executor;
        this.backupManager = backupManager;
    }

    private final List<StructureInfo> parseXml(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ComponentName componentNameUnflattenFromString = null;
        String str = null;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return arrayList;
            }
            String name = xmlPullParser.getName();
            if (name == null) {
                name = "";
            }
            if (next == 2 && kotlin.jvm.internal.n.c(name, TAG_STRUCTURE)) {
                componentNameUnflattenFromString = ComponentName.unflattenFromString(xmlPullParser.getAttributeValue(null, TAG_COMPONENT));
                String attributeValue = xmlPullParser.getAttributeValue(null, TAG_STRUCTURE);
                str = attributeValue != null ? attributeValue : "";
            } else if (next == 2 && kotlin.jvm.internal.n.c(name, "control")) {
                String attributeValue2 = xmlPullParser.getAttributeValue(null, "id");
                String attributeValue3 = xmlPullParser.getAttributeValue(null, "title");
                String attributeValue4 = xmlPullParser.getAttributeValue(null, "subtitle");
                String str2 = attributeValue4 == null ? "" : attributeValue4;
                String attributeValue5 = xmlPullParser.getAttributeValue(null, TAG_ZONE);
                String str3 = attributeValue5 == null ? "" : attributeValue5;
                String attributeValue6 = xmlPullParser.getAttributeValue(null, "type");
                Integer numValueOf = attributeValue6 != null ? Integer.valueOf(Integer.parseInt(attributeValue6)) : null;
                if (attributeValue2 != null && attributeValue3 != null && numValueOf != null) {
                    arrayList2.add(new ControlInfo(attributeValue2, attributeValue3, str2, str3, numValueOf.intValue()));
                }
            } else if (next == 3 && kotlin.jvm.internal.n.c(name, TAG_STRUCTURE)) {
                kotlin.jvm.internal.n.d(componentNameUnflattenFromString);
                kotlin.jvm.internal.n.d(str);
                arrayList.add(new StructureInfo(componentNameUnflattenFromString, str, I0.u.k0(arrayList2), false, 8, null));
                arrayList2.clear();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void storeFavorites$lambda$4(ControlsFavoritePersistenceWrapper this$0, List structures) {
        boolean z2;
        BackupManager backupManager;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(structures, "$structures");
        Log.d(TAG, "Saving data to file: " + this$0.file);
        AtomicFile atomicFile = new AtomicFile(this$0.file);
        synchronized (controlsDataLock) {
            try {
                FileOutputStream fileOutputStreamStartWrite = atomicFile.startWrite();
                try {
                    try {
                        XmlSerializer xmlSerializerNewSerializer = Xml.newSerializer();
                        xmlSerializerNewSerializer.setOutput(fileOutputStreamStartWrite, "utf-8");
                        z2 = true;
                        xmlSerializerNewSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
                        xmlSerializerNewSerializer.startDocument(null, Boolean.TRUE);
                        xmlSerializerNewSerializer.startTag(null, "version");
                        xmlSerializerNewSerializer.text("1");
                        xmlSerializerNewSerializer.endTag(null, "version");
                        xmlSerializerNewSerializer.startTag(null, TAG_STRUCTURES);
                        Iterator it = structures.iterator();
                        while (it.hasNext()) {
                            StructureInfo structureInfo = (StructureInfo) it.next();
                            xmlSerializerNewSerializer.startTag(null, TAG_STRUCTURE);
                            xmlSerializerNewSerializer.attribute(null, TAG_COMPONENT, structureInfo.getComponentName().flattenToString());
                            xmlSerializerNewSerializer.attribute(null, TAG_STRUCTURE, structureInfo.getStructure().toString());
                            xmlSerializerNewSerializer.startTag(null, TAG_CONTROLS);
                            for (ControlInfo controlInfo : structureInfo.getControls()) {
                                xmlSerializerNewSerializer.startTag(null, "control");
                                xmlSerializerNewSerializer.attribute(null, "id", controlInfo.getControlId());
                                xmlSerializerNewSerializer.attribute(null, "title", controlInfo.getControlTitle().toString());
                                xmlSerializerNewSerializer.attribute(null, "subtitle", controlInfo.getControlSubtitle().toString());
                                xmlSerializerNewSerializer.attribute(null, TAG_ZONE, String.valueOf(controlInfo.getZone()));
                                xmlSerializerNewSerializer.attribute(null, "type", String.valueOf(controlInfo.getDeviceType()));
                                xmlSerializerNewSerializer.endTag(null, "control");
                            }
                            xmlSerializerNewSerializer.endTag(null, TAG_CONTROLS);
                            xmlSerializerNewSerializer.endTag(null, TAG_STRUCTURE);
                        }
                        xmlSerializerNewSerializer.endTag(null, TAG_STRUCTURES);
                        xmlSerializerNewSerializer.endDocument();
                        atomicFile.finishWrite(fileOutputStreamStartWrite);
                    } catch (Throwable unused) {
                        Log.e(TAG, "Failed to write file, reverting to previous version");
                        atomicFile.failWrite(fileOutputStreamStartWrite);
                        z2 = false;
                    }
                } finally {
                    IoUtils.closeQuietly(fileOutputStreamStartWrite);
                }
            } catch (IOException e2) {
                Log.e(TAG, "Failed to start write file", e2);
                return;
            }
        }
        if (!z2 || (backupManager = this$0.backupManager) == null) {
            return;
        }
        backupManager.dataChanged();
    }

    public final void changeFileAndBackupManager(File fileName, BackupManager backupManager) {
        kotlin.jvm.internal.n.g(fileName, "fileName");
        this.file = fileName;
        this.backupManager = backupManager;
    }

    public final void deleteFile() {
        this.file.delete();
    }

    public final boolean getFileExists() {
        return this.file.exists();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.BufferedInputStream, java.io.InputStream, java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Object, org.xmlpull.v1.XmlPullParser] */
    /* JADX WARN: Type inference failed for: r5v0, types: [miui.systemui.devicecontrols.controller.ControlsFavoritePersistenceWrapper] */
    public final List<StructureInfo> readFavorites() {
        List<StructureInfo> xml;
        ?? Exists = this.file.exists();
        if (Exists == 0) {
            Log.d(TAG, "No favorites, returning empty list");
            return I0.m.h();
        }
        try {
            try {
                Exists = new BufferedInputStream(new FileInputStream(this.file));
                Log.d(TAG, "Reading data from file: " + this.file);
                synchronized (controlsDataLock) {
                    ?? NewPullParser = Xml.newPullParser();
                    NewPullParser.setInput(Exists, null);
                    kotlin.jvm.internal.n.d(NewPullParser);
                    xml = parseXml(NewPullParser);
                }
                return xml;
            } catch (FileNotFoundException unused) {
                Log.i(TAG, "No file found");
                return I0.m.h();
            }
        } catch (Exception e2) {
            Log.e(TAG, "Failed parsing favorites file: " + this.file, e2);
            return I0.m.h();
        } finally {
            IoUtils.closeQuietly((AutoCloseable) Exists);
        }
    }

    public final void storeFavorites(final List<StructureInfo> structures) {
        kotlin.jvm.internal.n.g(structures, "structures");
        if (!structures.isEmpty() || this.file.exists()) {
            this.executor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.w
                @Override // java.lang.Runnable
                public final void run() {
                    ControlsFavoritePersistenceWrapper.storeFavorites$lambda$4(this.f5595a, structures);
                }
            });
        }
    }

    public /* synthetic */ ControlsFavoritePersistenceWrapper(File file, Executor executor, BackupManager backupManager, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, executor, (i2 & 4) != 0 ? null : backupManager);
    }
}
