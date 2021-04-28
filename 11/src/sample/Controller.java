package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField tfUrokovaMiera;
    @FXML
    private TextField tfPocetRokov;
    @FXML
    private TextField tfSuma;
    @FXML
    private TextField tfMesacneSplatky;
    @FXML
    private TextField tfSpolu;
    @FXML
    private Button btVypocet;

    public void klikolSiNaVypocitaj(ActionEvent event) {
        double rocnyUrok = Double.parseDouble(tfUrokovaMiera.getText());
        double pocetRokov = Integer.parseInt(tfPocetRokov.getText());
        double suma = Double.parseDouble(tfSuma.getText());

        double mesacnyUrok = rocnyUrok/12/100;
        double mesacneSplatky = suma * mesacnyUrok/(1-(1/Math.pow(1+mesacnyUrok, pocetRokov*12)));

        tfMesacneSplatky.setText(String.format("%.2f", mesacneSplatky));

        double getTotalPayment = mesacneSplatky * pocetRokov * 12;
        tfSpolu.setText(String.format("%.2f", getTotalPayment));
    }
}
