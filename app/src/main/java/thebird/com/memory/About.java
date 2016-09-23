package thebird.com.memory;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created by Chivoin Long on 16-Sep-16.
 */
public class About extends Activity{

    TextView advisor, advisorName, developer, chivoin, sambath;
    Animation translatebu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        advisor = (TextView) findViewById(R.id.tvAdvisor);
        advisorName = (TextView) findViewById(R.id.tvAdvisorName);
        developer = (TextView) findViewById(R.id.tvDevelopers);
        sambath = (TextView) findViewById(R.id.tvSambath);
        chivoin = (TextView) findViewById(R.id.tvChivoin);

        translatebu = AnimationUtils.loadAnimation(this, R.anim.animation);

        startAnimate();
    }

    public void startAnimate(){
        advisor.startAnimation(translatebu);
        advisorName.startAnimation(translatebu);
        developer.startAnimation(translatebu);
        sambath.startAnimation(translatebu);
        chivoin.startAnimation(translatebu);
    }

    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            this.finish();
        return true;
    }
}
