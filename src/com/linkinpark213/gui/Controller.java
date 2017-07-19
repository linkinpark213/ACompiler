package com.linkinpark213.gui;

import com.linkinpark213.compiler.CompilerCore;
import com.linkinpark213.compiler.analyzer.lexical.LexicalAnalyzer;
import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.*;
import com.linkinpark213.compiler.analyzer.syntactic.SyntacticalAnalyzer;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.Program;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.VT;
import com.linkinpark213.compiler.error.AnalysisError;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.BiConsumer;


/**
 * Created by ooo on 2017/7/17 0017.
 */
public class Controller {
    @FXML
    private TextField fileDirectoryTextField;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button fileChooserButton;
    @FXML
    private TextArea codeTextArea;
    @FXML
    private TreeView syntaxTree;
    @FXML
    private TableView symbolList;
    @FXML
    private TableView quadList;
    @FXML
    private TableView tokenList;
    @FXML
    private TableColumn addressColumn;
    @FXML
    private TableColumn operatorColumn;
    @FXML
    private TableColumn variableAColumn;
    @FXML
    private TableColumn variableBColumn;
    @FXML
    private TableColumn resultColumn;
    @FXML
    private TableColumn symbolNameColumn;
    @FXML
    private TableColumn symbolTypeColumn;
    @FXML
    private TableColumn tokenNameColumn;
    @FXML
    private TableColumn tokenTypeColumn;
    @FXML
    private TableColumn tokenLineColumn;
    @FXML
    private TableColumn tokenColumnColumn;
    @FXML
    private TextArea statusText;
    @FXML
    private Tab codeTab;
    @FXML
    private Tab tokenListTab;
    @FXML
    private Tab syntaxTreeTab;
    @FXML
    private Tab quadListTab;
    @FXML
    private Tab outputTab;
    @FXML
    private Button outputTokenButton;
    @FXML
    private Button outputSyntaxTreeButton;
    @FXML
    private Button outputQuadListButton;
    private TokenQueue tokenQueue;
    private ArrayList<Token> tokens;
    private QuadQueue quadQueue;
    private Program program;


    @FXML
    public void handleFileChooserButtonClicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) mainPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("sample"));
        fileChooser.setTitle("Choose the code file");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Text File", "txt"));
        File codeFile = fileChooser.showOpenDialog(primaryStage);
        try {
            fileDirectoryTextField.setText(codeFile.getAbsolutePath());
            Scanner fileScanner = new Scanner(codeFile);
            codeTextArea.clear();
            while (fileScanner.hasNextLine()) {
                codeTextArea.appendText(fileScanner.nextLine() + "\n");
            }
        } catch (Exception e) {
            clearLog();
            log("Can't write to file");
        }
    }

    @FXML
    public void handleCompileButtonClicked(ActionEvent actionEvent) {
        String code = codeTextArea.getText();
        CompilerCore compilerCore = new CompilerCore();
        try {
            clearLog();
            tokenListTab.setDisable(true);
            syntaxTreeTab.setDisable(true);
            quadListTab.setDisable(true);
            outputTab.setDisable(true);

            log("Starting compilation.");

            //  Lexical Analysis
            log("Lexicon analyzing...");
            LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
            tokenQueue = new TokenQueue(lexicalAnalyzer.analyze(code));
            quadQueue = new QuadQueue();
            compilerCore.printLexicalAnalysisResult(tokenQueue.getTokens());
            printTokenList(tokenQueue);
            tokens = (ArrayList<Token>) tokenQueue.getTokens().clone();

            //  Syntactic Analysis
            log("Syntax Analyzing...");
            SyntacticalAnalyzer syntacticalAnalyzer = new SyntacticalAnalyzer();
            SymbolList symbolList = new SymbolList();
            program = syntacticalAnalyzer.analyze(tokenQueue, symbolList);
            compilerCore.printSyntacticalAnalysisResult(program);

            TreeItem<String> treeRoot = new TreeItem<String>("Program");
            syntaxTree.setRoot(treeRoot);
            generateSyntaxTree(treeRoot, program);

            symbolList.printList();
            clearSymbolList();
            printSymbolList(symbolList);

            //  Semantic Analysis
            log("Semantics Analyzing...");
            SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
            semanticAnalyzer.analyze(program, quadQueue, symbolList);

            compilerCore.printSemanticAnalysisResult(quadQueue.getQuadList());
            printQuadList(quadQueue);

            log("Compilation Finished.");
            log("Syntax Tree and Quad List Generated.");
            tokenListTab.setDisable(false);
            syntaxTreeTab.setDisable(false);
            quadListTab.setDisable(false);
            outputTab.setDisable(false);
        } catch (AnalysisError e) {
            log(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            clearLog();
            log("Invalid code.");
        }
    }

    @FXML
    public void handleOutputTokenButton(ActionEvent event) {
        Stage primaryStage = (Stage) mainPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("sample"));
        fileChooser.setTitle("Choose output file");
        File tokenFile = fileChooser.showOpenDialog(primaryStage);
        try {
            if (!tokenFile.exists()) {
                tokenFile.createNewFile();
            }
            PrintWriter printWriter = new PrintWriter(tokenFile);
            for (Token token : tokens) {
                printWriter.print(token.getFullTypeString());
                printWriter.print(": ");
                printWriter.print(token.getName());
                printWriter.print("   Line: " + token.getRow());
                printWriter.print(", Column: " + token.getColumn());
                printWriter.println();
            }
            printWriter.flush();
            printWriter.close();
            clearLog();
            log("Successfully wrote token list to file.");
        } catch (Exception e) {
            clearLog();
            log("Can't write to file.");
        }
    }

    @FXML
    public void handleOutputSyntaxTreeButton(ActionEvent event) {
        Stage primaryStage = (Stage) mainPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("sample"));
        fileChooser.setTitle("Choose output file");
        File tokenFile = fileChooser.showOpenDialog(primaryStage);
        try {
            if (!tokenFile.exists()) {
                tokenFile.createNewFile();
            }
            PrintWriter printWriter = new PrintWriter(tokenFile);
            program.printSyntacticalAnalysisTree(0, printWriter);
            printWriter.flush();
            printWriter.close();
            clearLog();
            log("Successfully wrote syntax tree to file.");
        } catch (Exception e) {
            clearLog();
            log("Can't write to file.");
        }
    }

    @FXML
    public void handleOutputQuadButton(ActionEvent event) {
        Stage primaryStage = (Stage) mainPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("sample"));
        fileChooser.setTitle("Choose output file");
        File quadFile = fileChooser.showOpenDialog(primaryStage);
        try {
            if (!quadFile.exists()) {
                quadFile.createNewFile();
            }
            PrintWriter printWriter = new PrintWriter(quadFile);
            for (Quad quad : quadQueue.getQuadList()) {
                printWriter.print(quad.getAddress());
                printWriter.print(" (");
                printWriter.print(quad.getOperator());
                printWriter.print(", ");
                printWriter.print(quad.getVariableA());
                printWriter.print(", ");
                printWriter.print(quad.getVariableB());
                printWriter.print(", ");
                printWriter.print(quad.getResult());
                printWriter.println(")");
            }
            printWriter.flush();
            printWriter.close();
            clearLog();
            log("Successfully wrote quad list to file.");
        } catch (Exception e) {
            clearLog();
            log("Can't write to file.");
        }
    }

    public void printTokenList(TokenQueue tokenQueue) {
        ObservableList<Token> tokens = FXCollections.observableArrayList();
        tokenNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tokenTypeColumn.setCellValueFactory(new PropertyValueFactory<>("fullTypeString"));
        tokenLineColumn.setCellValueFactory(new PropertyValueFactory<>("row"));
        tokenColumnColumn.setCellValueFactory(new PropertyValueFactory<>("column"));
        tokenList.setItems(tokens);
        tokens.addAll(tokenQueue.getTokens());
    }

    public void generateSyntaxTree(TreeItem<String> item, V v) {
        item.setExpanded(true);
        for (V child : ((VN) v).getChildren()) {
            if (child instanceof VT) {
                item.getChildren().add(new TreeItem<String>(((VT) child).toString()));
            } else {
                TreeItem<String> childItem = new TreeItem<>(((VN) child).getClass().getSimpleName());
                item.getChildren().add(childItem);
                generateSyntaxTree(childItem, child);
            }
        }
    }

    public void printQuadList(QuadQueue quadQueue) {
        ObservableList<Quad> quads = FXCollections.observableArrayList();
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        variableAColumn.setCellValueFactory(new PropertyValueFactory<>("variableA"));
        variableBColumn.setCellValueFactory(new PropertyValueFactory<>("variableB"));
        operatorColumn.setCellValueFactory(new PropertyValueFactory<>("operator"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
        quadList.setItems(quads);
        quads.addAll(quadQueue.getQuadList());
    }

    public void clearSymbolList() {
        symbolList.setItems(FXCollections.observableArrayList());
    }

    public void printSymbolList(SymbolList symbolList) {
        ObservableList<Symbol> symbols = FXCollections.observableArrayList();
        symbolNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        symbolTypeColumn.setCellValueFactory(new PropertyValueFactory<>("typeString"));
        this.symbolList.setItems(symbols);
        symbolList.getSymbolHashMap().forEach(new BiConsumer<String, Symbol>() {
            @Override
            public void accept(String s, Symbol symbol) {
                symbols.add(symbol);
            }
        });
        symbolList.getFunctionHashMap().forEach(new BiConsumer<String, Function>() {
            @Override
            public void accept(String s, Function function) {
                symbols.add(function.getSymbol());
            }
        });
    }

    public void log(String string) {
        statusText.appendText(string);
        statusText.appendText("\n");
    }

    public void clearLog() {
        statusText.setText("");
    }
}
