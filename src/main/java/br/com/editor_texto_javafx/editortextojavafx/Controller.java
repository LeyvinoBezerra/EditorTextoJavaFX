package br.com.editor_texto_javafx.editortextojavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.awt.*;
import java.awt.Label;
import java.io.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {
    @FXML
    private TextArea textArea;
    @FXML
    private Menu arquivoRecentesMenu;

    String modoAtual = "escuro";
    private int tamanhoFonte;
    private File arquivoAtual;
    private Stage stage;
    private boolean arquivoAlterado = false;
    @FXML
    private TextField busca;



    // Lista de arquivos abertos recentemente

    private List<String> arquivosRecentes = new ArrayList<>();

    @FXML
    private void atualizarMenuArquivosRecentes() {
        arquivoRecentesMenu.getItems().clear(); // Limpa os itens existentes

        for (String caminho : arquivosRecentes) {
            File arquivo = new File(caminho); // Cria um objeto File para extrair o nome do arquivo
            MenuItem item = new MenuItem(arquivo.getName()); // Exibe o nome do arquivo
            item.setOnAction(e -> abrirArquivo(caminho)); // Define a ação para abrir o arquivo ao clicar
            arquivoRecentesMenu.getItems().add(item); // Adiciona o item ao menu
        }
    }

    @FXML
    private void initialize() {
        // Inicializar com alguns arquivos recentes (se houver)
        arquivosRecentes.add(("C:/Users/leyvi/Documents/Arquivo1.txt"));
        arquivosRecentes.add(("C:/Users/leyvi/Documents/Arquivo2.txt"));
        arquivosRecentes.add(("C:/Users/leyvi/Documents/Arquivo3.txt"));

        // Atualizar menu de arquivos recentes
        atualizarMenuArquivosRecentes();
    }

    private void abrirArquivo(String caminho) {
        try {
            File arquivo = new File(caminho);
            String conteudoArquivo = lerConteudo(arquivo);
            textArea.setText(conteudoArquivo);
            definirNome(arquivo, false); // Atualiza o título da janela com o nome do arquivo
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("Não foi possível abrir o arquivo: " + caminho);
            alert.showAndWait();
        }
    }


    private void observarAcoes() {
        arquivoAlterado = true;
        definirNome(arquivoAtual, arquivoAlterado);
    }

    @FXML
    public void salvar() {
        if (arquivoAtual != null) {
            escreverAquivo(arquivoAtual, textArea.getText());
        } else {
            salvarComoArquivo();
        }
    }

    @FXML
    public void escreverAquivo(File file, String conteudo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(conteudo);
        } catch (Exception e) {
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
    public void sair() {
        if (arquivoAlterado) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Saindo");
            alert.setContentText("Deseja salvar as alterações em " +
                    (arquivoAtual == null ? "Sem título" : arquivoAtual.getName()) + " ?");
            ButtonType btnSalvar = new ButtonType("Salvar");
            ButtonType btnNaoSalvar = new ButtonType("Não Salvar");
            ButtonType btnCancelar = new ButtonType("Cancelar");
            alert.getButtonTypes().setAll(btnSalvar, btnNaoSalvar, btnCancelar);

            alert.showAndWait().ifPresent(resposta -> {
                if (resposta == btnSalvar) {
                    salvar();
                    stage.close();
                } else if (resposta == btnNaoSalvar) {
                    stage.close();
                }
            });
        } else {
            stage.close();
        }
    }

    @FXML
    public void abrir() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolha um arquivo arquivo");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo de texto(*,.txt)", "*.txt"));

        fileChooser.setInitialFileName("nota.txt");
        arquivoAtual = fileChooser.showOpenDialog(textArea.getScene().getWindow());

        if (arquivoAtual != null) {
            String conteudoArquivo = lerConteudo(arquivoAtual);
            textArea.setText(conteudoArquivo);
            arquivosRecentes.add(String.valueOf(arquivoAtual)); // Adiciona à lista de arquivos abertos recentemente
            atualizarMenuArquivosRecentes();
            definirNome(arquivoAtual, arquivoAlterado);
        }
    }



    private void definirNome(File arquivoAtual, boolean arquivoAlterado) {
        if (arquivoAtual != null)
            stage.setTitle((arquivoAlterado ? "*" : "") + arquivoAtual.getName());
        else
            stage.setTitle((arquivoAlterado ? "*" : "") + "sem titulo");
    }

    private String lerConteudo(File arquivoAtual) {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoAtual))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conteudo.toString();
    }

    public void novo() {
        if (arquivoAlterado) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salvando");
            alert.setContentText("Deseja salvar as alterações em " +
                    (arquivoAtual == null ? "Sem título" : arquivoAtual.getName()) + " ?");
            ButtonType btnSalvar = new ButtonType("Salvar");
            ButtonType btnNaoSalvar = new ButtonType("Não Salvar");
            alert.getButtonTypes().setAll(btnSalvar, btnNaoSalvar);

            alert.showAndWait().ifPresent(resposta -> {
                if (resposta == btnSalvar) {
                    salvar();
                    resetarInformacoes();
                } else if (resposta == btnNaoSalvar) {
                    resetarInformacoes();
                }
            });
        } else {
            resetarInformacoes();
        }
    }

    public void onScroll(ScrollEvent evento) {
        if (evento.isControlDown()) {
            if (evento.getDeltaY() > 0) {
                tamanhoFonte += 2;
            } else {
                tamanhoFonte = Math.max(8, tamanhoFonte - 2);
            }
            textArea.setStyle(getModoAtual() + "-fx-font-size: " + tamanhoFonte + " pt;");
            evento.consume();
        }
    }

    private String getModoAtual() {

        return "escuro".equals(modoAtual) ? Tema.modoEscuro() : Tema.modoClaro();
    }

    private void resetarInformacoes() {
        arquivoAtual = null;
        textArea.setText("");
        arquivoAlterado = false;
        definirNome(arquivoAtual, arquivoAlterado);
    }

    public void copiar() {
        String textoSelecionado = textArea.getSelectedText();
        if (!textoSelecionado.isEmpty()) {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent conteudo = new ClipboardContent();
            conteudo.putString(textoSelecionado);
            clipboard.setContent(conteudo);
        }
    }

    public void colar() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        if (clipboard.hasString()) {
            String clipboardTexto = clipboard.getString();
            int caretPosition = textArea.getCaretPosition();
            textArea.insertText(caretPosition, clipboardTexto);
        }
    }

    public void selecionarTudo() {

        textArea.selectAll();
    }

    public void modoClaro() {
        modoAtual = "claro";
        textArea.setStyle(getModoAtual() + "-fx-font-size: " + tamanhoFonte + " pt;");
    }

    public void modoEscuro() {
        modoAtual = "escuro";
        textArea.setStyle(getModoAtual() + "-fx-font-size: " + tamanhoFonte + " pt;");
    }

    @FXML
    public void sobre() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Editor de texto");
        alert.setContentText("Editor de texto Simples");
        alert.showAndWait();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void fechar(WindowEvent evento) {
        evento.consume();
        sair();
    }

    public void atalhos(KeyEvent evento) {
        if (evento.isControlDown() && evento.getCode() == KeyCode.O) {
            abrir();
        }
        if (evento.isControlDown() && evento.isShiftDown() && evento.getCode() == KeyCode.S) {
            salvarComoArquivo();
        }
        if (evento.isControlDown() && evento.getCode() == KeyCode.N) {
            novo();
        }
        if (evento.isControlDown() && evento.getCode() == KeyCode.W) { //
            sair();
        }
    }

    // Método para buscar uma palavra e destacar suas ocorrências
    @FXML
    private TextField caixaBusca;

    @FXML
    private void buscarPalavra() {
        String palavra = caixaBusca.getText(); // Obtém o texto da caixa de busca
        String texto = textArea.getText(); // Obtém o texto do TextArea

        int index = texto.indexOf(palavra); // Localiza a posição inicial da palavra no texto

        if (index >= 0) {
            textArea.selectRange(index, index + palavra.length()); // Seleciona a palavra no TextArea
        } else {
            // Exibe um alerta informando que a palavra não foi encontrada
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Palavra não encontrada");
            alert.setContentText("A palavra \"" + palavra + "\" não foi encontrada no texto.");
            alert.showAndWait();
        }
    }











}




 
   
