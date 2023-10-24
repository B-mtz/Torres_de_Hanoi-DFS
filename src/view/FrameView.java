package view;


import model.Disk;
import model.Node;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Stack;

public class FrameView extends JFrame {
    private Color colorColumn = new Color(22, 82, 101), colorB1 = new Color(255, 112, 60);
    private Color colorB2 = new Color(254, 152, 61), colorB3 = new Color(254, 202, 60);
    private JButton btnStart;
    private int[] yPosition = {300, 270, 240};
    private Stack<Disk> tower1 = new Stack<>();
    private Stack<Disk> tower2 = new Stack<>();
    private Stack<Disk> tower3 = new Stack<>();
    private Disk disk1 = new Disk(100, 300, 130, 30, 1, colorB1);
    private Disk disk2 = new Disk(120, 270, 90, 30, 2, colorB2);
    private Disk disk3 = new Disk(140, 240, 50, 30, 3, colorB3);

    public FrameView() {
        super("Graficos");
        this.setSize(620, 400);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setContentPane(contentPane);

        btnStart = new JButton("Reiniciar");
        btnStart.setBackground(colorB1);
        btnStart.setBorderPainted(false);
        btnStart.setFocusPainted(false);
        contentPane.add(btnStart);

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
        super.paint(g);

        // Dibuja las columnas
        g.setColor(colorColumn);
        g.fillRect(155, 180, 25, 150);
        g.fillRect(305, 180, 25, 150);
        g.fillRect(455, 180, 25, 150);
        g.fillRect(100, 330, 130, 10);
        g.fillRect(250, 330, 130, 10);
        g.fillRect(400, 330, 130, 10);

        // Dibuja los discos en las torres
        //Torre 1 + 0 en x
        drawDisk(g, tower1, 0);
        //Torre 2 + 150 en x
        drawDisk(g, tower2, 150);
        //Torre 3 + 300e n x
        drawDisk(g, tower3, 300);

    }

    //Dibuja los discos de la torre que recibe
    public void drawDisk(Graphics g, Stack<Disk> tower, int incr) {
        if (tower.size() == 1) {
            if (tower.peek().getValue() == 3) {
                g.setColor(tower.peek().getColor());
                g.fillRect(tower.peek().getX() + incr, yPosition[0], tower.peek().getW(), tower.peek().getH());
            } else if (tower.peek().getValue() == 2) {
                g.setColor(tower.peek().getColor());
                g.fillRect(tower.peek().getX() + incr, yPosition[0], tower.peek().getW(), tower.peek().getH());
            } else {
                g.setColor(tower.peek().getColor());
                g.fillRect(tower.peek().getX() + incr, tower.peek().getY(), tower.peek().getW(), tower.peek().getH());
            }
        } else if (tower.size() == 2) {
            Disk disk = tower.peek();
            //Si son 2 el primero va a la mitad
            if (disk.getValue() == 2 || disk.getValue() == 3) {
                g.setColor(disk.getColor());
                g.fillRect(disk.getX() + incr, yPosition[1], disk.getW(), disk.getH());
            } else {
                g.setColor(disk.getColor());
                g.fillRect(disk.getX() + incr, yPosition[0], disk.getW(), disk.getH());
            }
            //El segundo abajo
            disk = tower.get(0);
            if (disk.getValue() == 2 || disk.getValue() == 3) {
                g.setColor(disk.getColor());
                g.fillRect(disk.getX() + incr, yPosition[0], disk.getW(), disk.getH());
            } else {
                g.setColor(disk.getColor());
                g.fillRect(disk.getX() + incr, yPosition[0], disk.getW(), disk.getH());
            }
        } else {
            for (Disk disk : tower) {
                g.setColor(disk.getColor());
                g.fillRect(disk.getX() + incr, disk.getY(), disk.getW(), disk.getH());
            }
        }
    }

    //Genera el estado de la ruta que recibe para mostrarlo
    public void generateRoute(Node aux) {
        tower1.clear();
        tower2.clear();
        tower3.clear();

        Stack<Integer> tower1Contents = aux.getTower1();
        Stack<Integer> tower2Contents = aux.getTower2();
        Stack<Integer> tower3Contents = aux.getTower3();

        for (int diskValue : tower1Contents) {
            if (diskValue == 1) {
                tower1.add(disk3);
            } else if (diskValue == 2) {
                tower1.add(disk2);
            } else if (diskValue == 3) {
                tower1.add(disk1);
            }
        }
        for (int diskValue : tower2Contents) {
            if (diskValue == 1) {
                tower2.add(disk3);
            } else if (diskValue == 2) {
                tower2.add(disk2);
            } else if (diskValue == 3) {
                tower2.add(disk1);
            }
        }
        for (int diskValue : tower3Contents) {
            if (diskValue == 1) {
                tower3.add(disk3);
            } else if (diskValue == 2) {
                tower3.add(disk2);
            } else if (diskValue == 3) {
                tower3.add(disk1);
            }
        }
    }
    public JButton getBtnStart() {
        return btnStart;
    }
}
