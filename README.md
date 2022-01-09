# Assignment 4

Assignment 4 for the course Object-Oriented Programming written and programmed by Ofri Tavor and Nir Sasson.

## Overview

In this project we were tasked to create a multi-agent algorithm which can connect to a remote server and using the given API collect the most targets in the given setup.

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

3. This project also uses and requires the library [JGraphT](https://jgrapht.org/):

   Maven:

    ```xml
    <dependency>
        <groupId>org.jgrapht</groupId>
        <artifactId>jgrapht-core</artifactId>
        <version>1.5.1</version>
    </dependency>
    ```

### Usage

In order to run the simulation, enter the following command to your terminal

```bash
java -jar ./OOP-Ex4.jar [CASE_NUMBER 0-15]
```

Which will open and load the specified case into the GUI.

## Documentation

For more information and details about the structure and background of the project please refer to the [Wiki Pages](../../wiki).

### Results

These results ran on a laptop with the following CPU: 11th Gen Intel(R) Core(TM) i7-1165G7 @ 2.80GHz, 2803 Mhz, 4 Core(s), 8 Logical Processor(s)

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
<center>
<img src="https://github.com/SassonNir/OOP-Ex4/blob/main/396a880f-e5c0-48d0-8ee4-b6c822913fae.gif" width="480" height="400" class="centerImage" text="Authorede by Ofri Tavor and Nir Sasson">
</center>

## Literature for review:
1. https://www.youtube.com/watch?v=Kmgo00avvEw&t=16173s
2. https://www.youtube.com/watch?v=tWVWeAqZ0WU
3. https://www.youtube.com/watch?v=Bk1GmENOvrQ
4. <cite>Jäger, G. & Goldengorin, Boris. (2005). How to make a greedy heuristic for the asymmetric traveling salesman problem competitive. University of Groningen, Research Institute SOM (Systems, Organisations and Management), Research Report.</cite>
https://www.researchgate.net/publication/4768411_How_to_make_a_greedy_heuristic_for_the_asymmetric_traveling_salesman_problem_competitive

5. <cite>Gonzalez, A. (2020). A parallel implementation of a greedy TSP algorithm (Unpublished thesis). Texas State University, San Marcos, Texas.</cite>
https://digital.library.txstate.edu/handle/10877/12922

6. https://www.youtube.com/watch?v=oovLgT2oId4&t=111s
