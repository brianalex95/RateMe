package de.swtp.Rateme.model;

@SuppressWarnings("serial")
public class RatemeDbException extends RuntimeException {
	public RatemeDbException(String errorMessage, Throwable exce) {
		super(errorMessage, exce);
	}
}
