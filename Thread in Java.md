
La cooperazione tra processi avviene attraverso l’InterProcess Communication come pipe e [[Socket]]. I #thread esistono all’interno di un processo, condividendo tra loro memoria e file aperti.
In java ogni applicazione ha almeno un thread utente (main thread), più alcuni thread di sistema che gestiscono la memoria e i segnali. Il main thread può creare e far partire diversi altri thread. 
 
 I #thread in java sono oggetti, istanze di una classe Thread. 

Come istanziare un thread in java: 
```Java
public class HelloThread extends Thread {  
    public void run() {
        System.out.println("Hello from a Thread");  
    }  
  
    public static void main(String[] args) {  
        (new HelloThread()).start();  
    }  
}
```

Estendendo la classe Thread non potremo più estendere nessun’altra classe e questa è
un’importante limitazione, per questo si preferisce usare un’interfaccia:
```Java
public class HelloRunnable implements Runnable {  
    @Override
    public void run() {
        System.out.println("Hello from a Runable Thread");  
    }  
  
    public static void main(String[] args) {  
        (new Thread(new HelloRunnable())).start();  
    }  
  
}
```

Metodi run() e start():
1. **run()**:
	1. intestazione: public void run();
	2. cosa fa: il metodo run contiene il codice che sarà eseguito dal thread.
2. **start()**:
	1. intestazione: public void start();
	2. cosa fa: fa in modo che il thread inizi l'esecuzione; la Java Virtual Machine chiama il metodo run del thread su cui si è usato start(). Il risultato sarà che due thread vengono eseguiti contemporaneamente: il main thread (che ritorna dalla chiamata al metodo start) e l'altro thread (che esegue il suo metodo run). Un thread non può essere riavviato una volta completata l'esecuzione.


Metodi utili:
1. **sleep()**: 
	1. Intestazione: public static void sleep(long millis) throws InterruptedException;
	2. Cosa fa: fa in modo che il thread attualmente in esecuzione si interrompa (interrompendo temporaneamente l'esecuzione) per il numero specificato di millisecondi, in base alla precisione e all'accuratezza dei timer di sistema e degli scheduler.
```Java
public class SleepMessages {
	public static void main(String args[]) throws InterruptedException {
		String importantInfo[]= {
					"Mares eat oats",
					"Does eat oats", 
					"Little lambs eat ivy",
					"A kid will eat ivy too"
		};
		for(i=0; i<importantInfo.lenght; i++) {
			Thread.sleep(4000);
			System.out.println(importantInfo[i]);		
		}
	}
}
```
2. **interrupt**: Un interrupt è un’indicazione che un #thread dovrebbe fermare quello che sta facendo e fare qualcos’altro. Un #thread invia un interrupt invocando Interrupt sull'oggetto #thread  che deve essere interrotto. Un thread che non invoca un metodo che lancia l’eccezione InterruptedException, può controllare se è stato interrotto utilizzando Thread.interrupted().
```Java
// ...

	for(i=0; i<importantInfo.lenght; i++) {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			return;
		}
		System.out.println(importantInfo[i]);		
	}
// ...
```
3. **join()**: A volte è necessario che un #thread attenda il completamento di un altro thread, se t è l’oggetto il cui thread è in esecuzione allora avremo t.join(), che mette il thread corrente in pausa finché il thread t non termina. Il metodo join() risponde ad un interrupt lanciando InterruptedException.
```Java
public class SimpleThreads {
	static void threadMessage(String msg){
		String tn = Thread.currentThread().getName();
		System.out.format("%s: %s%n", tn, msg);
	}
}

private static class MessageLoop implements Runnable {
	public void run(){
		String impinf[] = {
			"Mares eat oats",
			"Does eat oats", 
			"Little lambs eat ivy",
			"A kid will eat ivy too"
		};
		try{
			for(i = 0; i < impinf.lenght; i++) {
				Thread.sleep(4000);
				threadMessage(impinf[i]);		
			}
		} catch (InterruptedException e) {
			threadMessage("I wasn't done!")
		}
	}
}

private static void main (String args[]) throws InterruptedException {
	long patience = 1000*60*60;
	if(args.length > 0){
		try{
			patience = Long.parseLong(args[0])*1000;
		} 
		catch (NumberFormatException e) {
			System.err.println("Argument must be an integer.");
			System.exit(1);
		}
	}
	threadMessage("Starting MessageLoop thread");
	long startTime = System.currentTimeMillis();
	Thread t = new Thread(new MessageLoop());
	t.start();
	//...
	threadMessage("Waiting for MessageLoop to finish");
	while (t.isAlive()){
		threadMessage("Still waiting...");
		t.join(1000);
		if(((System.currentTimeMillis() - startTime) > patience) && t.isAlive()){
			threadMessage("Tired of waiting!");
			t.interrupt();
			t.join();
		}
	}
	threadMessage("Finally!");
}
```

Le fasi del ciclo di vita dei #thread:
1. **new**: fase in cui si trova il #thread quando viene creato;
2. **Runnable**: fase raggiunta  al momento della partenza del metodo run() del thread a seguito dell'invocazione di start. Un thread in questa fase può avere due stati:
	1. *ready*, se è appena entrato in questa fase o se viene sospeso dallo scheduler;
	2. running, se viene selezionato per una esecuzione;
3. **time waiting**:  il #thread passa in stato di attesa per un determinato periodo di tempo, può tornare in fase Runnable o in fase Terminated. I metodi che lo portano in questo stato sono:
	1. sleep();
	2. wait(timeout);
	3. join(timeout);
	4. LockSupport.parkNanos();
	5. LockSupport.parkUntil().
4. **waiting**: il #thread passa in stato di attesa, finché non viene sbloccato da in metodo, al suo sblocco può passare in fase Runnable o in fase Terminated. I metodi che lo portano in stato di attesa sono:
	1. wait();
	2. join();
	3. LockSupport.park().
	I metodi che lo fanno uscire dallo stato wait sono:
	4. notify();
	5. notifyAll().
5. **blocked**:  il thread entra in stato di blocco, ci entra in due casi:
	1. attende per accedere ad un blocco di istruzioni o un metodo #sincronizzato;
	2. attende per riaccedere ad un blocco di istruzioni o un metodo #sincronizzato.
	Si sblocca nel momento in ci può accedere al brocco sincronizzato, passa in fase Runnable.
6. **Terminated**: fase raggiunta alla terminazione dell'esecuzione del #thread.


I #thread comunicano principalmente condividendo accesso a campi (tipi primitivi) e campi che
contengono riferimenti a oggetti. Si possono verificare due tipi di errore:
1. **interferenza di thread**: quando il risultato di un’operazione dipende dall’ordine di esecuzione di diversi thread, infatti nell’esecuzione di due thread non sappiamo quali operazioni vengono eseguite prima. Questo è dovuto ad un problema di *race condition* (quando il risultato di un’operazione dipende dall’ordine di esecuzione di diversi thread), può creare degli Heisenbug;
2. **inconsistenza della memoria**: quando thread diversi hanno visione diverse dei dati, la soluzione è la #happens-before che ci garantisce che la memoria scritta da un thread è visibile anche agli altri thread.

Per risolvere questi problemi è necessari la [[sincronizzazione]], che a sua volta genera problemi di
contesa, infatti quando più thread cercano di accedere alla stessa risorsa simultaneamente si ha il
deadlock o il livelock.

Una maniera per stabilire delle relazioni #happens-before è la [[sincronizzazione]]. Due operazione che abbiamo descritto introducono questo tipo di relazione:
- Thread.start(): gli effeti del codice che ha condotto alla creazione sono visibili al nuovo thread.
- Thread.join(): quando la terminazione di un thread A causa il return della join() di B, tutte le istruzioni di A sono in #happens-before con le istruzioni di B che seguono la join.
Un’altra maniera è rendere la variabile **volatile**: una scrittura a un campo volatile #happens-before ogni successiva lettura della variabile (da parte di qualsiasi thread).