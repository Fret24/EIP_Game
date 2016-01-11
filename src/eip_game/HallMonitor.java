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

    public HallMonitor(Model model, int nodeA, int nodeB, float pos) {
        super(model, nodeA, nodeB, pos);
    }

    @Override
    void update() {
        location.move(5);
        
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
