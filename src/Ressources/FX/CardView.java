package Ressources.FX;
import javax.swing.*;
import java.awt.*;
import Ressources.GameClasses.*;


public class CardView {

    private Card card;
    private JPanel cardview;

    public CardView(Card c){
        this.card = c;
    }

    public JPanel prepareCardView(){
        this.cardview = new JPanel();
        this.cardview.setLayout(new GridLayout(3,1));
        JPanel statsPanel = new JPanel(new GridLayout(1,3));
        JLabel HP = new JLabel("HP : " + this.card.getHealth());
        statsPanel.add(HP);
        JLabel name = new JLabel(this.card.getName());
        statsPanel.add(name);
        JLabel Cost = new JLabel("Cost" +this.card.getCost());
        statsPanel.add(Cost);
        this.cardview.add(statsPanel);

        Image cardImg = card.getImage();
        this.cardview.add(new JLabel(new ImageIcon(cardImg)));
        JPanel footerPanel = new JPanel(new GridLayout(1,2));
        JLabel Effect = new JLabel("DMG :" + this.card.getDamage());
        footerPanel.add(Effect);
        JLabel emptySpace = new JLabel("");
        footerPanel.add(emptySpace);
        return this.cardview;
    }

    
}
