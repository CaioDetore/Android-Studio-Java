package com.example.aprendaingles.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.aprendaingles.R;

public class NumerosFragment extends Fragment implements View.OnClickListener {

    private ImageButton ibUm, ibDois, ibTres, ibQuatro, ibCinco, ibSeis;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_numeros, container, false);

        ibUm = view.findViewById(R.id.ibUm);
        ibDois = view.findViewById(R.id.ibDois);
        ibTres = view.findViewById(R.id.ibTres);
        ibQuatro = view.findViewById(R.id.ibQuatro);
        ibCinco = view.findViewById(R.id.ibCinco);
        ibSeis = view.findViewById(R.id.ibSeis);

        //clique botão
        ibUm.setOnClickListener(this);
        ibDois.setOnClickListener(this);
        ibTres.setOnClickListener(this);
        ibQuatro.setOnClickListener(this);
        ibCinco.setOnClickListener(this);
        ibSeis.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibUm:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.one);
                tocarSom();
                break;
            case R.id.ibDois:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.one);
                tocarSom();
                break;
            case R.id.ibTres:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.one);
                tocarSom();
                break;
            case R.id.ibQuatro:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.one);
                tocarSom();
                break;
            case R.id.ibCinco:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.one);
                tocarSom();
                break;
            case R.id.ibSeis:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.one);
                tocarSom();
                break;

        }
    }

    public void tocarSom(){
        if (mediaPlayer != null){
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener( mp -> mediaPlayer.release());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}