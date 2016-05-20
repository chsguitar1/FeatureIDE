/* FeatureIDE - A Framework for Feature-Oriented Software Development
 * Copyright (C) 2005-2016  FeatureIDE team, University of Magdeburg, Germany
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
package de.ovgu.featureide.fm.core.explanations;

import java.util.Collection;
import java.util.List;

import org.prop4j.Literal;
import org.prop4j.Node;

import de.ovgu.featureide.fm.core.base.IConstraint;
import de.ovgu.featureide.fm.core.base.IFeature;
import de.ovgu.featureide.fm.core.base.IFeatureModel;
import de.ovgu.featureide.fm.core.editing.NodeCreator;

/**
 * The class deadFeatures generates explanations for dead features. It uses a logic truth maintenance system (LTMS)
 * and its boolean constraint propagation (BCP).
 * 
 * @author "Ananieva Sofia"
 */
public class DeadFeatures {

	/**
	 * The model after a change (with a constraint that makes a feature dead).
	 */
	private static IFeatureModel model; 

	/**
	 * Explains dead features using boolean constraint propagation. Sets initial truth value assumptions of dead features to true
	 * and propagates them until a violation in a clause occurs.
	 * 
	 * @param newModel the model with the new constraint which leads to a dead feature
	 * @param deadFeatures a list of dead features
	 * @param c the constraint which leads to a dead feature
	 * @return String an explanation why the feature(s) is dead
	 */
	public String explain(IFeatureModel newModel, Collection<IFeature> deadFeatures, IConstraint c) {
		String reason = "";
		setNewModel(newModel);
		Node constr = c.getNode(); // constraint node which makes a feature dead
		Node node = NodeCreator.createNodes(model, true).toCNF();
		Node[] clauses = node.getChildren();

		for (IFeature deadFeature : deadFeatures) {
			Literal deadF = getLiteralFromNode(constr, deadFeature);
			if (deadF == null) { // possible that constraint does not contain the dead feature. Instantiate the dead literal
				deadF = new Literal(deadFeature.getName());
			}
			LTMS ltms = new LTMS(model);

			// generate explanation which stops after first violation with "used" clauses in stack
			String tmpReason = "Feature " + deadF + " is dead, because: \n";
			List<String> explList = ltms.explainDeadF(clauses, deadF);
			if (explList.isEmpty()){
				tmpReason += "No explanation possible";
			}
			else{	
				for (String tmp : explList) {
					tmpReason += tmp + ",\n";
				}
				int lastChar = tmpReason.lastIndexOf(",");
				tmpReason = tmpReason.substring(0, lastChar);
			}
			reason += tmpReason + "\n\n";
		}
		return reason;
	}

	/**
	 * Returns a literal from a node (the constraint which leads to dead feature(s)) with the same name
	 * as the specified feature as BCP only works with literals and not with features.
	 * 
	 * @param node the constraint which leads to dead feature(s)
	 * @param l the dead feature
	 * @return the respective literal from the constraint node which leads to the dead feature
	 */
	private Literal getLiteralFromNode(Node node, IFeature l) {
		Literal res = null;
		if (node instanceof Literal) {
			Literal lit = (Literal) node;
			if (lit.var.toString().equals(l.getName().toString())) {
				res = lit;
			}
			return res;
		}
		Node[] childs = node.getChildren();
		if (childs != null) {
			for (Node child : childs) {
				res = getLiteralFromNode((child), l);
				if (res != null) {
					return res;
				}
			}
		}
		return res;
	}

	/**
	 * Sets the model with the new constraint which lead to a dead feature.
	 * 
	 * @param model the model with the new constraint
	 */
	private void setNewModel(IFeatureModel newModel) {
		model = newModel;
	}

	/**
	 * Gets the model with the new constraint. Used for tooltips to get the correct constraint index.
	 * 
	 * @return the model with the new constraint
	 */
	public static IFeatureModel getNewModel() {
		return model;
	}
}