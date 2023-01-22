package com.example.a7pce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ///
    Dialog test;
    Dialog pomoc;

    AutoCompleteTextView x;
    EditText u;
    Button s;
    Button d;
    Button e;
    ImageButton kvaka;
    ImageButton upitnik;
    TextView t;
    TextView naslov;

    int uk = 0;
    int xd=0;


    private int mod = -1;
    public void setMod(int a){
        mod = a;
    }
    public int getMod(){
        return mod;
    }
    Vibrator vibrator;
    boolean dalDaVibrira = true;
    com.google.android.material.textfield.TextInputLayout izbor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //////////////////////////////////////
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        test = new Dialog(this);
        test.setContentView(R.layout.izbacivanje);
        test.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialogbackground));

        pomoc = new Dialog(this);
        pomoc.setContentView(R.layout.guide);
        pomoc.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialogbackground));


            Spinner spinner = findViewById(R.id.spinner1);
            //Spinner spinner = findViewById(R.id.autoCompleteTextView);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Modovi, android.R.layout.simple_spinner_item); //R.layout.dropdownitem
                                                                                                            //android.r.layour.simplespinneritem

            adapter.setDropDownViewResource(R.layout.dropdownitem); //R.layour.dropdownitem
                                                //android.r.layout.simple spinner dropwon item
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);






    u = (EditText) findViewById(R.id.izviniUNOS);
    t = (TextView) findViewById(R.id.textView2);
    d = (Button) findViewById(R.id.button);
    s = (Button) findViewById(R.id.button2);
    e = (Button) findViewById(R.id.button3);
    naslov = (TextView) findViewById(R.id.textView);
    kvaka = (ImageButton) findViewById(R.id.imageButton2);
    upitnik = (ImageButton) findViewById(R.id.imageButton3);
    vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);


    naslov.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (xd==0) {
                naslov.setTextColor(Color.parseColor("#FFFFFF"));
                xd = 1;
            } else {
                naslov.setTextColor(Color.parseColor("#FF4C29"));
                xd = 0;
            }
        }
    });

    naslov.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            vibrator.vibrate(100);
            return false;
        }
    });

    e.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            e.setVisibility(View.INVISIBLE);
            s.setVisibility(View.INVISIBLE);
            u.setText("");
            u.setHint("Unesi babunlije");
            t.setText("");
           // t.setText(Integer.toString(mod));
        }
    });

    kvaka.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            dalDaVibrira = !dalDaVibrira;
            if (dalDaVibrira) vibrator.vibrate(200);
            if (dalDaVibrira) toast("Vibracija: ON");
            else toast("Vibracija: OFF");
        }
    });

    upitnik.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pomoc.show();
        }
    });

    s.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String string = t.getText().toString();
            int brojac= 0;
            for (int i = 0; i < string.length(); i++){
               if (string.charAt(i)=='\n') brojac++;
            }

            //if (getMod() < 0) t.setText("ne valja mod");
            //else t.setText(Integer.toString(getMod()));


            if (brojac < 2) {
                u.setHint("MORA VISE OD 1 IGRACA");
                return;
            }

            ////// OTVARANJE NOVOG AKTIVITIJA
            int max = 7;           /////////////////////
            boolean izvini = true; /////////////////////
           if (mod == 1){
               max = 1; izvini = false;
           }
           if (mod == 2){
               max = 1; izvini = true;
           }
           if (mod == 3){ //bez cepa
               max = 7; izvini = false; //TREBA SREDITI ZA BEZ CEPA I ZA ZLATNU
           }
           if (mod == 4){ //zlatna


           }


            openMainActivity2(string, max, izvini);
        }
    });

//    d.setOnLongClickListener(new View.OnLongClickListener() {
//        @Override
//        public boolean onLongClick(View v) {
//            test.show();
//
//
//            return false;
//        }
//    });
    d.setOnClickListener(new View.OnClickListener() {
        @Override //dugme za dodavanje ljudi
        public void onClick(View v) {

            String uneseno = u.getText().toString();
            if (uneseno == null || uneseno.isEmpty()) {
                if (u.getHint() == "UNESI BABUNLIJE") u.setHint("U N E S I    BABUNLIJO"); else
                u.setHint("UNESI BABUNLIJE");


                return;
            }
            String[] likovi = uneseno.split(" ");

            int broj = likovi.length; //broj ljudi koji igraju
            for (int i = 0; i < likovi.length; i++) {
                if (likovi[i].isEmpty()) {
                    broj--;
                    likovi[i] = null;
                }

                if (likovi[i]!=null) t.append(likovi[i]+ "\n");
            }
            uk += broj;
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager. hideSoftInputFromWindow(v. getApplicationWindowToken(),0); //sakriva tastaturu pri kliku

            u.setText("");
            u.setHint("jos babunlija?");
            s.setVisibility(View.VISIBLE);
            e.setVisibility(View.VISIBLE);
            if (uk < 10) t.setTextSize(24);
            if (uk > 9) t.setTextSize(18);
            if (uk > 14) t.setTextSize(15);
        }

    });

}
public void openMainActivity2(String a, int b, boolean c){
        Intent intent = new Intent(this, MainActivity2.class);


        intent.putExtra("igraci", a);
        intent.putExtra("max", b);
        intent.putExtra("izvini", c);
        intent.putExtra("mod", mod);
        intent.putExtra("vibr", dalDaVibrira);

        startActivity(intent);
}


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mod = position;
        if (mod == 4) toast("e moj diete");
//        else
//        toast("pickice");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void toast(String poruka){
        Toast.makeText(getApplication().getBaseContext(), poruka, Toast.LENGTH_SHORT).show();
    }

}