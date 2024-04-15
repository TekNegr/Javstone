package Ressources.FX;
import javax.swing.*;

import Ressources.GameClasses.*;
import java.awt.GridLayout;

public class GamePreparer extends swingControler{
    private JTextField player1Input;
    private JTextField player2Input;
    private JButton startGameButton;
    private Game game;

    public GamePreparer(GUI g) {
        super(400,300,"SetUp Game",g);
        preparePanel();
    }

    public void preparePanel(){
        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(3, 1));

        player1Input = new JTextField("Player 1");
        panel.add(player1Input);

        player2Input = new JTextField("Player 2");
        panel.add(player2Input);

        startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> {
            System.out.println("Preparing Game");
            if(player1Input.getText()!=null && player2Input.getText()!=null){
                Game gameCreated = new Game(player1Input.getText(),player2Input.getText());
                System.out.println("Starting Game");
                this.panel.add(new JLabel("Game Started"));
                this.gui.game = gameCreated;
                this.gui.goToGame();
            }}
        );
        panel.add(startGameButton);
        this.addPanel();        
    };

    public JTextField getPlayer1Input() {
        return player1Input;
    }

    public JTextField getPlayer2Input() {
        return player2Input;
    }
}
