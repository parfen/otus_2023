rootProject.name = "otus_2023"
include ("L01-gradle")
include ("L04-generics")
include ("L06-annotations")

include ("L08-gc")
include ("L10-byteCodes")
include ("L12-solid")
include("L15-structuralPatterns:demo")
include("L15-structuralPatterns:homework")

include("L16-io:demo")
include("L16-io:homework")

include("L19-jdbc:demo")
include("L19-jdbc:homework")

include("L21-jpql:class-demo")
include("L21-jpql:homework-template")

pluginManagement {
    val jgitver: String by settings
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings
    val johnrengelmanShadow: String by settings
    val jib: String by settings
    val protobufVer: String by settings

    plugins {
        id("fr.brouillard.oss.gradle.jgitver") version jgitver
        id("io.spring.dependency-management") version dependencyManagement
        id("org.springframework.boot") version springframeworkBoot
        id("com.github.johnrengelman.shadow") version johnrengelmanShadow
        id("com.google.cloud.tools.jib") version jib
        id("com.google.protobuf") version protobufVer
    }
}