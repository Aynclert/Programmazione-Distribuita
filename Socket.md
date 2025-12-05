
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

