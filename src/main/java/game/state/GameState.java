package game.state;

import controller.NPCController;
import core.Size;
import entity.Enemy;
import entity.npc.OldMan;
import game.Input;
import game.ui.UIGameTime;
import game.ui.UIHealthStatistics;
import map.GameMap;

import java.io.IOException;

public class GameState extends  State{

    public GameState(Size windowSize, Input input) throws IOException {
        super(windowSize, input);
        _gameMap = new GameMap(new Size(20, 20));
        initializeScene();
        initializeUi(windowSize);
    }

    private void initializeScene(){
        //SelectionCircle selectionCircle = new SelectionCircle();
        //selectionCircle.setParent(_player);
        //gameObjects.add(selectionCircle);
        initializeEnemy(0);
        intializeNpc(0);
    }

    private void intializeNpc(int count) {
        for (int i = 0; i < count; i++){
            OldMan oldMan = new OldMan(new NPCController(), getRandomPosition());
            gameObjects.add(oldMan);
        }
    }

    private  void initializeUi(Size windowSize){
        //containerEnd.addUIComponent(new UIHealthStatistics(windowSize));
        uiContainers.add(new UIGameTime(windowSize));
        uiContainers.add(new UIHealthStatistics(windowSize));
        //_uiContainers.add(containerEnd);
    }
    private  void initializeEnemy(int enemyCount){
        for (int i = 0; i < enemyCount; i++){
            Enemy enemy = new Enemy(new NPCController(), getRandomPosition());
            gameObjects.add(enemy);
        }
    }
}
