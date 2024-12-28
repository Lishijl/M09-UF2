# SORTIDES D'EXECUCIONS DE L'ACTIVITAT 02-FUTBOL

## Part 1:

```
Inici dels xuts --------------------
Fi dels xuts -----------------------
--- Estadístiques ------
Piqué -> 11 gols
Vinicius -> 8 gols
Torres -> 10 gols
Ramos -> 8 gols
Ronaldo -> 9 gols
Lewan -> 9 gols
Belli -> 9 gols
Arnau -> 12 gols
Aspas -> 8 gols
Messi -> 12 gols
MBapé -> 10 gols
```

## Part 2:

Demostra que qualsevol programa que s'executi, el primer fil principal en executar-se es dirà sempre **main**, amb una prioritat per defecte d'un 5.

Perquè tots els programes s'executen desde el mètode main, i per tant es el primer fil en crear-se i iniciar-se, que pertany al grup de fils ***main*** (on es creen els fils de manera predeterminada).

En les versions modernes del JDK, ja no mostra el ID del ***Thread[main,5,main]***, però si calgués accedir-hi, només ens caldria cridar-li l'accessor getId() al Thread corresponent.

```
MainDemoFil.main:
Prioritat -> 5, Nom -> main
toString() -> Thread[main,5,main]
```