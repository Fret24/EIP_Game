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
    int changeDirsHowClose = 35;

    public Player(Model model, int nodeA, int nodeB, float pos) {
        super(model, nodeA, nodeB, pos);
    }

    @Override
    void update() {

    }

    private void moveByStep(int dir) {
        location.move(stepSize);

        int n = location.closeToNode(howClose);

        if (n == location.nodeB) {
            Map.Node node = model.map.nodes[n];
            if (node.dirs[dir] != Map.Node.NO_PATH) {
                location.set(location.nodeB, node.dirs[dir], 0);
            }
        }
    }

    private void changeDir(int dir) {
        int n = location.closeToNode(changeDirsHowClose);
        if (n != Map.Node.NO_PATH) {
            Map.Node node = model.map.nodes[n];
            if (node.dirs[dir] != Map.Node.NO_PATH) {
                location.set(n, node.dirs[dir], 0);
            }
        }
    }

    private void moveVertical(int dir) {
        int curDir = location.getDirection();
        if (curDir == Map.oppositeDir(dir)) {
            location.flipDirection();
            moveByStep(dir);
        } else if (curDir == dir) {
            moveByStep(dir);
        } else if (curDir == Map.Right || curDir == Map.Left) {
            changeDir(dir);
        }        
    }
    
    private void moveHorizontal(int dir) {
        int curDir = location.getDirection();
        if (curDir == Map.oppositeDir(dir)) {
            location.flipDirection();
            moveByStep(dir);
        } else if (curDir == dir) {
            moveByStep(dir);
        } else if (curDir == Map.Up || curDir == Map.Down) {
            changeDir(dir);
        }        
    }
    
    
    void moveUp() {
        moveVertical(Map.Up);
    }

    void moveDown() {
        moveVertical(Map.Down);        
    }

    void moveRight() {
        moveHorizontal(Map.Right);
    }

    void moveLeft() {
        moveHorizontal(Map.Left);
    }

}
