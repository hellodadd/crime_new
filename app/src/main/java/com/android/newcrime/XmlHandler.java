package com.android.newcrime;

import android.os.Environment;
import android.util.Log;
import android.util.Xml;

import com.android.newcrime.utils.CommonConst;
import com.android.newcrime.utils.DateTimePicker;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zwb on 2017/3/8.
 */

public class XmlHandler {

    public void createDeviceMsg(String deviceid, String initstatus, String swversion, String mapversion ) {
        List<Device> devices = new ArrayList<Device>();
        Device device = new Device();

        device.setDeviceId(deviceid);
        device.setInitStatus(initstatus);
        device.setSwVersion(swversion);
        device.setMapVersion(mapversion);
        devices.add(device);
        createDeviceMsgXmlFile(devices);
    }

    private void createDeviceMsgXmlFile(Object obj) {
        XmlSerializer xmlSerializer = null;
        FileOutputStream fileOutputStream = null;
        String enter = System.getProperty("line.separator");
        List<Device> devices = (List<Device>)obj;
        //List<Dictionary> dictionaryList = createData();
        //List<Dictionary> dictionaryList = createData();

        try {
            //获取xmlSerializer
            xmlSerializer = Xml.newSerializer();
            File file = new File(Environment.getExternalStorageDirectory(), "DeviceMsg.xml");
            fileOutputStream = new FileOutputStream(file);

            String encoding = "utf-8";
            xmlSerializer.setOutput(fileOutputStream, encoding);
            xmlSerializer.startDocument(encoding, null);
            changeLine(xmlSerializer, enter);
            //根节点开始

            xmlSerializer.startTag(null, "device");
            changeLine(xmlSerializer, enter);

            //内容结点
            for(Device d: devices) {

                xmlSerializer.startTag(null, d.getDeviceId()[0].trim());
                xmlSerializer.text(d.getDeviceId()[1].trim());
                xmlSerializer.endTag(null, d.getDeviceId()[0].trim());
                changeLine(xmlSerializer, enter);

                xmlSerializer.startTag(null, d.getInitStatus()[0].trim());
                xmlSerializer.text(d.getInitStatus()[1].trim());
                xmlSerializer.endTag(null, d.getInitStatus()[0].trim());
                changeLine(xmlSerializer, enter);

                xmlSerializer.startTag(null, d.getSwVersion()[0].trim());
                xmlSerializer.text(d.getSwVersion()[1]);
                xmlSerializer.endTag(null, d.getSwVersion()[0].trim());
                changeLine(xmlSerializer, enter);

                xmlSerializer.startTag(null, d.getMapVersion()[0].trim());
                xmlSerializer.text(d.getMapVersion()[1].trim());
                xmlSerializer.endTag(null, d.getMapVersion()[0].trim());
                changeLine(xmlSerializer, enter);

            }

            //根节点结束
            xmlSerializer.endTag(null, "device");
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

    public static void changeLine(XmlSerializer serializer, String enter){
        try{
            serializer.text(enter);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void createCaseInfoXmlFile(Object[] obj) {
        XmlSerializer xmlSerializer;
        FileOutputStream fileOutputStream = null;

        try {
            xmlSerializer = Xml.newSerializer();
            File file = new File(Environment.getExternalStorageDirectory(), CommonConst.CASE_INFO_XML);
            fileOutputStream = new FileOutputStream(file);
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
            xmlSerializer.startTag(null, tagstring.trim()+"s");
            for (int i = 0; i < info.size(); i++) {
                HashMap<String, String> map = info.get(i);
                xmlSerializer.startTag(null, tagstring.trim());
                for (HashMap.Entry<String, String> entry : map.entrySet()) {
                    xmlSerializer.startTag(null, entry.getKey().trim());
                    xmlSerializer.text(entry.getValue().trim());
                    xmlSerializer.endTag(null, entry.getKey().trim());
                }
                xmlSerializer.endTag(null, tagstring.trim());
            }
            xmlSerializer.endTag(null, tagstring.trim()+"s");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> deleteSceneInfoCmd() {
        List<String> sceneId = new ArrayList<String>();
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "deleteCaseInfoCmd.xml");
            FileInputStream fileInputStream = new FileInputStream(file);
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(fileInputStream, "utf-8");
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("localeid")) {
                            eventType = parser.next();
                            sceneId.add(parser.getText().trim());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                eventType = parser.next();
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sceneId;
    }

    public static void createSuccessDeleteMsgFile(Object obj) {
        XmlSerializer xmlSerializer = null;
        FileOutputStream fileOutputStream = null;
        String enter = System.getProperty("line.separator");
        List<String> result = (List<String>) obj;

        try {
            //获取xmlSerializer
            xmlSerializer = Xml.newSerializer();
            File file = new File(Environment.getExternalStorageDirectory(), "SuccessToDelete.xml");
            fileOutputStream = new FileOutputStream(file);

            String encoding = "utf-8";
            xmlSerializer.setOutput(fileOutputStream, encoding);
            xmlSerializer.startDocument(encoding, null);
            changeLine(xmlSerializer, enter);
            //根节点开始

            xmlSerializer.startTag(null, "delete");
            changeLine(xmlSerializer, enter);

            //内容结点
            for (String d : result) {
                xmlSerializer.startTag(null, "localeid");
                xmlSerializer.text(d.trim());
                xmlSerializer.endTag(null, "localeid");
                changeLine(xmlSerializer, enter);
            }

            //根节点结束
            xmlSerializer.endTag(null, "delete");
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

    public String[] writeSceneIdCmd() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "writeCaseIdCmd.xml");
            FileInputStream fileInputStream = new FileInputStream(file);
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(fileInputStream, "utf-8");
            int eventType = parser.getEventType();
            String sceneId = "";
            String sceneNo = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("localeid")) {
                            eventType = parser.next();
                            sceneId = parser.getText();
                        } else if (name.equalsIgnoreCase("status")) {
                            eventType = parser.next();
                            sceneNo = parser.getText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                eventType = parser.next();
            }
            fileInputStream.close();
            return new String[]{sceneId, sceneNo};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
