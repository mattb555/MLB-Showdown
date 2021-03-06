package players;

import java.util.Set;

import gameData.StrategyCard;

/**
 * PlayerData is the representation of player data common to both pitchers and
 * position players in MLB Showdown. Instances of PlayerData are not intended to
 * be created, within the scope of MLB Showdown, it simply provides a baseline
 * set of methods useful for both HitterData and PitcherData, which are the
 * actual classes that should be used for the creation of Hitters and Pitchers
 * respectively.
 * 
 * @author Matthew Bunge
 */

public abstract class PlayerData implements Comparable<PlayerData> {

	protected int setNum;
	protected String edition;
	protected String name;
	protected String setTeam;
	protected String year;
	protected int cost;
	protected Set<String> special;
	protected Range strikeout;
	protected Range groundout;
	protected Range flyout;
	protected Range walk;
	protected Range single;
	protected Range twobase;
	protected Range homer;
	protected int[] positions = new int[10]; // Follow scorecard format, 1=P,
												// 2=C, 3=1B, etc, 0=DH
	protected int baseMod; // On-Base/Control

	/**
	 * Returns the value of the base at-bat/pitching value of a given player
	 * 
	 * @return the integer value of the base at-bat/pitching value
	 */
	public int getBaseMod() {
		return baseMod;
	}

	/**
	 * Returns the number of the card in its respective release set
	 * 
	 * @return The set number of the card
	 */
	public int getSetNum() {
		return setNum;
	}

	/**
	 * Returns the numeric range of a strikeout result
	 * 
	 * @return the Range resulting in a strikeout
	 */
	public Range getStrikeout() {
		return strikeout;
	}

	/**
	 * Returns the numeric range of a groundout result
	 * 
	 * @return the Range resulting in a groundout
	 */
	public Range getGroundout() {
		return groundout;
	}

	/**
	 * Returns the numeric range of a flyout result
	 * 
	 * @return the Range resulting in a flyout
	 */
	public Range getFlyout() {
		return flyout;
	}

	/**
	 * Returns the numeric range of a walk result
	 * 
	 * @return the Range resulting in a walk
	 */
	public Range getWalk() {
		return walk;
	}

	/**
	 * Returns the numeric range of a single result
	 * 
	 * @return the Range resulting in a single
	 */
	public Range getSingle() {
		return single;
	}

	/**
	 * Returns the numeric range of a double result
	 * 
	 * @return the Range resulting in a double
	 */
	public Range getDouble() {
		return twobase;
	}

	/**
	 * Returns the numeric range of a homerun result
	 * 
	 * @return the Range resulting in a homerun
	 */
	public Range getHomer() {
		return homer;
	}

	/**
	 * Changes the player's name to a given string
	 * 
	 * @param s
	 *            The player's new name
	 */
	protected void setName(String s) {
		name = s;
	}

	/**
	 * Changes the player's strikeout value to a given range
	 * 
	 * @param r
	 *            The player's new strikeout range
	 */
	protected void setStrikeout(Range r) {
		strikeout = r;
	}

	/**
	 * Changes the player's groundout value to a given range
	 * 
	 * @param r
	 *            The player's new groundout range
	 */
	protected void setGroundout(Range r) {
		groundout = r;
	}

	/**
	 * Changes the player's flyout value to a given range
	 * 
	 * @param r
	 *            The player's new flyout range
	 */
	protected void setFlyout(Range r) {
		flyout = r;
	}

	/**
	 * Changes the player's walk value to a given range
	 * 
	 * @param r
	 *            The player's new walk range
	 */
	protected void setWalk(Range r) {
		walk = r;
	}

	/**
	 * Changes the player's single value to a given range
	 * 
	 * @param r
	 *            The player's new single range
	 */
	protected void setSingle(Range r) {
		single = r;
	}

	/**
	 * Changes the player's double value to a given range
	 * 
	 * @param r
	 *            The player's new double range
	 */
	protected void setDouble(Range r) {
		twobase = r;
	}

	/**
	 * Changes the player's homer value to a given range
	 * 
	 * @param r
	 *            The player's new homer range
	 */
	protected void setHomer(Range r) {
		homer = r;
	}

	/**
	 * Changes the player's fielding value at a given position to a given value
	 * 
	 * @param val
	 *            The player's new fielding value
	 * @param index
	 *            The position whose fielding value is being adjusted
	 */
	protected void setPosition(int val, int index) {
		positions[index] = val;
	}

	/**
	 * Changes the player's at-bat/pitching value to a given value
	 * 
	 * @param i
	 *            The player's new at-bat/pitching value
	 */
	protected void setBaseMod(int i) {
		baseMod = i;
	}

	/**
	 * Returns a string representation of the player in the form of "FirstName
	 * LastName"
	 * 
	 * @return The string representation of the player
	 */
	public String toString() {
		return name;
	}

	/**
	 * Checks if the player is qualified to play at a given position
	 * 
	 * @param i
	 *            the fielding position being queried
	 * @return True if the player is qualified to play the position and false
	 *         otherwise
	 */
	public boolean playsPosition(int i) {
		// All positions are valid as DH's other than Pitchers
		if (i == 0 && positions[1] == 0) {
			return true;
		}
		return positions[i] >= 0;
	}

	/**
	 * Returns a list of all positions the player is qualified to play in the
	 * form: "3, 6, 7, 8, 9"
	 * 
	 * @return The string containing all the positions a player is qualified to
	 *         play
	 */
	public String queryPosition() {
		String plays = "";
		for (int i = 0; i <= 9; i++) {
			if (positions[i] >= 0) {
				if (plays.isEmpty()) {
					plays += i;
				} else {
					plays += ", " + i;
				}
			}
		}
		return plays;
	}

	/**
	 * Returns the numeric value of the players fielding ability at a given
	 * position
	 * 
	 * @param position
	 *            The position being queried
	 * @return the integer value of the players fielding ability
	 */
	public int getFielding(int position) {
		return positions[position];
	}

	/**
	 * Verifies what result comes from a diceroll and emits the result to the
	 * gamelog The actual result is not called to give the client a chance to
	 * check for strategy cards that could impact the outcome
	 * 
	 * @param dice
	 *            The value of the diceroll being compared against the card
	 */
	protected void checkCard(int dice) {
		if (strikeout.inRange(dice)) {
			StrategyCard.emit("SO");
		} else if (groundout.inRange(dice)) {
			StrategyCard.emit("GO");
		} else if (flyout.inRange(dice)) {
			StrategyCard.emit("FO");
		} else if (walk.inRange(dice)) {
			StrategyCard.emit("BB");
		} else if (single.inRange(dice)) {
			StrategyCard.emit("1B");
		} else if (twobase.inRange(dice)) {
			StrategyCard.emit("2B");
		} else if (homer.inRange(dice)) {
			StrategyCard.emit("HR");
		}
	}

	/**
	 * Returns a string representation to be printed on the final GUI. Should be
	 * overwritten by a child class.
	 * 
	 * @throws UnsupportedOperationException
	 *             if method not overwritten
	 */
	public String getCard() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns whether the given card is a Pitcher
	 */
	public abstract boolean isPitcher();

	/**
	 * Comapres two PlayerDats based on their Set Numbers
	 */
	public int compareTo(PlayerData p) {
		return this.setNum - p.setNum;
	}

	@Override
	// Format used in Effective Java
	/**
	 * Overrides the generic Equals method Two PlayerDatas are equal if they
	 * have the same set number and the same name
	 */
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof PlayerData)) {
			return false;
		}

		PlayerData p = (PlayerData) o;

		return p.getSetNum() == setNum && p.toString().equals(name);
	}
}
