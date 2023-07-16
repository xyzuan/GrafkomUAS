package com.grafkom.kel3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class Transformation extends JFrame {
    private Point initialMousePosition;
    private AffineTransform transform;
    private JLabel transformedPositionLabel;

    public Transformation() {
        setTitle("Transformasi - Inputan Mouse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        transform = new AffineTransform();
        transformedPositionLabel = new JLabel();

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialMousePosition = e.getPoint();
            }

            public void mouseReleased(MouseEvent e) {
                initialMousePosition = null;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (initialMousePosition != null) {
                    int dx = e.getX() - initialMousePosition.x;
                    int dy = e.getY() - initialMousePosition.y;
                    transform.translate(dx, dy);
                    updateTransformedPosition();
                    repaint();

                    initialMousePosition = e.getPoint();
                }
            }
        });

        // Menambahkan transformedPositionLabel ke content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(transformedPositionLabel, BorderLayout.NORTH);
    }

    private void updateTransformedPosition() {
        int transformedX = 250 + (int) transform.getTranslateX();
        int transformedY = 250 + (int) transform.getTranslateY();
        transformedPositionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        transformedPositionLabel.setText("Transformed Position: (" + transformedX + ", " + transformedY + ")");
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setTransform(transform);

        // Draw a rectangle at the transformed position
        g2d.setColor(Color.BLACK);
        g2d.drawRect(250, 250, 250, 250);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Transformation app = new Transformation();
                app.setVisible(true);
                app.updateTransformedPosition();
            }
        });
    }
}
