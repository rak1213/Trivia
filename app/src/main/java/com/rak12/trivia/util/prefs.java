package com.rak12.trivia.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class prefs {
    private SharedPreferences sharedPreferences;

    public prefs(Activity activity) {
        this.sharedPreferences = activity.getPreferences(activity.MODE_PRIVATE);

    }
    public void savehighscore(int score1)
    {int currentscore=score1;
   int lastscore= sharedPreferences.getInt("highscore",0);
    if(currentscore>lastscore)
            {sharedPreferences.edit().putInt("highscore",currentscore).apply();

                            }
    }


   public int gethighscore()
    {
       return sharedPreferences.getInt("highscore",0);
    }
    public void setstate(int index)
    {sharedPreferences.edit().putInt("index",index).apply();

    }
    public int getstate()
    {return sharedPreferences.getInt("index",0);
    }
}
