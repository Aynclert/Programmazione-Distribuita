
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
- *java.rmi* e *java.rmi.server*: contengono il meccanismo delle invocazioni remote;
- *java.rmi.activation*: contiene il meccanismo di attivazione per gli oggetti remoti;
- *java.rmi.dgc*: contiene la Distributed Garbage Collection;
- *java.rmi.registry*: contiene il servizio di localizzazione.

Ogni interfaccia remota deve estendere *java.rmi.remote*, ovvero una interfaccia marker, di cui ogni metodo descritto deve:
1. dichiarare esplicitamente l'eccezione *java.rmi.RemoteException*;
2. contenere parametri remoti dichiarati tramite la loro interfaccia remota, e non la classe.


Per realizzare l'implementazione di una interfaccia remota, si hanno due modi:
1. definire una classe derivante da *java.rmi.server* e contenente l'implementazione dell'oggetto;
2. definire una classe il cui comportamento derivi da un'altra classe non remota, in modo date da dover solo esportare l'oggetto ed implementare la semantica di alcune operazioni di Object per oggetti remoti non ridefiniti in *java.rmi.server*.

Nel modello distribuito, l'oggetto #Stub del #Client espone localmente le stesse interfacce remote definite dall'oggetto remoto. Il #Client può accedere al tipo di un oggetto remoto controllandone l'interfaccia remota attraverso **instanceof**.
Per poter invocare un metodo remoto, il #Client deve aver a disposizione un suo riferimento rmeoto, ottenibile come risultato di altre invocaizoni (locali o remote) di metodi, oppure attraverso un servizio di directory. Quest'ultimo fornisce un meccanismo di **name server** nella classe *java.rmi.Naming*, la quale:
- permette di gestire riferimenti ad oggetti remoti specificando un ID;
- fornisce metodi di ricerca (lookup()), registrazione(bind(), unbind(), rebind()), ed elenco (list()) degli ID registrati, accedendovi con uno standard URL.
Tutti i metodi remoti implementano l'eccezione RemoteException.

Un metodo remoto può dichiarare solo parametri o valori restituiti  serializzabili. 
Un oggetto locale passato come parametro o restituito come valore da un'invocazione remota, viene passato per copia.
Quando si passa un oggetto remoto come parametro o lo si ottiene come valore restituito, viene passato il suo #Stub e non l'implementazione.


Una prima modifica applicata alla classe Object da *java.rmi.server.RemoteObject* è la ridefinizione di:
- X.**hashCode()**, che restituirà lo stesso codice per due #Stub diversi di oggetti remoti che si riferiscono allo stesso oggetto remoto;
- X.**equals()**, che restituisce un booleano T se il riferimento passato è uguale al riferimento dello #Stub di X;
- X.**toString()**, che restituisce informazioni circa su quale macchina si trova l'oggetto remoto, il nome della classe ed un codice hash.
Queste modifiche non vengono effettuate qualora si usi il metodo attraverso *exportObject()*.
I #Client degli oggetti remoti interagiscono soltanto con l'interfaccia remota.
Il *passaggio dei parametri* è possibile in modo trasparente, infatti i riferimenti remoti possono essere passati/restituiti a/da un metodo come parametri, o restituiti come valori.
Per la *gestione dei tipi* si può effettuare un semplice casting di un oggetto remoto ad una qualsiasi interfaccia remota che implementa.
La differenza nelle *invocazioni di metodo* sta nel fatto che le invocazioni remote forzano il programmatore a dover gestire esplicitamente i fallimenti di invocazioni di metodi remoti.


Il sistema di Java RMI è strutturato su tre layer:
- [[Stub&Skeleton layer]]: comprende gli #Stub lato client e gli #Skeleton lato server;
- [[Remote Reference Layer]]: specifica il comportamento dell'invocazione e la semantica del riferimento;
- [[Transport Layer]]: si occupa della connessione e della sua gestione.


Java RMI utilizza il **caricamento dinamico delle classi**.
Quando si fa il #marshalling degli oggeti per la trasmissione, essi vengpono anche annotati con il #codebase, ovvero un URL di un server WWW da dove è possibile trovare la definizione della classe, ovvero il suo **.class**.
Quando viene fatto l'unmarshalling dell'oggetto, il classloader cerca di risolvere uil nome della classe nel suo contesto, e in caso negativo, viene acceduta la definizione della classe per poter ricreare l'oggetto all'altro capo della comunicazione.

Il meccanismo di #marshalling di Java RMI si basa sulla specializzazione del meccanismo tradizionale di #serializzazione effettuata da *ObjectOutputStream*. Questo meccanismo avviene modificando tre metodi della classe *ObjectOutputStream*:
- **replaceObject()**: può definire un metodo alternativo per serializzare un oggetto sullo stream;
- **enableReplaceObject()**: restituisce un booleano e stabilisce se l'istanza deve o meno specializzare il meccanismo di #serializzazione, usando **replaceObject()**;
- **annotateClass()**: permette di inserire informazioni addizionali sulla classe, viene usato per specificare il #codebase e permettere quindi il caricamento dinamico.
L'operazione più complessa è quella del metodo **replaceObject()**, che:
- se l'oggetto da serializzare è istanza di *java.rmi.Remote* e
	1. l'oggetto risulta esportato a runtime di RMI, allora restituisce il suo #Stub attraverso **java.rmi.server.RemoteObject.toStub()**;
	2. l'oggetto non è esportato, allora restituisce l'oggetto remoto stesso;
- se l'oggetto da serializzare non è istanza di *java.rmi.Remote*, allora viene restituito **writeObject()**.


Il processo diu creazione di un programma Java RMI si suddivide in due sottoprocessi:
1. sviluppo ed esecuzione del #Server;
2. sviluppo del #Client.

Il primo passo è la definizione dei servizi offerti dal nostro #Server, specificandoli all'interno di una interfaccia che:
1. deve derivare dall'interfaccia mark-up **Remote**;
2. deve poter lanciare l'eccezione **java.rmi.RemoteException** per ogni metodo al suo interno.

Ogni oggetto remoto deve essere istanza di una classe che:
1. implementi una o più interfacce remote;
2. derivi da **java.rmi.UnicastRemoteObject**, implicando la necessità di un costruttore scritto.

Una volta specificati interfaccia remota e #Server, si possono generare automaticamente i file #Stub e #Skeleton attraverso lo stub compiler di RMI, chiamato **rmic**.

A questo punto è necessario il servizio di naming, chiamato **rmiregistry**, in modo tale che l'oggetto remoto possa essere accessibile da eventuali client. Rmiregistry deve essere lanciato prima di eseguire il server poiché, tra le prime operazioni, il server andrà a registrarsi presso questo registro con un'etichetta. Il servizio di naming deve essere lanciato nella directory in cui si trova il .class dello #Stub.

Dopodiché va scelta la politica di sicurezza per la macchina virtuale, ma noi adotteremo una politica estremamente liberale, garantendo ogni permesso possibile.

Una volta lanciato il #Server, esso si registrerà sul servizio di naming, attraverso metodi del package *java.rmi.Naming*, e non è possibile avere un servizio di naming "esterno".

Per il #Client non vi sono molte modifiche rispetto ad una applicazione locale. Si deve ottenere un riferimento remoto ad un oggetto #Server attraverso i servizi di *java.rmi.Naming*, e si deve gestire l'eccezione **RemoteException** lanciata da tutti i metodi remoti.

Durante la compilazione del #Client , lo #Stub del #Server deve essere presente nella directory dove il #Client viene compilato.

Uno dei pattern strutturali più importanti è la classe **adapter**, che implementa un'interfaccia conosciuta dai suoi #Client e fornisce accesso ad una classe chiamata **adaptee** non conosciuta da essi. Attraverso questo pattern, l'**adapter** fornisce un servizio per l'**adaptee** che non deve essere modificata per fornire i servizi direttamente al #Client, implementando l'interfaccia appropriata.