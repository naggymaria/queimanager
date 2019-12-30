package com.example.carmanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.carmanager.login.CreateCarFragment;
import com.example.carmanager.login.FrontPageFragment;
import com.example.carmanager.login.JoinCarFragment;
import com.example.carmanager.menu.MainMenuActivity;
import com.example.carmanager.services.API;
import com.example.carmanager.services.interfaces.CarInterface;
import com.example.carmanager.services.interfaces.PersonInterface;
import com.example.carmanager.services.model.Car;
import com.example.carmanager.services.model.Person;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.common.util.SharedPreferencesUtils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FrontPageFragment.OnFragmentInteractionListener, JoinCarFragment.OnFragmentInteractionListener, CreateCarFragment.OnFragmentInteractionListener {

    private CallbackManager callbackManager = CallbackManager.Factory.create();
    private PersonInterface personInterface;
    private CarInterface carInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        FacebookSdk.fullyInitialize();
        AppEventsLogger.activateApp(this.getApplication());

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        final String carCode = getIntent().getStringExtra("carCode");
                        String personName = getIntent().getStringExtra("personName");

                        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("sp", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();

                        editor.putString("carCode", carCode);
                        editor.apply();

                        personInterface = API.getClient().create(PersonInterface.class);

                        Person p = new Person();
                        p.code = loginResult.getAccessToken().getUserId();
                        p.admin = false;
                        p.personName = personName;

                        Call<String> call = personInterface.createPerson(p, carCode);
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        System.out.println("Cancelei Login");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        System.out.println(exception.toString());
                        System.out.println("Error");
                    }
                });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if(isLoggedIn) {
            //mudar a atividade
            Intent intent = new Intent(this, MainMenuActivity.class);
            startActivity(intent);
            return;
        }

        FrontPageFragment fragmentOne = FrontPageFragment.newInstance("","");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout, fragmentOne, "fragOne");
        fragmentTransaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
