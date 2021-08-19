plugins {
    javafx
    kotlin("jvm")
    kotlin("kapt")
    application
}

application.mainClass.set("com.example.$RELEASE_ARTIFACT.ExampleApp")

javafx {
    version = "11"
    modules("javafx.controls", "javafx.fxml")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

sourceSets {
    get("main").java.srcDir("src")
    get("test").java.srcDir("tests/src")
}

dependencies {
    api(kotlin("stdlib", VERSION_KOTLIN))
    implementation(project(":$RELEASE_ARTIFACT-jvm"))
    kapt(project(":$RELEASE_ARTIFACT-compiler"))
    implementation(hendraanggrian("ktfx", "ktfx", VERSION_KTFX))
    implementation(apache("commons", "commons-lang3", VERSION_COMMONS_LANG))
}