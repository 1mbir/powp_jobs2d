package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectJoe2FigureOptionListener implements ActionListener {

    private DriverManager driverManager;

    public SelectJoe2FigureOptionListener(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FiguresJoe.figureScript2(driverManager.getCurrentDriver());
    }
}
