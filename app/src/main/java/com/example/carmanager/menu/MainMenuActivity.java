package com.example.carmanager.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.carmanager.R;
import com.example.carmanager.login.CreateCarFragment;
import com.example.carmanager.login.FrontPageFragment;
import com.example.carmanager.login.JoinCarFragment;
import com.example.carmanager.menu.fragments.CardsFragment;
import com.example.carmanager.menu.fragments.CardsHistoryFragment;
import com.example.carmanager.menu.fragments.MembersFragment;
import com.example.carmanager.menu.fragments.MoneyFragment;
import com.example.carmanager.services.API;
import com.example.carmanager.services.interfaces.CarInterface;
import com.example.carmanager.services.model.Car;

import java.lang.reflect.Member;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainMenuActivity extends AppCompatActivity implements CardsFragment.OnFragmentInteractionListener, MembersFragment.OnFragmentInteractionListener, MoneyFragment.OnFragmentInteractionListener, CardsHistoryFragment.OnFragmentInteractionListener {
    private Button buttonMembersMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //fazer transaçao do primeiro fragment que deve aparecer
        MembersFragment memberFragment = MembersFragment.newInstance("","");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout, memberFragment, "memberFragment");
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
