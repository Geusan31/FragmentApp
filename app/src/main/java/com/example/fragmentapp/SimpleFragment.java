package com.example.fragmentapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimpleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimpleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CHOICE = "choice";

    // TODO: Rename and change types of parameters
    private int mChoice;

    private  OnFragmentListener mListener;

    public interface OnFragmentListener {
        void onRadioButtonChoice(int choice);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentListener) {
            mListener = (OnFragmentListener) context;
        }
    }


    public SimpleFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SimpleFragment newInstance(int choice) {
            SimpleFragment fragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putInt(CHOICE, choice);
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
        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);
        final TextView textView = rootView.findViewById(R.id.fragment_header);
        if(getArguments().containsKey(CHOICE)) {
            mChoice = getArguments().getInt(CHOICE);
            if(mChoice != 2) {
                radioGroup.check(radioGroup.getChildAt(mChoice).getId());
            }
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                switch (index) {
                    case 0:
                        mChoice = 0;
                        textView.setText(("Article: Like!"));
                        break;
                    case 1:
                        mChoice = 1;
                        textView.setText("Article: Thanks!");
                        break;
                    default:
                        mChoice = 2;
                        break;
                }
                mListener.onRadioButtonChoice(mChoice);
            }
        });
        return rootView;
    }
}