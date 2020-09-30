
1 - ir na pasta bin do wildfly-15 e dar o comando jboss-cli --connect e executar os seguintes comando para configurar o 
driver e o datasource de conexão com o oracle 

module add --name=com.oracle --resources=C:/SERVERS/wildfly-15.0.0.Final/wildfly-15.0.0.Final/ojdbc8-12.2.0.1.jar --dependencies=javax.api,javax.transaction.api


/subsystem=datasources/jdbc-driver=oracle:add(driver-name=oracle,driver-module-name=com.oracle,driver-xa-datasource-class-name=oracle.jdbc.xa.client.OracleXADataSource)

data-source add --name=AgendamentoDS --jndi-name=java:jboss/datasources/AgendamentoDS --driver-name=oracle  --connection-url=jdbc:oracle:thin:@//localhost:1521/INSTANCIA_BD --user-name=USUARIO_BD --password=SENHA_BD --min-pool-size=10 --max-pool-size=20




2 - Tabela de teste para criar no banco oracle

-- Create table
create table DB_INFO2
(
  id          NUMBER(10) not null,
  version     VARCHAR2(20) not null,
  name        VARCHAR2(30) not null,
  description VARCHAR2(240)
)
tablespace GIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 64K
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
  

3 - 
URL para chamar o GET e verificar se conseguiu consultar no banco


http://localhost:8080/agendamentoemal-0.0.1-SNAPSHOT/resources/agendamentoemail

Atencao:

Depois que foi feito o redireiconamento no descritor jboss-web.xml
a URL passou a ser
http://localhost:8080/resources/agendamentoemail

E para acessar a pagina feita em vue.js que faz a chamada via REST da aplicação 
acesssar
http://localhost:8080

4 - URL de teste POST para enviar um JSON que será convertido em DbInfo e persistido no banco
 
  Header Content-Type = application/json
  
  BODY
  enviando via postman
  
{
    "name":"teste3"
}



5 - Configurando um email server no wildlfy

logar no jboss-cli.bat
e connect
Os comandos que o instrutor executa são os seguintes:

/subsystem=mail/mail-session=agendamentoMailSession:add(jndi-name=java:jboss/mail/AgendamentoMailSession)
/socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=my-smtp-binding:add(host=smtp.mailtrap.io, port=2525)
/subsystem=mail/mail-session=agendamentoMailSession/server=smtp:add(outbound-socket-binding-ref= my-smtp-binding, username=bc82647d48b758, password=6320632ea13bd1, tls=true)

Obs: usar o site mailtrap para fazer um cadastro de um email fake
https://mailtrap.io/


6 JMS
 Configurar queue no WILDLFY
 
 connect no jboss-cli.bat
 
 jms-queue add --queue-address=EmailQueue --entries=java:/jms/queue/EmailQueue
 
 
 Para listar o conteudo da fila JMS no console do jboss
 
  jms-queue list-messages --queue-address=EmailQueue
 


7 
A anotacao na classe AgendamentoEmailBusiness
@TransactionManagement(TransactionManagementType.CONTAINER) eh implicita (se nao declarar eh container tbm)
Significa que o container vai gerenciar a transacao = CMT


Se fosse
@TransactionManagement(TransactionManagementType.BEAN)
Caberia ao DEV gerenciar a transacao com tx.begin e tx.commit
