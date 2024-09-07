@file:Suppress("PropertyName")

plugins {
    id("fabric-loom") version "1.6-SNAPSHOT"
}

group = getProperty("maven_group")
version = getProperty("mod_version")

dependencies {
    minecraft("com.mojang", "minecraft", getProperty("minecraft_version"))
    mappings("net.fabricmc", "yarn", getProperty("yarn_mappings"), classifier = "v2")
    modImplementation("net.fabricmc", "fabric-loader", getProperty("loader_version"))
    modImplementation("net.fabricmc.fabric-api", "fabric-api", getProperty("fabric_version"))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks {
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release = 17
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

fun getProperty(key: String): String = extra[key].toString()