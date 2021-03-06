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

package de.sep2011.funckit.controller.listener.settings;

import de.sep2011.funckit.controller.Controller;
import de.sep2011.funckit.model.sessionmodel.Settings;
import de.sep2011.funckit.view.View;
import javax.swing.AbstractAction;
import javax.swing.JColorChooser;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.event.ActionEvent;

public class ChangeColorActionListener extends AbstractAction {

    private static final long serialVersionUID = -4697814765198499817L;
    private final View view;
    private final Controller controller;
    private JColorChooser chooserPane;
    private String settingKey;

    public ChangeColorActionListener(View view, Controller controller) {
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        settingKey = event.getActionCommand();
        Settings settings = controller.getSessionModel().getSettings();
        Color oldColor = settings.get(settingKey, Color.class);

        if (oldColor == null) {
            chooserPane = new JColorChooser();
        } else {
            chooserPane = new JColorChooser(oldColor);
        }

        JDialog colorDialog = JColorChooser.createDialog(view.getMainRootPane(), settingKey, true,
                chooserPane, new OkListener(), null);
        colorDialog.setVisible(true);
    }

    private class OkListener extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            Color newColor = chooserPane.getColor();
            Settings settings = controller.getSessionModel().getSettings();
            settings.set(settingKey, newColor);

        }

    }

}
