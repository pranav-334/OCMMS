package com.example.ocmms;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import org.w3c.dom.Document;

import java.util.ArrayList;
public class Data_Model_Notifications {


    public String title;
    public String postedBy;
    public String description;
    public String created_at;
    public String complaint_id;
    public String notification_id;
    public int is_seen;




    public Data_Model_Notifications(String title, String postedBy,String description) {
        this.title = title;
        this.postedBy = postedBy;
        this.description = description;

    }



    public Data_Model_Notifications(JSONObject object) {


        try {
            String created = object.getString("created_at");
            String description = object.getString("description");
            String complaint_id = object.getString("complaint_id");
            String notification_id = object.getString("id");
            String title = object.getString("title");
            int is_seen = object.getInt("is_seen");
            Document doc = (Document) Jsoup.parse(description);

            // changed from org.jsoup.select.Elements links = doc.select("a"); to->
            org.jsoup.select.Elements links = (Elements) doc.getElementsByTagName("a");

            String name = links.get(0).text();
            this.postedBy = name;

            // this.description = doc.text();
            this.description = doc.getTextContent();

            this.created_at = created;
            this.complaint_id = complaint_id;
            this.notification_id = notification_id;
            this.title = title;
            this.is_seen = is_seen;
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public static ArrayList<Data_Model_Notifications> fromJson(JSONArray jsonObjects) {

        ArrayList<Data_Model_Notifications> gradesData = new ArrayList<>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                gradesData.add(new Data_Model_Notifications(jsonObjects.getJSONObject(jsonObjects.length()-i-1)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return gradesData;
    }
}
