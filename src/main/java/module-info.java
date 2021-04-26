module it.edu.isspitagora.indovinaunnumero {
    requires javafx.controls;
    requires javafx.fxml;

    opens it.edu.isspitagora.indovinaunnumero to javafx.fxml;
    exports it.edu.isspitagora.indovinaunnumero;
}
