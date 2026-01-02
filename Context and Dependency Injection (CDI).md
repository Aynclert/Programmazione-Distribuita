
L'iniezione in Java EE 7 avviene attraverso i Bean gestiti, che fungono da fondamenta comune per i diversi tipi di #componenti esistenti nella piattaforma. 
I Bean Gestiti altro non sono che oggetti gestiti da un container e che supportano un insieme di servizi base:
- risorse di Injection;
- gestione del ciclo di vita;
- Interception.

Gli oggetti #CDI sono costruiti sul modello dei Bean Gestiti. i quali:
- hanno un ciclo di vita migliorato per oggetti @Stateful;
- portano ad un approccio favorevole a [[Injection]], [[Interception]] e [[Decoration]];
- sono specializzati con annotazioni di tipo [[Qualificatori]].


Se si desidera eseguire un bean CDI all'interno di un contenitore, è necessario **iniettare** il bean, ed il contenitore fa il resto.
Quando si inietta un bean, il contenitore è il responsabile della creazione dell'istanza, risolve le dipendenze e richiama qualsiasi metodo annotato con @PostConstruct pima della prima chiamata al metodo business sul bean.
La notifica di callback @PreDestroy segnala che l'istanza è in fase di rimozione dal contenitore.

I bean CDI vivono in un ambito ben definito (request, application, conversation), e possono essere #Stateful e #Contextual.
I riferimenti iniettati ai bean sono anche consapevoli del contesto, ovvero l'intera catena delle dipendenze del bean è #Contextual.
Il contenitore gestisce automaticamente tutti i bean all'interno dell'ambito e, al termine della sessione, li distrugge automaticamente.
Diversi client di un bean #Stateful vedono il bean in stati diversi: quando un bean è #Stateful, i client in esecuzione nello stesso contesto vedranno la stessa istanza del bean. I client in contesti diversi potrebbero vedere un'istanza diversa a seconda della relazione tra i contesti.

CDI utilizza annotazioni fortemente tipizzate (Qualificatori, stereotipi e binding di #Interceptor) per collegare insieme i bean. L'utilizzo dei descrittori #XML è ridotto al minimo.

Il descrittore di implementazione di un Bean CDI si chiama *beans.xml* ed è obbligatorio. Esso può essere usato per configurare determinate funzionalità, ma è necessario per abilitare il CDI, poiché quest'ultimo ha bisogno di identificare i bean nel percorso della classe (bean discovery) per trasformare i #POJO in bean CDI.


Un bean #CDI può essere un qualsiasi tipo di classe contenente la logica aziendale. Esso può essere richiamato direttamente dal codice Java tramite [[Injection]], oppure richiamato tramite EL da una pagina #JSF.

Secondo la specifica #CDI, il contenitore tratta come un bean CDI qualsiasi classe che soddisfi le seguenti condizioni:
- non è una classe interna o statica;
- è una classe concreta, o è annotata #Decorator;
- ha un costruttore predefinito senza parametri, o dichiara un costruttore annotato #Inject.
Un bean può avere un ambito opzionale, un nome EL facoltativo, un insieme di collegamenti di #Interceptor e una gestione facoltativa del ciclo di vita.