**Legge di Amdahl**: lo speedup che si ottiene eseguendo il programma X su `n` processori, dove `p` è la parte che si può parallelizzare è: `S = 1 / (1 - p + (p / n))`,  dove `p/n` è il tempo impiegato per la parte parallela, mentre `1 - p`  è il tempo impiegato per la parte sequenziale.

I metodi synchronized permettono di risolvere semplicemente gli errori di concorrenza al costo di inefficienza. Per rendere un metodo tale basta aggiungere `synchronized` alla sua dichiarazione:

```Java
public class SynchronizedCounter {

	private int c = 0;
	
	public synchronized void increment() {
		c++;
	}
	
	public synchronized void decrement() {
		c--;
	}
	
	public synchronized int value() {
		return c;
	}
}
```

Quando un thread esegue un metodo #sincronizzato per un oggetto, gli altri thread che invocano metodi sincronizzati dello stesso oggetto sono sospesi fino al termine del primo thread. Quando un thread esce da un metodo sincronizzato, si stabilisce una relazione #happens-before con tutte le successive invocazioni dello stesso metodo sullo stesso oggetto. I cambi allo stato effettuati dal thread appena uscito sono visibili a tutti i thread.
I costruttori non possono essere sincronizzati.

Quando si costruisce un oggetto che sarà condiviso, non si deve far scappare il riferimento prima che questo si riferisca ad un oggetto completamente costruito. Per evitare questa situazione, si utilizza un #lock intrinseco, ovvero un'entità associata ad ogni oggetto.
Un #lock intrinseco garantisce una relazione #happens-before (accesso esclusivo e consistente). Un thread deve:
1. Acquisire il lock di un oggetto;
2. Rilasciarlo quando ha terminato.
Una volta rilasciato il lock, viene stabilita la relazione #happens-before. Quando un thread esegue un metodo sincronizzato di un oggetto, ne acquisisce il lock e lo rilascia al termine dell'esecuzione (anche con un'eccezione).

E' possibile anche specificare l'oggetto di cui si usa il lock, attraverso le istruzioni sincronizzate:

```Java
public void addName(String name) {
	synchronized(this) {
		lastName = name;
		nameCount++;
	}
	nameList.add(name);
}
```

Le istruzioni sincronizzate sono utili per migliorare la concorrenza a grana fine (passaggio molto frequente tra una istruzione e l'altra).

E' possibile anche acquisire un #lock di un oggetto di tipo classe attraverso i metodi statici sincronizzati, i quali prevengono l'esecuzione interfogliata di tutti gli altri metodi statici (garantiscono accesso in mutua esclusione ad altri metodi statici sincronizzati). 

Vi sono poi azioni non interrompibili, definite **azioni atomiche**. Esse si possono specificare per:
- Read & Write su variabili di riferimento e tipi primitivi (esclusi long e double)
- Read & Write su tutte le variabili `volatile`.
Write sulle variabili `volatile` stabilisce una relazione #happens-before con le successive letture.