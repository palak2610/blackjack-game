package view;

import javax.swing.JFrame;
import model.implementation.GameEngineCallbackImpl;
import model.implementation.Registry;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class AppFrame extends JFrame {
	
	private  AppMenuBar appMenuBar;
	private AppPanel appPanel;
	private GameEngine model;
	
	
	public AppFrame(){
		super("Speed21");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//Loading Model from Registry
		loadModel();
		//Loading the components of Frame window
		loadAppPanel();
		setVisible(true);
		
	}
	
	
	public AppMenuBar getAppMenuBar() { return appMenuBar; }
	public AppPanel getAppPanel() { return appPanel; }

	
	/**
	 * Load the menubar(AppMenuBar) and panel(AppPanel) components for frame window
	 * 
	 * @author pratap	
	 * @see view.AppMenuBar AppPanel
	 */
	private void loadAppPanel(){
		appMenuBar = new AppMenuBar(this,model);
		setJMenuBar(appMenuBar);
		appPanel = new AppPanel(this,model);
		add(appPanel);		
	}
	
	/**
	 * Load GameEngine from Registry, and create GameEngine callback by passing view as 
	 * reference in argument constructor
	 * 
	 * @author pratap
	 * 
	 * @see model.implementation.Registry
	 */
	private void loadModel(){
		model = Registry.getModel(); //Loading from Registry
		//Assigning CallBack with passing frame as constructor.
		model.addGameEngineCallback(new GameEngineCallbackImpl(this));
	}
	
	
}
