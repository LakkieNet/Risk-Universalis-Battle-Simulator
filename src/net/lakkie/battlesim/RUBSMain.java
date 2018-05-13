package net.lakkie.battlesim;

import javax.swing.UIManager;

import net.lakkie.battlesim.menus.MenuStart;

public class RUBSMain {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new MenuStart();
	}

}
