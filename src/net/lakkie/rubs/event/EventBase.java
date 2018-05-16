package net.lakkie.rubs.event;

public class EventBase {

	private final String name;
	
	public EventBase(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void fire() {
		EventSystem.getSystem().passEvent(this);
	}
	
}
