
Java RMI, ovvero Java Remote Method Invocation, è una libreria di Java che permette lo sviluppo di applicazioni distribuite, fornendo la possibilità di effettuare comunicazione remota tra i programmi scritti in Java. Funge da integration library situata al disopra delle librerie standard di Java. Le applicazioni RMI seguono un'architettura client-server, dove il server crea gli oggetti remoti e attende che gli oggetti client ne utilizzino i servizi.
- Java RMI deve poter offrire al programmatore un meccanismo semplice per l'invocazione di metodi offerti da un oggetto remoto.
- Java RMI fornisce anche un garbage collector distribuito in modo da preservare la modalità di gestione della memoria di Java che solleva il programmatore dal doversi occupare esplicitamente dell'allocazione e deallocazione della memoria.
- In Java RMI il fatto che un oggetto sia remoto o locale deve essere chiaro ed evidente in progettazione ed implementazione.
- Si deve assicurare la minima complessità all'applicazione distribuita basata su Java RMI, ed il livello di complicazione introdotto da un oggetto distribuito deve essere limitato.
- Il modello ad oggetti distribuiti non deve alterare il livello di sicurezza offerto da Java.


Java RMI fornisce due tipi di invocazione:
1. **Unicast**: da un #Client verso un #Server;
2. **Multicast**: da un #Client verso diversi #Server replicati.
Inoltre deve essere possibile che l'oggetto server sia attivato solo al momento dell'invocazione e che i riferimento ad oggetti persistenti siano persistenti.

La descrizione dei servizi offerti da remoto, in Java RMI, è contenuta all'interno di un'interfaccia remota che dichiara metodi remoti. L'oggetto client di oggetti remoti server utilizza, infatti, esclusivamente l'interfaccia remota dell'oggetto, non la sua implementazione.

Java RMI è contenuto in 5 *package*:
- 