/*
    Copyright (C) 2016, Sam Cooper-Drake, all rights reserved.
    Bush School EIP 2015/2016 Video Game Development Project
*/
package eip_game;

import processing.core.*;

public class EIP_Game extends PApplet {
    Model model;
    PImage mapImage;
    PImage player;
    PImage hallMonitor;

    @Override
    public void settings() {
        size(1280, 800);
    }
    
    @Override
    public void setup() {
        model = new Model(width, height);
        mapImage = loadImage("GameScreen.png");
        hallMonitor = loadImage("hallmonitor.png");
        player = loadImage("Trump=Chump.png");
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
    
    void drawGameObject(GameObject go, PImage img) {
        image(img, go.location.x, go.location.y);
    }
    
    void drawGameObjects() {
        pushStyle();
        imageMode(CENTER);
        drawGameObject(model.player, player);
        for (int i = 0; i < model.hallMonitors.length; ++i) {
            drawGameObject(model.hallMonitors[i], hallMonitor);
        }
        popStyle();
    }
    
    @Override
    public void draw() {
        model.update();
        
        image(mapImage, 0, 0);
//        drawMapGraph();

        drawGameObjects();
        
    
        
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
