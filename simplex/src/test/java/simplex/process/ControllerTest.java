package simplex.process;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ControllerTest {


    @Test
    public void should_returnProperResult_when_Maximization1() {
        String objective = "5x1 + 10x2 + 8x3";
        String constraints = "3x1 + 5x2 + 2x3 >= 60 \n 4x1 + 4x2 + 4x3 <= 72 \n 2x1 + 4x2 + 5x3 <= 100";

        Controller controller = new Controller(objective, constraints, true);
        controller.makeSimplex();
        double totalCost = controller.getTableau().getTotalCost();

        assertEquals(totalCost, 180, 0.000001);
    }

    @Test
    public void should_returnProperResult_when_Minimization1() {
        String objective = "5x1 + 10x2 + 8x3";
        String constraints = "3x1 + 5x2 + 2x3 >= 60 \n 4x1 + 4x2 + 4x3 <= 72 \n 2x1 + 4x2 + 5x3 <= 100";

        Controller controller = new Controller(objective, constraints, false);
        controller.makeSimplex();
        double totalCost = controller.getTableau().getTotalCost();

        assertEquals(totalCost, 105, 0.000001);
    }


    @Test
    public void should_returnProperResult_when_Maximization2() {
        String objective = "4x1 + 3x2";
        String constraints = "2x1 + x2 <= 1000 \n x1 + x2 <= 800 \n    x1 <= 400 \n   x2 <= 700 \n x1 >= 0 \n x2 >= 0";

        Controller controller = new Controller(objective, constraints, true);
        controller.makeSimplex();
        double totalCost = controller.getTableau().getTotalCost();

        assertEquals(totalCost, 2600, 0.0000001);
    }


    @Test
    public void should_returnProperResult_when_Minimization2() {
        String objective = "4x1 + 3x2";
        String constraints = "2x1 + x2 <= 1000 \n x1 + x2 <= 800 \n x1 <= 400 \n x2 <= 700 \n";

        Controller controller = new Controller(objective, constraints, false);
        controller.makeSimplex();
        double totalCost = controller.getTableau().getTotalCost();

        assertEquals(totalCost, 0, 0.0000001);
    }


    @Test
    public void should_returnProperResult_when_Maximization3() {
        String objective = " 5x1 + 3x2";
        String constraints = "2x1 + 4x2 <= 12 \n 2x1 + 2x2 = 10 \n 5x1 + 2x2 >= 10  \n x1 >= 0 \n x2 >= 0";

        Controller controller = new Controller(objective, constraints, true);
        controller.makeSimplex();
        double totalCost = controller.getTableau().getTotalCost();

        assertEquals(totalCost, 25, 0.0000001);
    }


    @Test
    public void should_returnProperResult_when_Minimization3() {
        String objective = " 5x1 + 3x2";
        String constraints = "2x1 + 4x2 <= 12 \n 2x1 + 2x2 = 10 \n 5x1 + 2x2 >= 10  \n x1 >= 0 \n x2 >= 0";

        Controller controller = new Controller(objective, constraints, false);
        controller.makeSimplex();
        double totalCost = controller.getTableau().getTotalCost();

        assertEquals(totalCost, 23, 0.000001);
    }


    @Test
    public void should_returnProperResult_when_Maximization4() {
        String objective = "x1 + 2x2 + 3x3 - x4";
        String constraints = "x1 + 2x2 + 3x3 = 15 \n 2x1 + x2 + 5x3 = 20 \n x1 + 2x2 + x3 + x4 = 10 \n";

        Controller controller = new Controller(objective, constraints, true);
        controller.makeSimplex();
        double totalCost = controller.getTableau().getTotalCost();

        assertEquals(totalCost, 15, 0.0000001);
    }




}