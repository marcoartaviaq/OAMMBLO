package com.fr4gus.android.oammblo.service;

/**
 * Exception para encapular excepciones de bajo nivel como IOException
 * @author fr4gus
 *
 */
public class TwitterServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TwitterServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TwitterServiceException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		// TODO Auto-generated constructor stub
	}

	public TwitterServiceException(String detailMessage) {
		super(detailMessage);
		// TODO Auto-generated constructor stub
	}

	public TwitterServiceException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

}
