package it.disi.unitn.surname;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import static javafx.scene.paint.Color.AQUAMARINE;
import static javafx.scene.paint.Color.BLACK;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        HBox Phand = new HBox();
        HBox Ehand = new HBox();
        AnchorPane ap = new AnchorPane();
        test();
        Mano player = new Mano();
        Mano adversary = new Mano();
        Deck d = new Deck();
        d.shuffle();
        giveInitialCard(player, d);
        giveInitialCard(adversary, d);
        player.sort();
        adversary.sort();
        populate(Phand, player);
        populate(Ehand, adversary);
        System.out.print("Pippo: ");
        printcards(player);
        System.out.println();
        System.out.print("Pluto: ");
        printcards(adversary);
        System.out.println("===");
        Button firstP = new Button("Pesca dall'avversario");
        Button secondP = new Button("Cerca le coppie");
        Button thirdP = new Button("Pesca dal mazzo");
        Button firstE = new Button("Pesca dall'avversario");
        Button secondE = new Button("Cerca le coppie");
        Button thirdE = new Button("Pesca dal mazzo");
        secondP.setDisable(true);
        thirdP.setDisable(true);
        firstE.setDisable(true);
        secondE.setDisable(true);
        thirdE.setDisable(true);
        firstP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Random rm = new Random();
                int n = rm.nextInt(adversary.size());
                Carta b = (Carta) adversary.get(n);
                player.add(b);
                adversary.remove(b);
                player.sort();
                populate(Phand, player);
                populate(Ehand, adversary);
                System.out.print("Pippo: ");
                printcards(player);
                System.out.println("===");
                firstP.setDisable(true);
                secondP.setDisable(false);
                if(adversary.size() == 0){
                    Vittoria vt = new Vittoria("Pluto");
                    System.out.println("Pluto ha vinto");
                }
                }
        });
        final boolean[] discarded = new boolean[1];
        secondP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                discarded[0] = player.rmPair(player);
                populate(Phand, player);
                System.out.print("Pippo: ");
                printcards(player);
                System.out.println("===");
                secondP.setDisable(true);
                thirdP.setDisable(false);
                if(player.size() == 0){
                    Vittoria vt = new Vittoria("Pippo");
                    System.out.println("Pippo ha vinto");
                }
            }
        });
        thirdP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (discarded[0]){
                    System.out.println("PIPPO: ho scartato, non pesco");
                }
                else{
                    if(d.size() != 0) {
                        Carta c = (Carta) d.get(0);
                        player.add(c);
                        d.remove(c);
                    }
                }
                player.sort();
                populate(Phand, player);
                System.out.print("Pippo: ");
                printcards(player);
                System.out.println("===");
                thirdP.setDisable(true);
                firstE.setDisable(false);
            }
        });
        firstE.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Random rm = new Random();
                int n = rm.nextInt(player.size());
                Carta b = (Carta) player.get(n);
                adversary.add(b);
                player.remove(b);
                adversary.sort();
                populate(Phand, player);
                populate(Ehand, adversary);
                System.out.print("Pluto: ");
                printcards(adversary);
                System.out.println("===");
                firstE.setDisable(true);
                secondE.setDisable(false);
                if(player.size() == 0){
                    Vittoria vt = new Vittoria("Pippo");
                    System.out.println("Pippo ha vinto");
                }
            }
        });
        secondE.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                discarded[0] = adversary.rmPair(adversary);
                populate(Ehand, adversary);
                System.out.print("Pluto: ");
                printcards(adversary);
                System.out.println("===");
                secondE.setDisable(true);
                thirdE.setDisable(false);
                if(adversary.size() == 0){
                    Vittoria vt = new Vittoria("Pluto");
                    System.out.println("Pluto ha vinto");
                }
            }
        });
        thirdE.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (discarded[0]){
                    System.out.println("PLUTO: ho scartato, non pesco");
                }
                else{
                    if(d.size()!=0) {
                        Carta c = (Carta) d.get(0);
                        adversary.add(c);
                        d.remove(c);
                    }
                }
                adversary.sort();
                populate(Ehand, adversary);
                System.out.print("Pluto: ");
                printcards(adversary);
                System.out.println("===");
                thirdE.setDisable(true);
                firstP.setDisable(false);
            }
        });
        Label pippo = new Label("Pippo");
        Label pluto = new Label("Pluto");
        HBox p = new HBox();
        HBox a = new HBox();
        p.getChildren().addAll(pippo, firstP, secondP, thirdP);
        a.getChildren().addAll(pluto, firstE, secondE, thirdE);
        p.setSpacing(25);
        a.setSpacing(25);
        ap.getChildren().addAll(a, p, Phand, Ehand);
        ap.setTopAnchor(a, 5.0);
        ap.setBottomAnchor(p, 5.0);
        ap.setTopAnchor(Ehand, 35.0);
        ap.setBottomAnchor(Phand, 35.0);
        ap.setLeftAnchor(Phand, 180.0);
        ap.setLeftAnchor(Ehand, 180.0);
        Ehand.setAlignment(Pos.CENTER);
        Phand.setAlignment(Pos.CENTER);
        primaryStage.setTitle("Name Surname");
        primaryStage.setScene(new Scene(ap, 600, 300));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    void test(){
        Deck d = new Deck();
        d.shuffle();
        printcards(d);
        d.ordina();
        printcards(d);
    }

    void giveInitialCard(Mano m, Deck d){
        Carta trust;
        for(int i=0; i<4; i++){
            trust = (Carta) d.get(i);
            m.add(trust);
            d.remove(trust);
        }
    }
    void printcards(ArrayList d){
        for(int i=0; i<d.size(); i++){
            Carta g = (Carta) d.get(i);
            System.out.print(g.toString() + " ");
        }
        System.out.println();
    }

    void populate(HBox hb, ArrayList b){
        hb.getChildren().clear();
        for(int i=0; i<b.size(); i++){
          Rectangle rec = new Rectangle(50, 50, AQUAMARINE);
          rec.setStroke(BLACK);
          Carta f = (Carta) b.get(i);
          Label tx = new Label(f.toString());
          StackPane sp = new StackPane();
          sp.getChildren().addAll(rec, tx);
          hb.getChildren().add(sp);
        }
    }
}
