/**
 * Optimus, framework for Model Transformation
 *
 * Copyright (C) 2013 Worldline or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
package net.atos.optimus.m2t.merger.java.core.internal;

import java.util.ResourceBundle;

import java.text.MessageFormat;

/**
 *  @author Maxence Vanbésien (mvaawl@gmail.com)
 *  @since 1.0
 */
public enum MergerLoggerMessages {
	BODYDECL_INSERT, BODYDECL_REMOVE, BODYDECL_REMOVEMODIFIER, BODYDECL_LEAVEMODIFIER, BODYDECL_ADDMODIFIER, BODYDECL_MODIFIERNOTADDED, BODYDECL_ANNOADDED, BODYDECL_ANNOREMOVED, ENUM_ADDINTERFACE, FIELDDECL_INITIALIZER, FIELDDECL_INITIALIZERADD, MERGER_START, MERGER_END, MERGER_ADDIMPORT, MERGER_DOMERGE_GENERATED, MERGER_DOMERGE_NOTGENERATED, MERGER_DOMERGE_EMPTYGENERATED, MERGER_NOMERGE, MERGER_DOMERGE_CASE6, MERGER_DOMERGE_CASE7, MERGER_DOMERGE_CASE8, MERGER_DOMERGE_CASE9, MERGER_REMOVE_JAVADOC, MERGER_REPLACE_JAVADOC, MERGER_ADD_JAVADOC, METHODDECL_ERRORSETBODY, METHODDECL_RENAME, METHODDECL_REPLACEPARAMS, METHODDECL_REPLACEBODY, METHODDECL_ADDBODY, METHODDECL_ADDEXCEPTION, TYPE_REPLACESUPERCLASS, TYPE_ADDSUPERCLASS
	;
	
	/*
	 * ResourceBundle instance
	 */
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("MergerLoggerMessages");
	
	/*
	 * Returns value of the message
	 */ 
	public String value() {
		if (MergerLoggerMessages.resourceBundle == null || !MergerLoggerMessages.resourceBundle.containsKey(this.name()))
			return "!!"+this.name()+"!!";
		
		return MergerLoggerMessages.resourceBundle.getString(this.name());
	}
	
	/*
	 * Returns value of the formatted message
	 */ 
	public String value(Object... args) {
		if (MergerLoggerMessages.resourceBundle == null || !MergerLoggerMessages.resourceBundle.containsKey(this.name()))
			return "!!"+this.name()+"!!";
		
		return MessageFormat.format(MergerLoggerMessages.resourceBundle.getString(this.name()), args);
	}
	
}
