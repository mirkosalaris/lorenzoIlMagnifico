# LorenzoIlMagnifico
##Dove si trova il:
- Server: src/main/java/it/polimi/ingsw/GC_36/server/Server.java
- Client: src/main/java/it/polimi/ingsw/GC_36/client/Main.java
- Immagini diagrammi UML: uml/images/ 

##Come giocare
Avviare il Server.
_Il Server deve essere lanciato una sola volta, supporta comunque più partite 
contemporaneamente._ 
Avviare il client. 
Scegliere se giocare da CLI o da GUI.
Scegliere se giocare mediante Socket o RMI.
La partita inizierà dopo un intervallo di tempo (configurato da file, 30s) 
successivo alla connessione di un numero minimo di utenti (2 utenti).
Due giocatori possono giocare insieme se scelgono la stessa modalità di gioco
 (Normale o Avanzata) e se si collegano al server nello stesso intervallo di tempo (30s)
*NB: su grafica la funzionalità avanzata non è supportata e parte dunque in 
modalità Normale in modo automatico.
L'azione viene compilata completamente sul client e gli errori vengono 
controllati alla fine. In caso di errore il giocatore dovrà ricompilare 
l'azione. 
Lato Client è implementato un checking di base per migliorare l'usabilità, 
ma il checking completo è realizzato lato server. 
Se l'azione non va a buon fine il giocatore deve rifarla, non viene segnalato
 esplicitamente l'errore, ma ricomincia chiedendo di selezionare il familiare.

###CLI:
- Scegliere se giocare mediante Socket o RMI. 
_(il gioco è completamente trasparente a questa scelta)_

- Scegli se giocare in modalità Normale o Avanzata. 
    
####Modalità Avanzata
La scelta della BonusTile è sequenziale. Il secondo giocatore potrà 
scegliere solo dopo aver atteso la scelta del primo giocatore. E così via
 per gli altri giocatori. 
_NB: da CLI il gioco sembra bloccato ma si sta semplicemente attendendo 
la scelta degli altri giocatori_

Per quanto riguarda la distribuzione delle carte Leader la scelta viene 
effettuata a turni in contemporanea: tutti scelgono la loro prima carta, 
quando tutti hanno scelta tutti possono scegliere la seconda carta. E così via.
    
####ID degli spazi azione 
- **Zona delle Torri**: Gli spazi azione sono numerati in ordine crescente, da 
sinistra verso destra, dal basso verso l'alto, da ID=1 ad ID=16 
- **Zona Raccolto** Lo spazio azione singolo ha ID=17, lo spazio azione 
multiplo ha ID=18
- **Zona Produzione** Lo spazio azione singolo ha ID=19, lo spazio azione 
multiplo ha ID=20
- **Zona Palazzo del Consiglio** Lo spazio azione ha ID=0
- **Zona Mercato** Gli spazi azione sono numerati da sinistra verso destra, 
da ID=21 
   ad ID=24.
  
###GUI
Scegliere se giocare con Socket o RMI mediante gli appositi bottoni.
Attendere che si connetta almeno un altro giocatore.  

I familiari sono i 4 bottoni sulla destra. Il familiare neutro ha il colore 
rosa. 
Le carte vengono ingrandite con il passaggio del mouse.  
L'utilizzo della GUI è ritenuto intuitivo e non necessita di ulteriori spiegazioni.

##Elementi caricati da file (commons.json):
- Carte Sviluppo
- Carte Leader
- Tessere Bonus Personali
- Plancia Personale
- Privilegi del concilio
- Tassa d'ingresso alle Torri
- Timer di attesa per l'inzio della partita
- Timer di attesa per compilare una mossa
- Spazi Azione

##Elementi di gioco non implementati
- Rapporto in vaticano (malus scomunica e tessere scomunica)
- Effetti permanenti per le carte blu
- Carte Leader implementate: 9/16, 
  per raggiungere il numero minimo di carte Leader necessarie per la 
  distribuzione iniziale ne sono state duplicate alcune (è stato cambiato il 
  nome per facilitare la giocabilità)
   
##Bugs




