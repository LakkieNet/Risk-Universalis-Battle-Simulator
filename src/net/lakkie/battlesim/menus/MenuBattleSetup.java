package net.lakkie.battlesim.menus;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import net.lakkie.battlesim.BasicUtils;
import net.lakkie.battlesim.BattleNameFormatter;
import net.lakkie.battlesim.graphics.DivisionPreviewRenderer;
import net.lakkie.battlesim.graphics.GraphicalGameLoop;
import net.lakkie.battlesim.readers.ReaderUtils;
import net.lakkie.battlesim.storage.ExampleUnitGroup;
import net.lakkie.battlesim.storage.RUBSBattle;
import net.lakkie.battlesim.storage.UnitGroup;
import net.lakkie.battlesim.util.AbstractButtonCallback;

public class MenuBattleSetup extends JFrame {

	private static final long serialVersionUID = -7126096430468006088L;
	private JPanel contentPane;
	public JTextField textFieldName;
	public JTextField textFieldEvalName;
	private JTextField textFieldSizeD;
	private JTextField textFieldInfantryD;
	private JTextField textFieldCavalryD;
	private JTextField textFieldArmorD;
	private JTextField textFieldArmorA;
	private JTextField textFieldCavalryA;
	private JTextField textFieldInfantryA;
	private JTextField textFieldSizeA;
	private Canvas canvasA, canvasD;
	private DivisionPreviewRenderer prevA, prevD;
	private JCheckBox chckbxKeepPosition;
	private JCheckBoxMenuItem chckbxDebugLines;
	public UnitGroup armyA = new ExampleUnitGroup(),
			armyD = new ExampleUnitGroup();
	private MenuArmyEditor editorA, editorD;

	public MenuBattleSetup() {
		this.setTitle("Battle Setup");
		this.setBounds(100, 100, 882, 579);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('F');
		menuBar.add(mnFile);

		JMenuItem mntmSave = new JMenuItem("Save...");
		mnFile.add(mntmSave);

		JMenu mnDebug = new JMenu("Debug");
		mnDebug.setMnemonic('d');
		menuBar.add(mnDebug);

		this.chckbxDebugLines = new JCheckBoxMenuItem("Debug Lines");
		this.chckbxDebugLines.setMnemonic('d');
		mnDebug.add(this.chckbxDebugLines);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		JPanel panelMeta = new JPanel();
		panelMeta.setBorder(new TitledBorder(
				new BevelBorder(BevelBorder.RAISED, null, null, null, null),
				"Meta Information", TitledBorder.TRAILING, TitledBorder.TOP,
				null, new Color(0, 0, 0)));
		panelMeta.setBounds(20, 11, 836, 152);
		this.contentPane.add(panelMeta);
		panelMeta.setLayout(null);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 22, 37, 14);
		panelMeta.add(lblName);

		this.textFieldName = new JTextField();
		this.textFieldName.setBounds(47, 19, 196, 20);
		panelMeta.add(this.textFieldName);
		this.textFieldName.setColumns(10);

		JLabel lblEvalName = new JLabel("Evaluated Name:");
		lblEvalName.setBounds(253, 22, 82, 14);
		panelMeta.add(lblEvalName);

		this.textFieldEvalName = new JTextField();
		this.textFieldEvalName.setBounds(345, 19, 481, 20);
		panelMeta.add(this.textFieldEvalName);
		this.textFieldEvalName.setEditable(false);
		this.textFieldEvalName.setColumns(10);

		JLabel lblComment = new JLabel("Comment:");
		lblComment.setBounds(10, 53, 53, 88);
		panelMeta.add(lblComment);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(73, 50, 753, 91);
		panelMeta.add(scrollPane);

		JTextArea textAreaComment = new JTextArea();
		textAreaComment.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(textAreaComment);

		mntmSave.addActionListener((event) -> {
			RUBSBattle battle = new RUBSBattle(this.armyA, this.armyD);
			battle.setName(this.textFieldEvalName.getText());
			battle.setComment(textAreaComment.getText());
			JFileChooser chooser = new JFileChooser(
					new File(ReaderUtils.getRoot(), "Assets/"));
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
			int returnVal = chooser.showDialog(null, "Save");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File targetFile = chooser.getSelectedFile();
				if (!targetFile.getName().endsWith("rudf")) {
					targetFile = new File(
							targetFile.getAbsolutePath() + ".rudf");
				}
				try {
					ObjectOutputStream out = new ObjectOutputStream(
							new FileOutputStream(targetFile));
					out.writeObject(battle);
					out.flush();
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(
						new BevelBorder(BevelBorder.RAISED, null, null, null,
								null),
						"Defending Army", TitledBorder.TRAILING,
						TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(20, 174, 327, 327);
		this.contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSizeD = new JLabel("Size: ");
		lblSizeD.setBounds(10, 21, 58, 14);
		panel.add(lblSizeD);

		JLabel lblInfantryD = new JLabel("Infantry: ");
		lblInfantryD.setBounds(10, 46, 58, 14);
		panel.add(lblInfantryD);

		JLabel lblCavalryD = new JLabel("Cavalry: ");
		lblCavalryD.setBounds(10, 71, 58, 14);
		panel.add(lblCavalryD);

		JLabel lblArmorD = new JLabel("Armor:");
		lblArmorD.setBounds(10, 96, 58, 14);
		panel.add(lblArmorD);

		this.textFieldSizeD = new JTextField();
		this.textFieldSizeD.setEditable(false);
		this.textFieldSizeD.setBounds(78, 18, 239, 20);
		panel.add(this.textFieldSizeD);
		this.textFieldSizeD.setColumns(10);

		this.textFieldInfantryD = new JTextField();
		this.textFieldInfantryD.setEditable(false);
		this.textFieldInfantryD.setColumns(10);
		this.textFieldInfantryD.setBounds(78, 43, 239, 20);
		panel.add(this.textFieldInfantryD);

		this.textFieldCavalryD = new JTextField();
		this.textFieldCavalryD.setEditable(false);
		this.textFieldCavalryD.setColumns(10);
		this.textFieldCavalryD.setBounds(78, 68, 239, 20);
		panel.add(this.textFieldCavalryD);

		this.textFieldArmorD = new JTextField();
		this.textFieldArmorD.setEditable(false);
		this.textFieldArmorD.setColumns(10);
		this.textFieldArmorD.setBounds(78, 93, 239, 20);
		panel.add(this.textFieldArmorD);

		JButton btnEditD = new JButton("Edit...");
		btnEditD.setBounds(10, 121, 307, 46);
		panel.add(btnEditD);
		btnEditD.addActionListener((event) -> {
			this.editorD = new MenuArmyEditor(this.armyD);
			this.editorD.setFinish(this::updateTroopInfo);
		});

		JLabel lblPreviewD = new JLabel("Preview:");
		lblPreviewD.setBounds(10, 174, 46, 14);
		panel.add(lblPreviewD);

		this.canvasD = new Canvas();
		this.canvasD.setBackground(Color.WHITE);
		this.canvasD.setBounds(62, 173, 255, 144);
		panel.add(this.canvasD);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(
						new BevelBorder(BevelBorder.RAISED, null, null, null,
								null),
						"Attacking Army", TitledBorder.TRAILING,
						TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(395, 174, 327, 327);
		this.contentPane.add(panel_1);
		panel_1.setLayout(null);

		this.canvasA = new Canvas();
		this.canvasA.setBackground(Color.WHITE);
		this.canvasA.setBounds(62, 173, 255, 144);
		panel_1.add(this.canvasA);

		JLabel lblPreviewA = new JLabel("Preview:");
		lblPreviewA.setBounds(10, 174, 46, 14);
		panel_1.add(lblPreviewA);

		JButton buttonEditA = new JButton("Edit...");
		buttonEditA.setBounds(10, 121, 307, 46);
		panel_1.add(buttonEditA);
		buttonEditA.addActionListener((event) -> {
			this.editorA = new MenuArmyEditor(this.armyA);
			this.editorA.setFinish(this::updateTroopInfo);
		});

		this.textFieldArmorA = new JTextField();
		this.textFieldArmorA.setEditable(false);
		this.textFieldArmorA.setColumns(10);
		this.textFieldArmorA.setBounds(78, 93, 239, 20);
		panel_1.add(this.textFieldArmorA);

		JLabel lblArmorA = new JLabel("Armor:");
		lblArmorA.setBounds(10, 96, 58, 14);
		panel_1.add(lblArmorA);

		JLabel lblCavalryA = new JLabel("Cavalry: ");
		lblCavalryA.setBounds(10, 71, 58, 14);
		panel_1.add(lblCavalryA);

		this.textFieldCavalryA = new JTextField();
		this.textFieldCavalryA.setEditable(false);
		this.textFieldCavalryA.setColumns(10);
		this.textFieldCavalryA.setBounds(78, 68, 239, 20);
		panel_1.add(this.textFieldCavalryA);

		this.textFieldInfantryA = new JTextField();
		this.textFieldInfantryA.setEditable(false);
		this.textFieldInfantryA.setColumns(10);
		this.textFieldInfantryA.setBounds(78, 43, 239, 20);
		panel_1.add(this.textFieldInfantryA);

		this.textFieldSizeA = new JTextField();
		this.textFieldSizeA.setEditable(false);
		this.textFieldSizeA.setColumns(10);
		this.textFieldSizeA.setBounds(78, 18, 239, 20);
		panel_1.add(this.textFieldSizeA);

		JLabel lblSizeA = new JLabel("Size: ");
		lblSizeA.setBounds(10, 21, 58, 14);
		panel_1.add(lblSizeA);

		JLabel lblInfantryA = new JLabel("Infantry: ");
		lblInfantryA.setBounds(10, 46, 58, 14);
		panel_1.add(lblInfantryA);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener((event) -> {
			RUBSBattle battle = new RUBSBattle(this.armyA, this.armyD);
			battle.setName(this.textFieldEvalName.getText());
			battle.setComment(textAreaComment.getText());

			new MenuBattleSim(battle.copy());
		});
		btnCreate.setBounds(754, 470, 112, 48);
		this.contentPane.add(btnCreate);
		this.textFieldName.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {

			}

			public void keyReleased(KeyEvent e) {
				textFieldEvalName.setText(BattleNameFormatter
						.getBattleName(textFieldName.getText()));
			}

			public void keyPressed(KeyEvent e) {

			}
		});

		this.chckbxKeepPosition = new JCheckBox("Keep Position");
		this.chckbxKeepPosition.setBounds(20, 504, 97, 23);
		this.contentPane.add(this.chckbxKeepPosition);
		this.prevA = new DivisionPreviewRenderer(this.canvasA, this.armyA,
				new AbstractButtonCallback(this.chckbxKeepPosition),
				new AbstractButtonCallback(this.chckbxDebugLines));
		this.prevD = new DivisionPreviewRenderer(this.canvasD, this.armyD,
				new AbstractButtonCallback(this.chckbxKeepPosition),
				new AbstractButtonCallback(this.chckbxDebugLines));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);

		GraphicalGameLoop.getInstance().getDraws().add(this::drawArmyPreviews);
		this.updateTroopInfo();
	}

	public void updateTroopInfo() {
		this.textFieldSizeA
				.setText(BasicUtils.formatCommas(this.armyA.getTotalTroops()));
		this.textFieldInfantryA.setText(
				BasicUtils.formatCommas(this.armyA.getTotalInfantry()));
		this.textFieldCavalryA
				.setText(BasicUtils.formatCommas(this.armyA.getTotalCavalry()));
		this.textFieldArmorA
				.setText(BasicUtils.formatCommas(this.armyA.getTotalArmor()));
		this.textFieldSizeD
				.setText(BasicUtils.formatCommas(this.armyD.getTotalTroops()));
		this.textFieldInfantryD.setText(
				BasicUtils.formatCommas(this.armyD.getTotalInfantry()));
		this.textFieldCavalryD
				.setText(BasicUtils.formatCommas(this.armyD.getTotalCavalry()));
		this.textFieldArmorD
				.setText(BasicUtils.formatCommas(this.armyD.getTotalArmor()));
	}

	private void drawArmyPreviews() {
		this.prevA.draw();
		this.prevD.draw();
	}
}
