/*
    Copyright (C) 2016, Sam Cooper-Drake, all rights reserved.
    Bush School EIP 2015/2016 Video Game Development Project
*/

/*
 * This is the model of the game map
 */
package eip_game;

import processing.core.PApplet;

/**
 *
 * @author Sam
 */
public class Map {
    
    static final int 
            Up = 0,
            Down = 1,
            Left = 2,
            Right = 3;
            
    static int oppositeDir(int dir) {
        if (dir == Up) return Down;
        if (dir == Down) return Up;
        if (dir == Left) return Right;
        return Left;      
    }
            
    static class Node {
        static final int NO_PATH = -1;
        int x;
        int y;
        int[] dirs;
       
        
        Node(int x, int y, int up, int down, int left, int right) {
            this.x = x;
            this.y = y;
            dirs = new int[] {
                up,
                down,
                left,
                right
            };
        }
    }

    Node[] nodes = new Node[] {
        new Node(60, 740, Node.NO_PATH, 6, Node.NO_PATH, 1),
        new Node(400, 740, Node.NO_PATH, 4, 0, 2),
        new Node(800, 740, Node.NO_PATH, 5, 1, 3),
        new Node(1200, 740, Node.NO_PATH, 9, 2, Node.NO_PATH),
        new Node(400, 600, 1, 7, Node.NO_PATH, 5),
        new Node(800, 600, 2, 8, 4, Node.NO_PATH),
        new Node(60, 400, 0, 12, Node.NO_PATH, 7),
        new Node(400, 400, 4, 10, 6 , Node.NO_PATH),
        new Node(800, 400, 5, 11, Node.NO_PATH, 9),
        new Node(1200, 400, 3, 15, 8, Node.NO_PATH),
        new Node(400, 200, 7, 13, Node.NO_PATH, 11),
        new Node(800, 200, 8, 14, 10, Node.NO_PATH),
        new Node(60, 60, 6, Node.NO_PATH, Node.NO_PATH, 13),
        new Node(400, 60, 10, Node.NO_PATH, 12, 14),
        new Node(800, 60, 11, Node.NO_PATH, 13, 15),
        new Node(1200, 60, 9, Node.NO_PATH, 14, Node.NO_PATH)
    };
    
    class Location {
        int nodeA;
        int nodeB;
        float pos;
        int x;
        int y;
        
        Location(int nodeA, int nodeB, float pos) {
            set(nodeA, nodeB, pos);
        }
        
        final void setPos(float newPos) {
            if (newPos < 0) {
                newPos = 0;
            }
            if (newPos > 1) {
                newPos = 1;
            }
            pos = newPos;
            x = Math.round((1 - pos)*nodes[nodeA].x + pos*nodes[nodeB].x);
            y = Math.round((1 - pos)*nodes[nodeA].y + pos*nodes[nodeB].y);                  
        }
        
        int closeToNode(float maxDist) {
            float d; 
            d = PApplet.dist(x, y, nodes[nodeA].x, nodes[nodeA].y);
            if (d < maxDist) {
                return nodeA;
            }
            
            d = PApplet.dist(x, y, nodes[nodeB].x, nodes[nodeB].y);
            if (d < maxDist) {
                return nodeB;
            }
            return Node.NO_PATH;
        }
        
        /**
         * moves location by distance pixels. Positive is from nodeA to nodeB.
         * @param distance in pixels to move
         */
        void move(float distance) {
            float f = distance/PApplet.dist(nodes[nodeA].x, nodes[nodeA].y, nodes[nodeB].x, nodes[nodeB].y);
            setPos(pos + f);
        }
        
        /**
         * defines what direction the line is going. 
         * @return 
         */
        int getDirection() {
            int deltaY = nodes[nodeB].y - nodes[nodeA].y;
            int deltaX = nodes[nodeB].x - nodes[nodeA].x;
            
            if (Math.abs(deltaY) > Math.abs(deltaX)) {
                // vertical line
                if (deltaY > 0) {
                    return Down;
                }
                else {
                    return Up;
                }
            }
            else {
                //  horizontal line
                if (deltaX > 0) {
                    return Right;
                }
                else {
                    return Left;
                }
            }
        }
        
        void flipDirection() {
            int temp = nodeA;
            nodeA = nodeB;
            nodeB = temp;
            pos = 1 - pos;
        }
        
        final void set(int nodeA, int nodeB, float pos) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            setPos(pos);
        }
    }
    
    int width;
    int height;
    
    
    Map(int width, int height) {
        this.width = width;
        this.height = height;
        // For processing we need (0, 0) at the top, left.
        for (int i = 0; i < nodes.length; i = i + 1) {
            nodes[i].y = height - nodes[i].y;          
        }
    }
    
    /**
     * create location between nodeA and nodeB at relative pos. 
     * @param nodeA
     * @param nodeB
     * @param pos
     * @return 
     */
    Location place(int nodeA, int nodeB, float pos) {
        return new Location(nodeA, nodeB, pos);
    }
    
}
