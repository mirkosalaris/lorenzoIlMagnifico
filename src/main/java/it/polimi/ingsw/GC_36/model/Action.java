package it.polimi.ingsw.GC_36.model;

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
	public void setActionSpaceIds(ActionSpaceIds actionSpaceIds) {
		this.actionSpaceIds = actionSpaceIds;
	}

	@Override
	public void setCardPaymentOptions(int choice){
		cardPaymentOptions=choice;
	}

	@Override
	public int getCardPaymentOption() {
		return cardPaymentOptions;
	}

	public void setActionValueIncrement(int increment){
		this.actionValueIncrement=increment;

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
	public void setExtraAction(ExtraAction extraAction) {
		this.extraAction=extraAction;
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
		int memberValue=getMemberValue(player);
		return memberValue + actionValueIncrement;
	}

	public int getProductionChoice() {
		int choice = productionChoice.get(0);
		productionChoice.remove(0);
		return choice;
	}

	public int getMemberValue(Player player){
		int memberValue = player.getFamilyMemberValue(memberColor);
		return memberValue;
	}

	public int getActionValueIncrement(){
		return actionValueIncrement;
	}
	@Override
	public String toString() {
		return "Action{" +
				"memberColor=" + memberColor +
				", actionSpaceIds=" + actionSpaceIds +
				", councilPrivilegeList=" + councilPrivilegeList +
				", extraActions=" + extraAction +
				", productionChoice=" + productionChoice +
				'}';
	}
	public ExtraAction getExtraAction(){
		return extraAction;
	}
}
