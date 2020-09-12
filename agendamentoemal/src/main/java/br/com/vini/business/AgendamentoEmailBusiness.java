package br.com.vini.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;

import br.com.vini.dao.AgendamentoDao;
import br.com.vini.entitiy.DbInfo;

@Stateless
public class AgendamentoEmailBusiness {

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
}
