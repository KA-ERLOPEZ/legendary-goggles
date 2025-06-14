package com.sistema.pizzeria.elpirata.api.core.services;

public interface EmailService {

	/**
	 * Envía un mensaje de correo electrónico simple.
	 *
	 * @param to      la dirección de correo electrónico del destinatario
	 * @param subject el asunto del correo electrónico
	 * @param text    el contenido del correo electrónico
	 */
	void sendSimpleMessage(String to, String subject, String text);

	/**
	 * Envía un mensaje de correo electrónico HTML.
	 *
	 * @param to      la dirección de correo electrónico del destinatario
	 * @param subject el asunto del correo electrónico
	 * @param text    el contenido HTML del correo electrónico
	 */
	void sendHtmlMessage(String to, String subject, String text);

	/**
	 * Envía un mensaje de correo electrónico con un archivo adjunto.
	 *
	 * @param to               la dirección de correo electrónico del destinatario
	 * @param subject          el asunto del correo electrónico
	 * @param text             el contenido del correo electrónico
	 * @param pathToAttachment la ruta al archivo adjunto
	 */
	void sendSimpleMessageWithAttachment(String to, String subject, String text, String pathToAttachment);

	/**
	 * Envía un mensaje de correo electrónico con un recurso en línea.
	 *
	 * @param to                   la dirección de correo electrónico del
	 *                             destinatario
	 * @param subject              el asunto del correo electrónico
	 * @param text                 el contenido del correo electrónico
	 * @param pathToInlineResource la ruta al recurso en línea
	 * @param cid                  el ID del recurso en línea
	 */
	void sendSimpleMessageWithInlineResource(String to, String subject, String text, String pathToInlineResource, String cid);
	
}
