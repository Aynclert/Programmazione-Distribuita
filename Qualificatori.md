
I qualificatori sono annotazioni Java che portano ad una [[Injection]] typesafe e disambiguano un tipo senza dover ricorrere a nomi basati su String.
Un qualificatore, in breve, è un'annotazione definita dall'utente, a sua volta annotata con @Qualifier.
Una volta definiti i qualificatori necessari, questi devono essere applicati all'implementazione appropriata.
Questi qualificatori vengono quindi applicati ai punti di iniezione per distinguere quale implementazione è richiesta dal cliente.

Un modo per evitare la moltiplicazione delle annotazioni è l'utilizzo dei membri, ovvero annotazioni con valori interni, come nell'esempio sottostante:

```Java
@Qualifier
@Retention(RUNTIME)
@Target({FIELD, TYPE, METHOD})
public @interface NumberOfDigits {
	Digits value();
	boolean odd();
}

public enum Digits {
	TWO,
	EIGHT,
	TEN,
	THIRTEEN
}
```

Il modo in cui si usano questi qualificatori con membri non cambia:
```Java
@Inject @NumerOfDigits(value = Digits.THIRTEEN, odd = false)
//istanza da iniettare
```

E' possibile l'utilizzo di qualificatori multipli per risolvere ambiguità di typesafe, a costo di più codice da scrivere.


Esistono anche dei qualificatori speciali chiamati #alternative, ovvero bean con qualificatore @Alternative.
Le #alternative sono di base disabilitiate e devono essere abilitate nel descrittore beans.xml per renderle disponibili per istanziazione e iniezione