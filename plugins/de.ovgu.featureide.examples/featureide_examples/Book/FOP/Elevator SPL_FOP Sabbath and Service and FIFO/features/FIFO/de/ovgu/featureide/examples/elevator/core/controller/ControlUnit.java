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
package de.ovgu.featureide.examples.elevator.core.controller;

import java.util.LinkedList;
import java.util.Queue;

public class ControlUnit {

	private Queue<Request> requests = new LinkedList<Request>();

	public void trigger(Request request) {
		if (!requests.contains(request)) {
			requests.offer(request);
		}
	}

	private ElevatorState calculateNextState() {
		final int currentFloor = elevator.getCurrentFloor();
		if (!requests.isEmpty()) {
			Request nextRequest = requests.peek();
			int floor = nextRequest.getFloor();
			if (floor > currentFloor) {
				return ElevatorState.MOVING_UP;
			} else if (floor < currentFloor) {
				return ElevatorState.MOVING_DOWN;
			} else {
				requestFinished(requests.poll());
				return ElevatorState.FLOORING;
			}
		}
		return original();
	}

	private void requestFinished(Request request) {
		for (ITickListener listener : this.tickListener) {
			listener.onRequestFinished(elevator, request);
		}
	}

	public int getCurrentFloor() {
		return elevator.getCurrentFloor();
	}

}