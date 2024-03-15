[![Typing SVG](https://readme-typing-svg.herokuapp.com/?color=dcebfd&size=35&center=true&vCenter=true&width=1000&lines=GottaBattleEmAllGA)](https://git.io/typing-svg)
---

## Prerequisiti
- JDK versione 17
- Apache Maven
- Un ambiente di sviluppo (IDE) 

## Installazione
- Entra nella directory GottaBattleEmAllGA 

```
cd GottaBattleEmAllGA
```

- Compila il progetto con Maven:
```
mvn package
```

- utilizzare il proprio IDE per il run

## Utilizzo
Dopo aver fatto il run dell'App.java verrà presentato un mini applicazione 

- All'avvio, mostra un menu con sette opzioni.
- L'utente può selezionare un'opzione digitando il numero corrispondente e premendo Invio.
- Se l'utente seleziona "Inserisci pokemon team 1" (opzione 1) o "Inserisci pokemon team 2" (opzione 2), l'applicazione chiederà all'utente di inserire un Pokémon nel team 1 o nel team 2 rispettivamente.
- Se l'utente seleziona "Guarda i pokemon disponibili" (opzione 3), l'applicazione mostrerà i Pokémon disponibili.
- Se l'utente seleziona "Genera un team casuale" (opzione 4), l'applicazione genererà casualmente un team per entrambi i team.
- Se l'utente seleziona "Predici il vincitore fra i due team" (opzione 5), l'applicazione stamperà la percentuale di vittoria prevista relativa al primo team rispetto al secondo e terminerà l'esecuzione.
- Se l'utente seleziona "Cancella Team" (opzione 6), l'applicazione cancellerà entrambi i team.
- Se l'utente seleziona "Esci" (opzione 7), l'applicazione terminerà l'esecuzione.


Invece se si vuole avviare l'algoritmo genetico, bisogna avviare il file SaveGA il quale penserà a salvare i dati generati dall'algoritmo nel file "squadra.dat".
