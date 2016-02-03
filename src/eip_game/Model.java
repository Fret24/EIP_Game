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
import processing.core.PApplet;

/**
 *
 * @author Sam
 */
public class Model {
    float collisionDist = 55;
    float hwSpacing = 50;
    Map map;
    Player player;
    HallMonitor[] hallMonitors;
    Homework[] homework;
    boolean gameOver;
    boolean youWin;
    
    Model(int width, int height) {
        gameOver = false;
        youWin = false;
        map = new Map(width, height);
        
        player = new Player(this, 0, 1, 0);
        
        hallMonitors = new HallMonitor[] {
            new HallMonitor(this, 4, 5, 0),
            new HallMonitor(this, 5, 8, 0),
            new HallMonitor(this, 11, 10, 0),
            new HallMonitor(this, 10, 7, 0)            
        };
        homework = buildHomework();
    }
    
    void layoutHwOnEdge(int a, int b, List<Homework> hw) {
        if (b == Map.Node.NO_PATH) {
            return;
        }
        if (b < a) {
            return;
        }
        float dist = PApplet.dist(map.nodes[a].x, map.nodes[a].y, map.nodes[b].x, map.nodes[b].y);
        int q = (int)(dist/hwSpacing);
        for (int i = 1; i <= q; ++i) {
            hw.add(new Homework(this, a, b, (float)i/q));        
        }
            
        

    }
    
    Homework[] buildHomework() {
        List<Homework> hw = new ArrayList<>();
        
        for (int i = 0; i < map.nodes.length; ++i) {
            layoutHwOnEdge(i, map.nodes[i].dirs[Map.Up], hw);
            layoutHwOnEdge(i, map.nodes[i].dirs[Map.Down], hw);
            layoutHwOnEdge(i, map.nodes[i].dirs[Map.Left], hw);
            layoutHwOnEdge(i, map.nodes[i].dirs[Map.Right], hw);            
        }
        return hw.toArray(new Homework[hw.size()]);
    }
    
    
    boolean testCollision(GameObject a, GameObject b) {
        float dist = PApplet.dist(a.location.x, a.location.y, b.location.x, b.location.y); 
        return dist < collisionDist; 
    }
    
    void testCollisions() {
        for (int i = 0; i < hallMonitors.length; ++i) {
            if (testCollision(player, hallMonitors[i])) {
                gameOver = true;
            }
        }
        for (int i = 0; i < homework.length; ++i) {
            if (!homework[i].isCollected && testCollision(player, homework[i])) {
                homework[i].isCollected = true;
                player.numOfHw++;
                if (player.numOfHw == homework.length) {
                    youWin = true;
                    gameOver = true;
                }
            }
        }
        
    } 
    
    void update() {
        testCollisions();
        
        float factor = (float)player.numOfHw / homework.length;
        
        player.setStepSize(factor);
        
        for (int i = 0; i < hallMonitors.length; ++i) {
            hallMonitors[i].setStepSize(factor);
            hallMonitors[i].update();
        }
    }
    
    void moveUp() {
        player.moveUp();
    }   
    
    void moveDown() {
        player.moveDown();
    }
    
    void moveLeft() {
        player.moveLeft();
    }
    
    void moveRight() {
        player.moveRight();
    }
    
}
 