/**
 * Sample Skeleton for 'primary.fxml' Controller Class
 */
package it.edu.isspitagora.indovinaunnumero;

import it.edu.isspitagora.indovinaunnumero.model.Model;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class PrimaryController {

    private Model model; //Il controller deve sapere chi governa la logica del programma

    //Setter per impostare il modello
    public void setModel(Model model) {
        this.model = model;
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="layoutTentativo"
    private HBox layoutTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="btnNuovaPartita"
    private Button btnNuovaPartita; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativi"
    private TextField txtTentativi; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativoUtente"
    private TextField txtTentativoUtente; // Value injected by FXMLLoader

    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void handleNuovaPartita(ActionEvent event) {
        //Avvio la partita
        this.model.nuovaPartita();

        //Gestione interfaccia
        this.txtTentativi.setText(Integer.toString(this.model.getTMAX())); //Scrivo 8 nella casella di testo dei tentativi rimasti
        this.btnNuovaPartita.setDisable(this.model.isInGioco());
        this.layoutTentativo.setDisable(!(this.model.isInGioco())); //Disabilita l'HBOX 
        this.txtRisultato.clear();//Pulisco l'area di testo Risultato
    }

    @FXML
    void handleTentativo(ActionEvent event) {
        //Lettura input utente
        String ts = txtTentativoUtente.getText();

        //Parsing Tentativo e validazione dell'input
        int tentativo;

        try {
            tentativo = Integer.parseInt(ts);
        } catch (NumberFormatException e) {
            txtRisultato.appendText("Devi digitare un numero....\n");
            txtTentativoUtente.clear();
            return;
        }

        //Aggiorno interfaccia utente
        txtTentativoUtente.clear();
        int result;
        try {
            result = model.tentativo(tentativo);
        } catch (IllegalStateException se) {
            this.txtRisultato.appendText(se.getMessage());
            //Riattiva bottone nuova partita
            this.btnNuovaPartita.setDisable(this.model.isInGioco());
            this.layoutTentativo.setDisable(!(this.model.isInGioco()));
            return;
        } catch (InvalidParameterException pe) {
            this.txtRisultato.appendText(pe.getMessage());
            return;
        }

        //Aggiornare interfaccia
        this.txtTentativi.setText(Integer.toString(this.model.getTMAX() - this.model.getTentativiFatti()));

        if (result == 0) {
            //HO VINTO
            txtRisultato.appendText("COMPLIMENTI, Hai vinto!!!!! Con " + this.model.getTentativiFatti() + " Tentativi\n");
            this.btnNuovaPartita.setDisable(this.model.isInGioco());
            this.layoutTentativo.setDisable(!(this.model.isInGioco()));
            return;
        } else if (result < 0) {
            txtRisultato.appendText("TENTATIVO TROPPO BASSO,RITENTA.......\n");
        } else {
            txtRisultato.appendText("TENTATIVO TROPPO ALTO,RITENTA.......\n");
        }

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'primary.fxml'.";
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'primary.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'primary.fxml'.";
        assert txtTentativoUtente != null : "fx:id=\"txtTentativoUtente\" was not injected: check your FXML file 'primary.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'primary.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'primary.fxml'.";

    }
}
