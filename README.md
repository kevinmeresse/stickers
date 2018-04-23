# stickers
An Android app that shows a list of stickers based on what you type.
This implements the latest Android Architecture Components and the MVVM pattern (separation of concerns).

<img src="/art/screenshots-combined.jpg" height="400">

### Features
- Parse stickers and associated tags from local JSON file
- Store data in memory using a Trie to handle tags with multiple words efficiently
- Analyze input text and display all related stickers
- Choose between 2 different image management libraries (Glide and Fresco)
- Adaptive app icons

### Architecture components
- ViewModel (link between UI and data)
- LiveData (observable data holder)
- Repository (data provider)

### Third-party libraries
- Apache commons: Utility algorithms working on strings
- Butterknife: Layout views binding
- Glide and Fresco: Image management libraries to efficiently load and cache images