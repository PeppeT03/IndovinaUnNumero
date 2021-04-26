/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.isspitagora.indovinaunnumero.model;

import java.security.InvalidParameterException;

/**
 *
 * @author Testa
 */
public class Model {

    private final int NMAX = 100; //Limite superiore al valore casuale scelto dal programma (1<=segreto<=100)
    private final int TMAX = 8; //Numero di tentativi
    private int segreto; //Conserva il valore del numero scelto a caso
    private int tentativiFatti; //Mantiene traccia del numero di tentativi fatti dall'utente
    private boolean inGioco = false; //La partita è in corso??

    public int getSegreto() {
        return segreto;
    }

    public void setSegreto(int segreto) {
        this.segreto = segreto;
    }

    public int getTentativiFatti() {
        return tentativiFatti;
    }

    public void setTentativiFatti(int tentativiFatti) {
        this.tentativiFatti = tentativiFatti;
    }

    public boolean isInGioco() {
        return inGioco;
    }

    public void setInGioco(boolean inGioco) {
        this.inGioco = inGioco;
    }

    public int getNMAX() {
        return NMAX;
    }

    public int getTMAX() {
        return TMAX;
    }

    public void nuovaPartita() {
        //Gestione avvio Partita
        this.segreto = (int) (Math.random() * NMAX) + 1; //Genera il numero casuale
        this.tentativiFatti = 0;
        this.inGioco = true;
    }

    public int tentativo(int tentativo) {
        //Restituisce:
        // 0 Se ha vinto
        //-1 Se è BASSO
        //1 se è ALTO

        //Controllo se la partita è in corso
        if (!this.inGioco) {
            throw new IllegalStateException("La partita è finita....\n HAI PERSO!\nIl numero da indovinare era" + this.segreto + "Ritenta\n");
        }
        //Controlla il range di valori

        if (tentativo < 1 || tentativo > 100) {
            throw new InvalidParameterException("Devi inserire un valore compreso tea 1 e 100......\n");
        }

        //il numero passato è valido e quindi deve valutare la scelta del giocatore
        this.tentativiFatti++;

        //Controllo se non ho più tentativi
        if (this.tentativiFatti == this.TMAX) {
            this.inGioco = false;
        }

        if (tentativo == this.segreto) {
            this.inGioco = false;
            return 0;
        } else if (tentativo < this.segreto) {
            return -1;
        } else {
            return 1;
        }

    }
}
