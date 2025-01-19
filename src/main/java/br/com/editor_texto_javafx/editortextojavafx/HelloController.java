package br.com.editor_texto_javafx.editortextojavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class HelloController {
    @FXML
    private TextArea textArea;
    private File arquivoAtual;

    public void  salvarArquivo(){
        
        if(arquivoAtual != null){
            escreverAquivoComo(arquivoAtual, textArea.getText());
        }else {
            salvarArquivoComo();
        }

    }
    private void escreverAquivoComo(File file, String coteudo) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(coteudo);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void salvarArquivoComo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar arquivo");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo de texto(*,.txt)", "*.txt"));

        arquivoAtual = fileChooser.showSaveDialog(textArea.getScene().getWindow());

        if (arquivoAtual != null) {
            escreverAquivoComo(arquivoAtual, textArea.getText());
        }
    }
        public void novoArquivo(){

        }
        public void abrirArquivo(){

        }
        public void copiar(){

        }
        public void colar(){

        }
        public void selecionar(){

        }
        public void modoClaro(){

        }
        public void modoEscuro() {

        }
        public void exibirInformacoes(){

    }





}