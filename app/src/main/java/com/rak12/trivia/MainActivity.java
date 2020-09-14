package com.rak12.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.rak12.trivia.data.AnswerListAsyncResponse;
import com.rak12.trivia.data.QuestionBank;
import com.rak12.trivia.model.Question;
import com.rak12.trivia.model.score;
import com.rak12.trivia.util.prefs;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String MESSAGE_ID = "MESSAGE";
    private TextView qtv;
    private TextView qc;
    private Button t;
    private Button f;
    private ImageButton n;
    private ImageButton p;
    private CardView c;
    private TextView s;
    private TextView hs;
    private int i = 0;;
    private score score;
    private prefs prefs;
    private int scorecounter=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       score=new score(scorecounter);
      prefs =new prefs(MainActivity.this);
      i= prefs.getstate();


        qtv = findViewById(R.id.q);
        qc = findViewById(R.id.c);
        t = findViewById(R.id.tb);
        f = findViewById(R.id.fb);
        n = findViewById(R.id.nb);
        s=findViewById(R.id.sc);
        hs=findViewById(R.id.high);
        p = findViewById(R.id.pb);
        t.setOnClickListener(this);
        f.setOnClickListener(this);
        n.setOnClickListener(this);
        p.setOnClickListener(this);
        s.setText("SCORE:0");
        qc.setText(String.valueOf(i)+" out of 100");
       hs.setText(MessageFormat.format("highest score: {0}", String.valueOf(prefs.gethighscore())));

    }
    final List<Question> questionList = new QuestionBank().getQuestions(new AnswerListAsyncResponse() {
        @Override
        public void processfinished(ArrayList<Question> questionArrayList) {
            qtv.setText(questionArrayList.get(i).getQ());

        }
    });
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tb:
                answerfind(true);
                update();


                break;
            case R.id.fb:
                answerfind(false);
                update();
                break;
            case R.id.nb:

                i = (i + 1);
             //   qc.setText(String.valueOf(i) + " out of 100");
            update();
                break;
            case R.id.pb:
                if (i != 0) {
                    i = i - 1;
                    update();
                }
                break;

            default:
        }
    }
    public  void answerfind(final boolean  q)
    {


boolean actualans=questionList.get(i).isAns();
if (actualans==q)
{   fade();
    addscore();
    s.setText(MessageFormat.format("SCORE : {0}", String.valueOf(score.getScore())));
    Toast.makeText(MainActivity.this, "Thats  Correct", Toast.LENGTH_SHORT).show();


}
else
{   shakeanimation();
    dedscore();
    s.setText(MessageFormat.format("SCORE : {0}", String.valueOf(score.getScore())));
    Toast.makeText(MainActivity.this, "Thats  Incorrect", Toast.LENGTH_SHORT).show();


}
            }



    private void update(){
        String q=questionList.get(i).getQ();
        qtv.setText(q);
        qc.setText(MessageFormat.format("{0} out of 100", String.valueOf(i)));

    }
    private void shakeanimation()
    {
        Animation shake= AnimationUtils.loadAnimation(this,R.anim.shake_anim);
        c=findViewById(R.id.cardView);
        c.setAnimation(shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onAnimationStart(Animation animation) {
                c.setCardBackgroundColor(Color.RED);
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onAnimationEnd(Animation animation) {
                c.setCardBackgroundColor(Color.WHITE);

                i = (i + 1);
                //   qc.setText(String.valueOf(i) + " out of 100");
                update();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public void fade(){
        AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,0.0F);
        alphaAnimation.setDuration(350);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        c=findViewById(R.id.cardView);
        c.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                c.setCardBackgroundColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                c.setCardBackgroundColor(Color.WHITE);

                i = (i + 1);
                //   qc.setText(String.valueOf(i) + " out of 100");
                update();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private void addscore()
    {
        scorecounter+=100;
        score.setScore(scorecounter);
    }



    private  void  dedscore()
    {if(scorecounter>0)
    {scorecounter-=100;
        score.setScore(scorecounter);

    }
    else{
        score.setScore(0);
    }
}

   @Override
    protected void onPause() {
       prefs.savehighscore(score.getScore());
        prefs.setstate(i);

        super.onPause();
    }
}

