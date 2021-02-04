//Page 391 in the JavaFX Textbook
package spherejavafx;

import java.util.*;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SphereJavaFX extends Application {
    
    private static String[][] gameBoard = new String[10][10];
    private static int x;
    private static int y;
    private static int score; 
    
    
    

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();

        //root.getChildren().add(btn);
        Scene scene = new Scene(root, 300, 300);
        PerspectiveCamera camera = new PerspectiveCamera(true);

        //Backs the camera away from the scene by 1000 units
        camera.setTranslateZ(-1000);

        //This is the range of which the camera will render objects
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);

        //The default field of view for the scene is 30 but change to suit
        camera.setFieldOfView(30);
        scene.setCamera(camera);

        //This sets up my sphere
        Sphere mysphere = new Sphere(200);
        mysphere.setTranslateX(-180);
        mysphere.setTranslateY(-100);
        mysphere.setTranslateZ(100);
        root.getChildren().add(mysphere);

        //This sets up the image of the earth to wrap around my sphere
        Image earthImage = new Image("file:earth.jpg");
        PhongMaterial earthPhong = new PhongMaterial();
        earthPhong.setDiffuseMap(earthImage);
        mysphere.setMaterial(earthPhong);

        //This rotates my sphere
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(mysphere);
        rotate.setDuration(Duration.millis(5000));
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.setByAngle(-360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.play();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

        score = 0;
        setUpBoard();
        addTreasure();

        for (int i = 0; i < 5; i++) {
            coordinateUserGuess();
            checkForTreasure();

        }
        printBoard();
        System.out.println("Your final score is: " + score);

    }

    public static void setUpBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gameBoard[i][j] = "[  ]";

            }

        }

    }

    public static void printBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(gameBoard[i][j]);

            }
            System.out.println("");
        }

    }

    public static void addTreasure() {
        Random random = new Random();
        int numberofTreasureItems = random.nextInt((50 - 15) + 1) + 15;

        for (int i = 0; i < numberofTreasureItems; i++) {
            gameBoard[random.nextInt(10)][random.nextInt(10)] = "[" + (random.nextInt((99 - 10) - 1) + 10) + "]";

        }

    }

    public static void coordinateUserGuess() {

        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("please enter in x-coordinate which is the row (any number between 0 and 9)");
                int x = input.nextInt();

                System.out.println("please enter in y-coordiante which is the column (any number between 0 and 9)");
                int y = input.nextInt();

                // only accepts numbers between 0 and 9 to  atch the gameboard size
                if (x >= 0 && x <= 9 && y >= 0 && y <= 9) {
                    break;

                }

            } catch (Exception e) {
                System.out.println(" there has been an ERROR " + e);
                System.out.println("please tpe in only interger numbers between 0 and 9");

            }

        }

    }

    public static void checkForTreasure() {
        if (!gameBoard[x][y].substring(1, 3).equals("  ")) {
            score = score + Integer.parseInt(gameBoard[x][y].substring(1, 3));
            System.out.println(score);
        }

    }

}


