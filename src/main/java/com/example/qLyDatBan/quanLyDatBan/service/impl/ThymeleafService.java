package com.example.qLyDatBan.quanLyDatBan.service.impl;

import java.sql.Date;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;

@Service
public class ThymeleafService {
	private static final String MAIL_TEMPLATE_BASE_NAME = "mail/MailMessages";
	private static final String MAIL_TEMPLATE_PREFIX = "/templates/";
	private static final String MAIL_TEMPLATE_SUFFIX = ".html";
	private static final String UTF_8 = "UTF-8";

	private static TemplateEngine templateEngine;

	static {
		templateEngine = emailTemplateEngine();
	}

	private static TemplateEngine emailTemplateEngine() {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(htmlTemplateResolver());
		templateEngine.setTemplateEngineMessageSource(emailMessageSource());
		return templateEngine;
	}

	private static ResourceBundleMessageSource emailMessageSource() {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename(MAIL_TEMPLATE_BASE_NAME);
		return messageSource;
	}

	private static ITemplateResolver htmlTemplateResolver() {
		final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix(MAIL_TEMPLATE_PREFIX);
		templateResolver.setSuffix(MAIL_TEMPLATE_SUFFIX);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding(UTF_8);
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	public String getContent(BookingEntity booking) {
		final Context context = new Context();

		String name = booking.getCustomerDetail().getName();
		int booking_id = booking.getId();
		Date booking_date = booking.getBooking_date();
		String note = booking.getAddition_note();
		String table = booking.getViews().getName();

		context.setVariable("name", name);
		context.setVariable("booking_id", booking_id);
		context.setVariable("booking_date", booking_date);
		context.setVariable("note", note);
		context.setVariable("table", table);

		String TEMPLATE_NAME = "accept_mail";
		if (booking.getBooking_status() == 0) {
			TEMPLATE_NAME = "reject_mail";
		}

		return templateEngine.process(TEMPLATE_NAME, context);
	}
}
