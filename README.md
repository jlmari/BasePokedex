# Base Pokedex
Base Pokedex Project is created with the only purpose to be the reference for the future projects being developed easily. Its purpose is to create an app with the sufficient level of quality, pretty robust and easy-to-read in order to achieve our goal ASAP.

Currently, following the last trends in the Android Development scenario, this app is mainly developed with [Kotlin](https://developer.android.com/kotlin), by using [MVP](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) following [Clean architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) best practices and [SOLID](https://en.wikipedia.org/wiki/SOLID) principles.

### Clean Architecture
![Clean Architecture](https://miro.medium.com/max/600/1*D1EvAeK74Gry46JMZM4oOQ.png)

### Model View Pesenter
![Model View Presenter](https://miro.medium.com/max/700/0*4E8U5YuG22bLp4h8.)

### How it works?
It shows the Pokédex, an electronic device which contains the list of all known Pokémon, and allows to see detailed information of each one of them.

In order to get this information, it a modern RESTful API called , which is the real source of our pokedex.

### Implementation
In order to respect the rules established by Clean architecture and SOLID principles, this application is divided into a series of modules, each of which has its own purpose:

* Data Sources (xdatasource module) - Responsible for managing the reading and writing of a specific data source. This application has two of them:
  * Network Data Source (Java/Kotlin library) - Rest API [PokeApi](https://pokeapi.co/) where the app gets the pokemon list and each specific pokemon detail information.
  * Memory Data Source (android library)      - SQLite DataBase created with [Room](https://developer.android.com/jetpack/androidx/releases/room) which allows us to cache the retrieved pokemon detail information during a specified time. This time has been established in 10 minutes.
* Repositories (data module - Java/Kotlin library) - In charge of coordinating the Data Sources that are under their responsibility. They can decide, for each particular case, what Data Sources to use and how. We have one:
  * PokeRepository - Asks for the pokemon list to the Network Data Source using pagination (offset and limit). Also, it gets pokemon detail data by trying to obtain the information from the Memory Data Source and, if there is not there, it asks to the Network Data Source to finally store the data in Memory (Cache-based logic).
* Use Cases (domain module - Java/Kotlin library) - Responsible for requesting/sending certain information from one or more Repositories, so those Repositories can perform how and where they consider, being all part of a single unitary action (single responsibility).
  * GetPokemonsUseCase      - Returns a Response that can be Success (pokemon list) or a Failure (custom handled error).
  * GetPokemonDetailUseCase - Returns a Response that can be Success (pokemon detail data) or a Failure (custom handled error).
* Presenters (presentation module - Java/Kotlin library) - Contain the business logic (or business rules) of the screens and the actions they can perform. Through their injected Use Cases request or send data, and report and manage the result of those operations directly to the Views.
  * PokedexPresenter       - Asks for new pokedex to its GetPokemonsUseCase entries managing pagination (offset, limit) and notifies the view of each update.
  * PokemonDetailPresenter - Asks for a specific pokemon detail information by its unique identifier and notifies the view of the result.
* Views (main app module - Android Application) - Contain all view logic such as data drawing, navigation, animations, user interactions notifications and other responsibilities that a view can have. Through their Presenter they can indicate that certain actions have occurred and may also be notified of actions to be taken.
  * PokedexFragment       - Draws a RecyclerView with the pokemons retrieved from its PokedexPresenter, notifies the presenter when scroll finished and performs navigation when called to do it.
  * PokemonDetailFragment - Draws the retrieved Pokemon Detail information when retrieved from its PokemonDetailPresenter, including some image loading from external URLs.

Note that the Presentation and Main modules described here are following MVP pattern. In case of following MVVM, the Views will be observing LiveData from its corresponding ViewModels, but all other modules should remain the same. This separation of concerns makes the app easy to move from one pattern to another.

### Dependency rules
The most important thing to note is that the outermost layers of this application do not know the details of implementation of the innermost layers.
* A View does not know what its Presenter (or ViewModel if it was the case) does with the feedback it is giving and simply receives subsequent orders from it.
* A Presenter does not know which Repositories its Use Cases are using
* A Use Case does not know which Data Sources their Repositories are using
* A Repository does not know how its Data Sources are really working

All they know are the interfaces' methods they have access to, but not the actual implementation.
But at the same time, they all have in common the same enterprise business logic, so each one can see the domain layer where pure entities are located. 

#### Boundaries in modules
Each module or layer takes care of only one thing and not more than one.
In other words, they take care of its own and should not know the details of other layers.

app --------> presentation --------> domain <-------- data <-------- each data source

views --> presenters --> pure entities and use cases <-- repositories <-- data sources

### Other considerations
* BaseActivity, BaseFragment and BasePresenter (and BaseContract between them) are abstract classes which facilitates the injection and all the presenters lifecycle according to the views.
* VPHelpers is a class created to centralize management of ViewBinding in each View.
* Mappers are used to convert models attached to external libraries (API Models or DB Models) to pure entity models.
* Single Source of Truth (SSAO) extension function has been created to easily get pokemon detail from Network or Memory Data Source (and store in Memory if required).
* Cache Object has created to manage cache in Memory Data Source (DB), storing data for 10 minutes.
* Safe API Call extension function is created to manage Network API Responses and return either the desired Object (Model or Unit) or a Failure (handling the error into a custom ErrorModel).
* Unit Tests are performed in every module to guarantee a correct behaviour of the application.

### Possible future improvements
* Migrate to Jetpack Compose to create Composable Views.
* Add Android Tests with Espresso for Views.
* Currently, the application divides the different modules by separated layers, in case of a more complex app consider dividing modules by features for easier maintainability

# Dependencies
* Kotlin (Main language)
* Navigation Components (Navigation between Fragments)
* Dagger (Dependency Injection)
* Retrofit/Okhttp (Network API calls)
* Room (DataBase)
* Coroutines (Async calls)
* Glide (Photo loader)
* JUnit (Unit testing framework for Java)
* Robolectric (Unit testing framework for Android)
* MockK (Testing with mocks)
