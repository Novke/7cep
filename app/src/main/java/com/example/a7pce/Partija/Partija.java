package com.example.a7pce.Partija;

import android.os.Vibrator;

import com.example.a7pce.MainActivity2;

import java.util.LinkedList;
import java.util.List;

public class Partija {

    private Babunlija[] babunlije;
    private String sef = null;
    private int broj;
    private boolean traje = true;
    private int brojzivih;
    private int naRedu = 0;
    private int uFlasi = 0;
    private int prosuto = 0;
    private int poslednjiIgrac = 0;
    private int max = 7;
    private boolean sabajla = false;
    private String tekstZaIspis = "";
    private boolean imaIzvini;
    public boolean vibracija = false;

    /////////////////////////////////////////// STATISTIKE
    private int ubodeneZaredom = 0;
    private int nubKrug = 0;
    private int odigrano = 0;
    private int odigranoURundi = 0;
    private int maxUbodeneZaredom = 0;
    private int maxNubKrug = 0;
    private int bacenihFlasa = 0;
    private int maxUbodeneZaredomURundi = 0;
    private int maxNubKrugURundi = 0;
    private int bacenihFlasaURundi = 0;
    private double ukupanHitRate = 0;
    private int ubodenihFlasa = 0;
    private double hitRateURundi = 0;
    private int ubodenihFlasaURundi = 0;
    private int brojNubKrugova = 0;
    private int brojNubKrugovaURundi = 0;

    /////////////////////////////////////////// SVE IZNAD OVOGA CE KOPIRATI ONAKO KAKO BOG ZAPOVEDA






    public String toStringSkor() {
        int index = 0, maks = -1;
        odigrano++;


        for (int i = 0; i < broj; i++) {
            if (babunlije[i].getSkor()>maks) {
                index = i;
                maks = babunlije[i].getSkor();
            }
        }
        if (odigrano > 4 && maks + maks> odigrano && broj > 2)
            return maks + ", " + babunlije[index].getIme() + " ŠEF IGRICE \n" + (odigrano-maks) + ", Svi ostali";

        List<Integer> lista = new LinkedList<>();
        int[] niz = new int[broj];
        int j = 1;
        niz[0]=maks;
        lista.add(index);
        while (j<broj) {
            maks = -1;
            for (int i = 0; i < broj; i++) {
                if (!lista.contains(i) && babunlije[i].getSkor()>maks) {
                    index = i;
                    maks = babunlije[i].getSkor();
                }
            }
            lista.add(index);
            j++;
        } //popunjena lista treba ispisati

        String s = "";
        String fse = "";

        for (int i = 0; i < broj; i++) {
           if (lista.get(i)==naRedu) fse = "         ŠEF"; /////////////////////////////////////////
            else fse = "";

            s = s + babunlije[lista.get(i)].toStringSkor() + fse + "\n";
            if (broj<6) s += "\n";
        }
        return s;
    }
    public String bezCepaKraj(){
        for (int i = 0; i < broj; i++){
            if (babunlije[i].getDoktor()>0){
                return babunlije[i].toString(1);
            }
        }
        return "Nema doktora";
    }
    @Override
    public String toString() {
        if (babunlije == null || babunlije.length == 0) throw new RuntimeException("toString za babunlije");
        String ispis = "";
        for (int i = 0; i < broj; i++){
            ispis = ispis + babunlije[i].toString();
            if (i == naRedu) ispis = ispis + " <<<<<<<";
            ispis = ispis + "\n";
            if (broj<=5) ispis = ispis + "\n";
        }
        return ispis;
    }

    public String toString(int x){

            if (babunlije == null || babunlije.length == 0) throw new RuntimeException("toString za babunlije");
            String ispis = "";
            for (int i = 0; i < broj; i++){
                ispis = ispis + babunlije[i].toString(1);
                if (i == naRedu) ispis = ispis + "<<<<<<<";
                ispis = ispis + "\n";
                if (broj<=5) ispis = ispis + "\n";
            }
            return ispis;
        }

    public String toStringIgraci(){
        String string = "";
        for (int i = 0; i< broj; i++){
            string += getBabunlije(i).getIme() + ": [" + Integer.toString(i+1) +"]\n";
        }
        return string;
    }



    public Partija(String igraci, int max, boolean imaIzvini) {
        String[] babuni = igraci.split("\n");
        this.broj = babuni.length;
        this.max = max;
        this.imaIzvini = imaIzvini;
        this.brojzivih = this.broj;

        Babunlija[] x = new Babunlija[this.broj];
        for (int i = 0; i < broj; i++){
            x[i] = new Babunlija(babuni[i]);
          //x[i].setIme(babuni[i]);
            x[i].setIzvini(imaIzvini);
        }
        babunlije = x;
    }

    public void kopiraj(Partija p){

         for (int i = 0; i < broj; i++){
             babunlije[i].kopiraj(p.getBabunlije(i));
         }
         sef = null;

         traje = p.isTraje();
         brojzivih = p.getBrojzivih();
         naRedu = p.getNaRedu();
         uFlasi = p.getuFlasi();
         prosuto = p.getProsuto();
         poslednjiIgrac = p.getPoslednjiIgrac();

         sabajla = p.isSabajla();

         tekstZaIspis = p.getTekstZaIspis();



         ubodeneZaredom = p.getUbodeneZaredom();
         nubKrug = p.getNubKrug();
         odigrano = p.getOdigrano();
         odigranoURundi = p.getOdigranoURundi();
         maxUbodeneZaredom = p.getMaxUbodeneZaredom();
         maxNubKrug = p.getMaxNubKrug();

        bacenihFlasa = p.getBacenihFlasa();
        maxUbodeneZaredomURundi = p.getMaxUbodeneZaredomURundi();
        maxNubKrugURundi = p.getMaxNubKrugURundi();
        bacenihFlasaURundi = p.getBacenihFlasaURundi();

        ukupanHitRate = p.getUkupanHitrate();
        ubodenihFlasa = p.getUbodenihFlasa();
        hitRateURundi = p.getHitRateURundi();
        ubodenihFlasaURundi = p.getUbodenihFlasaURundi();

        brojNubKrugova = p.getBrojNubKrugovaURundi();
        brojNubKrugovaURundi = p.getBrojNubKrugovaURundi();
    }


    public static String ispis(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }

    public void skloniDoktora(){
        for (int i = 0; i < broj; i++){
            if (i==naRedu) continue;
            babunlije[i].skiniDoktora();
        }
    }

    public int getBrojNubKrugova(){
        return brojNubKrugova;
    }
    public int getBrojNubKrugovaURundi(){
        return brojNubKrugovaURundi;
    }
    public double getHitRateURundi(){
        return hitRateURundi;
    }
    public double getUkupanHitrate(){
        return ukupanHitRate;
    }
    public int getUbodenihFlasaURundi(){
        return ubodenihFlasaURundi;
    }
    public int getUbodenihFlasa(){
        return ubodenihFlasa;
    }

    public int getBacenihFlasa(){
        return bacenihFlasa;
    }
    public int getMaxUbodeneZaredomURundi(){
        return maxUbodeneZaredomURundi;
    }
    public int getMaxNubKrugURundi(){
        return maxNubKrugURundi;
    }
    public int getBacenihFlasaURundi(){
        return bacenihFlasaURundi;
    }

    public int getUbodeneZaredom(){
        return ubodeneZaredom;
    }
    public int getOdigranoURundi(){
        return odigranoURundi;
    }
    public int getMaxUbodeneZaredom(){
        return maxUbodeneZaredom;
    }
    public int getMaxNubKrug(){
        return maxNubKrug;
    }

    public String getTekstZaIspis(){
        return tekstZaIspis;
    }

    public int getPoslednjiIgrac() {
        return poslednjiIgrac;
    }

    public void setPoslednjiIgrac(int poslednjiIgrac) {
        this.poslednjiIgrac = poslednjiIgrac;
    }

    public void setBabunlije(Babunlija[] babunlije) {
        this.babunlije = babunlije;
    }

    public boolean isSabajla(){
        return sabajla;
    }

    public void setSef(String sef) {
        this.sef = sef;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public void setTraje(boolean traje) {
        this.traje = traje;
    }

    public void setBrojzivih(int brojzivih) {
        this.brojzivih = brojzivih;
    }

    public void setNaRedu(int naRedu) {
        this.naRedu = naRedu;
    }

    public void setuFlasi(int uFlasi) {
        this.uFlasi = uFlasi;
    }

    public void setProsuto(int prosuto) {
        this.prosuto = prosuto;
    }

    public void setNubKrug(int nubKrug) {
        this.nubKrug = nubKrug;
    }

    public void nubKrug(){nubKrug++;}

    public void setMax(int max) {
        this.max = max;
    }

    public void setOdigrano(int odigrano) {
        this.odigrano = odigrano;
    }

    public Babunlija[] getBabunlije() {
        return babunlije;
    }

    public Babunlija getBabunlije(int i){
        if (i >= babunlije.length) throw new RuntimeException("Ne postoji ova babunlija");

        return babunlije[i];
    }

    public String getSef() {
        return sef;
    }

    public int getBroj() {
        return broj;
    }

    public boolean isTraje() {
        return traje;
    }

    public int getBrojzivih() {
        return brojzivih;
    }

    public int getNaRedu() {
        return naRedu;
    }

    public int getuFlasi() {
        return uFlasi;
    }

    public int getProsuto() {
        return prosuto;
    }

    public int getNubKrug() {
        return nubKrug;
    }

    public int getMax() {
        return max;
    }

    public int getOdigrano() {
        return odigrano;
    }

    public void sledeci(){
        naRedu++;
        if (naRedu >= broj) naRedu = 0;
        if (!babunlije[naRedu].isZiv()) sledeci();
    }
    public void ubo(){
        if (odigranoURundi++==0) sabajla=true; ///////////////////////////////////
        uFlasi++;
        babunlije[naRedu].ubo();
        nubKrug=0;
        ubodeneZaredom++;
        bacenihFlasa++;
        bacenihFlasaURundi++;
        if (ubodeneZaredom > maxUbodeneZaredom) maxUbodeneZaredom = ubodeneZaredom;
        if (ubodeneZaredom > maxUbodeneZaredomURundi) maxUbodeneZaredomURundi = ubodeneZaredom;
        poslednjiIgrac = naRedu;
        ubodenihFlasa++;
        ukupanHitRate = ((double) ubodenihFlasa) / bacenihFlasa;
        ubodenihFlasaURundi++;
        hitRateURundi = ((double) ubodenihFlasaURundi) / bacenihFlasaURundi;
        sledeci();
    }

    public boolean nije(){
        if (!getBabunlije(naRedu).isIzvini())sabajla = false; /////////////////////////////////////////////
        odigranoURundi++;
        nubKrug++;
        if (nubKrug % brojzivih == 0){
            brojNubKrugova++;
            brojNubKrugovaURundi++;
        }
        bacenihFlasa++;
        ukupanHitRate = ((double) ubodenihFlasa) / bacenihFlasa;
        bacenihFlasaURundi++;
        hitRateURundi = ((double) ubodenihFlasaURundi) / bacenihFlasaURundi;
        if (nubKrug>maxNubKrug) maxNubKrug = nubKrug;
        if (maxNubKrugURundi<nubKrug) maxNubKrugURundi = nubKrug;
        int aaa = ubodeneZaredom;
        ubodeneZaredom=0;
        babunlije[naRedu].omasio();
        babunlije[naRedu].dodajPopijeno(uFlasi);
        if (uFlasi == 0){
            sledeci();
            prosuto=uFlasi;
            return false; //bilo 0 u flasi
        }
        if (babunlije[naRedu].getPoeni() + uFlasi >= max) {

            if (babunlije[naRedu].isIzvini())  {
                izvini(naRedu); //ako bi ispao
                ubodeneZaredom=aaa;
                vibracija = true;
            }
            else {
                babunlije[naRedu].setPoeni(babunlije[naRedu].getPoeni()+uFlasi);
                uFlasi=0;
                babunlije[naRedu].setZiv(false);

                babunlije[poslednjiIgrac].ubioNekog();
                brojzivih--;
                if (brojzivih <2) {
                    tekstZaIspis=kraj();
                    //ovde dolazi tekst
                    prosuto=uFlasi;
                    return false; //ako je ostao samo 1 lik
                }

                sledeci();
            }
            prosuto=uFlasi;
            return false; //nista nije uradio
        }


        babunlije[naRedu].setPoeni(babunlije[naRedu].getPoeni()+uFlasi);
        prosuto = uFlasi;
        uFlasi=0;
        poslednjiIgrac = naRedu;

        if (!babunlije[naRedu].isIzvini()) {
            sledeci();
            return false;
        }

        sledeci();
        return true;
    }

    public String kraj() {
        String tekst;
        /////////////




        for (int i = 0; i < broj; i++) {

            if (babunlije[i].isZiv()) {
                babunlije[i].setSkor(babunlije[i].getSkor() + 1);
                naRedu = i;
            }


            babunlije[i].setPoeni(0);
            babunlije[i].setZiv(true);
            if (imaIzvini) babunlije[i].setIzvini(true);

        }
        tekst = toStringSkor();

            brojzivih = broj;
            uFlasi = 0;
            prosuto = 0;
            nubKrug = 0;

            traje = false;


        return tekst;
    }

    public void dodajPopijenoPobedniku(){
        babunlije[poslednjiIgrac].dodajPopijeno(babunlije[poslednjiIgrac].getPoeni());
    }

    public void resetujStatistikuIzRunde(){
        sabajla=true;

        bacenihFlasaURundi=0;
        maxNubKrugURundi=0;
        maxUbodeneZaredomURundi=0;
        ubodenihFlasaURundi=0;
        hitRateURundi = 0;
        brojNubKrugovaURundi = 0;
        for (int i = 0; i < broj; i++){
            babunlije[i].ocistiStatistike();
        }
    }

    public void izvini(int i){
//        babunlije[i].setIzvini(false); dodato u funkciji dole
        babunlije[i].oduzmiPopijeno(uFlasi);
        babunlije[i].izvini();

        nubKrug--;
    }
    public void izvini() {
        babunlije[poslednjiIgrac].oduzmiPopijeno(prosuto);

        babunlije[poslednjiIgrac].setIzvini(false);
        naRedu = poslednjiIgrac;
        babunlije[naRedu].setPoeni(babunlije[naRedu].getPoeni()-prosuto);
        uFlasi = prosuto;
        ubodeneZaredom = uFlasi;
    }


    public void oziviSve() {
        for (Babunlija z:babunlije
             ) { z.setZiv(true);

        }
    }
    public String ispisiStatistikuNaslovRunda(){
        String ispis = "";

        ispis = "POSLEDNJA RUNDA:";

        return ispis;
    }

    public String ispisiStatistikuRunda() {
        String ispis = "\n"; // "POSLEDNJA RUNDA:\n";
        ispis = ispis + "Ukupan hitrate:   "+ ispisDouble(hitRateURundi) +"\n";
        ispis = ispis + "Bacenih flasa:   " + bacenihFlasaURundi + "\n";
        ispis = ispis + "Najvise u flasi:   " + maxUbodeneZaredomURundi + "\n";
        ispis = ispis + "Broj nubkrugova:   " + brojNubKrugovaURundi + "\n";
        ispis = ispis + "Najveci nubkrug:   " + maxNubKrugURundi + "\n";
        ispis = ispis + "\n============================\n\n";

        ispis = ispis + statistikaHitRateURundi() + "\n\n";
        ispis = ispis + statistikaUbistvaURundi() + "\n\n";
        ispis = ispis + statistikaUbodeneZaredomURundi() + "\n\n";
        ispis = ispis + statistikaOmaseneZaredomURundi() + "\n\n";
        ispis = ispis + statistikaUbodeneURundi() + "\n\n";
        ispis = ispis + statistikaBaceneFlaseURundi() + "\n\n";



        ispis = ispis + "\n\n============================\n";
        ispis = ispis + "============================\n\n";





        return ispis;
    }

    public String ispisiStatistikuNaslovPartija(){
        String ispis = "";


        ispis = ispis + "P A R T I J A:";


        return ispis;
    }

    public String ispisiStatistikuPartija(){
        String ispis = "";

        ispis = ispis + "Odigrano: " + odigrano +"\n";
        ispis = ispis + "Ukupan hitrate:   "+ ispisDouble(ukupanHitRate) +"\n";
        ispis = ispis + "Bacenih flasa:   " + bacenihFlasa + "\n";
        ispis = ispis + "Najvise u flasi:   " + maxUbodeneZaredom + "\n";
        ispis = ispis + "Broj nubkrugova:   " + brojNubKrugova + "\n";
        ispis = ispis + "Najveci nubkrug:   " + maxNubKrug + "\n\n";
        ispis = ispis + "============================\n\n";

        ispis = ispis + statistikaHitRate() + "\n\n";
        ispis = ispis + statistikaUbistva() + "\n\n";
        ispis = ispis + statistikaUbodeneZaredom() + "\n\n";
        ispis = ispis + statistikaOmaseneZaredom() + "\n\n";
        ispis = ispis + statistikaUkupnoPopijeno() + "\n\n";
        ispis = ispis + statistikaUbodene() + "\n\n";
        ispis = ispis + statistikaBaceneFlase() + "\n\n";

        ispis = ispis + "============================\n";
        ispis = ispis + "============================\n\n";


        return ispis;
    }

    private String statistikaBaceneFlaseURundi() {
        List<Integer> sort = new LinkedList<>();
        String ispis="BACENIH:   ";

        for (int j = 0; j < broj; j++) {
            int max = 0, index = -1;
            for (int i = 0; i < broj; i++) {
                if (babunlije[i].getBaceneURundi() > max && !sort.contains(i)) { //////////////////////////////////////////////////////////////////////
                    max = babunlije[i].getBaceneURundi();
                    index = i;
                }
            }
            if (index == -1) continue;
            ispis = ispis + babunlije[index].getIme() + ": " + max;
            if (j!=broj-1) ispis = ispis + "; ";
            sort.add(index);
        }
        return ispis;
    }

    private String statistikaUbodeneURundi() {
        List<Integer> sort = new LinkedList<>();
        String ispis="UBODENE:   ";

        for (int j = 0; j < broj; j++) {
            int max = 0, index = -1;
            for (int i = 0; i < broj; i++) {
                if (babunlije[i].getBrojUbodenihURundi() > max && !sort.contains(i)) { //////////////////////////////////////////////////////////////////////
                    max = babunlije[i].getBrojUbodenihURundi();
                    index = i;
                }
            }
            if (index == -1) continue;
            ispis = ispis + babunlije[index].getIme() + ": " + max;
            if (j!=broj-1) ispis = ispis + "; ";
            sort.add(index);
        }
        return ispis;
    }

    private String statistikaOmaseneZaredomURundi() {
        List<Integer> sort = new LinkedList<>();
        String ispis="MISS STREAK:   ";

        for (int j = 0; j < broj; j++) {
            int max = 0, index = -1;
            for (int i = 0; i < broj; i++) {
                if (babunlije[i].getMaxOmasenihZaredomURundi() > max && !sort.contains(i)) { //////////////////////////////////////////////////////////////////////
                    max = babunlije[i].getMaxOmasenihZaredomURundi();
                    index = i;
                }
            }
            if (index == -1) continue;
            ispis = ispis + babunlije[index].getIme() + ": " + max;
            if (j!=broj-1) ispis = ispis + "; ";
            sort.add(index);
        }
        return ispis;
    }

    private String statistikaUbodeneZaredomURundi() {
        List<Integer> sort = new LinkedList<>();
        String ispis="HITSTREAK:   ";

        for (int j = 0; j < broj; j++) {
            int max = 0, index = -1;
            for (int i = 0; i < broj; i++) {
                if (babunlije[i].getMaxUbodenihZaredomURundi() > max && !sort.contains(i)) { //////////////////////////////////////////////////////////////////////
                    max = babunlije[i].getMaxUbodenihZaredomURundi();
                    index = i;
                }
            }
            if (index == -1) continue;
            ispis = ispis + babunlije[index].getIme() + ": " + max;
            if (j!=broj-1) ispis = ispis + "; ";
            sort.add(index);
        }
        return ispis;
    }

    private String statistikaUbistvaURundi() {
        List<Integer> sort = new LinkedList<>();
        String ispis="UBISTAVA:   ";

        for (int j = 0; j < broj; j++) {
            int max = 0, index = -1;
            for (int i = 0; i < broj; i++) {
                if (babunlije[i].getBrojUbistavaURundi() > max && !sort.contains(i)) { //////////////////////////////////////////////////////////////////////
                    max = babunlije[i].getBrojUbistavaURundi();
                    index = i;
                }
            }
            if (index == -1) continue;
            ispis = ispis + babunlije[index].getIme() + ": " + max;
            if (j!=broj-1) ispis = ispis + "; ";
            sort.add(index);
        }
        return ispis;
    }

    private String statistikaHitRateURundi() {
        List<Integer> sort = new LinkedList<>();
        String ispis="HITRATE:   ";

        for (int j = 0; j < broj; j++) {
            double max = 0;
                    int index = -1;
            for (int i = 0; i < broj; i++) {
                if (babunlije[i].getHitRateURundi() > max && !sort.contains(i)) {
                    max = babunlije[i].getHitRateURundi();
                    index = i;
                }
            }
            if (index == -1) continue;
            ispis = ispis + babunlije[index].getIme() + ": " + ispisDouble(max);
            if (j!=broj-1) ispis = ispis + "; ";
            sort.add(index);
        }
        return ispis;
    }

    private String statistikaUkupnoPopijeno() {
        List<Integer> sort = new LinkedList<>();
        String ispis="PRIMLJENO POENA:   ";

        for (int j = 0; j < broj; j++) {
            int max = 0, index = -1;
            for (int i = 0; i < broj; i++) {
                if (babunlije[i].getPopijeno() > max && !sort.contains(i)) { //////////////////////////////////////////////////////////////////////
                    max = babunlije[i].getPopijeno();
                    index = i;
                }
            }
            if (index == -1) continue;
            ispis = ispis + babunlije[index].getIme() + ": " + max;
            if (j!=broj-1) ispis = ispis + "; ";
            sort.add(index);
        }
        return ispis;
    }




    private String statistikaBaceneFlase() {

        List<Integer> sort = new LinkedList<>();
        String ispis="BACENIH:   ";

        for (int j = 0; j < broj; j++) {
            int max = 0, index = -1;
            for (int i = 0; i < broj; i++) {
                if (babunlije[i].getBrojBacenihFlasa() > max && !sort.contains(i)) {
                    max = babunlije[i].getBrojBacenihFlasa();
                    index = i;
                }
            }
            if (index == -1) continue;
            ispis = ispis + babunlije[index].getIme() + ": " + max;
            if (j!=broj-1) ispis = ispis + "; ";
            sort.add(index);
        }

        return ispis;
    }

    private String statistikaOmaseneZaredom() {

        List<Integer> sort = new LinkedList<>();
        String ispis="MISS STREAK:   ";

        for (int j = 0; j < broj; j++) {
            int max = 0, index = -1;
            for (int i = 0; i < broj; i++) {
                if (babunlije[i].getMaxOmasenihZaredom() >= max && !sort.contains(i)) {
                    max = babunlije[i].getMaxOmasenihZaredom();
                    index = i;
                }
            }
            if (index == -1) continue;
            ispis = ispis + babunlije[index].getIme() + ": " + max;
            if (j!=broj-1) ispis = ispis + "; ";
            sort.add(index);
        }

        return ispis;
    }

    private String statistikaUbodeneZaredom() {

        List<Integer> sort = new LinkedList<>();
        String ispis="HITSTREAK:   ";

        for (int j = 0; j < broj; j++) {
            int max = 0, index = -1;
            for (int i = 0; i < broj; i++) {
                if (babunlije[i].getMaxUbodenihZaredom() > max && !sort.contains(i)) {
                    max = babunlije[i].getMaxUbodenihZaredom();
                    index = i;
                }
            }
            if (index == -1) continue;
            ispis = ispis + babunlije[index].getIme() + ": " + max;
            if (j!=broj-1) ispis = ispis + "; ";
            sort.add(index);
        }

        return ispis;
    }

    private String statistikaUbistva() {

        List<Integer> sort = new LinkedList<>();
        String ispis="UBISTAVA:   ";

        for (int j = 0; j < broj; j++) {
            int max = 0, index = -1;
            for (int i = 0; i < broj; i++) {
                if (babunlije[i].getBrojUbistava() > max && !sort.contains(i)) {
                    max = babunlije[i].getBrojUbistava();
                    index = i;
                }
            } if (index==-1) return ispis;
            ispis = ispis + babunlije[index].getIme() + ": " + max;
            if (j!=broj-1) ispis = ispis + "; ";
            sort.add(index);
        }

        return ispis;
    }

    private String statistikaHitRate() { //INDEX OSTAJE -1 ZASTO?

        List<Integer> sort = new LinkedList<>();
        String ispis="HITRATE:   ";

        for (int j = 0; j < broj; j++) {
            int  index = -1;
            double max = 0;
            for (int i = 0; i < broj; i++) {
                if (babunlije[i].getHitRate() > max && !sort.contains(i)) {
                    max = babunlije[i].getHitRate();
                    index = i;
                }
            } if (index == -1) continue;                                                              //OVO CACKAMO SAD
            ispis = ispis + babunlije[index].getIme() + ": " + ispisDouble(max); //String.format("%.2f", max);
            if (j!=broj-1) ispis = ispis + "; ";
            sort.add(index);
        }

        return ispis;
    }

    public String statistikaUbodene() {

        List<Integer> sort = new LinkedList<>();
        String ispis="UBODENE:   ";

        for (int j = 0; j < broj; j++) {
            int max = 0, index = -1;
            for (int i = 0; i < broj; i++) {
                if (babunlije[i].getBrojUbodenih() >= max && !sort.contains(i)) {
                    max = babunlije[i].getBrojUbodenih();
                    index = i;
                }
            }
            if (index == -1) continue;
            ispis = ispis + babunlije[index].getIme() + ": " + max;
            if (j!=broj-1) ispis = ispis + "; ";
            sort.add(index);
        }

        return ispis;
    }

    public String ispisDouble(Double d){ //0,732 --> 73 , 2 %
        if (d==1) return "100%";
        d = d*1000;
        int a = d.intValue();
        int b = a % 10;
        a = a / 10;

        return a + "," + b + "%";


    }

//    public void undo(Partija r){
//        for (int i = 0; i < broj; i++){
//           babunlije[i] = r.getBabunlije(i);
//        }
//        naRedu=r.getNaRedu();
//        uFlasi=r.getuFlasi();
//    }

    public String alternativnoResenje(){
        int index = 0, maks = -1;






        List<Integer> lista = new LinkedList<>();

        int j = 0;


        while (j<broj) {
            maks = -1;
            for (int i = 0; i < broj; i++) {
                if (!lista.contains(i) && babunlije[i].getSkor()>maks) {
                    index = i;
                    maks = babunlije[i].getSkor();
                }
            }
            lista.add(index);
            j++;
        } //popunjena lista treba ispisati

        String s = "Ubodene: ";

        for (int i = 0; i < broj; i++) {
                        s = s + babunlije[lista.get(i)].getIme()+ ": " + babunlije[lista.get(i)].getBrojUbodenih() + "\n";
        }
        return s;
    }


    public void izbaci(int x) {
        // 1 2 # 4 5    3
        Babunlija pomocni = new Babunlija("");
        pomocni.kopiraj(getBabunlije(x-1));
        for (int i = x-1; i+1 < broj; i++){
            getBabunlije(i).kopiraj(getBabunlije(i+1));
        }
//        getBabunlije(broj-1).kopiraj(pomocni);
        broj--;
        brojzivih--;

    }
}
