package net.atos.optimus.m2m.engine.core.ctxinject.impl;

public class NullInstanceException extends Exception {

	private static final String MESSAGE = "Could not instantiate/get implementation %s for injection to field %s";
	private String name;
	private Class<?> clazz;

	public NullInstanceException(String name, Class<?> clazz) {
		this.name = name;
		this.clazz = clazz;
	}

	public NullInstanceException(String name, Class<?> clazz, Exception cause) {
		super(cause);
		this.name = name;
		this.clazz = clazz;
	}

	@Override
	public String getMessage() {
		return String.format(MESSAGE, this.clazz == null ? null : this.clazz.getName(), this.name);
	}

	private static final long serialVersionUID = 1L;
}
