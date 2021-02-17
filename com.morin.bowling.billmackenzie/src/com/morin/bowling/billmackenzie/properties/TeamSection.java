/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.morin.bowling.billmackenzie.properties;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.morin.bowling.billmackenzie.Activator;
import com.morin.bowling.billmackenzie.model.BMTeam;
import com.morin.bowling.billmackenzie.model.BMTournament;

public class TeamSection
    extends AbstractPropertySection {
	
	private BMTeam currentTeam;
	private BMTeam previousTeam;

    private Text labelText;
    
    private IPropertyChangeListener teamPropertyListener;

    private ModifyListener listener = new ModifyListener() {

        public void modifyText(ModifyEvent arg0) {
        	BMTournament tournament = Activator.getDefault().getTournament();
			if (currentTeam != null)
			{
				currentTeam.setBowlingCentre(labelText.getText());
				tournament.updateTeam(currentTeam);
				currentTeam.teamChanged();
			}
        }      	
    };

    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        Assert.isTrue(selection instanceof IStructuredSelection);
        Object input = ((IStructuredSelection) selection).getFirstElement();
        Assert.isTrue(input instanceof BMTeam);
        currentTeam = (BMTeam) input;
        inputChanged(previousTeam, currentTeam);
    }
    
public void inputChanged(Object oldInput, Object newInput) {
		
	    if (oldInput != newInput) { // if not the same

	      if (newInput != null) { // add listener to new - fires even if old is null
	        ((BMTeam) newInput).addPropertyChangeListener(teamPropertyListener);
	      }

	      if (oldInput != null) { // remove from old - fires even if new is null
	        ((BMTeam) oldInput).removePropertyChangeListener(teamPropertyListener);
	      }
	    }
}

    public void createControls(Composite parent,
            TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        Composite composite = getWidgetFactory().createFlatFormComposite(parent);
        FormData data;
        
        teamPropertyListener = new IPropertyChangeListener() 
        {        	
        	public void propertyChange(PropertyChangeEvent event) 
        	{        		
        		if (event.getProperty() == "teamChanged")
  	          	{
        			if ( !labelText.isFocusControl() )
        				refresh();
  	          	}
        	}
        };

        labelText = getWidgetFactory().createText(composite, ""); //$NON-NLS-1$
        data = new FormData();
        data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        labelText.setLayoutData(data);
        labelText.addModifyListener(listener);

        CLabel labelLabel = getWidgetFactory().createCLabel(composite, "Bowling Centre:"); //$NON-NLS-1$
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        //data.right = new FormAttachment(labelText, -ITabbedPropertyConstants.HSPACE );
        data.top = new FormAttachment(labelText, 0, SWT.CENTER);
        labelLabel.setLayoutData(data);
    }

    public void refresh() {
    	labelText.removeModifyListener(listener);
    	if ( currentTeam != null)
    	{
    		labelText.setText(currentTeam.getBowlingCentre());
    	}
        labelText.addModifyListener(listener);
    }
}