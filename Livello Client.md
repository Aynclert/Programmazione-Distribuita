
Il #Client di Java EE è solitamente un #Client #Web o un'applicazione.

1. Un #Client #Web , anche chiamato "thin client", è costituito da due parti:
	- Pagine Web dinamiche contenenti vari tipi di linguaggi di markup (HTML, XML e così via), generati da #componenti #Web in esecuzione nel [[Livello Web]];
	- Un browser #Web che esegue il rendering delle pagine ricevute dal #Server.
	Solitamente un #Client #Web non esegue #query sui #database, non eseguono regole aziendali complesse o si connettono ad applicazioni legacy. Di fatto, tali operazioni vengono scaricate sui [[Enterprise Java Bean]] in esecuzione sul #Server [[Java EE]], dove possono sfruttare la sicurezza, la velocità, #servizi e l'affidabilità delle tecnologie [[Java EE]] sul lato #Server.


2. Un #Client applicativo viene viene eseguito su un computer #Client  e fornisce agli utenti un modo per gestire attività che richiedono un'interfaccia utente più ricca di quella che può essere fornita da un linguaggio di markup. I #Client dell'applicazione accedono direttamente ai [[Enterprise Java Bean]] in esecuzione nel [[Livello Business]]. Tuttavia, se i requisiti dell'applicazione lo giustificano, un #Client applicativo può aprire una connessione HTTP per stabilire la comunicazione con una #servlet in esecuzione nel [[Livello Web]].


3. Una #Applet, scritta in linguaggio Java e possibilmente inclusa in una pagina #Web ricevuta dal [[Livello Web]], è una piccola applicazione #Client eseguita nella #JVM installata nel browser #Web. I sistemi #Client, tuttavia, avranno probabilmente bisogno del plug-in Java e di un file di politica di sicurezza affinché la applet possa essere eseguita correttamente.


4. Uno o più #JavaBeans possono essere inclusi nei livelli #Client e #Server, per gestire il flusso di dati tra i seguenti:
	- un #Client applicativo o #Applet e #componenti in esecuzione sul #Server Java EE;
	- #componenti del #Server e un #database.
	I #componenti #JavaBeans non sono considerati #componenti Java EE.


l #Client comunica con il [[Livello Business]] in esecuzione sul #Server Java EE direttamente o, come nel caso di #Client in esecuzione in un browser, passando attraverso pagine #Web è #servlet in esecuzione nel [[Livello Web]].