package br.com.vini.resources;
import java.util.List;
//import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.vini.business.AgendamentoEmailBusiness;
import br.com.vini.entitiy.DbInfo;
import br.com.vini.interception.LoggerAnnotation;

@Path("/agendamentoemail")
@LoggerAnnotation
public class AgendamentoEmailResource {
	
	//private static Logger logger = Logger.getLogger(AgendamentoEmailResource.class.getName());
	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarAgendamentosEmail() {
		
		List<DbInfo> emails = agendamentoEmailBusiness.listarAgendamentosEmail();
		return Response.ok(emails).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvarAgendamento(DbInfo dbInfo) {
		
		agendamentoEmailBusiness.salvarAgendamento(dbInfo);
		
		
		return Response.status(201).build();
	}
	
	
}
