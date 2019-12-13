package com.example.finalyearapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText EmailId;
    private EditText Password;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public static final String KEY1="com.example.MESSAGE1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        EmailId = findViewById(R.id.EmailLogin);
        Password = findViewById(R.id.LoginPassword);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            //=========================================================================================================================================================================================
            //If somebody opens the app and are already logged in this will check it and send them to the menu
            //=========================================================================================================================================================================================

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null){
                    Toast.makeText(MainActivity.this,"You are Logged in",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,
                            Menu.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,"You are Logged out",Toast.LENGTH_SHORT).show();

                }

            }
        };
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
        //===========
        // ==============================================================================================================================================================================
        //Setting up an onclick listener for my Login
        //
        //When Login is clicked it will check if each box contains a value and then check if the values are correct
        //=========================================================================================================================================================================================

        Button login = (Button) findViewById(R.id.LoginButton);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View c) {
                String Email = EmailId.getText().toString().trim();
                String pword = Password.getText().toString().trim();
                if (Email.isEmpty()) {
                    EmailId.setError("Please enter Email");
                    EmailId.requestFocus();
                } else if (pword.isEmpty()) {
                    Password.setError("Please enter Password");
                    Password.requestFocus();
                } else {


                    //=========================================================================================================================================================================================
                    //Here we then sign in the user with the Firebase Authenticaiton
                    //=========================================================================================================================================================================================

                    mFirebaseAuth.signInWithEmailAndPassword(Email, pword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                startActivity(new Intent(MainActivity.this, Menu.class));

                            }
                            else {

                                Toast.makeText(MainActivity.this, "Login Error, Please Login Again!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }

            }
        });


        //=========================================================================================================================================================================================
        //Creating a register intent so if people haven't signed up that they can register
        //=========================================================================================================================================================================================

        TextView registerButton = (TextView) findViewById(R.id.Register);
        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
        public void onClick(View v){
            Intent intent=new Intent(MainActivity.this,
                    Register.class);

            startActivity(intent);
        }


    });
    }
    //=========================================================================================================================================================================================
    //Hardcoding questions into my database
    //=========================================================================================================================================================================================

    protected void onStart() {
        super.onStart();
       mFirebaseAuth.addAuthStateListener((mAuthStateListener));

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference().child("Questions");
        Question newQuestion = new Question();

        newQuestion.setQuestion("How many tonnes of plastic is dumped in the ocean each year");
        newQuestion.setAnswer1("750,000 ");
        newQuestion.setAnswer2("200,000 ");
        newQuestion.setDifficulty("Easy");
        newQuestion.setAnswer3("10,000,000 ");
        newQuestion.setAnswerCorrect("8,000,000 ");
        newQuestion.setCategory("Plastic");
        newQuestion.setDescription("We are facing a crisis, there is too much plastic in the ocean due to illegal dumping. Recycle safely or Stop using plastics");
        newQuestion.setScore(100);
        newQuestion.setId(1);

        mRootRef.child("Plastic").child("1").setValue(newQuestion);


        Question newQuestion1 = new Question();

        newQuestion1.setQuestion("What type of supermarket bag is more ecofriendly, paper or plastic?");
        newQuestion1.setAnswer1("Plastic");
        newQuestion1.setAnswer2("Paper");
        newQuestion1.setDifficulty("Easy");
        newQuestion1.setAnswer3("Either");
        newQuestion1.setAnswerCorrect("Neither");
        newQuestion1.setDescription("Manufacturing and disposing of paper and plastic bags is bad for the environment use your own reusable bags ");
        newQuestion1.setScore(100);
        newQuestion1.setId(2);

        mRootRef.child("Plastic").child("2").setValue(newQuestion1);

        Question newQuestion2 = new Question();

        newQuestion2.setQuestion("Approximately how much global electricity output is produced from renewable sources?");
        newQuestion2.setAnswer1("25%");
        newQuestion2.setAnswer2("11%");
        newQuestion2.setDifficulty("Easy");
        newQuestion2.setAnswer3("2%");
        newQuestion2.setAnswerCorrect("10%");
        newQuestion2.setDescription("Only 10% of energy comes from renewable sources, the other 90 comes from oil, gas and coals etc");
        newQuestion2.setScore(100);
        newQuestion2.setId(3);


        mRootRef.child("Plastic").child("3").setValue(newQuestion2);

        Question newQuestion3 = new Question();

        newQuestion3.setQuestion("Which of the following is an alternative material for making paper?");
        newQuestion3.setAnswer1("hemp");
        newQuestion3.setAnswer2("sheep skin");
        newQuestion3.setDifficulty("Easy");
        newQuestion3.setAnswer3("Panda Excrement");
        newQuestion3.setAnswerCorrect("All of the above");
        newQuestion3.setDescription("All of these can be used although hemp is the cleanest out of them all and it easily recycled ");
        newQuestion3.setScore(100);
        newQuestion3.setId(4);


        mRootRef.child("Plastic").child("4").setValue(newQuestion3);

        Question newQuestion4 = new Question();

        newQuestion4.setQuestion("How much single use plastic bottles are thrown out each hour in the US");
        newQuestion4.setAnswer1("1,000");
        newQuestion4.setAnswer2("12,500");
        newQuestion4.setDifficulty("Easy");
        newQuestion4.setAnswer3("20,000");
        newQuestion4.setAnswerCorrect("25,0000");
        newQuestion4.setDescription("majority of it ends up in the ocean through deliberate dumping of garbage into waterways, inefficient waste infrastructure, and simple littering.");
        newQuestion4.setScore(100);
        newQuestion4.setId(5);


        mRootRef.child("Plastic").child("5").setValue(newQuestion4);
        Question newQuestion5 = new Question();

        newQuestion5.setQuestion("What happens to plastic");
        newQuestion5.setAnswer1("It is biodegradable material os it eventually disintegrates");
        newQuestion5.setAnswer2("It is dumped into the ocean for the fish to eat");
        newQuestion5.setDifficulty("Easy");
        newQuestion5.setAnswer3("There is no such thing as plastic waste, all plastic is recycled");
        newQuestion5.setAnswerCorrect("It never fully goes away, it just breaks into little pieces");
        newQuestion5.setDescription("Plastic will never ever disappear, it will continually break down into smaller pieces, it highlights the importance of reducing plastic usage ");
        newQuestion5.setScore(100);
        mRootRef.child("Plastic").child("6").setValue(newQuestion5);
        newQuestion5.setId(6);

        Question newQuestion6 = new Question();


        newQuestion6.setQuestion("Why are plastic bags dangerous for marine life");
        newQuestion6.setAnswer1("They mistake it for food and cannot digest it");
        newQuestion6.setAnswer2("It is not dangerous as they use it to build their habitats");
        newQuestion6.setDifficulty("Easy");
        newQuestion6.setAnswer3("They get tangled in it and can reduce their ability to swim");
        newQuestion6.setAnswerCorrect("Both A and c");
        newQuestion6.setDescription("Plastic bags are often mistaken for  jellyfish and consumed by turtles, plastic often floats and bobbles which is why it is mistaken for marine life");
        newQuestion6.setScore(100);
        newQuestion6.setId(7);


        mRootRef.child("Plastic").child("7").setValue(newQuestion6);

        Question newQuestion7 = new Question();


        newQuestion7.setQuestion("How many marine species are harmed by plastic pollution?");
        newQuestion7.setAnswer1("52");
        newQuestion7.setAnswer2("693");
        newQuestion7.setDifficulty("Easy");
        newQuestion7.setAnswer3("1,326");
        newQuestion7.setAnswerCorrect("693");
        newQuestion7.setDescription("A 2015 Plymouth University study compiled reports recorded from around the world and found evidence of 44,000 animals becoming entangled or swallowing plastic debris");
        newQuestion7.setScore(100);
        newQuestion7.setId(8);


        mRootRef.child("Plastic").child("8").setValue(newQuestion7);


        Question newQuestion8 = new Question();


        newQuestion8.setQuestion("What percent of its plastic does the US recycle?");
        newQuestion8.setAnswer1("70%");
        newQuestion8.setAnswer2("35%");
        newQuestion8.setDifficulty("Easy");
        newQuestion8.setAnswer3("50%");
        newQuestion8.setAnswerCorrect("9%");
        newQuestion8.setDescription("if current production and management trends continue in the U.S., 12 billion Mt of plastic waste will end up in landfills or the ocean by 2050.");
        newQuestion8.setScore(100);
        newQuestion8.setId(9);


        mRootRef.child("Plastic").child("9").setValue(newQuestion8);

        Question newQuestion9 = new Question();

        mRootRef.child("Plastic").child("6").setValue(newQuestion5);
        newQuestion9.setQuestion("Approximately, Americans use about how many plastic drinking straws per day");
        newQuestion9.setAnswer1("25,000");
        newQuestion9.setAnswer2("250,000,000");
        newQuestion9.setDifficulty("Easy");
        newQuestion9.setAnswer3("100,000");
        newQuestion9.setAnswerCorrect("500,000,000");
        newQuestion9.setDescription("On average, Americans use 1.6 plastic drinking straws every day, totaling to 500 million per day. That’s enough to fill up 125 school buses a day, or 46,400 school buses a year! ");
        newQuestion9.setScore(100);
        newQuestion9.setId(10);


        mRootRef.child("Plastic").child("10").setValue(newQuestion9);

        Question pollutionquiz = new Question();


        pollutionquiz.setQuestion("How many deaths are there annually as a result of exposure to outdoor air pollution?");
        pollutionquiz.setAnswer1("100");
        pollutionquiz.setAnswer2("200,000");
        pollutionquiz.setDifficulty("Easy");
        pollutionquiz.setAnswer3("2,000,000");
        pollutionquiz.setAnswerCorrect("3,700,000");
        pollutionquiz.setDescription("According to statistics from the UN, 3.7 million deaths are attributed to outdoor air pollution. ");
        pollutionquiz.setScore(100);
        pollutionquiz.setId(1);


        mRootRef.child("Pollution").child("1").setValue(pollutionquiz);

        Question pollutionquiz1 = new Question();


        pollutionquiz1.setQuestion("Facemasks are often used in China during air pollution episodes. What is the percentage range of ambient air particles that are filtered by standard facemasks?");
        pollutionquiz1.setAnswer1("90 - 100%");
        pollutionquiz1.setAnswer2("1- 15%");
        pollutionquiz1.setDifficulty("Easy");
        pollutionquiz1.setAnswer3("26 - 40%");
        pollutionquiz1.setAnswerCorrect("48 - 75%");
        pollutionquiz1.setDescription("A study in Particle and Fibre Toxicology tested 6 types of standard facemasks used in China and found that the most effective facemask removed only 75% of ambient air particles and the least effective mask removed 48%.");
        pollutionquiz1.setScore(100);
        pollutionquiz1.setId(2);


        mRootRef.child("Pollution").child("2").setValue(pollutionquiz1);

        Question pollutionquiz2 = new Question();


        pollutionquiz2.setQuestion("Does Environmental pollution exposure during pregnancy increase risk factor for pre-term (premature) birth");
        pollutionquiz2.setAnswer1("No only smoking causes this");
        pollutionquiz2.setAnswer2("no the air you breathin doesnt effect your baby");
        pollutionquiz2.setDifficulty("Easy");
        pollutionquiz2.setAnswer3("only if you work in polluted areas");
        pollutionquiz2.setAnswerCorrect("yes regular exposure to pollution increase chances of pre term");
        pollutionquiz2.setDescription("A study in Particle and Fibre Toxicology tested 6 types of standard facemasks used in China and found that the most effective facemask removed only 75% of ambient air particles and the least effective mask removed 48%.");
        pollutionquiz2.setScore(100);
        pollutionquiz2.setId(3);


        mRootRef.child("Pollution").child("3").setValue(pollutionquiz2);

        Question pollutionquiz3 = new Question();
        pollutionquiz3.setQuestion("Exposure to indoor second hand smoke, dirty cooking fuel, and dampness increases the risk of which infectious disease in children?");
        pollutionquiz3.setAnswer1("Malaria");
        pollutionquiz3.setAnswer2("Mumps");
        pollutionquiz3.setDifficulty("Cholera");
        pollutionquiz3.setAnswer3("only if you work in polluted areas");
        pollutionquiz3.setAnswerCorrect("Tuberculosis");
        pollutionquiz3.setDescription("A study based in Durban, South Africa published in BMC Public Health suggests an increased risk of childhood tuberculosis when exposed to these types of indoor air pollutants.");
        pollutionquiz3.setScore(100);
        pollutionquiz3.setId(4);

        mRootRef.child("Pollution").child("4").setValue(pollutionquiz3);
        Question pollutionquiz4 = new Question();
        pollutionquiz4.setQuestion("How much CO2 was removed from the atmosphere by Brazilian forest plantations between 1990 and 2016?");
        pollutionquiz4.setAnswer1("1,000 tonnes");
        pollutionquiz4.setAnswer2("10 gigatonnes");
        pollutionquiz4.setDifficulty("100 gigatones");
        pollutionquiz4.setAnswer3("only if you work in polluted areas");
        pollutionquiz4.setAnswerCorrect("1669 Gigatonnes");
        pollutionquiz4.setDescription("1669 Gigatonnes of CO2 over the 26 year period. 1 Gigatonne is equal to 1 billion tonnes. This study emphasizes the important role forests play in mitigating greenhouse gas emissions");
        pollutionquiz4.setScore(100);
        pollutionquiz4.setId(5);

        mRootRef.child("Pollution").child("5").setValue(pollutionquiz4);



        Question pollutionquiz5 = new Question();
        pollutionquiz5.setQuestion("Which of the following is the most effective strategy to reduce the impact of toxic waste");
        pollutionquiz5.setAnswer1("reusing raw materials");
        pollutionquiz5.setAnswer2("raising taxes on companies that pollute");
        pollutionquiz5.setDifficulty("recycling polluting materials");
        pollutionquiz5.setAnswer3("only if you work in polluted areas");
        pollutionquiz5.setAnswerCorrect("stopping the production of polluting materials");
        pollutionquiz5.setDescription("Toxins can be reduced through the substitution of nonpolluting alternatives, such as oxygen for chlorine in the bleaching of wood, or through green chemistry");
        pollutionquiz5.setScore(100);
        pollutionquiz5.setId(6);

        mRootRef.child("Pollution").child("6").setValue(pollutionquiz5);
    }

}