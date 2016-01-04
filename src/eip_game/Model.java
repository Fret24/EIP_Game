/*
    Copyright (C) 2016, Sam Cooper-Drake, all rights reserved.
    Bush School EIP 2015/2016 Video Game Development Project
*/
/*
This is the model for my game.
It has the data and behavors of the game out side of the view.
*/
package eip_game;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sam
 */
public class Model {
    Map map;
    List<GameObject> objects;
    Player player;
    HallMonitor[] hallMonitors;
    
    Model(int width, int height) {
        map = new Map(width, height);
        objects = new ArrayList<>();
        
        player = new Player(this, 0, 1, 0);
        objects.add(player);
        
        hallMonitors = new HallMonitor[] {
            new HallMonitor(this, 4, 5, 0),
            new HallMonitor(this, 5, 8, 0),
            new HallMonitor(this, 11, 10, 0),
            new HallMonitor(this, 10, 7, 0)            
        };
        
        for (int i = 0; i < hallMonitors.length; ++i) {
            objects.add(hallMonitors[i]);
        }  
    }
    
    void update() {
        for (GameObject go: objects) {
            go.update();
        }
    }
}
