/* FeatureIDE - A Framework for Feature-Oriented Software Development
 * Copyright (C) 2005-2015  FeatureIDE team, University of Magdeburg, Germany
 *
 * This file is part of FeatureIDE.
 * 
 * FeatureIDE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * FeatureIDE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with FeatureIDE.  If not, see <http://www.gnu.org/licenses/>.
 *
 * See http://featureide.cs.ovgu.de/ for further information.
 */
package de.ovgu.featureide.fm.ui.editors.featuremodel.layouts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import de.ovgu.featureide.fm.core.base.FeatureUtils;
import de.ovgu.featureide.fm.core.base.IFeature;
import de.ovgu.featureide.fm.core.base.IFeatureStructure;
import de.ovgu.featureide.fm.core.functional.Functional;

/**
 * A feature representation for layout.
 * 
 * @author Patrick Sulkowski
 */
public class LayoutableFeature {

	private boolean showHidden;
	private IFeature feature;
	
	public LayoutableFeature(IFeatureStructure featureStructure, boolean showHidden) {
		this (featureStructure.getFeature(), showHidden);
	}

	public LayoutableFeature(IFeature feature, boolean showHidden) {
		this.feature = feature;
		this.showHidden = showHidden;
	}

	public LinkedList<LayoutableFeature> getChildren() {

		LinkedList<LayoutableFeature> children = new LinkedList<LayoutableFeature>();

		for (IFeature child : FeatureUtils.convertToFeatureList(feature.getStructure().getChildren())) {
			if (showHidden) {
				children.add(new LayoutableFeature(child, showHidden));
			} else {
				if (!child.getStructure().isHidden()) {
					children.add(new LayoutableFeature(child, showHidden));
				}
			}

		}
		return children;

	}

	public LayoutableFeature getFirstChild() {
		if (getChildren().isEmpty())
			return null;
		return getChildren().get(0);
	}

	public LayoutableFeature getLastChild() {
		LinkedList<LayoutableFeature> children = getChildren();
		if (!children.isEmpty()) {
			return children.getLast();
		}
		return null;
	}

	public boolean hasChildren() {
		return !getChildren().isEmpty();
	}

	public IFeature getFeature() {
		return feature;
	}

	public static Collection<IFeature> convertFeatures(Iterable<IFeature> features, boolean showHidden) {
		if (showHidden) {
			return Functional.toList(features);
		} else {
			final ArrayList<IFeature> newFeatures = new ArrayList<IFeature>();
			for (IFeature feature : features) {
				if (feature.getStructure().hasHiddenParent()) {
					newFeatures.add(feature);
				}
			}
			return newFeatures;
		}
	}

	public static boolean isHidden(IFeature feature, boolean showHidden) {
		if (showHidden)
			return false;
		if (!feature.getStructure().isRoot())
			return (feature.getStructure().isHidden() || isHidden(feature.getStructure().getParent().getFeature(), showHidden));
		else
			return feature.getStructure().isHidden();
	}

}
