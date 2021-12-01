package com.example.aprendaingles.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aprendaingles.R;

public class BichosFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BichosFragment() {

    }

    public static BichosFragment newInstance(String param1, String param2) {
        BichosFragment fragment = new BichosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private ImageButton ibDog, ibGato, ibLeao, ibMacaco, ibOvelha, ibVaca;
    private MediaPlayer mediaPlayer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bichos, container, false);

        ibDog = view.findViewById(R.id.ibDog);
        ibGato = view.findViewById(R.id.ibGato);
        ibLeao = view.findViewById(R.id.ibLeao);
        ibMacaco = view.findViewById(R.id.ibMacaco);
        ibOvelha = view.findViewById(R.id.ibOvelha);
        ibVaca = view.findViewById(R.id.ibVaca);

        //Aplica eventos de clique
        ibDog.setOnClickListener(this);
        ibGato.setOnClickListener(this);
        ibLeao.setOnClickListener(this);
        ibMacaco.setOnClickListener(this);
        ibOvelha.setOnClickListener(this);
        ibVaca.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ){
            case R.id.ibDog:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.dog);
                tocarSom();
                break;
            case R.id.ibGato:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.cat);
                tocarSom();
                break;
            case R.id.ibLeao:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.lion);
                tocarSom();
                break;
            case R.id.ibMacaco:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.monkey);
                tocarSom();
                break;
            case R.id.ibOvelha:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sheep);
                tocarSom();
                break;
            case R.id.ibVaca:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.cow);
                tocarSom();
                break;
        }
    }

    public void tocarSom(){
        if (mediaPlayer != null) {
            mediaPlayer.start();

            mediaPlayer.setOnCompletionListener( mp -> mediaPlayer.release());
        }
    }
}