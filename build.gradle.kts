@file:Suppress("PropertyName")

import org.spongepowered.asm.gradle.plugins.MixinExtension
import java.text.SimpleDateFormat
import java.util.*

val minecraft_version: String by project
val forge_version: String by project

val mapping_channel: String by project
val mapping_version: String by project

val mod_id: String by project
val mod_name: String by project
val mod_license: String by project
val mod_version: String by project
val mod_group_id: String by project
val mod_authors: String by project

plugins {
    id("net.minecraftforge.gradle") version "[6.0,6.2)"
}

buildscript {
    repositories {
        mavenCentral()
        maven("https://maven.minecraftforge.net")
        maven("https://repo.spongepowered.org/repository/maven-public/")
    }
    dependencies {
        classpath("net.minecraftforge.gradle", "ForgeGradle", "6.0.+")
        classpath("org.spongepowered", "mixingradle", "0.7-SNAPSHOT")
        classpath("org.jetbrains.kotlin", "kotlin-gradle-plugin", "1.6.10")
    }
}

apply {
    plugin("net.minecraftforge.gradle")
    plugin("org.spongepowered.mixin")
}

group = mod_group_id
version = mod_version

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

minecraft {
    mappings(mapping_channel, mapping_version)

    runs {
        create("client") {
            workingDirectory(project.file("run/client"))
            property("forge.logging.markers", "SCAN,REGISTRIES,REGISTRYDUMP")
            property("forge.logging.console.level", "debug")
        }
    }
}

val Project.mixin: MixinExtension get() = extensions.getByType()

mixin.run {
    add(sourceSets.main.get(), "$mod_id.refmap.json")
    config("$mod_id.mixins.json")
}

dependencies {
    minecraft("net.minecraftforge", "forge", "$minecraft_version-$forge_version")
    annotationProcessor("org.spongepowered", "mixin", "0.8.5", classifier = "processor")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

sourceSets.main.configure { resources.srcDirs("src/generated/resources/") }

tasks {
    withType<Jar> {
        archiveBaseName.set(project.name)
        destinationDirectory.set(File("C:/Users/rhdwl/AppData/Roaming/MultiMC/instances/1.20.1 - 포지/.minecraft/mods"))
        manifest {
            val map = mutableMapOf<String, String>()
            map["Specification-Title"] = mod_id
            map["Specification-Vendor"] = mod_authors
            map["Specification-Version"] = "1"
            map["Implementation-Title"] = project.name
            map["Implementation-Version"] = project.version.toString()
            map["Implementation-Vendor"] = mod_authors
            map["Implementation-Timestamp"] = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(Date())
            attributes(map)
        }
        finalizedBy("reobfJar")
    }
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }
}