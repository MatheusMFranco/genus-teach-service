package com.genus.teach.util;

import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.Pageable;

import com.genus.teach.enumeration.RequestLogEnum;

public abstract class FormatUtil {

	private FormatUtil(){}

	public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd/MM/YYYY");

	public static StringBuilder formatLogBuilder(RequestLogEnum type, String entity) {
		String format = "Request method to %s %s";
		return new StringBuilder(String.format(format, type.getName(), entity));
	}

	public static StringBuilder formatLogBuilder(RequestLogEnum type, String entity, Pageable pageable) {
		return new StringBuilder(formatWithPages(formatLogBuilder(type, entity).toString(), pageable));
	}

	public static String formatLog(
			RequestLogEnum type,
			String entity,
			String requestValue,
			Pageable pageable
	) {
		String format = formatLogBuilder(type, entity)
				.append(": %s")
				.toString();
		return formatWithPages(String.format(format, requestValue), pageable);
	}

	public static String formatLogBy(
			RequestLogEnum type,
			String entity,
			String param,
			String requestValue
	) {
		String format = formatLogBuilder(type, entity)
				.append(" by %s: %s")
				.toString();
		return String.format(format, param, requestValue);
	}

	public static String formatLogBy(
			RequestLogEnum type,
			String entity,
			String param,
			String requestValue,
			Pageable pageable
	) {
		return formatWithPages(formatLogBy(type, entity, param, requestValue), pageable);
	}

	public static String formatLogBetween(
			RequestLogEnum type,
			String entity,
			String param,
			String start,
			String end
	) {
		String format = formatLogBuilder(type, entity)
				.append(" %s between %s and %s")
				.toString();
		return String.format(format, param, param, start, end);
	}

	public static String formatLogBetween(
			RequestLogEnum type,
			String entity,
			String param,
			String start,
			String end,
			Pageable pageable
	) {
		return formatWithPages(formatLogBetween(type, entity, param, start, end), pageable);
	}

	private static String formatWithPages(String log, Pageable pageable) {
		return new StringBuilder(log)
				.append(" on page: ")
				.append(pageable.getPageNumber())
				.append(" size: ")
				.append(pageable.getPageSize())
				.toString();
	}

}
