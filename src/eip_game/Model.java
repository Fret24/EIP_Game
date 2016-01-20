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
    float collisionDist = 64;    
    Map map;
    Player player;
    HallMonitor[] hallMonitors;
    Homework[] homework;
    boolean gameOver;
    
    Model(int width, int height) {
        gameOver = false;
        map = new Map(width, height);
        
        player = new Player(this, 0, 1, 0);
        
        hallMonitors = new HallMonitor[] {
            new HallMonitor(this, 4, 5, 0),
            new HallMonitor(this, 5, 8, 0),
            new HallMonitor(this, 11, 10, 0),
            new HallMonitor(this, 10, 7, 0)            
        };
        homework = new Homework[] {
            new Homework(this, 15, 14, 0)
        };
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
            if (testCollision(player, homework[i])) {
                // to do
            }
        }
        
    } 
    
    void update() {
        testCollisions();
        
        for (int i = 0; i < hallMonitors.length; ++i) {
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
 