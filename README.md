#AVVIO APPLICAZIONE
Per avviare l'applicazione, eseguire il comando `docker compose up -d` nella directory principale del progetto. Questo comando avvierà il container Docker contenente l'applicazione e il db PostgreSQL. Una volta avviato il container, l'applicazione sarà accessibile all'indirizzo `http://localhost:8080`.

#DOCUMENTAZIONE API
La documentazione delle API è disponibile all'indirizzo `http://localhost:8080/swagger-ui.html`. Qui è possibile visualizzare tutte le API disponibili, i parametri richiesti e le risposte attese. La documentazione è generata automaticamente utilizzando OpenAPI e Swagger.

#CONFIGURAZIONE DATABASE
L'applicazione utilizza un database PostgreSQL. La configurazione del database è gestita tramite il file `application.properties` presente nella directory `src/main/resources`.
Le proprietà principali da configurare sono:
- `spring.datasource.url`: `jdbc:postgresql://localhost:5432/nome_database`.
- `spring.datasource.username`: `admin`
- `spring.datasource.password`: `password`# esercitazione1
