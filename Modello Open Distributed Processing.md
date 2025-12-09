
E' importante la condivisione di un modello comune in maniera da essere indipendente dalla specifica implementazione tecnologica.
Il modello RM-ODP si basa ed integra il modello tradizionale di rete proposto da ISO/OSI su sette layers, aggiungendo ai 6 già presenti un 7° chiamato application. Questo modello ha come obiettivo quello di gestire i problemi di comunicazione in un sistema rispetto ai problemi di connessione.
Possiamo identificare le caratteristiche attraverso delle parole chiave:
- **Remoto**: le componenti devono poter essere locali o remote, anche potenzialmente localizzate su macchine diverse;
- **Concorrenza**: un sistema distribuito è per sua stessa natura concorrente, in quanto la contemporanea esecuzione di due (o più) istruzioni è possibile, su macchine diverse;
- **Assenza di uno stato globale**: non esiste una maniera per poter determinare lo stato globale del sistema;
- **Malfunzionamenti parziali**: ogni componente può smettere di funzionare correttamente, in maniera indipendente dalle altre componenti e questo fallimento non deve inficiare le funzionalità che sono localizzate altrove nel sistema distribuito;
- **Eterogeneità**;
- **Autonomia**: non ha un singolo punto dal quale esso può essere controllato, coordinato e gestito;
- **Evoluzione**: i sistemi distribuiti devono assecondare la evoluzione dell’ambiente all’interno del quale vengono realizzati e forniscono le loro funzionalità;
- **Mobilità**: naturale deve essere la mobilità dei nodi e delle risorse (ad esempio, dati) all’interno del sistema in modo da poter adattare al meglio le prestazioni del sistema.

Requisiti di non funzionalità
Indicano essenzialmente la qualità del sistema e specificano che la progettazione deve puntare a realizzare sistemi distribuiti che hanno le seguenti caratteristiche:
- **aperti**: in modo da supportare la portabilità di esecuzione e di interoperabilità attraverso interfacce e servizi ben documentati ed aderenti a standard noti e riconosciuti;
- **integrati**: così da incorporare al proprio interno sistemi e risorse differenti senza dover utilizzare strumenti ad-hoc;
- **Flessibili**: per poter evolvere e fare evolvere i sistemi distribuiti in maniera da integrare sistemi legacy al proprio interno;
- **Modulari**: in modo da permettere ad ogni componente di poter essere autonoma ma con un grado di interdipendenza verso il resto del sistema;
- **Supportino la federazione di sistemi**: in modo da unire diversi sistemi, per lavorare e fornire servizi in maniera congiunta;
- **Facilmente gestibili**: in modo da permettere il controllo, la gestione e la manutenzione per configurarne i servizi, la loro qualità e le politiche di accesso;
- **Supporto per la qualità del servizio**: la *tolleranza ai malfunzionamenti* è una delle principali richieste di qualità del servizio di un sistema distribuito in quanto deve raggirare i malfunzionamenti utilizzando (dinamicamente) componenti alternative per fornire funzionalità che alcune componenti non sono in grado temporaneamente di fornire;
- **Scalabili**: perché qualsiasi sistema distribuito accessibile da Internet può essere soggetto a picchi di carico non prevedibili e deve essere in grado di gestirli;
- **Sicuri**: così che utenti non autorizzati non possano accedere a dati sensibili;
- **[[Trasparenza]]**: mascherando i dettagli e le differenze dell’ architettura sottostante che assicura la distribuzione dei servizi sulle componenti del sistema.



