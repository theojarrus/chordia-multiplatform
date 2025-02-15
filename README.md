
# Chordia

![](https://img.shields.io/badge/Version-Beta_1.0-778899)

Powerful and scalable chord generation library for Kotlin Multiplatform

![](https://i.imgur.com/LYPCs8b.jpeg)

## Usage

The library is divided into three main modules:
- Generation - creates unique chords variants from pattern and tonic
- Rendering - creates images from previously generated chord variants
- Common - connects generation and rendering into complete creation cycle

Each of these modules is divided so that you can manage the list of dependencies that are included:

- Essential - contains only essential parts of the library, for example, data models and pure-kotlin algorithmic code
- Complete - contains ready to use platform components, including features like svg-support, caching, etc.

Setting up dependencies in build.gradle:


```kotlin
dependencies {
    implementation(project(":chordia-common-complete"))

    // Examples of other usage:
    // implementation(project(":chordia-generation-essential"))
    // implementation(project(":chordia-rendering-complete"))

    // Additionally, you may need a library to display svg
    // implementation(libs.accompanist.drawablepainter)
}
```

Create Chordia and generate images:

```kotlin
// Imports
import com.neotive.chordia.common.api.tool.Chordia
import com.neotive.chordia.common.invoke

import com.neotive.chordia.configuration.model.Parameters
import com.neotive.chordia.core.model.Instrument
import com.neotive.chordia.core.model.Note
import com.neotive.chordia.core.model.Pattern

// Define generation parameters
val instrument = Instrument(Type.Strings, Length.Guitar.Standard, Drop.Guitar.Standard)
val pattern = Pattern.Major
val tonic = Note.C

val parameters = Parameters(instrument, pattern, tonic)

// Create instance of Chordia
val chordia = Chordia()

// Generate images
val images = chordia.chordify(parameters)

// Draw images
images.values.forEach { rememberDrawablePainter(it.drawable) }
```

## Customization

You can manage generation and rendering parameters using `ConfigurationManager`:

```kotlin
Chordia().apply {
    configurationManager.set(
        BaseRenderingComposerConfiguration::scale to 100f,
        BaseGenerationValidatorConfiguration::maxFingers to 5
    )
}
```

Also, settings can be set separately for each instrument:

```kotlin
Chordia().apply {
    configurationManager.set(
        Strings::class,
        BaseRenderingComposerConfiguration::scale to 100f
    )
    configurationManager.set(
        Keys::class,
        BaseRenderingComposerConfiguration::scale to 40f
    )
}
```

List of all customizable parameters can be found in corresponding to component interface inside `chordia-generation-configuration`, `chordia-rendering-configuration` and `chordia-common-configuration` modules.

If any component does not meet your needs, you can easily replace it via the class constructor.

## Future and contribution

Resources for future development are limited, so feel free to contribute to this library. There are still many things to add, here is a list of the most important ones:
- Documentation
- Publishing process
- Platform native components and utilities for caching and rendering
- iOS and Desktop sample apps
- More instruments support
