package it.polimi.ingsw.GC_36.model;

import it.polimi.ingsw.GC_36.exception.NotAvailableException;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ActionInterface extends Serializable, Remote {
	void setMemberColor(MemberColor MemberColor) throws RemoteException;

	void setActionSpaceId(ActionSpaceIds actionSpaceId)
			throws RemoteException, NotAvailableException;

	void setCardPaymentOptions(int choice) throws RemoteException;

	int getCardPaymentOption() throws RemoteException;

	int getActionValue(Player player) throws RemoteException;

	Integer getProductionChoice(boolean checking) throws RemoteException;

	int getActionValueIncrement() throws RemoteException;

	void setActionValueIncrement(int increment) throws RemoteException;

	public MemberColor getMemberColor() throws RemoteException;

	ActionSpaceIds getActionSpaceId() throws RemoteException;

	void putPrivilegeChoice(int choice) throws RemoteException;

	List<CouncilPrivilege> getCouncilPrivilegeList() throws RemoteException;

	public void copyFrom(ActionInterface original) throws RemoteException;

	void setExtraAction(ExtraAction extraAction) throws RemoteException;

	boolean isAvailable(ActionSpaceIds actionSpaceIds) throws RemoteException;

	void addProductionChoice(Integer choice) throws RemoteException;

	ExtraAction getExtraAction() throws RemoteException;

	List<LeaderCard> getLeaderCards() throws RemoteException;
}
