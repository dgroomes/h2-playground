plugins {
    application
}

repositories {
    mavenCentral()
}

val slf4jVersion = "1.7.36" // SLF4J releases: http://www.slf4j.org/news.html
val h2Version = "2.1.214" // H2 releases: https://github.com/h2database/h2database/releases

dependencies {
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")
    implementation("com.h2database:h2:$h2Version")
}

application {
    mainClass.set("dgroomes.App")
}
