package com.morin.bowling.platform.dialogs;

import java.util.ArrayList;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.morin.bowling.platform.Tournament;

public class NewTournamentDialog extends TitleAreaDialog {
	
	private TableViewer viewer;
	private Tournament selectedTournament;

  public NewTournamentDialog(Shell shell) {
    super(shell);
  }

  /**
   * @see org.eclipse.jface.window.Window#create() We complete the dialog with
   *      a title and a message
   */
  public void create() {
    super.create();
    setTitle("New Tournament");
    setMessage("Create a new Tournament");
    getShell().setText("New Tournament");
  }
  
  class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
			IExtensionPoint extPoint = extRegistry.getExtensionPoint("com.morin.bowling.platform.tournament");
			IExtension[] extensions = extPoint.getExtensions();
			ArrayList<Tournament> list = new ArrayList<Tournament>();
			for (int i = 0; i < extensions.length; i++) {
				IConfigurationElement[] confElement = extensions[i].getConfigurationElements();
				for (int j = 0; j < confElement.length; j++) {
						Tournament tournament = new Tournament(confElement[j].getAttribute("perspective"), confElement[j].getAttribute("name"), confElement[j].getAttribute("description"));
						list.add(tournament);
				}
			}
			return list.toArray(new Tournament[list.size()]);
		}
	}

	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

  /**
   * @see org.eclipse.jface.dialogs.Dialog#
   *      createDialogArea(org.eclipse.swt.widgets.Composite) Here we fill the
   *      center area of the dialog
   */
  protected Control createDialogArea(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);
    GridLayout layout = new GridLayout();
    layout.numColumns=2;
    container.setLayout(layout);
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    container.setLayoutData(data);
    
    viewer = new TableViewer(container, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
	viewer.setContentProvider(new ViewContentProvider());
	viewer.setLabelProvider(new ViewLabelProvider());
	viewer.setInput(getShell());
	data = new GridData(GridData.FILL_HORIZONTAL);
	data.horizontalSpan = 2;
	viewer.getTable().setLayoutData(data);
    
    /*Label label = new Label(container, SWT.RIGHT);
    label.setText("Name:");
    
    tournamentNameText = new Text(container,SWT.SINGLE|SWT.BORDER);
    data = new GridData(GridData.FILL_HORIZONTAL);
    tournamentNameText.setLayoutData(data);  */
    
    return container;
  }
  
  public Tournament getSelectedTournament() {
	  
      return selectedTournament;
  }
  
  public void setFocus() {
		viewer.getControl().setFocus();
	}
  
  protected void buttonPressed(int buttonId) {
      if (buttonId == IDialogConstants.OK_ID) 
      {
    	 IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
    	 selectedTournament = (Tournament)selection.getFirstElement();
      }
      super.buttonPressed(buttonId);
  }
}
