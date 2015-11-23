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
package de.ovgu.featureide.fm.core.base.event;

/**
 * Provides constants for <code>IFeatureModelListener</code>.
 * 
 * @author Thomas Thuem
 */
public interface PropertyConstants {

	String MODEL_DATA_LOADED = "MODEL_DATA_LOADED";
	String REDRAW_DIAGRAM = "REDRAW_DIAGRAM";
	String MODEL_DATA_CHANGED = "MODEL_DATA_CHANGED";
	String MODEL_LAYOUT_CHANGED = "MODEL_LAYOUT_CHANGED";
	String LEGEND_LAYOUT_CHANGED = "LEGEND_LAYOUT_CHANGED";
	String REFRESH_ACTIONS = "REFRESH_ACTIONS";
	String NAME_CHANGED = "NAME_CHANGED";
	String LOCATION_CHANGED = "LOCATION_CHANGED";
	String COLOR_CHANGED = "COLOR_CHANGED";
	String MANDATORY_CHANGED = "MANDATORY_CHANGED";
	String HIDDEN_CHANGED = "HIDDEN_CHANGED";
	String PARENT_CHANGED = "PARENT_CHANGED";
	String CHILDREN_CHANGED = "CHILDREN_CHANGED";
	String FEATURE_NAME_CHANGED = "NAME_CHANGED";
	String ATTRIBUTE_CHANGED = "ATTRIBUTE_CHANGED";
	String CONSTRAINT_SELECTED = "CONSTRAINT_SELECTED";

	String STRUCTURE_CHANGED = "STRUCTURE_CHANGED";

	String FEATURE_ADD = "FEATURE_ADD";
	String FEATURE_DELETE = "FEATURE_DELETE";
	String FEATURE_MODIFY = "FEATURE_MODIFY";

	String CONSTRAINT_ADD = "CONSTRAINT_ADD";
	String CONSTRAINT_DELETE = "CONSTRAINT_DELETE";
	String CONSTRAINT_MODIFY = "CONSTRAINT_MODIFY";
	String CONSTRAINT_MOVE = "CONSTRAINT_MOVE";

}