package com.miniproject.productifylife;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    Button logoutBtn;
    public static String routineDefaultCoinsVal;
    public static String todoDefaultCoinsVal;
    public static String bonusRoutineDefaultCoinsVal;
    public static String bonusTodoDefaultCoinsVal;
    int notifyHour;
    int notifyMinute;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RadioGroup themeRadioGroup;
    RadioButton lightThemeRadioBtn;
    RadioButton darkThemeRadioBtn;
    RadioButton autoThemeRadioBtn;
    SwitchMaterial notifySwitch;
    TextView notifyMeAtTextView;
    TextView notifyMeAtTime;
    TextView themeHead;
    EditText routineDefaultCoins;
    EditText todoDefaultCoins;
    EditText bonusRoutineDefaultCoins;
    EditText bonusTodoDefaultCoins;
    FirebaseFirestore db;
    String userEmail;

    Boolean showNotification = false;

    public SettingsFragment() {
        // Required empty public constructor
    }
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        logoutBtn = getView().findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(), NavigateAuthMainScreen.class);
                startActivity(intent);
                Toast.makeText(getContext(), "Successfully!! Sign out", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        themeRadioGroup = view.findViewById(R.id.themeRadioGroup);
        lightThemeRadioBtn = view.findViewById(R.id.lightThemeRadioBtn);
        darkThemeRadioBtn = view.findViewById(R.id.darkThemeRadioBtn);
        autoThemeRadioBtn = view.findViewById(R.id.autoThemeRadioBtn);
        notifySwitch = view.findViewById(R.id.notifySwitch);
        notifyMeAtTextView = view.findViewById(R.id.totalEarnedHead);
        notifyMeAtTime= view.findViewById(R.id.notifyMeAtTime);
        themeHead= view.findViewById(R.id.themeHead);
        routineDefaultCoins= view.findViewById(R.id.routine_default_coins);
        todoDefaultCoins= view.findViewById(R.id.todo_default_coins);
        bonusRoutineDefaultCoins= view.findViewById(R.id.bonus_routine_default_coins);
        bonusTodoDefaultCoins= view.findViewById(R.id.bonus_todo_default_coins);

        userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        db = FirebaseFirestore.getInstance();
        assert userEmail != null;
        DocumentReference dr =  db.collection("userSettings").document(userEmail);
        dr.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                assert value != null;
                notifySwitch.setChecked(value.getBoolean("displayNotification"));
                int hour = Integer.parseInt(value.getString("hour"));
                int minute = Integer.parseInt(value.getString("minute"));
                notifyHour = hour;
                notifyMinute = minute;
                String time = "";
                if(hour > 12)
                    time =  String.format("%02d", (hour -12)) + ":" + String.format("%02d", minute) + " pm";
                else
                    time =  String.format("%02d", hour) + ":" + String.format("%02d", minute) + " am";
                notifyMeAtTime.setText(time);
                routineDefaultCoinsVal = value.getString("rewardRoutineCoins");
                todoDefaultCoinsVal = value.getString("rewardTodoCoins");
                bonusRoutineDefaultCoinsVal = value.getString("bonusRewardRoutineCoins");
                bonusTodoDefaultCoinsVal = value.getString("bonusRewardTodoCoins");
                routineDefaultCoins.setText(routineDefaultCoinsVal);
                todoDefaultCoins.setText(todoDefaultCoinsVal);
                bonusRoutineDefaultCoins.setText(bonusRoutineDefaultCoinsVal);
                bonusTodoDefaultCoins.setText(bonusTodoDefaultCoinsVal);
            }
        });

        routineDefaultCoins.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String val = routineDefaultCoins.getText().toString();
                if(!hasFocus && !routineDefaultCoinsVal.equals(val))
                    db.collection("userSettings").document(userEmail).update("rewardRoutineCoins", val);
            }
        });
        todoDefaultCoins.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String val = todoDefaultCoins.getText().toString();
                if(!hasFocus && !todoDefaultCoinsVal.equals(val))
                    db.collection("userSettings").document(userEmail).update("rewardTodoCoins", val);
            }
        });
        bonusRoutineDefaultCoins.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String val = bonusRoutineDefaultCoins.getText().toString();
                if(!hasFocus && !bonusRoutineDefaultCoinsVal.equals(val))
                    db.collection("userSettings").document(userEmail).update("bonusRewardRoutineCoins", val);
            }
        });
        bonusTodoDefaultCoins.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String val = bonusTodoDefaultCoins.getText().toString();
                if(!hasFocus && !bonusTodoDefaultCoinsVal.equals(val))
                    db.collection("userSettings").document(userEmail).update("bonusRewardTodoCoins", val);
            }
        });

        themeRadioGroup.setVisibility(View.GONE);
        themeHead.setVisibility(View.GONE);

        lightThemeRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
        darkThemeRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });
        autoThemeRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            }
        });

        notifySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showNotification = b;
                if(showNotification)
                    enableNotifications();
                else
                    disableNotifications();
            }
        });

        notifyMeAtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                        .setTitleText("Set Time For Notification")
                        .setHour(notifyHour)
                        .setMinute(notifyMinute)
                        .build();
                timePicker.show(getActivity().getSupportFragmentManager(),"notifyChannelId");

                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int hour = timePicker.getHour();
                        int minute = timePicker.getMinute();
                        String time = "";
                        if(hour > 12)
                            time =  String.format("%02d", (hour -12)) + ":" + String.format("%02d", minute) + " pm";
                        else
                            time =  String.format("%02d", hour) + ":" + String.format("%02d", minute) + " am";
                        notifyMeAtTime.setText(time);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);

                        AlarmManager alarmManager;
                        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                        Intent notifyIntent;
                        notifyIntent = new Intent(getContext(), DisplayNotification.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, notifyIntent,0);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);

                        db.collection("userSettings").document(userEmail).update("hour", String.valueOf(hour));
                        db.collection("userSettings").document(userEmail).update("minute", String.valueOf(minute));
                    }
                });
            }
        });

        return view;
    }

    void disableNotifications(){
        notifyMeAtTextView.setVisibility(View.GONE);
        notifyMeAtTime.setVisibility(View.GONE);
        db.collection("userSettings").document(userEmail).update("displayNotification", false);
    }
    void enableNotifications(){
        notifyMeAtTextView.setVisibility(View.VISIBLE);
        notifyMeAtTime.setVisibility(View.VISIBLE);
        db.collection("userSettings").document(userEmail).update("displayNotification", true);

    }

}