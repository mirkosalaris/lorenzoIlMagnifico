* Period non è più un enumeratore. Questo permette di rendere più flessibile
	il codice. È ora una classe che accetta nel costruttore un numero 
	incrementale che ne indica il periodo, salvato nell'attributo periodNumber
* TurnExecutor ora è statico, in questo modo non abbiamo istanze multiple ma il
	player gli viene passato come parametro
* Aggiunto TowerActionHandler, che eredita da actionHandler e reimplementa ogni
	metodo semplicemente chiamando super.method() e facendo altre elaborazioni
	specifiche, relative alla presenza delle carte
* Die non è più un enumeratore. In questo modo il codice è potenzialmente più
	flessibile. Le tre istanze vengono create da board.initialize e i
	familyMember vengono associati ai dati da player, tramite costruttore
* Aggiunti un po' di getter mancanti alle classi del Model
