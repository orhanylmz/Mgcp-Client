package com.mgcp.old.manager.session;

public interface MediaSessionEvent {

	public void timeout();

	public void connected(String content);

}
