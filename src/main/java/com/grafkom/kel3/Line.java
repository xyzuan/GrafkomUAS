package com.grafkom.kel3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Line extends Application {
    private List<Double> coordinates;
    private boolean isDrawing;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(500, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);

        coordinates = new ArrayList<>();
        isDrawing = false;

        canvas.setOnMousePressed(event -> {
            if (!isDrawing) {
                startDrawing(event.getX(), event.getY());
                isDrawing = true;
                redraw(canvas, gc);
                isDrawing = false;
            }
        });

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setTitle("Membuat Garis - Inputan Mouse");
        primaryStage.show();
    }

    private void startDrawing(double x, double y) {
        coordinates.add(x);
        coordinates.add(y);
    }

    private void continueDrawing(double x, double y) {
        coordinates.add(x);
        coordinates.add(y);
    }

    private void endDrawing(double x, double y) {
        coordinates.add(x);
        coordinates.add(y);
    }

    private void redraw(Canvas canvas, GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int i = 0; i < coordinates.size() - 2; i += 2) {
            double startX = coordinates.get(i);
            double startY = coordinates.get(i + 1);
            double endX = coordinates.get(i + 2);
            double endY = coordinates.get(i + 3);

            gc.strokeLine(startX, startY, endX, endY);
        }

        // Menampilkan koordinat setiap titik
        gc.setFill(Color.RED);
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        for (int i = 0; i < coordinates.size() - 1; i += 2) {
            double x = coordinates.get(i);
            double y = coordinates.get(i + 1);
            gc.fillText(String.format("Titik - %d", i/2 + 1), x + 5, y - 20);
            gc.fillText(String.format("(%.2f, %.2f)", x, y), x + 5, y - 5);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
