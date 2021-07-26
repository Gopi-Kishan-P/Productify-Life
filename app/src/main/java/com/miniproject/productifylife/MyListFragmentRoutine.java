package com.miniproject.productifylife;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.app.FragmentManager;

import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.miniproject.productifylife.models.RoutineModel;
import com.miniproject.productifylife.models.TodoModel;
import com.miniproject.productifylife.services.dbServices;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MyListFragmentRoutine extends ListFragment implements OnItemClickListener {
    private static View view;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_list, container, false);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList data=new ArrayList<String>();
        data.add("do exercies");
        data.add("do hw");
        ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,data);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

        dbServices.fetchUserIncompleteRoutines()
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    String TAG="stream firestore*******************";


                    @Override
                    public void onEvent(QuerySnapshot value,
                                        FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }
                        data.clear();

                        List<String> cities = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : value) {
                            DocumentSnapshot document=doc;
                            RoutineModel routineModel=RoutineModel.fromFirestore(document);
                            data.add(routineModel.name);
                            synchronized(adapter){
                                adapter.notifyDataSetChanged();
                            }

                        }
                    }
                });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
}