package model.implementation;

import java.util.HashMap;

import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class Registry extends HashMap<String,Object> {
	
	private static Registry singletonInstance = null;
	private static final String MODEL = "Model";
	
	/**
	 * Single instance of Registry class
	 * Creates the gameEngine only once.
	 */
	private Registry(){
		GameEngine model = new GameEngineImpl();
		put(MODEL,model);
	}

	
	/**
	 * Access the static singleinstance of regsitry to access the model
	 * @return
	 */
	private static Registry getSingletonInstance(){
		if(singletonInstance == null)
			singletonInstance = new Registry();
		return singletonInstance;
	}
	
	/**
	 * Retrieves the Model.Restricts to create only one gameEngine.
	 * @author pratap
	 * @return
	 */
	public static GameEngine getModel(){
		return (GameEngine) getSingletonInstance().get(MODEL);
	}	
}
