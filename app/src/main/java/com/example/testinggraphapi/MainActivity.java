package com.example.testinggraphapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private ImageView imageView;
    private TextView textView;
    public ArrayList<Bundle> Links = new ArrayList<Bundle>();
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.iv_profilePic);

        loginButton.setPermissions(Arrays.asList("user_gender,user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Demo", "Login Succesfull");
            }


            @Override
            public void onCancel() {
                Log.d("Demo", "Login Cancelled");
            }

            @Override
            public void onError(@NonNull FacebookException e) {
                Log.d("Demo", "Login Error");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(@Nullable JSONObject jsonObject, @Nullable GraphResponse graphResponse) {
                Log.d("demo", jsonObject.toString());
                List<String> vallist = new ArrayList<>();
                String[] pics = jsonObject.toString().split("\"posts\":")[1].split(",");
                for(String pic:pics){
                    if(pic.contains("full_picture")){
                        String[] values = pic.split("\"full_picture\":");
                        if(values.length>1){
                            String value = values[1].replace("\\","").replace("}","").substring(1);
                            System.out.println(value);
                            vallist.add(value);
                        }

                    }
                }

                try {
                    String name = jsonObject.getString("name");
                    String id = jsonObject.getString("id");
                    textView.setText(name);
                    Picasso.get().load("https://graph.facebook.com/" + id + "/picture?width=9999")
                            .into(imageView);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle bundle = new Bundle();
        Bundle printbundle = new Bundle();

        bundle.putString("fields", "gender,name,id,first_name,last_name,posts{full_picture}");

        graphRequest.setParameters(bundle);

        graphRequest.executeAsync();

        System.out.println("Sout");

    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            if (currentAccessToken == null) {
                LoginManager.getInstance().logOut();
                textView.setText("");
                imageView.setImageResource(0);
            }
        }

    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.startTracking();
    }
}