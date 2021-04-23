package game_engine;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public abstract class Scene {
    
    private HashMap<String, ArrayList<GameObject>> groups; 
    public ArrayList<GameObject> gameObjects;
    public HashMap<String, Scene> transitions;

    public Scene() {
        this.gameObjects = new ArrayList<GameObject> ();
        this.transitions = new HashMap<String, Scene> ();
        this.groups = new HashMap<String, ArrayList<GameObject>> ();
    }

    // Override to define logic for loading in the new scene
    public abstract void load(Game game);

    // Remove all game objects from groups and gameObjects
    public void reset() {
        this.gameObjects = new ArrayList<GameObject> ();
        this.groups = new HashMap<String, ArrayList<GameObject>> ();
    }

    // Add a gameObject to the scene without a group
    public void addGameObject (GameObject gameObject) {
        // Check if gameObjects already contains reference to gameObject
        if (!this.gameObjects.contains(gameObject)) {
            this.gameObjects.add(gameObject);
        }
    }

    // Add a gameObject to the scene with one group
    public void addGameObject (GameObject gameObject, String groupName) {
        this.addGameObject(gameObject);
        ArrayList<GameObject> group;
        // Get list of objects in group if groupName is found
        if (this.groups.containsKey(groupName)) {
            group = this.groups.get(groupName);
        }
        // Add new list to groupss if groupName is not found
        else {
            group = new ArrayList<GameObject> ();
            this.groups.put(groupName, group);
        }
        // Check if group already contains reference to gameObject
        if (!group.contains(gameObject)) {
            group.add(gameObject);
        }
    }

    // Add a gameObject to the scene with list groups
    public void addGameObject (GameObject gameObject, List<String> groupNames) {
        this.addGameObject(gameObject);
        for (String groupName: groupNames) {
            this.addGameObject(gameObject, groupName);
        }
    }

    // Return ArrayList of GameObjects within group specified by groupName
    public ArrayList<GameObject> getGroup (String groupName) {
        if (groups.containsKey(groupName)) {
            ArrayList<GameObject> gameObjects = groups.get(groupName);
            ArrayList<GameObject> active = new ArrayList<GameObject> ();
            for (GameObject gameObject : gameObjects) {
                if (gameObject.active) {
                    active.add(gameObject);
                }
            }
            return active;
        }
        else {
            return null;
        }
    }

    // Add new scene to transition map with specified transitionName
    public void addTransition (String transitionName, Scene nextScene) {
        this.transitions.put(transitionName, nextScene);
    }

    // Return scene in transition table with key transitionName
    public Scene transition (String transitionName) {
        if (transitions.containsKey(transitionName)) {
            return transitions.get(transitionName);
        }
        else {
            // Should throw exception here
            return this;
        }
    }

}

