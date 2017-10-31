package com.mgcp.old.manager.session;

import java.util.Properties;

import com.mgcp.old.manager.MgcpConnectionManager;

public abstract class IvrSession extends Thread {

	private MediaSessionEvent mediaSessionEvent;
	private String sdpContent;
	private MgcpConnectionManager mgcpConnectionManager;
	private String localSdpContent;
	
	//Mgcp Message
	private String connectionId;
	private String specificEndpointID;
	
	
	protected Properties lockProperties = new Properties();
	

	public IvrSession(MgcpConnectionManager mgcpConnectionManager, MediaSessionEvent mediaSessionEvent, String sdpContent) {
		setMediaSessionEvent(mediaSessionEvent);
		setSdpContent(sdpContent);
		setMgcpConnectionManager(mgcpConnectionManager);
	}

	public abstract void processSession();

	public abstract void processResponse(String message);

	@Override
	public void run() {
		processSession();
	}

	public MediaSessionEvent getMediaSessionEvent() {
		return mediaSessionEvent;
	}

	public void setMediaSessionEvent(MediaSessionEvent mediaSessionEvent) {
		this.mediaSessionEvent = mediaSessionEvent;
	}

	public String getSdpContent() {
		return sdpContent;
	}

	public void setSdpContent(String sdpContent) {
		this.sdpContent = sdpContent;
	}

	public MgcpConnectionManager getMgcpConnectionManager() {
		return mgcpConnectionManager;
	}

	public void setMgcpConnectionManager(MgcpConnectionManager mgcpConnectionManager) {
		this.mgcpConnectionManager = mgcpConnectionManager;
	}

	public String getLocalSdpContent() {
		return localSdpContent;
	}

	public void setLocalSdpContent(String localSdpContent) {
		this.localSdpContent = localSdpContent;
	}

	public String getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(String connectionId) {
		this.connectionId = connectionId;
	}

	public String getSpecificEndpointID() {
		return specificEndpointID;
	}

	public void setSpecificEndpointID(String specificEndpointID) {
		this.specificEndpointID = specificEndpointID;
	}

}
