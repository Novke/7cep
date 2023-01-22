package com.example.a7pce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a7pce.Partija.Babunlija;
import com.example.a7pce.Partija.Partija;

import java.util.LinkedList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    int max;
    int rezim;
    String igraci;
    boolean imaIzvini = true;
    boolean jelTrebaIzviniDaBudeVidljivo = false;

    Partija gejm;
    Partija rezervni;
    Partija rezervni1;
    Partija rezervni2;

    TextView ispis;
    TextView statistikaTekst;
    TextView statistikaTekst2;
    TextView statistikaTekst3;
    TextView statistikaTekst4;
    TextView tekstZaIzbacivanje;
    TextView tekst2;

    Button ubo;
    Button nije;
    Button izvini;
    Button statistika;
    Button dalje;

    ImageButton jusles;
    ImageButton undo;
    ImageButton izbaci;
    ImageButton pomoc;


    ImageView klik;
    ImageView flasa0;
    ImageView flasa1;
    ImageView flasa2;
    ImageView flasa3;
    ImageView flasa4;
    ImageView flasa5;
    ImageView flasa6;
    ImageView flasaZlatna1;
    ImageView flasaZlatna2;
    ImageView flasaZlatna3;
    ImageView flasaZlatna4;
    ImageView flasaZlatna5;
    ImageView flasaZlatna6;
    ImageView flasaZlatna;
    ImageView flasaOrange1;
    ImageView flasaOrange2;
    ImageView flasaOrange3;
    ImageView flasaOrange4;
    ImageView flasaOrange5;
    ImageView flasaOrange6;
    ImageView flasaOrange;

    TextView mod;
    TextView uFlasi;
    TextView baca;
    TextView scoreboard;
    androidx.constraintlayout.widget.ConstraintLayout pozadina;
    ConstraintLayout pozadina1;

    Dialog dialog;
    Dialog kraj;
    Dialog statistike;
    Dialog edit;
    Dialog izbacivanje;
    Dialog zamena;

    Vibrator vibrator;
    Boolean dalDaVibrira = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        igraci = getIntent().getStringExtra("igraci");
        imaIzvini = getIntent().getBooleanExtra("izvini", true);
        max = getIntent().getIntExtra("max", 7);
        rezim = getIntent().getIntExtra("mod", 0);
        dalDaVibrira = getIntent().getBooleanExtra("vibr", true);

        flasa0 = (ImageView) findViewById(R.id.flasa0);
        flasa1 = (ImageView) findViewById(R.id.flasa1);
        flasa2 = (ImageView) findViewById(R.id.flasa2);
        flasa3 = (ImageView) findViewById(R.id.flasa3);
        flasa4 = (ImageView) findViewById(R.id.flasa4);
        flasa5 = (ImageView) findViewById(R.id.flasa5);
        flasa6 = (ImageView) findViewById(R.id.flasa6);
        flasaZlatna1 = (ImageView) findViewById(R.id.flasaZlatna1);
        flasaZlatna2 = (ImageView) findViewById(R.id.flasaZlatna2);
        flasaZlatna3 = (ImageView) findViewById(R.id.flasaZlatna3);
        flasaZlatna4 = (ImageView) findViewById(R.id.flasaZlatna4);
        flasaZlatna5 = (ImageView) findViewById(R.id.flasaZlatna5);
        flasaZlatna6 = (ImageView) findViewById(R.id.flasaZlatna6);
        flasaZlatna = (ImageView) findViewById(R.id.flasaZlatna);
        flasaOrange1 = (ImageView) findViewById(R.id.flasaOrange1);
        flasaOrange2 = (ImageView) findViewById(R.id.flasaOrange2);
        flasaOrange3 = (ImageView) findViewById(R.id.flasaOrange3);
        flasaOrange4 = (ImageView) findViewById(R.id.flasaOrange4);
        flasaOrange5 = (ImageView) findViewById(R.id.flasaOrange5);
        flasaOrange6 = (ImageView) findViewById(R.id.flasaOrange6);
        flasaOrange = (ImageView) findViewById(R.id.flasaOrange);

        ubo = (Button) findViewById(R.id.buttonUbo);
        nije = (Button) findViewById(R.id.buttonNije);
        izvini = (Button) findViewById(R.id.buttonIzvini);
        statistika = (Button) findViewById(R.id.buttonStatistika);
        dalje = (Button) findViewById(R.id.buttonDalje);
        jusles = (ImageButton) findViewById(R.id.imageButton);
        undo = (ImageButton) findViewById(R.id.buttonUndo);
        izbaci = (ImageButton) findViewById(R.id.buttonIzbaci);
        pomoc = (ImageButton) findViewById(R.id.buttonPomoc);

        mod = (TextView) findViewById(R.id.textMod);
        uFlasi  = (TextView) findViewById(R.id.textUflasi);
        baca = (TextView) findViewById(R.id.textBaca);
        scoreboard = (TextView) findViewById(R.id.scoreboard);
        pozadina = (androidx.constraintlayout.widget.ConstraintLayout) findViewById(R.id.pozadina);

        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
///////////////////////////////////////////////////////////////////////////
        izbacivanje = new Dialog(this);
        izbacivanje.setContentView(R.layout.izbacivanje);
        izbacivanje.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialogbackground));
        Button ok = (Button) izbacivanje.findViewById(R.id.izviniOK);
        Button abort = (Button) izbacivanje.findViewById(R.id.redosledAbort);
        Button moze = (Button) izbacivanje.findViewById(R.id.redosledAjde);
        EditText unos = (EditText) izbacivanje.findViewById(R.id.izviniUNOS);
        final int[] u = new int[1];
        List<Integer> izbaceni = new LinkedList<>();
        tekstZaIzbacivanje = (TextView) izbacivanje.findViewById(R.id.izvinitekst);

        abort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //brojevi.clear();
                unos.setText("");
                if (gejm.getBroj()>2) ok.setEnabled(true);
                izbacivanje.dismiss();
            }
        });
        moze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String string = "";
            for (int i = 0; i < gejm.getBroj(); i++){
                if (i+1==u[0]) continue;
                string += gejm.getBabunlije(i).toStringSkor() + "\n";
                if (gejm.getBroj()<6) string+="\n";
            }
            scoreboard.setText(string);
                gejm.izbaci(u[0]);
                izbaceni.add(u[0]);
                if (gejm.getNaRedu()>u[0]-1) gejm.setNaRedu(gejm.getNaRedu()-1);
                if (gejm.getNaRedu()>=gejm.getBroj()) gejm.setNaRedu(gejm.getNaRedu()-1);
                unos.setText("");
                izbacivanje.dismiss();
                ok.setEnabled(true);

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //BAGUJE KAD IZBACIS IGRACA 1 pa 2 || 1 pa 3       1 pa 4 izbaci 1 i 2
                if (unos.getText().toString()==null){
                    toast("Unesi broj pored igraca");
                    return;
                }
                u[0] = Integer.parseInt(unos.getText().toString());
                if (u[0] >gejm.getBroj() || u[0] < 1){
                    toast("Ne valja broj");
                    return;
                }

                moze.setEnabled(true);
                ok.setEnabled(false);
                unos.setText("");

                String[] stringovi = tekstZaIzbacivanje.getText().toString().split("\n");
                String ispis = "";

                for (int i = 0; i < gejm.getBroj(); i++){

                    if (i+1== u[0]) continue;
                    ispis += stringovi[i] + "\n";
                }
                tekstZaIzbacivanje.setText(ispis);
//                if (gejm.getBroj()-brojevi.size()<3) ok.setEnabled(false);

            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////
        edit = new Dialog(this);
        edit.setContentView(R.layout.editigrace);
        edit.getWindow().setBackgroundDrawable(getDrawable((R.drawable.dialogbackground)));
        TextView tekstIzbaci = (TextView) edit.findViewById(R.id.textIzbaci);
        TextView tekstRedosled = (TextView) edit.findViewById(R.id.textRedosled);
        tekstIzbaci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.dismiss();
                tekstZaIzbacivanje.setText(gejm.toStringIgraci());


                izbacivanje.show();
            }
        });
        tekstRedosled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.dismiss();
                tekst2.setText(gejm.toStringIgraci());

                zamena.show();
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////////
        zamena = new Dialog(this);
        zamena.setContentView(R.layout.redosled);
        zamena.getWindow().setBackgroundDrawable(getDrawable((R.drawable.dialogbackground)));
        Button ok2 = (Button) zamena.findViewById(R.id.izviniOK);
        Button abort2 = (Button) zamena.findViewById(R.id.redosledAbort);
        Button moze2 = (Button) zamena.findViewById(R.id.redosledAjde);
        EditText unos2 = (EditText) zamena.findViewById(R.id.izviniUNOS);
        tekst2 = (TextView) zamena.findViewById(R.id.izvinitekst);
        List<Integer> red = new LinkedList<>();
        abort2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unos2.setText("");
                red.clear();
                moze2.setEnabled(false);
                ok2.setText("OK");
                zamena.dismiss();
            }
        });
        ok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                red.clear();
                String unos = (String) unos2.getText().toString();
                if (unos.length()!=gejm.getBroj()){
                    toast("Los unos");
                    return;
                }
                int u = Integer.parseInt(unos);
                while (u>0){
                    if (u%10>gejm.getBroj() || u%10==0 || red.contains(u%10)){
                        toast("Los unos");
                        return;
                    }
                    red.add(0, u%10);
                    u=u/10;
                }
                moze2.setEnabled(true);
                ok2.setText("Promeni");
                String ss = gejm.toStringIgraci(); //ovde roka exception
                String[] s = ss.split("\n");
                String string = "";
                for (int i = 0; i < red.size();i++){
                    string+=s[red.get(i)-1]+"\n";  //KRESUJE OVDE NEGDE
                }
                tekst2.setText(string);
                unos2.setText("");

            }
        });
        moze2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Babunlija[] b = new Babunlija[red.size()];
                for (int i = 0; i < red.size(); i++){
                    b[i] = new Babunlija("");
                    b[i].kopiraj(gejm.getBabunlije(red.get(i)-1));
                }
                for (int i = 0; i < red.size();i++){
                    gejm.getBabunlije(i).kopiraj(b[i]);
                }
                //gejm.setNaRedu(red.get(gejm.getNaRedu()-1)); da zelimo da isti lik baca sledeci
                gejm.setNaRedu(0);
                unos2.setText("");
                zamena.dismiss();
            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////////
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.finaledialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialogbackground));
        Button opcija1 = dialog.findViewById(R.id.da);
        Button opcija2 = dialog.findViewById(R.id.ne);
        Button izlaz = dialog.findViewById(R.id.izlaz);
        izlaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(1);
            }
        });
        opcija1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //BEZ CEPA
                //gejm.set
                rezim = 3;
                statistika.setVisibility(View.INVISIBLE);
                gejm.oziviSve();
                novoIzvini();
                dalje.setVisibility(View.INVISIBLE);
                statistika.setVisibility(View.INVISIBLE);
                ubo.setVisibility(View.VISIBLE);
                nije.setVisibility(View.VISIBLE);
                ispisBezCepa(gejm);
                rezervni2.kopiraj(gejm);
                rezervni1.kopiraj(gejm);
                rezervni.kopiraj(gejm);

                dialog.dismiss();
            }
        });
        opcija2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obojiUZlatno();
                dalje.setVisibility(View.INVISIBLE);
                statistika.setVisibility(View.INVISIBLE);
                ubo.setVisibility(View.VISIBLE);
                nije.setVisibility(View.VISIBLE);
                izvini.setVisibility(View.INVISIBLE);
                rezim = 4;
                ispis(gejm, 0);
                rezervni2.kopiraj(gejm);
                rezervni1.kopiraj(gejm);
                rezervni.kopiraj(gejm);
                dialog.dismiss();
            }
        });

        kraj = new Dialog(this);
        kraj.setContentView(R.layout.esisig);
        kraj.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialogbackground));
        Button d = kraj.findViewById(R.id.da);
        Button n = kraj.findViewById(R.id.ne);

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
               System.exit(1);
            }
        });
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kraj.dismiss();
            }
        });


        statistike = new Dialog(this);
        statistike.setContentView(R.layout.stats);
        statistike.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialogbackground));
        statistikaTekst = (TextView) statistike.findViewById(R.id.tekst);
        statistikaTekst2 = (TextView) statistike.findViewById(R.id.tekst2);
        statistikaTekst.setMovementMethod(new ScrollingMovementMethod());
        klik = (ImageView) statistike.findViewById(R.id.imageView);

        statistikaTekst2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statistikaTekst2.getText().charAt(1)=='O' || statistikaTekst2.getText().charAt(0) == 'Z'){
                    statistikaTekst2.setText(gejm.ispisiStatistikuNaslovPartija());
                    klik.setVisibility(View.INVISIBLE);
//                    statistikaTekst2.setTextColor(getResources().getColor(R.color.p1orange));
                    statistikaTekst.setText(gejm.ispisiStatistikuPartija());
                } else {
                    statistikaTekst2.setText(gejm.ispisiStatistikuNaslovRunda());
                    statistikaTekst.setText(gejm.ispisiStatistikuRunda());

                    if (rezim == 4){
                        statistikaTekst2.setText("Z L A T N A:");
//                        statistikaTekst2.setTextColor(getResources().getColor(R.color.gold));
                    }
                }
            }
        });




///////////////////////////////////////////////////////////////////////////

        gejm = new Partija(igraci, max, imaIzvini);
        rezervni = new Partija(igraci, max, imaIzvini);
        rezervni1 = new Partija(igraci, max, imaIzvini);
        rezervni2 = new Partija(igraci, max, imaIzvini);
        if (gejm.getBroj()>10) scoreboard.setTextSize(14);
        if (gejm.getBroj()>15) scoreboard.setTextSize(12);
        if (gejm.getBroj()>18) scoreboard.setTextSize(10);
        if (rezim == 4){
            obojiUZlatno();

            //pozadina.setBackgroundColor(Color.parseColor("#000000"));
        }
        ispis = (TextView) findViewById(R.id.scoreboard);
        ispis.setText(gejm.toString());
        mod.setText(ispisModa());
        uFlasi.setText("U FLASI: 0");
        baca.setText("BACA: " + gejm.getBabunlije(0).getIme());
        izvini.setVisibility(View.INVISIBLE);
        postaviFlasu();
        if (rezim == 3) novoIzvini();
        if (rezim==3) ispisBezCepa(gejm);


//        ubo.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                return false;
//            }
//        });

        ubo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    rezervni = (Partija) gejm.clone();
//                } catch (CloneNotSupportedException e) {
//                    e.printStackTrace();
//                }
                jelTrebaIzviniDaBudeVidljivo = izvini.isShown();

                if (rezervni1!=null) rezervni2.kopiraj(rezervni1);
                if (rezervni!=null) rezervni1.kopiraj(rezervni);
                rezervni.kopiraj(gejm);

                undo.setVisibility(View.VISIBLE);
                if (rezim == 3){
                    gejm.skloniDoktora(); //FALI JOS ISPIS
                    gejm.getBabunlije(gejm.getNaRedu()).setDoktor(true);
                    gejm.sledeci();
                    ispisBezCepa(gejm);
                    return;
                }
                pomoc.setVisibility(View.INVISIBLE);
                gejm.ubo();
                ubo();
                ispis(gejm, 0);
            }
        });
        ubo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

//                scoreboard.setText(gejm.ispisiStatistiku1());
//                scoreboard.setTextSize(10);

                return false;
            }
        });





//        uFlasi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int y = 3;
//                scoreboard.setText(Double.toString(gejm.getBabunlije(0).hitRate));
//            }
//        });
        nije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    rezervni = (Partija) gejm.clone();
//                } catch (CloneNotSupportedException e) {
//                    e.printStackTrace();
//                }
                jelTrebaIzviniDaBudeVidljivo = izvini.isShown();
                if (rezervni1!=null) rezervni2.kopiraj(rezervni1);
                if (rezervni!=null) rezervni1.kopiraj(rezervni);
                rezervni.kopiraj(gejm);
                undo.setVisibility(View.VISIBLE);

                if (rezim ==3){
                    gejm.sledeci();
                    ispisBezCepa(gejm);
                    return;
                }
                if (gejm.isSabajla()&&!gejm.getBabunlije(gejm.getNaRedu()).isIzvini()) sabajla(gejm);  /////////////////////////

                if (gejm.getBabunlije(gejm.getNaRedu()).getPoeni() + gejm.getuFlasi() >= max && !gejm.getBabunlije(gejm.getNaRedu()).isIzvini()) pomoc.setVisibility(View.VISIBLE);
                else pomoc.setVisibility(View.INVISIBLE);

                if (gejm.nije())  nije(); else ubo(); /////////////////////////////
                if (gejm.vibracija){
                    vibriraj(500);
                    gejm.vibracija = false;
                }
                if (gejm.isTraje())  {
                    ispis(gejm, 1);
                    if (gejm.getNubKrug()==gejm.getBroj()) toast("Nub krug");
                    return;
                }
                 //ovde ispis mora nesto drugo


                ispisKraj(gejm);
                gejm.setTraje(true);
                statistika.setVisibility(View.VISIBLE);
                if (!gejm.getBabunlije(gejm.getPoslednjiIgrac()).isIzvini()) pomoc.setVisibility(View.INVISIBLE);
                if (rezim!=4) dalje.setVisibility(View.VISIBLE);
                izbaci.setVisibility(View.VISIBLE);

                sakrijFlasu();
                ubo.setVisibility(View.INVISIBLE);
                nije.setVisibility(View.INVISIBLE);
                izvini.setVisibility(View.GONE);

                //undo.setVisibility(View.INVISIBLE);
                undo.setBackgroundColor(Color.parseColor("#2C394B"));
                if (rezim == 4){
                    gejm.setTraje(false);
                    vibriraj(750);
                    ispis.setText(gejm.getBabunlije(gejm.getPoslednjiIgrac()).getIme() + "  B O G    O T A C");
                    ubo.setVisibility(View.INVISIBLE);
                    nije.setVisibility(View.INVISIBLE);
                }

            }
        });

        izvini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    rezervni = (Partija) gejm.clone();
//                } catch (CloneNotSupportedException e) {
//                    e.printStackTrace();
//                }
                jelTrebaIzviniDaBudeVidljivo = izvini.isShown();
                if (rezervni1!=null) rezervni2.kopiraj(rezervni1);
                if (rezervni!=null) rezervni1.kopiraj(rezervni);
                rezervni.kopiraj(gejm);
                undo.setVisibility(View.VISIBLE);
                if (rezim == 3) {

                    ubo.setVisibility(View.INVISIBLE);
                    nije.setVisibility(View.INVISIBLE);
                    izvini.setVisibility(View.INVISIBLE);
                    statistika.setVisibility(View.VISIBLE);
                    sakrijFlasu();
                    scoreboard.setText(gejm.bezCepaKraj());
                    return;
                }
                /////////////
                gejm.izvini();
                izvini();
                ispis(gejm, 0);
            }
        });

        jusles.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                System.exit(1);
                return false;
            }
        });
        statistika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                statistikaTekst.setText(gejm.ispisiStatistikuRunda());
                statistikaTekst2.setText(gejm.ispisiStatistikuNaslovRunda());
                if (rezim==4) {
//                    statistikaTekst2.setTextColor(0xFFD700);
                    statistikaTekst2.setText("Z L A T N A:");
                }

                statistike.show();
            }
        });
        jusles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rezim<3){
                    dialog.show();


                }
                else kraj.show();


                //Toast.makeText(getApplication().getBaseContext(), "Ovo dugme ne radi nista", Toast.LENGTH_SHORT).show();
            }
        });
        izbaci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (gejm.getBroj()==2){
                    toast("???????????????");
                    return;
                }
                edit.show();

            }
        });

        dalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dalje.setVisibility(View.GONE);
                ubo.setVisibility(View.VISIBLE);
                nije.setVisibility(View.VISIBLE);
                izvini.setVisibility(View.INVISIBLE);
                statistika.setVisibility(View.INVISIBLE);
                izbaci.setVisibility(View.INVISIBLE);
                gejm.resetujStatistikuIzRunde();
                rezervni2.kopiraj(gejm);
                rezervni1.kopiraj(gejm);
                rezervni.kopiraj(gejm);
                ispis(gejm, 0);
            }
        });

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gejm.kopiraj(rezervni);
                rezervni.kopiraj(rezervni1);
                rezervni1.kopiraj(rezervni2);
//                try {
//                    gejm = (Partija) rezervni.clone();
//                } catch (CloneNotSupportedException e) {
//                    e.printStackTrace();
//                }
                ispis(gejm, 0);
                ubo.setVisibility(View.VISIBLE);
                nije.setVisibility(View.VISIBLE);
                postaviFlasu();
                statistika.setVisibility(View.INVISIBLE);
                izbaci.setVisibility(View.INVISIBLE);
                dalje.setVisibility(View.GONE);
                if (jelTrebaIzviniDaBudeVidljivo)
                    izvini.setVisibility(View.VISIBLE);
                else
                    izvini.setVisibility(View.INVISIBLE);
            }
        });





    }

    private void sakrijFlasu() {
        flasaOrange.setVisibility(View.INVISIBLE);
        flasaOrange1.setVisibility(View.INVISIBLE);
        flasaOrange2.setVisibility(View.INVISIBLE);
        flasaOrange3.setVisibility(View.INVISIBLE);
        flasaOrange4.setVisibility(View.INVISIBLE);
        flasaOrange5.setVisibility(View.INVISIBLE);
        flasaOrange6.setVisibility(View.INVISIBLE);
        flasaZlatna.setVisibility(View.INVISIBLE);
        flasaZlatna1.setVisibility(View.INVISIBLE);
        flasaZlatna2.setVisibility(View.INVISIBLE);
        flasaZlatna3.setVisibility(View.INVISIBLE);
        flasaZlatna4.setVisibility(View.INVISIBLE);
        flasaZlatna5.setVisibility(View.INVISIBLE);
        flasaZlatna6.setVisibility(View.INVISIBLE);
        flasa1.setVisibility(View.INVISIBLE);
        flasa2.setVisibility(View.INVISIBLE);
        flasa3.setVisibility(View.INVISIBLE);
        flasa4.setVisibility(View.INVISIBLE);
        flasa5.setVisibility(View.INVISIBLE);
        flasa6.setVisibility(View.INVISIBLE);
        flasa0.setVisibility(View.INVISIBLE);
    }
    public void postaviFlasu(){
        int x = gejm.getuFlasi();
        sakrijFlasu();


        if (x==0 || rezim == 3){

            flasa0.setVisibility(View.VISIBLE);
            return;
        }

        if (x==1){
            if (gejm.getBabunlije(gejm.getNaRedu()).getPoeni()+gejm.getuFlasi()<gejm.getMax()) {
                if (rezim==4) flasa1.setVisibility(View.VISIBLE);
                else flasa1.setVisibility(View.VISIBLE);
            } else {
                if (rezim==4) flasaZlatna1.setVisibility(View.VISIBLE);
                else flasaOrange1.setVisibility(View.VISIBLE);
            }

            return;
        }
        if (x==2){
            if (gejm.getBabunlije(gejm.getNaRedu()).getPoeni()+gejm.getuFlasi()<gejm.getMax()) {
                if (rezim==4) flasa2.setVisibility(View.VISIBLE);
                else flasa2.setVisibility(View.VISIBLE);
            } else {
                if (rezim==4) flasaZlatna2.setVisibility(View.VISIBLE);
                else flasaOrange2.setVisibility(View.VISIBLE);
            }

            return;
        }
        if (x==3){
            if (gejm.getBabunlije(gejm.getNaRedu()).getPoeni()+gejm.getuFlasi()<gejm.getMax()) {
                if (rezim==4) flasa3.setVisibility(View.VISIBLE);
                else flasa3.setVisibility(View.VISIBLE);
            } else {
                if (rezim==4) flasaZlatna3.setVisibility(View.VISIBLE);
                else flasaOrange3.setVisibility(View.VISIBLE);
            }

            return;
        }

        if (x==4){
            if (gejm.getBabunlije(gejm.getNaRedu()).getPoeni()+gejm.getuFlasi()<gejm.getMax()) {
                if (rezim==4) flasa4.setVisibility(View.VISIBLE);
                else flasa4.setVisibility(View.VISIBLE);
            } else {
                if (rezim==4) flasaZlatna4.setVisibility(View.VISIBLE);
                else flasaOrange4.setVisibility(View.VISIBLE);
            }

            return;
        }
        if (x==5){
            if (gejm.getBabunlije(gejm.getNaRedu()).getPoeni()+gejm.getuFlasi()<gejm.getMax()) {
                if (rezim==4) flasa5.setVisibility(View.VISIBLE);
                else flasa5.setVisibility(View.VISIBLE);
            } else {
                if (rezim==4) flasaZlatna5.setVisibility(View.VISIBLE);
                else flasaOrange5.setVisibility(View.VISIBLE);
            }

            return;
        }
        if (x==6){
            if (gejm.getBabunlije(gejm.getNaRedu()).getPoeni()+gejm.getuFlasi()<gejm.getMax()) {
                if (rezim==4) flasa6.setVisibility(View.VISIBLE);
                else flasa6.setVisibility(View.VISIBLE);
            } else {
                if (rezim==4) flasaZlatna6.setVisibility(View.VISIBLE);
                else flasaOrange6.setVisibility(View.VISIBLE);
            }

            return;
        }
        if (x>6){

            if (rezim == 4) flasaZlatna.setVisibility(View.VISIBLE);
            else
                flasaOrange.setVisibility(View.VISIBLE);

        }
    }


    private void sabajla(Partija gejm) {

        if (gejm.getuFlasi()>=gejm.getMax()){
            toast("od sabajle");
            return;
        }

    }

    private void novoIzvini() {
        izvini.setText("PRAZNA");
        izvini.setVisibility(View.VISIBLE);
    }

    private void obojiUZlatno() {
        scoreboard.setBackgroundColor(Color.parseColor("#FFD700"));
        scoreboard.setTextColor(Color.parseColor("#082032"));


        ubo.setBackground(getResources().getDrawable(R.drawable.goldenbtn));
        nije.setBackground(getResources().getDrawable(R.drawable.goldenbtn));
        statistika.setBackground(getResources().getDrawable(R.drawable.goldenbtn));
        izvini.setBackground(getResources().getDrawable(R.drawable.goldenbtn));

//        ubo.setBackgroundColor(Color.parseColor("#FFD700"));
//        ubo.setBackgroundTintMode(null);
//        nije.setBackgroundColor(Color.parseColor("#FFD700"));
//        ubo.setBackgroundColor(0x082032);
//        nije.setBackgroundColor(0x082032);

    }

    public void ubo(){
        izvini.setVisibility(View.INVISIBLE);
    }

    public void nije(){
        izvini.setVisibility(View.VISIBLE);
    }

    public void izvini(){
        izvini.setVisibility(View.INVISIBLE);
    }

    public void ispisKraj(Partija gejm){
        ispis.setText(gejm.getTekstZaIspis());
        if (rezim == 4)
        mod.setText(ispisModa());
        String pom = "U FLASI: 0";
        uFlasi.setText(pom);
        pom = "BACA: " + gejm.getBabunlije(gejm.getNaRedu()).getIme();
        baca.setText(pom);
        izvini.setVisibility(View.INVISIBLE);
    }
    public String ispisModa(){

        if (rezim == 1) return "MOD: do 1 bez izvini";
        if (rezim == 2) return "MOD: do 1 izvini";
        if (rezim == 3) return "MOD: bez cepa";
        if (rezim == 4) return "MOD: ZLATNA";
        return "MOD: Klasika";
    }
    public void ispisBezCepa(Partija gejm){
        //                                                                  fali ovde
        ispis.setText(gejm.toString(1));
        mod.setText("MOD: bez cepa");
        uFlasi.setText("U FLASI nema vode");

        baca.setText("BACA: " + gejm.getBabunlije(gejm.getNaRedu()).getIme());
    }

    public void ispis(Partija gejm, int x){

        ispis.setText(gejm.toString());
        mod.setText(ispisModa());
        String pom = "U FLASI: " + Integer.toString(gejm.getuFlasi());
        if (gejm.getNubKrug()>=gejm.getBroj()) {
            pom = "U FLASI NUB KRUG";
            if (gejm.getNubKrug()==gejm.getBroj())
            vibriraj(100);
        }
        if (gejm.getNubKrug()>=gejm.getBroj()*2 && gejm.getNubKrug()>5) {
            pom = "U FLASI I DALJE NUB KRUG";
            if (gejm.getNubKrug() == gejm.getBroj()*2)
            vibriraj(200);
        }            // && gejm.getBabunlije(gejm.getPoslednjiIgrac()).isIzvini() && gejm.getProsuto()>0
        if (x==1 && izvini.getVisibility()==View.VISIBLE) pom = pom + " (Bilo " + gejm.getProsuto()+ ")";
        uFlasi.setText(pom);
               pom = "BACA: " + gejm.getBabunlije(gejm.getNaRedu()).getIme();
        baca.setText(pom);
        postaviFlasu();
    }






    public void toast(String poruka){
        Toast.makeText(getApplication().getBaseContext(), poruka, Toast.LENGTH_SHORT).show();
    }
    public void vibriraj(int x){
        if (dalDaVibrira)
        vibrator.vibrate(x);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Sigurno?")
                .setPositiveButton("Da", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                        //close();


                    }
                })
                .setNegativeButton("Ne", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }

}