package net.lakkie.rubs;

import javax.swing.UIManager;

import net.lakkie.rubs.menus.MenuStart;

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
