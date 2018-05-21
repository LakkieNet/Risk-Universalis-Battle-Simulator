package net.lakkie.rubs;

import javax.swing.UIManager;

import net.lakkie.rubs.args.RUBSArgumentParser;
import net.lakkie.rubs.menus.MenuStart;

public class RUBSMain {

	public static void main(String[] args) {
		if (args.length != 0) {
			RUBSArgumentParser.startAppWithArgs(args);
		}
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new MenuStart();
	}

}
