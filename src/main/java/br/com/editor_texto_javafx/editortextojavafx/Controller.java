package br.com.editor_texto_javafx.editortextojavafx;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Controller {
    @FXML
    private TextArea textArea;
    @FXML
    private MenuItem menuCopiar;
    @FXML
    private MenuItem menuSelecionarTudo;

    private File arquivoAtual;
    @FXML
    private TextField textField;
    @FXML
    private Stage stage;

    @FXML
    public void salvar(){
        
        if(arquivoAtual != null){
            escreverAquivo(arquivoAtual, textArea.getText());
        }else {
            salvarComoArquivo();
        }

    }
    @FXML
    public void escreverAquivo(File file, String conteudo) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(conteudo);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void salvarComoArquivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar arquivo");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo de texto(*,.txt)", "*.txt"));

        fileChooser.setInitialFileName("nota.txt");
        arquivoAtual = fileChooser.showSaveDialog(textArea.getScene().getWindow());


        if (arquivoAtual != null) {
            escreverAquivo(arquivoAtual, textArea.getText());
        }
    }


       @FXML
       public void sair(){

       }

        @FXML
        public void abrir(){

        }
        @FXML
        public void copiar(){

        }
        @FXML
        public void colar(){

        }
        @FXML
        public void selecionarTudo(){

        }
        @FXML
        public void modoClaro(){

        }

        @FXML
        public void modoEscuro() {

        }
        @FXML
        public void sobre(){}







}