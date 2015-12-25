/*
This is the model for my game.
It has the data and behavors of the game out side of the view.
*/
package eip_game;

/**
 *
 * @author Sam
 */
public class Model {
    Map map;
    
    Model(int width, int height) {
        map = new Map(width, height);
    }
}
