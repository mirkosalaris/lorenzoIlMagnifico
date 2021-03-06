package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.Commons;
import it.polimi.ingsw.GC_36.exception.NotAvailableException;
import it.polimi.ingsw.GC_36.utils.Pair;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Action extends UnicastRemoteObject implements ActionInterface {
	private MemberColor memberColor;
	private ActionSpaceIds actionSpaceId;
	private int actionValueIncrement;
	private int cardPaymentOptions; //parte da zero
	private List<CouncilPrivilege> councilPrivilegeList;
	private ExtraAction extraAction;
	private ArrayList<Integer> productionChoice;
	private int baseActionValue;
	private ResourcesList discount;
	private List<LeaderCard> leaderCards;
	private ArrayList<Integer> productionChoiceCopy;

	public Action() throws RemoteException {
		productionChoice = new ArrayList<>();
		councilPrivilegeList = new ArrayList<>();
	}

	public Action(int baseActionValue, ResourcesList discount)
			throws RemoteException {
		this();
		this.baseActionValue = baseActionValue;
		this.discount = discount;
	}

	@Override
	public void setMemberColor(MemberColor MemberColor) {
		this.memberColor = MemberColor;
	}

	@Override
	public void setActionSpaceId(ActionSpaceIds actionSpaceId)
			throws NotAvailableException {
		this.actionSpaceId = actionSpaceId;
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
	public MemberColor getMemberColor() {
		return memberColor;
	}

	@Override
	public ActionSpaceIds getActionSpaceId() {
		return actionSpaceId;
	}

	@Override
	public void putPrivilegeChoice(int choice) {
		councilPrivilegeList.add(CouncilPrivilege.values()[choice]);
	}

	@Override
	public List<CouncilPrivilege> getCouncilPrivilegeList() {
		return councilPrivilegeList;
	}

	@Override
	public void copyFrom(ActionInterface action) {
		Action original = (Action) action;
		this.memberColor = original.memberColor;
		this.actionSpaceId = original.actionSpaceId;
		this.actionValueIncrement = original.actionValueIncrement;
		this.cardPaymentOptions = original.cardPaymentOptions;
		this.councilPrivilegeList = original.councilPrivilegeList;
		this.extraAction = original.extraAction;
		this.productionChoice = original.productionChoice;
		this.baseActionValue = original.baseActionValue;
		this.discount = original.discount;
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
		int memberValue = (memberColor != null) ? player.getFamilyMember(
				memberColor).getValue() : 0;
		return baseActionValue + memberValue + actionValueIncrement;
	}

	@Override
	public Integer getProductionChoice(boolean checking) {
		if (checking) {
			if (productionChoiceCopy == null) {
				productionChoiceCopy = new ArrayList<>(productionChoice);
			}
			Integer choice = productionChoiceCopy.get(0);
			productionChoiceCopy.remove(0);
			return choice;
		} else {
			Integer choice = productionChoice.get(0);
			productionChoice.remove(0);
			return choice;
		}
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
	public List<LeaderCard> getLeaderCards() throws RemoteException {
		// return a copy
		return new ArrayList<>(leaderCards);
	}

	@Override
	public String toString() {
		return "Action{" +
				"memberColor=" + memberColor +
				", actionSpaceId=" + actionSpaceId +
				", actionValueIncrement=" + actionValueIncrement +
				", cardPaymentOptions=" + cardPaymentOptions +
				", councilPrivilegeList=" + councilPrivilegeList +
				", extraAction=" + extraAction +
				", productionChoice=" + productionChoice +
				'}';
	}

	/**
	 * compile the whole list of resources to pay, taking into account tower
	 * taxes, development card, increased action value.
	 * NB: this does not take into account the ExtraAction!
	 *
	 * @return the resourcesList to pay
	 */
	public Pair<ResourcesList, ResourcesList> compilePaymentList() {
		ResourcesList requirements = new ResourcesList();
		ResourcesList payments = new ResourcesList();

		Pair<ResourcesList, ResourcesList> paymentList = new Pair<>(
				requirements, payments);

		ActionSpaceIds id = this.actionSpaceId;

		if (Commons.isFloor(id)) {
			Tower tower = Game.getInstance().getBoard().getActionSpace(
					id).getAssociatedFloor().getAssociatedTower();

			if (!(tower.isFree())) {
				// the tower is not free, pay the tax
				paymentList.getFirst().addResources(tower.getTax());
				paymentList.getSecond().addResources(tower.getTax());
			}
			DevelopmentCard card = Game.getInstance().getBoard()
					.getActionSpace(
							id).getAssociatedFloor().readAssociatedCard();

			// get from action the user's choice on how to pay the card
			int choice = cardPaymentOptions;

			// take the resourcesList associated with the user's choice
			Pair<ResourcesList, ResourcesList> cardPayment = card
					.getRequirements().get(choice);

			// add to the payment list
			paymentList.getFirst().addResources(cardPayment.getFirst());
			paymentList.getSecond().addResources(cardPayment.getSecond());
		}

		// pay the extra servants used to increase the action value
		ResourcesList actionValuePayment = new ResourcesList();
		actionValuePayment.set(ResourceType.SERVANT, actionValueIncrement);
		paymentList.getFirst().addResources(actionValuePayment);
		paymentList.getSecond().addResources(actionValuePayment);

		if (discount != null) {
			paymentList.getFirst().applyDiscount(discount);
			paymentList.getSecond().applyDiscount(discount);
		}

		return paymentList;
	}

	public int getBaseActionValue() {
		return baseActionValue;
	}

	public void setLeaderCards(List<LeaderCard> leaderCards) {
		this.leaderCards = leaderCards;
	}
}
