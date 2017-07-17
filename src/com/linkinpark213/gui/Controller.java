package com.linkinpark213.gui;

import com.linkinpark213.compiler.CompilerTest;
import com.linkinpark213.compiler.analyzer.lexical.LexicalAnalyzer;
import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.*;
import com.linkinpark213.compiler.analyzer.syntactic.SyntacticalAnalyzer;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.Program;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.VT;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.reflect.generics.tree.Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        CompilerTest compilerTest = new CompilerTest();

        //  Lexical Analysis
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        ArrayList<Token> tokenQueue = lexicalAnalyzer.analyze(code);
        QuadQueue quadQueue = new QuadQueue();
        compilerTest.printLexicalAnalysisResult(tokenQueue);

        //  Syntactic Analysis
        SyntacticalAnalyzer syntacticalAnalyzer = new SyntacticalAnalyzer();
        SymbolList symbolList = new SymbolList();
        Program program = syntacticalAnalyzer.analyze(tokenQueue, symbolList);
        compilerTest.printSyntacticalAnalysisResult(program);

        TreeItem<String> treeRoot = new TreeItem<String>("Program");
        syntaxTree.setRoot(treeRoot);
        generateSyntaxTree(treeRoot, program);

        symbolList.printList();
        printSymbolList(symbolList.getSymbolHashMap());

        //  Semantic Analysis
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
        semanticAnalyzer.analyze(program, quadQueue);

        compilerTest.printSemanticAnalysisResult(quadQueue.getQuadList());
        printQuadList(quadQueue);
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

    public void printSymbolList(HashMap<String, Symbol> symbolHashMap) {
        ObservableList<Symbol> symbols = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeString"));
        symbolList.setItems(symbols);
        symbolHashMap.forEach(new BiConsumer<String, Symbol>() {
            @Override
            public void accept(String s, Symbol symbol) {
                symbols.add(symbol);
            }
        });
    }
}
