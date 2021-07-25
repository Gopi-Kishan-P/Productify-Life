package com.miniproject.productifylife.services;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.miniproject.productifylife.data.GlobalData;
import com.miniproject.productifylife.models.RoutineModel;
import com.miniproject.productifylife.models.TodoModel;
import com.miniproject.productifylife.models.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class dbServices {
    public static void fetchUser(){
        Log.d("init", "*******************came in init");
//        FirebaseApp.initializeApp(this);
        CollectionReference userCollection = FirebaseFirestore.getInstance().collection("users");
//        List<FirebaseApp> firebaseApps = FirebaseApp.getApps(this);
//        Log.d("Init", "" + firebaseApps.toString());
        FirebaseAuth mAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        if (firebaseUser != null) {
            Task<DocumentSnapshot> tdoc = userCollection.document(Objects.requireNonNull(firebaseUser.getEmail())).get();
            tdoc.addOnCompleteListener(documentSnapshotTask -> {

                GlobalData.cUser = UserModel.fromFirestore(documentSnapshotTask.getResult());
//                updateUI(firebaseUser);


            });


        }

    }

    public static void createRoutine(String rName, String rCoins) {
        CollectionReference ref = FirebaseFirestore.getInstance().collection("userRoutine");

        UserModel userModel = GlobalData.cUser;
        RoutineModel routineModel = new RoutineModel((userModel.id + "_" + rName).replaceAll("\\s+", "_").toLowerCase(), rName, userModel.email, "", "0", rCoins, Timestamp.now(), false);
        ref.document(routineModel.id).set(routineModel.getMap());
        Log.d("createRoutine", "***************added routine");
    }

    public static void createTodo(String rName, String rCoins) {
        CollectionReference ref = FirebaseFirestore.getInstance().collection("userTodo");

        UserModel userModel = GlobalData.cUser;
        TodoModel todoModel = new TodoModel((userModel.id + "_" + rName).replaceAll("\\s+", "_").toLowerCase(), rName, userModel.email, "", "0", rCoins, Timestamp.now(), false);
        ref.document(todoModel.id).set(todoModel.getMap());
        Log.d("createdTodo", "***************added todo");
    }

    public static List<RoutineModel> fetchUserCompletedRoutines() {
        List<RoutineModel> completed = new ArrayList<RoutineModel>();
        QuerySnapshot documentSnapshot = FirebaseFirestore.getInstance().collection("userRoutines").whereEqualTo("completed", true).get().getResult();
        assert documentSnapshot != null;
        List<DocumentSnapshot> docs = documentSnapshot.getDocuments();
        docs.forEach((doc) -> {
            completed.add(RoutineModel.fromFirestore(doc));

        });
        return completed;
    }

    public static List<RoutineModel> fetchUserIncompleteRoutines() {
        List<RoutineModel> incomplete = new ArrayList<RoutineModel>();
        QuerySnapshot documentSnapshot = FirebaseFirestore.getInstance().collection("userRoutines").whereEqualTo("completed", false).get().getResult();
        assert documentSnapshot != null;
        List<DocumentSnapshot> docs = documentSnapshot.getDocuments();
        docs.forEach((doc) -> {
            incomplete.add(RoutineModel.fromFirestore(doc));

        });

        return incomplete;

    }
    public static List<TodoModel> fetchUserCompletedTodo() {
        List<TodoModel> completed = new ArrayList<TodoModel>();
        QuerySnapshot documentSnapshot = FirebaseFirestore.getInstance().collection("userTodos").whereEqualTo("completed", true).get().getResult();
        assert documentSnapshot != null;
        List<DocumentSnapshot> docs = documentSnapshot.getDocuments();
        docs.forEach((doc) -> {
            completed.add(TodoModel.fromFirestore(doc));

        });
        return completed;
    }

    public static List<TodoModel> fetchUserIncompleteTodo() {
        List<TodoModel> incomplete = new ArrayList<TodoModel>();
        QuerySnapshot documentSnapshot = FirebaseFirestore.getInstance().collection("userTodos").whereEqualTo("completed", false).get().getResult();
        assert documentSnapshot != null;
        List<DocumentSnapshot> docs = documentSnapshot.getDocuments();
        docs.forEach((doc) -> {
            incomplete.add(TodoModel.fromFirestore(doc));

        });

        return incomplete;

    }

}
