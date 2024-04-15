package Ressources.FX;
import javax.swing.*;


public class MainMenu extends swingControler{
    //Create a simple swing menu to with 3 buttons : Start, Credits, Quit
    public MainMenu(GUI g){
        super(400,300,"MainMenu",g);//this makes it able to create the frame and set its title from parent class
        preparePanel();
    }

    public void preparePanel(){
        this.panel = new JPanel();
        addButtonsToPanel();
        this.addPanel();
    }

    private void addButtonsToPanel() {
        JButton startButton = new JButton("Start");
        JButton creditsButton = new JButton("Credits");
        JButton quitButton = new JButton("Quit");

        startButton.addActionListener(e -> {
            gui.goToGamePreparer();
        });

        creditsButton.addActionListener(e -> {
            gui.goToCredits();
        });

        quitButton.addActionListener(e -> {
            gui.quit();
        });

        // Add buttons to the panel (assuming the panel is a JPanel)
        panel.add(startButton);
        panel.add(creditsButton);
        panel.add(quitButton);
    }
}
