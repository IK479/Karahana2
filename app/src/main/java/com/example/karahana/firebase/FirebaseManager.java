package com.example.karahana.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.karahana.managers.Models.PartyCard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener; 

import java.util.ArrayList;
import java.util.Date;

public class FirebaseManager {
    private static final String TAG = "FireBaseManager";
    private static FirebaseManager instance;
    public static FirebaseManager getInstance() {
        if(instance == null){
            instance = new FirebaseManager();
        }
        return instance;
    }

    FirebaseDatabase database;

    DatabaseReference usersReference;
    DatabaseReference partiesReference;
    DatabaseReference gameReference;
    DatabaseReference registrationReference;

    private FirebaseManager(){
        database = FirebaseDatabase.getInstance();
        gameReference = database.getReference("game");
        usersReference= database.getReference("users");
        partiesReference= database.getReference("parties");
        registrationReference= database.getReference("registrationUsers");
    }

    // region party
    //
    //

    public void getParties(ActionListener listener) {
        partiesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<PartyCard> list=new ArrayList<>();

                for(DataSnapshot child : snapshot.getChildren()){
                    PartyCard party = child.getValue(PartyCard.class);
                    if(party !=null && !party.deleted) {
                        list.add(party);
                    }
                }

                listener.onResult(ActionResult.toSuccess(list));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onResult(getErrorResult(error));
            }
        });
    }

    public void registerToPartyUpdate(ActionListener listener) {
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                onUpdate(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());
                onUpdate(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());
                onUpdate(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
            }

            private void onUpdate(DataSnapshot dataSnapshot){
                PartyCard party = dataSnapshot.getValue(PartyCard.class);
                if(listener!= null){
                    listener.onResult(ActionResult.toSuccess(party));
                }
            }
        };
        partiesReference.addChildEventListener(childEventListener);
    }

    public void loadParties(ActionListener listener){
        partiesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<PartyCard> parties =new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    PartyCard u=postSnapshot.getValue(PartyCard.class);
                    if(u!=null && !u.deleted) {
                        parties.add(u);
                    }
                }

                PartyCard[] arr=new PartyCard[parties.size()];
                arr=parties .toArray(arr);
                listener.onResult(ActionResult.toSuccess(arr));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                listener.onResult(getErrorResult(error));
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    public void updateParty(PartyCard party,ActionListener listener) {
        partiesReference.child(party.uid).setValue(party).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                listener.onResult(ActionResult.toSuccess(party));
            }else{
                listener.onResult(getErrorResult(task));
            }
        });
    }

    public void deleteParty(PartyCard party, ActionListener listener) {
        party.deleted = true;
        updateParty(party,listener);
    }

    public void addParty(PartyCard party,ActionListener listener) {
        DatabaseReference ref= partiesReference.push();
        party.uid = ref.getKey();
        ref.setValue(party).addOnCompleteListener(task->{
            if(task.isSuccessful()){
                listener.onResult(ActionResult.toSuccess(party));
            }else{
                listener.onResult(getErrorResult(task));
            }
        });
    }
    // endregion

    private ActionResult getErrorResult(DatabaseError error) {
        return ActionResult.toError(error.toException().toString());
    }

    private ActionResult getErrorResult(Task<Void> task) {
        return ActionResult.toError(getErrorMessage(task));
    }

    private String getErrorMessage(Task<Void> task) {
        return "Fire base action failed\n\n" + task.getException().getMessage();
    }
}


