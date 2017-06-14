package it.polimi.ingsw.GC_36.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Action extends UnicastRemoteObject implements ActionInterface {
	private MemberColor memberColor;
	private ActionSpaceIds actionSpaceIds;
	private ResourcesList paymentList;
	private List<Integer> councilPrivilegeList;
	private ArrayList<ExtraAction> extraActions;
	private ArrayList<Integer> productionChoice;

	public Action() throws RemoteException {
		extraActions = new ArrayList<ExtraAction>();
		productionChoice = new ArrayList<>();
	}

	@Override
	public void setMemberColor(MemberColor MemberColor) {
		this.memberColor = MemberColor;
	}

	@Override
	public void setActionSpaceIds(ActionSpaceIds actionSpaceIds) {
		this.actionSpaceIds = actionSpaceIds;
	}

	@Override
	public void setPaymentList(ResourcesList resourcesList) {
		this.paymentList = resourcesList;
	}

	@Override
	public void setCouncilPrivilegeList(List<Integer> privilegeList) {
		this.councilPrivilegeList = privilegeList;
	}

	public MemberColor getMemberColor() {
		return memberColor;
		//TODO:associated test replacing getFamilyMemberTest
	}

	@Override
	public ActionSpaceIds getActionSpaceId() {
		return actionSpaceIds;
	}

	public ResourcesList getPaymentList() {
		return paymentList;
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
	}

	@Override
	public void addExtraAction(ExtraAction extraAction) {
		extraActions.add(extraAction);
	}

	@Override
	public boolean isAvailable(ActionSpaceIds actionSpaceIds) {
		return true;
	}

	@Override
	public void addProductionChoice(Integer choice) {
		productionChoice.add(choice);
	}

	public int getActionValue(Player player) {
		int memberValue = player.getFamilyMemberValue(memberColor);
		int numOfServants = paymentList.get(ResourceType.SERVANT).getValue();
		return memberValue + numOfServants;
	}

	public int getProductionChoice() {
		int choice = productionChoice.get(0);
		productionChoice.remove(0);
		return choice;
	}
}
