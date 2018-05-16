package net.lakkie.rubs.menus;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import net.lakkie.rubs.graphics.UnitGroupEditor;
import net.lakkie.rubs.readers.ReaderUtils;
import net.lakkie.rubs.storage.PositionedUnit;
import net.lakkie.rubs.storage.UnitActionType;
import net.lakkie.rubs.storage.UnitGroup;
import net.lakkie.rubs.storage.Vector2i;

public class MenuArmyEditor extends JFrame implements WindowListener {

	public static final Map<UnitGroup, MenuArmyEditor> windows = new HashMap<UnitGroup, MenuArmyEditor>();
	private static final long serialVersionUID = 3796425275181694051L;
	private JPanel contentPane;
	private UnitGroup editing;
	private UnitGroupEditor editor;
	private Runnable finish;

	public MenuArmyEditor(UnitGroup editing) {
		this.editing = editing;
		if (windows.containsKey(this.editing)) {
			windows.get(this.editing).requestFocus();
			this.dispose();
			return;
		} else {
			windows.put(this.editing, this);
		}
		this.setTitle("Army Editor");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 450, 339);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		JPanel panelPreview = new JPanel();
		panelPreview
				.setBorder(new TitledBorder(
						new BevelBorder(BevelBorder.RAISED, null, null, null,
								null),
						"Army Preview", TitledBorder.LEADING, TitledBorder.TOP,
						null, null));
		TitledBorder previewBorder = (TitledBorder) panelPreview.getBorder();
		panelPreview.setBounds(10, 10, 308, 250);
		this.contentPane.add(panelPreview);
		panelPreview.setLayout(new BorderLayout(0, 0));

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		panelPreview.add(canvas, BorderLayout.CENTER);

		Button buttonCreateNew = new Button("Add");
		buttonCreateNew.setBounds(324, 10, 110, 22);
		this.contentPane.add(buttonCreateNew);

		buttonCreateNew.addActionListener((event) -> {
			PositionedUnit unit = new PositionedUnit(new Vector2i(0),
					UnitActionType.GENERIC);
			try {
				unit.infantry = Integer.parseInt(
						JOptionPane.showInputDialog(null, "Enter # of infantry")
								.replaceAll(",", ""));
				unit.cavalry = Integer.parseInt(
						JOptionPane.showInputDialog(null, "Enter # of cavalry")
								.replaceAll(",", ""));
				unit.armor = Integer.parseInt(
						JOptionPane.showInputDialog(null, "Enter # of armor")
								.replaceAll(",", ""));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Invalid input specified!",
						"Invalid Input", JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.editing.addUnit(unit);
		});

		Button buttonFinish = new Button("Finish");
		buttonFinish.setBounds(324, 258, 110, 42);
		buttonFinish.addActionListener((event) -> {
			this.windowClosing(null);
			this.dispose();
		});
		contentPane.add(buttonFinish);

		JLabel lblTip = new JLabel(
				"Hold down right click to position, middle click to delete");
		lblTip.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTip.setBounds(10, 261, 308, 39);
		// TODO: Fix the info image using ImageLoader
		BufferedImage image;
		try {
			image = ImageIO.read(new File(ReaderUtils.getRoot(),
					"Assets/Images/Info-32.png"));
			lblTip.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
		contentPane.add(lblTip);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.addWindowListener(this);

		this.editor = new UnitGroupEditor(this.editing, canvas, previewBorder,
				this);
	}

	public UnitGroup getEditing() {
		return this.editing;
	}

	public UnitGroupEditor getEditor() {
		return this.editor;
	}
	
	public void setFinish(Runnable finish) {
		this.finish = finish;
	}
	
	public Runnable getFinish() {
		return this.finish;
	}

	public void windowOpened(WindowEvent e) {

	}

	public void windowClosing(WindowEvent e) {
		this.editor.onEditFinish();
		windows.remove(this.editing);
		if (this.finish != null) {
			this.finish.run();
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
