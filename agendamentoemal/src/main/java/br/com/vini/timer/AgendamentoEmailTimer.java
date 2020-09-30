package br.com.vini.timer;

import java.util.List;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.vini.business.AgendamentoEmailBusiness;
import br.com.vini.entitiy.DbInfo;

@Singleton
public class AgendamentoEmailTimer {
	
	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;
	
	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;
	
	
	@Inject
	AgendamentoEmailBusiness agendamentoEmailBusiness;
	
	private static Logger logger = Logger.getLogger(AgendamentoEmailTimer.class.getName());
	@Schedule(hour="*",minute="*")
	public void enviarEmailsAgendados() {
		
		List<DbInfo> listaEmail = agendamentoEmailBusiness.listarAgendamentosEmail();
		listaEmail.stream().forEach(agendamentoEmail -> context.createProducer().send(queue, agendamentoEmail));
		
		//listaEmail.stream().forEach(DbInfo -> agendamentoEmailBusiness.enviarAgendamentoEmail(DbInfo));
		
		logger.info("Rodou");		 
	}
}
