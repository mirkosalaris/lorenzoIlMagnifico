package it.polimi.ingsw.GC_36.model;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ActionInterface extends Serializable, Remote {
	void setMemberColor(MemberColor MemberColor) throws RemoteException;

	void setActionSpaceIds(ActionSpaceIds actionSpaceIds)
			throws RemoteException;

	void setPaymentList(ResourcesList resourcesList) throws RemoteException;

	void setCouncilPrivilegeList(List<Integer> privilegeList)
			throws RemoteException;

	ActionSpaceIds getActionSpaceId() throws RemoteException;

	void putPrivilegeChoice(int choice) throws RemoteException;

	List<Integer> getCouncilPrivilegeList() throws RemoteException;

	public void copyFrom(ActionInterface original) throws RemoteException;

	void addExtraAction(ExtraAction extraAction) throws RemoteException;

	boolean isAvailable(ActionSpaceIds actionSpaceIds) throws RemoteException;

	void addProductionChoice(Integer choice) throws RemoteException;
}
