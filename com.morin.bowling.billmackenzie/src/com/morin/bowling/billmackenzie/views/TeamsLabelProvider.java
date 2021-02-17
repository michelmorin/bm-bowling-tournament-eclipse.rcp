package com.morin.bowling.billmackenzie.views;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.morin.bowling.billmackenzie.IImageKeys;
import com.morin.bowling.billmackenzie.model.BMTeam;

public class TeamsLabelProvider implements ITableLabelProvider {
	
	private Image image;

	public Image getImage(Object element) {		
		URL url = (Platform.getBundle("com.morin.bowling.tournament")).getEntry(IImageKeys.TEAM);
		image = ImageDescriptor.createFromURL(url).createImage();
		return image;
	}

	public String getText(Object element) {
		String text = String.valueOf(((BMTeam)element).getId()) + " " + ((BMTeam)element).getName();
		return text;
	}

	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	public void dispose() {
		if (image != null)
			image.dispose();
	}

	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	public Image getColumnImage(Object element, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return null;
		case 1:
			return null;
		}
		return null; //$NON-NLS-1$	
	}

	public String getColumnText(Object element, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.valueOf(((BMTeam)element).getId());
		case 1:
			return ((BMTeam)element).getYouth().getName() + " / " + ((BMTeam)element).getAdult().getName();
		}
		return ""; //$NON-NLS-1$	
	}

}
