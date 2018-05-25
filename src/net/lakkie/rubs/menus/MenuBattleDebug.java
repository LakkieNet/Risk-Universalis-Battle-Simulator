package net.lakkie.rubs.menus;

import java.awt.BorderLayout;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.lakkie.rubs.graphics.GraphicalGameLoop;
import net.lakkie.rubs.storage.PositionedUnit;
import net.lakkie.rubs.storage.RUBSBattle;
import net.lakkie.rubs.util.BasicUtils;

public class MenuBattleDebug extends JFrame {

	public static final List<MenuBattleDebug> instances = new CopyOnWriteArrayList<MenuBattleDebug>();
	private static final long serialVersionUID = -6315380453614499345L;
	private JPanel contentPane;
	private JTable table;
	public DefaultTableModel model;
	public RUBSBattle battle;
	public Runnable updateMethod = this::updateInfo;

	public MenuBattleDebug(RUBSBattle battle) {
		this.battle = battle;
		this.setTitle(this.battle.getName() + " - Battle Debug");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 629, 393);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(this.contentPane);

		JScrollPane scrollPane = new JScrollPane();
		this.contentPane.add(scrollPane, BorderLayout.CENTER);

		this.table = new JTable();
		List<PositionedUnit> unitsList = this.battle.getAllUnits();
		PositionedUnit[] units = unitsList.toArray(new PositionedUnit[unitsList.size()]);
		Object[][] data = new Object[units.length][];
		for (int i = 0; i < units.length; i++) {
			PositionedUnit unit = units[i];
			data[i] = new String[]{Integer.toString(unit.getSize()),
					String.format("(%s, %s, %s)", Math.round(unit.infantry), Math.round(unit.cavalry), Math.round(unit.armor)),
					BasicUtils.getMoraleFormat().format(unit.getMorale()), unit.posExact.round().toString(), unit.actionType.toString(),
					unit.decision.toString(), unit.aiTargetPos.toString()};
		}
		this.model = new DefaultTableModel(data,
				new String[]{"Unit Size", "Infantry, Cavalry, Armor", "Morale", "Position(X, Y)", "Action Type", "Decision", "Target Position"});
		this.table.setModel(this.model);
		this.table.removeEditor();
		scrollPane.setViewportView(this.table);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		instances.add(this);
		GraphicalGameLoop.getInstance().getDraws().add(this.updateMethod);
	}

	public void updateInfo() {
		List<PositionedUnit> unitsList = this.battle.getAllUnits();
		PositionedUnit[] units = unitsList.toArray(new PositionedUnit[unitsList.size()]);
		for (int i = 0; i < units.length; i++) {
			PositionedUnit unit = units[i];
			// i is row, rest is hard-coded
			this.model.setValueAt(Integer.toString(unit.getSize()), i, 0);
			this.model.setValueAt(String.format("(%s, %s, %s)", Math.round(unit.infantry), Math.round(unit.cavalry), Math.round(unit.armor)), i, 1);
			this.model.setValueAt(BasicUtils.getMoraleFormat().format(unit.getMorale()), i, 2);
			this.model.setValueAt(unit.posExact.round().toString(), i, 3);
			this.model.setValueAt(unit.actionType.toString(), i, 4);
			this.model.setValueAt(unit.decision.toString(), i, 5);
			this.model.setValueAt(unit.aiTargetPos.toString(), i, 6);
		}
	}

}
