package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.exception.NotAvailableException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Action extends UnicastRemoteObject implements ActionInterface {
	private MemberColor memberColor;
	private ActionSpaceIds actionSpaceIds;
	private int actionValueIncrement;
	private int cardPaymentOptions; //parte da zero
	private List<Integer> councilPrivilegeList;
	private ExtraAction extraAction;
	private ArrayList<Integer> productionChoice;

	public Action() throws RemoteException {

		productionChoice = new ArrayList<>();
	}

	@Override
	public void setMemberColor(MemberColor MemberColor) {
		this.memberColor = MemberColor;
	}

	@Override
	public void setActionSpaceIds(ActionSpaceIds actionSpaceIds)
			throws NotAvailableException {
		this.actionSpaceIds = actionSpaceIds;
	}

	@Override
	public void setCardPaymentOptions(int choice) {
		cardPaymentOptions = choice;
	}

	@Override
	public int getCardPaymentOption() {
		return cardPaymentOptions;
	}

	@Override
	public void setActionValueIncrement(int increment) {
		this.actionValueIncrement = increment;

	}

	@Override
	public void setCouncilPrivilegeList(List<Integer> privilegeList) {
		this.councilPrivilegeList = privilegeList;
	}

	@Override
	public MemberColor getMemberColor() {
		return memberColor;
		//TODO:associated test replacing getFamilyMemberTest
	}

	@Override
	public ActionSpaceIds getActionSpaceId() {
		return actionSpaceIds;
	}

	@Override
	public void putPrivilegeChoice(int choice) {
		councilPrivilegeList.add(choice);
	}

	@Override
	public List<Integer> getCouncilPrivilegeList() {
		return councilPrivilegeList;
	}

	@Override
	public void copyFrom(ActionInterface action) {
		// TODO test if constraints are the same between this and action
		Action original = (Action) action;
		this.memberColor = original.memberColor;
		this.actionSpaceIds = original.actionSpaceIds;
		this.councilPrivilegeList = original.councilPrivilegeList;
		this.actionValueIncrement = original.actionValueIncrement;
		this.cardPaymentOptions = original.cardPaymentOptions;
		this.extraAction = original.extraAction;
		this.productionChoice = original.productionChoice;
	}

	@Override
	public void setExtraAction(ExtraAction extraAction) {
		this.extraAction = extraAction;
	}

	@Override
	public boolean isAvailable(ActionSpaceIds actionSpaceIds) {
		return true;
	}

	@Override
	public void addProductionChoice(Integer choice) {
		productionChoice.add(choice);
	}

	@Override
	public int getActionValue(Player player) {
		int memberValue = player.getFamilyMember(memberColor).getValue();
		return memberValue + actionValueIncrement;
	}

	@Override
	public int getProductionChoice() {
		int choice = productionChoice.get(0);
		productionChoice.remove(0);
		return choice;
	}

	@Override
	public int getActionValueIncrement() {
		return actionValueIncrement;
	}

	@Override
	public ExtraAction getExtraAction() {
		return extraAction;
	}

	@Override
	public String toString() {
		return "Action{" +
				"memberColor=" + memberColor +
				", actionSpaceIds=" + actionSpaceIds +
				", actionValueIncrement=" + actionValueIncrement +
				", cardPaymentOptions=" + cardPaymentOptions +
				", councilPrivilegeList=" + councilPrivilegeList +
				", extraAction=" + extraAction +
				", productionChoice=" + productionChoice +
				'}';
	}
}
