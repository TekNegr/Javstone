package Ressources.FX;
import javax.swing.*;
import java.awt.*;
public class Credits extends swingControler{
    private JButton GoToMainbutton, QuitButton;
    public Credits(GUI g){
        super(400,300,"Credits",g);
        preparePanel();
    }


    public void preparePanel(){
        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(2,1));

        String text = """
                CREDITS DU JEUX :\n
                \n
                CREATEURS : \n
                Henintsoa RAMAKAVELO\n
                Xavier OGANDAGA\n
                \n
                RESSOURCES : \n
                Front : java swing (auteur : Oracle)\n
                Graphics Cartes : Free Printable Tarot Card Deck (auteur : ???)\n
                """;


        this.panel.add(new JLabel(text));

        JPanel ButtonPanel = new JPanel(new GridLayout(1,2));

        GoToMainbutton = new JButton("Main");
        GoToMainbutton.addActionListener(e->{
            this.gui.goToMainMenu();
        });
        ButtonPanel.add(GoToMainbutton);

        QuitButton =  new JButton("Quit");
        QuitButton.addActionListener(e->{
            this.gui.quit();
        });
        ButtonPanel.add(QuitButton);

        this.panel.add(ButtonPanel);
        this.addPanel();
    }
}
