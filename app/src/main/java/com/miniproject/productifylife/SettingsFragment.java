package com.miniproject.productifylife;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    Button logoutBtn;

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
                Intent logout = new Intent(getContext(), AuthActivity.class);
                startActivity(logout);
                Toast.makeText(getContext(), "Successfully!! Sign out", Toast.LENGTH_SHORT).show();
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
        notifyMeAtTextView = view.findViewById(R.id.notifyMeAtTextView);
        notifyMeAtTime= view.findViewById(R.id.notifyMeAtTime);

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

                        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                        Intent intent = new Intent(getContext(), DisplayNotification.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent,0);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);
                    }
                });
            }
        });

        return view;
    }

    void disableNotifications(){
        notifyMeAtTextView.setVisibility(View.GONE);
        notifyMeAtTime.setVisibility(View.GONE);
    }
    void enableNotifications(){
        notifyMeAtTextView.setVisibility(View.VISIBLE);
        notifyMeAtTime.setVisibility(View.VISIBLE);


    }

}