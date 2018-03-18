package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class AppPanel extends JPanel {
	
	private AppToolBar appToolBar;
	private AppCardPanel appCardPanel;
	
	/**
	 * constructor that loads toolbar and cardpanel(panel for displaying cards)
	 * @param appFrame
	 * @param model
	 * @author pratap
	 * 
	 * @see view.AppToolBar AppCardPanel
	 */
	public AppPanel(AppFrame appFrame,GameEngine model){
		setLayout(new BorderLayout());
		appToolBar = new AppToolBar(appFrame,model);
		appCardPanel = new AppCardPanel();
		add(appToolBar,BorderLayout.PAGE_START);
		add(appCardPanel,BorderLayout.LINE_START);
	}
	
	
	/**
	 * Getter for toolbar
	 * @author pratap
	 * @see view.AppToolBar
	 * @return
	 */
	public AppToolBar getAppToolBar() { return appToolBar; }
	
	/**
	 * Getter for cardPanel
	 * @author pratap
	 * @see view.AppCardPanel
	 * @return
	 */
	public AppCardPanel getAppCardPanel() {	return appCardPanel; }

	

}
