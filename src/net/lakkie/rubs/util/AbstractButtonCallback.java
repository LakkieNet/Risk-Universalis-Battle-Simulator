package net.lakkie.rubs.util;

import javax.swing.AbstractButton;

public class AbstractButtonCallback implements BooleanCallback {

	private AbstractButton button;

	public AbstractButtonCallback(AbstractButton button) {
		this.button = button;
	}

	public AbstractButton getButton() {
		return this.button;
	}

	public void setButton(AbstractButton button) {
		this.button = button;
	}

	public boolean get(Object... args) {
		if (this.button == null) {
			return false;
		}
		return this.button.isSelected();
	}

}
