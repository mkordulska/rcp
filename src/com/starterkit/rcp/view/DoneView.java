package com.starterkit.rcp.view;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

public class DoneView extends ViewPart {

	public DoneView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		ListViewer listViewer = new ListViewer(parent, SWT.BORDER | SWT.V_SCROLL);
		List list = listViewer.getList();

		 MenuManager menuManager = new MenuManager();
		 Menu menu = menuManager.createContextMenu(list);
		 list.setMenu(menu);
		 getSite().registerContextMenu(menuManager, listViewer);
		    getSite().setSelectionProvider(listViewer);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
