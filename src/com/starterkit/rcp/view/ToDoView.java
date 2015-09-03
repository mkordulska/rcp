package com.starterkit.rcp.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;

import com.starterkit.rcp.view.model.ModelProvider;
import com.starterkit.rcp.view.model.Task;

public class ToDoView extends ViewPart {
	private Table table;
	private WritableList input;
	
	private static final Image CHECKED = Activator.getImageDescriptor("icons/checked.png").createImage();
	private static final Image UNCHECKED = Activator.getImageDescriptor("icons/unchecked.png").createImage();

	public ToDoView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		final TableViewer tableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		
		TableViewerColumn tableViewerColumn_0 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnDone = tableViewerColumn_0.getColumn();
		tblclmnDone.setWidth(50);
		tblclmnDone.setText("Done");
		tableViewerColumn_0.setLabelProvider(new ColumnLabelProvider() {
	        @Override
	        public Image getImage(Object element) {
	          if (((Task) element).getDone()) {
	            return CHECKED;
	          } else {
	            return UNCHECKED;
	          }
	        }
	      });
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnTask = tableViewerColumn_1.getColumn();
		tblclmnTask.setWidth(175);
		tblclmnTask.setText("Task");
		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider() {
	        @Override
	        public String getText(Object element) {
	          Task task = (Task) element;
	          return task.getTask();
	        }
	      });
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnEdit = tableViewerColumn_2.getColumn();
		tblclmnEdit.setWidth(50);
		tblclmnEdit.setText("Edit");
		tableViewerColumn_2.setLabelProvider(new ColumnLabelProvider() {
		      @Override
		      public void update(ViewerCell cell) {
		    	     TableItem item = (TableItem) cell.getItem();

		    	     Composite buttonPane = new Composite(table, SWT.NONE);
		    	     buttonPane.setLayout(new FillLayout());

		    	     Button button = new Button(buttonPane,SWT.NONE);
		    	     button.setText("Edit");
		    	     button.addSelectionListener(new SelectionListener() {
						
						@Override
						public void widgetSelected(SelectionEvent e) {
							InputDialog inputDialog = new InputDialog(new Shell(), "Edit task",
						            "Edit your task", null, new IInputValidator() {
										
										@Override
										public String isValid(String newText) {
											// TODO Auto-generated method stub
											return null;
										}
									});
							inputDialog.create();
							inputDialog.open();
						}
						
						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
							// TODO Auto-generated method stub
							
						}
					});

		    	     TableEditor editor = new TableEditor(item.getParent());
		    	     editor.grabHorizontal  = true;
		    	     editor.grabVertical = true;
		    	     editor.setEditor(buttonPane, item, cell.getColumnIndex());
		    	     editor.layout();
		         }
		      });
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnDelete = tableViewerColumn_3.getColumn();
		tblclmnDelete.setWidth(75);
		tblclmnDelete.setText("Delete");
		tableViewerColumn_3.setLabelProvider(new ColumnLabelProvider(){
		    @Override
			public void update(ViewerCell cell) {
	    	     TableItem item = (TableItem) cell.getItem();

	    	     Composite buttonPane = new Composite(table, SWT.NONE);
	    	     buttonPane.setLayout(new FillLayout());

	    	     Button button = new Button(buttonPane,SWT.NONE);
	    	     button.setText("Delete");
	    	     button.addSelectionListener(new SelectionListener() {
						
					@Override
					public void widgetSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});

	    	     TableEditor editor = new TableEditor(item.getParent());
	    	     editor.grabHorizontal  = true;
	    	     editor.grabVertical = true;
	    	     editor.setEditor(buttonPane, item, cell.getColumnIndex());
	    	     editor.layout();
			}
		});
		
		ModelProvider.INSTANCE.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				tableViewer.refresh();
			}
		});

		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setInput(ModelProvider.INSTANCE.getTasks());
		
		input=new WritableList(ModelProvider.INSTANCE.getTasks(), Task.class);
		tableViewer.setInput(input);
		ViewerSupport.bind(tableViewer,input,BeanProperties.values(new String[] { "done", "task" }));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
