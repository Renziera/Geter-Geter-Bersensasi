package com.interpixel.geter_geterbersensasi;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SongFragment extends Fragment {

    private GeterListener geterListener;


    public SongFragment() {
        // Required empty public constructor
    }

    public void setGeterListener(MainActivity activity){
        geterListener = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song, container, false);

        view.findViewById(R.id.buttonPilihLagu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("audio/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 42);
            }
        });

        view.findViewById(R.id.buttonGetar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VibrateFromAudio();
                getActivity().finish();
            }
        });

        return view;
    }

    private void VibrateFromAudio(){
        //TODO vibrate sesuai audio
        /**
         * Audio harus di parse dulu, dapetin amplitude dan timings nya
         * lalu dijadiin array dan bikin VibrationEffect createWaveForm (API 26+)
         *
         */
    }

}
