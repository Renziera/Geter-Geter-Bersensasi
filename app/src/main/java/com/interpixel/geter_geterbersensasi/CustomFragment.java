package com.interpixel.geter_geterbersensasi;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomFragment extends Fragment {

    private GeterListener geterListener;
    private EditText[] customValue = new EditText[8];
    private long[] pattern = new long[9];
    private Button buttonGetar;
    private CheckBox repeat;
    private int totalTime;
    private View.OnClickListener start = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (EditText editText : customValue){
                editText.setEnabled(false);
            }
            buttonGetar.setText("Berhenti");
            buttonGetar.setOnClickListener(stop);
            totalTime = 0;
            for (int i = 0; i < customValue.length; i++){
                if(customValue[i].getText().toString().isEmpty()){
                    pattern[i+1] = 0;
                }else{
                    pattern[i+1] = Long.parseLong(customValue[i].getText().toString());
                }
                totalTime = (int) (totalTime + pattern[i+1]);
            }
            if(!repeat.isChecked()){
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (EditText editText : customValue){
                            editText.setEnabled(true);
                        }
                        buttonGetar.setText("Getar");
                        buttonGetar.setOnClickListener(start);
                    }
                }, totalTime);
            }
            geterListener.geterPattern(pattern, repeat.isChecked());
        }
    };
    private View.OnClickListener stop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            geterListener.stopGetar();
            for (EditText editText : customValue){
                editText.setEnabled(true);
            }
            buttonGetar.setText("Getar");
            buttonGetar.setOnClickListener(start);
        }
    };

    public CustomFragment() {
        // Required empty public constructor
    }

    public void setGeterListener(MainActivity activity){
        geterListener = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom, container, false);

        buttonGetar = view.findViewById(R.id.buttonGetar);
        buttonGetar.setOnClickListener(start);
        repeat = view.findViewById(R.id.repeat);

        customValue[0] = view.findViewById(R.id.durasi1);
        customValue[2] = view.findViewById(R.id.durasi2);
        customValue[4] = view.findViewById(R.id.durasi3);
        customValue[6] = view.findViewById(R.id.durasi4);
        customValue[1] = view.findViewById(R.id.jeda1);
        customValue[3] = view.findViewById(R.id.jeda2);
        customValue[5] = view.findViewById(R.id.jeda3);
        customValue[7] = view.findViewById(R.id.jeda4);

        return view;
    }

}
