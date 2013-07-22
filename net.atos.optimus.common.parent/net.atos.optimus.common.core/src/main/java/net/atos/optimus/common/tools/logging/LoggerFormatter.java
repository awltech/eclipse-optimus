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
package net.atos.optimus.common.tools.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Default message formatter for XA Console.
 * 
 * Pattern is as follows : <loggerId>@<time> [<severity>] <message>
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class LoggerFormatter extends Formatter {

	/**
	 * Date Formatter
	 */
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	/**
	 * Pattern used for standard messages
	 */
	private static final String MSGPATTERN = "%1$-10s@%2$s [%3$-7s] %5$s\n";

	/**
	 * Pattern used for messages containing an Exception
	 */
	private static final String MSGPATTERN_WITH_EXCEPTION = MSGPATTERN + "%6$s\n";

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
	 */
	@Override
	public String format(LogRecord record) {
		String dateValue = this.dateFormatter.format(new Date(record.getMillis()));
		String message = record.getParameters() == null || record.getParameters().length == 0 ? record.getMessage()
				: MessageFormat.format(record.getMessage(), record.getParameters());
		if (record.getThrown() == null)
			return String.format(MSGPATTERN, record.getLoggerName(), dateValue, record.getLevel().getName(),
					record.getSourceClassName(), message);
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter, true);
		record.getThrown().printStackTrace(printWriter);
		printWriter.flush();
		stringWriter.flush();
		return String.format(MSGPATTERN_WITH_EXCEPTION, record.getLoggerName(), dateValue, record.getLevel().getName(),
				record.getSourceClassName(), message, stringWriter.toString());
	}
}
