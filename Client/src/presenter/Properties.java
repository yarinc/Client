package presenter;

import java.io.Serializable;

/**
 * The Class Properties.
 */
public class Properties implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cacheFileLocation;
	private int numberOfThreads;
	private String ip;
	private int port;
	private String viewType;
	
	public int getNumberOfThreads() {
		return numberOfThreads;
	}
	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}
	public String getCacheFileLocation() {
		return cacheFileLocation;
	}
	public void setCacheFileLocation(String cacheFileLocation) {
		this.cacheFileLocation = cacheFileLocation;
	}
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public String getIP() {
		return ip;
	}
	public void setIP(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
}
