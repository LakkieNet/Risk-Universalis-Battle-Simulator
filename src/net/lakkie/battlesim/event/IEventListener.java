package net.lakkie.battlesim.event;

public interface IEventListener {

	void onEvent(EventBase event);
	
	default int getPriority() {
		return 0;
	}
	
}
