package editor;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;


public class Controller {
    public TextArea textArea;
    public MenuItem copia;
    public MenuItem talla;
    public Button btncopiar;
    public Button btntallar;
    public Button btnenganxar;
    public Button btndesfer;
    public CheckMenuItem arial;
    public CheckMenuItem liberation;
    public CheckMenuItem verdana;
    public CheckMenuItem t10;
    public CheckMenuItem t12;
    public CheckMenuItem t14;
    public MenuItem contentcopia;
    public MenuItem contenttalla;
    public MenuItem contentenganxa;
    public MenuItem contentdesfe;
    public AnchorPane mainPane;
    private String textAnt = "";
    private String text;
    private String font = "Arial";
    private int tamany = 12;
    private Stage primaStage;
    private File file;


    public void initialize(){
        btncopiar.setGraphic(new ImageView("ic_content_copy_black_24dp_1x.png"));
        btntallar.setGraphic(new ImageView("ic_content_cut_black_24dp_1x.png"));
        btnenganxar.setGraphic(new ImageView("ic_content_paste_black_24dp_1x.png"));
        btndesfer.setGraphic(new ImageView("ic_undo_black_24dp_1x.png"));
    }

    public void tanca(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void copiar(ActionEvent actionEvent) {
        text = textArea.getSelectedText();
    }

    public void tallar(ActionEvent actionEvent) {
        text = textArea.getSelectedText();
        String textTotal = textArea.getText();
        textAnt = textTotal;
        textArea.setText(textTotal.replace(text, ""));
    }

    public void enganxar(ActionEvent actionEvent) {
        String textTotal = textArea.getText();
        String textFinal = "";
        int posicio = textArea.getCaretPosition();
        for(int i = 0; i < textTotal.length(); i++){
            if(posicio == i){
                textFinal += text + textTotal.charAt(i);
            } else {
                textFinal += textTotal.charAt(i);
            }
        }
        if(textFinal.equals(textTotal)){
           textFinal += text;
        }
        textAnt = textTotal;
        textArea.setText(textFinal);
        textArea.positionCaret(posicio + text.length());
    }

    public void desfer(ActionEvent actionEvent) {
        textArea.setText(textAnt);
    }

    public void canviFont(ActionEvent actionEvent) {
        CheckMenuItem men = (CheckMenuItem) actionEvent.getSource();
        switch (men.getId()){
            case "arial":
                font = "Arial";
                liberation.setSelected(false);
                verdana.setSelected(false);
                break;
            case "liberation":
                font = "Liberation Serif";
                arial.setSelected(false);
                verdana.setSelected(false);
                break;
            case "verdana":
                font = "Verdana";
                arial.setSelected(false);
                liberation.setSelected(false);
                break;
        }
        textArea.setFont(new Font(font, tamany));
    }

    public void canviTamany(ActionEvent actionEvent) {
        MenuItem men = (MenuItem) actionEvent.getSource();
        switch (men.getId()){
            case "t10":
                tamany = 10;
                t12.setSelected(false);
                t14.setSelected(false);
                break;
            case "t12":
                tamany = 12;
                t10.setSelected(false);
                t14.setSelected(false);
                break;
            case "t14":
                tamany = 14;
                t10.setSelected(false);
                t12.setSelected(false);
                break;
        }
        textArea.setFont(new Font(font, tamany));
    }

    public void deshabilita(Event actionEvent) {
        if(textArea.getSelectedText().equals("")) {
            copia.setDisable(true);
            talla.setDisable(true);
            contentcopia.setDisable(true);
            contenttalla.setDisable(true);
        } else {
            copia.setDisable(false);
            talla.setDisable(false);
            contentcopia.setDisable(false);
            contenttalla.setDisable(false);
        }
    }

    public void ajuda(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajuda");
        alert.setHeaderText("Ajuda Editor de Text");
        alert.setContentText("Tinc un gran missatge per a tu.\nQue et dire un altre dia.\nXD");

        alert.showAndWait();
    }

    public void obrirFitxer(ActionEvent actionEvent) {
        primaStage = (Stage)mainPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Obrir Fitxer");
        file = fileChooser.showOpenDialog(primaStage);
        if (file != null) {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                primaStage.setTitle(file.getName());
                String linia, textTotal = "";
                while((linia = br.readLine())!=null){
                    textTotal += linia + "\n";
                }
                textArea.setText(textTotal);
                br.close();
                fr.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void guardarFitxer(ActionEvent actionEvent) {
        guardar();
    }

    public void guardarComFitxer(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Fitxer");
        file = fileChooser.showSaveDialog(primaStage);
        guardar();
    }
    private void guardar(){
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(textArea.getText());
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
