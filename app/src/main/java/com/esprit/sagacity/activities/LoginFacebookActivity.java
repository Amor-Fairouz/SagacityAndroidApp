package com.esprit.sagacity.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.esprit.sagacity.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by amor on 31/12/2015.
 */
public class LoginFacebookActivity extends ActionBarActivity{
    CallbackManager callbackManager;
    Button go;
    Button details;
    ShareDialog shareDialog;
    LoginButton login;
    ProfilePictureView profile;
    Dialog details_dialog;
    TextView details_txt;
    Context context;
    ImageButton iconprofile;
    ImageButton icongo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        login = (LoginButton)findViewById(R.id.login_button);

        profile = (ProfilePictureView)findViewById(R.id.picture);
        shareDialog = new ShareDialog(this);
        go = (Button)findViewById(R.id.continued);
        details = (Button)findViewById(R.id.fbuser);
        iconprofile=(ImageButton)findViewById(R.id.profileicon);
        icongo=(ImageButton)findViewById(R.id.icongo);
        login.setReadPermissions("public_profile email");
        //profile.setVisibility(View.INVISIBLE);
        go.setVisibility(View.INVISIBLE);
        details.setVisibility(View.INVISIBLE);
        //iconprofile.setVisibility(View.INVISIBLE);
        //icongo.setVisibility(View.INVISIBLE);
        details_dialog = new Dialog(this);
        details_dialog.setContentView(R.layout.dialog_details);
        details_dialog.setTitle("Details");
        details_txt = (TextView)details_dialog.findViewById(R.id.fbuser);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                details_dialog.show();
            }
        });


        if(AccessToken.getCurrentAccessToken() != null){
            RequestData();
            icongo.setVisibility(View.VISIBLE);
            iconprofile.setVisibility(View.VISIBLE);
            go.setVisibility(View.VISIBLE);
            details.setVisibility(View.VISIBLE);


        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AccessToken.getCurrentAccessToken() != null) {
                   // profile.setVisibility(View.INVISIBLE);
                    go.setVisibility(View.INVISIBLE);
                    details.setVisibility(View.INVISIBLE);
                    //icongo.setVisibility(View.INVISIBLE);
                    //iconprofile.setVisibility(View.INVISIBLE);
                    profile.setProfileId(null);
                }
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // ShareLinkContent content = new ShareLinkContent.Builder().build();
               // shareDialog.show(content);
                Intent intent = new Intent(LoginFacebookActivity.this,
                        ParseAuthorsActivity.class);
                startActivity(intent);

            }
        });
        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                if(AccessToken.getCurrentAccessToken() != null){
                    RequestData();
                    go.setVisibility(View.VISIBLE);
                    details.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {
            }
        });

    }
    public void RequestData(){
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object,GraphResponse response) {

                JSONObject json = response.getJSONObject();
                try {
                    if(json != null){
                        String text = "<b>Name :</b> "+json.getString("name")+"<br><br><b>Email :</b> "+json.getString("email")+"<br><br><b>Profile link :</b> "+json.getString("link");
                        details_txt.setText(Html.fromHtml(text));
                        profile.setProfileId(json.getString("id"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
