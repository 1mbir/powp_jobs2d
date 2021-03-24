package edu.kis.powp.factories;

import edu.kis.powp.command.ComplexCommand;
import edu.kis.powp.command.OperateToCommand;
import edu.kis.powp.command.SetPositionCommand;

public class ShapeFactory {
    public static ComplexCommand createRectangle(int x, int y, int a, int b){
        ComplexCommand cc = new ComplexCommand();
        cc.addCommand(new SetPositionCommand(x, y));
        cc.addCommand(new OperateToCommand(x+a, y));
        cc.addCommand(new OperateToCommand(x+a, y+b));
        cc.addCommand(new OperateToCommand(x, y+b));
        cc.addCommand(new OperateToCommand(x, y));
        return cc;
    }

    public static ComplexCommand createSquare(int x, int y, int a){
        ComplexCommand cc = new ComplexCommand();
        cc.addCommand(new SetPositionCommand(x, y));
        cc.addCommand(new OperateToCommand(x+a, y));
        cc.addCommand(new OperateToCommand(x+a, y+a));
        cc.addCommand(new OperateToCommand(x, y+a));
        cc.addCommand(new OperateToCommand(x, y));
        return cc;
    }

    public static ComplexCommand createTriangle(int x, int y, int a, int b){
        ComplexCommand cc = new ComplexCommand();
        cc.addCommand(new SetPositionCommand(x, y));
        cc.addCommand(new OperateToCommand(x, y+a));
        cc.addCommand(new OperateToCommand(x+b, y+a));
        cc.addCommand(new OperateToCommand(x, y));
        return cc;
    }

}
