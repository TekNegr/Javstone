package Ressources.FX;
import javax.swing.*;
import Ressources.GameClasses.*;
public class GUI {
    public Game game;
    private swingControler sC;


    public GUI(){
        this.init();
    }


    public void init(){
        goToMainMenu();
    }


    public void goToMainMenu(){
        if(sC!=null){
            sC.close();
        }
        sC = new MainMenu(this);
        sC.show();
    }

    public void goToGamePreparer(){
        if(sC!=null){
            sC.close();
        }
        sC = new GamePreparer(this);
        sC.show();
    }

    public void goToCredits(){
        if(sC!=null){
            sC.close();
        }
        sC = new Credits(this);
        sC.show();
    }

    public void goToGame(){
        System.out.println("Going to Game");
        if(sC!=null){
            sC.close();
        }
        sC = new GameArena(this, game);
        sC.show();
    }

    public void pauseGame(){}

    public void quit(){
        game = null;
        sC.close();
    }
}
