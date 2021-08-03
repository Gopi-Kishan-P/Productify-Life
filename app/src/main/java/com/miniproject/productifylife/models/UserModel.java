package com.miniproject.productifylife.models;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class UserModel {
    public String id;
    public String name;
    public String email;
    public String img;

    public UserModel(String id, String name, String email, String img) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.img = img;
    }

    public static UserModel fromFirestore(DocumentSnapshot doc) {
        Map<String, Object> data = doc.getData();
        assert data != null;
        return new UserModel(doc.getId(), doc.get("name").toString(), doc.get("email").toString(), doc.get("img").toString());

    }

    public Map<String, Object> getMap() {
        Map<String, Object> mapData = new HashMap<>();

        mapData.put("name", this.name);
        mapData.put("email", this.email);
        mapData.put("img", this.img);
        return mapData;
    }

}
