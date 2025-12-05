
Il Singleton restringe l'istanziazione di una classe ad un singolo oggetto, e viene usato per la memorizzazione di stato.
Sfrutta la *Lazy allocation*, ovvero l'allocazione avviene solo quando utilizzato per la prima volta.

```Java
public class Singleton
{
	private static Singleton instance; 
	// variabile di classe che mantiene l’istanza
	
	private Singleton() {
	// costruttore privato
	// ...
	}
	
	public static Singleton getInstance()
	// metodo di classe che restituisce un Singleton
	{
		if (instance == null) {
		 // se non è stato ancora creata un’istanza
			instance = new Singleton(); // allora la crea
		}
		return instance;
		 // restituisci la nuova istanza a disposizione
	}
}
```

In caso vi siano più #thread, il loro interleaving può causare errori, quindi più singleton.
Per ovviare a questo problema vi sono stati applicati alcuni concetti della [[Sincronizzazione]], purtroppo a scapito delle performance.

```Java
public class Singleton
{
	private static Singleton instance; 
	// variabile di classe che mantiene l’istanza
	
	private Singleton() {
	// costruttore privato
	// ...
	}
	public static synchronized Singleton getInstance() 
	// metodo sincronizzato
	{
		if (instance == null) {
		// se non è stato ancora creata un’istanza
			instance = new Singleton(); // allora la crea
		}
		return instance;
		// restituisci la nuova istanza a disposizione
	}
}
```

In questo caso la sincronizzazione serve solo la prima volta, anche se l'overhead di ottenere e rilasciare il #lock ad ogni richiamo del metodo sembra non necessario.

Un altro tentativo risolutivo è il *double-checked locking*:

```Java
public static Singleton getInstance() 
{
	if (instance == null) // se non esiste l’istanza 
	{
		synchronized(Singleton.class) //si entra in CS 
		{
			if (instance == null) 
			// si controlla che nel waiting non sia cambiata la situazione 
				instance = new Singleton(); // se è il caso crea un nuovo Singleton
		}
	return instance;
```

Anche questo metodo, però, non è garantito che funzioni.

Una nuova soluzione sarebbe rendere *volatile* la variabile instance, anche se funziona da Java 5 in poi.

In alternativa, si possono usare le classi statiche con l'idioma "Initialization-on-demand holder":
```Java
public class Something {  
    private Something() {}  
  
    private static class LazyHolder {  
        private static final Something INSTANCE = new Something();  
    }  
  
    public static Something getInstance() {  
        return LazyHolder.INSTANCE;  
    }  
}
```

LazyHolder è inizializzata dalla JVM solo quando serve (alla prima getInstance()). Essendo un
inizializzatore statico, viene eseguito una sola volta ( al caricamento) e stabilisce una relazione
#happens-before su tutte le altre operazioni sulla classe.