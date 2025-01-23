package br.com.editor_texto_javafx.editortextojavafx;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.*;

public class Controller {
    @FXML
    private TextArea textArea;
    @FXML
    private MenuItem menuCopiar;
    @FXML
    private MenuItem menuSelecionarTudo;


    private File arquivoAtual;
    private Stage stage;
    private boolean arquivoAlterado = false;

    public void initialize() {
     textArea.textProperty().addListener((obs,valorAntigo,valorNovo) -> observarAcoes());
    }

    private void observarAcoes() {
        arquivoAlterado = true;
        definirNome(arquivoAtual,arquivoAlterado);
    }

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
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Escolha um arquivo arquivo");

            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo de texto(*,.txt)", "*.txt"));

            fileChooser.setInitialFileName("nota.txt");
            arquivoAtual = fileChooser.showOpenDialog(textArea.getScene().getWindow());


            if (arquivoAtual != null) {
                String conteudoArquivo = lerConteudo(arquivoAtual);

                textArea.setText(conteudoArquivo);
               // arquivoAlterado = false;
                definirNome(arquivoAtual,arquivoAlterado);
            }


        }

    private void definirNome(File arquivoAtual, boolean arquivoAlterado) {
        if(arquivoAtual != null)
            stage.setTitle((arquivoAlterado ? "*" : "")+ arquivoAtual.getName());

        else
            stage.setTitle((arquivoAlterado ? "*" : "")+ "sem titulo");

    }

    private String lerConteudo(File arquivoAtual) {
        StringBuilder conteudo = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new FileReader(arquivoAtual)) ){
            String linha;
            while((linha = reader.readLine()) != null){
                conteudo.append(linha).append("\n");
            }


        } catch (Exception e) {
            e.printStackTrace();

        }
        return conteudo.toString();
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }







}