package com.miniproject.productifylife.models;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class UserSettingsModel {
    public String id;
    public boolean displayNotification;
    public String hour;
    public String minute;
    public String rewardRoutineCoins;
    public String rewardTodoCoins;
    public String bonusRewardRoutineCoins;
    public String bonusRewardTodoCoins;
    public String theme; //l-Light d-Dark sa-System(Auto)

    public UserSettingsModel(String id, boolean displayNotification, String hour,String minute, String rewardRoutineCoins, String rewardTodoCoins, String bonusRewardRoutineCoins, String bonusRewardTodoCoins, String theme) {
        this.id = id;
        this.displayNotification = displayNotification;
        this.hour = hour;
        this.minute = minute;
        this.rewardRoutineCoins = rewardRoutineCoins;
        this.rewardTodoCoins = rewardTodoCoins;
        this.bonusRewardRoutineCoins = bonusRewardRoutineCoins;
        this.bonusRewardTodoCoins = bonusRewardTodoCoins;
        this.theme = theme;
    }

    public static UserSettingsModel fromFirestore(DocumentSnapshot doc) {
        Map<String, Object> data = doc.getData();
        assert data != null;
        return new UserSettingsModel(doc.getId(), doc.getBoolean("displayNotification"), doc.getString("hour"), doc.getString("minute"), doc.getString("rewardRoutineCoins"),doc.getString("rewardTodoCoins"), doc.getString("bonusRewardRoutineCoins"), doc.getString("bonusRewardTodoCoins"),doc.getString("theme"));

    }

    public Map<String, Object> getMap() {
        Map<String, Object> mapData = new HashMap<>();

        mapData.put("displayNotification", this.displayNotification);
        mapData.put("hour", this.hour);
        mapData.put("minute", this.minute);
        mapData.put("rewardRoutineCoins", this.rewardRoutineCoins);
        mapData.put("rewardTodoCoins", this.rewardTodoCoins);
        mapData.put("bonusRewardRoutineCoins", this.bonusRewardRoutineCoins);
        mapData.put("bonusRewardTodoCoins", this.bonusRewardTodoCoins);
        mapData.put("theme", this.theme);
        return mapData;
    }

}
