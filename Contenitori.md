
I #contenitori sono l'interfaccia tra un componente e la funzionalità specifica della piattaforma di basso livello che supporta il componente. Prima che possa essere eseguito, un componente #Web, [[Enterprise Java Bean]] o applicazione #Client deve essere assemblato in un modulo Java EE e #distribuito nel relativo contenitore.
Le impostazioni del contenitore personalizzano il supporto sottostante fornito dal #Server Java EE, inclusi servizi quali sicurezza, gestione delle #transazioni, ricerche API [[JNDI]] e connettività remota.
- Il modello di sicurezza Java EE consente di configurare un componente #Web o [[Enterprise Java Bean]] in modo che le risorse di sistema siano accessibili solo agli utenti autorizzati.
- Il modello di transazione Java EE consente di specificare le relazioni tra i metodi che costituiscono la singola transazione in modo che tutti i metodi in una transazione vengano considerati come una singola unità.
- I servizi di ricerca [[JNDI]] forniscono un'interfaccia unificata a più servizi di denominazione e directory nell'azienda in modo che i #componenti dell'applicazione possano accedere a questi servizi.
- Il modello di connettività remota Java EE gestisce le comunicazioni di basso livello tra #Client e [[Enterprise Java Bean]]. Dopo aver creato un [[Enterprise Java Bean]], un #Client richiama i metodi su di esso come se si trovasse nella stessa #JVM.
Poiché l'architettura Java EE fornisce #servizi configurabili, i #componenti all'interno della stessa applicazione possono comportarsi diversamente in base alla posizione in cui vengono distribuiti. Il contenitore gestisce anche servizi non configurabili, quali:
- cicli di vita degli [[Enterprise Java Bean]] e dei #servlet;
- pooling delle risorse di connessione al #database;
- #persistenza dei dati;
- accesso alle API della piattaforma Java EE.

Il processo di distribuzione installa i #componenti dell'applicazione Java EE nei #contenitori Java EE, i quali sono:
- [[Contenitore EJB]];
- [[Contenitore Web]];
- [[Contenitore del client dell'applicazione]];
- [[Contenitore di applet]].

Una applicazione Java EE è pacchettizzata in una o più standard per la distribuzione su sistemi compatibili con Java EE. Ogni unità contiene:
- uno o più #componenti funzionali ([[Enterprise Java Bean]], pagina Web, #servlet o #Applet);
- un descrittore di distribuzione opzionale che ne descrive il contenuto (.xml).


I #componenti distribuiti in contenitori possono essere richiamati tramite protocolli diversi:
- HTTP: protocollo Web onnipresente nelle applicazioni moderne. L' #API lato #Client è definita nel pacchetto *java.net*, mentre quella lato #Server e definita dalle interfacce #servlet, #JSF e #JSP, nonché dai #servizi #Web #SOAP e #RESTful.
- RMI/IIOP: [[Java RMI]] consente di chiamare oggetti remoti indipendentemente dal protocollo sottostante. RMI/IIOP è un'estensione di RMI utilizzata per l'integrazione con CORBA. Il linguaggio di descrizione dell'interfaccia Java consente ai componenti dell'applicazione Java EE di richiamare oggetti CORBA esterni utilizzando il protocollo IIOP.


