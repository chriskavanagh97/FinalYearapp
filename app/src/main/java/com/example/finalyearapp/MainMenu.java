package com.example.finalyearapp;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.finalyearapp.Carbonfootprint.Results;
import com.example.finalyearapp.Carbonfootprint.StepOne;
import com.example.finalyearapp.Carbonfootprint.linechartresults;
import com.example.finalyearapp.ClimateChange.Animal;
import com.example.finalyearapp.ClimateChange.AnimalMenu;
import com.example.finalyearapp.ClimateChange.Didyouknow;
import com.example.finalyearapp.Leaderboard.Leaderboard;
import com.example.finalyearapp.Maps.Instructions;
import com.example.finalyearapp.Maps.Locationservices;
import com.example.finalyearapp.Maps.MapsActivity;
import com.example.finalyearapp.Maps.locations;
import com.example.finalyearapp.News.ArticlesActivity;
import com.example.finalyearapp.News.NewsSelection;
import com.example.finalyearapp.QuizMenuPackage.QuizMenu;
import com.example.finalyearapp.RecycleMaterial.recyclemenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.Objects;

import static com.example.finalyearapp.MainActivity.KEY1;


public class MainMenu extends AppCompatActivity {

    int score1;

    private int score;
    DatabaseReference database;
    TextView emaiil;
    RelativeLayout maincontent;
    LinearLayout mainmenu;
    ImageView News, map;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        String email = Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getEmail();

        maincontent = (RelativeLayout) findViewById(R.id.mainContent);
        mainmenu = (LinearLayout) findViewById(R.id.mainmenu);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.toolbar));



        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maincontent.animate().translationX(0);
                mainmenu.animate().translationX(0);


            }
        });
        News = (ImageView) findViewById(R.id.News);
        News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, NewsSelection.class);

                startActivity(intent);
            }
        });
        map = (ImageView) findViewById(R.id.maps);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Instructions.class);
                intent.putExtra("value", 3);

                startActivity(intent);
            }
        });
        maincontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                maincontent.animate().translationX(-510);
                mainmenu.animate().translationX(-510);
            }
        });
        //=========================================================================================================================================================================================
        //reading from the Database
        //=========================================================================================================================================================================================


        //=========================================================================================================================================================================================
        //Setting the text box with info from the database
        //=========================================================================================================================================================================================

        ImageView quiz = (ImageView) findViewById(R.id.Quiz);
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inttoQuiz = new Intent(MainMenu.this, QuizMenu.class);
                startActivity(inttoQuiz);

            }
        });
        ImageView carbonfootprint = (ImageView) findViewById(R.id.Carbonfootprint);
        carbonfootprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent footprint = new Intent(MainMenu.this, StepOne.class);
                startActivity(footprint);

            }
        });

        //=========================================================================================================================================================================================
        //Sign Out
        //=========================================================================================================================================================================================

        Button signout = (Button) findViewById(R.id.SignOut);
        signout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(MainMenu.this, HomeScreen .class);
                startActivity(intToMain);

            }
        });

        //=========================================================================================================================================================================================
        //Writing a new score to the database
        //=========================================================================================================================================================================================
       /* Button scoreupdate = (Button) findViewById(R.id.ScoreUpdate);
        scoreupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c) {

                score = 100;




            }
        });*/

        //=========================================================================================================================================================================================
        //Switching to the profile page
        //=========================================================================================================================================================================================
        Button profile = (Button) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,
                        ViewUser.class);

                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        intent.getStringExtra(KEY1);


        ImageView leaderboard = (ImageView) findViewById(R.id.Leaderboard);
        leaderboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,
                        Didyouknow.class);

                startActivity(intent);
            }
        });
        ImageView recycle = (ImageView) findViewById(R.id.recycle);
        recycle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,
                        recyclemenu.class);

                startActivity(intent);
            }
        });

    }


}



