package br.com.vini.timer;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import br.com.vini.business.AgendamentoEmailBusiness;
import br.com.vini.entitiy.DbInfo;

@Singleton
public class AgendamentoEmailTimer {
	
	@Inject
	AgendamentoEmailBusiness agendamentoEmailBusiness;
	
	private static Logger logger = Logger.getLogger(AgendamentoEmailTimer.class.getName());
	@Schedule(hour="*",minute="*")
	public void enviarEmailsAgendados() {
		
		List<DbInfo> listaEmail = agendamentoEmailBusiness.listarAgendamentosEmail();
		listaEmail.stream().forEach(DbInfo -> agendamentoEmailBusiness.enviarAgendamentoEmail(DbInfo));
		
		logger.info("Rodou");		 
	}
}
