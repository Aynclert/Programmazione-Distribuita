
L'iniezione delle dipendenze CDI è la capacità di iniettare i bean in altri senza l'utilizzo di XML, ma attraverso le annotazioni.
L'iniezione esisteva già in Java EE 5 con le annotazioni @Resource, @PersistentUnit o @EJB

Con il [[Context and Dependency Injection (CDI)]] si può iniettare praticamente ovunque grazie all'annotazione #Inject, ed il punto in cui si trova la suddetta annotazione è chiamato **punto di iniezione**.

L'iniezione può avvenire tramite tre diversi meccanismi:
- proprietà: iniezione di un attributo;
- costruttore: iniezione di un costruttore di classe;
- setter: iniezione di un metodo *set* di classe.

Se vi è una singola implementazione di una classe da iniettare, è possibile specificare la sua iniezione tramite l'annotazione #Inject @Default.