package com.mgcp.old.parameters;

/*
 * Supported by Endpoint
 * Events and signals are grouped into packages PL:
 */

public class MgcpPackage {

	private String genericMedia;
	private String dtmf;
	private String mf;
	private String trunk;
	private String line;
	private String handset;
	private String rtp;
	private String networkAccessServer;
	private String announcmentServer;

	/*
	 * G:
	 */
	public String getGenericMedia() {
		return genericMedia;
	}

	public void setGenericMedia(String genericMedia) {
		this.genericMedia = genericMedia;
	}

	/*
	 * D:
	 */
	public String getDtmf() {
		return dtmf;
	}

	public void setDtmf(String dtmf) {
		this.dtmf = dtmf;
	}

	/*
	 * M:
	 */
	public String getMf() {
		return mf;
	}

	public void setMf(String mf) {
		this.mf = mf;
	}

	/*
	 * T:
	 */
	public String getTrunk() {
		return trunk;
	}

	public void setTrunk(String trunk) {
		this.trunk = trunk;
	}

	/*
	 * L:
	 */
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	/*
	 * H:
	 */
	public String getHandset() {
		return handset;
	}

	public void setHandset(String handset) {
		this.handset = handset;
	}

	/*
	 * R:
	 */
	public String getRtp() {
		return rtp;
	}

	public void setRtp(String rtp) {
		this.rtp = rtp;
	}

	/*
	 * N:
	 */
	public String getNetworkAccessServer() {
		return networkAccessServer;
	}

	public void setNetworkAccessServer(String networkAccessServer) {
		this.networkAccessServer = networkAccessServer;
	}

	/*
	 * A:
	 */
	public String getAnnouncmentServer() {
		return announcmentServer;
	}

	public void setAnnouncmentServer(String announcmentServer) {
		this.announcmentServer = announcmentServer;
	}
}
