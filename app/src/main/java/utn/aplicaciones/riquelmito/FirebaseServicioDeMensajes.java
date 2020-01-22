package utn.aplicaciones.riquelmito;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseServicioDeMensajes extends FirebaseMessagingService {

    public static int NOTIFICATION_ID = 1;
    public static final String TAG = "MyTag";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        generateNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "onMessageReceived: called");
        Log.d(TAG, "onMessageReceived: Mensaje recibido de "+remoteMessage.getFrom());

        if(remoteMessage.getNotification() != null){
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();

            //TODO ver si se cambia esto de FCM_CHANNEL_ID, 14:20 de https://www.youtube.com/watch?v=Fb9y06G6MWg
            Notification notification = new NotificationCompat.Builder(this, "FCM_CHANNEL_ID")
                    .setSmallIcon(R.mipmap.app_logo)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setColor(Color.GRAY)
                    .build();

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(1002, notification);
        }

        if(remoteMessage.getData().size() > 0){
            Log.d(TAG, "onMessageReceived: Data "+ remoteMessage.getData().toString());
        }

    }

/*    private void generateNotification(String body, String title){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent prndingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.app_logo)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(prndingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(NOTIFICATION_ID > 2147483646) {
            NOTIFICATION_ID = 0;
        }

        notificationManager.notify(NOTIFICATION_ID++, notificationBuilder.build());

    }
 */

/*    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);
    }
*/

    @Override
    public void onDeletedMessages(){
        super.onDeletedMessages();
        Log.d(TAG, "onDeletedMessages: Called");
    }

    @Override
    public void onNewToken(@NonNull String s){
        super.onNewToken(s);
        Log.d(TAG, "onNewToken: Called");
    }

    public void suscribirATopico(String topico){
/*        FirebaseMessaging.getInstance().subscribeToTopic(topico)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful())
                        Toast.makeText(this, "Suscrito a tópico", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(this, "Suscripción fallida", Toast.LENGTH_SHORT).show();
                });
 */

        FirebaseMessaging.getInstance().subscribeToTopic(topico)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Suscrito a tópico", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Suscripción fallida", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void desuscribirATopico(String topico){

        FirebaseMessaging.getInstance().unsubscribeFromTopic(topico)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Desuscrito a tópico", Toast.LENGTH_SHORT).show();;
                            //Toast.makeText(this, "Desuscrito a tópico", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Desuscripción fallida", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
