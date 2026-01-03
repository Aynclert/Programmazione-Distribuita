
La Decoration è utilizzata per separare le preoccupazioni legate al business, ed è attuata attraverso i #Decorator. 
I decorators sono un modello di design comune della "Gang of Four". L'idea è di prendere una classe e avvolgere un'altra classe intorno ad essa. In questo modo, quando chiami una classe decorata, passi sempre attraverso il #Decorator circostante prima di raggiungere la classe target.
I decorators hanno lo scopo di aggiungere ulteriore logica ad un metodo di business.
#Interceptor e #Decorator sono simili e complementari.

L'annotazione principale è @Decorator, mentre quella per l'iniezione è @Delegate.

I decorators, come le alternative e gli interceptors, devono essere definiti nel file beans.xml.