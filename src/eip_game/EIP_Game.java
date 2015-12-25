
package eip_game;

import processing.core.*;

public class EIP_Game extends PApplet {
    Model model;
    PImage mapImage;
    PImage player;
    Map.Location playerLoc;
    float playerStep = 5f;

    @Override
    public void settings() {
        size(1280, 800);
    }
    
    @Override
    public void setup() {
        model = new Model(width, height);
        mapImage = loadImage("GameScreen.png");
        mapImage.resize(width, height);
//        player = loadImage("Trump=Chump.png");
        player = loadImage("skinsofHW.png");
        playerLoc = model.map.place(0, 1, 0f);
    }

    void drawNeighbor(int i, int dir) {
        Map.Node node = model.map.nodes[i];
        
        if (dir != Map.Node.NO_PATH ) {
            if (dir > i) {
                Map.Node other = model.map.nodes[dir];
                
                line(node.x, node.y, other.x, other.y);
            }
        }
    }
    
    void drawMapGraph() {
        for (int i = 0; i < model.map.nodes.length; i = i + 1) {
            // i will go from 0 to 15 one at a time
            Map.Node node = model.map.nodes[i];
            // node is a poionter to make it easier to write this code            
            for (int j = 0; j < node.dirs.length; j = j + 1) {
                drawNeighbor(i, node.dirs[j]);              
            }        
        }
        
        for (int i = 0; i < model.map.nodes.length; i = i + 1) {
            // i will go from 0 to 15 one at a time
            Map.Node node = model.map.nodes[i];
            
            ellipse(node.x, node.y, 10, 10);

        }

    }
    
    @Override
    public void draw() {
        background(11, 200, 254);
        image(mapImage, 0, 0);
        drawMapGraph();
        imageMode(CENTER);
        image(player, playerLoc.x, playerLoc.y);
        imageMode(CORNER);

//        playerLoc.move(playerStep);
        
        int n = playerLoc.closeToNode(5);
        
        if (n == playerLoc.nodeB) {
            Map.Node node = model.map.nodes[n];
            
            while (true) {
                int i = (int)(Math.random() * node.dirs.length);
                if (node.dirs[i] != Map.Node.NO_PATH) {
                    playerLoc = model.map.place(n, node.dirs[i], 0);
                    break;
                }
            }
            
        }
        
//        if (keyPressed && key == 'g') {
//            g.save("/Users/Sam/Desktop/GameScreen.png");
//        }
        
//        if (keyPressed) {
//            if (key == 'd') {
//            }
//            if (key == 'a') {
//            }
//             if (key == 'w') {
//             }
//             if (key == 's') {
//             }
//        }
    }
  
    public static void main(String[] args) {
       PApplet.main(eip_game.EIP_Game.class.getName());
    }
    
}
