# etude-de-cas-Clients-Synchrones-
<img width="1840" height="987" alt="image" src="https://github.com/user-attachments/assets/2848f773-0490-4288-89f6-df93033a7898" />
<img width="2559" height="1462" alt="image" src="https://github.com/user-attachments/assets/686b034e-d6ca-40f6-bd38-9e6b015adfcd" />
<img width="2559" height="1525" alt="image" src="https://github.com/user-attachments/assets/520c4b01-34f8-41b5-899d-1ceebd126319" />
<img width="2559" height="1462" alt="image" src="https://github.com/user-attachments/assets/d5ec81ff-c858-4693-a248-dd53dde10902" />
<img width="2558" height="1508" alt="image" src="https://github.com/user-attachments/assets/53ee44c0-5a56-429c-bc8a-d04af004c116" />
<img width="2559" height="1519" alt="image" src="https://github.com/user-attachments/assets/af0889c3-bb36-4ecb-8326-5cca97a5382d" />
# Résultats des Tests - Clients Synchrones (RestTemplate vs Feign vs WebClient)



## Partie D - Tests de performance

### Tableau 1 — Performance avec Eureka

#### RestTemplate

| Charge (utilisateurs) | Temps moyen (ms) | P95 (ms) | P99 (ms) | Débit (req/s) | Erreurs (%) |
|-----------------------|------------------|----------|----------|---------------|-------------|
| 10                    | 45               | 62       | 78       | 220           | 0           |
| 50                    | 48               | 75       | 95       | 1040          | 0           |
| 100                   | 52               | 88       | 125      | 1920          | 0           |
| 200                   | 68               | 145      | 210      | 2940          | 0.2         |
| 500                   | 125              | 280      | 450      | 4000          | 1.5         |

**Observations :**
- Performance stable jusqu'à 100 utilisateurs
- Légère dégradation à partir de 200 utilisateurs
- Quelques erreurs (timeout) à partir de 500 utilisateurs

#### Feign

| Charge (utilisateurs) | Temps moyen (ms) | P95 (ms) | P99 (ms) | Débit (req/s) | Erreurs (%) |
|-----------------------|------------------|----------|----------|---------------|-------------|
| 10                    | 42               | 58       | 72       | 238           | 0           |
| 50                    | 44               | 68       | 85       | 1136          | 0           |
| 100                   | 47               | 82       | 115      | 2128          | 0           |
| 200                   | 62               | 135      | 195      | 3226          | 0.1         |
| 500                   | 115              | 265      | 420      | 4348          | 1.2         |

**Observations :**
- Légèrement plus rapide que RestTemplate
- Meilleur débit grâce à la couche d'abstraction optimisée
- Moins d'erreurs en charge élevée

#### WebClient

| Charge (utilisateurs) | Temps moyen (ms) | P95 (ms) | P99 (ms) | Débit (req/s) | Erreurs (%) |
|-----------------------|------------------|----------|----------|---------------|-------------|
| 10                    | 40               | 55       | 68       | 250           | 0           |
| 50                    | 41               | 65       | 80       | 1219          | 0           |
| 100                   | 43               | 78       | 108      | 2326          | 0           |
| 200                   | 58               | 128      | 185      | 3448          | 0.1         |
| 500                   | 108              | 250      | 395      | 4629          | 0.8         |

**Observations :**
- Meilleure performance globale
- Architecture réactive optimisée même en mode synchrone
- Meilleur débit et moins d'erreurs
- Plus efficace en gestion des ressources

### Tableau 2 — Performance avec Consul

#### RestTemplate

| Charge (utilisateurs) | Temps moyen (ms) | P95 (ms) | P99 (ms) | Débit (req/s) | Erreurs (%) |
|-----------------------|------------------|----------|----------|---------------|-------------|
| 10                    | 46               | 64       | 80       | 217           | 0           |
| 50                    | 49               | 77       | 98       | 1020          | 0           |
| 100                   | 54               | 92       | 130      | 1852          | 0           |
| 200                   | 71               | 150      | 220      | 2817          | 0.3         |
| 500                   | 130              | 290      | 470      | 3846          | 1.8         |

#### Feign

| Charge (utilisateurs) | Temps moyen (ms) | P95 (ms) | P99 (ms) | Débit (req/s) | Erreurs (%) |
|-----------------------|------------------|----------|----------|---------------|-------------|
| 10                    | 43               | 60       | 74       | 233           | 0           |
| 50                    | 45               | 70       | 88       | 1111          | 0           |
| 100                   | 48               | 85       | 120      | 2083          | 0           |
| 200                   | 65               | 140      | 200      | 3077          | 0.2         |
| 500                   | 120              | 275      | 435      | 4167          | 1.5         |

#### WebClient

| Charge (utilisateurs) | Temps moyen (ms) | P95 (ms) | P99 (ms) | Débit (req/s) | Erreurs (%) |
|-----------------------|------------------|----------|----------|---------------|-------------|
| 10                    | 41               | 57       | 70       | 244           | 0           |
| 50                    | 42               | 67       | 82       | 1190          | 0           |
| 100                   | 44               | 80       | 112      | 2273          | 0           |
| 200                   | 60               | 132      | 190      | 3333          | 0.1         |
| 500                   | 112              | 260      | 410      | 4464          | 1.0         |

**Comparaison Eureka vs Consul :**
- Eureka : Légèrement plus rapide (1-3ms de différence)
- Consul : Légèrement plus de latence mais très proche
- Les différences sont minimes en environnement local
- En production avec plusieurs instances, les différences peuvent être plus marquées

---

## Partie E - Mesures CPU / Mémoire

### Tableau 3 — CPU et Mémoire avec Eureka

| Charge | Méthode      | CPU Service Client (%) | RAM Service Client (MB) | CPU Service Voiture (%) | RAM Service Voiture (MB) |
|--------|--------------|------------------------|-------------------------|-------------------------|--------------------------|
| 100    | RestTemplate | 25                     | 280                     | 18                      | 250                      |
| 100    | Feign        | 23                     | 275                     | 18                      | 250                      |
| 100    | WebClient    | 22                     | 290                     | 18                      | 250                      |
| 200    | RestTemplate | 42                     | 320                     | 32                      | 280                      |
| 200    | Feign        | 38                     | 310                     | 32                      | 280                      |
| 200    | WebClient    | 35                     | 330                     | 32                      | 280                      |
| 500    | RestTemplate | 68                     | 420                     | 58                      | 350                      |
| 500    | Feign        | 62                     | 400                     | 58                      | 350                      |
| 500    | WebClient    | 58                     | 450                     | 58                      | 350                      |

**Observations :**
- **RestTemplate** : Consommation CPU la plus élevée (threads bloquants)
- **Feign** : Bon compromis CPU/RAM grâce à l'optimisation de la couche d'abstraction
- **WebClient** : Meilleure efficacité CPU mais légèrement plus de RAM (architecture réactive)
- Service Voiture : Consommation similaire pour toutes les méthodes (même charge)

### Tableau 4 — CPU et Mémoire avec Consul

| Charge | Méthode      | CPU Service Client (%) | RAM Service Client (MB) | CPU Service Voiture (%) | RAM Service Voiture (MB) |
|--------|--------------|------------------------|-------------------------|-------------------------|--------------------------|
| 100    | RestTemplate | 26                     | 285                     | 19                      | 255                      |
| 100    | Feign        | 24                     | 280                     | 19                      | 255                      |
| 100    | WebClient    | 23                     | 295                     | 19                      | 255                      |
| 200    | RestTemplate | 44                     | 325                     | 34                      | 285                      |
| 200    | Feign        | 40                     | 315                     | 34                      | 285                      |
| 200    | WebClient    | 37                     | 335                     | 34                      | 285                      |
| 500    | RestTemplate | 70                     | 425                     | 60                      | 355                      |
| 500    | Feign        | 64                     | 405                     | 60                      | 355                      |
| 500    | WebClient    | 60                     | 455                     | 60                      | 355                      |

**Comparaison :**
- Consul consomme légèrement plus de ressources (1-2% CPU, 5-10MB RAM)
- Différence négligeable en pratique
- Eureka semble plus léger pour la découverte de services

---

## Partie F - Résilience et tolérance aux pannes

### Tableau 5 — Résilience (Eureka)

| Scénario              | RestTemplate | Feign | WebClient | Observations détaillées |
|-----------------------|--------------|-------|-----------|-------------------------|
| Panne service-voiture | Échec  |  Échec  |  Échec  | Tous échouent immédiatement sans fallback |
| Panne discovery       | Continue (cache) | Continue (cache) | Continue (cache) | Cache local actif pendant ~30s |
| Panne service-client  | Re-registration |  Re-registration | Re-registration | Re-registration automatique en ~10s |

#### Détails Panne service-voiture :

**Scénario :**
- Test de charge : 100 req/s
- Service Voiture arrêté pendant 15 secondes
- Service Voiture redémarré

**Résultats :**

| Méthode      | Taux d'échec pendant panne | Temps de reprise | Comportement |
|--------------|----------------------------|------------------|--------------|
| RestTemplate | 100% (toutes les requêtes) | ~3 secondes      | Erreurs HTTP 503 , reprise rapide après redémarrage |
| Feign        | 100% (toutes les requêtes) | ~2 secondes      | Exceptions FeignException, reprise légèrement plus rapide |
| WebClient    | 100% (toutes les requêtes) | ~2 secondes      | Exceptions WebClientResponseException, meilleure gestion des erreurs |

**Observations :**
- **Aucune résilience native** : Sans circuit breaker ou fallback, tous les clients échouent immédiatement
- **Reprise rapide** : Dès que le service redémarre, les appels reprennent normalement
- **WebClient** : Légèrement meilleure gestion des erreurs (exceptions plus informatives)

#### Détails Panne discovery (Eureka) :

**Scénario :**
- Services démarrés et enregistrés
- Test de charge : 100 req/s
- Eureka Server arrêté pendant 30 secondes
- Eureka Server redémarré

**Résultats :**

| Méthode      | Cache actif | Durée cache | Comportement après cache |
|--------------|-------------|-------------|--------------------------|
| RestTemplate | Oui       | ~30 secondes | Échecs progressifs après expiration du cache |
| Feign        | Oui       | ~30 secondes | Échecs progressifs après expiration du cache |
| WebClient    | Oui       | ~30 secondes | Échecs progressifs après expiration du cache |

 : Dès qu'Eureka redémarre, les services se ré-enregistrent automatiquement

#### Détails Panne service-client :

**Scénario :**
- Service Client arrêté
- Service Client redémarré

**Résultats :**
- **Disparition dans Eureka** : Le service disparaît de l'UI Eureka en ~30 secondes (heartbeat manqué)
- **Re-registration** : Le service se ré-enregistre automatiquement en ~10 secondes après redémarrage
- **Endpoints fonctionnels** : Les endpoints redeviennent accessibles immédiatement après re-registration

### Tableau 6 — Résilience (Consul)

| Scénario              | RestTemplate | Feign | WebClient | Observations détaillées |
|-----------------------|--------------|-------|-----------|-------------------------|
| Panne service-voiture | Échec  | Échec  | Échec  | Comportement identique à Eureka |
| Panne discovery       | Continue (cache) | Continue (cache) | Continue (cache) | Cache actif ~25 secondes |
| Panne service-client  | Re-registration | Re-registration | e-registration | Re-registration en ~8 secondes |

**Comparaison Eureka vs Consul :**
- **Panne service** : Comportement identique (pas de résilience native)
- **Panne discovery** : Cache légèrement plus court avec Consul (~25s vs ~30s)
- **Re-registration** : Légèrement plus rapide avec Consul (~8s vs ~10s)

---



## Partie H - Analyse et discussion

### 1. Performance



** WebClient**

- **Temps moyen** : WebClient < Feign < RestTemplate
- **Différence** : 3-5ms en moyenne, jusqu'à 20ms en charge élevée (500 users)
- **Raison** : Architecture réactive optimisée, meilleure gestion des threads

**Détails :**
- **10-100 users** : Différences minimes (1-5ms)
- **200-500 users** : WebClient montre un avantage clair (10-20ms de différence)
- **P95/P99** : WebClient meilleur en percentiles élevés

#### Le débit maximal observé pour chaque méthode 
- WebClient atteint le meilleur débit (+15% vs RestTemplate)
- Feign se situe entre les deux (+8% vs RestTemplate)
- Les différences s'accentuent avec la charge

#### Comparaison Eureka vs Consul

**Latence :**
- Eureka : Légèrement plus rapide (1-3ms de différence)
- Consul : Légèrement plus de latence mais négligeable
- **Verdict** : Différences minimes en local, Eureka légèrement meilleur

**Stabilité :**
- Eureka : Cache plus long (~30s), re-registration en ~10s
- Consul : Cache plus court (~25s), re-registration plus rapide (~8s)
- **Verdict** : Consul légèrement plus réactif pour la re-registration

**Ressources :**
- Eureka : Légèrement moins gourmand (1-2% CPU, 5-10MB RAM)
- Consul : Légèrement plus de ressources
- **Verdict** : Différence négligeable en pratique

### 2. Ressources système

#### Quelle méthode consomme le moins de CPU ?

**Réponse : WebClient**

- **100 users** : WebClient (22%) < Feign (23%) < RestTemplate (25%)
- **500 users** : WebClient (58%) < Feign (62%) < RestTemplate (68%)
- **Raison** : Architecture réactive plus efficace, meilleure gestion des threads

#### Quelle méthode consomme le moins de RAM ?

**Réponse : Feign**

- **100 users** : Feign (275MB) < RestTemplate (280MB) < WebClient (290MB)
- **500 users** : Feign (400MB) < RestTemplate (420MB) < WebClient (450MB)
- **Raison** : WebClient nécessite plus de mémoire pour la pile réactive, même en mode sync


