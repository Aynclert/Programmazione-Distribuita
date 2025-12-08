
Java fornisce le API per la programmazione di rete nel package *java.net*, al cui interno sono presenti classi come *InetAddress*.
La comunicazione tra programmi su Internet avviene tramite il protocollo TCP/IP, e vengono usati i #socket per ricevere e trasmettere i dati.
Il protocollo TCP (Transmission Control Protocol) offre una connessione affidabile, mentre l'UDP (User Datagram Protocol) permette di inviare pacchetti dati ( #datagram ).
La trasmissione tramite #socket avviene assegnando una specifica porta che serve ad identificare, tra tutti i pacchetti che arrivano, quali sono quelli destinati ad una determinata applicazione.

I #socket TCP sono gli endpoint di una comunicazione bidirezionale sulla rete che unisce due programmi. Nel loro uso, distinguiamo due computer coinvolti:
- #Server: è in esecuzione ed attende che qualche client richieda la connessione;
- #Client: il programma conosce l'indirizzo della macchina su cui è in esecuzione il server ed il suo numero di porta. Il client deve anche comunicare al server il numero di porta locale sulla quale riceverà i dati.

Il procedimento di connessione prevede che il server debba *accettare* la connessione e che assegni un nuovo #socket per la comunicazione bidirezionale tra client e server.
Così facendo, il #Server può comunque accettare connessioni da altri #Client,  ma può anche lanciare #thread per ogni #socket stabilito, in modo da permettere la gestione concorrente delle comunicazioni con tutti i client.
Queste funzionalità sono offerte dal package *java.net* attraverso due classi:
- *ServerSocket*: implementa un #socket di connessione che attende richieste da parte di #Client. Quando ne riceve una, assegna un #socket alla connessione bidirezionale, restituendo l'oggetto #socket che viene utilizzato per la connessione;
- *Socket*.

La comunicazione tra #Client e #Server avviene attraverso la scrittura e la lettura di #stream associati con il #socket e che permettono una facile interazione per poter trasmettere istanze di classi Java attraverso il meccanismo di #serializzazione. Gli stream I/O sono utili per trattare una sequenza di dati "diretta a"/"proveniente da" diverse entità, quali file, memoria, socket, ecc.
Gli stream sono presenti nel package *java.io*, e le classi da esso fornite sono:
- *InputStream*, di cui la sottoclasse più importante è *ObjectInputStream poiché essa fornisce il meccanismo di deserializzazione quando riceve un oggetto serializzato da ObjectOutputStream;
- *OutputStream*, di cui la sottoclasse più importante è *ObjectOutputStream*.
- 
Gli oggetti che possono essere trasmessi sugli #stream devono implementare l'interfaccia **serializable** o **Externalizable**, mentre i tipi primitivi possono essere letti tramite i seguenti metodi:
- *readByte()*;
- *readFloat()*.

Gli #stream vengono creati attraverso il meccanismo di #wrapping, ovvero ogni classe via via più specializzata prende come argomento per il costruttore un'istanza delle classi più alte nella gerarchia. 
```Java
ObjectInputStream inStream = new ObjectInputStream(Socket.getInputStream());
```

A questo scopo, tra le classi derivanti dalla classe *Reader* esiste la classe *InputStreamReader* che rappresenta la connessione tra gli stream binari e quelli di testo.
Un'altra classe utile è la classe *BufferedReader* che fornisce una bufferizzazione di uno #stream di input di testo allo scopo di aumentare l'efficienza.
```Java
BufferedReader bin = new BufferedReader (new InputStreamReader(System.in));
```

La sequenza di istruzioni classicamente usate per accedere agli #stream di un #socket lato #Server è:
```Java
ServerSocket serverSocket = new ServerSocket(9000);
socket = serverSocket.accept();
System.out.println("Accettata una connessione... attendo comandi");
ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
```

E' importante distinguere quando #Client e #Server sono usati nell'ambito di architetture distribuite e quando si descrive invece il ruolo di oggetti distribuiti; in quest'ultimo caso, infatti, ci si riferisce ad una singola chiamata di metodo: l'oggetto server rappresenta l'oggetto che riceve l'invocazione che viene effettuata dall'oggetto client. 
Il ruolo può invertirsi qualora il server diventi client per un'invocazione di un metodo su un altro oggetto remoto. 

Per rendere #distribuito un semplice programma, adottiamo il principio dell'astrazione, introducendo lo strato di trasparenza comprendente #Stub e #Skeleton.

- #Stub: oggetto situato sul #Client, rappresentante l'oggetto server in locale. Presenta ed espone gli stessi metodi che vengono esposti sul server. Il suo compito principale è comunicare con lo #Skeleton. Ogni chiamata del client verso i metodi remoti dello #Stub genera una comunicazione tra esso e lo #Skeleton.
- #Skeleton: oggetto situato sul #Server, è incaricato di:
	1. effettuare l'invocazione del metodo richiesto sull'oggetto server;
	2. ricevere il valore restituito dal metodo;
	3. comunicare il suddetto valore allo #Stub, che lo restituisce verso il client.

Ogni comunicazione fra stub e skeleton avviene attraverso un protocollo comune che deve prevedere come si indica il metodo da eseguire e come si inviano i parametri ed il valore restituito.
Sia lo #Stub che lo #Skeleton implementano un'interfaccia comune, detta **interfaccia remota**, dove sono definiti i metodi che devono essere invocati in remoto.
Ogni metodo, essendo remoto, viene dichiarato tale da poter lanciare eccezioni.
Per reperire il riferimento all'oggetto remoto da parte del/dei client, è possibile avere un servizio disponibile, la cui locazione è conosciuta, che permetta di reperire l'indirizzo dell'oggetto di cui sappiamo solamente l'identificativo.


Per un #socket si deve sempre aprire prima lo stream di output poiché, in caso si apra prima quello di input, l'applicazione rimarrà bloccata in attesa di leggere qualcosa nell'header che non ci sarà, poiché gli stream di output non hanno ancora scritto.