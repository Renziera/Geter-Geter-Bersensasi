package com.interpixel.geter_geterbersensasi;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MorseFragment extends Fragment {

    private GeterListener geterListener;
    private Button buttonGetar;
    private EditText teks;
    private String teksAsli;
    private View.OnClickListener start = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            teks.setEnabled(false);
            buttonGetar.setText("Berhenti");
            buttonGetar.setOnClickListener(stop);
            teksAsli = teks.getText().toString();
        }
    };
    private View.OnClickListener stop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            geterListener.stopGetar();
            teks.setEnabled(true);
            buttonGetar.setText("Getar");
            buttonGetar.setOnClickListener(start);
        }
    };

    public MorseFragment() {
        // Required empty public constructor
    }

    public void setGeterListener(MainActivity activity){
        geterListener = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_morse, container, false);

        teks = view.findViewById(R.id.teks);
        buttonGetar = view.findViewById(R.id.buttonGetar);

        return view;
    }

    /**
     * Each Morse code symbol is formed by a sequence of dots and dashes.
     * The dot duration is the basic unit of time measurement in Morse code transmission.
     * The duration of a dash is three times the duration of a dot.
     * Each dot or dash within a character is followed by period of signal absence, called a space,
     * equal to the dot duration.
     * The letters of a word are separated by a space of duration equal to three dots,
     * and the words are separated by a space equal to seven dots.
     */

    private static final long dit = 100;
    private static final long dah = 300;
    private static final long gap = 100;
    private static final long end = 300;
    private static final long space = 700;

    public long[] toMorse(char c){
        long[] code;

        switch (c){
            case 'A':
            case 'a':
                code = new long[]{0, dit, gap, dah, end};
                break;
            case 'B':
            case 'b':
                code = new long[]{0, dah, gap, dit, gap, dit, gap, dit, end};
                break;
            case 'C':
            case 'c':
                code = new long[]{0, dah, gap, };
                break;
        }

        return code;
    }

}
