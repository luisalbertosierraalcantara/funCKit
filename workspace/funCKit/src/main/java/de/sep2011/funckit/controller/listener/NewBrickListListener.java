/*
 * funCKit - functional Circuit Kit
 * Copyright (C) 2013  Lukas Elsner <open@mindrunner.de>
 * Copyright (C) 2013  Peter Dahlberg <catdog2@tuxzone.org>
 * Copyright (C) 2013  Julian Stier <mail@julian-stier.de>
 * Copyright (C) 2013  Sebastian Vetter <mail@b4sti.eu>
 * Copyright (C) 2013  Thomas Poxrucker <poxrucker_t@web.de>
 * Copyright (C) 2013  Alexander Treml <alex.treml@directbox.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.sep2011.funckit.controller.listener;

import de.sep2011.funckit.controller.Controller;
import de.sep2011.funckit.controller.CreateTool;
import de.sep2011.funckit.model.sessionmodel.SessionModel;
import de.sep2011.funckit.util.Log;
import de.sep2011.funckit.view.NewBrickList;
import de.sep2011.funckit.view.View;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.MouseEvent;

/**
 * Listener that reacts on events generated by the {@link NewBrickList}.
 */
public class NewBrickListListener extends AbstractMouseListener implements
        ListSelectionListener {

    private final Controller controller;

    /**
     * Constructor that expects the current {@link Controller} and {@link View}
     * reference.
     *
     * @param controller Application controller object, should not be null
     * @param view       associated View object, should not be null
     */
    public NewBrickListListener(View view, Controller controller) {
        this.controller = controller;
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        int index = ((JList) event.getSource()).getSelectedIndex();
        setToolByIndex(index);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int index = ((JList) mouseEvent.getSource()).getSelectedIndex();
        setToolByIndex(index);
    }

    private void setToolByIndex(int index) {
        SessionModel s = controller.getSessionModel();

        if (index < 0 && !s.getNewBrickList().isEmpty()) {
            s.setCurrentBrick(s.getNewBrickList().get(0));
        } else if (index >= 0) {
            s.setCurrentBrick(s.getNewBrickList().get(index));
        }
        s.setTool(new CreateTool(controller));

        Log.gl().debug(
                "Selected in NewBrickList: " + s.getCurrentBrick().getName());
    }

}