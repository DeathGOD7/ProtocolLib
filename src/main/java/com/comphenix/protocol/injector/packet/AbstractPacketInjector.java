package com.comphenix.protocol.injector.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.concurrency.PacketTypeSet;
import com.comphenix.protocol.events.ListenerOptions;
import com.comphenix.protocol.injector.packet.PacketInjector;
import java.util.Set;

public abstract class AbstractPacketInjector implements PacketInjector {

	private final PacketTypeSet inboundFilters;

	public AbstractPacketInjector(PacketTypeSet inboundFilters) {
		this.inboundFilters = inboundFilters;
	}

	@Override
	public boolean isCancelled(Object packet) {
		// No, it's never cancelled
		return false;
	}

	@Override
	public void setCancelled(Object packet, boolean cancelled) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addPacketHandler(PacketType type, Set<ListenerOptions> options) {
		this.inboundFilters.addType(type);
		return true;
	}

	@Override
	public boolean removePacketHandler(PacketType type) {
		this.inboundFilters.removeType(type);
		return true;
	}

	@Override
	public boolean hasPacketHandler(PacketType type) {
		return this.inboundFilters.contains(type);
	}

	@Override
	public Set<PacketType> getPacketHandlers() {
		return this.inboundFilters.values();
	}

	@Override
	public void cleanupAll() {
		this.inboundFilters.clear();
	}
}
