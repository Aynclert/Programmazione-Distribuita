
Gli oggetti distribuiti si trovano alla confluenza di due aree della tecnologia software: 
- i sistemi distribuiti che puntano a realizzare un unico sistema integrato basato sulle risorse offerte da diversi calcolatori messi in rete;
- lo sviluppo e la programmazione orientata agli oggetti, che si focalizzano sulle modalità per ridurre la complessità dei sistemi software.

L'obiettivo è quello di realizzare servizi distribuiti riutilizzabili efficienti, flessibili, sicuri e robusti. Il tutto basato su un’architettura che utilizza come risorse dei nodi eterogenei, sia per l’hardware che per il software. Questa integrazione viene realizzata attraverso il middleware ad oggetti distribuiti, che risiede tra le applicazioni e lo strato sistema operativo.

Lo scopo del middleware è quello di rendere semplici questi compiti e di fornire delle astrazioni appropriate per i programmatori, ed è suddiviso in tre strati:
1. **Middleware di infrastruttura**: si occupa delle comunicazioni tra i sistemi operativi diversi e della gestione della concorrenza;
2. **Middleware di distribuzione**: basa i suoi servizi sul middleware d’infrastruttura per automatizzare operazioni comuni per la comunicazione. Tra i compiti più importanti abbiamo:
	1. richiedere un servizio ad un altro nodo potendo inviare parametri ( #marshalling) tra piattaforme hardware/software diversi;
	2. Utilizzare lo stesso canale di comunicazione ([[Socket]], ecc.) per diverse richieste, oppure utilizzare una sola macro-richiesta che include diverse richieste;
	3. Modificare la semantica delle operazioni di invocazione oltre quella tradizionale di unicast, come ad esempio la multicast oppure l’attivazione di oggetti in risposta ad invocazione di servizi;
	4. Riconoscimento e gestione dei malfunzionamenti di rete.
3. Middleware per servizi comuni di supporto: un layer che serve a fornire i servizi comuni a tutte le applicazioni distribuite, riutilizzabili in tutti i contesti.

L’obiettivo di questi tre livelli è quello di assicurare che il programmatore di applicazione concentri i propri sforzi sullo sviluppo della logica di business dell’applicazione e che non si debba interessare direttamente dei dettagli di comunicazione a livello di rete. Forniscono anche astrazioni utili per il programmatore, ben integrate nell’ambiente di sviluppo dell’applicazione.
Lo sviluppo avviene ad alto livello, permettendo di poter utilizzare e riutilizzare framework e soluzioni, appoggiandosi a metodologie evolute di ingegneria del software per rendere maggiormente proficua, efficiente e efficace la soluzione proposta.

Il middleware nei sistemi distribuiti può essere esplicito oppure implicito.