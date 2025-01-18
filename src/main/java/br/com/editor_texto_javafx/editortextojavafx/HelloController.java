package br.com.editor_texto_javafx.editortextojavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.File;

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

    private void salvarArquivoComo() {
    }

    private void escreverAquivoComo(File file, String coteudo) {

        try {

        }
    }


}