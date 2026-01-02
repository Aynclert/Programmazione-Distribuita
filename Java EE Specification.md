
[[Java EE]] è una specifica ad ombrello che raggruppa ed integra altre #API. Un application server deve implementare 31 specifiche per essere compatibile con Java EE 7 e uno sviluppatore deve conoscere migliaia di API per sfruttare al massimo il contenitore. 

Java EE 7 si concentra sul portare semplicità alla piattaforma introducendo un semplice modello di programmazione basato su #POJO , un profilo Web e un infarinatura di alcune tecnologie obsolete. 

La specifica Java EE 7 è definita dal JSR 342 e contiene 31 altre specifiche. Un server di applicazioni che mira ad essere conforme a Java EE 7 deve implementare tutte queste specifiche.

Nel dominio #Web:
- I servizi WEB non sono stati aggiornati poiché Web #SOAP non è stato aggiornato;
- I servizi Web #REST sono stati ampiamente utilizzati recentemente;
- JAX-RS 2.0 ha seguito un importante aggiornamento con l'introduzione dell' #API client;
- La nuova specifica JSON-P (JSON Processing) è l'equivalente di JAXP ma per JSON invece di #XML;
- #JSP o JSTL non sono stati modificati in quanto non sono stati aggiornati;
- Expression Language è stato estratto da #JSP e ora si evolve nel suo JSR;
- #servlet  e #JSF sono stati entrambi aggiornati e #WebSocket 1.0 è stato introdotto in Java EE 7.

Nel dominio business ci sono due aggiornamenti principali:
-  #JMS 2.0 e #JTA 1.2 non sono aggiornati;
- #EJB , specifiche #JPA e #Interceptor hanno ricevuto aggiornamenti minori.

Nei domini di sicurezza, gestione e altro:
- Elaborazione Batch;
- Concurrency Utility per Java EE;
- Aggiornamenti di:
	- Bean Validation 1.1;
	- #CDI 1.1;
	- #JMS 2.0.


Java EE 7 definisce un profilo singolo chiamato Web Profile, il cui scopo è consentire ai developer di creare applicazioni web con l'insieme appropriato di tecnologie. Web Profile 7.0 è specificato in un #JSR separato ed è, per ora, l'unico profilo della piattaforma Java EE 7.

Java EE 7 non è composto solo da queste specifiche, ma dipende anche da Java SE 7.
