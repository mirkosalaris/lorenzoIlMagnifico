package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.model.*;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class ViewGUI implements ViewInterface {

	@Override
	public MemberColor chooseMemberColor() {
		return null;
	}

	@Override
	public int setActionValueIncrement() {
		return 0;
	}

	@Override
	public int chooseActionSpaceId() {
		return 0;
	}

	@Override
	public int choosePrivilege(int n) {
		return 0;
	}

	@Override
	public int chooseExtraActionSpaceId(Set<ActionSpaceIds> actionSpaceIds,
	                                    int actionValue) {
		return 0;
	}

	@Override
	public int chooseConvertingMethod(
			Map<Integer, Pair<ResourcesList, ResourcesList>> options) {
		return 0;
	}

	@Override
	public int chooseCardPaymentOptions(DevelopmentCard card) {
		return 0;
	}

	@Override
	public int selectNumberOfWoods() {
		return 0;
	}

	@Override
	public int selectNumberOfStones() {
		return 0;
	}

	@Override
	public int selectNumberOfServants() {
		return 0;
	}

	@Override
	public int selectNumberOfCoins() {
		return 0;
	}

	@Override
	public int selectNumberOfVictoryPoints() {
		return 0;
	}

	@Override
	public int selectNumberOfMilitaryPoints() {
		return 0;
	}

	@Override
	public int selectNumberOfFaithPoints() {
		return 0;
	}

	@Override
	public void show(String message) {

	}

	@Override
	public void fatalError(String s) {

	}

	@Override
	public void play(ActionInterface action) {

	}

	@Override
	public void exit(String message) {

	}

	@Override
	public void setIdentifier(PlayerIdentifier identifier) throws IOException {

	}

	@Override
	public void update() {

	}

	@Override
	public void update(BoardState currentState) {

	}

	@Override
	public void update(DieColor dieColor, int value) throws IOException {

	}

	@Override
	public void update(PlayerState newState) {

	}

	@Override
	public void update(ActionSpaceIds id, boolean free) {

	}

	@Override
	public void update(ActionSpaceIds id, PlayerColor playerColor)
			throws IOException {

	}

	@Override
	public void update(GameState newState) {

	}

	@Override
	public void update(int periodNumber) {

	}

	@Override
	public void update(RoundState newState) {

	}

	@Override
	public void update(PlayerIdentifier newPlayer) {

	}

	@Override
	public void update(int floorNumber, DevelopmentCard developmentCard) {

	}

	@Override
	public void update(DevelopmentCard card) throws IOException {

	}

	@Override
	public void update(ResourcesList resourcesList) {

	}
}
