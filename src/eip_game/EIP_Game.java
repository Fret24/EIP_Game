/*
    Copyright (C) 2016, Sam Cooper-Drake, all rights reserved.
    Bush School EIP 2015/2016 Video Game Development Project
 */
// this class is the view as well as the controller
package eip_game;

import processing.core.*;
import org.gamecontrolplus.*;


public class EIP_Game extends PApplet {

    Model model;
    PImage hallMonitorSprite;
    PImage hwSprite;
    PImage youlost;
    PImage youwin;
    
    PImage backgroundImage;
    PImage wolfBackground;
    PImage schoolBackground;

    PImage playerImage;
    PImage badPlayer;
    PImage goodPlayer;
    
    // Direct keyboard control
    ControlIO controlIO;
    ControlDevice keyboard = null;
    ControlButton[] buttons;

    @Override
    public void settings() {
        size(1280, 800, P2D);
    }

    @Override
    public void setup() {
        // Set up direct keyboard access.
        controlIO = ControlIO.getInstance(this);
        
        String devList = controlIO.deviceListToText("");
//        println(devList);
        
        for (ControlDevice dev: controlIO.getDevices()) {
            if (dev.getTypeName().equals("Keyboard") && dev.getName().toLowerCase().contains("apple")) {
                keyboard = dev;
                keyboard.open();
                break;
            }
        }
        
        if (keyboard != null) {
//            println(keyboard.buttonsToText(""));
        }
        if (keyboard == null) throw new AssertionError();
        buttons = new ControlButton[8];
        buttons[0] = keyboard.getButton("W");
        buttons[1] = keyboard.getButton("S");
        buttons[2] = keyboard.getButton("A");
        buttons[3] = keyboard.getButton("D");
        buttons[4] = keyboard.getButton("Up");
        buttons[5] = keyboard.getButton("Down");
        buttons[6] = keyboard.getButton("Left");
        buttons[7] = keyboard.getButton("Right");
        
        model = new Model(width, height);
        hallMonitorSprite = loadImage("Hallmonitor.png");
        hwSprite = loadImage("Homework.png");
        youwin = loadImage("you win.png");
        youlost = loadImage("you lost.png");
        
        schoolBackground = loadImage("GameScreen.png");
        wolfBackground = loadImage("WolfBoyNeonPink icon.png");
        
        goodPlayer = loadImage("Player.png");
        badPlayer = loadImage("BadPlayer.png");
        
        backgroundImage = schoolBackground;
        playerImage = goodPlayer;
    }

    void drawNeighbor(int i, int dir) {
        Map.Node node = model.map.nodes[i];

        if (dir != Map.Node.NO_PATH) {
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
        
        for (int i = 0; i < model.homework.length; ++i) {
            if (!model.homework[i].isCollected) {
                drawGameObject(model.homework[i], hwSprite);
            }            
        }

        for (int i = 0; i < model.hallMonitors.length; ++i) {
            drawGameObject(model.hallMonitors[i], hallMonitorSprite);
        }
        
        drawGameObject(model.player, playerImage);

        popStyle();
    }

    void checkInput() {
//        if (keyPressed && key == 'g') {
//            g.save("/Users/Sam/Desktop/GameScreen.png");
//        }

        if (buttons[0].pressed() || buttons[4].pressed()) model.moveUp();
        if (buttons[1].pressed() || buttons[5].pressed()) model.moveDown();
        if (buttons[2].pressed() || buttons[6].pressed()) model.moveLeft();
        if (buttons[3].pressed() || buttons[7].pressed()) model.moveRight();
    }

    @Override
    public void draw() {
        if (!model.gameOver) {
            checkInput();
            model.update();
            
            if (keyPressed && key == '`') {
               if (backgroundImage == wolfBackground) {
                   backgroundImage = schoolBackground;
               } 
               else {
                   backgroundImage = wolfBackground;
               }
               
               if (playerImage == badPlayer) {
                   playerImage = goodPlayer;
               } 
               else {
                   playerImage = badPlayer;
               }
            }
        } 
        else {
            if (keyPressed && key == ' ') {
                model = new Model(width, height);
                backgroundImage = schoolBackground;
                playerImage = goodPlayer;
            }
        }
        
        image(backgroundImage, 0, 0);
//        drawMapGraph();

        drawGameObjects();
        
        if (model.gameOver) {
            pushStyle();
            imageMode(CENTER);
            if (model.youWin) {
                image(youwin, width/2, height/2);    
            }
            else {
            image(youlost, width/2, height/2);                
            } 
            popStyle();
        }
    }

    public static void main(String[] args) {
        PApplet.main(
                new String[] {
//                    "--display=2",
                    eip_game.EIP_Game.class.getName()}
        );
    }

    
}
