
Nella programmazione ci sono due approcci:
- **Programmazione imperativa**: specifica l'algoritmo per raggiungere un obiettivo.
- **Programmazione dichiarativa**: specifica come raggiungere questo obiettivo.

La programmazione dichiarativa viene eseguita utilizzando i #metadati, ovvero annotazioni o/e descrittori di distribuzione. I componenti vengono eseguiti in un contenitore e questo contenitore fornisce al componente un insieme di servizi.
I metadati vengono utilizzati per dichiarare e personalizzare questi servizi e associano informazioni aggiuntive insieme a classi, interfacce, costruttori, metodi, campi o parametri Java.

```java
@Stateless
@Remote(ItemRemote.class)
@Local(ItemLocal.class)
@LocalBean
public class ItemEJB implements ItemLocal, ItemRemote {
	@PersistenceContext(unitName = "chapter01PU")
	private EntityManager em;
	public Book findBookById(Long id) {
		return em.find(Book.class, id);
	}
}
```

L'altro modo di dichiarare i metadati è usando i descrittori di implementazione. Un descrittore di implementazione (DD) fa riferimento a un file di configurazione #XML distribuito con il componente nei #contenitori . 
I descrittori di implementazione devono essere impacchettati con i componenti nella directory speciale `META-INF` o `WEB-INF` da prendere in considerazione.

**Se si utilizzano entrambi, i metadati vengono sovrascritti dal descrittore di implementazione**.

Il più grande vantaggio delle annotazioni è che riducono significativamente la quantità di codice che uno sviluppatore deve scrivere e, utilizzandole è possibile evitare i descrittori di implementazione. Tuttavia, i descrittori di implementazione sono file XML esterni che possono essere modificati senza richiedere modifiche al codice sorgente e alla ricompilazione.

