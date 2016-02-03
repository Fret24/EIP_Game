/*
    Copyright (C) 2016, Sam Cooper-Drake, all rights reserved.
    Bush School EIP 2015/2016 Video Game Development Project
*/

package eip_game;

/**
 *
 * @author Sam
 */
public class HallMonitor extends MovingGameObject {
    
    float stepSize = 4;
    float minStepSize = 4;
    float maxStepSize = 6;    


    public HallMonitor(Model model, int nodeA, int nodeB, float pos) {
        super(model, nodeA, nodeB, pos);
        stepSize = minStepSize;
    }
    
    void setStepSize(float factor) {
        stepSize = (1 - factor) * minStepSize + factor * maxStepSize;
    }


    @Override
    void update() {
        location.move(stepSize);
        
        int n = location.closeToNode(5);
        
        if (n == location.nodeB) {
            Map.Node node = model.map.nodes[n];
            
            while (true) {
                int i = (int)(Math.random() * node.dirs.length);
                if (node.dirs[i] != Map.Node.NO_PATH) {
                    location = model.map.place(n, node.dirs[i], 0);
                    break;
                }
            }
            
        }
    }
}
