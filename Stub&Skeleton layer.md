
Si occupa di essere l'interfaccia tra l'applicazione ed il resto del sistema. Consiste nel fornire uno #stream di marshall di oggetti Java che vengono passati al [[Remote Reference Layer]] per copia.

Lo #Stub è incaricato di:
- iniziare la connessione con la macchina virtuale remota, chiamando il [[Remote Reference Layer]];
- effettuare il #marshalling verso uno #stream di marshall, fornito dal [[Remote Reference Layer]];
- attendere il risultato dell'invocazione;
- effettuare l'unmarshalling dei valori restituiti;
- restituire il valore verso l'oggetto #Client che ha richiesto l'invocazione.

Lo #Skeleton è incaricato di effettuare il dispatching verso l'oggetto remoto. Quando uno #Skeleton riceve un'invocazione in entrata, si occupa di:
- effettuare l'unmarshalling dal [[Remote Reference Layer]] (lato #Server) dei parametri per l'invocazione;
- invocare il metodo sull'implementazione che si trova nella sua JVM;
- effettuare il #marshalling del valore restituito verso chi ha invocato il metodo.

#Stub e #Skeleton vengono creati dall'RMI rmic, tool che, a partire da una classe compilata che rappresenta l'implementazione di un oggetto remoto, genera i file #Stub e #Skeleton.