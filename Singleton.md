
Il Singleton restringe l'istanziazione di una classe ad un singolo oggetto, e viene usato per la memorizzazione di stato.
Sfrutta la Lazy allocation, ovvero l'allocazione avviene solo quando utilizzato per la prima volta.

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

In caso vi siano  più thread, il loro interleaving può causare errori, quindi più singleton.
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

