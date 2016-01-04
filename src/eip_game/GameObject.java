/*
    Copyright (C) 2016, Sam Cooper-Drake, all rights reserved.
    Bush School EIP 2015/2016 Video Game Development Project
*/

package eip_game;

/**
 *
 * @author Sam
 */
public class GameObject {
    Map.Location location;
    Model model;

    public GameObject(Model model, int nodeA, int nodeB, float pos) {
        this.model = model;
        location = model.map.place(nodeA, nodeB, pos);
        
    }

    void update() {
        
    }    
    
}


