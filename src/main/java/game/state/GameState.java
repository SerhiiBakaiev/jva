package game.state;

import controller.EnemyController;
import core.Size;
import entity.Enemy;
import entity.SelectionCircle;
import game.Input;
import game.ui.UIGameTime;
import game.ui.UIHealthStatistics;
import map.GameMap;
import ui.*;

import java.awt.*;
import java.io.IOException;

public class GameState extends  State{

    public GameState(Size windowSize, Input input) throws IOException {
        super(windowSize, input);
        _gameMap = new GameMap(new Size(20, 20), _spriteLibrary);
        initializeScene();
        initializeUi(windowSize);
    }

    private void initializeScene(){
        SelectionCircle selectionCircle = new SelectionCircle();
        selectionCircle.setParent(_player);
        _gameObjects.add(selectionCircle);
        initializeEnemy(0);
    }

    private  void initializeUi(Size windowSize){
        //containerEnd.addUIComponent(new UIHealthStatistics(windowSize));
        _uiContainers.add(new UIGameTime(windowSize));
        _uiContainers.add(new UIHealthStatistics(windowSize));
        //_uiContainers.add(containerEnd);
    }
    private  void initializeEnemy(int enemyCount){
        for (int i = 0; i < enemyCount; i++){
            Enemy enemy = new Enemy(new EnemyController(), _spriteLibrary);
            enemy.setPosition(getRandomPosition());
            _gameObjects.add(enemy);
        }
    }
}
