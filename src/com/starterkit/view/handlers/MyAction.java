package com.starterkit.view.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import com.starterkit.rcp.view.model.ModelProvider;
import com.starterkit.rcp.view.model.Task;

public class MyAction implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		InputDialog inputDialog = new InputDialog(new Shell(), "Add new task",
	            "Write your new task", null, new IInputValidator() {
					
					@Override
					public String isValid(String newText) {
						// TODO Auto-generated method stub
						return null;
					}
				});
		inputDialog.create();
		inputDialog.open();
		Task task = new Task(inputDialog.getValue(), false);
		
		ModelProvider.INSTANCE.addTask(task);

		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
