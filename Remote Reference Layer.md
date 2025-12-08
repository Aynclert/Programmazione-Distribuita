
Si occupa di interfacciare il [[Transport Layer]] con lo [[Stub&Skeleton layer]], fornendo e supportando la semantica dell'operazione d'invocazione di un metodo.
Oltre a *unicast* e *multicast*, vi sono anche altri due tipi di invocazioni:
- invocazioni *di oggetti attivabili*: invocazioni effettuate ad oggetti persistenti;
- invocazioni *di riconnessione*: invocazioni per connessioni alternative in caso di nessuna risposta dall'oggetto remoto originariamente contattato.

Questo layer espone verso l'alto un riferimento ad un oggetto che implementa l'interfaccia *java.rmi.server.RemoteServer* che espone il metodo **invoke()** per effettuare l'inoltro dell'invocazione, che viene chiamato dallo #Stub.
Interagisce col [[Transport Layer]] utilizzando l'astrazione di una connessione orientata ai flussi, che potrebbe utilizzare protocolli connectionless senza alterare la modalità di comunicazione dei dati.