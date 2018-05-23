package net.lakkie.rubs.menus;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import net.lakkie.rubs.NamingUtils;
import net.lakkie.rubs.storage.RUBSBattle;
import net.lakkie.rubs.util.CipherUtils;
import net.lakkie.rubs.util.RUBSLogger;
import net.lakkie.rubs.util.ReaderUtils;

public class MenuStart extends JFrame {

	private static final long serialVersionUID = 4957573164123568271L;
	private JPanel contentPane;

	public MenuStart() {
		this.setTitle("Risk Universalis Battle Simulator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 485, 332);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		JLabel lblRiskUniversalisBattle = new JLabel("Risk Universalis Battle Simulator");
		lblRiskUniversalisBattle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRiskUniversalisBattle.setHorizontalAlignment(SwingConstants.CENTER);
		lblRiskUniversalisBattle.setBounds(59, 84, 350, 32);
		this.contentPane.add(lblRiskUniversalisBattle);

		JButton btnNewBattle = new JButton("New Battle");
		btnNewBattle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewBattle.setBounds(164, 127, 150, 55);
		btnNewBattle.addActionListener((event) -> {
			// Open battle setup menu
			new MenuBattleSetup();
		});
		this.contentPane.add(btnNewBattle);

		JButton buttonOpenTemplate = new JButton("Open Template...");
		buttonOpenTemplate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonOpenTemplate.setBounds(164, 193, 150, 55);
		buttonOpenTemplate.addActionListener((event) -> {
			JFileChooser chooser = new JFileChooser(new File(ReaderUtils.getRoot(), "SaveFiles/"));
			chooser.setFileFilter(new FileFilter() {

				public String getDescription() {
					return "Risk Universalis Database File (*.rudf)";
				}

				public boolean accept(File f) {
					if (f.isDirectory()) {
						return true;
					}
					return f.getName().endsWith(".rudf");
				}
			});
			int returnVal = chooser.showDialog(null, "Select");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File selection = chooser.getSelectedFile();
				try {
					RUBSBattle battle = this.readFile(selection);
					MenuBattleSetup setup = new MenuBattleSetup();
					setup.armyA = battle.getAttacking();
					setup.armyD = battle.getDefending();
					setup.createPreviews();
					setup.textFieldName.setText(NamingUtils.getRootBattleName(battle.getName()));
					setup.textFieldEvalName.setText(battle.getName());
					setup.textAreaComment.setText(battle.getComment());
					setup.updateTroopInfo();
					RUBSLogger.logger().info("Successfully loaded save: " + selection.getAbsolutePath());
					RUBSLogger.logger().info("Army is:\n" + battle);
				} catch (Exception e) {
					RUBSLogger.logger().warning("Error loading save: " + selection.getAbsolutePath());
					JOptionPane.showMessageDialog(null, "Error reading the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		this.contentPane.add(buttonOpenTemplate);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	private RUBSBattle readFile(File selection) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new CipherInputStream(new FileInputStream(selection), CipherUtils.getCipher(Cipher.DECRYPT_MODE)));
		RUBSBattle battle = (RUBSBattle) in.readObject();
		in.close();
		return battle;
	}
}
