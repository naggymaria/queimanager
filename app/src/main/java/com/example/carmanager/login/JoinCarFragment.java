package com.example.carmanager.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.carmanager.R;
import com.example.carmanager.menu.MainMenuActivity;
import com.example.carmanager.services.API;
import com.example.carmanager.services.interfaces.PersonInterface;
import com.example.carmanager.services.model.Person;
import com.facebook.CallbackManager;

import java.util.Objects;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JoinCarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JoinCarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JoinCarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageButton buttonBack;
    private EditText personName;
    private String personNameAux;
    private EditText carCode;
    private String carCodeAux;

    private Button createPersonButton;

    private PersonInterface personInterface;

    private OnFragmentInteractionListener mListener;
    private CallbackManager callbackManager = CallbackManager.Factory.create();


    public JoinCarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JoinCarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JoinCarFragment newInstance(String param1, String param2) {
        JoinCarFragment fragment = new JoinCarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_join_car, container, false);

        buttonBack = view.findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrontPageFragment frontPageFragment = FrontPageFragment.newInstance("","");
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout, frontPageFragment, "frontPage");
                fragmentTransaction.commit();
            }
        });

        createPersonButton = view.findViewById(R.id.createPerson);

        carCode = view.findViewById(R.id.carCode);
        personName = view.findViewById(R.id.carName);

        carCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                carCodeAux = s.toString();
                Objects.requireNonNull(getActivity()).getIntent().putExtra("carCode", carCodeAux);
            }
        });

        personName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                personNameAux = s.toString();
                Objects.requireNonNull(getActivity()).getIntent().putExtra("personName", personNameAux);
            }
        });

        createPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personInterface = API.getClient().create(PersonInterface.class);
                Random rand = new Random();
                Person p = new Person();
                p.admin = false;
                p.personName = personNameAux;

                SharedPreferences sharedPref = getActivity().getApplicationContext().getSharedPreferences("sp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString("carCode", carCodeAux);
                editor.apply();

                Call<String> call = personInterface.createPerson(p, carCodeAux);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), MainMenuActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
