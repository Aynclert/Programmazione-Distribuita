git 
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


