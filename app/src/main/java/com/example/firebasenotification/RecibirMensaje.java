package com.example.firebasenotification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

public class RecibirMensaje extends FirebaseMessagingService{

    public RecibirMensaje() {

    }

    String TAG = "Notificacion";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());


            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.

            } else {
                // Handle message within 10 seconds

            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Título de la Notificación: " + remoteMessage.getNotification().getTitle());
            Log.d(TAG, "Mensaje de la Notificación: " + remoteMessage.getNotification().getBody());



            Map<String, Object> notificaciones = new HashMap<>();
            notificaciones.put("titulo",remoteMessage.getNotification().getTitle());
            notificaciones.put("mensaje", remoteMessage.getNotification().getBody());

            FirebaseApp.initializeApp(this);
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference();

            databaseReference.child("Notificaciones").child(remoteMessage.getFrom()).updateChildren(notificaciones) ;

        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
}
