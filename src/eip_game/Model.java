/*
    Copyright (C) 2015, Sam Cooper-Drake, all rights reserved.
    Bush School EIP 2015/2016 Video Game Development Project
*/
/*
This is the model for my game.
It has the data and behavors of the game out side of the view.
*/
package eip_game;

/**
 *
 * @author Sam
 */
public class Model {
    Map map;
    
    Model(int width, int height) {
        map = new Map(width, height);
    }
}
