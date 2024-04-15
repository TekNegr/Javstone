package Ressources.FX;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class swingControler {
    protected JFrame frame;
    protected JPanel panel;
    protected GUI gui;

    public swingControler(int h, int w, String t, GUI g){
        this.gui = g;
        frame = new JFrame();
        this.prepareFrame(h, w, t);
    }

    protected void prepareFrame(int height, int weight, String title){
        frame.setTitle(title);
        frame.setSize(height, weight);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public abstract void preparePanel();

    
    protected void addPanel(){
        frame.add(panel);
    }

    public void show(){
        frame.setVisible(true);
    }

    public void close(){
        frame.dispose();
    }
}
