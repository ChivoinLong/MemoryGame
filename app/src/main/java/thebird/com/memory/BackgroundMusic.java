package thebird.com.memory;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Chivoin Long on 11-Sep-16.
 */
public class BackgroundMusic extends Service{

//    private static Context mContext;
//    @Override
//    protected Void doInBackground(Void... params) {
//        MediaPlayer player = MediaPlayer.create(getContext(), R.raw.background_music);
//        player.setLooping(true); // Set looping
//        player.setVolume(100,100);
//        player.start();
//        return null;
//    }
//
//    public BackgroundMusic(Context context) {
//        mContext = context;
//    }
//
//    public Context getContext() {
//        return mContext;
//    }

    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100,100);
        mediaPlayer.start();
        Toast.makeText(getBaseContext(), "Play", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        Toast.makeText(getBaseContext(), "Stop", Toast.LENGTH_LONG).show();
    }
}
