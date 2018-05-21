package net.lakkie.rubs.readers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.rubs.util.ReaderUtils;
import net.lakkie.acl.parser.IACLReader;

public class UnitImager implements IACLReader {

	public String id;
	public String genericPath, attackerPath, defenderPath;
	public BufferedImage generic, attacker, defender;
	public UnitCoordinator coordinator;

	public UnitImager(UnitCoordinator coordinator) {
		this.coordinator = coordinator;
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("id")) {
			this.id = machine.readString();
			this.coordinator.imagers.put(this.id, this);
		}
		if (token.equals("generic")) {
			this.genericPath = machine.readString();
			try {
				this.generic = ImageIO.read(
						new File(ReaderUtils.getRoot(), this.genericPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (token.equals("attacker")) {
			this.attackerPath = machine.readString();
			try {
				this.attacker = ImageIO.read(
						new File(ReaderUtils.getRoot(), this.attackerPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (token.equals("defender")) {
			this.defenderPath = machine.readString();
			try {
				this.defender = ImageIO.read(
						new File(ReaderUtils.getRoot(), this.defenderPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public BufferedImage getById(int id) {
		switch (id) {
			case 0 :
				return this.attacker;
			case 1 :
				return this.defender;
			default :
				return this.generic;
		}
	}

}
