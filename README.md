# The Lord of the Rings: Duel for Middle-earth

## Running the program

To compile the project, execute the following command:

```shell
mvn compile
```

To play the game, execute the following command:

```shell
mvn exec:java@play
```

## Documentation

To generate the `javadoc` documentation, execute the following command:

```shell
mvn javadoc:javadoc
```

## Gameplay

After starting the game, there are two main commands to use:

- `move` → lists the available moves and asks to choose one. You can choose a move by entering its number that is
  present at the beginning of the row. Note that after calling this command, there is no way to retrieve the state so if
  you would like to examine the state and choose a move accordingly, first call the `state` command
- `state` → displays a console-serialized view of the game state

### Rules

The official rules about the game can be found in the `doc` directory