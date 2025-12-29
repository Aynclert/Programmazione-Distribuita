
Java EE è un modello basato su Java e sull'utilizzo della sua macchina virtuale. Esso supporta le applicazioni che implementano servizi aziendali per clienti, dipendenti, fornitori, partner e altri che fanno richieste o contributi all'impresa. Le funzioni aziendali per supportare questi vari utenti sono condotte nel livello intermedio, che rappresenta un ambiente strettamente controllato dal dipartimento di information technology di un'azienda, il quale, a sua volta, viene eseguito su hardware #Server dedicato e ha accesso ai #Server completi dell'azienda.
Il modello di applicazione Java EE definisce un'architettura e gestibilità necessarie per le applicazioni di livello enterprise.

La piattaforma Java EE utilizza un modello di applicazione multitier #distribuito per le applicazioni aziendali. La logica dell'applicazione è suddivisa in #componenti che sono suddivisi in base alle funzioni che svolgono.

I livelli dell'applicazione sono:
1. [[Livello Client]]: i #componenti di questo livello vengono eseguiti sul computer #Client;
2. [[Livello Web]]: i #componenti di questo livello vengono eseguiti sul #Server Java EE;
3. [[Livello Business]]: i #componenti di questo livello vengono eseguiti sul #Server Java EE;
4. [[Livello EIS (Enterprise Information System)]]: i #componenti di questo livello sono software più avanzati.


L'ambiente di sicurezza Java EE consente di definire vincoli di sicurezza al momento dell'implementazione, e fornisce regole di controllo degli accessi dichiarative standard definite dallo sviluppatore e interpretate quando l'applicazione viene distribuita sul server. Fornisce inoltre i meccanismi di accesso standard in modo che gli sviluppatori di applicazioni non debbano implementare questi meccanismi nelle loro applicazioni.

Le applicazioni Java EE sono costituite da #componenti, ovvero unità software funzionali autonome che vengono assemblate con le relative classi e files e che comunicano con altri componenti.
La specifica Java EE definisce i seguenti #componenti Java EE:
- I #Client e #Applet dell'applicazione sono #componenti eseguiti sul #Client;
- Java Servlet, #JSF (JavaServer Faces) e #JSP (JavaServer Pages) sono #componenti #Web eseguiti sul #Server;
- I #componenti [[Enterprise Java Bean]] sono componenti aziendali che vengono eseguiti sul #Server.
Tutti i #componenti Java EE sono scritti in Java e compilati nello stesso linguaggio. Inoltre sono verificati per essere ben formati e conformi alle specifiche Java EE e distribuiti alla produzione, dove sono eseguiti e gestiti dal #Server Java EE.

Il #Server Java EE fornisce servizi sottostanti sotto forma di [[Contenitori]] per ogni tipologia di #componenti.

Java EE fornisce dei [[Servizi]] per le proprie applicazioni distribuite.