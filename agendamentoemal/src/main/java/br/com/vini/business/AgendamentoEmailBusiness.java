package br.com.vini.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import br.com.vini.dao.AgendamentoDao;
import br.com.vini.entitiy.DbInfo;

@Stateless
public class AgendamentoEmailBusiness {
	
	@Resource(lookup = "java:jboss/mail/AgendamentoMailSession3")
	private Session sessaoEmail;
	private static String EMAIL_FROM = "mail.address";
	private static String EMAIL_USER = "mail.smtp.user";
	private static String EMAIL_PASSWORD = "mail.smtp.pass";

	@Inject
	AgendamentoDao agendamentoDao;

	public List<DbInfo> listarAgendamentosEmail(){

		List<String> emails =  new ArrayList<>();
		emails.add("Vinicius@cpqd.com.br");
		emails.add("pinheiro@cpqd.com.br");

		return agendamentoDao.listarAgendamentos();

		//return emails;


	}


	public void salvarAgendamento(@Valid DbInfo dbInfo) {

		//if(dbInfo.getVersion() == null) {
		//	dbInfo.setVersion("XX");
		//}
		agendamentoDao.salvarAgendamento(dbInfo);
	}
	
	public List<DbInfo> listarEmailsNaoEnviados(){
		return agendamentoDao.listarAgendamentoNaoEnviados();
		
	}


	public Object enviarAgendamentoEmail(DbInfo dbInfo) {
		try {
		    MimeMessage mensagem = new MimeMessage(sessaoEmail);
		    mensagem.setFrom(sessaoEmail.getProperty(EMAIL_FROM));
		    mensagem.setRecipients(Message.RecipientType.TO, "vinic.pinheiro9@gmail.com");
		    mensagem.setSubject(dbInfo.getVersion());
		    mensagem.setText(Optional.ofNullable(dbInfo.getVersion()).orElse(""));
		    Transport.send(mensagem,
		    sessaoEmail.getProperty(EMAIL_USER),
		    sessaoEmail.getProperty(EMAIL_PASSWORD));
		} catch (MessagingException e) {
		    throw new RuntimeException(e);
		}
		return dbInfo;
	}
	
	
}
