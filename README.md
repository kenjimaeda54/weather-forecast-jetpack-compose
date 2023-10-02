# Weather Forecast
Um App para consulta de previsão do tempo semanal de uma determinada região, pode escolher a região que deseja consultar, colocar e visualizar as favoritas regiões é escolher o tipo de unidade que deseja visualizar Fahrenheit  ou Celsius

## Feature
- Utilizei o Room novamente pra armazenar dados locais, neste caso foi para adicionar as regiões favoritas
- Toda vez que manipular algo relacionando ao banco é necessário mudar a versão, por exemplo,crio  uma tabela e futuramente precisei de outra, então precisa mudar a versão do sua database
- A classe UnitModel foi adicionada depois que já foi criado a database

```kotlin

@Database(entities = [FavoritesModel::class,UnitModel::class], version = 2, exportSchema = false)
abstract class ForecastDatabase: RoomDatabase() {
    abstract fun ForecastDao(): ForecastDao
}



```

##

- Quando deseja restaurar uma valor de um estado dentro de uma Activy Ou Fragment pode usar o rememberSaveable,[artigo]([https://developer.android.com/jetpack/compose/state?hl=pt-br#:~:text=Embora%20remember%20ajude%20a%20manter,ser%20salvo%20em%20um%20Bundle%20.](https://stefma.medium.com/jetpack-compose-remember-mutablestateof-derivedstateof-and-remembersaveable-explained-270dbaa61b8)https://stefma.medium.com/jetpack-compose-remember-mutablestateof-derivedstateof-and-remembersaveable-explained-270dbaa61b8)
- Abaixo alguns recursos específicos do kotlin que ajuda no dia a dia

```kotlin
//kotlin não possui if ternário, porem pode associar variável a praticamente tudo como exemplo abaixo
val unit = if(forecastViewModel.listUnits.collectAsState().value.isEmpty())  {
          "miles/hours"
} else if(forecastViewModel.listUnits.collectAsState().value[0].unit == UnitsTemperature.Celsius.units) {
       "meter/sec"
} else {
        "miles/hours"
}



//também pode usar o when uma alternativa elegante ao switch
Image(
       imageVector = when (it) {
             "About" -> Icons.Default.Info
             "Favorites" -> Icons.Default.FavoriteBorder
             else -> Icons.Default.Settings
         }, contentDescription = it, contentScale = ContentScale.Fit
        )
       Text(
         modifier = Modifier.padding(start = 5.dp),
           text = it,
           style = MaterialTheme.typography.labelSmall
    )
 }


```



