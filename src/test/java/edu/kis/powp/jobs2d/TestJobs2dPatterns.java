package edu.kis.powp.jobs2d;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.kis.legacy.drawer.panel.DefaultDrawerFrame;
import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.factories.ShapeFactory;
import edu.kis.powp.jobs2d.drivers.adapter.JaneDrawerAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.LineDrawerAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.DrawingAdapter;
import edu.kis.powp.jobs2d.events.SelectChangeVisibleOptionListener;
import edu.kis.powp.jobs2d.events.SelectJaneFigureOptionListener;
import edu.kis.powp.jobs2d.events.SelectJoe1FigureOptionListener;
import edu.kis.powp.jobs2d.events.SelectJoe2FigureOptionListener;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;

public class TestJobs2dPatterns {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Setup test concerning preset figures in context.
	 * 
	 * @param application Application context.
	 */
	private static void setupPresetTests(Application application) {
		SelectJoe1FigureOptionListener selectJoe1FigureOptionListener = new SelectJoe1FigureOptionListener(
				DriverFeature.getDriverManager());

		application.addTest("Figure Joe 1", selectJoe1FigureOptionListener);

		SelectJoe2FigureOptionListener selectJoe2FigureOptionListener = new SelectJoe2FigureOptionListener(
				DriverFeature.getDriverManager());
		application.addTest("Figure Joe 2", selectJoe2FigureOptionListener);

		SelectJaneFigureOptionListener selectJaneFigureOptionListener = new SelectJaneFigureOptionListener(
				DriverFeature.getDriverManager());
		application.addTest("Figure Jane", selectJaneFigureOptionListener);

		ActionListener myRectangle = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShapeFactory.createRectangle(10,10, 50, 100).execute(DriverFeature.getDriverManager().getCurrentDriver());
			}
		};

		application.addTest("My Rectangle", myRectangle);

		ActionListener mySquare = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShapeFactory.createSquare(10,10, 100).execute(DriverFeature.getDriverManager().getCurrentDriver());
			}
		};

		application.addTest("My Square", mySquare);

		ActionListener myTriangle = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShapeFactory.createTriangle(10,10, 50, 100).execute(DriverFeature.getDriverManager().getCurrentDriver());
			}
		};

		application.addTest("My Triangle", myTriangle);
	}

	/**
	 * Setup driver manager, and set default driver for application.
	 * 
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {
		Job2dDriver loggerDriver = new LoggerDriver();
		DriverFeature.addDriver("Logger Driver", loggerDriver);
		DriverFeature.getDriverManager().setCurrentDriver(loggerDriver);

		Job2dDriver testDriver = new DrawingAdapter(DrawerFeature.getDrawerController());
		DriverFeature.addDriver("Buggy Simulator", testDriver);

		Job2dDriver specialDriver = new LineDrawerAdapter(DrawerFeature.getDrawerController());
		((LineDrawerAdapter)specialDriver).changeLine(LineDrawerAdapter.LineType.SPECIAL);
		DriverFeature.addDriver("Special Driver", specialDriver);

		Job2dDriver dottedDriver = new LineDrawerAdapter(DrawerFeature.getDrawerController());
		((LineDrawerAdapter)dottedDriver).changeLine(LineDrawerAdapter.LineType.DOTTED);
		DriverFeature.addDriver("Dotted Driver", dottedDriver);

		Job2dDriver janeDriver = new JaneDrawerAdapter();
		DriverFeature.addDriver("Jane Driver", janeDriver);

		DriverFeature.updateDriverInfo();
	}

	/**
	 * Auxiliary routines to enable using Buggy Simulator.
	 * 
	 * @param application Application context.
	 */
	private static void setupDefaultDrawerVisibilityManagement(Application application) {
		DefaultDrawerFrame defaultDrawerWindow = DefaultDrawerFrame.getDefaultDrawerFrame();
		application.addComponentMenuElementWithCheckBox(DrawPanelController.class, "Default Drawer Visibility",
				new SelectChangeVisibleOptionListener(defaultDrawerWindow), true);
		defaultDrawerWindow.setVisible(false);
	}

	/**
	 * Setup menu for adjusting logging settings.
	 * 
	 * @param application Application context.
	 */
	private static void setupLogger(Application application) {
		application.addComponentMenu(Logger.class, "Logger", 0);
		application.addComponentMenuElement(Logger.class, "Clear log",
				(ActionEvent e) -> application.flushLoggerOutput());
		application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
		application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
		application.addComponentMenuElement(Logger.class, "Warning level",
				(ActionEvent e) -> logger.setLevel(Level.WARNING));
		application.addComponentMenuElement(Logger.class, "Severe level",
				(ActionEvent e) -> logger.setLevel(Level.SEVERE));
		application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("2d jobs Visio");
				DrawerFeature.setupDrawerPlugin(app);
				setupDefaultDrawerVisibilityManagement(app);

				DriverFeature.setupDriverPlugin(app);
				setupDrivers(app);
				setupPresetTests(app);
				setupLogger(app);

				app.setVisibility(true);
			}
		});
	}

}
