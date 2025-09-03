package vermesa.lotr.model.player;

import vermesa.lotr.model.race_effects.Race;
import vermesa.lotr.model.skills.ExtendedSkillSet;

import java.io.Serializable;


/**
 * An abstract parent of players holding common implementation details:
 * - {@link FellowshipPlayer}
 * - {@link SauronPlayer}
 */
public class Player implements Serializable {
    private final PlayerType type;

    /**
     * The skill set that the player has gained through different actions
     */
    private final ExtendedSkillSet skillSet;

    /**
     * The player state holding state such as chaining symbols
     */
    private final PlayerState playerState;

    /**
     * Holds the number of supporters for each race
     * The length of this array should be equal to the number of races presented here: {@link Race}
     */
    private final int[] supportingRaces;

    /**
     * The number of coins the player currently has
     */
    private int coins;

    /**
     * The number of units the player currently has that are not placed in any regions
     */
    private int units;

    /**
     * The number of fortresses the player currently has that are not placed in any regions
     */
    private int fortresses;

    public Player(PlayerType type, int startingCoins, int units, int towers) {
        this.type = type;
        this.coins = startingCoins;
        this.units = units;
        this.fortresses = towers;
        this.skillSet = new ExtendedSkillSet();
        this.supportingRaces = new int[Race.values().length];
        this.playerState = new PlayerState();
    }

    /**
     *
     * @return Returns the type of the player {@link PlayerType}
     */
    public PlayerType getType() {
        return type;
    }

    /**
     *
     * @return The state of the player
     */
    public PlayerState getPlayerState() {
        return playerState;
    }

    /**
     * Adds a support of a race
     * @param race Race to support
     */
    public void addSupportingRace(Race race) {
        supportingRaces[race.ordinal()]++;
    }

    /**
     *
     * @return The race supporting array
     */
    public int[] getSupportingRaces() {
        return supportingRaces;
    }

    /**
     *
     * @return The skill set the player has
     */
    public ExtendedSkillSet getSkills() {
        return skillSet;
    }

    /**
     *
     * @return Available coins of the player
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Adds coins to the player
     *
     * @param coins Number of coins to add
     */
    public void addCoins(int coins) {
        this.coins += coins;
    }

    /**
     * Removes coins from the player
     *
     * @param coins Number of coins to remove
     */
    public void removeCoins(int coins) {
        this.coins -= coins;
    }

    /**
     *
     * @return The number of units the player has not placed yet in any regions
     */
    public int getUnits() {
        return units;
    }

    /**
     * Remove units i.e. place them to a Region
     * @param units Number of units to place
     */
    public void removeUnits(int units) {
        this.units -= units;
    }

    /**
     * Places back units from a region e.g. as a result of {@link vermesa.lotr.model.actions.central_board_actions.TakeEnemyUnitFromCentralBoardAction}
     *
     * @param units
     */
    public void placeBackUnits(int units) {
        this.units += units;
    }

    /**
     * @return Number of currently available fortresses to place to a region
     */
    public int getFortresses() {
        return fortresses;
    }

    /**
     * Removes a fortress from the player to place in to a region on the central board
     */
    public void removeFortress() {
        fortresses--;
    }

    /**
     * Places back a fortress to a player from a region e.g. as a result of {@link vermesa.lotr.model.actions.central_board_actions.RemoveEnemyFortressAction}
     */
    public void placeBackFortress() {
        fortresses++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        return getClass() == o.getClass();
    }
}
