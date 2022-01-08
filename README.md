# Assignment 4

Assignment 4 for the course Object-Oriented Programming written and programmed by Ofri Tavor and Nir Sasson.

## Overview
In this project we were tasked

## Getting Started

### Installation

Follow the steps in order to clone the project and install the required dependencies:

1. Clone the repo

   ```sh
   git clone https://github.com/SassonNir/OOP-Ex4.git
   ```

2. This project uses and requires the [Google's serialization/deserialization library for JSON in Java called gson](https://github.com/google/gson):

    Gradle:

    ```gradle
    dependencies {
        implementation 'com.google.code.gson:gson:2.8.9'
    }
    ```

    Maven:

    ```xml
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.9</version>
    </dependency>
    ```

    [Gson jar downloads](https://maven-badges.herokuapp.com/maven-central/com.google.code.gson/gson) are available from Maven Central.

### Usage

In order to run the graphical user interface run the following command into the teminal:

```bash
java -jar ./path/to/jar/Ex2.jar ./path/to/json/Graph.json
```

Which will open and load the specified json into the GUI.

## Documentation

For more information and details about the structure and background of the project please refer to the [Wiki Pages](../../wiki).

## Comparison and Stats

These results ran on a laptop with the following CPU: AMD Ryzen 5 3500U with Radeon Vega Mobile Gfx (8 CPUs), ~2.1 GHz, 12 GB RAM.

### The GUI


### Results
case 0:
```json
{"GameServer":{"pokemons":1,"is_logged_in":false,"moves":292,"grade":147,"game_level":0,"max_user_level":-1,"id":0,"graph":"data/A0","agents":1}}
```
case 1:
```json
{"GameServer":{"pokemons":2,"is_logged_in":false,"moves":585,"grade":543,"game_level":1,"max_user_level":-1,"id":0,"graph":"data/A0","agents":1}}
```
case 2:
```json
{"GameServer":{"pokemons":3,"is_logged_in":false,"moves":292,"grade":275,"game_level":2,"max_user_level":-1,"id":0,"graph":"data/A0","agents":1}}
```
case 3:
```json
{"GameServer":{"pokemons":4,"is_logged_in":false,"moves":586,"grade":911,"game_level":3,"max_user_level":-1,"id":0,"graph":"data/A0","agents":1}}
```
case 4:
```json
{"GameServer":{"pokemons":5,"is_logged_in":false,"moves":291,"grade":262,"game_level":4,"max_user_level":-1,"id":0,"graph":"data/A1","agents":1}}
```
case 5:
```json
{"GameServer":{"pokemons":6,"is_logged_in":false,"moves":589,"grade":851,"game_level":5,"max_user_level":-1,"id":0,"graph":"data/A1","agents":1}}
```
case 6:
```json
{"GameServer":{"pokemons":1,"is_logged_in":false,"moves":293,"grade":79,"game_level":6,"max_user_level":-1,"id":0,"graph":"data/A1","agents":1}}
```
case 7:
```json
{"GameServer":{"pokemons":2,"is_logged_in":false,"moves":588,"grade":396,"game_level":7,"max_user_level":-1,"id":0,"graph":"data/A1","agents":1}}
```
case 8:
```json
{"GameServer":{"pokemons":3,"is_logged_in":false,"moves":292,"grade":130,"game_level":8,"max_user_level":-1,"id":0,"graph":"data/A2","agents":1}}
```
case 9:
```json
{"GameServer":{"pokemons":4,"is_logged_in":false,"moves":587,"grade":516,"game_level":9,"max_user_level":-1,"id":0,"graph":"data/A2","agents":1}}
```
case 10:
```json
{"GameServer":{"pokemons":5,"is_logged_in":false,"moves":292,"grade":210,"game_level":10,"max_user_level":-1,"id":0,"graph":"data/A2","agents":1}}
```
case 11:
```json
{"GameServer":{"pokemons":6,"is_logged_in":false,"moves":587,"grade":1768,"game_level":11,"max_user_level":-1,"id":0,"graph":"data/A2","agents":3}}
```
case 12:
```json
{"GameServer":{"pokemons":1,"is_logged_in":false,"moves":292,"grade":40,"game_level":12,"max_user_level":-1,"id":0,"graph":"data/A3","agents":1}}
```
case 13:
```json
{"GameServer":{"pokemons":2,"is_logged_in":false,"moves":581,"grade":363,"game_level":13,"max_user_level":-1,"id":0,"graph":"data/A3","agents":2}}
```
case 14:
```json
{"GameServer":{"pokemons":3,"is_logged_in":false,"moves":291,"grade":246,"game_level":14,"max_user_level":-1,"id":0,"graph":"data/A3","agents":3}}
```
case 15:
```json
{"GameServer":{"pokemons":4,"is_logged_in":false,"moves":588,"grade":375,"game_level":15,"max_user_level":-1,"id":0,"graph":"data/A3","agents":1}}
```
