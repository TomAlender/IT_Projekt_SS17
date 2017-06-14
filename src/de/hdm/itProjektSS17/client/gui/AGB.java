package de.hdm.itProjektSS17.client.gui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.Showcase;

/**
 * @see de.hdm.itProjektSS17.client.client.showcase
 * @author Tim
 *
 */
public class AGB extends Showcase {

	/**
	 * HeadlineText returnen
	 * @return String
	 */
	@Override
	protected String getHeadlineText() {
		return "Prokeko AGBs";
	}

	@Override
	protected void run() {
		VerticalPanel agbPanel = new VerticalPanel();
		

		Label agblabel = new Label("Die AGBs des Prokeko Projektmarktplatzes");
		
		//Label html = new HTML(); muss noch implementiert werden
		agbPanel.add(agblabel);
		this.add(agblabel);
	
		
	}
	
	

}
