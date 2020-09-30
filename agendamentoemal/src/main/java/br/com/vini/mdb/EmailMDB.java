package br.com.vini.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MessageListener;

import br.com.vini.business.AgendamentoEmailBusiness;
import br.com.vini.entitiy.DbInfo;
import br.com.vini.interception.LoggerAnnotation;

@LoggerAnnotation
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:/jms/queue/EmailQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class EmailMDB implements MessageListener {
    @Inject
    private AgendamentoEmailBusiness agendamentoEmailBusiness;
    @Override
    public void onMessage(javax.jms.Message message) {
    	
    	try {
			DbInfo dbInfo = message.getBody(DbInfo.class);
			
			agendamentoEmailBusiness.enviarAgendamentoEmail(dbInfo);
		
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
}