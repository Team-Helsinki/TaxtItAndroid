package startng.task.taxit;


import android.graphics.PixelFormat;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.constraint.ConstraintLayout;


public class Startupscreen extends Activity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    // Called when the activity is first created
    Thread splashTread;
    ImageView imageView;
    TextView textView;
    Animation frombottom, fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startupscreen);
        imageView = findViewById(R.id.splash);
        textView = findViewById(R.id.textIntro);
        StartAnimations();
    }
    private void StartAnimations() {
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        ConstraintLayout c = findViewById(R.id.con_lay);
        anim.reset();

        c.clearAnimation();
        c.startAnimation(anim);

        imageView.setAnimation(fromtop);
        textView.setAnimation(frombottom);
        anim.reset();
        //imageView.clearAnimation();
        //textView.clearAnimation();

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(Startupscreen.this,
                            HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    Startupscreen.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Startupscreen.this.finish();
                }

            }
        };
        splashTread.start();
    }

}