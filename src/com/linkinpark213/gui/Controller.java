package com.linkinpark213.gui;

import com.linkinpark213.compiler.CompilerCore;
import com.linkinpark213.compiler.analyzer.lexical.LexicalAnalyzer;
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
import java.util.HashMap;
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
    private TableColumn nameColumn;
    @FXML
    private TableColumn typeColumn;
    @FXML
    private TextArea statusText;

    @FXML
    public void handleFileChooserButtonClicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) mainPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\ooo\\Documents\\Labs\\Compilers\\ACompiler\\ACompiler"));
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
            System.out.println("File not found.");
        }
    }

    @FXML
    public void handleCompileButtonClicked(ActionEvent actionEvent) {
        String code = codeTextArea.getText();
        CompilerCore compilerCore = new CompilerCore();
        try {
            clearLog();
            log("Starting compilation.");

            //  Lexical Analysis
            log("Lexicon analyzing...");
            LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
            TokenQueue tokenQueue = new TokenQueue(lexicalAnalyzer.analyze(code));
            QuadQueue quadQueue = new QuadQueue();
            compilerCore.printLexicalAnalysisResult(tokenQueue.getTokens());


            //  Syntactic Analysis
            log("Syntax analyzing...");
            SyntacticalAnalyzer syntacticalAnalyzer = new SyntacticalAnalyzer();
            SymbolList symbolList = new SymbolList();
            Program program = syntacticalAnalyzer.analyze(tokenQueue, symbolList);

            TreeItem<String> treeRoot = new TreeItem<String>("Program");
            syntaxTree.setRoot(treeRoot);
            generateSyntaxTree(treeRoot, program);

            symbolList.printList();
            clearSymbolList();
            printSymbolList(symbolList);

            //  Semantic Analysis
            log("Semantics analyzing...");
            SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
            semanticAnalyzer.analyze(program, quadQueue);

            compilerCore.printSemanticAnalysisResult(quadQueue.getQuadList());
            printQuadList(quadQueue);

            log("Compilation finished. View the syntax tree and quad list in other tabs.");
        } catch (AnalysisError e) {
            log(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            clearLog();
            log("Invalid code.");
        }
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
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeString"));
        this.symbolList.setItems(symbols);
        symbolList.getSymbolHashMap().forEach(new BiConsumer<String, Symbol>() {
            @Override
            public void accept(String s, Symbol symbol) {
                symbols.add(symbol);
            }
        });
        symbolList.getFunctionHashMap().forEach(new BiConsumer<String, Symbol>() {
            @Override
            public void accept(String s, Symbol symbol) {
                symbols.add(symbol);
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
