package com.miniproject.productifylife.models;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class TodoModel {
    public String id;
    public String name;
    public String img;
    public String userId;
    public String priority;
    public String coins;
    public Timestamp timestamp;
    public boolean completed;

    public TodoModel(String id, String name, String img, String userId, String priority, String coins,Timestamp timestamp,boolean completed) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.userId = userId;
        this.priority = priority;
        this.coins = coins;
        this.timestamp = timestamp;
        this.completed=completed;
    }

    public static TodoModel fromFirestore(DocumentSnapshot doc) {
        Map<String, Object> data = doc.getData();
        assert data != null;
        return new TodoModel(doc.getId(), doc.get("name").toString(), doc.get("img").toString(), doc.getString("userId"), doc.getString("priority"), doc.getString("coins"),doc.getTimestamp("timestamp"),doc.getBoolean("completed"));

    }

    public Map<String, Object> getMap() {
        Map<String, Object> mapData = new HashMap<>();

        mapData.put("name", this.name);
        mapData.put("img", this.img);
        mapData.put("userId", this.userId);
        mapData.put("priority", this.priority);
        mapData.put("coins", this.coins);
        mapData.put("timestamp",this.timestamp);
        mapData.put("completed",this.completed);
        return mapData;
    }
}
