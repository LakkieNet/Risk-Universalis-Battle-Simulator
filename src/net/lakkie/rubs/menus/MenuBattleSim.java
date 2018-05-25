package net.lakkie.rubs.menus;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import net.lakkie.rubs.graphics.BattleRenderer;
import net.lakkie.rubs.graphics.GraphicalGameLoop;
import net.lakkie.rubs.storage.RUBSBattle;
import net.lakkie.rubs.storage.Vector2i;
import net.lakkie.rubs.util.AbstractButtonCallback;
import net.lakkie.rubs.util.BasicUtils;

public class MenuBattleSim extends JFrame implements WindowListener {

	private static final long serialVersionUID = 4187909471813315334L;
	private JPanel contentPane;
	private RUBSBattle battle;
	private BattleRenderer renderer;

	public MenuBattleSim(RUBSBattle battle) {
		this.battle = battle;
		this.setTitle("Battle - " + this.battle.getName());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 1280, 734);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnDebug = new JMenu("Debug");
		menuBar.add(mnDebug);
		mnDebug.setMnemonic('D');

		JCheckBoxMenuItem chckbxmntmShowTargetPaths = new JCheckBoxMenuItem("Show Target Paths");
		AbstractButtonCallback showTargetPaths = new AbstractButtonCallback(chckbxmntmShowTargetPaths);
		mnDebug.add(chckbxmntmShowTargetPaths);
		chckbxmntmShowTargetPaths.setMnemonic('S');

		JMenuItem mntmDebugMenu = new JMenuItem("Debug Menu...");
		mnDebug.add(mntmDebugMenu);
		mntmDebugMenu.addActionListener((event) -> {
			new MenuBattleDebug(battle);
		});
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Battle", TitledBorder.TRAILING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel.setBounds(10, 11, 1244, 568);
		this.contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		panel.add(canvas, BorderLayout.CENTER);

		JLabel lblBattleName = new JLabel(this.battle.getName());
		lblBattleName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBattleName.setBounds(10, 589, 205, 81);
		this.contentPane.add(lblBattleName);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(225, 590, 1029, 80);
		this.contentPane.add(scrollPane);

		JTextArea txtrComment = new JTextArea();
		scrollPane.setViewportView(txtrComment);
		txtrComment.setEditable(false);
		txtrComment.setText(this.battle.getComment());
		txtrComment.setFont(BasicUtils.getCommentFont());
		this.addWindowListener(this);
		this.setResizable(false);
		this.setVisible(true);
		this.renderer = new BattleRenderer(this.battle, canvas, new Vector2i(panel.getWidth(), panel.getHeight()), showTargetPaths);
	}

	public RUBSBattle getBattle() {
		return this.battle;
	}

	public void windowOpened(WindowEvent e) {

	}

	public void windowClosing(WindowEvent e) {
		this.renderer.close();
		for (MenuBattleDebug debugMenu : MenuBattleDebug.instances) {
			if (debugMenu.battle == this.battle) {
				GraphicalGameLoop.getInstance().getDraws().remove(debugMenu.updateMethod);
				MenuBattleDebug.instances.remove(debugMenu);
				debugMenu.dispose();
			}
		}
	}

	public void windowClosed(WindowEvent e) {

	}

	public void windowIconified(WindowEvent e) {

	}

	public void windowDeiconified(WindowEvent e) {

	}

	public void windowActivated(WindowEvent e) {

	}

	public void windowDeactivated(WindowEvent e) {

	}
}
