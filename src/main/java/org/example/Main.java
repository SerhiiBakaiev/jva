package org.example;

import core.Size;
import game.Game;
import game.GameLoop;
import game.Settings;

import java.io.IOException;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static  int WIDTH = 960;
    static  int HEIGHT = 960;

    public static void main(String[] args) throws IOException {

        Settings.getInstance().setDebugMode(true);
        Game game = new Game(WIDTH, HEIGHT);
        var gm = new GameLoop(game);
        gm.run();

    }
}