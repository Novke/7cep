package com.example.a7pce.Partija;

import java.util.LinkedList;
import java.util.List;

public class Babunlija {

    private boolean izvini = true;
    private boolean ziv = true;
    private int poeni = 0;
    private int skor = 0;

    private String ime = "";
    private List<Integer> ubo = new LinkedList<>();
    private int doktor = 0;
///////////////////////////////////////////////// STATS
    private int omasenihZaredom = 0;
    private int maxOmasenihZaredom = 0; //
    private int ubodenihZaredom = 0;
    private int maxUbodenihZaredom = 0; //
    private int brojUbodenih = 0; //
    private int brojOmasenih = 0;
    private int brojUbodenihURundi = 0;
    private int brojOmasenihURundi = 0;
    private int kolkoPutaIzvini = 0;
    private int brojUbistava = 0;
    public double hitRate=0.0;
    private double hitRateURundi=0.0;
    private int brojBacenihFlasa = 0;
    private String sviPokusaji = "";
    private int popijeno = 0;

///////////////////////////////////////////////// SVE IZNAD OVOGA CE KOPIRATI ONAKO KAKO BOG ZAPOVEDA

    private int brojUbistvaURundi = 0;
    private int maxUbodenihZaredomURundi = 0;
    private int maxOmasenihZaredomURundi = 0;
    private int baceneURundi = 0;

    ////////////////////////


    public static String ispis(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }

//    public static String ispis(String string, int length){
//        if (string.length()>=length) return string;
//        String s = "";
//        for (int i = 0; i <length-string.length();i++){
//            s+=" ";
//        } return s+string;
//    }

    public String toString(int x){
        if (doktor==1) return ime + "   DOKTOR";
        if (doktor>1) return ime +  "   DOKTOR x" + doktor;
        return ime;
    }

//    public String toString() {                O G
//        String i = "NEMA";
//        if (izvini) i = "IMA";
//        if (!ziv) i = "U M R O";
//        return  poeni + " " + ispis(ime, 15) + ", " + i;
//    }

    public String toString(){
        if (izvini) return poeni + " " + ispis(ime, 20) + " +Izvini";
        if (ziv) return poeni + " " + ispis(ime, 20)+ "           ";
        //return poeni + " R.I.P. " + ime;
        //return poeni + " " + ispis("["+ime+"]", 16);
        return poeni + " [" + ime +"]";
    }

    public String toStringSkor(){
        return skor + ", " + ispis(ime, 24);
    }


    public Babunlija(String ime) {
        this.ime = ime;
    }

    public void kopiraj(Babunlija b){
            izvini = b.isIzvini();
            ziv = b.isZiv();
            poeni = b.getPoeni();
             skor = b.getSkor();

             ime = b.getIme();

         doktor = b.getDoktor();
        omasenihZaredom = b.getOmasenihZaredom();
         maxOmasenihZaredom = b.getMaxOmasenihZaredom();
        ubodenihZaredom = b.getUbodenihZaredom();
         maxUbodenihZaredom = b.getMaxUbodenihZaredom();
       brojUbodenih = b.getBrojUbodenih();
        brojOmasenih = b.getBrojOmasenih();
         brojUbodenihURundi = b.getBrojUbodenihURundi();
         brojOmasenihURundi = b.getBrojOmasenihURundi();
         kolkoPutaIzvini = b.getKolkoPutaIzvini();
        brojUbistava = b.getBrojUbistava();
        hitRate=b.getHitRate();
         hitRateURundi=b.getHitRateURundi();
        brojBacenihFlasa = b.getBrojBacenihFlasa();
         sviPokusaji = b.getSviPokusaji();

         popijeno = b.getPopijeno();

        brojUbistvaURundi = b.getBrojUbistavaURundi();
        maxUbodenihZaredomURundi = b.getMaxUbodenihZaredomURundi();
        maxOmasenihZaredomURundi = b.getMaxOmasenihZaredomURundi();
        baceneURundi=b.getBaceneURundi();
    }
    public int getBaceneURundi(){
        return baceneURundi;
    }
    public int getMaxOmasenihZaredomURundi(){
        return maxOmasenihZaredomURundi;
    }
    public int getMaxUbodenihZaredomURundi(){
        return maxUbodenihZaredomURundi;
    }
    public int getBrojUbistavaURundi(){
        return brojUbistvaURundi;
    }
    public void dodajPopijeno(int p){
        popijeno = popijeno + p;
    }
    public void oduzmiPopijeno(int p){
        popijeno = popijeno - p;
    }
    public int getPopijeno(){
        return popijeno;
    }
    public void setPopijeno(int popijeno){
        this.popijeno = popijeno;
    }
    public int getOmasenihZaredom() {
        return omasenihZaredom;
    }
    public void setDoktor(boolean a){doktor++;}
    public int getDoktor(){
        return doktor;
    }
    public void skiniDoktora(){doktor = 0;}
    public void setOmasenihZaredom(int omasenihZaredom) {
        this.omasenihZaredom = omasenihZaredom;
    }

    public int getUbodenihZaredom() {
        return ubodenihZaredom;
    }

    public int getMaxOmasenihZaredom() {
        return maxOmasenihZaredom;
    }

    public int getMaxUbodenihZaredom() {
        return maxUbodenihZaredom;
    }

    public int getBrojUbodenihURundi() {
        return brojUbodenihURundi;
    }

    public int getBrojOmasenihURundi() {
        return brojOmasenihURundi;
    }
    public void ubioNekog(){
        brojUbistava++;
        brojUbistvaURundi++;
    }
//    public int getBrojUbistava(){
//        return brojUbistava;
//    }

    public int getKolkoPutaIzvini() {
        return kolkoPutaIzvini;
    }

    public int getBrojUbistava() {
        return brojUbistava;
    }

    public double getHitRate() {
        return hitRate;
    }

    public double getHitRateURundi() {
        return hitRateURundi;
    }

    public int getBrojBacenihFlasa() {
        return brojBacenihFlasa;
    }

    public String getSviPokusaji() {
        return sviPokusaji;
    }

    public void setUbodenihZaredom(int ubodenihZaredom) {
        this.ubodenihZaredom = ubodenihZaredom;
    }

    public void setUbo(int a){
        if (a==1 || a==0) ubo.add(a);
    }

    public void setIzvini(boolean izvini) {
        this.izvini = izvini;
    }

    public void setZiv(boolean ziv) {
        this.ziv = ziv;
    }

    public void setPoeni(int poeni) {
        this.poeni = poeni;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }

    public void setBrojUbodenih(int brojUbodenih) {
        this.brojUbodenih = brojUbodenih;
    }

    public void setBrojOmasenih(int brojOmasenih) {
        this.brojOmasenih = brojOmasenih;
    }

//    public void setBrojUbodenihSve(int brojUbodenihURundi) {
//        this.brojUbodenihURundi = brojUbodenihURundi;
//    }
//
//    public void setBrojOmasenihSve(int brojOmasenihURundi) {
//        this.brojOmasenihURundi = brojOmasenihURundi;
//    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public boolean isIzvini() {
        return izvini;
    }

    public boolean isZiv() {
        return ziv;
    }

    public int getPoeni() {
        return poeni;
    }

    public int getSkor() {
        return skor;
    }

    public int getBrojUbodenih() {
        return brojUbodenih;
    }

    public int getBrojOmasenih() {
        return brojOmasenih;
    }

    public String getIme() {
        return ime;
    }

    public void ubo(){
        omasenihZaredom=0;
        ubodenihZaredom++;
        if (ubodenihZaredom>maxUbodenihZaredom) maxUbodenihZaredom = ubodenihZaredom;
        if (ubodenihZaredom > maxUbodenihZaredomURundi) maxUbodenihZaredomURundi = ubodenihZaredom;
        brojBacenihFlasa++;
        brojUbodenih++;
        brojUbodenihURundi++;
        hitRate =  ((double) brojUbodenih) / brojBacenihFlasa;
        hitRateURundi = ((double) brojUbodenihURundi) / (brojOmasenihURundi + brojUbodenihURundi);
        sviPokusaji = sviPokusaji + "+";
        baceneURundi++;
    }

    public void omasio(){
        ubodenihZaredom =0;
        brojBacenihFlasa++;
        omasenihZaredom++;
        if (omasenihZaredom>maxOmasenihZaredom) maxOmasenihZaredom = omasenihZaredom;
        if (omasenihZaredom > maxOmasenihZaredomURundi) maxOmasenihZaredomURundi = omasenihZaredom;
        brojOmasenih++;
        brojOmasenihURundi++;
        hitRate =  ((double) brojUbodenih) / brojBacenihFlasa;
        hitRateURundi = ((double) brojUbodenihURundi) / (brojOmasenihURundi + brojUbodenihURundi);
        sviPokusaji = sviPokusaji + "-";
        baceneURundi++;

    }
    public void ocistiStatistike(){
        hitRateURundi=0;
        brojUbistvaURundi = 0;
        maxUbodenihZaredomURundi = 0;
        maxOmasenihZaredomURundi = 0;
        baceneURundi=0;
        brojOmasenihURundi=0;
        brojUbodenihURundi=0;

    }

    public List<Integer> getUbo() {
        return ubo;
    }

    public void izvini(){
        izvini = false;
        kolkoPutaIzvini++;
    }
}
