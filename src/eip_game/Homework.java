/*
    Copyright (C) 2016, Sam Cooper-Drake, all rights reserved.
    Bush School EIP 2015/2016 Video Game Development Project
*/

package eip_game;

/**
 *
 * @author Sam
 */
public class Homework extends GameObject {

    boolean isCollected;
    
    public Homework(Model model, int nodeA, int nodeB, float pos) {
        super(model, nodeA, nodeB, pos);
        
        isCollected = false;        
    }
    
}
