package gameStates;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Help extends GameState{
    public Help(StateHandler handler) {
        super(handler);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("HELP", 50, 40);
        g.drawString("ARROWS - control snake, choose", 50, 70);
        g.drawString("Enter - select", 50, 90);
        g.drawString("E - enable/disable grid", 50, 110);
        g.drawString("R - restart", 50, 130);
        g.drawString("P - pause/continue", 50, 150);
        g.drawString("M - Main Menu", 50, 170);
        g.drawString("ESC - escape", 50, 190);
        g.drawString("Press Enter to go back to Main Menu", 50, 230);
    }

    @Override
    public void tick() {

    }

    @Override
    public void keyPressed(int key) {
        if (key == KeyEvent.VK_ENTER) {
            handler.setState(StateName.MAINMENU);
        }
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
    }
}
