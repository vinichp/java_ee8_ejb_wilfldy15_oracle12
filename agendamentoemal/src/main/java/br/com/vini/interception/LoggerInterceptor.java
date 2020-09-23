package br.com.vini.interception;

import javax.annotation.Priority;
import javax.ejb.EJBException;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolationException;

import java.util.logging.Logger;

import br.com.vini.resources.AgendamentoEmailResource;

@Interceptor
@Priority(1) 
@LoggerAnnotation
public class LoggerInterceptor {

	public static Logger logger = Logger.getLogger(AgendamentoEmailResource.class.getName());


	@AroundInvoke
	public Object treatException(InvocationContext context) throws Exception {
		
		try {
			return context.proceed();

	}catch(Exception e){
		if(e.getCause() instanceof ConstraintViolationException) {
			logger.info("LOGGER com Annotation: " + e.getMessage()); 
		}else {
			logger.severe(e.getMessage());
		}
		throw e;
	}
		
	}
}
