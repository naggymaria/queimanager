package com.example.carmanager.menu.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.carmanager.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CardsHistoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CardsHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardsHistoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageButton buttonMembersMenu;
    private ImageButton buttonMoneyMenu;
    private ImageButton buttonCardMenu;
    private ImageButton buttonCardHistoryMenu;

    private OnFragmentInteractionListener mListener;

    public CardsHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CardsHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CardsHistoryFragment newInstance(String param1, String param2) {
        CardsHistoryFragment fragment = new CardsHistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cards_history, container, false);

        buttonMembersMenu = view.findViewById(R.id.membersButton);

        buttonMembersMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MembersFragment membersFragment = MembersFragment.newInstance("","");
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout, membersFragment, "membersFragment");
                fragmentTransaction.commit();
            }
        });

        buttonMoneyMenu = view.findViewById(R.id.moneyButton);

        buttonMoneyMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoneyFragment moneyFragment = MoneyFragment.newInstance("","");
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout, moneyFragment, "moneyFragment");
                fragmentTransaction.commit();
            }
        });

        buttonCardMenu = view.findViewById(R.id.eventsButton);

        buttonCardMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardsFragment cardsFragment = CardsFragment.newInstance("","");
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout, cardsFragment, "cardsFragment");
                fragmentTransaction.commit();
            }
        });

        buttonCardHistoryMenu = view.findViewById(R.id.historyButton);

        buttonCardHistoryMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardsHistoryFragment cardsHistoryFragment = CardsHistoryFragment.newInstance("","");
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout, cardsHistoryFragment, "cardsHistoryFragment");
                fragmentTransaction.commit();
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
