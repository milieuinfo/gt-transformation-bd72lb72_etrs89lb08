# Geotools transformation grid

Deze repo verpakt
het [bd72lb72_etrs89lb08.gsb](https://www.ngi.be/website/hulpmiddelen-voor-transformatie-van-coordinaten/)
transformatiegrid als een JAR. Deze JAR is nodig
om met Geotools precieze transformaties te maken tussen
Lambert 72 xy-coördinaten (EPSG:31370) en Lambert 2008 (EPSG:3812) xy-coördinaten.

## Hoe gebruiken

Het volstaat om de package als een dependency toe te voegen in de Maven pom-file.

```xml

<dependency>
  <groupId>be.vlaanderen.omgeving.mercator.geotools</groupId>
  <artifactId>gt-transformation-bd72lb72_etrs89lb08</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

In combinatie met onderstaande Geotools libraries zal Geotools automatisch kiezen voor de meest
precieze transformatiemethode met het `bd72lb72_etrs89lb08.gsb` transformatiegrid.

```xml

<dependencies>
   <dependency>
      <groupId>org.geotools</groupId>
      <artifactId>gt-referencing</artifactId>
      <version>${geotools.version}</version>
   </dependency>
   <dependency>
      <groupId>org.geotools</groupId>
      <artifactId>gt-epsg-hsql</artifactId>
      <version>${geotools.version}</version>
   </dependency>
</dependencies>
```

## Context

In GIS-toepassingen is de transformatiemethode [EPSG:8369](https://epsg.io/8369) (BD72 to ETRS89
Transformation of
coordinates at 0.01m level of accuracy) de meest precieze transformatiemethode die momenteel
beschikbaar is voor de omzetting tussen
Lambert 72 xy-coördinaten (EPSG:31370) en Lambert 2008 (EPSG:3812) xy-coördinaten.

Op haar [website](https://www.ngi.be/website/hulpmiddelen-voor-transformatie-van-coordinaten/)  
geeft het NGI een schematisch overzicht van deze methode die verloopt in 3 stappen:

1. De omzetting van cartesiaanse xy-coördinaten van de Lambert projectie naar geografische
   φλ-coördinaten volgens de Lambert72 datum.
2. De omzetting van Lambert 72 datum naar ETRS89 datum via het NTV2 transformatiegrid
   bd72lb72_etrs89lb08.gsb ontwikkeld door Nicolas SIMON (SPW) en gevalideerd door het NGI. Dit
   transformatiegrid kan gebruikt worden onder de Creative Commons Attribution license CC BY
   licentie.
3. De omzetting van de geografische φλ-coördinaten volgens de ETRS89 datum naar de cartesiaanse
   xy-coördinaten Lambert 2008 projectie.

![Omzetting van LB72 naar LB08 coördinaten mbv NTv2 transformatiegrid](https://www.ngi.be/website/wp-content/uploads/2020/07/LB08-naar-LB72_2.png)