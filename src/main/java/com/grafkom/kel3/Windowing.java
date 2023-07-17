package com.grafkom.kel3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Windowing extends JFrame {
    private final Rectangle clipWindow;
    private final Rectangle viewport;

    public Windowing() {
        clipWindow = new Rectangle(63, 63, 500, 500);
        viewport = new Rectangle(174, 174, 300, 300);

        setTitle("Windowing - Inputan Mouse");
        setSize( 650, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Menggambar clip window
                g2d.setColor(Color.GRAY);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRect(clipWindow.x, clipWindow.y, clipWindow.width, clipWindow.height);

                // Menggambar viewport
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRect(viewport.x, viewport.y, viewport.width, viewport.height);

                // Menggambar objek di dalam viewport
                g2d.setColor(Color.BLUE);
                g2d.fillRect(viewport.x, viewport.y, viewport.width, viewport.height);

                // Menambahkan tampilan koordinat
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.PLAIN, 12));
                g2d.drawString("Clip Window: (" + clipWindow.x + ", " + clipWindow.y + ")", 10, 20);
                g2d.drawString("Viewport: (" + viewport.x + ", " + viewport.y + ")", 10, 40);
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                // Update viewport berdasarkan posisi klik mouse
                if (clipWindow.contains(mouseX, mouseY)) {
                    int viewportX = mouseX - viewport.width / 2;
                    int viewportY = mouseY - viewport.height / 2;
                    viewport.setLocation(viewportX, viewportY);
                    panel.repaint();
                }
            }
        });

        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Windowing windowing = new Windowing();
            windowing.setVisible(true);
        });
    }
}
