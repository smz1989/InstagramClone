package com.zibari.sahand.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity, btnSignUpLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        setTitle("Log In");

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignUpLoginActivity = findViewById(R.id.btnSignUpLoginActivity);

        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)

                    onClick(btnSignUpLoginActivity);

                return false;
            }
        });


        btnLoginActivity.setOnClickListener(this);
        btnSignUpLoginActivity.setOnClickListener(this);

        if (ParseUser.getCurrentUser() !=null){

//            ParseUser.getCurrentUser().logOut();
             transitionTOSocialMediaActivity();
        }



    }

    @Override
    public void onClick(View view) {


        switch (view.getId()){

          case  R.id.btnLoginActivity:


              if (edtLoginEmail.getText().toString().equals("") || edtLoginPassword.toString().equals("")){

                  FancyToast.makeText(LoginActivity.this,  "Email, Password is required", Toast.LENGTH_SHORT, FancyToast.INFO,true).show();

              }else {


                  final ParseUser appUser = new ParseUser();
                  appUser.setEmail(edtLoginEmail.getText().toString());
                  appUser.setPassword(edtLoginPassword.getText().toString());
                  final ProgressDialog progressDialog = new ProgressDialog(this);
                  progressDialog.setMessage(" Logging User " );
                  progressDialog.show();

                  ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(),
                          new LogInCallback() {
                              @Override
                              public void done(ParseUser user, ParseException e) {

                                  if (user != null && e == null) {

                                      FancyToast.makeText(LoginActivity.this,
                                              user.getUsername() + " is logged in",
                                              Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                      transitionTOSocialMediaActivity();

                                  }
                                  progressDialog.dismiss();
                              }
                          });

              }

            break;

            case R.id.btnSignUpLoginActivity:

                break;

        }
    }

    public void rootLayoutLogin(View view){

        try{

            InputMethodManager inputOutManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputOutManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        } catch (Exception e){

            e.printStackTrace();

        }

    }

    private void transitionTOSocialMediaActivity(){

        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);

        startActivity(intent);


    }


}
