
L’accesso in memoria con i multi core è molto complicato, si possono avere diversi problemi:
- legati alla sincronia: i diversi core potrebbero essere asincroni tra di loro;
- ritardi impercettibili: Cache misses (breve), Page faults (lungo), Context-switch dallo scheduler (molto lungo).

(Page fault: un'eccezione di tipo trap, generata quando un processo cerca di accedere ad una pagina che é presente nel suo spazio di indirizzamento virtuale, ma che non è presente nella memoria fisica;
Context-switch: la context switch è una particolare operazione del sistema operativo che cambia il processo correntemente in esecuzione su una CPU. Questo avviene all'occorrenza di una qualsiasi interruzione dovuta allo scheduler, ma anche a interruzioni dovute a errori di altri processi o segnali; viene effettuato per salvare tutte le informazioni necessarie al riavvio successivo del processo)

La programmazione distribuita implica la conoscenza (di base) della programmazione concorrente, di cui vi sono 3 tipi:
1. programmazione concorrente eseguita su calcolatori diversi;
2. processi concorrenti sulla stessa macchina (multitasking): processo padre che genera processi figli per fork ();
3. programmazione concorrente nello stesso proceora ci sono sso: “processi lightweight” all’interno del processo, chiamati #thread.

Il multithread è l’estensione del multitask riferito ad un singolo programma, è in grado di
eseguire più thread “contemporaneamente”.
I #thread, a differenza dei processi, hanno a disposizione e condividono gli stessi dati (trovandosi all’interno dello stesso processo).

[[Thread in Java]]

I thread possono raggiungere degli stati di blocco, ovvero:
- **Deadlock**: due thread sono bloccati, ognuno in attesa dell'altro;
- **Starvation**: un thread non è in grado di ottenere accesso regolare alle risorse condivise e non riesce ad avanzare;
- **Livelock**: un primo thread risponde all'azione di un secondo thread, il quale risponde all'azione del primo. I due thread non sono bloccati, ma non c'è progresso.

