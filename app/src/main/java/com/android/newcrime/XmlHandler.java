package com.android.newcrime;

import android.os.Environment;
import android.util.Log;
import android.util.Xml;

import com.android.newcrime.utils.DateTimePicker;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zwb on 2017/3/8.
 */

public class XmlHandler {

    public void createCaseInfoXmlFile(Object[] obj) {
        XmlSerializer xmlSerializer;
        FileOutputStream fileOutputStream = null;

        try {
            xmlSerializer = Xml.newSerializer();
            File dir = new File(Environment.getExternalStorageDirectory()
                    + "/CaseReport/"+ DateTimePicker.getCurrentDashDate(Calendar.getInstance().getTimeInMillis()));
            if (!dir.exists()){
                dir.mkdirs();
            }

            File file = new File(dir, "CaseInfo.xml");

            fileOutputStream = new FileOutputStream(file);

            String encoding = "utf-8";
            xmlSerializer.setOutput(fileOutputStream, encoding);
            xmlSerializer.startDocument(encoding, null);

            xmlSerializer.startTag(null, "datas");

            for (int i=0; i < obj.length; i++) {
                createCaseInfo(xmlSerializer,
                        (List<HashMap<String, String>>) obj[i], "caseinfo");
            }

            xmlSerializer.endTag(null, "datas");
            xmlSerializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void createCaseInfo(XmlSerializer xmlSerializer, List<HashMap<String, String>> info, String tagstring) {

        if(info.size() < 0) {
            return;
        }
        try {
            xmlSerializer.startTag(null, tagstring+"s");
            for (int i = 0; i < info.size(); i++) {
                HashMap<String, String> map = info.get(i);
                xmlSerializer.startTag(null, tagstring);
                for (HashMap.Entry<String, String> entry : map.entrySet()) {
                    xmlSerializer.startTag(null, entry.getKey());
                    xmlSerializer.text(entry.getValue());
                    xmlSerializer.endTag(null, entry.getKey());
                }
                xmlSerializer.endTag(null, tagstring);
            }
            xmlSerializer.endTag(null, tagstring+"s");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
