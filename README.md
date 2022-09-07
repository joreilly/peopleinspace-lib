# peopleinspace-lib

Kotlin Multiplatform library that provides API to retrieve list of people in space and current position of International Space Station.

Add following to `commonMain`

```
implementation("io.github.joreilly:peopleinspace-lib:0.5.2")
```

Can then create instane of `PeopleInSpaceApi` and then call `fetchPeople()` or `fetchISSPosition()`


Supports following platforms
- Android
- iOS
- macOS
- JS
- JVM
