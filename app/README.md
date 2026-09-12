# StringConcatenator

## Por que usei cada coisa

### Por que uma sealed class para as rotas (Routes.kt)

Em vez de escrever o nome das telas como texto solto ("home", "addWord") em vários lugares do código, eu guardei tudo em um único arquivo. Assim, se eu errar de digitar o nome de uma rota, o próprio compilador acusa o erro antes de eu nem rodar o app — em vez de descobrir isso só quando o app trava. É basicamente uma forma de organizar e proteger contra erro de digitação.

### Por que mandei a string atual como "argumento de rota" (Home → AddWord)

Quando o usuário toca em "Adicionar palavra", a tela AddWordScreen já precisa nascer sabendo qual é a string atual, porque ela vai mostrar isso na tela assim que abrir. Por isso faz sentido mandar esse valor junto com a navegação, como se fosse um "parâmetro de entrada" da tela — ela não existiria de forma completa sem esse dado.

### Por que usei SavedStateHandle para devolver a palavra (AddWord → Home)

Aqui é o contrário do caso acima: quando o usuário toca em "Concatenar", a tela HomeScreen já existe. Não dá pra "mandar um argumento" pra uma tela que já está pronta e só esperando de volta; argumento só funciona para criar uma tela nova. Por isso a volta funciona diferente: eu guardo a palavra digitada num espacinho de memória ligado à tela anterior (o SavedStateHandle), e simplesmente fecho a tela atual (popBackStack()). Quando HomeScreen volta a aparecer, ela olha se tem algo guardado nesse espacinho e usa.

### Por que a conta de concatenar fica no NavGraph, e não dentro da HomeScreen

A string acumulada precisa "sobreviver" enquanto o usuário vai e volta entre as duas telas várias vezes. Se eu guardasse esse valor dentro da própria HomeScreen, corria risco de ele se perder ou zerar em certos momentos da navegação. Guardando no NavGraph (que é tipo o "maestro" que controla as duas telas), o valor fica seguro o tempo todo, e as telas em si ficam mais simples: só mostram informação e avisam quando o usuário clica em algo, sem precisar saber os detalhes de como a navegação funciona por trás.

 
