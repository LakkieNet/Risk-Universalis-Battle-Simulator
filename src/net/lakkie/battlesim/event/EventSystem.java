package net.lakkie.battlesim.event;

import java.util.ArrayList;
import java.util.List;

public class EventSystem {

	private static final EventSystem system = new EventSystem();

	private List<IEventListener> listeners = new ArrayList<IEventListener>();

	public void registerListener(IEventListener listener) {
		this.listeners.add(listener);
		this.listeners.sort((o1, o2) -> {
			return o1.getPriority() - o2.getPriority();
		});
	}

	public void removeListener(IEventListener listener) {
		this.listeners.remove(listener);
	}

	public void passEvent(EventBase event) {
		for (IEventListener listener : this.listeners) {
			listener.onEvent(event);
		}
	}

	public List<IEventListener> getListeners() {
		return this.listeners;
	}

	public static EventSystem getSystem() {
		return system;
	}

}
