/*
    Copyright (C) 2016, Sam Cooper-Drake, all rights reserved.
    Bush School EIP 2015/2016 Video Game Development Project
*/

package eip_game;

/**
 *
 * @author Sam
 */
public class Player extends MovingGameObject {
    
    int stepSize = 6;
    int howClose = 5;
    
    public Player(Model model, int nodeA, int nodeB, float pos) {
        super(model, nodeA, nodeB, pos);
    }

    @Override
    void update() {
        
    }
    
    void moveUp() {
    }   
    
    void moveDown() {
        int dir = location.getDirection();
        if (dir == Map.Up) {
            location.flipDirection();
            location.move(stepSize);
            
            int n = location.closeToNode(howClose);
            
            if (n == location.nodeB) {
                Map.Node node = model.map.nodes[n];
                if (node.dirs[Map.Down] != Map.Node.NO_PATH) {
                    location.set(location.nodeB, node.dirs[Map.Down], 0);
                } 
            }
        }
        else if (dir == Map.Down) {
            location.move(stepSize);
            
            int n = location.closeToNode(howClose);
            
            if (n == location.nodeB) {
                Map.Node node = model.map.nodes[n];
                if (node.dirs[Map.Down] != Map.Node.NO_PATH) {
                    location.set(location.nodeB, node.dirs[Map.Down], 0);
                } 
            }
        }
        else if (dir == Map.Right || dir == Map.Left) {
            int n = location.closeToNode(howClose);
            if (n != Map.Node.NO_PATH) {
                Map.Node node = model.map.nodes[n];
                if (node.dirs[Map.Down] != Map.Node.NO_PATH) {
                    location.set(n, node.dirs[Map.Down], 0);
                }                 
            }
        }
    }
    
    void moveRight() {
        int dir = location.getDirection();
        if (dir == Map.Up) {
        }
        else if (dir == Map.Down) {
        }
        else if (dir == Map.Right) {
            location.move(stepSize);
            
            int n = location.closeToNode(howClose);
            
            if (n == location.nodeB) {
                Map.Node node = model.map.nodes[n];
                if (node.dirs[Map.Right] != Map.Node.NO_PATH) {
                    location.set(location.nodeB, node.dirs[Map.Right], 0);
                } 
            }
        }
        else if (dir == Map.Left) {
            location.flipDirection();
            location.move(stepSize);
            
            int n = location.closeToNode(howClose);
            
            if (n == location.nodeB) {
                Map.Node node = model.map.nodes[n];
                if (node.dirs[Map.Right] != Map.Node.NO_PATH) {
                    location.set(location.nodeB, node.dirs[Map.Right], 0);
                } 
            }
        }
    }
    
    void moveLeft() {
        int dir = location.getDirection();
        if (dir == Map.Up) {
        }
        else if (dir == Map.Down) {
        }
        else if (dir == Map.Left) {
            location.move(stepSize);
            
            int n = location.closeToNode(howClose);
            
            if (n == location.nodeB) {
                Map.Node node = model.map.nodes[n];
                if (node.dirs[Map.Left] != Map.Node.NO_PATH) {
                    location.set(location.nodeB, node.dirs[Map.Left], 0);
                } 
            }
        }
        else if (dir == Map.Right) {
            location.flipDirection();
            location.move(stepSize);
            
            int n = location.closeToNode(howClose);
            
            if (n == location.nodeB) {
                Map.Node node = model.map.nodes[n];
                if (node.dirs[Map.Left] != Map.Node.NO_PATH) {
                    location.set(location.nodeB, node.dirs[Map.Left], 0);
                } 
            }
        }
    }
    
    
}
