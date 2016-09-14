package thebird.com.memory.additional_classes;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import thebird.com.memory.R;

/**
 * Created by Chivoin Long on 11-Sep-16.
 */
public class BackgroundMusicService extends Service {

    MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.background_music);
        player.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return 1;
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

}
