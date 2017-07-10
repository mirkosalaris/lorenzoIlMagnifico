# LorenzoIlMagnifico
> *Nel gruppo è presente un laureando*

## File importanti
- Server: `src/main/java/it/polimi/ingsw/GC_36/server/Server.java`
- Client: `src/main/java/it/polimi/ingsw/GC_36/client/Main.java`
- Immagini diagrammi UML: `uml/images/`

## Come giocare
Avviare il Server, file sopraindicato.  

>_Il Server deve essere lanciato una sola volta, supporta comunque più partite 
contemporaneamente._

Avviare il Client, file sopra indicato.  
Seguendo le istruzioni a video, scegliere se giocare in CLI o in GUI e con utilizzando Socket o RMI (la scelta della comunicazione è completamente trasparente al gioco).  
Dopo aver raggiunto un numero minimo di utenti (2 utenti) la partita inizierà dopo un determinato intervallo di tempo (configurato da file, 30s).  
**Attenzione**: sia da CLI che da GUI viene mostrata una scritta che indica il tempo rimanente. **Quella scritta viene aggiornata una volta sola**, quando il client si connette, poi rimane ferma e a un certo punto il gioco inizia.   
Due giocatori possono giocare insieme solo se scelgono la stessa modalità di gioco (Normale o Avanzata).

>NB: su grafica la funzionalità avanzata **non è supportata** e parte dunque in 
modalità Normale in modo automatico.

L'azione viene compilata completamente sul client e solo successivamente inviata al server. Gli errori vengono 
controllati alla fine e in caso di errore il giocatore dovrà ricompilare l'azione.

>Lato Client è implementato un checking di base per migliorare l'usabilità, ma il checking completo è realizzato lato server. 

## Istruzioni di base
Sia da CLI che da GUI l'utente è guidato nelle sue scelte e non sono necessarie quindi istruzioni dettagliate.
> **Nella GUI le istruzioni vengono mostrate in una label bianca**, circa al centro della Board, sotto la zona del consiglio, sopra al mercato.

Ci sono alcuni momenti del gioco o azioni particolari che richiedono una spiegazione, indicata quindi qui di seguto.

### CLI:  
##### Modalità Avanzata
La scelta della BonusTile è sequenziale. Il secondo giocatore potrà 
scegliere solo dopo aver atteso la scelta del primo giocatore. E così via
 per gli altri giocatori. 
 
>_NB: da CLI il gioco sembra bloccato ma si sta semplicemente attendendo 
la scelta degli altri giocatori_

Per quanto riguarda la distribuzione delle carte Leader la scelta viene 
effettuata a turni **in contemporanea**: tutti scelgono la loro prima carta, 
quando tutti hanno scelta tutti possono scegliere la seconda carta. E così via.
    
##### ID degli spazi azione
Da CLI, quando richiesto, bisogna inserire manualmente l'id dello spazio azione. Qui sotto la corrispondenza tra id e Spazi Azione.
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
  
### GUI
Scegliere se giocare con Socket o RMI mediante gli appositi bottoni.
Attendere che si connetta almeno un altro giocatore.  

I familiari sono i 4 bottoni colorati sulla destra. Il familiare neutro ha il colore 
rosa. 
Le carte vengono ingrandite con il passaggio del mouse.  
L'utilizzo della GUI è ritenuto intuitivo e non necessita di ulteriori spiegazioni.

## Elementi caricati da file (commons.json):
- Carte Sviluppo
- Carte Leader
- Tessere Bonus Personali (bonus Tile)
- Plancia Personale (personal Board)
- Privilegi del concilio
- Tassa d'ingresso alle Torri
- Timer di attesa per l'inzio della partita
- Timer di attesa per compilare una mossa
- Spazi Azione

## Elementi di gioco non implementati
- Rapporto in vaticano (malus scomunica e tessere scomunica)
- Effetti permanenti per le carte blu
- Carte Leader implementate: 9/16, 
  per raggiungere il numero minimo di carte Leader necessarie per la 
  distribuzione iniziale ne sono state duplicate alcune (è stato cambiato il 
  nome per facilitare la giocabilità)






